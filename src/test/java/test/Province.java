package test;

public class Province
{
    private String proName;
    private Country country;

    public void setCountry(Country country)
    {
        this.country = country;
    }

    public Country getCountry()
    {
        return country;
    }

    public void setProName(String proName)
    {
        this.proName = proName;
    }

    public String getProName()
    {
        return proName;
    }
}
