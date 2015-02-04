/**
 * Program  : UserServiceApiImpl.java
 * Author   : zhouq
 * Create   : 2014-5-27 下午4:31:25
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

package jt56.webService.impl;

import javax.jws.WebService;

import jt56.webService.UserServiceApi;
import jt56.webService.entity.BaseUser;


/**
 * 
 * @author zhouq
 * @version 1.0.0
 * @2014-5-27 下午4:31:25
 */
@WebService(endpointInterface = "jt56.webService.UserServiceApi",serviceName="userServiceApi")
public class UserServiceApiImpl implements UserServiceApi{

	//@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	//private DataJdbcService dataJdbcService; 

	/**
	 * 注册时，验证用户名，邮箱，手机，身份证的唯一性
	 * @author zhouq
	 * @create 2014-5-27 下午4:32:47
	 * @param user
	 * @return
	 */
	public boolean checkUser(BaseUser user) {
		System.out.println(user);
    	boolean flag = false;
    	/*if (StringUtil.isValid(user.getUser_name())) {//用户名
    		flag = dataJdbcService.userIsValid("username", user.getUser_name());
		}else if (StringUtil.isValid(user.getEmail())) {//邮箱
    		flag = dataJdbcService.userIsValid("email", user.getEmail());
		}else if (StringUtil.isValid(user.getUseridsn())) {//身份证
    		flag = dataJdbcService.userIsValid("useridsn", user.getUseridsn());
		}else if (StringUtil.isValid(user.getMobile())) {//手机
    		flag = dataJdbcService.userIsValid("usertelsn", user.getMobile());
		}*/
		return flag;
	}

	/**
	 * 登录验证，如果登录成功，则同步到雁城节点数据库
	 * @author zhouq
	 * @create 2014-5-28 上午9:42:54
	 * @param user
	 * @return
	 */
	public BaseUser login(BaseUser user) {
		//BaseUser userInfo = dataJdbcService.login(user);
		BaseUser userInfo = new BaseUser();
		userInfo.setRealname("111");
		System.out.println(userInfo);
		return userInfo;
	}

}

