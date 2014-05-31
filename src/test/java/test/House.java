package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class House implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String address;
    List<Person> members;

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

    public void setMembers(List<Person> members)
    {
        this.members = members;
    }

    public List<Person> getMembers()
    {
        return members;
    }

    public static void outPut() throws IOException
    {

        FileOutputStream out = new FileOutputStream(new File("d:/test/ou.txt"));
        ObjectOutputStream objectOut = new ObjectOutputStream(out);
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("高其成1", 28, true));
        persons.add(new Person("高其成2", 27, true));
        persons.add(new Person("高其成3", 26, true));
        persons.add(new Person("高其成4", 25, true));
        House house = new House();
        house.setAddress("浙江省杭州市拱墅区");
        house.setMembers(persons);
        objectOut.writeObject(house);
        objectOut.flush();
        objectOut.close();

    }

    public static void inPut() throws IOException, ClassNotFoundException
    {
        FileInputStream in = new FileInputStream(new File("d:/test/ou.txt"));
        ObjectInputStream obStream = new ObjectInputStream(in);

        House house = (House) obStream.readObject();

        System.out.println(house.toString());
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        outPut();
//        inPut();
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer().append("address=").append(address);
        if (null != members)
        {
            for (Person p : members)
            {
                sb.append("  person: " + p.toString() + "\r\n");
            }

        }
        return sb.toString();

    }

}
