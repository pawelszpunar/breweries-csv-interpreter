package enums;

import lombok.Data;

@Data
public class Hours {

    private String day;
    private String hour;

    @Override
    public String toString() {
        return "Hours{" +
                "day='" + day + '\'' +
                ", hour='" + hour + '\'' +
                '}';
    }
}
