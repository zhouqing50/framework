/**
 * Program  : CodeGenerateFactory.java
 * Author   : zhouq
 * Create   : 2014-6-9 上午10:22:03
 *
 * Copyright 2014 by jt56 Technologies Ltd.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of jt56 Technologies Ltd.("Confidential Information").  
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with jt56 Technologies Ltd.
 *
 */

package jt56.comm.code.factory;

import java.util.Map;

import jt56.comm.code.def.CodeResourceUtil;
import jt56.comm.code.util.CommonPageParser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;

import codeGenerate.CreateBean;



/**
 * 
 * @author zhouq
 * @version 1.0.0
 * @2014-6-9 上午10:22:03
 */
public class CodeGenerateFactory {
	private static final Log log = LogFactory.getLog(CodeGenerateFactory.class);
	  private static String url = CodeResourceUtil.URL;
	  private static String username = CodeResourceUtil.USERNAME;
	  private static String passWord = CodeResourceUtil.PASSWORD;

	  private static String buss_package = CodeResourceUtil.bussiPackage;
	  private static String projectPath = getProjectPath();

	  public static void codeGenerate(String tableName, String codeName, String entityPackage, String keyType)
	  {
	    CreateBean createBean = new CreateBean();
	    createBean.setMysqlInfo(url, username, passWord);

	    String className = createBean.getTablesNameToClassName(tableName);
	    String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());

	    String srcPath = projectPath + CodeResourceUtil.source_root_package + "\\";

	    String pckPath = srcPath + CodeResourceUtil.bussiPackageUrl + "\\";

	    String webPath = projectPath + CodeResourceUtil.web_root_package + "\\WEB-INF\\" + "\\views\\" + CodeResourceUtil.bussiPackageUrl + "\\";

	    String modelPath = entityPackage + "\\" + "page\\" + className + "Page.java";
	    String beanPath = entityPackage + "\\" + "entity\\" + className + ".java";
	    String mapperPath = entityPackage + "\\" +  "dao\\" + className + "Dao.java";
	    String servicePath = entityPackage + "\\" + "service\\" + className + "ServiceI.java";
	    String serviceImplPath = entityPackage + "\\" + "service\\" + "impl\\" + className + "ServiceImpl.java";
	    String controllerPath = entityPackage + "\\" + "controller\\" + className + "Controller.java";
	    String sqlMapperPath = entityPackage + "\\" + "mapper\\" + className + "Mapper.xml";
	    webPath = webPath + entityPackage + "\\";

	    String webJsPath = projectPath + CodeResourceUtil.web_root_package + "\\js\\" + CodeResourceUtil.bussiPackageUrl + "\\";
	    webJsPath = webJsPath + entityPackage + "\\";
	    String jspListPath = lowerName + ".jsp";
	    String jspAddPath = lowerName + "Add" + ".jsp";
	    String jspEditPath = lowerName + "Edit" + ".jsp";
	    
	    VelocityContext context = new VelocityContext();
	    context.put("className", className);
	    context.put("lowerName", lowerName);
	    context.put("codeName", codeName);
	    context.put("tableName", tableName);
	    context.put("bussPackage", buss_package);
	    context.put("entityPackage", entityPackage);
	    context.put("keyType", keyType);
	    try
	    {
	      context.put("feilds", createBean.getBeanFeilds(tableName));
	    } catch (Exception e) {
	      e.printStackTrace();
	    }

	    try
	    {
	      Map<String, Object> sqlMap = createBean.getAutoCreateSql(tableName);
	      context.put("columnDatas", createBean.getColumnDatas(tableName));
	      context.put("SQL", sqlMap);
	    } catch (Exception sqlMap) {
	    	sqlMap.printStackTrace();
	      return;
	    }

	    CommonPageParser.WriterPage(context, "EntityTemplate.ftl", pckPath, beanPath);
	   // CommonPageParser.WriterPage(context, "PageTemplate.ftl", pckPath, modelPath);
	    CommonPageParser.WriterPage(context, "DaoTemplate.ftl", pckPath, mapperPath);
	    CommonPageParser.WriterPage(context, "ServiceTemplate.ftl", pckPath, servicePath);
	    CommonPageParser.WriterPage(context, "ServiceImplTemplate.ftl", pckPath, serviceImplPath);
	    CommonPageParser.WriterPage(context, "MapperTemplate.xml", pckPath, sqlMapperPath);
	    CommonPageParser.WriterPage(context, "ControllerTemplate.ftl", pckPath, controllerPath);
	   
	    CommonPageParser.WriterPage(context, "jspListTemplate.ftl", webPath, jspListPath);
	    CommonPageParser.WriterPage(context, "jspAddTemplate.ftl", webPath, jspAddPath);
	    CommonPageParser.WriterPage(context, "jspEditTemplate.ftl", webPath, jspEditPath);
	    
	    log.info("----------------------------代码生成完毕---------------------------");
	  }

	  public static String getProjectPath()
	  {
	    String path = System.getProperty("user.dir").replace("\\", "/") + "/";
	    return path;
	  }
}

