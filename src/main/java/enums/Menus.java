package enums;

import lombok.Data;

import java.util.List;

@Data
public class Menus {

    private float amountMax;
    private float amountMin;
    private String currency;
    private List<String> dateSeen;
    private String description;
    private String name;
    private List<String> sourceURLs;
    private String category;

    @Override
    public String toString() {
        return "Menus{" +
                "amountMax=" + amountMax +
                ", amountMin=" + amountMin +
                ", currency='" + currency + '\'' +
                ", dateSeen=" + dateSeen +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", sourceURLs=" + sourceURLs +
                ", category='" + category + '\'' +
                '}';
    }
}
