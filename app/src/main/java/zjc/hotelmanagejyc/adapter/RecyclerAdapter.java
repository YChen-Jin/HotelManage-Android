package zjc.hotelmanagejyc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import zjc.hotelmanagejyc.R;
import zjc.hotelmanagejyc.beans.HotelList;

public class RecyclerAdapter extends RecyclerView.Adapter{

    private HotelList hotelList;
    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
//    public void setOnItemClickListener(RecyclerAdapter.OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }

    public HotelList getHotelList() {
        return hotelList;
    }

    public void setHotelList(HotelList hotelList) {
        this.hotelList = hotelList;
    }

    public RecyclerAdapter(HotelList hotelList) {
        this.hotelList = hotelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item,parent,false);
        HotelHolder hotelHolder=new HotelHolder(view);
        return hotelHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HotelHolder hotelViewHolder=(HotelHolder)holder;
        hotelViewHolder.idTv.setText(String.valueOf(hotelList.getHotels().get(position).getHotelid()));
        hotelViewHolder.nameTv.setText(hotelList.getHotels().get(position).getHotelname());
        hotelViewHolder.priceTv.setText(hotelList.getHotels().get(position).getHotelprice());
        if(onItemClickListener!=null){
            final int hotelkindID = new Integer(hotelList.getHotels().get(position).getHotelid());
            hotelViewHolder.nameTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(hotelkindID);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return hotelList.getHotels().size();
    }

    private View recyclerViewHotelClass;

    public void setRecyclerViewHotelClass(View embeddevice_viewholder1View) {
        this.recyclerViewHotelClass = embeddevice_viewholder1View;
    }
    public interface OnItemClickListener{
        void onItemClick(int hotelclassID);
    }

    class HotelHolder extends RecyclerView.ViewHolder {
        private TextView idTv;
        private TextView nameTv;
        private TextView priceTv;
        public HotelHolder(@NonNull View itemView) {
            super(itemView);
            idTv=(TextView) itemView.findViewById(R.id.hotel_id_tv);
            nameTv=(TextView) itemView.findViewById(R.id.hotel_name_tv);

            priceTv=(TextView) itemView.findViewById(R.id.hotel_price_tv);
        }
    }
}
