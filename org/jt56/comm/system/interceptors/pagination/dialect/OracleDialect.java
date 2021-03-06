package jt56.comm.system.interceptors.pagination.dialect;

/**
 * @Title OracleDialect.java
 * @Description:oracle方言实现
 * @author: liuxingmi
 * @date(创建日期)：2013-3-20 上午11:34:54
 * @company(公司)：深圳市彩讯科技有限公司
 * @version 1.0
 * @History(修改历史):
 */
public class OracleDialect extends Dialect {

	public String getLimitString(String sql, int offset, int limit) {

		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		
		pagingSelect.append(sql);
		
		pagingSelect.append(" ) row_ ) where rownum_ > "+offset+" and rownum_ <= "+(offset + limit));

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}
		
		return pagingSelect.toString();
	}
}
