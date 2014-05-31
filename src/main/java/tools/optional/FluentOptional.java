package tools.optional;

import java.util.Set;
import test.City;
import test.Country;
import test.Person;
import test.Place;
import test.Province;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;

public class FluentOptional<T>
{
    private Optional<T> optional;

    private FluentOptional(Optional<T> optional)
    {
        this.optional = optional;
    }

    public static <T> FluentOptional<T> from(Optional<T> optional)
    {
        return new FluentOptional<T>(optional);
    }

    public <U> FluentOptional<U> bind(Function<T, Optional<U>> function)
    {
        if (isPresent())
        {
            return from(function.apply(get()));
        }

        return from(Optional.<U> absent());
    }

    public boolean isPresent()
    {
        return optional.isPresent();
    }

    public T get()
    {
        return optional.get();
    }

    public T or(T defaultValue)
    {
        return optional.or(defaultValue);
    }

    public Optional<T> or(Optional<? extends T> secondChoice)
    {
        return optional.or(secondChoice);
    }

    public T or(Supplier<? extends T> supplier)
    {
        return optional.or(supplier);
    }

    public T orNull()
    {
        return optional.orNull();
    }

    public Set<T> asSet()
    {
        return optional.asSet();
    }

    public <V> Optional<V> transform(Function<? super T, V> function)
    {
        return optional.transform(function);
    }

    @Override
    public boolean equals(Object object)
    {
        return optional.equals(object);
    }

    @Override
    public int hashCode()
    {
        return optional.hashCode();
    }

    @Override
    public String toString()
    {
        return optional.toString();
    }

    public static void main(String[] args)
    {
        Country country = new Country();
        country.setName("中国");
        Province province = new Province();
        province.setProName("江苏");
        province.setCountry(country);
        City city = new City();
        city.setCityName("南京");
        city.setProvince(province);
        Place place = new Place();
        place.setPlaceName("鼓楼区");
        place.setCity(city);
        Person person = new Person("高其成", 28, true);

        FluentOptional<Province> op = FluentOptional.from(Optional.of(person)).bind(getPlaceFromPerson())
                .bind(getCityFromPlace()).bind(getProvinceFromCity());
        System.out.println(op.toString());
    }

    public static Function<Person, Optional<Place>> getPlaceFromPerson()
    {
        return new Function<Person, Optional<Place>>()
        {

            @Override
            public Optional<Place> apply(Person person)
            {

                return Optional.fromNullable(person.getPlace());

            }
        };
    }

    public static Function<Place, Optional<City>> getCityFromPlace()
    {
        return new Function<Place, Optional<City>>()
        {

            @Override
            public Optional<City> apply(Place place)
            {

                return Optional.fromNullable(place.getCity());
            }
        };
    }

    public static Function<City, Optional<Province>> getProvinceFromCity()
    {
        return new Function<City, Optional<Province>>()
        {

            @Override
            public Optional<Province> apply(City city)
            {
                return Optional.fromNullable(city.getProvince());
            }
        };
    }

}