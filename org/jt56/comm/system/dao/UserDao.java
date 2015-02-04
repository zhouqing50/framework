package jt56.comm.system.dao;

import java.util.List;

import jt56.comm.system.model.Tresource;
import jt56.comm.system.model.Tuser;
import jt56.comm.system.model.UserAndRole;
import jt56.comm.system.pageModel.User;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;



@Repository
public interface UserDao {
	
	public Tuser findUserByParams(User user);
	
	public List<Tresource> getResourceById(String id);
	
	public Tuser getUserById(String id);
	
	public Tuser getUserAllById(String id);
	
	public void save(Tuser user);
	
	public void delete(String id);
	
	/**把用户和用户下的角色的关联表数据先清空*/
	public void deleteUserAndRoleByUid(String id);
	
	/**再把用户和用户下的角色的关联关系保存*/
	public void saveUserAndRole(List<UserAndRole> urL);
	
	public List<Tuser> queryUserList(@Param("user")User user,	RowBounds rowBounds,@Param("sort")String sort, @Param("order")String order);

	public int queryTotal(@Param("user")User user);
}
