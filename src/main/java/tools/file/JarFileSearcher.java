/**
 *
 */
package tools.file;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @Description:在jar 中查出某个类文件或资源文件
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author gaoqc5
 * @Date 2013-10-14
 */
public class JarFileSearcher
{

    public static void main(String[] args) throws IOException
    {
        searchInDri("C:/Users/gaoqc5/Desktop/ftp/", "spring-mvc-3.0.xsd");
    }

    /**
     * @Description:在指定某个jar 下面查找类文件或者资源文件
     * @author gaoqc5 2013-10-14
     * @param jarFileName
     * @param fileName
     * @throws IOException
     * @return void
     */
    public static void searchInJar(String jarFileName, String fileName) throws IOException
    {

        String filename = jarFileName;
        if (filename.endsWith(".jar") || filename.endsWith(".zip"))
        {
            ZipFile zip = new ZipFile(filename);
            Enumeration entries = zip.entries();
            while (entries.hasMoreElements())
            {
                ZipEntry entry = (ZipEntry) entries.nextElement();

                if (isEqual(getName(entry), fileName))
                {
                    System.out.println("found fileName [" + entry.getName() + "] at  " + filename);
                }
            }
        }

    }

    /**
     * @Description:在指定目录下查找类文件或资源文件
     * @author gaoqc5 2013-10-14
     * @param dir
     * @param fileName
     * @throws IOException
     * @return void
     */
    public static void searchInDri(String dir, String fileName) throws IOException
    {

        File d = new File(dir);
        if (!d.isDirectory())
        {
            return;
        }
        File[] files = d.listFiles();

        for (int i = 0; i < files.length; i++)
        {
            if (files[i].isDirectory())
            {
                searchInDri(files[i].getAbsolutePath(), fileName);
            }
            else
            {
//                System.out.println("begin to search jar: " + files[i].getName());
                searchInJar(files[i].getAbsolutePath(), fileName);
            }
        }

    }

    private static boolean isEqual(String jarFileFullName, String fileName)
    {
        if (jarFileFullName.endsWith(".class"))
        {
            return isEqualClassName(jarFileFullName, fileName);
        }
        else
        {
            return isEqualResourceName(jarFileFullName, fileName);
        }
    }

    /**
     * @Description:类文件只需要匹配类名即可
     * @author gaoqc5 2013-10-14
     * @param jarFileFullName
     * @param fileName
     * @return
     * @return boolean
     */
    private static boolean isEqualClassName(String jarFileFullName, String fileName)
    {
        return jarFileFullName.replace(".class", "").endsWith(fileName);
    }

    /**
     * @Description:资源文件需要全名匹配
     * @author gaoqc5 2013-10-14
     * @param jarFileFullName
     * @param fileName
     * @return
     * @return boolean
     */
    private static boolean isEqualResourceName(String jarFileFullName, String fileName)
    {
//        return jarFileFullName.equals(fileName);
        return jarFileFullName.contains(fileName);
    }

    private static String getName(ZipEntry entry)
    {
        StringBuffer className = new StringBuffer(entry.getName().replace('/', '.'));
        return className.toString();
    }

}
