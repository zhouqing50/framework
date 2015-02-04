package jt56.comm.system.util;
/**
 * 
 * <p>Description:生成主键 </p>
 * @date 2013年11月5日
 * @author 周青
 * @version 2.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class PrimaryKey {
	/**
	 * 分润流水值后缀初始值从1开始，即0001
	 */
	private static int SERIALNUM_SUFFIX_INIT = 1;
	
	/**
	 * 分润流水值生成的日期，即20131015 
	 * 主要用于不是同一天，则SERIALNUM_SUFFIX_INIT不需要递增，直接归1
	 */
	private static String CREATETIME = "20131015";
	
	/**
	 * 如果startS:HYDL,dateType:yyyyMMdd,NumLen:4  则生成HYDL201310150001
	 * 
	 * 方法用途: <br>
	 * 实现步骤: <br>
	 * @param startS 主键的开始字符
	 * @param dateType 主键中间要包含的时间格式
	 * @param NumLen 主键动态生成的数字长度
	 * @return
	 */
	public synchronized static String createKey(String startS, String dateType, int NumLen){
		
		if(isTime()){//如果不是同一天调用此生成主键方法，则需要从1开始
			SERIALNUM_SUFFIX_INIT = 1;
		}
		String suffix = SERIALNUM_SUFFIX_INIT + "";

    		if(suffix.length() >= NumLen){
    			suffix = suffix.substring(0, NumLen);
    		}else{
    			int c = NumLen - suffix.length();
    			for(int i=0;i<c;i++){
    				suffix = "0" + suffix;
    			}
    		}
    	String dataStr = "";
    	if ("yyyyMMdd".equals(dateType)) {
    		dataStr = DateUtils.getShortYMD();
		} else if("yyyyMMddHHmmss".equals(dateType)) {
			dataStr = DateUtils.getShortYMDHMS();
		}else {
			
		}
    	SERIALNUM_SUFFIX_INIT++;
    	String key = startS + dataStr + suffix;
		return key;
	}

	/**
	 * 
	 * 方法用途: 用于判断是否是同一天调用生成主键的方法<br>
	 * 实现步骤: <br>
	 * @return
	 */
	public static boolean isTime(){
		boolean flag = false;
		if(DateUtils.getCurrentDate().equals(CREATETIME)){
			flag = true;
		}
		return flag;
		
	}
	
	public static void main(String[] args) {
		String aa = createKey("JSDX","yyyyMMddHHmmss",4);
		String bb = createKey("SZDD","yyyyMMdd",4);
		System.out.println(aa+"==="+bb);
	}

}
