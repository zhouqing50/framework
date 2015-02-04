package jt56.comm.system.dao;

import java.util.List;

import jt56.comm.system.model.Tlog;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
/**
 * 
 * <br>
 * <b>功能：</b>TlogDao<br>
 * <b>作者：</b>zhouq<br>
 * <b>版权所有：<b>版权所有(C) 2013，www.jt56.org<br>
 */
 @Repository
public interface TlogDao{
	
	public Tlog getEntityById(String id);
	
	public void add(Tlog entity);
	
	public void update(Tlog entity);
	
	public void delete(String id);

	public List<Tlog> queryEntityList(@Param("entity")Tlog entity,RowBounds rowBounds,@Param("sort")String sort, @Param("order")String order);
	
	public int queryTotal(@Param("entity")Tlog entity);
}
