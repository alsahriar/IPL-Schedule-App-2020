package com.softtechbd.iplschedule2020.Fragment;


import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.softtechbd.iplschedule2020.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Privacy_Policy extends Fragment {
    private WebView webView;

    public Privacy_Policy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_privacy__policy,container,false);
        webView=(WebView)v.findViewById(R.id.webViewID);
        webView.loadUrl("file:///android_asset/privacy_policy.html");

        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setTitle("Privacy and Policy");

        }

    }

}
