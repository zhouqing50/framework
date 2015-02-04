package jt56.comm.code.def;
/**
 * Program  : TableConvert.java
 * Author   : zhouq
 * Create   : 2014-6-9 上午10:10:53
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

/**
 * 
 * @author zhouq
 * @version 1.0.0
 * @2014-6-9 上午10:10:53
 */
public class TableConvert {
	  public static String getNullAble(String nullable)
	  {
	    if (("YES".equals(nullable)) || ("yes".equals(nullable)) || ("y".equals(nullable)) || ("Y".equals(nullable))) {
	      return "Y";
	    }
	    if (("NO".equals(nullable)) || ("N".equals(nullable)) || ("no".equals(nullable)) || ("n".equals(nullable))) {
	      return "N";
	    }
	    return null;
	  }
}

