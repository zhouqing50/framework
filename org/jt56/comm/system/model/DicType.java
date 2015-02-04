package jt56.comm.system.model;



public class DicType implements java.io.Serializable {

	/**
	 * @author zhouq
	 * @create 2014-6-13 下午2:06:36
	 */
	private static final long serialVersionUID = 1L;
	private String id; //数据字典编码
	private String uid;
	private String text;//数据字典显示名称
	private String remark;
	private String pid;//父字典编码
	private String seq;
	
	public DicType() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	

}
