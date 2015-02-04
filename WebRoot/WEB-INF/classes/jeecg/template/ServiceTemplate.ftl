package ${bussPackage}.${entityPackage}.service;

import java.util.List;

import jt56.comm.system.pageModel.DataGrid;
import jt56.comm.system.pageModel.PageHelper;
import ${bussPackage}.${entityPackage}.entity.${className};
/**
 * 
 * <br>
 * <b>功能：</b>${className}Service<br>
 * <b>作者：</b>zhouq<br>
 * <b>版权所有：<b>版权所有(C) 2013，www.jt56.org<br>
 */

public interface ${className}ServiceI {



	public DataGrid dataGrid(${className} entity, PageHelper ph);


	public void add(${className} entity) throws Exception;
	

	public ${className} get(String id);
	

	public void update(${className} entity) throws Exception;


	public void delete(String id);



}
