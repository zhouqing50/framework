package jt56.comm.system.log;

/**
 * @Description ：公共日志模块
 * @author ：shenjincheng
 * @date：2011-8-22
 * @company：深圳市彩讯科技有限公司
 * 
 * @History:
 */
public class CommonLogger extends BaseLogger {
	private static CommonLogger commonLogger = null;

	/**
	 * 私有构造函数
	 */
	private CommonLogger() {};

	/**
	 * 返回公共模块的日志记录器实例
	 * 
	 * @return
	 */
	public static CommonLogger getInstance() {
		if (commonLogger == null) {
			commonLogger = new CommonLogger();
		}

		return commonLogger;
	}
	
	/**
	 * 返回公共模块的日志记录器名称
	 */
	@Override
	protected String getLogName() {
		return "common";
	}

}
