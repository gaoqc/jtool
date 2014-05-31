package tools.time;

public class TimeUtil
{
    public static void sleepSecods(int senconds){
        try
        {
            Thread.sleep(senconds*1000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
