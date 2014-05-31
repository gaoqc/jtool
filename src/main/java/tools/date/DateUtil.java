package tools.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import tools.print.TLogger;

/**
 * @Description: 定义日期相关的公用方法
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-2
 * @Date 2012-10-22 zengxr cache the format template and default expire date
 */
public class DateUtil
{
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS_ORACLE = "yyyymmddHH24miss";
    public static final String DATE_FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmm";
    public static final String DATE_FORMAT_YYYYMMDDHH = "yyyyMMddHH";
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_FORMAT_YYYYMM = "yyyyMM";
    public static final String DATE_FORMAT_YYYY = "yyyy";
    public static final String DATE_FORMAT_EN_A_YYYYMMDDHHMMSS = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT_EN_A_YYYYMMDDHHMM = "yyyy/MM/dd HH:mm";
    public static final String DATE_FORMAT_EN_A_YYYYMMDDHH = "yyyy/MM/dd HH";
    public static final String DATE_FORMAT_EN_A_YYYYMMDD = "yyyy/MM/dd";
    public static final String DATE_FORMAT_EN_A_YYYYMM = "yyyy/MM";
    public static final String DATE_FORMAT_EN_A_YYYY = "yyyy";
    public static final String DATE_FORMAT_EN_B_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_EN_B_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_EN_B_YYYYMMDDHH = "yyyy-MM-dd HH";
    public static final String DATE_FORMAT_EN_B_YYYYMMDD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_EN_B_YYYYMM = "yyyy-MM";
    public static final String DATE_FORMAT_EN_B_YYYY = "yyyy";
    public static final String DATE_FORMAT_CN_YYYYMMDDHHMMSS = "yyyy'年'MM'月'dd'日' HH'时'mm'分'ss'秒'";
    public static final String DATE_FORMAT_CN_YYYYMMDDHHMM = "yyyy'年'MM'月'dd'日' HH'时'mm'分'";
    public static final String DATE_FORMAT_CN_YYYYMMDDHH = "yyyy'年'MM'月'dd'日' HH'时'";
    public static final String DATE_FORMAT_CN_YYYYMMDD = "yyyy'年'MM'月'dd'日'";
    public static final String DATE_FORMAT_CN_YYYYMM = "yyyy'年'MM'月'";
    public static final String DATE_FORMAT_CN_YYYY = "yyyy'年'";
    private static TLogger imsLogger = new TLogger(DateUtil.class);

    private static final Map<String, ThreadLocal<SimpleDateFormat>> innerMap = new ConcurrentHashMap<String, ThreadLocal<SimpleDateFormat>>();

    /**
     * 不推荐原因:方法名太长 2008-09-13 zengxr 增加获取当前时间的方法，统一使用该接口方便以后转换实现方式
     * 
     * @return // * @deprecated use nowDate() public static Date currentDate() { return nowDate(); }
     */

    public static Date nowDate()
    {
        return new Date();
    }

    /**
     * 不推荐原因:方法名含糊
     * 
     * @param date
     * @param format
     * @return //* @deprecated use toString public static String formatDate(Date date, String format) { return toString(date,
     *         format); }
     */

    /**
     * 不推荐原因:方法名功能含糊不清 根据日期模式，把字符串型得日期转换程JAVA的日期
     * 
     * @param dateStr 日期
     * @param format 模式
     * @return java型的日期 //* @deprecated use toDate public static Date getFormatDate(String dateStr, String format) throws
     *         IMSException { return toDate(dateStr, format); }
     */

