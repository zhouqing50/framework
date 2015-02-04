/**
 * Program  : UserPojo.java
 * Author   : zhouq
 * Create   : 2014年7月2日 上午9:43:00
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

package jt56.webService.entity;

import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author zhouq
 * @version 1.0.0
 * @2014年7月2日 上午9:43:00
 */
@XmlRootElement(name = "UserInfos")
public class Users {
		private List<User> users;
	    
	    private User[] userArr;
	    
	    private HashMap<String, User> maps;

		public List<User> getUsers() {
			return users;
		}

		public void setUsers(List<User> users) {
			this.users = users;
		}

		public User[] getUserArr() {
			return userArr;
		}

		public void setUserArr(User[] userArr) {
			this.userArr = userArr;
		}

		public HashMap<String, User> getMaps() {
			return maps;
		}

		public void setMaps(HashMap<String, User> maps) {
			this.maps = maps;
		}
	    
	    
}

