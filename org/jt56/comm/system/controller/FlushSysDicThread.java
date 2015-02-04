/**
 * Program  : SaveSmsThread.java
 * Author   : zhouq
 * Create   : 2014-5-29 下午4:48:38
 *
 * Copyright 2014 by jt56 Technologies Ltd.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of jt56 Technologies Ltd.("Confidential Information").  
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with jt56 Technologies Ltd.
 *
 */

package jt56.comm.system.controller;


import jt56.comm.system.service.InitServiceI;

/**
 * 刷新缓存中数据字典的线程
 * @author zhouq 
 * @create 2014-6-10 下午3:59:28
 */
public class FlushSysDicThread implements Runnable{
	
	private InitServiceI initService;
	
	public FlushSysDicThread() {
		
	}
	
	public FlushSysDicThread( InitServiceI  initService){
		this.initService = initService;
	}
	
	public void run() {
		//重新刷新缓存中的数据字典
		initService.flushDicMap();
	}

}

