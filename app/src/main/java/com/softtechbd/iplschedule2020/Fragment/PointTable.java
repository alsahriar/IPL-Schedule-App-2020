package com.softtechbd.iplschedule2020.Fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.ads.AdSize;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.softtechbd.iplschedule2020.Extra_code.RemoteConfig_Result;
import com.softtechbd.iplschedule2020.R;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class PointTable extends Fragment {
    /*private AdView adView;*/
    private AdView mAdView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudienceNetworkAds.initialize(Objects.requireNonNull(getActivity()));
    }

    public PointTable() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_point_table, container, false);
        /*adView = new AdView(getContext(),getResources().getString(R.string.ban_placementID), AdSize.BANNER_HEIGHT_50);



        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout)v. findViewById(R.id.banner_container3);

        // Add the ad view to your activity layout
        adContainer.addView(adView);
        adView.loadAd();*/
        mAdView =v.findViewById(R.id.adView_Point_table);
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
        return v;
    }@Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setTitle("Point Table");

        }
    }

}
