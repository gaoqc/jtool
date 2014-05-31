/**
 *
 */
package tools.print;

import org.slf4j.Logger;
import tools.string.StringUtil;

/**
 * @Description:
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author gaoqc5
 * @Date 2013-10-31
 */
public class TLogger
{
    private String prefix = "====>";
    // private String suffix="<";
    private Logger logger;

    public TLogger(Class<?> clz, String prefix)
    {
        logger = org.slf4j.LoggerFactory.getLogger(clz);
        this.prefix = prefix;
    }

    public TLogger(Class<?> clz)
    {
        logger = org.slf4j.LoggerFactory.getLogger(clz);
    }

    /**
     * 打印debug级别日志
     * 
     * @param msgArr:多个日志信息
     */
    public void debug(long start, Object... msgArr)
    {

        logger.debug(concat(prefix, concat(msgArr),buildCostTime(start)));

    }

    public void debug(Object... msgArr)
    {

        logger.debug(concat(prefix, concat(msgArr)));

    }

    /**
     * 多个参数组成字符串
     */
    private static String concat(Object... args)
    {
        return StringUtil.concat(args);
    }
    private String buildCostTime(long start){
        StringBuffer sb=new StringBuffer(" ,cost time[ms]:");
        sb.append(System.currentTimeMillis()-start);
        return sb.toString();
    }

    /**
     * 打印info级别日志
     */
    public void info(Object... msgArr)
    {

        logger.info(concat(prefix, concat(msgArr)));

    }

    public void info(long start, Object... msgArr)
    {

        logger.info(concat(prefix, concat(msgArr),buildCostTime(start)));

    }

    public void trace(long start, Object... msgArr)
    {
        
        logger.trace(concat(msgArr)+buildCostTime(start));
    }

    public void trace(Object... msgArr)
    {
        logger.trace(concat(msgArr));
    }
    public void warn(Object ...msgArr){
        logger.warn(concat(msgArr));
    }
    public void warn(long start,Object ...msgArr){
        logger.warn(concat(msgArr)+buildCostTime(start));
    }
    
    

    /**
     * 打印error级别日志
     */
    public void error(Object... msgArr)
    {
        logger.error(concat(prefix, msgArr));
    }

}
