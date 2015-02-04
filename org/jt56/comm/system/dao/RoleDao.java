package jt56.comm.system.dao;

import java.util.List;
import java.util.Map;

import jt56.comm.system.model.RoleAndResource;
import jt56.comm.system.model.Trole;

import org.springframework.stereotype.Repository;



@Repository
public interface RoleDao {
	
	public Trole getRoleById(String id);
	
	public void update(Trole role);
	
	public void save(Trole role);
	
	/**授权，把角色和资源保存到关联表*/
	public void saveRoleAndResource(List<RoleAndResource> rrL);
	
	public void delete(String id);
	
	/**再次授权，把角色和资源的关联表数据先清空*/
	public void deleteRoleAndResourceByRid(String roleId);
	
	public void deleteRoleByPid(String pid);
	
	/**把角色和资源的关联表数据先清空，*/
	public void deleteRoleAndResourceByPid(String pid);
	
	public List<Trole> getRoleListByPid(String pid);
	
	public List<Trole> getRoleByParams(Map<String, Object> params);
	
	public List<Trole> getRoleByUserId(String userId);
}

