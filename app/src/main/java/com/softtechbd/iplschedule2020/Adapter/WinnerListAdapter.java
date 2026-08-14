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
import com.softtechbd.iplschedule2020.Model.WinnerListModel;

import java.util.List;

public class WinnerListAdapter extends RecyclerView.Adapter<WinnerListAdapter.MyViewHolder> {

    Context mContext;
    List<WinnerListModel> mData;

    public WinnerListAdapter() {
    }

    public WinnerListAdapter(Context mContext, List<WinnerListModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.winnerlist_example,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.teampic.setImageResource(mData.get(position).getPicture());
        holder.year.setText(mData.get(position).getYear());
        holder.winner.setText(mData.get(position).getWinner());
        holder.runnerup.setText(mData.get(position).getRunnerUp());
        holder.orangecap.setText(mData.get(position).getOrangeCap());
        holder.purplecap.setText(mData.get(position).getPurpleCap());
        holder.manofthematch.setText(mData.get(position).getManoftheMatch());
        holder.playerofthematch.setText(mData.get(position).getPlayeroftheTournament());
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animatiom));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView teampic;
        TextView year,winner,runnerup,orangecap,purplecap,manofthematch,playerofthematch;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            teampic=(ImageView) itemView.findViewById(R.id.WinnerTeamPic);
            year=(TextView) itemView.findViewById(R.id.yearID);
            winner=(TextView) itemView.findViewById(R.id.winnerID);
            runnerup=(TextView) itemView.findViewById(R.id.runnerUpID);
            orangecap=(TextView) itemView.findViewById(R.id.orangecapID);
            purplecap=(TextView) itemView.findViewById(R.id.purplecapID);
            manofthematch=(TextView) itemView.findViewById(R.id.manofthematchID);
            playerofthematch=(TextView) itemView.findViewById(R.id.playerofthematchID);
            cardView=(CardView)itemView.findViewById(R.id.winnerlist_cardView);
        }
    }
}
