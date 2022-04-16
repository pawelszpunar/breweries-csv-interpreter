package helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import enums.Brewery;
import enums.Hours;
import enums.Menus;

import java.util.Arrays;
import java.util.List;

public class CreateBrewery {

    public final static ObjectMapper mapper = new ObjectMapper();
    public static final String menusError =
            "There are too many menus objects to show. Please use a view that flattens this field to see this data.";

    public static Brewery build(String[] data) {
        Brewery brewery = new Brewery();
        brewery.setId(data[0]);
        brewery.setAddress(data[1]);
        brewery.setCategories(data[2]);
        brewery.setCity(data[3]);
        brewery.setCountry(data[4]);

        try {
            if (!data[5].isEmpty()) {
                List<Hours> tempList;
                tempList = Arrays.asList(mapper.readValue(data[5], Hours[].class));
                brewery.setHoursList(tempList);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        brewery.setKeys(data[6]);
        brewery.setLatitude(data[7]);
        brewery.setLongitude(data[8]);

        try {
            if (!data[9].isEmpty() && !data[9].equals(menusError)) {
                List<Menus> tempList;
                tempList = Arrays.asList(mapper.readValue(data[9], Menus[].class));
                brewery.setMenusList(tempList);
            }
        } catch (JsonProcessingException exc) {
            exc.printStackTrace();
        }

        brewery.setName(data[10]);
        brewery.setPostalCode(data[11]);
        brewery.setProvince(data[12]);
        brewery.setTwitter(data[13]);
        brewery.setWebsite(data[14]);
        return brewery;
    }
}
