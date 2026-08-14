package com.softtechbd.iplschedule2020.Fragment;


import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.softtechbd.iplschedule2020.Extra_code.RemoteConfig_Result;
import com.softtechbd.iplschedule2020.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Play_Crickert extends Fragment {
    View view;
    private WebView webView;
    AdView mAdView;


    public Play_Crickert() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play__crickert,container,false);

        webView=(WebView)view.findViewById(R.id.play_cricketWeb);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://doodlecricket.github.io/");

        mAdView = view.findViewById(R.id.adView_playCricket);
        RemoteConfig_Result result = new RemoteConfig_Result();
        if (result.isAdEnabale()){
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
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setTitle("Play Cricket");
        }
    }
}
