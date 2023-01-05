package zjc.hotelmanagejyc.model.Interface;

import zjc.hotelmanagejyc.beans.Hotel;

public interface HotelService {
    //    1 根据酒店分类号查找所有酒店
    public void findHotelByHotelClassId(String hotelkindid);


    //    2 根据酒店号查找酒店
    public void findHotelById(Integer hotelid);

    //    3 修改指定酒店名称和价格
    public void updateHotel(Hotel hotel);
}
