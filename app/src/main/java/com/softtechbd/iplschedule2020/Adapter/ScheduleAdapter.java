package com.softtechbd.iplschedule2020.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.futuremind.recyclerviewfastscroll.SectionTitleProvider;
import com.softtechbd.iplschedule2020.Model.ModelClass1;
import com.softtechbd.iplschedule2020.R;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> implements Filterable, SectionTitleProvider {
    private Context mContent;
    private List<ModelClass1> mData;
    private List<ModelClass1> mDatafull;
    private int lastPosition = -1;

    public ScheduleAdapter() {
    }

    public ScheduleAdapter(Context mContent, List<ModelClass1> mData) {
        this.mContent = mContent;
        this.mData = mData;
        mDatafull = new ArrayList<>(mData);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mLayoutinflate = LayoutInflater.from(mContent);
        view = mLayoutinflate.inflate(R.layout.schedule_example,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.match_no.setText(mData.get(position).getMatch_no());
        holder.team1.setImageResource(mData.get(position).getTeam1());
        holder.team2.setImageResource(mData.get(position).getTeam2());
        holder.vanue.setText( mData.get(position).getVanue());
        holder.date.setText(mData.get(position).getDate());

        setAnimation(holder.itemView, position);
//        holder.cardView.setAnimation(AnimationUtils.loadAnimation(mContent,R.anim.fade_scale_animatiom));

    }
    private void setAnimation(View viewToAnimate, int position) {

        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContent, android.R.anim.slide_in_left);
            animation.setDuration(400);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        } /*else if ( position < lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContent, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }*/
    }

    public void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        return ExampleFilter;
    }
    private Filter ExampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ModelClass1> filterlist = new ArrayList<>();
            if(constraint==null || constraint.length()==0){
                filterlist.addAll(mDatafull);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ModelClass1 item : mDatafull){
                    if(item.getMatch_no().toLowerCase().contains(filterPattern)){
                        filterlist.add(item);
                    }if(item.getDate().toLowerCase().contains(filterPattern)){
                        filterlist.add(item);
                    }

                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterlist;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData.clear();
            mData.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };

    @Override
    public String getSectionTitle(int position) {
        return mData.get(position).getMatch_no();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView match_no;
        ImageView team1;
        ImageView team2;
        TextView vanue;
        TextView date;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            match_no = (TextView)itemView.findViewById(R.id.matchNoID);
            team1 = (ImageView) itemView.findViewById(R.id.team1ID);
            team2 = (ImageView) itemView.findViewById(R.id.team2ID);
            vanue = (TextView) itemView.findViewById(R.id.vanueID);
            date = (TextView) itemView.findViewById(R.id.dateID);
            cardView = (CardView)itemView.findViewById(R.id.cardviewID);
        }
    }
}
