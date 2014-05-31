package tools.print;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

 

/**
 * 
 *@Description:
 *@Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 *@Author gaoqc5
 *@Date 2013-10-27
 */
public class LogUtil
{

     public static void main(String[] args)
    {
    
    }
     
     private static void testList(){
//         List<Student> list=new ArrayList<Student>();
//         list.add(new Student("gaoqciehng",10,100));
//         list.add(new Student("111",10,100));
//
//         list.add(new Student("222",10,100));
//
//         list.add(new Student("333",10,100));
//         Gson gson=new Gson();
//         System.out.println(gson.toJson(list));

     }
     public static void testArray(){
         String[] names=new String[]{"111","222","aaa","bbb"};
         Gson gson=new Gson();
         System.out.println(gson.toJson(names));
     }


}
