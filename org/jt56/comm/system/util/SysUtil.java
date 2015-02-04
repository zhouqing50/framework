/**
 * Program  : SysUtil.java
 * Author   : zhouq
 * Create   : 2014-6-10 上午10:02:55
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

package jt56.comm.system.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jt56.comm.system.model.DicType;




/**
 * 
 * @author zhouq
 * @version 1.0.0
 * @2014-6-10 上午10:02:55
 */
public class SysUtil {

	public static HashMap<String, List<DicType>> dicMap = new HashMap<String, List<DicType>>();
	
	public static List<DicType> getDicTypeListByKey (String dicKey){
		List<DicType> dicList = new ArrayList<DicType>();
		dicList = dicMap.get(dicKey);
		return dicList;
	}
}

