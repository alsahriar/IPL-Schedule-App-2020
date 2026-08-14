package com.softtechbd.iplschedule2020.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;

import com.softtechbd.iplschedule2020.R;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


public class Team extends Fragment {
        private GridLayout gridLayout;
        private CardView tcard1,tcard2,tcard3,tcard4,tcard5,tcard6,tcard7,tcard8;
        private FragmentAlistner listenr;
        private String input;
        Context mcontext;
//        private InterstitialAd interstitialAd;
        private int Item_Count=0;

        public interface FragmentAlistner{
            void onInputAsent(CharSequence input);
        }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_team, container, false);
        gridLayout=(GridLayout)v.findViewById(R.id.Teamagridlayout);
        tcard1 = v.findViewById(R.id.tCard1);
        tcard2 = v.findViewById(R.id.tCard2);
        tcard3 = v.findViewById(R.id.tCard3);
        tcard4 = v.findViewById(R.id.tCard4);
        tcard5 = v.findViewById(R.id.tCard5);
        tcard6 = v.findViewById(R.id.tCard6);
        tcard7 = v.findViewById(R.id.tCard7);
        tcard8 = v.findViewById(R.id.tCard8);
        tcard1.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.uptodown));
        tcard2.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.uptodown));
        tcard3.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.anime_left_to_right));
        tcard4.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.anime_right_to_left));
        tcard5.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.anime_left_to_right));
        tcard6.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.anime_right_to_left));
        tcard7.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.downtoup));
        tcard8.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.downtoup));
        SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences("team_item_count",MODE_PRIVATE);
        Item_Count= sharedPreferences.getInt("count_key",0);
        setSingleEvent(gridLayout);






        /*interstitialAd = new InterstitialAd(getContext(),getResources().getString(R.string.int_placementID));
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                if(interstitialAd.isAdLoaded()){
                    interstitialAd.show();
                }
                *//*if(interstitialAd.isAdLoaded()){
                    interstitialAd.show();
                }*//*
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });*/

        return v;
    }



    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setTitle("Team & Squad");

        }

    }
    private void setSingleEvent(GridLayout gridLayout) {
        for (int i=0;i<gridLayout.getChildCount();i++){
            CardView cardView= (CardView)gridLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(finalI ==0){
                        /*CharSequence input1 = "CSK";
                        listenr.onInputAsent(input1);*/
                        input="CSK";
                        showSquadList();
                        storeItem();

                    }else if (finalI==1){
                        input="MI";
                        showSquadList();
                        storeItem();
                    }else if (finalI==2){
                        input="KKR";
                        showSquadList();
                        storeItem();
                    }else if (finalI==3){
                        input="SRH";
                        showSquadList();
                        storeItem();
                    }else if (finalI==4){
                        input="RCB";
                        showSquadList();
                        storeItem();
                    }else if (finalI==5){
                        input="DC";
                        showSquadList();
                        storeItem();
                    }else if (finalI==6){
                        input="KXIP";
                        showSquadList();
                        storeItem();
                    }else if (finalI==7){
                        input="RR";
                        showSquadList();
                        storeItem();
                    }
                }
            });
        }
    }

    private void storeItem() {
            /*if(Item_Count>=2){
//                interstitialAd.loadAd();
            Item_Count=0;
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("team_item_count",MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedPreferences.edit();
            editor1.putInt("count_key",Item_Count+1);
            editor1.apply();
            }
            else{
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("team_item_count",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("count_key",Item_Count+1);
                editor.apply();
            }*/
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FragmentAlistner){
            listenr= (FragmentAlistner)context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listenr = null;
    }

    public Team() {
        // Required empty public constructor
    }


    private void showSquadList(){
        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        SquadList fragement =new SquadList(mcontext);
        Bundle args = new Bundle();
        args.putString("Team",input);
        fragement.setArguments(args);
        transaction.replace(R.id.fragment_container, fragement );
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
