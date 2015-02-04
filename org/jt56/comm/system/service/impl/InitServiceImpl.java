package jt56.comm.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import jt56.comm.system.model.DicType;
import jt56.comm.system.service.DicTypeServiceI;
import jt56.comm.system.service.InitServiceI;
import jt56.comm.system.service.LoggerUtil;
import jt56.comm.system.util.SysUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InitServiceImpl implements InitServiceI {


	@Autowired
	private DicTypeServiceI dicTypeService;
	
	/**
	 * 日志记录
	 */
	@Autowired
	protected LoggerUtil loggerUtil;

	synchronized public void init() {

		flushDicMap();
	} 


	/**
	 * 初始化数据字典到MAP中
	 * @author zhouq
	 * @create 2014-6-10 上午10:27:50
	 */
	public void flushDicMap() {
		//所有的父数据字典
		List<DicType> dicList = new ArrayList<DicType>();
		dicList = dicTypeService.findPDicTypeList();
		
		for (DicType dicType : dicList) {
			//所有的父数据字典对应的所有字数据项
			List<DicType> dicTypeList = new ArrayList<DicType>();
			dicTypeList = dicTypeService.getDicTypeListByPid(dicType.getId());
			
			//以父数据字典的CODE为key,所有对应子数据字典项为value 
			SysUtil.dicMap.put(dicType.getId(), dicTypeList);
		}
		loggerUtil.info("初始化数据字典到MAP中");
		
	}
	


}
