package zjc.hotelmanagejyc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import zjc.hotelmanagejyc.beans.Hotel;
import zjc.hotelmanagejyc.fragments.HomeFragment;
import zjc.hotelmanagejyc.model.Imp.HotelServiceImp;
import zjc.hotelmanagejyc.model.Interface.HotelService;

public class HotelDetailActivity extends AppCompatActivity {
    private EditText hotelName;
    private EditText hotelPrice;
    private Hotel hotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent =this.getIntent();
        hotel=(Hotel)intent.getSerializableExtra("Hotel");
        setContentView(R.layout.activity_hotel_detail);
        hotelName=findViewById(R.id.hotel_detail_name);
        hotelPrice=findViewById(R.id.hotel_detail_price);
        hotelName.setText(hotel.getHotelname());
        hotelPrice.setText(hotel.getHotelprice());
    }

    public void update(View view){
        HotelService hotelService=new HotelServiceImp(this);
        hotel.setHotelname(hotelName.getText().toString());
        hotel.setHotelprice(hotelPrice.getText().toString());
        hotelService.updateHotel(hotel);
    }

    public void updateSuccess(){
        Intent intent=new Intent(zjc.hotelmanagejyc.HotelDetailActivity.this, MainActivity.class);
        startActivity(intent);
    }
}