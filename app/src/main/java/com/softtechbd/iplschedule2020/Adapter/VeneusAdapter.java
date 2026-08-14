package com.softtechbd.iplschedule2020.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.softtechbd.iplschedule2020.R;
import com.softtechbd.iplschedule2020.Model.VenuesModel;

import java.util.List;

public class VeneusAdapter extends RecyclerView.Adapter<VeneusAdapter.MyViewHolder> {

    Context mContext;
    List<VenuesModel> mdata;

    public VeneusAdapter() {
    }

    public VeneusAdapter(Context mContext, List<VenuesModel> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
    }

    public Context getmContext() {
        return mContext;
    }

    public List<VenuesModel> getMdata() {
        return mdata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.venues_example,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.veneusImg.setImageResource(mdata.get(position).getVenueImg());
        holder.veneuName.setText(mdata.get(position).getVenueName());
        holder.veneuHometeam.setText(mdata.get(position).getVeneuHometeam());
        holder.veneuLocation.setText(mdata.get(position).getVenueLocation());
        holder.veneuOpened.setText(mdata.get(position).getVenueOpened());
        holder.veneuCapacity.setText(mdata.get(position).getVenueCapacity());
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transaction_animation));
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView veneusImg;
        private TextView veneuName, veneuHometeam, veneuLocation, veneuOpened, veneuCapacity;
        private CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            veneusImg=(ImageView)itemView.findViewById(R.id.stadiumImg);
            veneuName=(TextView)itemView.findViewById(R.id.stadiumName);
            veneuHometeam=(TextView)itemView.findViewById(R.id.stadiumHometeam);
            veneuLocation=(TextView)itemView.findViewById(R.id.stadiumLocation);
            veneuOpened=(TextView)itemView.findViewById(R.id.stadiumOpened);
            veneuCapacity=(TextView)itemView.findViewById(R.id.stadiumCapacity);
            cardView=(CardView)itemView.findViewById(R.id.venuesCardCView);
        }
    }
}
