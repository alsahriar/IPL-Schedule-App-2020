package com.softtechbd.iplschedule2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    MyApplication myApplication;

    private int a=0;
    public static int SPLASH_TIME_OUT=3100;
    private LinearLayout layout1,layout2;
    private MediaPlayer mdeiaplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        layout1 = (LinearLayout)findViewById(R.id.layout1);
        layout2 = (LinearLayout)findViewById(R.id.layout2);
        layout1.setAnimation(AnimationUtils.loadAnimation(this,R.anim.uptodown));
        layout2.setAnimation(AnimationUtils.loadAnimation(this,R.anim.downtoup));
        TextView versionName = (TextView)findViewById(R.id.versionName);
        versionName.setText("Version: "+BuildConfig.VERSION_NAME);
        myApplication = MyApplication.getmInstant();

        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        new CountDownTimer(2000, 250) {
            @Override
            public void onTick(long millisUntilFinished) {
                a=a+1;
//                progressBar.setProgress(a);
            }

            @Override
            public void onFinish() {
                Intent intent= new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }.start();
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent= new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        },SPLASH_TIME_OUT);*/
    }
}
