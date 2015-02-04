/**
 * Program  : UserServiceApi.java
 * Author   : zhouq
 * Create   : 2014-5-27 下午4:31:02
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

package jt56.webService;

import javax.jws.WebService;

import jt56.webService.entity.BaseUser;


/**
 * 
 * @author zhouq
 * @version 1.0.0
 * @2014-5-27 下午4:31:02
 */
@WebService
public interface UserServiceApi {
	
	/**
	 * 注册时，验证用户名，邮箱，手机，身份证的唯一性
	 * @author zhouq
	 * @create 2014-5-27 下午4:32:47
	 * @param user
	 * @return
	 */
	public boolean checkUser(BaseUser user);
	
	/**
	 * 登录验证，如果登录成功，则同步到雁城节点数据库
	 * @author zhouq
	 * @create 2014-5-28 上午9:42:54
	 * @param user
	 * @return
	 */
	public BaseUser login(BaseUser user);
}

