package jt56.comm.system.service;

/**
 * 初始化数据库服务
 * 
 * @author 孙宇
 * 
 */
public interface InitServiceI {

	/**
	 * 
	 */
	public void init();
	
	
	/**
	 * 重新刷新缓存中的数据字典
	 */
	public void flushDicMap();

}
