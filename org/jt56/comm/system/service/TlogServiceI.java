package jt56.comm.system.service;

import jt56.comm.system.model.Tlog;
import jt56.comm.system.pageModel.DataGrid;
import jt56.comm.system.pageModel.PageHelper;
/**
 * 
 * <br>
 * <b>功能：</b>TlogService<br>
 * <b>作者：</b>zhouq<br>
 * <b>版权所有：<b>版权所有(C) 2013，www.jt56.org<br>
 */

public interface TlogServiceI {



	public DataGrid dataGrid(Tlog entity, PageHelper ph);


	public void add(Tlog entity) throws Exception;
	

	public Tlog get(String id);
	

	public void update(Tlog entity) throws Exception;


	public void delete(String id);



}
