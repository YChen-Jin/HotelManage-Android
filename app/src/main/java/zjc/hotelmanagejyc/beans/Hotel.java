package zjc.hotelmanagejyc.beans;

import java.io.Serializable;

public class Hotel implements Serializable {
    private Integer hotelid;

    private Integer hotelkindid;

    private String hotelname;

    private String hotelprice;

    public Integer getHotelid() {
        return hotelid;
    }

    public void setHotelid(Integer hotelid) {
        this.hotelid = hotelid;
    }

    public Integer getHotelkindid() {
        return hotelkindid;
    }

    public void setHotelkindid(Integer hotelkindid) {
        this.hotelkindid = hotelkindid;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getHotelprice() {
        return hotelprice;
    }

    public void setHotelprice(String hotelprice) {
        this.hotelprice = hotelprice;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelid=" + hotelid +
                ", hotelkindid=" + hotelkindid +
                ", hotelname='" + hotelname + '\'' +
                ", hotelprice='" + hotelprice + '\'' +
                '}';
    }
}
