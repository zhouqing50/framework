/**
 * 
 */
package jt56.comm.system.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


public class Tools
{
    private static final String regutf = "[^\\f\\a\\v\\t\\r\\n\u0020-\u007E\u3400-\u4DB5\u4E00-\u9FA5\u9FA6-\u9FBB\uF900-\uFA2D\uFA30-\uFA6A\uFA70-\uFAD9\u20000-\u2A6D6\u2F800-\u2FA1D\uFF00-\uFFEF\u2E80-\u2EFF\u3000-\u303F\u31C0-\u31EF\u3040-\u309F\u30A0-\u30FF\u31F0-\u31FF\uAC00-\uD7AF\u1100-\u11FF\u3130-\u318F]";

    private static Logger log = Logger.getLogger(Tools.class);
    
    private static final Pattern patutf = Pattern.compile(regutf);

    /** 默认显示的记录数 */
    private static final int DEFAULT_PER_PAGENUM = 10;

    /** 10进制转换64进制字符集 */
    private final static char[] digits = { '0', '1', '2', '3', '4', '5', '6',
        '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
        'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
        'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '+',
        '/', };

    public static String sqlErrorForOra(int sqlErrorCode)
    {
        if (sqlErrorCode == 1)
            return "您要添加的记录已存在！";
        else if (sqlErrorCode == 2291)
            return "您要添加的记录父键找不到！";
        else if (sqlErrorCode == 2292)
            return "您要删除的记录是其它表的外键，不能删除！";
        else if (sqlErrorCode == 1438)
            return "值大于此列指定的允许精确度！";
        else if (sqlErrorCode == 1401)
            return "插入的值对于列过大！";
        else if (sqlErrorCode == 942)
            return "表或视图不存在！";

        return "内部错误！代码： " + sqlErrorCode;
    }

    /**
     * 
     * 此处插入方法说明。 创建日期：
     * 
     * @return java.lang.String
     * @param intString java.lang.String
     */
    public static String intToChinese(String intString)
    {

        if (Tools.isEmpty(intString))
        {
            return null;
        }

        // 先校验是否为有效的数字字符串
        if (String2Int(intString, -1) == -1)
        {
            return null;
        }

        String returnStr = "";
        for (int i = 0; i < intString.length(); i++)
        {

            String ss = intString.substring(i, i + 1);

            int j = new Integer(ss).intValue();

            switch (j)
            {
            case 0:
            {
                returnStr += "零";
                break;
            }
            case 1:
            {
                returnStr += "一";
                break;
            }
            case 2:
            {
                returnStr += "二";
                break;
            }
            case 3:
            {
                returnStr += "三";
                break;
            }
            case 4:
            {
                returnStr += "四";
                break;
            }
            case 5:
            {
                returnStr += "五";
                break;
            }
            case 6:
            {
                returnStr += "六";
                break;
            }
            case 7:
            {
                returnStr += "七";
                break;
            }
            case 8:
            {
                returnStr += "八";
                break;
            }
            case 9:
            {
                returnStr += "九";
                break;
            }

            }
        }
        return returnStr;

    }

    private static String getlineSeparator()
    {
        return "\r\n";
    }

    // 由Content-Type得到boundary
    public static String getBoundary(String ContentType)
    {
        StringBuilder sb = new StringBuilder("--");
        int index = ContentType.indexOf("boundary=");

        String tmp;
        if (index == -1)
        {
            return null;
        }
        else
        {
            tmp = ContentType.substring(index);
            int k = tmp.indexOf(";");
            if (k == -1)
            {
                k = tmp.indexOf(getlineSeparator());
                if (k == -1)
                {
                    sb.append(ContentType.substring(index + 9));
                    return sb.toString();
                }
            }
            sb.append(ContentType.substring(index + 9, (index + k)));
        }

        return sb.toString();
    }

    // 获得XML文档,
    protected static String subXMLString(String src)
        throws IllegalArgumentException
    {
        int part = src.indexOf("<?");
        if (part != -1)
        {
            src = src.substring(part);
        }
        else
        {
            throw new IllegalArgumentException("It's not a valid XML DOCUMENT!");
        }
        return src.trim();
    }

