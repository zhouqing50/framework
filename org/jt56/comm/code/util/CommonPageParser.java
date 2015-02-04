/**
 * Program  : CommonPageParser.java
 * Author   : zhouq
 * Create   : 2014-6-9 上午10:26:07
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

package jt56.comm.code.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import jt56.comm.code.factory.CodeGenerateFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * 
 * @author zhouq
 * @version 1.0.0
 * @2014-6-9 上午10:26:07
 */
public class CommonPageParser {
	 private static VelocityEngine ve;
	  private static final String CONTENT_ENCODING = "UTF-8";
	  private static final Log log = LogFactory.getLog(CommonPageParser.class);

	  private static boolean isReplace = true;

	  static
	  {
	    try
	    {
	      String templateBasePath = CodeGenerateFactory.getProjectPath() + "/resources/jeecg/template";
	      Properties properties = new Properties();
	      properties.setProperty("resource.loader", "file");
	      properties.setProperty("file.resource.loader.description", "Velocity File Resource Loader");
	      properties.setProperty("file.resource.loader.path", templateBasePath);
	      properties.setProperty("file.resource.loader.cache", "true");
	      properties.setProperty("file.resource.loader.modificationCheckInterval", "30");
	      properties.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.Log4JLogChute");
	      properties.setProperty("runtime.log.logsystem.log4j.logger", "org.apache.velocity");
	      properties.setProperty("directive.set.null.allowed", "true");
	      VelocityEngine velocityEngine = new VelocityEngine();
	      velocityEngine.init(properties);
	      ve = velocityEngine;
	    } catch (Exception e) {
	      log.error(e);
	    }
	  }

	  public static void WriterPage(VelocityContext context, String templateName, String fileDirPath, String targetFile)
	  {
	    try
	    {
	      File file = new File(fileDirPath + targetFile);
	      if (!(file.exists())) {
	        new File(file.getParent()).mkdirs();
	      }
	      else if (isReplace) {
	        log.info("替换文件:" + file.getAbsolutePath());
	      }

	      Template template = ve.getTemplate(templateName, "UTF-8");
	      FileOutputStream fos = new FileOutputStream(file);
	      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
	      template.merge(context, writer);
	      writer.flush();
	      writer.close();
	      fos.close();
	      log.info("生成文件：" + file.getAbsolutePath());
	    } catch (Exception e) {
	      log.error(e);
	    }
	  }
}

