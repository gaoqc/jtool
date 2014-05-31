package test.print;

import org.junit.Test;
import tools.print.TLogger;

public class TLoggerTest
{
    private static final TLogger logger = new TLogger(TLoggerTest.class);

    @Test
    public void testDdbug()
    {
        logger.debug("test debug!!");
    }
    @Test
    public void testInfo(){
        logger.info("test  info!!!");
    }

}
