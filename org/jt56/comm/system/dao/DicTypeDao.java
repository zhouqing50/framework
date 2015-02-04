package jt56.comm.system.dao;

import java.util.HashMap;
import java.util.List;

import jt56.comm.system.model.DicType;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface DicTypeDao {	
	
	/**找出所有该数据字典下面的所有字典项*/
	public List<DicType> getDicTypeListByPid(String pid);
	
	/**获取所有父数据字典*/
	public List<DicType> findPDicTypeList();
	
	public DicType getDicTypeById(String id);
	
	public DicType getDicTypeByUId(String uid);
	
	public DicType getDicTypeByPar(HashMap< String ,String> map);
	
	public void save(DicType dic);
	
	public void update(DicType dic);
	
	public void delete(String id);
	
	public void deleteDicTypeByPid(String pid);

	public List<DicType> queryDicTypeList(@Param("dic")DicType dic,@Param("pageNo")int pageNo, @Param("pageSize")int pageSize,@Param("sort")String sort, @Param("order")String order);

	public List<DicType> getAllDicTypeList();
}
