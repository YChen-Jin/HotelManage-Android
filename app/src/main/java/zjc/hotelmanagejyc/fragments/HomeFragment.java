package zjc.hotelmanagejyc.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import zjc.hotelmanagejyc.HotelDetailActivity;
import zjc.hotelmanagejyc.R;
import zjc.hotelmanagejyc.adapter.RecyclerAdapter_header;
import zjc.hotelmanagejyc.adapter.RecyclerAdapter;
import zjc.hotelmanagejyc.beans.Hotel;
import zjc.hotelmanagejyc.beans.HotelKindList;
import zjc.hotelmanagejyc.beans.HotelList;
import zjc.hotelmanagejyc.model.Imp.HotelServiceImp;
import zjc.hotelmanagejyc.model.Imp.HotelkindServiceImp;
import zjc.hotelmanagejyc.model.Interface.HotelkindService;
import zjc.hotelmanagejyc.model.Interface.HotelService;

public class HomeFragment extends Fragment {

    private View fragment_homeView;
    private Banner banner;
    private RecyclerView fragment_homekind_recyclerView;

    private RecyclerView fragment_home_recyclerView;



    private RecyclerAdapter_header recyclerAdapterHeader;
    private RecyclerAdapter recyclerAdapter_;


    public HomeFragment() {
        // Required empty public constructor
    }

    private  void initBanner(){
        List images = new ArrayList();
        images.add(R.drawable.bannerimg1);
        images.add(R.drawable.bannerimg2);
        images.add(R.drawable.bannerimg3);
        images.add(R.drawable.bannerimg4);
        images.add(R.drawable.bannerimg5);
        // 在fragment_homeView中利用findViewById函数获得Banner对象
        banner = fragment_homeView.findViewById(R.id.home_banner);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                // 利用Glide加载图片
                Glide.with(getContext()).load(path).into(imageView);
            }
        });
        banner.setImages(images);
        banner.start();
    }

    public void initHotelData(int hotelKindid){
        HotelService hotelService = new HotelServiceImp(this);
        hotelService.findHotelByHotelClassId(String.valueOf(hotelKindid));
    }


    public void initHotelClassData(){
        HotelkindService hotelkindService = new HotelkindServiceImp(this);
        hotelkindService.findAllHotelClass();
    }


    private HotelList hotelList = new HotelList();
    private HotelKindList hotelKindList = new HotelKindList();

    public void showAllHotelKindCallback(final HotelKindList hotelKindListFromJson){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerAdapterHeader.setHotelKindList(hotelKindListFromJson);
//                fragment_homekind_recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapterHeader.notifyDataSetChanged();
                System.out.println("--->显示酒店类型");
                recyclerAdapterHeader.setOnItemClickListener(new RecyclerAdapter_header.OnItemClickListener() {
                    @Override
                    public void onItemClick(int hotelclassID) {
                        Toast.makeText(fragment_homekind_recyclerView.getContext(), "酒店分类编号" +hotelclassID + "被点击",Toast.LENGTH_LONG).show();
                        initHotelData(hotelclassID);
                    }
                });
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragment_homeView = inflater.inflate(R.layout.fragment_home, container, false);
        initBanner();

        fragment_homekind_recyclerView = fragment_homeView.findViewById(R.id.hotelkind_rr);

        fragment_home_recyclerView = fragment_homeView.findViewById(R.id.hotel_rr);


        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
        fragment_homekind_recyclerView.setLayoutManager(gridLayoutManager);
        recyclerAdapterHeader = new RecyclerAdapter_header(hotelKindList);
        fragment_homekind_recyclerView.setAdapter(recyclerAdapterHeader);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        fragment_home_recyclerView.setLayoutManager(linearLayoutManager);

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        fragment_home_recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        recyclerAdapter_ =new RecyclerAdapter(hotelList);
//                recyclerAdapter_header.setHotelList(hotelListFromJson);
        fragment_home_recyclerView.setAdapter(recyclerAdapter_);


        initHotelClassData();
        return fragment_homeView;
    }


    public void showHotelByHotelkindIdCallback(HotelList hotelListFromJson) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                recyclerAdapter_header=new RecyclerAdapter_header(hotelListFromJson);
                recyclerAdapter_.setHotelList(hotelListFromJson);
//                fragment_home_recyclerView.setAdapter(recyclerAdapter_header);
                recyclerAdapter_.notifyDataSetChanged();
                System.out.println("--->显示酒店");

                recyclerAdapter_.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int hotelClassID) {
                        Toast.makeText(fragment_homekind_recyclerView.getContext(), "酒店编号" +hotelClassID + "被点击",Toast.LENGTH_LONG).show();
                        toHotelDetail(hotelClassID);
                    }
                });
            }
        });
    }

    private void toHotelDetail(int hotelID) {
        HotelService hotelService=new HotelServiceImp(this);
        hotelService.findHotelById(hotelID);
    }

    public void toHotelDetailActivity(Hotel hotel) {
        Intent intent=new Intent(getContext(), HotelDetailActivity.class);
        intent.putExtra("Hotel",hotel);
        startActivity(intent);

    }
}