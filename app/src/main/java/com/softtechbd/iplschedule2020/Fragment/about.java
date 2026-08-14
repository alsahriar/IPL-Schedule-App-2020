package com.softtechbd.iplschedule2020.Fragment;


import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.softtechbd.iplschedule2020.BuildConfig;
import com.softtechbd.iplschedule2020.R;


public class about extends Fragment  {
    Button instaBtn;
    View v;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public about() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);
        TextView versionName =(TextView)v.findViewById(R.id.versionTxtID);
        versionName.setText("Version: "+BuildConfig.VERSION_NAME);
        instaBtn =(Button) v.findViewById(R.id.instagram);
        instaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instagram();
            }
        });
        return v ;
    }
    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setTitle("About us");

        }
    }
    public void instagram (){
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.instagram.com/al_sahriarofficial/"));
        intent.setPackage("com.instagram.android");
        try {
            startActivity(intent);
        }catch (ActivityNotFoundException notfound){
            startActivity(new Intent("android.intent.action.VIEW",Uri.parse("https://www.instagram.com/al_sahriarofficial/")));
        }
    }
}
