package enums;

import lombok.Data;

import java.util.List;

@Data
public class Brewery {

    private String id;
    private String address;
    private String categories;
    private String city;
    private String country;
    private List<Hours> hoursList;
    private String keys;
    private String latitude;
    private String longitude;
    private List<Menus> menusList;
    private String name;
    private String postalCode;
    private String province;
    private String twitter;
    private String website;

    @Override
    public String toString() {
        return "Brewery{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", categories='" + categories + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", hoursList=" + hoursList +
                ", keys='" + keys + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", menusList=" + menusList +
                ", name='" + name + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", province='" + province + '\'' +
                ", twitter='" + twitter + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
