package test;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import tools.optional.FluentOptional;
import tools.print.TLogger;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

public class Test
{
    private static final TLogger logger = new TLogger(Test.class);

    public static void main(String[] args) throws IOException
    {
        // List<String> list = Lists.newArrayList();
        // list.add("cust_id");
        // for (File file : SearchSuffixIndir.searchFiles(
        // "E:/work/source/dev/infosystem_jslt/tools/ims-hbtest/src/main/resources/xml/webservice", "", "xml"))
        // {
        // logger.info("filename: " + file.getName());
        // List<String> lines = Lists.newArrayList();
        // for (String line : FileTool.readFileLines(file.getAbsolutePath()))
        // {
        //
        // lines.add(replace(line, list));
        // }
        // // logger.info(lines);
        // FileWriterTool.write(lines, file.getAbsolutePath(), false);
        //
        // }

        // Optional<Date> date=Optional.of(null);
        // System.out.println( date.or(new Date()));

        Collection<Integer> col = Collections2.transform(Lists.newArrayList("1", "2", "3", "4", "a", "b", "c"),
                new Function<String, Integer>()
                {

                    @Override
                    public Integer apply(String arg0)
                    {
                        
                        return Ints.tryParse(arg0);

                    }
                });
        logger.info(col);
 
         for(Integer i:Range.open(0, 1000).){
             System.out.println(i);
         }

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

    private static String replace(String line, List<String> list)
    {
        for (String s : list)
        {
            if (line.contains(s))
            {
                return new StringBuffer(getBlank(line, s)).append("<").append(s).append(">${").append(s).append("}</").append(s)
                        .append(">").toString();
            }
        }
        return line;
    }

    private static String getBlank(String line, String key)
    {
        return Strings.repeat(" ", line.indexOf(key) - 1);
    }
}