package ${bussPackage}.${entityPackage}.service.impl;

import org.apache.log4j.Logger;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jt56.comm.sys.pageModel.DataGrid;
import jt56.comm.sys.pageModel.Json;
import jt56.comm.sys.pageModel.PageHelper;
import com.alibaba.fastjson.JSON;
 
import ${bussPackage}.${entityPackage}.entity.${className};
import ${bussPackage}.${entityPackage}.service.${className}ServiceI;
import ${bussPackage}.${entityPackage}.dao.${className}Dao;

/**
 * 
 * <br>
 * <b>功能：</b>${className}Service<br>
 * <b>作者：</b>zhouq<br>
 * <b>版权所有：<b>版权所有(C) 2013，www.jt56.org<br>
 */

@Service
public class ${className}ServiceImpl implements ${className}ServiceI {


	@Autowired
    private ${className}Dao dao;
	

	public ${className}Dao getDao() {
		return dao;
	}

	
	public DataGrid dataGrid(${className} entity, PageHelper ph) {
		DataGrid dg = new DataGrid();

		List<${className}> ul = dao.queryEntityList(entity, (ph.getPage()-1)*ph.getRows(), ph.getRows(), ph.getSort().toUpperCase(), ph.getOrder().toUpperCase());
	
		dg.setRows(ul);
		dg.setTotal(dao.queryTotal(entity));
		return dg;
	}

	synchronized public void add(${className} entity) throws Exception {		
	
			dao.add(entity);
	}


	public ${className} get(String id) {

		return dao.getEntityById(id);
		
	}


	synchronized public void update(${className} entity) throws Exception {
		 dao.update(entity);
	}


	@Transactional
	public void delete(String id) {
		dao.delete(id);

	}

}