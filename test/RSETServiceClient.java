/**
 * Program  : RSETServiceClient.java
 * Author   : zhouq
 * Create   : 2014-5-27 上午9:22:09
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


import javax.ws.rs.core.MediaType;

import jt56.webService.entity.User;
import jt56.webService.entity.Users;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author zhouq
 * @version 1.0.0
 * @2014-5-27 上午9:22:09
 */
public class RSETServiceClient {
	 private static WebClient client;
	    
	    @Before
	    public void init() {
	        // 手动创建webClient对象，注意这里的地址是发布的那个/rest地址
	        String url = "http://zhouq.jt56.org/jt56-framework/webservice/user/";
	        client = WebClient.create(url);
	 
	        // 从Spring Ioc容器中拿webClient对象
	       // ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-client.xml");
	       // client = ctx.getBean("webClient", WebClient.class);
	    }
	    
	    @After
	    public void destory(){
	    }
	    
	    @Test
	    public void testGet() {
	        System.out.println(client.path("userApi").accept(MediaType.TEXT_PLAIN).get(String.class));
	    }
	    
	    @Test
	    public void testRequest() {
	        System.out.println(client.path("userApi/request/234234").accept(MediaType.TEXT_PLAIN).get(String.class));
	    }
	    
	    @Test
	    public void testBean() {
	        User user = client.path("userApi/bean/{id}", 25).accept(MediaType.APPLICATION_XML).get(User.class);
	        System.out.println(user);
	    }
	    
	    @Test
	    public void testList() {
	    	Users user = client.path("userApi/list").accept(MediaType.APPLICATION_XML).get(Users.class);
	        System.out.println(user.getUsers());
	        System.out.println("===="+user.getMaps());
	    }
	    
//	    @Test
//	    public void testMap() {
//	        System.out.println(client.path("userApi/map").accept(MediaType.APPLICATION_XML).get(MapBean.class).getMap());
//	    }
	    
	    @Test
	    public void testDeleteData() {
	        client.path("userApi/removeData/23").delete(); 
	    }
	    
	    @Test
	    public void testPostData() {
	        User user = new User();
	        user.setId(21432134);
	        user.setAddress("hoojo#gz");
	        user.setEmail("hoojo_@126.com");
	        user.setName("hoojo");
	        
	        System.out.println(client.path("userApi/postData").accept(MediaType.APPLICATION_XML).post(user, User.class));
	    }
	    
	    @Test
	    public void testPutData() {
	        User user = new User();
	        user.setId(21432134);
	        System.out.println(client.path("userApi/putData/1").accept(MediaType.APPLICATION_XML).put(user).getEntity());
	    }

	    
}

