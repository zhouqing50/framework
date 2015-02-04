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
	
		/**
	 * @author zhouq
	 * @create 2014年7月2日 上午9:42:14
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   用户ID	private String user_name;//   用户名	private String pwd;//   用户密码	private String realname;//   真实名字	private String sex;//   性别	private String useridsn;//   身份证	private String email;//   邮箱	private String qq;//   QQ	private String mobile;//   手机	private String status;//   状态(y:有效,n:无效)	private String create_by;//   创建人	private java.util.Date create_time;//   创建时间	private String update_by;//   修改人	private java.util.Date update_time;//   修改时间	private String source;//   用户来源
	
	
	
	
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
		public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getUser_name() {	    return this.user_name;	}	public void setUser_name(String user_name) {	    this.user_name=user_name;	}	public String getPwd() {	    return this.pwd;	}	public void setPwd(String pwd) {	    this.pwd=pwd;	}	public String getRealname() {	    return this.realname;	}	public void setRealname(String realname) {	    this.realname=realname;	}	public String getSex() {	    return this.sex;	}	public void setSex(String sex) {	    this.sex=sex;	}	public String getUseridsn() {	    return this.useridsn;	}	public void setUseridsn(String useridsn) {	    this.useridsn=useridsn;	}	public String getEmail() {	    return this.email;	}	public void setEmail(String email) {	    this.email=email;	}	public String getQq() {	    return this.qq;	}	public void setQq(String qq) {	    this.qq=qq;	}	public String getMobile() {	    return this.mobile;	}	public void setMobile(String mobile) {	    this.mobile=mobile;	}	public String getStatus() {	    return this.status;	}	public void setStatus(String status) {	    this.status=status;	}	public String getCreate_by() {	    return this.create_by;	}	public void setCreate_by(String create_by) {	    this.create_by=create_by;	}	public java.util.Date getCreate_time() {	    return this.create_time;	}	public void setCreate_time(java.util.Date create_time) {	    this.create_time=create_time;	}	public String getUpdate_by() {	    return this.update_by;	}	public void setUpdate_by(String update_by) {	    this.update_by=update_by;	}	public java.util.Date getUpdate_time() {	    return this.update_time;	}	public void setUpdate_time(java.util.Date update_time) {	    this.update_time=update_time;	}	public String getSource() {	    return this.source;	}	public void setSource(String source) {	    this.source=source;	}
}

