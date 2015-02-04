package jt56.comm.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jt56.comm.system.dao.RoleDao;
import jt56.comm.system.dao.UserDao;
import jt56.comm.system.model.Tresource;
import jt56.comm.system.model.Trole;
import jt56.comm.system.model.Tuser;
import jt56.comm.system.model.UserAndRole;
import jt56.comm.system.pageModel.DataGrid;
import jt56.comm.system.pageModel.PageHelper;
import jt56.comm.system.pageModel.SessionInfo;
import jt56.comm.system.pageModel.User;
import jt56.comm.system.service.UserServiceI;
import jt56.comm.system.util.MD5Util;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User login(User user) {

		user.setPwd(MD5Util.md5(user.getPwd()));
		Tuser t = userDao.findUserByParams(user);
		if (t != null) {
			BeanUtils.copyProperties(t, user);
			return user;
		}
		return null;
	}

	
	synchronized public void reg(User user) throws Exception {

		if (null != userDao.findUserByParams(user)) {
			throw new Exception("登录名已存在！");
		} else {
			Tuser u = new Tuser();
			u.setId(UUID.randomUUID().toString());
			u.setName(user.getName());
			u.setPwd(MD5Util.md5(user.getPwd()));
			u.setCreatedatetime(new Date());
			userDao.save(u);
		}
	}

	
	public DataGrid dataGrid(User user, PageHelper ph) {
		DataGrid dg = new DataGrid();
		List<User> ul = new ArrayList<User>();

		List<Tuser> l = userDao.queryUserList(user, new RowBounds((ph.getPage()-1)*ph.getRows(), ph.getRows()), ph.getSort().toUpperCase(), ph.getOrder().toUpperCase());
		
		if (l != null && l.size() > 0) {
			for (Tuser t : l) {
				User u = new User();
				BeanUtils.copyProperties(t, u);
				List<Trole> roles = roleDao.getRoleByUserId(t.getId());
				if (roles != null && !roles.isEmpty()) {
					String roleIds = "";
					String roleNames = "";
					boolean b = false;
					for (Trole tr : roles) {
						if (b) {
							roleIds += ",";
							roleNames += ",";
						} else {
							b = true;
						}
						roleIds += tr.getId();
						roleNames += tr.getName();
					}
					u.setRoleIds(roleIds);
					u.setRoleNames(roleNames);
				}
				ul.add(u);
			}
		}
		dg.setRows(ul);
		dg.setTotal(userDao.queryTotal(user));
		return dg;
	}

	synchronized public void add(User user) throws Exception {

		if (null != userDao.findUserByParams(user)) {
			throw new Exception("登录名已存在！");
		} else {
			Tuser u = new Tuser();
			BeanUtils.copyProperties(user, u);
			u.setId(UUID.randomUUID().toString());
			u.setCreatedatetime(new Date());
			u.setPwd(MD5Util.md5(user.getPwd()));
			userDao.save(u);
		}
	}


	public User get(String id) {
		User user = new User();
		Tuser t = userDao.getUserById(id);
		if (t != null) {
			BeanUtils.copyProperties(t, user);
			return user;
		}
		return null;
	}


	synchronized public void edit(User user) throws Exception {

		if (null != userDao.findUserByParams(user)) {
			throw new Exception("登录名已存在！");
		} else {
			Tuser u = userDao.getUserById(user.getId());
			BeanUtils.copyProperties(user, u, new String[] { "pwd", "createdatetime" });
			u.setModifydatetime(new Date());
		}
	}


	@Transactional
	public void delete(String id) {
		userDao.delete(id);
		userDao.deleteUserAndRoleByUid(id);//把用户和用户下的角色的关联表数据先清空
	}


	public List<String> resourceList(String id) {
		List<String> resourceList = new ArrayList<String>();
		List<Tresource> rL = userDao.getResourceById(id);
		if (rL != null && !rL.isEmpty()) {
			for (Tresource resource : rL) {
				if (resource != null && resource.getUrl() != null) {
					resourceList.add(resource.getUrl());
				}
			}
		}
		return resourceList;
	}


	public void editPwd(User user) {
		if (user != null && user.getPwd() != null && !user.getPwd().trim().equalsIgnoreCase("")) {
			Tuser u = userDao.getUserById(user.getId());
			u.setPwd(MD5Util.md5(user.getPwd()));
			u.setModifydatetime(new Date());
		}
	}


	public boolean editCurrentUserPwd(SessionInfo sessionInfo, String oldPwd, String pwd) {
		Tuser u = userDao.getUserById(sessionInfo.getId());
		if (u.getPwd().equalsIgnoreCase(MD5Util.md5(oldPwd))) {// 说明原密码输入正确
			u.setPwd(MD5Util.md5(pwd));
			u.setModifydatetime(new Date());
			return true;
		}
		return false;
	}

	public void grant(String ids, User user) {
		
		if (ids != null && ids.length() > 0) {
			for (String userId : ids.split(",")) {
				if (userId != null && !userId.equalsIgnoreCase("")) {
					//先清除用户和角色之前的关系
					userDao.deleteUserAndRoleByUid(userId);
					List<UserAndRole> urList = new ArrayList<UserAndRole>();
					if (user.getRoleIds() != null) {
						for (String roleId : user.getRoleIds().split(",")) {
							UserAndRole url = new UserAndRole();
							url.setRoleId(roleId);
							url.setUserId(userId);
							urList.add(url);
						}
					}
					//重新保存用户和角色关系
					userDao.saveUserAndRole(urList);
				}
			}

		}
		
	}

}
