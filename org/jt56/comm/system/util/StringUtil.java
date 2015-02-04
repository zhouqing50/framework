package jt56.comm.system.util;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 
 * <p>Description: 字符串相关的常用方法</p>
 * @date 2013年9月10日
 * @author 斯多
 * @version 2.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class StringUtil {
	/**
	 * 将字符串编码由 GBK 转换为 ISO-8859-1。
	 * @param str 要转换的字符串
	 * @return String 由 GBK 转换为 ISO-8859-1 的字符串
	 */
	public static String encode(String str) {
		try {
			return str == null ? null : new String(str.getBytes("GBK"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将字符串编码由 ISO-8859-1 转换为 GBK。
	 * @param str 要转换的字符串
	 * @return String 由 ISO-8859-1 转换为 GBK 的字符串
	 */
	public static String decode(String str) {
		try {
			return str == null ? null : new String(str.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将字符串编码由 ISO-8859-1 转换为 UTF-8。
	 * @param str 要转换的字符串
	 * @return String 由 ISO-8859-1 转换为 UTF-8 的字符串
	 */
	public static String decodeToUTF(String str) {
		try {
			return str == null ? null : new String(str.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 解析字符串为双精度浮点数，如果字符串为null则解析为0。
	 * @param str 要解析的字符串
	 * @return 由字符串解析的浮点数
	 */
	public static double parseDouble(String str) {
		return str == null ? 0 : Double.parseDouble(str);
	}

	/**
	 * 将字符中的空格去掉,重新组合字符串
	 * @param str   待处理的字符
	 * @return   返回没出有空格的字符串
	 */
	public static String formatBlank(String str) {
		char s = ' ';
		String reStr = "";
		if (str != null && !str.trim().equals("")) {
			for (int i = 0; i < str.trim().length(); i++) {
				if (str.trim().charAt(i) != s) {
					reStr += str.trim().charAt(i);
				}
			}
		}

		return reStr;
	}

	/**
	 * 把一个String数组用","连接成一个字符串。<br>
	 * 例如传入new String[] { "aaa", "bbb", "ccc" }，则返回"aaa,bbb,ccc"。
	 * @param strings String数组
	 * @return 连接后的字符串
	 */
	public static String connect(Object[] strings) {
		if (strings.length < 1) {
			return "";
		}

		StringBuffer str = new StringBuffer();
		str.append(strings[0]);

		for (int i = 1; i < strings.length; i++) {
			str.append(",").append(strings[i]);
		}

		return str.toString();
	}

	/**
	 * 把一个String数组用","连接成一个字符串。<br>
	 * 例如传入new String[] { "aaa", "bbb", "ccc" }，则返回"aaa,bbb,ccc"。
	 * @param strings String数组
	 * @return 连接后的字符串
	 */
	public static String connectStr(Object[] strings) {
		if (strings.length < 1) {
			return "";
		}

		StringBuffer str = new StringBuffer();
		str.append("'").append(strings[0]);

		for (int i = 1; i < strings.length; i++) {

			str.append("','").append(strings[i]);
		}

		str.append("'");

		return str.toString();
	}

	/**
	 * 把一个List中存放的String数组的每一列列用","连接成一个字符串。<br>
	 * @param list List(String[])
	 * @return 连接后的字符串
	 */
	public static String connectList(List<?> list) {
		StringBuffer str = new StringBuffer();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {

				String[] string = (String[]) list.get(i);
				for (int j = 0; j < string.length; j++) {
					if (j == string.length - 1) {
						str.append(string[j]).append("|");
					} else {
						str.append(string[j]).append(",");
					}
				}
			}

			return str.toString().substring(0, str.length() - 1);
		} else
			return "";
	}

	/**
	 * 把一个List中存放的String数组的第一列用","连接成一个字符串。<br>
	 * @param list List(String[])
	 * @return 连接后的字符串
	 */
	public static String connect(List<?> list) {
		StringBuffer str = new StringBuffer();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {

				String[] string = (String[]) list.get(i);
				str.append(string[0]).append(",");

			}

			return str.toString().substring(0, str.length() - 1);

		} else
			return "";
	}

	/**
	 * 把一个List中存放的String用","连接成一个字符串。<br>
	 * @param list List(String)
	 * @return 连接后的字符串
	 */
	public static String connetListString(List<?> list) {
		StringBuffer str = new StringBuffer();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String string = (String) list.get(i);
				str.append(string).append(",");
			}
			return str.toString().substring(0, str.length() - 1);
		} else
			return "";
	}

	/**
	 * 重复字符规定的次数
	 * @param str 要重复的字符串
	 * @param repeat 重复的次数，必须大于0，为0时返回""。
	 * @return 返回重复后的字符串
	 */
	public static String repeat(String str, int repeat) {
		if (str == null)
			throw new NullPointerException("重复的字符串不能为null。");

		if (repeat < 0)
			throw new IllegalArgumentException("重复的次数(" + repeat + ")小于底限0。");

		StringBuffer s = new StringBuffer();
		for (int i = 0; i < repeat; i++) {
			s.append(str);
		}

		return s.toString();
	}

	/**
	 * 获得字符串的字节数
	 * @param s 字符串
	 * @return 字节数
	 */
	public static int bytes(String s) {
		try {
			return s.getBytes("ISO-8859-1").length;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("不支持的字符集");
		}
	}

	/**
	 * 将带有占位符的表达式和参数进行格式化<br>
	 * @param exp 表达式
	 * @param args 参数数组
	 * @return 格式化之后的字符串
	 * 例如:<br>
	 * exp="({0}+{1})*{2}" <br>
	 * args=["3", "7", "4"] <br>
	 * result="(3+7)*4"<br>
	 * @throws Exception
	 */
	public static String format(String exp, String[] args) {
		if (exp == null || args == null) {
			return exp;
		}
		for (int i = 0; i < args.length; i++) {
			args[i] = args[i] == null ? "" : args[i];
		}

		StringBuffer result = new StringBuffer(exp);
		int start = 0, end = 0, index = -1;
		int i = 0;
		while (i < result.length()) {
			char c = result.charAt(i);
			if (c == '{') {
				start = i;
			}
			if (c == '}') {
				end = i;
				try {
					index = Integer.parseInt(result.substring(start + 1, end));
				} catch (NumberFormatException e) {
					throw new NumberFormatException(format("非法占位符({0})",
							new String[] { result.substring(start + 1, end) }));
				}
				result.replace(start, end + 1, args[index]);
				i = start + args[index].length();
				continue;
			}
			if (i + 1 == result.length()) {
				if (start >= end) {
					throw new NumberFormatException(format("表达式错误: {0}", new String[] { "括号不匹配" }));
				}
			}
			i++;
		}
		return result.toString();
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0)
			return true;
		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(str.charAt(i)))
				return false;

		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
