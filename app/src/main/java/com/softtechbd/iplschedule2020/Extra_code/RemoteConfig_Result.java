package com.softtechbd.iplschedule2020.Extra_code;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import static android.content.Context.MODE_PRIVATE;

public class RemoteConfig_Result {
    Activity activity;
    FirebaseRemoteConfig mfirebaseRemoteConfig;
    boolean firstStart;

    public boolean isAdEnabale(){
        mfirebaseRemoteConfig=FirebaseRemoteConfig.getInstance();
        return mfirebaseRemoteConfig.getBoolean("isAd_Enable");
    }
    public void displayWelcomeMessage() {
        mfirebaseRemoteConfig=FirebaseRemoteConfig.getInstance();
        SharedPreferences prefs = activity.getSharedPreferences("prefs",MODE_PRIVATE);
        firstStart = prefs.getBoolean("firstStart",true);

        Context context = activity.getApplicationContext(); // or activity.getApplicationContext()
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();

        String myVersionName = "1.0"; // initialize String

        try {
            myVersionName = packageManager.getPackageInfo(packageName, 0).versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        boolean mustUpdate = mfirebaseRemoteConfig.getBoolean("IPL_MHT_Update");
        String currentversion = mfirebaseRemoteConfig.getString("IPL_VERSION");
        if (!myVersionName.equals(currentversion) ){
            if (mustUpdate){
                prefs = activity.getSharedPreferences("prefs",MODE_PRIVATE);
                prefs.edit().remove("firstStart").apply();
                new AlertDialog.Builder(activity).setTitle("Application update available").setMessage("Please update the latest version of this application to use continue")
                        .setCancelable(false).setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try{
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("market://details?id=");
                            stringBuilder.append(activity.getPackageName());
                            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())));

                        }catch (ActivityNotFoundException activityNotFoundException){
                            Toast.makeText(activity,"Couldn't launch data",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                }).create().show();
            }else{
                if (firstStart) {
                    new AlertDialog.Builder(activity).setTitle("Application update available").setMessage("Please update the latest version of this application to use continue")
                            .setCancelable(true).setPositiveButton("Update", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try{
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("market://details?id=");
                                stringBuilder.append(activity.getPackageName());
                                activity.startActivity(new Intent("android.intent.action.VIEW",Uri.parse(stringBuilder.toString())));

                            }catch (ActivityNotFoundException activityNotFoundException){
                                Toast.makeText(activity,"Failed to load data",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setNeutralButton("Don't Ask Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences prefs = activity.getSharedPreferences("prefs",MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean("firstStart",false);
                            editor.apply();
                        }
                    }).setNegativeButton("Later", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
                }

            }

        }else {

        }

    }


    public RemoteConfig_Result(Activity activity) {
        this.activity = activity;
    }

    public RemoteConfig_Result() {
    }

}
