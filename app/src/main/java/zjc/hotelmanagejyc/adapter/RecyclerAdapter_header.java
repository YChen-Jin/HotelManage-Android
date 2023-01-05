package zjc.hotelmanagejyc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zjc.hotelmanagejyc.R;
import zjc.hotelmanagejyc.beans.HotelKindList;

public class RecyclerAdapter_header extends RecyclerView.Adapter {
    private HotelKindList hotelKindList;
    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public HotelKindList getHotelKindList() {
        return hotelKindList;
    }

    public void setHotelKindList(HotelKindList hotelKindList) {
        this.hotelKindList = hotelKindList;
    }



    public RecyclerAdapter_header(HotelKindList hotelKindList){
        this.hotelKindList=hotelKindList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hotelkind_item,parent,false);
        RecyclerViewHolder hotelKindViewHolder=new RecyclerViewHolder(view);
        return hotelKindViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecyclerViewHolder hotelkindViewHolder=(RecyclerViewHolder)holder;
        String itemText=hotelKindList.getHotelkinds().get(position).getHotelkindname();
        hotelkindViewHolder.textView.setText(itemText);
        switch (itemText){
            case "经济型":
                hotelkindViewHolder.imageView.setImageResource(R.drawable.live);
                break;
            case "三星级":
                hotelkindViewHolder.imageView.setImageResource(R.drawable.office);
                break;
            case "其他":
                hotelkindViewHolder.imageView.setImageResource(R.drawable.outdoor);
                break;
        }
        if(onItemClickListener!=null){
            final int hotelkindID = new Integer(hotelKindList.getHotelkinds().get(position).getHotelkindid());
            hotelkindViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(hotelkindID);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return hotelKindList.getHotelkinds().size();
    }

    public interface OnItemClickListener{
        void onItemClick(int hotelclassID);
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;
        private ImageView imageView;
        private TextView textView;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.hotelkind_viewholder);
            imageView=(ImageView)itemView.findViewById(R.id.hotelkind_viewholder_iv);
            textView=(TextView)itemView.findViewById(R.id.hotelkind_viewholder_tv);

        }
    }
}
