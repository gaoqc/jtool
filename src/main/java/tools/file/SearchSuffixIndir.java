package tools.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchSuffixIndir
{

    /**
     * 将给定目录下的及子目录下的 所有的给定后缀名的文件路径打印到一个文件中。 深度遍历该父目录，将符合条件的文件路径存到集合中。（进行过滤）。 再将集合写到文件中。
     */
    public static void main(String[] args)
    {

       String dir="E:/work/source/tag/openbilling60_20130516/infosystem64/ims-core/config";
       int size=0;
       for(File file:searchFiles(dir,null,".xml")){
           System.out.println(file.getAbsolutePath());
           size++;
       }
          System.out.println("total size:"+size);
          
 
    }
    
    public static  List<File> searchFiles(String searchDir,final String prefix,final String suffix ){
        File dir = new File(searchDir);
        FilenameFilter filenameFilter = new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                if(notEmpty(prefix)&&notEmpty(suffix)){
                    return name.startsWith(prefix)&&name.endsWith(suffix);
                }else if(notEmpty(prefix)){
                return name.startsWith(prefix);
                }
                    return name.endsWith(suffix);
                
            }
        };
        ArrayList<File> list = new ArrayList<File>();

        writeToList(dir, filenameFilter, list); 
        return list;
    }
    private static boolean notEmpty(String str){
        return null!=str&&!str.trim().equals("");
    }
    

    private static void writeTofile(ArrayList<File> al, File file)
    {
        BufferedWriter bw = null;
        try
        {
            bw = new BufferedWriter(new FileWriter(file));

            for (File f : al)
            {
                new String();
                // bw.write(String.valueOf(f.length()));
                bw.write(f.getAbsolutePath());
                bw.newLine();
                bw.flush();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("写入失败！");
        }
        finally
        {
            if (bw != null)
                try
                {
                    bw.close();
                }
                catch (IOException e)
                {
                    throw new RuntimeException("关闭失败！");
                }
        }

    }

    private static void writeToList(File dir, FilenameFilter searchSuffix, ArrayList<File> al)
    {

        File[] files = dir.listFiles();
        for (File f : files)
        {
            if (f.isDirectory())
            {
                // 递归了。
                writeToList(f, searchSuffix, al);
            }
            else
            {
                if (searchSuffix.accept(dir, f.getName()))
                {
                    al.add(f);
                }
            }
        }
    }

}