    // 将字符串str转换成InputStream
    @Deprecated
    public static InputStream transStream(String str)
    {
        return (new java.io.ByteArrayInputStream(str.getBytes()));
    }

    // 将输入流转换为字符串输出
    public static String toString(InputStream is)
    {

        StringBuilder str = new StringBuilder("");
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();

            while (line != null)
            {
                str = str.append(line + "\n");
                line = br.readLine();
            }
        }
        catch (IOException e)
        {
            return null;
        }
        finally
        {
            if (is != null)
            {
                try
                {
                    is.close();
                    is = null;
                }
                catch (Exception e2)
                {}
            }
            if (br != null)
            {
                try
                {
                    br.close();
                    br = null;
                }
                catch (Exception e2)
                {}
            }
        }
        return str.toString();

    }

    public static String getMD5(String src)
    {
        String md5 = "";
        byte[] rBytes = new byte[0];
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte bytes[] = src.getBytes("GBK");
            rBytes = md.digest(bytes);
            md5 = getHexs(rBytes);
        }
        catch (NoSuchAlgorithmException e)
        {}
        catch (UnsupportedEncodingException e)
        {}
        return md5;
    }

    private static String getHexs(byte[] bytes)
    {
        StringBuilder buff = new StringBuilder();
        for (int index = 0; index < bytes.length; index++)
        {
            buff.append(getHex(bytes[index]));
        }
        return buff.toString();
    }

    public static String getHex(byte b)
    {
        String hex = "";
        if (b > 0)
        {
            hex = Integer.toHexString(b);
        }
        else
        {
            hex = Integer.toHexString(b & 0xFF);
        }
        if (hex.length() == 1)
        {
            hex = "0" + hex;
        }
        return hex;
    }

    public static String urlEncoder(String value)
    {
        return urlEncoder(value, "GBK");
    }

    public static String urlEncoder(String src, String charset)
    {
        if (src == null)
        {
            return "";
        }
        String r = "";
        try
        {
            r = URLEncoder.encode(src, charset);
        }
        catch (UnsupportedEncodingException e)
        {
            r = src;
        }
        return r;
    }

    public static String strFormat(String pattern, Object params)
    {
        String msg = "";
        pattern = pattern.replaceAll("'", "''");
        MessageFormat msgFmt = new MessageFormat(pattern);
        msg = msgFmt.format(params);
        return msg;
    }

    /**
     * 判断输入参数据是否为空
     * 
     * @param param
     * @return
     */
    public static boolean isEmpty(String param)
    {
        if (param == null || ("").equals(param.trim()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 判断输入参数据是否为空
     * 
     * @param param
     * @return
     */
    public static boolean isNotEmpty(String param)
    {
        if (param == null || ("").equals(param.trim()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 把字符转换为整数，如果失败返回0
     */
    public static byte String2Byte(String param)
    {
        if (isEmpty(param))
        {
            log.error("Failed convert String to byte,param is null.");
            return 0;
        }
        try
        {
            return Byte.parseByte(param);
        }
        catch (Exception e)
        {
            log.error("Failed conver String to byte,param=" + param);
        }
        return 0;
    }

    /**
     * 把字符转换为整数，如果失败返回0
     */
    public static short String2Short(String param)
    {
        if (isEmpty(param))
        {
            log.error("Failed convert String to Short,param is null.");
            return 0;
        }
        try
        {
            return Short.parseShort(param);
        }
        catch (Exception e)
        {
            log.error("Failed conver String to Short,param=" + param);
        }
        return 0;
    }

    /**
     * 把字符转换为整数，如果失败返回0
     */
    public static short String2Short(String param, short defaultvalue)
    {
        if (isEmpty(param))
        {
            log.error("Failed convert String to Short,param is null.");
            return defaultvalue;
        }
        try
        {
            return Short.parseShort(param);
        }
        catch (Exception e)
        {
            log.error("Failed conver String to Short,param=" + param);
        }
        return defaultvalue;
    }

    /**
     * 把字符转换为整数，如果失败返回0
     */
    public static int String2Int(String param)
    {
        if (isEmpty(param))
        {
            log.error("Failed convert String to int,param is null.");
            return 0;
        }
        try
        {
            return Integer.parseInt(param);
        }
        catch (Exception e)
        {
            log.error("Failed conver String to int,param=" + param);
        }
        return 0;
    }

    /**
     * 把字符转换为整数，如果失败返回默认值
     */
    public static int String2Int(String param, int defaultvalue)
    {
        if (isEmpty(param))
        {
            log.error("Failed convert String to int,param is null.");
            return defaultvalue;
        }
        try
        {
            return Integer.parseInt(param);
        }
        catch (Exception e)
        {
            log.error("Failed conver String to int,param=" + param);
        }
        return defaultvalue;
    }

    /**
     * 把字符转换为长整数，如果失败返回0
     */
    public static long String2Long(String param)
    {
        if (isEmpty(param))
        {
            log.error("Failed convert String to Long,param is null.");
            return 0L;
        }
        try
        {
            return Long.parseLong(param);
        }
        catch (Exception e)
        {
            log.error("Failed conver String to Long,param=" + param);
        }
        return 0L;
    }

    /**
     * 把字符转换为长整数，如果失败返回默认值
     */
    public static long String2Long(String param, long defaultvalue)
    {
        if (isEmpty(param))
        {
            log.error("Failed convert String to Long,param is null.");
            return defaultvalue;
        }
        try
        {
            return Long.parseLong(param);
        }
        catch (Exception e)
        {
            log.error("Failed conver String to Long,param=" + param);
        }
        return defaultvalue;
    }

    /**
     * 对象转化为字符串
     * @param obj
     * @return
     */
    public static String object2String(Object obj)
    {
        return (obj == null) ? "" : obj.toString();
    }

    /**
     * 双目操作符，对于简单的IF，ESEL操作，可提高代码的可读性。
     * 
     * @param expr表达式
     * @param trueValue 当表达式为true时，返回的值
     * @param falseValue 当表达式为false时，返回的值
     * @return
     */
    public static String IF(boolean expr, String trueValue, String falseValue)
    {
        if (expr)
        {
            return trueValue;
        }
        else
        {
            return falseValue;
        }
    }

    /**
     * 双目操作符，对于简单的IF，ESEL操作，可提高代码的可读性。
     * 
     * @param expr表达式
     * @param trueValue 当表达式为true时，返回的值
     * @param falseValue 当表达式为false时，返回的值
     * @return
     */
    public static int IF(boolean expr, int trueValue, int falseValue)
    {
        if (expr)
        {
            return trueValue;
        }
        else
        {
            return falseValue;
        }
    }

    /**
     * 双目操作符，对于简单的IF，ESEL操作，可提高代码的可读性。
     * 
     * @param expr表达式
     * @param trueValue 当表达式为true时，返回的值
     * @param falseValue 当表达式为false时，返回的值
     * @return
     */
    public static long IF(boolean expr, long trueValue, long falseValue)
    {
        if (expr)
        {
            return trueValue;
        }
        else
        {
            return falseValue;
        }
    }

    /**
     * 双目操作符，对于简单的IF，ESEL操作，可提高代码的可读性。
     * 
     * @param expr表达式
     * @param trueObjRef 当表达式为true，返回的对象引用
     * @param falseObjRef 当表达式为false，返回的对象引用
     * @return
     */
    public static Object IF(boolean expr, Object trueObjRef, Object falseObjRef)
    {
        if (expr)
        {
            return trueObjRef;
        }
        else
        {
            return falseObjRef;
        }
    }

    /**
     * 双目操作符，对于简单的IF，ESEL操作，可提高代码的可读性。
     * 
     * @param expr表达式
     * @param trueObjRef 当表达式为true，返回的对象引用
     * @param falseObjRef 当表达式为false，返回的对象引用
     * @return
     */
    public static boolean IF(boolean expr, boolean trueObjRef,
        boolean falseObjRef)
    {
        if (expr)
        {
            return trueObjRef;
        }
        else
        {
            return falseObjRef;
        }
    }

    public static String htmlconvert(String source)
    {
        String target = null;
        if (source == null)
        {
            return null;
        }
        target = source.replaceAll("&amp;", "&");
        target = target.replaceAll("&", "&amp;");
        target = target.replaceAll("<", "&lt;");
        target = target.replaceAll(">", "&gt;");
        target = target.replaceAll("\"", "&quot;");
        return target;
    }

    public static String htmlconvertde(String source)
    {
        String target = null;
        if (source == null)
        {
            return null;
        }
        target = source.replaceAll("&amp;", "&");
        target = target.replaceAll("&lt;", "<");
        target = target.replaceAll("&gt;", ">");
        target = target.replaceAll("&quot;", "\"");
        return target;
    }

    /**
     * 防钓鱼函数
     * 
     * @param verStr :需要校验的字符串
     * @return:存在钓鱼串，则返回ture，不存在，则返回false
     */
    public static boolean isFishing(String verStr)
    {
        if (!isEmpty(verStr))
        {
            verStr = verStr.toLowerCase();
            if (verStr.indexOf("http:") > -1 || verStr.indexOf("ftp:") > -1
                || verStr.indexOf("mailto:") > -1)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * Checks if a String is whitespace, empty ("") or null.
     * </p>
     * 
     * <pre>
     * Tools.isBlank(null)      = true
     * Tools.isBlank(&quot;&quot;)        = true
     * Tools.isBlank(&quot; &quot;)       = true
     * Tools.isBlank(&quot;bob&quot;)     = false
     * Tools.isBlank(&quot;  bob  &quot;) = false
     * </pre>
     * 
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     */
    public static boolean isBlank(String str)
    {
        int strLen;
        if (str == null || (strLen = str.length()) == 0)
        {
            return true;
        }
        for (int i = 0; i < strLen; i++)
        {
            if ((Character.isWhitespace(str.charAt(i)) == false))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * <pre>
     * Tools.defaultString(null)  = &quot;&quot;
     * Tools.defaultString(&quot;&quot;)    = &quot;&quot;
     * Tools.defaultString(&quot;bat&quot;) = &quot;bat&quot;
     * </pre>
     */
    public static String defaultString(String str)
    {
        return str == null ? "" : str;
    }

    /**
     * @param str the String to check, may be null
     * @param defaultStr the default String to return if the input is
     *        <code>null</code> , may be null
     * @return the passed in String, or the default if it was <code>null</code>
     */
    public static String defaultString(String str, String defaultStr)
    {
        return str == null ? defaultStr : str;
    }

    /**
     * <pre>
     * Tools.defaultIfEmpty(null, &quot;NULL&quot;)  = &quot;NULL&quot;
     * Tools.defaultIfEmpty(&quot; &quot;, &quot;NULL&quot;)    = &quot;NULL&quot;
     * Tools.defaultIfEmpty(&quot;bat&quot;, &quot;NULL&quot;) = &quot;bat&quot;
     * Tools.defaultIfEmpty(&quot;&quot;, null)      = null
     * </pre>
     */
    public static String defaultIfEmpty(String str, String defaultStr)
    {
        return isEmpty(str) ? defaultStr : str;
    }

    /**
     * <pre>
     * Tools.trimToEmpty(null)          = &quot;&quot;
     * Tools.trimToEmpty(&quot;&quot;)            = &quot;&quot;
     * Tools.trimToEmpty(&quot;     &quot;)       = &quot;&quot;
     * Tools.trimToEmpty(&quot;abc&quot;)         = &quot;abc&quot;
     * Tools.trimToEmpty(&quot;    abc    &quot;) = &quot;abc&quot;
     * </pre>
     */
    public static String trimToEmpty(String str)
    {
        return str == null ? "" : str.trim();
    }

    /**
     * 把10进制的数字转换成64进制
     * 
     * @param number 需转换的10进制数
     * @param shift 转换结果位数,调用时,实参写6
     * @return 返回一个6位的64进制数.
     */
    public static String CompressNumber(long number, int shift)
    {
        char[] buf = new char[64];
        int charPos = 64;
        int radix = 1 << shift;
        long mask = radix - 1;
        do
        {
            buf[--charPos] = digits[(int) (number & mask)];
            number >>>= shift;
        }
        while (number != 0);
        return new String(buf, charPos, (64 - charPos));
    }

    /**
     * 把64进制的字符串转换成10进制
     * 
     * @param decompStr 待转换的64进制数.
     * @return 转换后的10进制数.
     */
    public static long UnCompressNumber(String decompStr)
    {
        long result = 0;
        for (int i = decompStr.length() - 1; i >= 0; i--)
        {
            if (i == decompStr.length() - 1)
            {
                result += getCharIndexNum(decompStr.charAt(i));
                continue;
            }
            for (int j = 0; j < digits.length; j++)
            {
                if (decompStr.charAt(i) == digits[j])
                {
                    result += ((long) j) << 6 * (decompStr.length() - 1 - i);
                }
            }
        }
        return result;
    }

    /**
     * 取得字符的ASCII码的数字码.
     * 
     * @param ch ASCII码.
     * @return ASCII数字码
     */
    private static long getCharIndexNum(char ch)
    {
        int num = ((int) ch);
        if (num >= 48 && num <= 57)
        {
            return num - 48;
        }
        else if (num >= 97 && num <= 122)
        {
            return num - 87;
        }
        else if (num >= 65 && num <= 90)
        {
            return num - 29;
        }
        else if (num == 43)
        {
            return 62;
        }
        else if (num == 47)
        {
            return 63;
        }
        return 0;
    }

   

    /**
     * 获得查询数据库的起始记录、结束记录、总页码 如记录总数为100，查询第5页，每页显示记录为10，则起始记录为51结束记录为60，总页码为10
     * 
     * @param toPage 跳转页
     * @param perPageNum 每页显示记录数
     * @param totalRecordCount 记录总数
     * 
     * @return 返回起始记录数和结束记录数 如 [51,60,10]
     */
    public static int[] getPagerStartEnd(int toPage, int perPageNum,
        int totalRecordCount)
    {
        if (totalRecordCount < 1)
        {
            return null;
        }

        if (toPage < 1)
        {
            toPage = 1;
        }

        if (perPageNum < 1)
        {
            perPageNum = DEFAULT_PER_PAGENUM;
        }

        // 计算总页码
        int totalPages = (int) Math.ceil((totalRecordCount + perPageNum - 1)
            / perPageNum);
        if (toPage > totalPages)
        {
            toPage = totalPages;
        }

        // 计算起始记录与结束记录数
        int start = (toPage - 1) * perPageNum + 1;
        int end = start + perPageNum - 1;
        if (end > totalRecordCount)
        {
            end = totalRecordCount;
        }

        return new int[] { start, end, totalPages };
    }

    public static boolean isRightUTF(String tmpstr)
    {
        if (Tools.isEmpty(tmpstr))
        {
            return true;
        }
        boolean result = true;
        if (patutf.matcher(tmpstr).find())
        {
            result = false;
        }
        return result;
    }

    /**
     * 检查是不是utf字符。 utf字符的特点是其中一个char数组应该大于255 只要有一个大于255,即认为是utf
     */
    public static boolean checkIsUTF(String str)
    {
        char[] charstr = str.toCharArray();
        for (int i = 0; i < charstr.length; i++)
        {
            if (charstr[i] > 255)
            {
                charstr = null;
                return true;
            }
        }
        charstr = null;
        return false;
    }

    /**
     * 检查给定的data中指定的位（从低位到高位，0开始）是否是1
     */
    public static boolean checkOneBit(byte data, int position)
    {
        if (position > 7 || position < 0)
        {
            return false;
        }
        int target = (int) Math.pow(2, position);
        return (data & target) == target;
    }

    /**
     * 为data数据指定的位设置为1或者0 setopen为true时，指定的位置(position)设置为1
     * setopen为false时，指定的位置(position)设置为0 这个函数不检查原始位置的值。
     */
    public static byte setOneBit(byte data, int position, boolean setopen)
    {
        if (position > 7 || position < 0)
        {
            return data;
        }
        int target = (int) Math.pow(2, position);
        byte result = data;
        boolean posopened = (data & target) == target;
        if (setopen)
        {
            if (!posopened)
            {
                result += target;
            }
        }
        else
        {
            if (posopened)
            {
                result -= target;
            }
        }
        return result;
    }

    /**
     * 判断邮件地址
     * 
     * @param email
     * @return
     */
    public static boolean checkEmail(String email)
    {
        boolean isemail = false;
        if (email.indexOf("<") > -1 && email.indexOf(">") > -1)
        {
            int start = email.indexOf("<");
            int end = email.indexOf(">");
            email = email.substring(start + 1, end);
        }
        Pattern patt3 = Pattern
            .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher mat3 = patt3.matcher(email);
        isemail = mat3.matches();
        return isemail;
    }

    public static String fixzero(int v)
    {
        if (v < 10)
        {
            return "0" + v;
        }
        else
        {
            return "" + v;
        }
    }

    public static String unicode2chinese(String str)
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            str = str.replaceAll(" ", "");
            str = str.replaceAll("　", "");
            int pos = 0;
            while (str.length() > 0)
            {
                if (str.startsWith("&#"))
                {
                    pos = str.indexOf(";");
                    sb.append((char) Integer.parseInt(str.substring(2, pos)));
                    str = str.substring(pos + 1);
                }
                else
                {
                    sb.append(str.charAt(0));
                    str = str.substring(1);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static byte[] getUTFBytes(String str)
    {
        if (isEmpty(str))
        {
            return null;
        }

        try
        {
            return str.getBytes("utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String unescape(String src)
    {

        StringBuilder tmp = new StringBuilder();

        tmp.ensureCapacity(src.length());

        int lastPos = 0, pos = 0;

        char ch;

        while (lastPos < src.length())
        {

            pos = src.indexOf("%", lastPos);

            if (pos == lastPos)
            {

                if (src.charAt(pos + 1) == 'u')
                {

                    ch = (char) Integer.parseInt(src

                    .substring(pos + 2, pos + 6), 16);

                    tmp.append(ch);

                    lastPos = pos + 6;

                }
                else
                {

                    ch = (char) Integer.parseInt(src

                    .substring(pos + 1, pos + 3), 16);

                    tmp.append(ch);

                    lastPos = pos + 3;

                }

            }
            else
            {

                if (pos == -1)
                {

                    tmp.append(src.substring(lastPos));

                    lastPos = src.length();

                }
                else
                {

                    tmp.append(src.substring(lastPos, pos));

                    lastPos = pos;

                }

            }

        }

        return tmp.toString();

    }

    /**
     * 把字符转换为整数，如果失败返回默认值
     */
    public static float String2Float(String param, float defaultvalue)
    {
        try
        {
            return Float.valueOf(param);
        }
        catch (Exception e)
        {}
        return defaultvalue;
    }
   
    /**
     * 特殊字符转义
     * @return
     * @date:2012-2-16
     */
    public static String convertSpChar(String s){
        return (s==null)?"":s.replace("\"","\\\"");
    }
    
    /**
     * HAMC-MD5 加密
     * @param key 密钥
     * @param src 待加密的字符串
     * @return 加密后的字符串
     */
    public static String getHamcMD5(String key,String src){
    	String hmd5 = "";
    	byte[] hmd5s = new byte[0];
    	try {
    		byte[] keyBytes = key.getBytes("GBK");
        	byte[] dataBytes = src.getBytes("GBK");
        	hmd5s = getHmacMd5Bytes(keyBytes, dataBytes);
        	hmd5 = getHexs(hmd5s);
		} catch (Exception e) {
		}
    	return hmd5;
    }
    
    /**
    * 计算参数的md5信息
    * @param str 待处理的字节数组
    * @return md5摘要信息
    * @throws NoSuchAlgorithmException
    */
    private static byte[] md5(byte[] str) 
    throws NoSuchAlgorithmException
    {
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(str);
	    return md.digest();
    }

    /**
    * 将待加密数据data，通过密钥key，使用hmac-md5算法进行加密，然后返回加密结果。
    * 参照rfc2104 HMAC算法介绍实现。
    * @param key 密钥
    * @param data 待加密数据
    * @return 加密结果
    * @throws NoSuchAlgorithmException
    */
    public static byte[] getHmacMd5Bytes(byte[] key,byte[] data) throws NoSuchAlgorithmException
    {
	    /* HmacMd5 calculation formula: H(K XOR opad, H(K XOR ipad, text))
	    * HmacMd5 计算公式：H(K XOR opad, H(K XOR ipad, text))
	    * H代表hash算法，本类中使用MD5算法，K代表密钥，text代表要加密的数据
	    * ipad为0x36，opad为0x5C。
	    */
	    int length = 64;
	    byte[] ipad = new byte[length];
	    byte[] opad = new byte[length];
	    for(int i = 0; i < 64; i++)
	    {
	    ipad[i] = 0x36;
	    opad[i] = 0x5C;
	    }
	    byte[] actualKey = key; //Actual key.
	    byte[] keyArr = new byte[length]; //Key bytes of 64 bytes length
	    /*If key's length is longer than 64,then use hash to digest it and use the result as actual key.
	    * 如果密钥长度，大于64字节，就使用哈希算法，计算其摘要，作为真正的密钥。
	    */
	    if(key.length>length)
	    {
	    actualKey = md5(key);
	    }
	    for(int i = 0; i < actualKey.length; i++)
	    {
	    keyArr[i] = actualKey[i];
	    }
	
	    /*append zeros to K
	    * 如果密钥长度不足64字节，就使用0x00补齐到64字节。
	    */
	    if(actualKey.length < length)
	    {
	    for(int i = actualKey.length; i < keyArr.length; i++)
	    keyArr[i] = 0x00;
	    }
	
	    /*calc K XOR ipad
	    * 使用密钥和ipad进行异或运算。
	    */
	    byte[] kIpadXorResult = new byte[length];
	    for(int i = 0; i < length; i++)
	    {
	    kIpadXorResult[i] = (byte) (keyArr[i] ^ ipad[i]);
	    }
	
	    /*append "text" to the end of "K XOR ipad"
	    * 将待加密数据追加到K XOR ipad计算结果后面。
	    */
	    byte[] firstAppendResult = new byte[kIpadXorResult.length+data.length];
	    for(int i=0;i<kIpadXorResult.length;i++)
	    {
	    firstAppendResult[i] = kIpadXorResult[i];
	    }
	    for(int i=0;i<data.length;i++)
	    {
	    firstAppendResult[i+keyArr.length] = data[i];
	    }
	
	    /*calc H(K XOR ipad, text)
	    * 使用哈希算法计算上面结果的摘要。
	    */
	    byte[] firstHashResult = md5(firstAppendResult);
	
	    /*calc K XOR opad
	    * 使用密钥和opad进行异或运算。
	    */
	    byte[] kOpadXorResult = new byte[length];
	    for(int i = 0; i < length; i++)
	    {
	    kOpadXorResult[i] = (byte) (keyArr[i] ^ opad[i]);
	    }
	
	    /*append "H(K XOR ipad, text)" to the end of "K XOR opad"
	    * 将H(K XOR ipad, text)结果追加到K XOR opad结果后面
	    */ 
	    byte[] secondAppendResult = new byte[kOpadXorResult.length+firstHashResult.length];
	    for(int i=0;i<kOpadXorResult.length;i++)
	    {
	    secondAppendResult[i] = kOpadXorResult[i];
	    }
	    for(int i=0;i<firstHashResult.length;i++)
	    {
	    secondAppendResult[i+keyArr.length] = firstHashResult[i];
	    }
	
	    /*H(K XOR opad, H(K XOR ipad, text))
	    * 对上面的数据进行哈希运算。
	    */
	    byte[] hmacMd5Bytes = md5(secondAppendResult);
	
	    return hmacMd5Bytes;
	
    }
    
    /**
     * 计算显示大小
     * @param sizef
     * @return
     */
    public String getFileSize(double sizef)
    {

        String sizestr = "";
        if (sizef > 0)
        {
            DecimalFormat df = new DecimalFormat("#0.00");
            sizestr = df.format(sizef) + "B";
            if (sizef > 1024)
            {
                sizef = sizef / 1024;
                sizestr = df.format(sizef) + "K";
            }
            if (sizef > 1024)
            {
                sizef = sizef / 1024;
                sizestr = df.format(sizef) + "M";
            }
            if (sizef > 1024)
            {
                sizef = sizef / 1024;
                sizestr = df.format(sizef) + "G";
            }
        }
        return sizestr;
    }

    public static void main(String[] args) {
        String key = "G03YQYYDjs2el2u1w5Tt0pOBHrOOpZGd";
        String data = "588705_0002";
        String hmd5 = getHamcMD5(key,data);
        System.out.println(hmd5);
        //Date date = new Date(1334073599000l);
        
    }

}
