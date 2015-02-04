package jt56.comm.system.log;

import jt56.comm.system.util.Tools;

import org.apache.log4j.Logger;


/**
 * @Description ：基本日志记录器 用于被其它各个模块的记录器所继承
 * @author ：shenjincheng
 * @date：2011-8-22
 * @company：深圳市彩讯科技有限公司
 * 
 * @History:
 */
public abstract class BaseLogger implements Log {

	/** 日志级别：调试 */
	private static final int debug = 1;
	/** 日志级别：调试 另加打印异常信息 */
	private static final int debugException = 11;
	/** 日志级别：普通 */
	private static final int info = 2;
	/** 日志级别：普通 另加打印异常信息 */
	private static final int infoException = 22;
	/** 日志级别：警告 */
	private static final int warn = 3;
	/** 日志级别：警告 另加打印异常信息 */
	private static final int warnException = 33;
	/** 日志级别：错误 */
	private static final int error = 4;
	/** 日志级别：错误 另加打印异常信息 */
	private static final int errorException = 44;
	/** 消息所属和消息内容的分隔符 */
	private static final String msgSplit = " - ";
	/** 消息对的分隔符 */
	private static final String propertiesSplit = " | ";
	
	/** 日志的key */
	private static final String CMD = "cmd=";
	/** 日志的key */
	private static final String RESULT = "result=";
	/** 日志的key */
	private static final String CORP_ID = "corpId=";
	/** 日志的key */
	private static final String USER_ID = "userId=";
	/** 日志的key */
	private static final String IP = "ip=";
	/** 日志的key */
	private static final String MSG = "msg=";
	/** 日志的key */
	private static final String SID = "sid=";

	private static final String className = BaseLogger.class.getName();
	private static BaseLogger baseLogger = null;
	private Logger logger = null;
	
	public final static String RESULT_FAIL = "S_FAIL";
	public final static String RESULT_OK = "S_OK";

	/**
	 * 返回基本日志记录实例
	 * 
	 * @return
	 */
	protected static BaseLogger getInstance() {
		//if (baseLogger == null) {
		//	baseLogger = new BaseLogger();
		//}

		return baseLogger;
	}
	
	protected abstract String getLogName();
	
	protected Logger getLogger() {
		String logName = getLogName();
		if (logger == null) {
			if(Tools.isNotEmpty(logName)){
				logger = Logger.getLogger(logName);
			}else{
				logger = Logger.getRootLogger();
			}
		}
		return logger;
	}


	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#debug(java.lang.String)
	 */
	public void debug(String messages) {
		log(debug, messages, Thread.currentThread().getStackTrace(), null,false);
	}

	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#debug(java.lang.String, java.lang.Exception)
	 */
	public void debug(String messages, Exception e) {
		log(debugException, messages, Thread.currentThread().getStackTrace(), e,false);
	}

	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#debug(java.lang.String[])
	 */
	public void debug(String[] messages) {
		log(debug, changeArrayToString(messages), Thread.currentThread().getStackTrace(), null, false);
	}

	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#debug(java.lang.String[], java.lang.Exception)
	 */
	public void debug(String[] messages, Exception e) {
		log(debugException, changeArrayToString(messages), Thread.currentThread().getStackTrace(), e,false);
	}

	/**
	 * 打印日志
	 * 
	 * @param level
	 * @param message
	 * @param ste
	 * @param e
	 */
	private void log(int level, String message, StackTraceElement[] ste, Exception e, boolean isStock) {
		
		if (ste != null) {
			// 加入源代码定位
			message = getStackMsg(ste) + msgSplit + message;
		}
		// 转入具体实现，此处为log4j，可以改为其他不同的日志实现。
		switch (level) {
		case debug:
			getLogger().debug(message);
			break;
		case debugException:
			getLogger().debug(message, e);
			break;
		case info:
			getLogger().info(message);
			break;
		case infoException:
			getLogger().info(message, e);
			break;
		case warn:
			getLogger().warn(message);
			break;
		case warnException:
			getLogger().warn(message, e);
			break;
		case error:
			getLogger().error(message,e);
			break;
		case errorException:
			getLogger().error(message, e);
			break;
		default:
			getLogger().debug(message);
		}
	}

