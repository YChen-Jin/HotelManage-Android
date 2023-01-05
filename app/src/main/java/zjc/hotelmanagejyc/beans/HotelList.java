package zjc.hotelmanagejyc.beans;

import java.util.ArrayList;
import java.util.List;

public class HotelList {
    private List<Hotel> hotels = new ArrayList<>();

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public String toString() {
        return "HotelList{" +
                "hotels=" + hotels +
                '}';
    }
}
