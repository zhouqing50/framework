package jt56.comm.system.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * <p>Description: 日期处理常用类</p>
 * @date 2013年9月10日
 * @author 华侨城项目拷贝
 * @version 2.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class DateUtils {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dateFormatDB = new SimpleDateFormat("yyyyMMdd");// 数据库使用的日期格式
	public static SimpleDateFormat dataTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String formatDateTime(Date date) {
		return dataTimeFormat.format(date);
	}

	/**
	 * 创建一个"yyyy-MM-dd"日期的格式化对象
	 * @return "yyyy-MM-dd"日期的格式化对象
	 */
	private static SimpleDateFormat newLongYMDFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}

	/**
	 * 创建一个"yyyy-MM-dd HH:mm:ss"日期的格式化对象
	 * @return "yyyy-MM-dd HH:mm:ss"日期的格式化对象
	 */
	private static SimpleDateFormat newLongYMDHMSFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * "yyyyMMddHHmmss"格式的日期转换为"yyyy-MM-dd HH:mm:ss"格式的日期
	 * @param shortYMDHMS "yyyyMMddHHmmss"格式的日期
	 * @return "yyyy-MM-dd HH:mm:ss"格式的日期
	 * @throws ParseException
	 */
	public static String toLongYMDHMS(String shortYMDHMS) {
		try {
			return newLongYMDHMSFormat().format(newShortYMDHMSFormat().parse(shortYMDHMS));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获得"yyyy-MM-dd"格式的当前日期
	 * @return 返回"yyyy-MM-dd"格式的当前日期
	 */
	public static String getLongYMD() {
		return newLongYMDFormat().format(new Date());
	}

	/**
	 * 创建一个"yyyyMMdd"日期的格式化对象
	 * @return "yyyyMMdd"日期的格式化对象
	 */
	private static SimpleDateFormat newShortYMDFormat() {
		return new SimpleDateFormat("yyyyMMdd");
	}

	/**
	 * 创建一个"yyyyMMddHHmmss"日期的格式化对象
	 * @return "yyyyMMddHHmmss"日期的格式化对象
	 */
	private static SimpleDateFormat newShortYMDHMSFormat() {
		return new SimpleDateFormat("yyyyMMddHHmmss");
	}

	/**
	 * 获得"yyyyMMddHHmmss"格式的当前日期
	 * @return 返回"yyyyMMddHHmmss"格式的当前时间
	 */
	public static String getShortYMDHMS() {
		return newShortYMDHMSFormat().format(new Date());
	}

	/**
	 * "yyyyMMdd"格式的日期转换为"yyyy-MM-dd"格式的日期
	 * @param shortYMD "yyyyMMdd"格式的日期
	 * @return "yyyy-MM-dd"格式的日期
	 * @throws ParseException
	 */
	public static String toLongYMD(String shortYMD) {
		try {
			return newLongYMDFormat().format(newShortYMDFormat().parse(shortYMD));
		} catch (ParseException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * 功能：生成日期yyyyMMdd
	 *
	 * @return
	 */
	public static String getDbDate() {
		return dateFormatDB.format(new Date());
	}

	/**
	 * 
	 * 功能：把日期yyyy-MM-dd格式转换成yyyyMMDD日期格式
	 *
	 * @param dateStr
	 * @return
	 */
	public static String convertClientDateToDbDate(String dateStr) {
		String dbDateStr = null;
		try {
			dbDateStr = dateFormatDB.format(dateFormat.parse(dateStr));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbDateStr;
	}

	/**
	 * 
	 * 功能：解析数据库中的日期字符串 格式:yyyy-MM-dd
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parseDate(String dateStr) {
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * 功能：格式化日期字符串
	 * 例如：2008-8-8  转换为2008-08-08
	 *
	 * @param dateStr
	 * @return
	 */
	public static String getDateStrFormat(String dateStr) {
		try {
			Date date = dateFormat.parse(dateStr);
			return dateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 功能：解析数据库中的时间字符串 格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parseDateTime(String dateTimeStr) {
		Date date = null;
		try {
			date = dataTimeFormat.parse(dateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 计算两个日期之间的天数
	 * 
	 * @param startDate
	 *          开始时间
	 * @param endDate
	 *          结束时间
	 * @return
	 */
	public static int calcDays(String startDate, String endDate) {
		int days = 1;
		try {
			long start = dateFormat.parse(startDate).getTime();
			long end = dateFormat.parse(endDate).getTime();
			days = (int) ((end - start) / (24 * 60 * 60 * 1000));
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
		return days;
	}

	/**
	 * 计算两个日期之间的天数
	 * 
	 * @param startDate
	 *          开始时间
	 * @param endDate
	 *          结束时间
	 * @return
	 */
	public static int calcDay(String startDate, String endDate) {
		int days = 1;
		try {
			long start = dateFormatDB.parse(startDate).getTime();
			long end = dateFormatDB.parse(endDate).getTime();
			days = (int) ((end - start) / (24 * 60 * 60 * 1000));
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
		return days;
	}

	/**
	 * 功能：指定日期加上指定天数
	 * 
	 * @param date
	 *          日期
	 * @param day
	 *          天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 功能：指定日期加上指定天数
	 * 
	 * @param date
	 *          日期
	 * @param minute
	 *          分钟
	 * @return 返回相加后的日期
	 */
	public static Date addMinute(Date date, int minute) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) minute) * 60 * 1000);
		return c.getTime();
	}

	/**
	 * 
	 * 功能：添加指定秒杀的时间
	 *
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date addSecond(Date date, int second) {
		long s = date.getTime();
		s = s + second * 1000;
		return new Date(s);
	}

	/**
	 * 功能：指定日期减去指定天数
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date diffDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 
	 * 功能：分钟相差 minute的时间值
	 * 
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date getDateByMinuteAdd(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * 功能:两个日期相隔天数
	 * 
	 * @param startDate
	 *          开始日期
	 * @param endDate
	 *          结束日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(Date startDate, Date endDate) {
		long endMillis = endDate.getTime();
		long startMillis = startDate.getTime();
		long s = (endMillis - startMillis) / (24 * 3600 * 1000);
		return (int) s;
	}

	/**
	 * 功能：传入时间按所需格式返回时间字符串
	 * 
	 * @param date
	 *          java.util.Date格式
	 * @param format
	 *          yyyy-MM-dd HH:mm:ss | yyyy年MM月dd日 HH时mm分ss秒
	 * @return
	 */
	public static String format(Date date, String format) {
		String result = "";
		try {
			if (date == null) {
				date = new Date();// 如果时间为空,则默认为当前时间
			}
			if (StringUtil.isBlank(format)) {// 默认格式化形式
				format = "yyyy-MM-dd";
			}
			DateFormat df = new SimpleDateFormat(format);
			result = df.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 功能：传入时间字符串按所需格式返回时间
	 * 
	 * @param dateStr
	 *          时间字符串
	 * @param format
	 *          跟传入dateStr时间的格式必须一样 yyyy-MM-dd HH:mm:ss | yyyy年MM月dd日 HH时mm分ss秒
	 * @return
	 */
	public static Date format(String dateStr, String format) {
		if (StringUtil.isBlank(dateStr)) {
			return new Date();
		}
		if (StringUtil.isBlank(format)) {
			format = "yyyy-MM-dd";
		}
		Date date = null;
		try {
			DateFormat f = new SimpleDateFormat(format);
			date = f.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;

	}

	/**
	 * 功能：时间字符串格式转换
	 * 
	 * @param dateStr
	 *          时间字符串
	 * @param format
	 *          时间字符串的格式
	 * @param toFormat
	 *          格式为的格式
	 * @return
	 */
	public static String format(String dateStr, String format, String toFormat) {
		return format(format(dateStr, format), toFormat);
	}

	/**
	 * 功能：格式化rss的时间
	 * 输入:
	 * @param date
	 * @return
	 */
	public static String formatRssDate(Date date) {
		if (date == null) {
			date = new Date();// 如果时间为空,则默认为当前时间
		}
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		SimpleTimeZone zone = new SimpleTimeZone(8, "GMT");
		sdf.setTimeZone(zone);
		return sdf.format(date);
	}

	/**
	 * 功能：返回年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);

	}

	/**
	 * 功能：返回月
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能：返回日
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能：返回小时
	 * 
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能：返回分
	 * 
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 功能：返回星期 1：星期一，2:星期二 ... 6:星期六 7:星期日
	 * 
	 * @param date
	 * @return
	 */
	public static int getChinaWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 0) {
			return 7;
		} else {
			return week;
		}
	}

	/**
	 * 功能：返回秒
	 * 
	 * @param date
	 * @return
	 */
	public static int getSecond2(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}

	/**
	 * 功能：返回毫秒
	 * 
	 * @param date
	 * @return
	 */
	public static long getMillis(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 功能：获取当前月的第一天日期
	 * 
	 * @return
	 */
	public static Date getMonFirstDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(getYear(date), getMonth(date) - 1, 1);
		return c.getTime();
	}

	/**
	 * 功能：获取当前月的最后一天日期
	 * 
	 * @return
	 */
	public static Date getMonLastDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(getYear(date), getMonth(date), 1);

		c.setTimeInMillis(c.getTimeInMillis() - (24 * 3600 * 1000));
		return c.getTime();
	}

	/**
	 * 功能：获取当前日期 格式:2008-02-02 23:11:10
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		Date date = new Date();
		return dataTimeFormat.format(date);
	}

	/**
	 * 功能：获取当前日期 格式:20101010
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * 
	 * 方法用途: 获取当前日期 格式只包括年月:201010<br>
	 * 实现步骤: <br>
	 * @return
	 */
	public static String getCurrentYearMonth() {
		return newShortYMFormat().format(new Date());
	}

	/**
	 * 创建一个"yyyyMM"日期的格式化对象
	 * @return "yyyyMM"日期的格式化对象
	 */
	private static SimpleDateFormat newShortYMFormat() {
		return new SimpleDateFormat("yyyyMM");
	}

	/**
	 * 获得距离输入月份的diffMonth月的日期
	 * @param month "yyyyMM"格式的日期
	 * @param diffMonth 相差的月数
	 * @return "yyyyMM"格式的日期
	 * @throws ParseException
	 */
	public static String getShortYMDiffMonth(String month, int diffMonth) {
		SimpleDateFormat sdf = newShortYMFormat();
		try {
			sdf.parse(month);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c = sdf.getCalendar();
		c.add(Calendar.MONTH, diffMonth);
		return sdf.format(c.getTime());
	}

	/**
	 * 获得距离给定日期diffDay天的日期
	 * @param shortYMD "yyyyMMdd"格式的日期
	 * @param diffDay 相差的天数
	 * @return "yyyyMMdd"格式的日期
	 * @throws ParseException
	 */
	public static String getShortYMDDiffDay(String shortYMD, int diffDay) {
		SimpleDateFormat sdf = newShortYMDFormat();
		try {
			sdf.parse(shortYMD);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c = sdf.getCalendar();
		c.add(Calendar.DATE, diffDay);
		return sdf.format(c.getTime());
	}
	
	/**
	 * 获得距离给定月的日期
	 * @param shortYMD "yyyyMMdd"格式的日期
	 * @param diffMonth 相差的月数
	 * @return "yyyyMMdd"格式的日期
	 * @throws ParseException
	 */
	public static String getShortYMDDiffMonth(String shortYMD, int diffMonth) {
		SimpleDateFormat sdf = newShortYMDFormat();
		try {
			sdf.parse(shortYMD);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c = sdf.getCalendar();
		c.add(Calendar.MONTH, diffMonth);
		return sdf.format(c.getTime());
	}

	/**
	 * 获取某月份的最后一天
	 * @param shortYM 月份
	 * @return 输入月份的最后一天
	 * @throws Exception
	 */
	public static String getEndDayOfMonth(String shortYM) {
		String month = "";
		try {
			month = DateUtils.getShortYMDiffMonth(shortYM, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DateUtils.getShortYMDDiffDay(month + "01", -1);
	}

	/**
	* 获得"yyyyMMdd"格式的当前日期
	* @return 返回"yyyyMMdd"格式的当前日期
	*/
	public static String getShortYMD() {
		return newShortYMDFormat().format(new Date());
	}

	/**
	 * 获得两个日期之间的所有日期列表（包括起始日期和结束日期）
	 * @param startShortYMD "yyyyMMdd"格式的起始日期
	 * @param endShortYMD "yyyyMMdd"格式的结束日期
	 * @return "yyyyMMdd"格式的日期列表
	 * @throws ParseException
	 */
	public static List getShortYMDList(String startShortYMD, String endShortYMD) throws ParseException {
		SimpleDateFormat startDateFormat = newShortYMDFormat();
		startDateFormat.parse(startShortYMD);
		Calendar startCal = startDateFormat.getCalendar();

		SimpleDateFormat endDateFormat = newShortYMDFormat();
		endDateFormat.parse(endShortYMD);
		Calendar endCal = endDateFormat.getCalendar();

		List dateList = new ArrayList();
		while (startCal.before(endCal)) {
			dateList.add(startDateFormat.format(startCal.getTime()));
			startCal.add(Calendar.DATE, 1);
		}

		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			dateList.add(startDateFormat.format(endCal.getTime()));
		}

		return dateList;
	}

	/**
	 * 获得两个日期之间的所有日期列表（包括起始日期和结束日期）
	 * @param startShortYM "yyyyMM"格式的起始日期
	 * @param endShortYM "yyyyMM"格式的结束日期
	 * @return "yyyyMM"格式的日期列表
	 * @throws ParseException
	 */
	public static List getShortYMList(String startShortYM, String endShortYM) throws ParseException {
		SimpleDateFormat startDateFormat = newShortYMFormat();
		startDateFormat.parse(startShortYM);
		Calendar startCal = startDateFormat.getCalendar();

		SimpleDateFormat endDateFormat = newShortYMFormat();
		endDateFormat.parse(endShortYM);
		Calendar endCal = endDateFormat.getCalendar();

		List dateList = new ArrayList();
		while (startCal.before(endCal)) {
			dateList.add(startDateFormat.format(startCal.getTime()));
			startCal.add(Calendar.MONTH, 1);
		}

		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			dateList.add(startDateFormat.format(endCal.getTime()));
		}

		return dateList;
	}

	/**
	 * 验证输入的日期是否合法
	 * @param expDate
	 * @return
	 */
	public static boolean checkExpDate(String expDate) {
		int year = Integer.parseInt(expDate.substring(0, 4));
		int month = Integer.parseInt(expDate.substring(4, 6));
		int day = Integer.parseInt(expDate.substring(6, 8));
		if (month > 12 || month < 1) {
			return false;
		}

		int[] monthLengths = new int[] { 0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (isLeapYear(year)) {
			monthLengths[2] = 29;
		} else {
			monthLengths[2] = 28;
		}

		int monthLength = monthLengths[month];
		if (day < 1 || day > monthLength) {
			return false;
		}
		return true;
	}

	/** 
	 * 是否是闰年 
	 * */
	private static boolean isLeapYear(int year) {
		return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
	}

	/**
	 * 比较日期大小
	 * @param DATE1
	 * @param DATE2
	 * @return
	 */
	public static int compareDate(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	    * "yyyy-MM-dd HH:mm:ss"格式的日期转换为"yyyyMMddHHmmss"格式的日期
	    * @param longYMDHMS "yyyy-MM-dd HH:mm:ss"格式的日期
	    * @return "yyyyMMddHHmmss"格式的日期
	    * @throws ParseException
	    */
	public static String toShortYMDHMS(String longYMDHMS) {
		try {
			return newShortYMDHMSFormat().format(newLongYMDHMSFormat().parse(longYMDHMS));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获得两个日期之间的所有日期列表（包括起始日期和结束日期）
	 * @param startLongYMD "yyyy-MM-dd"格式的起始日期
	 * @param endLongYMD "yyyy-MM-dd"格式的结束日期
	 * @return "yyyy-MM-dd"格式的日期列表
	 * @throws ParseException
	 */
	public static List getLongYMDList(String startLongYMD, String endLongYMD) throws ParseException {
		SimpleDateFormat startDateFormat = newLongYMDFormat();
		startDateFormat.parse(startLongYMD);
		Calendar startCal = startDateFormat.getCalendar();

		SimpleDateFormat endDateFormat = newLongYMDFormat();
		endDateFormat.parse(endLongYMD);
		Calendar endCal = endDateFormat.getCalendar();

		List dateList = new ArrayList();
		while (startCal.before(endCal)) {
			dateList.add(startDateFormat.format(startCal.getTime()));
			startCal.add(Calendar.DATE, 1);
		}

		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			dateList.add(startDateFormat.format(endCal.getTime()));
		}

		return dateList;
	}

	/**
	 * 获得距离给定日期diffDay天的日期
	 * @param longYMD "yyyy-MM-dd"格式的日期
	 * @param diffDay 相差的天数
	 * @return "yyyy-MM-dd"格式的日期
	 * @throws ParseException
	 */
	public static String getLongYMDDiffDay(String longYMD, int diffDay) throws ParseException {
		SimpleDateFormat sdf = newLongYMDFormat();
		sdf.parse(longYMD);
		Calendar c = sdf.getCalendar();
		c.add(Calendar.DATE, diffDay);
		return sdf.format(c.getTime());
	}

	/**
	 * "yyyy-MM-dd"格式的日期转换为"yyyyMMdd"格式的日期
	 * @param longYMD "yyyy-MM-dd"格式的日期
	 * @return "yyyyMMdd"格式的日期
	 * @throws ParseException
	 */
	public static String toShortYMD(String longYMD) {
		try {
			return newShortYMDFormat().format(newLongYMDFormat().parse(longYMD));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static int FLOWID = 0;

	/**
	 * 
	 * 方法用途: 获取22位流水<br>
	 * 实现步骤: 格式：yyyyMMddHHMMSS00000001<br>
	 * @return
	 */
	public static String getFlowID() throws Exception {
		String prefix = newShortYMDHMSFormat().format(new Date());
		java.util.concurrent.locks.ReentrantLock lock = new ReentrantLock();
		try {
			lock.lock();
			FLOWID++;
			if (FLOWID == 99999999) {
				FLOWID = 0;
			}
			NumberFormat nf = NumberFormat.getInstance();
			nf.setGroupingUsed(false);
			nf.setMaximumIntegerDigits(8);
			nf.setMinimumIntegerDigits(8);
			String suffix = nf.format(FLOWID);
			return prefix.concat(suffix);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 
	 * 方法用途: 获取指定月的第一天，返回格式为20130901<br>
	 * 实现步骤: <br>
	 * @param month	格式为201309
	 * @return
	 */
	public static String getFirstDay(String month) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		try {
			sdf.parse(month);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = sdf.getCalendar();
		cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
		String firstDay = dateFormatDB.format(cal.getTime());

		return firstDay;
	}

	/**
	 * 
	 * 方法用途: 获取指定月的最后一天，返回格式为20130930<br>
	 * 实现步骤: <br>
	 * @param month 格式为201309
	 * @return
	 */
	public static String getLastDay(String month) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		try {
			sdf.parse(month);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = sdf.getCalendar();

		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		String lastDay = dateFormatDB.format(cal.getTime());

		return lastDay;
	}

	/**
	 * 
	 * 方法用途: 判断当天是否是此月的第一天<br>
	 * 实现步骤: <br>
	 * @return
	 */
	public static boolean isFirstDay() {
		String firstDay = getFirstDay(getYearMonth());
		String toDay = dateFormatDB.format(new Date());
		if (firstDay.equals(toDay)) {
			return true;
		}

		return false;
	}
	
	public static boolean isSunday() {
		if (getChinaWeek(new Date()) == 7) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * 方法用途: 返回当前的年月<br>
	 * 实现步骤: <br>
	 * @return
	 */
	public static String getYearMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(new Date());
	}
	
	/**
	 * 
	 * 方法用途: 根据参数日期判断是否是季节的第一天<br>
	 * 实现步骤: <br>
	 * @param date 格式为yyyyMMdd
	 * @return
	 */
	public static boolean isSeasonFirstDay(String date) {
		if (date.substring(4).equals("0101")) {
			return true;
		} else if (date.substring(4).equals("0401")) {
			return true;
		} else if (date.substring(4).equals("0701")) {
			return true;
		} else if (date.substring(4).equals("1001")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * 方法用途: 根据参数日期判断是否是1月1日<br>
	 * 实现步骤: <br>
	 * @param date 格式为yyyyMMdd
	 * @return
	 */
	public static boolean isYearFirstDay(String date) {
		if (date.substring(4).equals("0101")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * 方法用途: 获取上个月的年月，返回字符串，如201312<br>
	 * 实现步骤: <br>
	 * @param date
	 * @return
	 */
	public static String getLastDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(cal.getTime());
    }
	
	/**
	 * 获取当前日期为日期型
	 *
	 * @return 当前日期，java.util.Date类型
	 */
	public static Date getCurrentTime(){
		Calendar cal = Calendar.getInstance();

		//String currentDate = null;
		Date d = cal.getTime();

		return d;
	}
	
	public static void main(String[] args) {
		System.out.println((getLastDate(new Date())));
	}
}
