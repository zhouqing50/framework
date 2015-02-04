package jt56.comm.system.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;


public class WebUtil {
	public static String getPostContent(HttpServletRequest request) throws IOException{
		StringBuffer messageContent=new StringBuffer();
		try {
			InputStream requert_inputStream=request.getInputStream();
			BufferedReader br = new BufferedReader(new  InputStreamReader(requert_inputStream,"UTF-8"));
			String readString=null;
			while((readString=br.readLine())!=null){
				messageContent.append(readString);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return messageContent.toString();
	}
	
	/**
	 * .net传过来的数据 Json格式"{\"name\":\"zzz\",\"pwd\":\"1223\"}"
	 * 转换为{"name":"zzz","pwd":"1223"}
	 * @author zhouq
	 * @create 2014-5-26 下午1:42:30
	 * @param jsonStr
	 * @return
	 */
	public static String getJsonString(String jsonStr) {
		if (jsonStr.indexOf("\"") == 0) { //表示.net传过来的数据 Json格式"{\"name\":\"zzz\",\"pwd\":\"1223\"}"
			jsonStr = jsonStr.replaceAll("\\\\\"", "#");
			jsonStr = jsonStr.replaceAll("\"", "");
			jsonStr = jsonStr.replaceAll("#", "\"");
		}
		return jsonStr;
	}
	
}
