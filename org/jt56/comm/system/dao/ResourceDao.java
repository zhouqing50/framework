package jt56.comm.system.dao;

import java.util.List;
import java.util.Map;

import jt56.comm.system.model.Tresource;

import org.springframework.stereotype.Repository;



@Repository
public interface ResourceDao {
	
	public Tresource getTresourceById(String id);

	public List<Tresource> getResourceByParams(Map<String, Object> params);
	
	public List<Tresource> getResourceByRoleId(String id);
	
	public void update(Tresource resource);
	
	public void save(Tresource resource);
	
	public void delete(String id);
	
	public void deleteResourceAndRole(String id);
	
	public void deleteTresourceByPid(String pid);
	
	public void deleteResourceAndRoleByPid(String pid);

}
