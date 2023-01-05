package zjc.hotelmanagejyc.beans;

import java.util.ArrayList;
import java.util.List;

public class HotelKindList {
    private List<Hotelkind> hotelkinds = new ArrayList<>();

    public List<Hotelkind> getHotelkinds() {
        return hotelkinds;
    }

    public void setHotelkinds(List<Hotelkind> hotelkinds) {
        this.hotelkinds = hotelkinds;
    }

    @Override
    public String toString() {
        return "HotelKindList{" +
                "hotelkinds=" + hotelkinds +
                '}';
    }
}
