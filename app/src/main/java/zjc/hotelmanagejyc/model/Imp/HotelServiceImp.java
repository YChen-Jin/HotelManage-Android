package zjc.hotelmanagejyc.model.Imp;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;
import zjc.hotelmanagejyc.HotelDetailActivity;
import zjc.hotelmanagejyc.MainActivity;
import zjc.hotelmanagejyc.beans.Hotel;
import zjc.hotelmanagejyc.beans.HotelList;
import zjc.hotelmanagejyc.fragments.HomeFragment;
import zjc.hotelmanagejyc.model.Interface.HotelService;
import zjc.hotelmanagejyc.okHttp.HttpUtil;

public class HotelServiceImp implements HotelService {
    String TAG="HotelServiceImp";
    private HotelList hotelListFromJson;
    private HomeFragment homeFragment;
    private HotelDetailActivity hotelDetailActivity;

    public HotelDetailActivity getHotelDetailActivity() {
        return hotelDetailActivity;
    }

    public void setHotelDetailActivity(HotelDetailActivity hotelDetailActivity) {
        this.hotelDetailActivity = hotelDetailActivity;
    }

    // 注入回调类的构造函数
    public HotelServiceImp(HomeFragment homeFragment){
        this.homeFragment=homeFragment;
    }

    public HotelServiceImp(HotelDetailActivity hotelDetailActivity) {
        this.hotelDetailActivity = hotelDetailActivity;
    }

    @Override
    public void findHotelByHotelClassId(String hotelkindid) {
        String findHotelByHotelClassId= HttpUtil.host+"/HotelManage/findHotelByHotelClassId=" + hotelkindid;
        HttpUtil.sendOkhttpGetRequest(findHotelByHotelClassId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v(TAG,"web接口服务连接失败，请确保主机ip地址是否正确，然后打开tomcat");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                parseJSONtoHotelList(response.body().string());
                homeFragment.showHotelByHotelkindIdCallback(hotelListFromJson);
            }
        });
    }

    @Override
    public void findHotelById(Integer hotelid) {
        String URL= HttpUtil.host+"/HotelManage/findHotelById/hotelId="+hotelid;
        HttpUtil.sendOkhttpGetRequest(URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("巴拉巴拉");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                parseJSONtoHotelList(response.body().string());
                homeFragment.toHotelDetailActivity(hotelListFromJson.getHotels().get(0));
            }
        });

    }

    @Override
    public void updateHotel(Hotel hotel) {
        String name=hotel.getHotelname();
        String price=hotel.getHotelprice();
        String id= String.valueOf(hotel.getHotelid());
        String URL= HttpUtil.host+"/HotelManage/updateBookById?hotelid="+id+"&hotelname="+name+"&hotelprice="+price;
        HttpUtil.sendOkhttpGetRequest(URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("高峰时段");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                hotelDetailActivity.updateSuccess();
            }
        });
    }

    private void parseJSONtoHotelList(String response) {
        Gson gson=new Gson();
        hotelListFromJson=gson.fromJson(response,new TypeToken<HotelList>(){}.getType());
        System.out.println("--->"+hotelListFromJson);
    }
}
