/**
 * Program  : RESTSample.java
 * Author   : zhouq
 * Create   : 2014-5-27 上午9:01:53
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

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import jt56.webService.entity.User;
import jt56.webService.entity.Users;

public interface UserRestApi {
    

    public String doGet();
    
    public String doRequest(@PathParam("param") String param, 
            @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);
    
    public User getBean(@PathParam("id") int id);
    
    public Users getList();
    
    /*
        @Consumes：声明该方法使用 HTML FORM。 
        @FormParam：注入该方法的 HTML 属性确定的表单输入。 
        @Response.created(uri).build()： 构建新的 URI 用于新创建的联系人（/contacts/{id}）并设置响应代码（201/created）。
        您可以使用 http://localhost:8080/Jersey/rest/contacts/<id> 访问新联系人
     */

    public User postData(User user) throws IOException;
    
    public User putData(@PathParam("id") int id, User user);
    
    public void deleteData(@PathParam("id") int id);
    
}