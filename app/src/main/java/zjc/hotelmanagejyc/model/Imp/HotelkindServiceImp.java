package zjc.hotelmanagejyc.model.Imp;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import zjc.hotelmanagejyc.beans.HotelKindList;
import zjc.hotelmanagejyc.fragments.HomeFragment;
import zjc.hotelmanagejyc.model.Interface.HotelkindService;
import zjc.hotelmanagejyc.okHttp.HttpUtil;

public class HotelkindServiceImp implements HotelkindService {
    final String TAG="HotelkindServiceImp";
    private HotelKindList hotelKindListFromJson;
    private HomeFragment homeFragment;
    // 注入回调类的构造函数
    public HotelkindServiceImp(HomeFragment homeFragment){
        this.homeFragment=homeFragment;
    }

    @Override
    public void findAllHotelClass() {
        String findAllHotelClass= HttpUtil.host+"/HotelManage/findAllHotelClass";
        HttpUtil.sendOkhttpGetRequest(findAllHotelClass, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v(TAG,"web接口服务连接失败，请确保主机ip地址是否正确，然后打开tomcat");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                parseJSONtoBookkindList(response.body().string());
                homeFragment.showAllHotelKindCallback(hotelKindListFromJson);
            }


        });
    }

    private void parseJSONtoBookkindList(String response) {
        Gson gson=new Gson();
        hotelKindListFromJson=gson.fromJson(response,
                new TypeToken<HotelKindList>(){}.getType());
        System.out.println("--->"+hotelKindListFromJson);
    }
}
