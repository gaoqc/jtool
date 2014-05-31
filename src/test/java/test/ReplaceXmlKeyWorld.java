package test;

import java.io.File;
import java.util.List;
import tools.file.FileTool;
import tools.file.FileWriterTool;
import tools.file.SearchSuffixIndir;
import tools.print.TLogger;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class ReplaceXmlKeyWorld
{
    private static final TLogger logger = new TLogger(ReplaceXmlKeyWorld.class);

    public static void main(String[] args)
    {
        List<String> exceptFiles=Lists.newArrayList();
         exceptFiles.add("many_user_reg.xml");
        List<String> list = Lists.newArrayList();
        list.add("so_nbr");
        list.add("cust_id");
        list.add("region_code");
        list.add("user_id");
        list.add("phone_id");
        list.add("acct_id");
        list.add("valid_date");
        list.add("expire_date");
        list.add("out_product_id");
        list.add("group_id");
        list.add("main_phone_id");
        for (File file : SearchSuffixIndir.searchFiles(
                "E:/work/source/dev/infosystem_jslt/tools/ims-hbtest/src/main/resources/xml/webservice", "", "xml"))
        {
            if(exceptFiles.contains(file.getName())){
                logger.info("filter  file: ",file.getName());
                continue;
            }
            logger.info("filename: " + file.getName());
            List<String> lines = Lists.newArrayList();
            for (String line : FileTool.readFileLines(file.getAbsolutePath()))
            {

                lines.add(replace(line, list));
            }
            // logger.info(lines);
            FileWriterTool.write(lines, file.getAbsolutePath(), false);

        }
        logger.info("finish!!");
    }

    private static String replace(String line, List<String> list)
    {
        for (String s : list)
        {
            if (line.contains(s))
            {
                return new StringBuffer(getBlank(line,s)).append("<").append(s).append(">${").append(s.toUpperCase()).append("}</").append(s).append(">").toString();
            }
        }
        return line;
    }

    private static String getBlank(String line,String key)
    {
       return Strings.repeat(" ", line.indexOf(key)-1);
    }
}