	/**
	 * 返回实际打印日志的类名和行号
	 * 
	 * @param ste
	 * @return
	 */
	protected String getStackMsg(StackTraceElement[] ste) {
		if (ste == null)
			return null;

		for (int i = 0; i < ste.length; i++) {
			StackTraceElement s = ste[i];
			// 定位本类的堆栈
			if (className.equals(s.getClassName())) {
				String result = ste[i + 1].toString();
				return result.substring(result.indexOf("("));
			}
		}

		return null;
	}


	/**
	 * 把 String array 转换成按 "|"分隔的字符串
	 * 
	 * @param messages
	 *            日志信息
	 * @return
	 */
	private String changeArrayToString(String[] messages) {
		if (messages == null || messages.length == 0) {
			return null;
		}

		StringBuilder message = new StringBuilder();
		for (String msg : messages) {
			message.append(msg + " | ");
		}

		return message.subSequence(0, message.length() - 3).toString();
	}



	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#info(java.lang.String)
	 */
	public void info(String messages) {
		log(info, messages, Thread.currentThread().getStackTrace(), null,false);
	}

	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#info(java.lang.String, java.lang.Exception)
	 */
	public void info(String messages, Exception e) {
		log(infoException, messages, Thread.currentThread().getStackTrace(), e,false);
	}

	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#info(java.lang.String[])
	 */
	public void info(String[] messages) {
		log(info, changeArrayToString(messages), Thread.currentThread().getStackTrace(), null,false);
	}

	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#info(java.lang.String[], java.lang.Exception)
	 */
	public void info(String[] messages, Exception e) {
		log(infoException, changeArrayToString(messages), Thread.currentThread().getStackTrace(), e,false);
	}




	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#warn(java.lang.String)
	 */
	public void warn(String messages) {
		log(warn, messages, Thread.currentThread().getStackTrace(), null,false);
	}

	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#warn(java.lang.String, java.lang.Exception)
	 */
	public void warn(String messages, Exception e) {
		log(warnException, messages, Thread.currentThread().getStackTrace(), e,false);
	}

	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#warn(java.lang.String[])
	 */
	public void warn(String[] messages) {
		log(warn, changeArrayToString(messages), 
				Thread.currentThread().getStackTrace(), null,false);
	}

	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#warn(java.lang.String[], java.lang.Exception)
	 */
	public void warn(String[] messages, Exception e) {
		log(warnException, changeArrayToString(messages),Thread.currentThread().getStackTrace(), e,false);
	}




	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#error(java.lang.String)
	 */
	public void error(String messages) {
		log(error, messages, Thread.currentThread().getStackTrace(), null,false);
	}

	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#error(java.lang.String, java.lang.Exception)
	 */
	public void error(String messages, Exception e) {
		log(errorException, messages, Thread.currentThread().getStackTrace(), e,false);
	}

	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#error(java.lang.String[])
	 */
	public void error(String[] messages) {
		log(error, changeArrayToString(messages), Thread.currentThread().getStackTrace(), null,false);
	}

	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#error(java.lang.String[], java.lang.Exception)
	 */
	public void error(String[] messages, Exception e) {
		log(errorException, changeArrayToString(messages), Thread.currentThread().getStackTrace(), e,false);
	}

	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#info(java.lang.String, boolean)
	 */
	public void infoStock(String messages) {
		log(info, messages, Thread.currentThread().getStackTrace(), null, true);
	}

	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#info(java.lang.String, java.lang.Exception, boolean)
	 */
	public void infoStock(String messages, Exception e) {
		log(infoException, messages, Thread.currentThread().getStackTrace(), e, true);
	}

	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#error(java.lang.String, boolean)
	 */
	public void errorStock(String messages) {
		log(error, messages, Thread.currentThread().getStackTrace(), null, true);
	}

