package com.softtechbd.iplschedule2020.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.softtechbd.iplschedule2020.Fragment.SquadList;
import com.softtechbd.iplschedule2020.Model.ModelClass2;
import com.softtechbd.iplschedule2020.R;

import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class SquadAdapter extends RecyclerView.Adapter<SquadAdapter.MyViewHolder> {
    private Context mcontext;
    private List<ModelClass2> mdata;
    private Dialog alertDialog;
    private String TeamName="MI";
    Activity activity;
    private int lastPosition = -1;
    // A menu item view type.
    private static final int MENU_ITEM_VIEW_TYPE = 0;
    // The list of banner ads and menu items.

    // The banner ad view type.
    private static final int BANNER_AD_VIEW_TYPE = 1;
    public SquadAdapter(Context mcontent, List<ModelClass2> mdata) {
        this.mcontext = mcontent;
        this.mdata = mdata;

    }

    public SquadAdapter() {

    }

    public void show_dialog(){

    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public SquadAdapter(FragmentActivity activity) {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mcontext).inflate(R.layout.squadlist_example,parent,false);


        final MyViewHolder viewHolder = new MyViewHolder(v);


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new Dialog(mcontext);
                alertDialog.setContentView(R.layout.player_details_dialog);

                Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ImageView player_profile = (ImageView)alertDialog.findViewById(R.id.Dplayer_pic);
                TextView player_name = (TextView)alertDialog.findViewById(R.id.Dplayer_name);
                TextView player_role = (TextView)alertDialog.findViewById(R.id.DP_Role);
                TextView batting_style = (TextView)alertDialog.findViewById(R.id.DP_battingStyle);
                TextView bowling_style= (TextView)alertDialog.findViewById(R.id.DP_bowlingStyle);
                TextView nationality=(TextView)alertDialog.findViewById(R.id.DP_nationality);
                LinearLayout layout=(LinearLayout)alertDialog.findViewById(R.id.layoutBG);

                player_profile.setImageResource(mdata.get(viewHolder.getAdapterPosition()).getPlayer_profile());
                player_name.setText(mdata.get(viewHolder.getAdapterPosition()).getPlayer_name());
                player_role.setText(mdata.get(viewHolder.getAdapterPosition()).getRole());
                batting_style.setText(mdata.get(viewHolder.getAdapterPosition()).getBatting_style());
                bowling_style.setText(mdata.get(viewHolder.getAdapterPosition()).getBowling_style());
                nationality.setText(mdata.get(viewHolder.getAdapterPosition()).getNationality());

                alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                Button dialogButton=(Button)alertDialog.findViewById(R.id.btnDialog);
                alertDialog.show();
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.player_profile.setImageResource(mdata.get(position).getPlayer_profile());
        holder.player_name.setText(mdata.get(position).getPlayer_name());
        holder.player_price.setText(mdata.get(position).getPlayer_price());
        holder.player_status.setImageResource(mdata.get(position).getPlayer_status());
    }
    private void setAnimation(View viewToAnimate, int position) {

        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mcontext, R.anim.slide_from_bottom);
            animation.setDuration(400);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        } /*else if ( position < lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContent, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }*/
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView player_profile;
        private TextView player_name;
        private TextView player_price;
        private ImageView player_status;
        private LinearLayout dialog_layout;
        private TextView DP_playerName;
        private TextView role;
        private TextView batting_style;
        private TextView bowling_style;
        private TextView nationality;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            player_profile=(CircleImageView)itemView.findViewById(R.id.player_profile);
            player_name=(TextView)itemView.findViewById(R.id.player_name);
            player_price=(TextView)itemView.findViewById(R.id.player_price);
            player_status=(ImageView)itemView.findViewById(R.id.player_status);
            dialog_layout=(LinearLayout)itemView.findViewById(R.id.player_details_dialog);
            DP_playerName=(TextView)itemView.findViewById(R.id.Dplayer_name);
            role = (TextView)itemView.findViewById(R.id.DP_Role);
            batting_style=(TextView)itemView.findViewById(R.id.DP_battingStyle);
            bowling_style=(TextView)itemView.findViewById(R.id.DP_bowlingStyle);
            nationality=(TextView)itemView.findViewById(R.id.DP_nationality);


        }
    }public static void main(String args){

    }


}
