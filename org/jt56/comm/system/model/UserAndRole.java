package jt56.comm.system.model;

public class UserAndRole implements java.io.Serializable {

	/**
	 * @author zhouq
	 * @create 2014-6-12 下午4:22:01
	 */
	private static final long serialVersionUID = 1L;
	private String roleId;
	private String userId;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	


}
