/**
 * Program  : UserServiceApiImpl.java
 * Author   : zhouq
 * Create   : 2014-5-27 下午1:46:25
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import jt56.webService.UserRestApi;
import jt56.webService.entity.User;
import jt56.webService.entity.Users;


/*
注释（Annotation）：在 javax.ws.rs.* 中定义，是 JAX-RS (JSR 311) 规范的一部分。 
@Path：定义资源基 URI。由上下文根和主机名组成，资源标识符类似于 http://localhost:8080/RESTful/rest/hello。 
@GET：这意味着以下方法可以响应 HTTP GET 方法。 
@Produces：以纯文本方式定义响应内容 MIME 类型。

@Context： 使用该注释注入上下文对象，比如 Request、Response、UriInfo、ServletContext 等。 
@Path("{contact}")：这是 @Path 注释，与根路径 “/contacts” 结合形成子资源的 URI。 
@PathParam("contact")：该注释将参数注入方法参数的路径，在本例中就是联系人 id。其他可用的注释有 @FormParam、@QueryParam 等。 
@Produces：响应支持多个 MIME 类型。在本例和上一个示例中，APPLICATION/XML 将是默认的 MIME 类型。
*/
/**
 * 
 * @author zhouq
 * @create 2014年7月2日 上午10:05:55
 */
@Path(value = "/userApi")
public class UserRestApiImpl implements UserRestApi{
	@Context
    private UriInfo uriInfo;
    
    @Context
    private Request request;
 
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String doGet() {
        return "this is get rest request";
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/request/{param}")
    public String doRequest(@PathParam("param") String param, 
            @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse) {
        System.out.println(servletRequest);
        System.out.println(servletResponse);
        System.out.println(servletRequest.getParameter("param"));
        System.out.println(servletRequest.getContentType());
        System.out.println(servletResponse.getCharacterEncoding());
        System.out.println(servletResponse.getContentType());
        return "success";
    }
    
    @GET
    @Path("/bean/{id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public User getBean(@PathParam("id") int id) {
        System.out.println("####getBean#####");
        System.out.println("id:" + id);
        System.out.println("Method:" + request.getMethod());
        System.out.println("uri:" + uriInfo.getPath());
        System.out.println(uriInfo.getPathParameters());
        
        User user = new User();
        user.setId(id);
        user.setName("JojO");
        return user;
    }
    
    @GET
    @Path("/list")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Users getList() {
        System.out.println("####getList#####");
        System.out.println("Method:" + request.getMethod());
        System.out.println("uri:" + uriInfo.getPath());
        System.out.println(uriInfo.getPathParameters());
        
        List<User> list = new ArrayList<User>();
        User user = null;
        for (int i = 0; i < 4;i ++) {
            user = new User();
            user.setId(i);
            user.setName("JojO-" + i);
            list.add(user);
        }
        
        Map<String, User> maps = new HashMap<String, User>();
        User user2 = null;
        for (int i = 0; i < 4;i ++) {
            user2 = new User();
            user2.setId(i);
            user2.setName("JojO-" + i);
            maps.put("key-" + i, user2);
        }
        Users users = new Users();
        users.setUsers(list);
        users.setMaps((HashMap<String, User>) maps);
        return users;
    }   
    
    /*
        @Consumes：声明该方法使用 HTML FORM。 
        @FormParam：注入该方法的 HTML 属性确定的表单输入。 
        @Response.created(uri).build()： 构建新的 URI 用于新创建的联系人（/contacts/{id}）并设置响应代码（201/created）。
        您可以使用 http://localhost:8080/Jersey/rest/contacts/<id> 访问新联系人
     */
    @POST
    @Path("/postData")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public User postData(User user) throws IOException {
        System.out.println(user);
        user.setName("jojo##12321321");
        return user;
    } 
    
    @PUT
    @Path("/putData/{id}")
    @Produces({ MediaType.APPLICATION_XML })
    public User putData(@PathParam("id") int id, User user) {
        System.out.println("#####putData#####");
        System.out.println(user);
        user.setId(id);
        user.setAddress("hoojo#gz");
        user.setEmail("hoojo_@126.com");
        user.setName("hoojo");
        System.out.println(user);
        return user;
    }
    
    @DELETE
    @Path("/removeData/{id}")
    public void deleteData(@PathParam("id") int id) {
        System.out.println("#######deleteData#######" + id);
    }
}

