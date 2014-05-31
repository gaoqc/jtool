package test;

public class City
{
    private String cityName;
   private Province province;
   
   
   public void setProvince(Province province)
{
    this.province = province;
}
   
   public Province getProvince()
{
    return province;
}
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public String getCityName()
    {
        return cityName;
    }

}
