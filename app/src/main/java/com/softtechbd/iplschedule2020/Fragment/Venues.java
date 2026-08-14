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
import com.softtechbd.iplschedule2020.Adapter.VeneusAdapter;
import com.softtechbd.iplschedule2020.Extra_code.RemoteConfig_Result;
import com.softtechbd.iplschedule2020.Model.VenuesModel;
import com.softtechbd.iplschedule2020.R;

import java.util.ArrayList;
import java.util.List;


public class Venues extends Fragment {
    View view;
    RecyclerView recyclerView;
    List<VenuesModel> list;
    VeneusAdapter adapter;
//    private AdView adView;
    private AdView mAdView;

    public Venues() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list=new ArrayList<VenuesModel>();
        list.add( new VenuesModel(R.drawable.stadium1_chinnaswamy,"M Chinnaswamy Stadium","Royal Challengers Bangalore","Bengaluru, India","1969","40,000"));
        list.add( new VenuesModel(R.drawable.stadium2_wankhe,"Wankhede Stadium","Mumbai Indians","Mumbai, India","1974","45,000"));
        list.add( new VenuesModel(R.drawable.stadium3_ma,"MA Chidambaram Stadium","Chennai Super Kings","Chennai, India","1916","50,000"));
        list.add( new VenuesModel(R.drawable.stadium4_firozshah,"Arun Jaitley Stadium","Delhi Capitals","Delhi, India","1883","41,820"));
        list.add( new VenuesModel(R.drawable.stadium5_rajibgrande,"Rajiv Gandhi Int. Stadium","Sunrisers Hyderabad","Hyderabad, India","2004","55000"));
        list.add( new VenuesModel(R.drawable.stadium6_holkar,"Holkar Cricket Stadium","Kings XI Punjab","Indore, India","1990","30,000"));
        list.add( new VenuesModel(R.drawable.stadium7_sawai,"Sawai Mansingh Stadium","Rajasthan Royals","Jaipur, India","1969","30,000"));
        list.add( new VenuesModel(R.drawable.stadium8_edengaden,"Eden Gardens Stadium","Kolkata Knight Riders","Kolkata, India","1864","90,000"));
        list.add( new VenuesModel(R.drawable.stadium9_isbindra,"IS Bindra Stadium","Kings XI Punjab","Mohali, India","1993","26,000"));
        list.add( new VenuesModel(R.drawable.stadium10_maharastha,"MCA Stadium","Rising Pune Supergiant","Pune, India","2012","37,406"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_venues, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.veneusRecylerView);
        adapter = new VeneusAdapter(getContext(),list);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        mAdView = v.findViewById(R.id.adView_venues);
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
        LinearLayout adContainer = (LinearLayout)v. findViewById(R.id.banner_container4);

        // Add the ad view to your activity layout
        adContainer.addView(adView);
        adView.loadAd();*/
        return v;
    }@Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setTitle("Venues");

        }
    }

}
