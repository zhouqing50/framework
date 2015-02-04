package jt56.webService.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * <br>
 * <b>功能：</b>BaseUserEntity<br>
 * <b>作者：</b>zhouq<br>
 * <b>版权所有：<b>版权所有(C) 2013，www.jt56.org<br>
 */
@XmlRootElement(name = "BaseUser")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseUser implements java.io.Serializable{
	
	
	 * @author zhouq
	 * @create 2014年7月2日 上午9:42:14
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   用户ID
	
	
	
	
	public BaseUser(String id, String user_name, String pwd, String realname,
			String sex, String useridsn, String email, String qq,
			String mobile, String status, String create_by, Date create_time,
			String update_by, Date update_time, String source) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.pwd = pwd;
		this.realname = realname;
		this.sex = sex;
		this.useridsn = useridsn;
		this.email = email;
		this.qq = qq;
		this.mobile = mobile;
		this.status = status;
		this.create_by = create_by;
		this.create_time = create_time;
		this.update_by = update_by;
		this.update_time = update_time;
		this.source = source;
	}

	public BaseUser() {
		
	}
	
}
