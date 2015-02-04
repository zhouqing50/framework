package jt56.comm.system.service.impl;

import java.util.List;

import jt56.comm.system.dao.TlogDao;
import jt56.comm.system.model.Tlog;
import jt56.comm.system.pageModel.DataGrid;
import jt56.comm.system.pageModel.PageHelper;
import jt56.comm.system.service.TlogServiceI;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * <br>
 * <b>功能：</b>TlogService<br>
 * <b>作者：</b>zhouq<br>
 * <b>版权所有：<b>版权所有(C) 2013，www.jt56.org<br>
 */

@Service
public class TlogServiceImpl implements TlogServiceI {


	@Autowired
    private TlogDao dao;
	

	public TlogDao getDao() {
		return dao;
	}

	
	public DataGrid dataGrid(Tlog entity, PageHelper ph) {
		DataGrid dg = new DataGrid();

		List<Tlog> ul = dao.queryEntityList(entity,new RowBounds((ph.getPage()-1)*ph.getRows(), ph.getRows()), ph.getSort().toUpperCase(), ph.getOrder().toUpperCase());
	
		dg.setRows(ul);
		dg.setTotal(dao.queryTotal(entity));
		return dg;
	}

	synchronized public void add(Tlog entity) throws Exception {		
	
			dao.add(entity);
	}


	public Tlog get(String id) {

		return dao.getEntityById(id);
		
	}


	synchronized public void update(Tlog entity) throws Exception {
		 dao.update(entity);
	}


	@Transactional
	public void delete(String id) {
		dao.delete(id);

	}

}