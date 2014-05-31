package tools.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class AddressUtil {

	private String user;
	private String pwd;
	private String host;
	private int port;
	private String subject;
	private String to;
	private String[] tos;
	private String from;
	private String text;
	private String encoding="GBK";
	private Map<String, String> fileMap;
	

	
	public AddressUtil(){}
	public AddressUtil(String from,String[] tos){
		this.from=from;
		this.tos=tos;
	}
	public void setSenderMsg(String sendUser,String sendPassword,String sendHost,int port){
		this.user=sendUser;
		this.pwd=sendPassword;
		this.host=sendHost;
		this.port=port;
		
	}
	public void setSendInfo(String subject,String text,Map<String, String> filePathMap){
		this.subject=subject;
		this.text=text;
		this.fileMap=filePathMap;
	}

 
	 public void setFileMap(Map<String, String> fileMap) {
		this.fileMap = fileMap;
	}
	 public Map<String, String> getFileMap() {
		return fileMap;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String[] getTos() {
		return tos;
	}
	public void setTos(String[] tos) {
		this.tos = tos;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
 /**

  * @param propertiesFilePath
  * @throws FileNotFoundException
  * @throws IOException
  */
	public AddressUtil(String propertiesFilePath) throws FileNotFoundException, IOException{
	    Properties properties=new Properties();
	    properties.load(new FileInputStream(new File(propertiesFilePath)));
	    this.encoding=properties.getProperty("mail.encoding");
	    this.from=properties.getProperty("mail.from");
	    this.host=properties.getProperty("mail.host");
	    this.pwd=properties.getProperty("mail.pwd");
	    this.user=properties.getProperty("mail.user");
	    this.port=parsePort(properties.getProperty("mail.port"));
	    this.tos=parseTo(properties.getProperty("mail.to"));
	  
	}
	private String[]parseTo(String tos){
	    if(tos.contains(",")){
	        return tos.split(",");
	    }else{
	        return tos.split(";");
	    }
	}
	private int parsePort(String port){
	    if(null==port||"".equals(port)){
	        return 25;
	    }
	    return Integer.parseInt(port);
	}
 
	
	
}
