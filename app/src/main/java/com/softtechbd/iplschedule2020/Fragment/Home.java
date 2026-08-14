package com.softtechbd.iplschedule2020.Fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

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

import com.crashlytics.android.Crashlytics;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.softtechbd.iplschedule2020.R;
import com.softtechbd.iplschedule2020.ScheduleList;

import static android.content.Context.MODE_PRIVATE;


public class Home extends Fragment {

    private GridLayout hGridLayout;
    private CardView hCard1,hCard2,hCard3,hCard4,hCard5,hCard6,hCard7,hCard8;
    Fragment selectedFragment = null;
//    private InterstitialAd interstitialAd;
    private int home_item_count=0;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        hGridLayout=v.findViewById(R.id.hGridlayoutID);
        hCard1 = v.findViewById(R.id.hCard1);
        hCard2 = v.findViewById(R.id.hCard2);
        hCard3 = v.findViewById(R.id.hCard3);
        hCard4 = v.findViewById(R.id.hCard4);
        hCard5 = v.findViewById(R.id.hCard5);
        hCard6 = v.findViewById(R.id.hCard6);
        hCard7 = v.findViewById(R.id.hCard7);
        hCard8 = v.findViewById(R.id.hCard8);

        hCard1.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.uptodown));
        hCard2.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.uptodown));
        hCard3.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.uptodown));
        hCard4.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.anime_left_to_right));
        hCard5.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.anime_right_to_left));
        hCard6.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.downtoup));
        hCard7.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.downtoup));
        hCard8.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.downtoup));

        final SharedPreferences preferences = getActivity().getSharedPreferences("TapTG",MODE_PRIVATE);
        boolean isTapped = preferences.getBoolean("isTapped",false);
        if(!isTapped){
            @SuppressLint("CutPasteId") TapTargetSequence sequence = new TapTargetSequence(getActivity())
                    .target(TapTarget.forView(v.findViewById(R.id.hCard1),"This is schedule","Here you can see IPL Schedule, Time table").tintTarget(false).outerCircleColor(R.color.TapTargetColor))
                    .target(TapTarget.forView(v.findViewById(R.id.hCard2),"This is Team & Squad","Here you can find every team SquadList, and Others Information").tintTarget(false).outerCircleColor(R.color.TapTargetColor))
                    .target(TapTarget.forView(v.findViewById(R.id.hCard3),"This is Live Score","Here you can see Live Cricket Scores").tintTarget(false).outerCircleColor(R.color.TapTargetColor))
                    .target(TapTarget.forView(v.findViewById(R.id.hCard4),"This is WinnerList","Here you can find IPL WinnerList 2008-19").tintTarget(false).outerCircleColor(R.color.TapTargetColor))
                    .target(TapTarget.forView(v.findViewById(R.id.hCard5),"This is Point Table","Here you can see latest point table").tintTarget(false).outerCircleColor(R.color.TapTargetColor))
                    .target(TapTarget.forView(v.findViewById(R.id.hCard6),"This is Venues","Here you can see IPL Venues").tintTarget(false).outerCircleColor(R.color.TapTargetColor))
                    .target(TapTarget.forView(v.findViewById(R.id.hCard7),"This is Play Cricket","Here you can Play Cricket").tintTarget(false).outerCircleColor(R.color.TapTargetColor))
                    .target(TapTarget.forView(v.findViewById(R.id.hCard8),"This is Record Corner","Here you can see all times IPL Records").tintTarget(false).outerCircleColor(R.color.TapTargetColor))
                    .listener(new TapTargetSequence.Listener() {
                        @Override
                        public void onSequenceFinish() {
                            SharedPreferences.Editor editor=preferences.edit();
                            editor.putBoolean("isTapped",true);
                            editor.apply();
                        }

                        @Override
                        public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                        }

                        @Override
                        public void onSequenceCanceled(TapTarget lastTarget) {
                            SharedPreferences.Editor editor=preferences.edit();
                            editor.putBoolean("isTapped",true);
                            editor.apply();
                        }
                    });sequence.start();
        }


        setSingleEvent(hGridLayout);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("home_item_count",MODE_PRIVATE);
        home_item_count= sharedPreferences.getInt("item_countKEY",0);
        return v;

    }@Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setTitle("Home");

        }
    }
    private void openTeam(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        Team newCustomFragment =new Team();
        transaction.replace(R.id.fragment_container, newCustomFragment );
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void CheckData() {
        /*if(home_item_count>=2){
            interstitialAd.loadAd();
            home_item_count=0;
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("home_item_count",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("item_countKEY",home_item_count+1);
            editor.apply();
        }else{
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("home_item_count",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("item_countKEY",home_item_count+1);
            editor.apply();
        }*/
    }


    private void setSingleEvent(GridLayout hGridLayout) {
        for (int i=0;i<hGridLayout.getChildCount();i++){
            CardView cardView= (CardView)hGridLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                boolean ck =false;
                @Override
                public void onClick(View v) {
                    if(finalI ==0){
                        ck=true;
                        startActivity(new Intent(getContext(), ScheduleList.class));
                        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }else if (finalI==1){
                        selectedFragment= new Team();
                    }else if (finalI==2){
                        selectedFragment=new LiveScore();
                    }else if (finalI==3){
                        selectedFragment=new WinnerList();
                    }else if (finalI==4){
                        selectedFragment=new PointTable();
                    }else if (finalI==5){
                        selectedFragment= new Venues();
                    }else if(finalI==6){
                        selectedFragment= new Play_Crickert();
                    }else if(finalI==7){
                        selectedFragment=new Records();
                    }
                    if(!ck){
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                                .replace(R.id.fragment_container,selectedFragment).addToBackStack(null).commit();
                    }

                }
            });
        }
    }





}
