package zjc.hotelmanagejyc.beans;

public class Hotelkind {
    private Integer hotelkindid;

    private String hotelkindname;

    public Integer getHotelkindid() {
        return hotelkindid;
    }

    public void setHotelkindid(Integer hotelkindid) {
        this.hotelkindid = hotelkindid;
    }

    public String getHotelkindname() {
        return hotelkindname;
    }

    public void setHotelkindname(String hotelkindname) {
        this.hotelkindname = hotelkindname;
    }

    @Override
    public String toString() {
        return "Hotelkind{" +
                "hotelkindid=" + hotelkindid +
                ", hotelkindname='" + hotelkindname + '\'' +
                '}';
    }
}
