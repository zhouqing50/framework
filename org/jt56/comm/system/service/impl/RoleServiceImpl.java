package jt56.comm.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jt56.comm.system.dao.ResourceDao;
import jt56.comm.system.dao.RoleDao;
import jt56.comm.system.dao.UserDao;
import jt56.comm.system.model.RoleAndResource;
import jt56.comm.system.model.Tresource;
import jt56.comm.system.model.Trole;
import jt56.comm.system.model.Tuser;
import jt56.comm.system.model.UserAndRole;
import jt56.comm.system.pageModel.Role;
import jt56.comm.system.pageModel.SessionInfo;
import jt56.comm.system.pageModel.Tree;
import jt56.comm.system.service.RoleServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RoleServiceImpl implements RoleServiceI {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ResourceDao resourceDao;


	public void add(Role role, SessionInfo sessionInfo) {
		Trole t = new Trole();
		BeanUtils.copyProperties(role, t);
		if (null != role.getPid() && !"".equalsIgnoreCase(role.getPid())) {
			t.setPid(role.getPid());
		}
		roleDao.save(t);

		// 刚刚添加的角色，赋予给当前的用户
		Tuser user = userDao.getUserById(sessionInfo.getId());
		List<UserAndRole> urList = new ArrayList<UserAndRole>();
		UserAndRole url = new UserAndRole();
		url.setRoleId(role.getId());
		url.setUserId(user.getId());
		urList.add(url);
		userDao.saveUserAndRole(urList);
	}


	public Role get(String id) {
		Role r = new Role();
		Trole t = roleDao.getRoleById(id);
		//Trole t = roleDao.get("select distinct t from Trole t left join fetch t.tresources resource where t.id = :id", params);
		if (t != null) {
			BeanUtils.copyProperties(t, r);
			if(null != t.getPid() && !"".equalsIgnoreCase(t.getPid())){
				r.setPid(t.getPid());
				r.setPname(roleDao.getRoleById(t.getPid()).getName());
			}
			
			List<Tresource> s = resourceDao.getResourceByRoleId(id);
			if (s != null && !s.isEmpty()) {
				boolean b = false;
				String ids = "";
				String names = "";
				for (Tresource tr : s) {
					if (b) {
						ids += ",";
						names += ",";
					} else {
						b = true;
					}
					ids += tr.getId();
					names += tr.getName();
				}
				r.setResourceIds(ids);
				r.setResourceNames(names);
			}
		}
		return r;
	}


	public void edit(Trole role) {
		
		roleDao.update(role);
	}


	public List<Role> treeGrid(SessionInfo sessionInfo) {
		List<Role> rl = new ArrayList<Role>();
		List<Trole> tl = null;
		Map<String, Object> params = new HashMap<String, Object>();
		if (sessionInfo != null) {
			params.put("userId", sessionInfo.getId());// 查自己有权限的角色
		}
		tl = roleDao.getRoleByParams(params);
		if (tl != null && tl.size() > 0) {
			for (Trole t : tl) {
				Role r = new Role();
				BeanUtils.copyProperties(t, r);
				r.setIconCls("status_online");
				if (null !=t.getPid() && !"".equalsIgnoreCase(t.getPid())) {
					r.setPid(t.getPid());
					r.setPname(roleDao.getRoleById(t.getPid()).getName());
				}
				List<Tresource> s = resourceDao.getResourceByRoleId(t.getId());
				if (s != null && !s.isEmpty()) {
					boolean b = false;
					String ids = "";
					String names = "";
					for (Tresource tr : s) {
						if (b) {
							ids += ",";
							names += ",";
						} else {
							b = true;
						}
						ids += tr.getId();
						names += tr.getName();
					}
					r.setResourceIds(ids);
					r.setResourceNames(names);
				}
				rl.add(r);
			}
		}
		return rl;
	}


	@Transactional
	public void delete(String id) {
		//删除此条记录
		roleDao.delete(id);
		roleDao.deleteRoleAndResourceByRid(id);//关联表数据
		//删除子角色
		roleDao.deleteRoleByPid(id);
		roleDao.deleteRoleAndResourceByPid(id);//关联表数据
	}


	public List<Tree> tree(SessionInfo sessionInfo) {
		List<Trole> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		Map<String, Object> params = new HashMap<String, Object>();
		if (sessionInfo != null) {
			params.put("userId", sessionInfo.getId());// 查自己有权限的角色
		}
		l = roleDao.getRoleByParams(params);
		if (l != null && l.size() > 0) {
			for (Trole t : l) {
				Tree tree = new Tree();
				BeanUtils.copyProperties(t, tree);
				tree.setText(t.getName());
				tree.setIconCls("status_online");
				if (null !=t.getPid() && !"".equalsIgnoreCase(t.getPid())) {
					tree.setPid(t.getPid());
				}
				lt.add(tree);
			}
		}
		return lt;
	}


	public List<Tree> allTree() {
		return this.tree(null);
	}


	@Transactional
	public void grant(Role role) {
		String roleId  = role.getId();
		List<RoleAndResource> rrL = new ArrayList<RoleAndResource>();
		if (role.getResourceIds() != null && !role.getResourceIds().equalsIgnoreCase("")) {
			roleDao.deleteRoleAndResourceByRid(roleId);
			for (String id : role.getResourceIds().split(",")) {
				RoleAndResource rr = new RoleAndResource();
				rr.setRoleId(roleId);
				rr.setResourceId(id);
				rrL.add(rr);
			}
			roleDao.saveRoleAndResource(rrL);
		} 
	}

}
