package jt56.comm.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import jt56.comm.system.model.Tlog;
import jt56.comm.system.pageModel.SessionInfo;
import jt56.comm.system.service.LoggerUtil;
import jt56.comm.system.service.TlogServiceI;
import jt56.comm.system.util.ConfigUtil;
import jt56.comm.system.util.DateUtils;
import jt56.comm.system.util.StringEscapeEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 基础控制器
 * 
 * 其他控制器继承此控制器获得日期字段类型转换和防止XSS攻击的功能
 * 
 */
@Controller
@RequestMapping("/baseController")
public class BaseController {

	/**
	 * 日志记录
	 */
	@Autowired
	protected LoggerUtil loggerUtil;
	
	@Autowired
	private TlogServiceI logService;
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
	}

	/**
	 * 用户跳转JSP页面
	 * 
	 * 此方法不考虑权限控制
	 * 
	 * @param folder
	 *            路径
	 * @param jspName
	 *            JSP名称(不加后缀)
	 * @return 指定JSP页面
	 */
	@RequestMapping("/{folder}/{jspName}")
	public String redirectJsp(@PathVariable String folder, @PathVariable String jspName) {
		return "/" + folder + "/" + jspName;
	}

	public LoggerUtil getLoggerUtil() {
		return loggerUtil;
	}

	public void setLoggerUtil(LoggerUtil loggerUtil) {
		this.loggerUtil = loggerUtil;
	}

	/**
	 * 保存系统操作日志
	 * @author zhouq
	 * @create 2014年6月30日 上午11:28:51
	 * @param session
	 * @param operation_type 操作类型(1：新增，2：修改，3：更新，4：删除，5：查询)
	 * @param tablename 操作表名
	 * @param tableid 操作主键
	 * @param comment 备注
	 */
	public void saveSysLog(HttpSession session,int operation_type,String tablename,String tableid,String comment) {
		Tlog entity = new Tlog();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		entity.setId(UUID.randomUUID().toString());
		entity.setUserid(sessionInfo.getId());//用户ID
		entity.setUsername(sessionInfo.getName());//用户名称
		entity.setIp(sessionInfo.getIp());//用户IP
		entity.setOperation_type(operation_type);//操作类型
		entity.setTableid(tableid);//操作表的主键
		entity.setTablename(tablename);//操作的表名称
		entity.setComment(comment);//日志备注
		entity.setCtime(DateUtils.getCurrentTime());
		try {
			logService.add(entity);
		} catch (Exception e) {
			e.printStackTrace();
			loggerUtil.error("保存系统操作日志异常!");
		}
	}
}
