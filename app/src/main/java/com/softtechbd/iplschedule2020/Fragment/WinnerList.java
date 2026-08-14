package com.softtechbd.iplschedule2020.Fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.softtechbd.iplschedule2020.Adapter.WinnerListAdapter;
import com.softtechbd.iplschedule2020.Extra_code.RemoteConfig_Result;
import com.softtechbd.iplschedule2020.Model.WinnerListModel;
import com.softtechbd.iplschedule2020.R;

import java.util.ArrayList;
import java.util.List;


public class WinnerList extends Fragment {
     View view;
     RecyclerView recyclerView;
     List<WinnerListModel> list;
     WinnerListAdapter adapter;
//     private AdView adView;
    private AdView mAdView;


    public WinnerList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<WinnerListModel>();
        list.add(new WinnerListModel(R.drawable.champion2008,"2008","Rajasthan Royals","Chennai Super Kings","Shaun Marsh(KXIP)","Sohail Tanvir(RR)","Yusuf Pathan(RR)","Shane Watson(RR)"));
        list.add(new WinnerListModel(R.drawable.champion2009,"2009","Deccan Chargers","Royal Challengers Bangalore","Matthew Hayden(CSK)","R.P Sing(DC)","Anil Kumble(RCB)","Adam Gilchrist(DC)"));
        list.add(new WinnerListModel(R.drawable.champion2010,"2010","Chennai Super Kings","Mumbai Indians","Sachin Tendulkar(MI)","Pragyan Ojha(DC)","Suresh Raina(CSK)","Sachin Tendulkar(MI)"));
        list.add(new WinnerListModel(R.drawable.champion2011,"2011","Chennai Super Kings","Royal Challengers Bangalore","Chris Gayle(RCB)","Lasith Malinga(MI)","Murali Vijay(CSK)","Chris Gayle(RCB)"));
        list.add(new WinnerListModel(R.drawable.champion2012,"2012","Kolkata Knight Riders","Chennai Super Kings","Chris Gayle(RCB)","Morne Morkel(DD)","Manvinder Bisla(KKR)","Sunil Narine(KKR)"));
        list.add(new WinnerListModel(R.drawable.champion2013,"2013","Mumbai Indians","Chennai Super Kings","Michael Hussey(CSK)","Dwayone Bravo(CSK)","Kieron Pollard(MI)","Shane Watson(RR)"));
        list.add(new WinnerListModel(R.drawable.champion2014,"2014","Kolkata Knight Riders","Kings XI Punjab","Robin Uthappa(KKR)","Mohit Sharma(CSK)","Manish Pandey(KKR)","Glenn Maxwell(KXIP)"));
        list.add(new WinnerListModel(R.drawable.champion2015,"2015","Mumbai Indians","Chennai Super Kings","David Warner(SRH)","Dwayne Bravo(CSK)","Rohit Sharma(MI)","Andre Russell(KKR)"));
        list.add(new WinnerListModel(R.drawable.champion2016,"2016","Sunrisers Hyderabad","Royal Challengers Bangalore","Virat Kohli(RCB)","Bhuvneswar Kumar(SRH)","Ben Cutting(SRH)","Virat Kohli(RCB)"));
        list.add(new WinnerListModel(R.drawable.champion2017,"2017","Mumbai Indians","Rising Pune Supergiants","David Warner(SRH)","Bhuvneswar Kumar(SRH)","Krunal Pandya(MI)","Ben Stokes(RPS)"));
        list.add(new WinnerListModel(R.drawable.champion2018,"2018","Chennai Super Kings","Sunrisers Hyderabad","Kane Williamson(SRH)","Andrew Tye(KXIP)","Shane Watson(CSK)","Sunil Narine(KKR)"));
        list.add(new WinnerListModel(R.drawable.champion2019,"2019","Mumbai Indians","Chennai Super Kings","David Warner(SRH)","Imran Tahir(CSK)","Jasprit Bumrah(MI)","Andre Russell(KKR)"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_winner_list, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.winnerlist_recylerView);
        adapter = new WinnerListAdapter(getContext(),list);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        mAdView = v.findViewById(R.id.adView_winnerList);
        RemoteConfig_Result result = new RemoteConfig_Result();
        if(result.isAdEnabale()){
            AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("0E055679FF1AD081660BA48B7139264F").build();
            mAdView.loadAd(adRequest);
        }

        mAdView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                if (mAdView.getVisibility() == View.GONE){
                    mAdView.setVisibility(View.VISIBLE);
                }else {
                    mAdView.setVisibility(View.GONE);
                }
            }
        });
        /*adView = new AdView(getContext(),getResources().getString(R.string.ban_placementID), AdSize.BANNER_HEIGHT_50);



        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout)v. findViewById(R.id.wBanner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);
        adView.loadAd();*/

        return v;
    } public void setUpRecylerView(){
        RecyclerView recyclerView = view.findViewById(R.id.winnerlist_recylerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        adapter = new WinnerListAdapter(getContext(),list);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }public void addData(){

    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setTitle("IPL Winner (2008-19)");

        }
    }

}
