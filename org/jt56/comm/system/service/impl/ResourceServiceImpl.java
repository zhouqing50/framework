package jt56.comm.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jt56.comm.system.dao.DicTypeDao;
import jt56.comm.system.dao.ResourceDao;
import jt56.comm.system.dao.RoleDao;
import jt56.comm.system.dao.UserDao;
import jt56.comm.system.model.RoleAndResource;
import jt56.comm.system.model.Tresource;
import jt56.comm.system.model.Trole;
import jt56.comm.system.model.Tuser;
import jt56.comm.system.pageModel.Resource;
import jt56.comm.system.pageModel.SessionInfo;
import jt56.comm.system.pageModel.Tree;
import jt56.comm.system.service.ResourceServiceI;
import jt56.comm.system.util.StringUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ResourceServiceImpl implements ResourceServiceI {

	@Autowired
	private ResourceDao resourceDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;

	@Autowired
	private DicTypeDao dicTypeDao;

	public List<Tree> tree(SessionInfo sessionInfo) {
		List<Tresource> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("resourceTypeId", "menu");// 菜单类型的资源

		if (sessionInfo != null) {
			params.put("userId", sessionInfo.getId());// 自查自己有权限的资源
		}
		l = resourceDao.getResourceByParams(params);
		
		if (l != null && l.size() > 0) {
			for (Tresource r : l) {
				Tree tree = new Tree();
				BeanUtils.copyProperties(r, tree);
				tree.setPid(r.getPid());
				tree.setText(r.getName());
				tree.setIconCls(r.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getUrl());
				tree.setAttributes(attr);
				lt.add(tree);
			}
		}
		return lt;
	}

	public List<Tree> allTree(SessionInfo sessionInfo) {
		List<Tresource> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		Map<String, Object> params = new HashMap<String, Object>();
		if (sessionInfo != null) {
			params.put("userId", sessionInfo.getId());// 查自己有权限的资源
		}
		l = resourceDao.getResourceByParams(params);
		
		if (l != null && l.size() > 0) {
			for (Tresource r : l) {
				Tree tree = new Tree();
				BeanUtils.copyProperties(r, tree);
				tree.setPid(r.getPid());
				tree.setText(r.getName());
				tree.setIconCls(r.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getUrl());
				tree.setAttributes(attr);
				lt.add(tree);
			}
		}
		return lt;
	}

	public List<Resource> treeGrid(SessionInfo sessionInfo) {
		List<Tresource> l = null;
		List<Resource> lr = new ArrayList<Resource>();

		Map<String, Object> params = new HashMap<String, Object>();
		if (sessionInfo != null) {
			params.put("userId", sessionInfo.getId());// 自查自己有权限的资源
		}
		l = resourceDao.getResourceByParams(params);
		
		if (l != null && l.size() > 0) {
			for (Tresource t : l) {
				Resource r = new Resource();
				BeanUtils.copyProperties(t, r);
				if(null != t.getPid() && !"".equalsIgnoreCase(t.getPid())){
					r.setPid(t.getPid());
					r.setPname(resourceDao.getTresourceById(t.getPid()).getName());
				}
				String typeId = t.getResourceTypeId();
				if(!StringUtil.isBlank(typeId)){
					r.setTypeId(typeId);
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("id", typeId);
					map.put("pid", "zylx");
					r.setTypeName(dicTypeDao.getDicTypeByPar(map).getText());
				}	
				if (t.getIcon() != null && !t.getIcon().equalsIgnoreCase("")) {
					r.setIconCls(t.getIcon());
				}
				lr.add(r);
			}
		}

		return lr;
	}

	@Transactional
	public void add(Resource resource, SessionInfo sessionInfo) {
		Tresource t = new Tresource();
		BeanUtils.copyProperties(resource, t);
		if (resource.getPid() != null && !resource.getPid().equalsIgnoreCase("")) {
			//t.setTresource(resourceDao.getTresourceById(resource.getPid()));
			t.setPid(resource.getPid());
		}
		if (resource.getTypeId() != null && !resource.getTypeId().equalsIgnoreCase("")) {
			//t.setTresourcetype(resourceTypeDao.getById(resource.getTypeId()));
			t.setResourceTypeId(resource.getTypeId());
		}
		if (resource.getIconCls() != null && !resource.getIconCls().equalsIgnoreCase("")) {
			t.setIcon(resource.getIconCls());
		}
		resourceDao.save(t);

		// 由于当前用户所属的角色，没有访问新添加的资源权限，所以在新添加资源的时候，将当前资源授权给当前用户的所有角色，以便添加资源后在资源列表中能够找到
		Tuser user = userDao.getUserById(sessionInfo.getId());
		// 查自己有权限的角色
		List<Trole> roles = roleDao.getRoleByUserId(user.getId());
		List<RoleAndResource> rrL = new ArrayList<RoleAndResource>();
		for (Trole r : roles) {
			RoleAndResource rr = new RoleAndResource();
			rr.setRoleId(r.getId());
			rr.setResourceId(t.getId());
			rrL.add(rr);
		}
		roleDao.saveRoleAndResource(rrL);
	}

	@Transactional
	public void delete(String id) {
		//删除此条记录
		resourceDao.delete(id);
		resourceDao.deleteResourceAndRole(id);//关联表数据
		//删除子资源
		resourceDao.deleteTresourceByPid(id);
		resourceDao.deleteResourceAndRoleByPid(id);//关联表数据
	}

	public void edit(Tresource resource) {
		
		resourceDao.update(resource);
	}

	public Resource get(String id) {

		Tresource t = resourceDao.getTresourceById(id);
		Resource r = new Resource();
		BeanUtils.copyProperties(t, r);
		if (t.getTresource() != null) {
			r.setPid(t.getTresource().getId());
			r.setPname(t.getTresource().getName());
		}
		String typeId = t.getResourceTypeId();
		if(!StringUtil.isBlank(typeId)){
			r.setTypeId(typeId);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", typeId);
			map.put("pid", "zylx");
			r.setTypeName(dicTypeDao.getDicTypeByPar(map).getText());
		}	
		if (t.getIcon() != null && !t.getIcon().equalsIgnoreCase("")) {
			r.setIconCls(t.getIcon());
		}
		return r;
	}

}
