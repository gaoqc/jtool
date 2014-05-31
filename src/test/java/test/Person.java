package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import com.google.common.base.Function;
import com.google.common.base.Optional;

public class Person implements Serializable
{

    Place place;
    private static final long serialVersionUID = 892602449752322778l;
    private String name;
    private int age;
    private boolean boy;
    private String address;
    private static String nation = null;
    transient private String pro = null;

    public void setPlace(Place place)
    {
        this.place = place;
    }

    public Place getPlace()
    {
        return place;
    }

    public Person(String name, int age, boolean boy)
    {
        this.name = name;
        this.age = age;
        this.boy = boy;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public boolean isBoy()
    {
        return boy;
    }

    public void setBoy(boolean boy)
    {
        this.boy = boy;
    }

    public void setPro(String pro)
    {
        this.pro = pro;
    }

    public String getPro()
    {
        return pro;
    }

    public static void setNation(String nation)
    {
        Person.nation = nation;
    }

    public static String getNation()
    {
        return nation;
    }

    @Override
    public String toString()
    {
        return "name=" + name + ", age=" + age + ", boy=" + boy + ", nation=" + nation + ", pro=" + pro;

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        // outPut();
        inPut();
    }

    public static void inPut() throws IOException, ClassNotFoundException
    {
        FileInputStream in = new FileInputStream(new File("d:/test/ou.txt"));
        ObjectInputStream obStream = new ObjectInputStream(in);
        Date date = (Date) obStream.readObject();
        System.out.println(date);
        Person person = (Person) obStream.readObject();

        System.out.println(person.toString());
    }

    public static void outPut() throws IOException
    {
        FileOutputStream out = new FileOutputStream(new File("d:/test/ou.txt"));
        ObjectOutputStream objectOut = new ObjectOutputStream(out);
        Person person = new Person("高其成", 28, true);
        person.setPro("浙江");
        objectOut.writeObject(new Date());
        objectOut.writeObject(person);
        objectOut.flush();
        objectOut.close();
    }
    

}
