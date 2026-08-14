package com.softtechbd.iplschedule2020.Fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.se.omapi.SEService;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.material.snackbar.Snackbar;
import com.softtechbd.iplschedule2020.Extra_code.RemoteConfig_Result;
import com.softtechbd.iplschedule2020.R;

import java.util.Objects;


public final class LiveScore extends Fragment {


    private WebView mWeb;
    private View mContentView;
    private LinearLayout no_internetLayout;
    private ProgressBar progressBar;
    private Button retry_btn;
    ProgressDialog progressDialog;
    private AdView adView;
    private com.google.android.gms.ads.AdView mAdView;
    LinearLayout web_layout,progress;
    CoordinatorLayout coordinatorLayout;


    public LiveScore() {
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:{
                    webViewGoBack();
                }break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AudienceNetworkAds.initialize(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_live_score, null);
        mWeb = (WebView)mContentView.findViewById(R.id.webViewID);
        web_layout =(LinearLayout)mContentView.findViewById(R.id.webview_layout);
        no_internetLayout=(LinearLayout)mContentView.findViewById(R.id.no_internetlayout);
        progressBar=(ProgressBar)mContentView.findViewById(R.id.progressBar);
        progress=(LinearLayout) mContentView.findViewById(R.id.progress);
        retry_btn=(Button)mContentView.findViewById(R.id.retry_btn);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Connection");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        coordinatorLayout=(CoordinatorLayout) mContentView.findViewById(R.id.web_container);
        checkInternet();
        mAdView = mContentView.findViewById(R.id.adView_liveScore);
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
        /*adView = new AdView(getContext(),getResources().getString(R.string.ban_placementID), AdSize.BANNER_HEIGHT_50);



        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout)mContentView. findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);
        adView.loadAd();*/


        retry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInternet();
            }
        });


       return mContentView;
    }@Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setTitle("Live Score");
        }
    }


    public void checkInternet(){

        ConnectivityManager connectivityManager = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(getActivity().CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        if(Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)).getState() == NetworkInfo.State.CONNECTED ||
                Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            if(no_internetLayout.getVisibility()==View.VISIBLE){
                no_internetLayout.setVisibility(View.GONE);
                progress.setVisibility(View.VISIBLE);
                web_layout.setVisibility(View.VISIBLE);
            }

            WebSettings settings = mWeb.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setSupportZoom(false);
            mWeb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            mWeb.getSettings().setBuiltInZoomControls(false);
            mWeb.setWebViewClient(new WebViewClient());
            mWeb.loadUrl("https://www.google.com/search?client=firefox-b-d&q=cricket+live+score#sie=lg;/g/11hczz6b00;5;/m/021q23;mt;fp;1;;");

            mWeb.setWebChromeClient(new WebChromeClient(){
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                    if(newProgress==100){
                        progressBar.setVisibility(View.GONE);
                        progress.setVisibility(View.GONE);
                        mWeb.setVisibility(View.VISIBLE);

                    }
                    super.onProgressChanged(view, newProgress);
                }
            });
            mWeb.setOnKeyListener(new View.OnKeyListener(){

                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK
                            && event.getAction() == MotionEvent.ACTION_UP
                            && mWeb.canGoBack()) {
                        handler.sendEmptyMessage(1);
                        return true;
                    }

                    return false;
                }

            });
        }else {
            web_layout.setVisibility(View.GONE);
            progress.setVisibility(View.GONE);
            no_internetLayout.setVisibility(View.VISIBLE);


            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Internet connection failed", Snackbar.LENGTH_LONG).setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkInternet();
                        }
                    }).setActionTextColor(Color.YELLOW);

            snackbar.show();


        }
    }

    private void webViewGoBack(){
        mWeb.goBack();
    }
}