    /**
     * 默认使用比较常用的YYYYMMDDHHMMSS模式来转换日期
     * 
     * @return
     * @throws IMSException 2012-10-22 zengxr cache the format template and default expire date // * @deprecated use toString
     *             public static String getFormatDate(Date date, String formatStr) throws IMSException { return toString(date,
     *             formatStr); }
     */
    /**
     * @param date
     * @param formatStr 可以为空，如果为空，则使用默认的格式:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String toString(Date date, String formatStr)
    {

        try
        {
            if (date == null)
            {
                return "";
            }

            // 为空选择默认的格式
            if (null == formatStr || "".equals(formatStr.trim()))
            {
                formatStr = DATE_FORMAT_EN_B_YYYYMMDDHHMMSS;
            }

            return getCacheDateFormatTpl(formatStr).format(date);
        }
        catch (Exception ex)
        {
            imsLogger.error(ex, ex);

        }
        return "";

    }

    /**
     * 从缓存中取 SimpleDateFormat
     * 
     * @param formatStr
     * @return
     */
    private static SimpleDateFormat getCacheDateFormatTpl(final String formatStr)
    {
        ThreadLocal<SimpleDateFormat> sdfThreadLocal = innerMap.get(formatStr);
        if (sdfThreadLocal == null)
        {

            imsLogger.info("begin to create new ThreadLocal for pattern:", formatStr);
            sdfThreadLocal = new ThreadLocal<SimpleDateFormat>()
            {
                @Override
                protected SimpleDateFormat initialValue()
                {

                    return new SimpleDateFormat(formatStr);
                }
            };

            innerMap.put(formatStr, sdfThreadLocal);

        }
        return sdfThreadLocal.get();
    }

    public static Date toDate(String dateStr)
    {
        return toDate(dateStr, null);
    }

    /**
     * @param dateStr
     * @param format 可为空，如果为空，则根据 dateStr格式字段判断
     * @return
     */
    public static Date toDate(String dateStr, String format)
    {

        if (null == format || "".equals(format.trim()))
        {
            format = parseDateformatTpl(dateStr);
        }
        try
        {
            return getCacheDateFormatTpl(format).parse(dateStr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            imsLogger.error(e);

        }
        return null;

    }

    /**
     * @param date ex. 20140501142530
     * @return ex. 2014-05-01 14:25:30
     * @throws IMSException
     */
    public static String toDate(long date)
    {
        Date d = toDate(String.valueOf(date), DATE_FORMAT_YYYYMMDDHHMMSS);
        return toString(d, DATE_FORMAT_EN_B_YYYYMMDDHHMMSS);

    }

    private static String parseDateformatTpl(String strDate)
    {
        String formatStr = "yyyyMMdd";
        if (strDate == null || strDate.trim().equals(""))
        {
            return null;
        }
        switch (strDate.trim().length())
        {
        case 6:
            if (strDate.substring(0, 1).equals("0"))
            {
                formatStr = "yyMMdd";
            }
            else
            {
                formatStr = "yyyyMM";
            }
            break;
        case 8:
            formatStr = "yyyyMMdd";
            break;
        case 10:
            if (strDate.indexOf("-") == -1)
            {
                formatStr = "yyyy/MM/dd";
            }
            else
            {
                formatStr = "yyyy-MM-dd";
            }
            break;
        case 11:
            if (strDate.getBytes().length == 14)
            {
                formatStr = "yyyy年MM月dd日";
            }
            else
            {
                return null;
            }
        case 14:
            formatStr = "yyyyMMddHHmmss";
            break;
        case 19:
            if (strDate.indexOf("-") == -1)
            {
                formatStr = "yyyy/MM/dd HH:mm:ss";
            }
            else
            {
                formatStr = "yyyy-MM-dd HH:mm:ss";
            }
            break;
        default:
            throw new IllegalArgumentException("invalid date format : " + strDate);
        }
        return formatStr;
    }

    /**
     * 不推荐原因：方法名表达含义不明确 默认使用比较常用的YYYYMMDDHHMMSS模式来转换日期
     * 
     * @param dateStr
     * @return
     * @throws IMSException //* @deprecated use toDate public static Date getFormatDate(String dateStr) throws IMSException {
     *             return toDate(dateStr, DATE_FORMAT_YYYYMMDDHHMMSS); }
     */
    /**
     * 不推荐原因：方法名表达含义不明确 对输入的日期字符串进行格式化.
     * 
     * @param strDate 需要进行格式化的日期，格式为前面定义的 DATE_FORMAT_YYYYMMDDHHMMSS
     * @return 经过格式化后的字符串
     * @throws IMSException // * @deprecated use toDate public static Date getFormattedDate(String strDate) throws IMSException {
     *             return toDate(strDate, null); }
     */
    /**
     * 不推荐原因：方法名表达含义不明确,有复合操作 对输入的日期字符串进行格式化.
     * 
     * @param strDate 需要进行格式化的日期，格式为前面定义的 DATE_FORMAT_YYYYMMDDHHMMSS
     * @param strFormatTo 指定采用何种格式进行格式化操作
     * @return 经过格式化后的字符串 // * @deprecated public static String getFormattedDate(String strDate, String strFormatTo) { Date d =
     *         toDate(strDate, null); return toString(d, strFormatTo); }
     */

    public static Date toLastDateAtMonth(Date date)
    {
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        // time.getMaximum(field)
        time.set(Calendar.DATE, time.getActualMaximum(Calendar.DAY_OF_MONTH));
        return time.getTime();
    }

    /**
     * @Description:判断是否是制定月末最后一天
     * @author gaoqc5 2013-12-21
     * @param date
     * @return
     * @return boolean
     */
    public static boolean isLastDayOfMonth(Date date)
    {

        Date lastMothDay = toLastDateAtMonth(date);
        return getBetweenDays(lastMothDay, date) == 0;

    }

    public static Date checkForNull(Date checked, Date instead)
    {
        if (checked == null)
        {
            return instead;
        }
        return checked;
    }

    /**
     * 日期转为秒数(日期的毫秒数除1000)
     */
    private static int toSecond(Date date)
    {
        if (date == null)
        {
            return 0;
        }
        long second = date.getTime() / 1000;
        return (second > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) second);
    }

