package jt56.comm.system.log;

public interface Log {

	/**
	 * 记录debug级别的日志
	 * 
	 * @param messages
	 */
	public void debug(String messages);

	/**
	 * 记录debug级别的日志
	 * 
	 * @param messages
	 * @param e
	 */
	public void debug(String messages, Exception e);

	/**
	 * 记录debug级别的日志
	 * 
	 * @param messages
	 */
	public void debug(String[] messages);
	
	/**
	 * 记录debug级别的日志
	 * @param cmd
	 * @param result
	 * @param corpId
	 * @param userId
	 * @param ip
	 * @param msg
	 * @param sid
	 */
	public void debug(String cmd, String result, Long corpId, String userId,String ip,String msg, String sid);

	/**
	 * 记录debug级别的日志
	 * 
	 * @param messages
	 * @param e
	 */
	public void debug(String[] messages, Exception e);
	
	/**
	 * 记录debug级别的日志
	 * @param cmd
	 * @param result
	 * @param corpId
	 * @param userId
	 * @param ip
	 * @param msg
	 * @param sid
	 * @param e
	 */
	public void debug(String cmd, String result, Long corpId, String userId,String ip,String msg, String sid, Exception e);

	/**
	 * 记录debug级别的日志
	 * @param cmd
	 * @param result
	 * @param msg
	 */
	public void debug(String cmd, String result,String msg);
	
	/**
	 * 记录info级别的日志
	 * 
	 * @param messages
	 *            日志信息
	 */
	public void info(String messages);
	
	/**
	 * 记录info级别的日志
	 * @param cmd
	 * @param result
	 * @param corpId
	 * @param userId
	 * @param ip
	 * @param msg
	 * @param sid
	 */
	public void info(String cmd, String result, Long corpId, String userId,String ip,String msg, String sid);
	
	/**
	 * 记录info级别的日志
	 * @param cmd
	 * @param result
	 * @param corpId
	 * @param userId
	 * @param ip
	 * @param msg
	 * @param sid
	 * @param e
	 */
	public void info(String cmd, String result, Long corpId, String userId,String ip,String msg, String sid, Exception e);
	
	/**
	 * 记录info级别的日志
	 * @param cmd
	 * @param result
	 * @param ip
	 * @param msg
	 */
	public void info(String cmd, String result,String msg);
	

	/**
	 * 记录info级别的日志
	 * 
	 * @param messages
	 * @param e
	 */
	public void info(String messages, Exception e);


	/**
	 * 记录info级别的日志
	 * 
	 * @param messages
	 */
	public void info(String[] messages);

	/**
	 * 记录info级别的日志
	 * 
	 * @param messages
	 * @param e
	 */
	public void info(String[] messages, Exception e);

	/**
	 * 记录warn级别的日志
	 * 
	 * @param messages
	 */
	public void warn(String messages);

	/**
	 * 记录warn级别的日志
	 * 
	 * @param messages
	 * @param e
	 */
	public void warn(String messages, Exception e);

	/**
	 * 记录warn级别的日志
	 * 
	 * @param messages
	 */
	public void warn(String[] messages);

	/**
	 * 记录warn级别的日志
	 * 
	 * @param messages
	 * @param e
	 */
	public void warn(String[] messages, Exception e);

	/**
	 * 记录error级别的日志
	 * 
	 * @param messages
	 */
	public void error(String messages);


	/**
	 * 记录error级别的日志
	 * @param cmd
	 * @param result
	 * @param corpId
	 * @param userId
	 * @param ip
	 * @param msg
	 * @param sid
	 */
	public void error(String cmd, String result, Long corpId, String userId,String ip,String msg, String sid);
	/**
	 * 记录error级别的日志
	 * @param cmd
	 * @param result
	 * @param corpId
	 * @param userId
	 * @param ip
	 * @param msg
	 * @param sid
	 * @param e
	 */
	public void error(String cmd, String result, Long corpId, String userId,String ip,String msg, String sid, Exception e);

	/**
	 * 记录error级别的日志
	 * @param cmd
	 * @param result
	 * @param msg
	 * @param e
	 */
	public void error(String cmd, String result,String msg,Exception e);
	
	/**
	 * 记录error级别的日志
	 * 
	 * @param messages
	 * @param e
	 */
	public void error(String messages, Exception e);
	
	/**
	 * 记录error级别的日志
	 * 
	 * @param messages
	 */
	public void error(String[] messages);

	/**
	 * 记录error级别的日志
	 * 
	 * @param messages
	 * @param e
	 */
	public void error(String[] messages, Exception e);
	
		
	

}