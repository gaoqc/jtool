package tools.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

public class FileWriterTool
{
    public static void write(String text, String fileName, boolean appand)
    {
        
 
            PrintWriter out;
            try
            {
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName,appand),"utf-8")));
                out.append(text);
                out.flush();
                out.close();
            }
            catch (FileNotFoundException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (UnsupportedEncodingException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
 
        
        
//        
//        catch (FileNotFoundException e1)
//        {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//        java.io.FileWriter writer;
//        try
//        {
//            writer = new FileWriter(new File(fileName),appand);
//            writer.write(text);
//            writer.flush();
//            writer.close();
//        }
//        catch (IOException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }


    }
    public static void write(Collection<String>collect, String fileName, boolean appand){
        StringBuffer sb=new StringBuffer();
        for(String str:collect){
            sb.append(str+"\r\n");
        }
        write(sb.toString(),fileName,appand);
    }
    public static void main(String[] args)
    {
        write("高其成", "d:/test/aaa.log", true);
        
    }

}