    /**
     * 日期转为秒数(日期的毫秒数除1000)
     */
    public static int toSecond(String dateStr)
    {
        Date date = toDate(dateStr, null);
        return toSecond(date);

    }

    public static Date UTCToDate(long utc)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(utc * 1000);
        return calendar.getTime();
    }

    public static String UTCToString(long utc)
    {
        return toString(UTCToDate(utc), DATE_FORMAT_EN_B_YYYYMMDDHHMMSS);
    }

    /**
     * @param d1
     * @param d2
     * @return 计算两日期的间隔天数,统一返回正值
     */
    public static Long getBetweenDays(Date d1, Date d2)
    {
        long diff = d1.getTime() - d2.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        return Math.abs(days);
    }

    /**
     * 计算两日期的大小
     * 
     * @param startDate
     * @param endDate
     * @return 如果startDate 比endDate 小，返回true，否则返回false
     */
    public static boolean compare2Day(Date startDate, Date endDate)
    {
        return startDate.before(endDate);
    }

    /**
     * 时分表设置为0<BR>
     * wangjt 2011-10-10
     */
    public static Date dayBegin(Date date)
    {
        throw new IllegalArgumentException("not impl");
        // return jef.tools.DateUtils.dayBegin(date);

    }

    /**
     * 时分秒设置为23:59:59 caohm5 2012-05-28
     */
    public static Date dayEnd(Date date)
    {
        throw new IllegalArgumentException("not impl");

    }

    /**
     * date转timestamp luojb 2012-3-26
     * 
     * @param date
     * @return
     */
    public static Timestamp toTimestamp(Date date)
    {
        return date == null ? null : new Timestamp(date.getTime());
    }

    /**
     * 获取两个时间之间的秒数差
     * 
     * @param d1
     * @param d2
     * @return 返回正值
     */
    public static long getBetweenSeconds(Date d1, Date d2)
    {
        long startSec = d1.getTime() / 1000;
        long endSec = d2.getTime() / 1000;
        return Math.abs(startSec - endSec);

    }

    /**
     * 把Date转成mdb要的秒数
     * 
     * @author wuyj 2013-3-29
     * @param validDate
     * @return
     */
    public static int date2UTC(Date validDate)
    {
        long mdbDate = validDate.getTime() / 1000;
        mdbDate = mdbDate > 0 ? mdbDate : 0;
        return (mdbDate > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) mdbDate);
    }

}
