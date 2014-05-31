package tools.pattern;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tools.date.DateUtil;
import tools.file.FileTool;
import tools.print.TLogger;
import com.google.common.collect.Maps;

public class PatternUtil
{
    private static final TLogger logger=new TLogger(PatternUtil.class);
    private static final String inputParamPrompt = "\\$\\{[^\\}]+\\}";// 输入正则表达式
    private static Pattern pattern = Pattern.compile(inputParamPrompt);

    public static String replace(String txt, Map<String, String> map)
    {

        Matcher matcher = pattern.matcher(txt);
        StringBuffer sb = new StringBuffer();
        while (matcher.find())
        {
            String oldKey = matcher.group();
            String val = map.get(getKey(oldKey));
            if(null==val){
                logger.info("params:\r\n"+map);
                throw new IllegalArgumentException("can't found the value of key["+oldKey+"]");
                
            }
            matcher.appendReplacement(sb, val);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 去掉标识符
     * 
     * @param oldKey
     * @return
     */
    private static String getKey(String oldKey)
    {
        return oldKey.replace("${", "").replace("}", "");
    }

    public static void main(String[] args)
    {

//        String xml = " <SESSIONID>${SESSIONID}</SESSIONID>";
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("SESSIONID", "1111");
//        System.out.println(replace(xml, map));
//        list.add("so_nbr");
//        list.add("cust_id");
//        list.add("region_code");
//        list.add("user_id");
//        list.add("phone_id");
//        list.add("acct_id");
//        list.add("valid_date");
//        list.add("expire_date");
        Map<String,String> map=Maps.newHashMap();
        map.put("so_nbr", "111");
        map.put("cust_id", "111");
        map.put("region_code", "25");
        map.put("user_id", "1234567890");
        map.put("phone_id", "1234567890");
        map.put("acct_id", "1234567890");
        map.put("valid_date",DateUtil.toString(DateUtil.nowDate(), null));
        map.put("expire_date", DateUtil.toString(DateUtil.nowDate(), null));
       String  xml= FileTool.readFileByBytes("E:/work/source/dev/infosystem_jslt/tools/ims-hbtest/src/main/resources/xml/webservice/1001/common_reg.xml");
       System.out.println(replace(xml, map));
        
        
    }

}
