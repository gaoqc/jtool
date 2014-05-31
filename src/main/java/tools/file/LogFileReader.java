package tools.file;

public interface LogFileReader
{
    public boolean isLogFileExists();
    
    public boolean isIgnoreBeforLog();
   
    
    public String  read() throws Exception;
    
    public void close();
    

}
