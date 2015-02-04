package jt56.comm.system.interceptors.pagination.dialect;

/**
 * @Title MySqlDialect.java
 * @Description:mysql方言实现
 * @author: liuxingmi
 * @date(创建日期)：2013-3-20 上午11:34:25
 * @company(公司)：深圳市彩讯科技有限公司
 * @version 1.0
 * @History(修改历史):
 */
public class MySqlDialect extends Dialect{
	
	protected static final String SQL_END_DELIMITER = ";";
	
	public String getLimitString(String sql, boolean hasOffset) {
		return MySqlPageHepler.getLimitString(sql,-1,-1);
	}

	public String getLimitString(String sql, int offset, int limit) {
		return MySqlPageHepler.getLimitString(sql, offset, limit);
	}

	public boolean supportsLimit() {
		return true;
	}
}
