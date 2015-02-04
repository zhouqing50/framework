package jt56.comm.system.service.impl;

import java.util.HashMap;
import java.util.List;

import jt56.comm.system.dao.DicTypeDao;
import jt56.comm.system.model.DicType;
import jt56.comm.system.service.DicTypeServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DicTypeServiceImpl implements DicTypeServiceI {

	@Autowired
	private DicTypeDao dicTypeDao;

	public DicTypeDao getDicTypeDao() {
		return dicTypeDao;
	}

	public void setDicTypeDao(DicTypeDao dicTypeDao) {
		this.dicTypeDao = dicTypeDao;
	}

	public List<DicType> getDicTypeListByPid(String pid) {
		return dicTypeDao.getDicTypeListByPid(pid);
	}

	public void add(DicType dic){
		 dicTypeDao.save(dic);
	}

	public DicType get(String id) {
		return dicTypeDao.getDicTypeById(id);
	}
	
	public DicType getDicTypeByUid(String uid) {
		return dicTypeDao.getDicTypeByUId(uid);
	}
	
	public DicType getDicTypeByPar(HashMap< String ,String> map) {
		return dicTypeDao.getDicTypeByPar(map);
	}

	public void update(DicType dic) throws Exception {
		 dicTypeDao.update(dic);
	}

	public void delete(String id, String text) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("text", text);
		DicType entity  = (DicType) dicTypeDao.getDicTypeByPar(map);
		String pid = entity.getPid();		
		dicTypeDao.delete(id);
		//如果parentId为空(空默认的值为'no')，则包含子字典项，需要一起删除
		if ("no".equals(pid)) {
			dicTypeDao.deleteDicTypeByPid(pid);
		}
	}

	/**
	 * 获得字典树
	 * 
	 * @param sessionInfo
	 * @return
	 */
	public List<DicType> tree(){
		return dicTypeDao.getAllDicTypeList();
	}

	
	public List<DicType> treeGrid() {
		return dicTypeDao.getAllDicTypeList();
	}

	/**
	 * 获取所有父数据字典
	 * @author zhouq
	 * @create 2014-6-10 上午10:15:30
	 * @return
	 */
	public List<DicType> findPDicTypeList() {
		return dicTypeDao.findPDicTypeList();
	}

}
