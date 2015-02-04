package ${bussPackage}.${entityPackage}.dao;

import java.util.List;


import ${bussPackage}.${entityPackage}.entity.${className};


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 
 * <br>
 * <b>功能：</b>${className}Dao<br>
 * <b>作者：</b>zhouq<br>
 * <b>版权所有：<b>版权所有(C) 2013，www.jt56.org<br>
 */
 @Repository
public interface ${className}Dao{
	
	public ${className} getEntityById(String id);
	
	public void add(${className} entity);
	
	public void update(${className} entity);
	
	public void delete(String id);

	public List<${className}> queryEntityList(@Param("entity")${className} entity,@Param("pageNo")int pageNo, @Param("pageSize")int pageSize,@Param("sort")String sort, @Param("order")String order);
	
	public int queryTotal(@Param("entity")${className} entity);
}
