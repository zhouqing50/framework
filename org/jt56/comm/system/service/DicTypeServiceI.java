package jt56.comm.system.service;

import java.util.HashMap;
import java.util.List;

import jt56.comm.system.model.DicType;




/**
 * 用户Service
 * 
 * @author 孙宇
 * 
 */
public interface DicTypeServiceI {
	
	/**
	 * 获取所有父数据字典
	 * @author zhouq
	 * @create 2014-6-10 上午10:15:30
	 * @return
	 */
	public List<DicType> findPDicTypeList();
	
	

	public List<DicType> getDicTypeListByPid(String pid);
	
	/**
	 * 添加
	 * 
	 * @param user
	 */
	public void add(DicType dic);
	
	/**
	 * 获得数据字典对象
	 * 
	 * @param id
	 * @return
	 */
	public DicType get(String id);
	
	/**
	 * 获得数据字典对象通过字典CODE
	 * 
	 * @param id
	 * @return
	 */
	public DicType getDicTypeByUid(String uid);
	
	/**
	 * 编辑
	 * 
	 * @param user
	 */
	public void update(DicType dic) throws Exception;

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(String uid, String text);

	
	/**
	 * 获得数据字典列表
	 * 
	 * @param sessionInfo
	 * 
	 * @return
	 */
	public List<DicType> treeGrid();
	
	/**
	 * 获得字典树
	 * @param sessionInfo
	 * @return
	 */
	public List<DicType> tree();

	public DicType getDicTypeByPar(HashMap< String ,String> map);

}
