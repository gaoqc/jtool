package test;

public class Place
{
    private String placeName;

    private City city;

    public String getPlaceName()
    {
        return placeName;
    }

    public void setPlaceName(String placeName)
    {
        this.placeName = placeName;
    }

    public void setCity(City city)
    {
        this.city = city;
    }

    public City getCity()
    {
        return city;
    }
}