	/* (non-Javadoc)
	 * @see cn.richinfo.cmail.basemail.common.log.Log#error(java.lang.String, java.lang.Exception,boolean)
	 */
	public void errorStock(String messages, Exception e) {
		log(errorException, messages, Thread.currentThread().getStackTrace(), e, true);
	}
	
	/**
	 * 把参数转成String
	 * @param cmd
	 * @param result
	 * @param corpId
	 * @param userId
	 * @param ip
	 * @param msg
	 * @return
	 */
	private String getStringByParam(String cmd, String result, Long corpId, String userId, String ip,String msg, String sid) {
		StringBuilder b = new StringBuilder();
		if(Tools.isNotEmpty(cmd)) b.append(CMD).append(cmd).append(propertiesSplit);
		if(Tools.isNotEmpty(result)) b.append(RESULT).append(result).append(propertiesSplit);
		if(corpId != null) b.append(CORP_ID).append(corpId).append(propertiesSplit);
		if(Tools.isNotEmpty(userId)) b.append(USER_ID).append(userId).append(propertiesSplit);
		if(Tools.isNotEmpty(ip)) b.append(IP).append(ip).append(propertiesSplit);
		if(Tools.isNotEmpty(msg)) b.append(MSG).append(msg).append(propertiesSplit);
		if(Tools.isNotEmpty(sid)) b.append(SID).append(sid);
		
		String r = b.toString();
		if (r.endsWith(propertiesSplit)) {
			return r.substring(0, r.length() - propertiesSplit.length());
		}
		
		return b.toString();
	}

	public void debug(String cmd, String result, Long corpId, String userId,String ip, String msg, String sid) {
		String log = getStringByParam(cmd, result, corpId, userId, ip, msg, sid);
		log(debug, log, Thread.currentThread().getStackTrace(), null, false);
	}

	public void debug(String cmd, String result, Long corpId, String userId,String ip, String msg, String sid, Exception e) {
		String log = getStringByParam(cmd, result, corpId, userId, ip, msg, sid);
		log(debugException, log, Thread.currentThread().getStackTrace(), e, false);
	}

	/*
	 * (non-Javadoc)
	 * @see cn.richinfo.cloudp.common.log.Log#debug(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void debug(String cmd, String result,String msg){
		String log = getStringByParam(cmd, result, null, "", "", msg, "");
		log(debug, log, Thread.currentThread().getStackTrace(), null, false);
	}
	
	public void info(String cmd, String result, Long corpId, String userId, String ip, String msg, String sid) {
		String log = getStringByParam(cmd, result, corpId, userId, ip, msg, sid);
		log(info, log, Thread.currentThread().getStackTrace(), null, false);
	}

	public void info(String cmd, String result, Long corpId, String userId,String ip, String msg, String sid, Exception e) {
		String log = getStringByParam(cmd, result, corpId, userId, ip, msg, sid);
		log(info, log, Thread.currentThread().getStackTrace(), e, false);
	}
	
	/*
	 * (non-Javadoc)
	 * @see cn.richinfo.cloudp.common.log.Log#info(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void info(String cmd, String result,String msg){
		String log = getStringByParam(cmd, result, null, "", "", msg, "");
		log(info, log, Thread.currentThread().getStackTrace(), null, false);
	}
	
	public void error(String cmd, String result, Long corpId, String userId,String ip, String msg, String sid) {
		String log = getStringByParam(cmd, result, corpId, userId, ip, msg, sid);
		log(error, log, Thread.currentThread().getStackTrace(), null, false);
	}

	public void error(String cmd, String result, Long corpId, String userId,String ip, String msg, String sid, Exception e) {
		String log = getStringByParam(cmd, result, corpId, userId, ip, msg, sid);
		log(errorException, log, Thread.currentThread().getStackTrace(), e, false);
	}
	
	public void error(String cmd, String result,String msg,Exception e){
		String log = getStringByParam(cmd, result, null, "", "", msg, "");
		log(error, log, Thread.currentThread().getStackTrace(), e, false);
	}
	
	
}
