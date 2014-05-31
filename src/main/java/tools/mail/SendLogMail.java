//package tools.mail;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Map;
//import org.apache.log4j.Logger;
//import com.tools.analyse.exception.ExceptionInfo;
//import com.tools.utils.Default;
//
//public class SendLogMail
//{
////   private  static final String mailProFilePath="src/main/resources/mail.properties";
//   private static final Logger logger=Logger.getLogger(SendLogMail.class);
//   
//   public static void send(String subject,String text,Map<String,String>fileMap){
//       AddressUtil addressUtil;
//       try
//       {  
//      
//        
//           logger.info("开始发送邮件");
//           addressUtil = new AddressUtil(Default.getMailProFilePath());
//           addressUtil.setSubject(subject);
//           addressUtil.setText(text);
//           if(null!=fileMap&&!fileMap.isEmpty()){
//               addressUtil.setFileMap(fileMap);
//           }
//           MailUtil.send(addressUtil);
//           logger.info("发送邮件完毕");
//       }
//       catch (FileNotFoundException e)
//       {
//           e.printStackTrace();
//           logger.error(e.toString());
//       }
//       catch (IOException e)
//       {
//           e.printStackTrace();
//           logger.error(e.toString());
//       }
//   }
//   public static void send(ExceptionInfo exceptionInfo,String log,Map<String,String>fileMap){
//       String subjecct=getMailSubject(exceptionInfo.getExceptionKeyword());
//       String text=getText(exceptionInfo,log);
//       send(subjecct,text,null);
//       
//       
//       
//   }
//   private static String  getMailSubject(String exceptionKeyword){
//       return exceptionKeyword+"异常日志";
//   }
//   
//
//   
//}
