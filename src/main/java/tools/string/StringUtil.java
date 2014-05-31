package tools.string;

public class StringUtil
{
    public static boolean isEmpty(Object obj){
        return null==obj||"".equals(obj.toString().trim());
    }
   public static boolean isNotEmpty(Object obj){
       return !isEmpty(obj);
   }
   public static String getFileEnter(){
       return "\r\n";
   }
   public static String getFileEnter(int count){
       StringBuffer sb=new StringBuffer();
       for(int i=0;i<count;i++){
           sb.append(getFileEnter());
       }
      return sb.toString();
   }
   
   /**
    * 多个参数组成字符串
    */
   public static String concat(Object... args)
   {
       int n = 0;
       for (Object s : args)
       {
           if (s == null)
           {
               continue;
           }
           n += s.toString().length();
       }
       StringBuilder sb = new StringBuilder(n);
       for (Object s : args)
       {
           if (s == null)
           {
               continue;
           }
           sb.append(s);
       }
       return sb.toString();
   }
   
    
   
 
   
}
