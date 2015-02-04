package jt56.comm.system.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public final class CommonJsonMsg{
	private CommonJsonMsg(){
		
	}  
	public CommonJsonMsg(JSONObject jsonObject){
		this.jsonObject=jsonObject;
	}
	private JSONObject jsonObject;
	public static CommonJsonMsg genCommonJsonMsg(String text) {
		try {
			CommonJsonMsg cjm=new CommonJsonMsg();
			cjm.jsonObject=JSONObject.fromObject(text);
			return cjm;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static CommonJsonMsg genCommonJsonMsg() {
		try {
			CommonJsonMsg cjm=new CommonJsonMsg();
			cjm.jsonObject=new JSONObject();
			return cjm;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public CommonJsonMsg getJsonString(String jsonStr) {
		try {
			CommonJsonMsg cjm=new CommonJsonMsg();
			cjm.jsonObject=new JSONObject();
			return cjm;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public  Map<String,String> ergodic(){
        Map<String,String> map = new HashMap<String,String>(); 
        for(Iterator<String> iter = jsonObject.keys(); iter.hasNext();){ 
            String key = (String)iter.next(); 
             if (jsonObject.get(key) instanceof String) {
            	map.put(key, (String)jsonObject.get(key)); 
            }else
            	continue;
        } 
        return map; 
	}
	public Object getObject(String key,Class<?> c){
		JSONObject jo=jsonObject.getJSONObject(key);
		return JSONObject.toBean(jo, c);
	}
	public Object getObject(Class<?> c){
		return JSONObject.toBean(jsonObject, c);
	}
	public void setObject(String key,Object ob){
		 JSONObject obj = JSONObject.fromObject(ob);
		 jsonObject.put(key, obj);
		
	}
	public String toString(){
		return jsonObject.toString();
	}
	
	public CommonJsonMsg getChild(String key){
		JSONObject jo=jsonObject.getJSONObject(key);
		CommonJsonMsg cjm=new CommonJsonMsg(jo);
		return cjm;
	}
	
	public boolean isNullObject(){
		return jsonObject.isNullObject();
	}
	public boolean isEnpty(){
		return jsonObject.isEmpty();
	}
	public boolean isArray(){
		return jsonObject.isArray();
	}
	public void setchild(String key,CommonJsonMsg cjm){
		jsonObject.put(key, cjm.getJsonObject());
	}
	public String getString(String key){
		try {
			return jsonObject.getString(key);
		} catch (Exception e) {
			System.out.println(key+"不存在");
		}
		return null;
	}
	public void setString(String key,String value){
		jsonObject.put(key, value);
	}
	
	
	public List<?> getArray(String key,Class<?> c){
		JSONArray jsonArray=jsonObject.getJSONArray(key);
		return JSONArray.toList(jsonArray,c);
	}
	public void setArray(String key,List<?>list){
		jsonObject.put(key, list);
	}
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	
	
}
