package tools.seq;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import tools.date.DateUtil;
import com.google.common.base.Strings;

/**
 * 序列产生器
 * 
 * @author gaoqc5
 */
public class SequenceUtils
{
    // private static final int bit=16;
    // private static SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
    private static Random random = new Random(10000);

    /**
     * 根据年月日时分秒+3位的16位的随机数
     * 
     * @return
     */
    public static String getSeqByTime()
    {

        return getDate() + getRandom();
    }

    /**
     * 产生一个以 startWith开头的bit 位的序列
     * 
     * @param startWith
     * @return
     */
    public static String getSeq(String startWith, int bit)
    {
        int extendLength = bit - startWith.length();
        String result = startWith;
        if (extendLength > 0)
        {
            String t = String.valueOf(System.currentTimeMillis());
            if (extendLength > t.length())
            {
                int count = extendLength % t.length();
                if (count > 0)
                {
                    t = Strings.repeat(t, ++count);
                }
                int l = extendLength / t.length();
                if (l > 0)
                {
                    t += Strings.padEnd(t, l, '0');
                }
            }
          
            result += new StringBuffer(t).reverse().substring(0, extendLength);

        }
        return result;
    }

    private static String getDate()
    {
        return DateUtil.toString(DateUtil.nowDate(), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS);
    }

    private static String getRandom()
    {
        return String.valueOf(random.nextInt(10000));
    }

    /**
     * test
     * 
     * @param args
     */
    public static void main(String[] args)
    {
   
       String s= SequenceUtils.getSeq("1300230",11);
       System.out.println(s.length());
        System.out.println(s);
        // System.out.println(Strings.padEnd("abc", 6, '0'));
    }
}
