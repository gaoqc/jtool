/**
 *
 */
package tools.print;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;


/**
 * @Description: 在TLogger 的基础上增加是dump功能
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author gaoqc5
 * @Date 2013-11-3
 */
public class TLoggerEx extends TLogger
{

    public void dump(Object obj)
    {

    }

    /**
     * @param clz
     */
    public TLoggerEx(Class clz)
    {
        super(clz);
    }

    public String dumpXml(Object obj)
    {
        dump(obj);
        XStream xstream = new XStream();
        return xstream.toXML(obj);

    }

    public String dumpJson(Object obj)
    {
        dump(obj);
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.setMode(XStream.NO_REFERENCES);
        return xStream.toXML(obj);
    }

}
