package jt56.comm.system.model;

/**
 * 
 * <br>
 * <b>功能：</b>TlogEntity<br>
 * <b>作者：</b>zhouq<br>
 * <b>版权所有：<b>版权所有(C) 2013，www.jt56.org<br>
 */
public class Tlog {
	
		private java.lang.String id;//   ID	private java.lang.String userid;//   用户ID	private java.lang.String username;//   用户名称
	private java.lang.String ip;//   用户IP	private java.lang.Integer operation_type;//   操作类型(1：新增，2：修改，3：更新，4：删除，5：查询)	private java.lang.String tablename;//   操作表名	private java.lang.String tableid;//   操作主键	private java.lang.String comment;//   备注	private java.util.Date ctime;//   操作时间	public java.lang.String getId() {	    return this.id;	}	public void setId(java.lang.String id) {	    this.id=id;	}	public java.lang.String getUserid() {	    return this.userid;	}	public void setUserid(java.lang.String userid) {	    this.userid=userid;	}	public java.lang.String getUsername() {	    return this.username;	}	public void setUsername(java.lang.String username) {	    this.username=username;	}	public java.lang.Integer getOperation_type() {	    return this.operation_type;	}	public void setOperation_type(java.lang.Integer operation_type) {	    this.operation_type=operation_type;	}	public java.lang.String getTablename() {	    return this.tablename;	}	public void setTablename(java.lang.String tablename) {	    this.tablename=tablename;	}	public java.lang.String getTableid() {	    return this.tableid;	}	public void setTableid(java.lang.String tableid) {	    this.tableid=tableid;	}	public java.lang.String getComment() {	    return this.comment;	}	public void setComment(java.lang.String comment) {	    this.comment=comment;	}	public java.util.Date getCtime() {	    return this.ctime;	}	public void setCtime(java.util.Date ctime) {	    this.ctime=ctime;	}
	public java.lang.String getIp() {
		return ip;
	}
	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}
	
}

