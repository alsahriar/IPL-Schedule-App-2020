package com.softtechbd.iplschedule2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.softtechbd.iplschedule2020.Extra_code.RemoteConfig_Result;
import com.softtechbd.iplschedule2020.Extra_code.Social_code;
import com.softtechbd.iplschedule2020.Fragment.Home;
import com.softtechbd.iplschedule2020.Fragment.LiveScore;
import com.softtechbd.iplschedule2020.Fragment.Play_Crickert;
import com.softtechbd.iplschedule2020.Fragment.PointTable;
import com.softtechbd.iplschedule2020.Fragment.Privacy_Policy;
import com.softtechbd.iplschedule2020.Fragment.Records;
import com.softtechbd.iplschedule2020.Fragment.SquadList;
import com.softtechbd.iplschedule2020.Fragment.Team;
import com.softtechbd.iplschedule2020.Fragment.Venues;
import com.softtechbd.iplschedule2020.Fragment.WinnerList;
import com.softtechbd.iplschedule2020.Fragment.about;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,FragmentManager.OnBackStackChangedListener   {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    GridLayout gridLayout;
    FirebaseRemoteConfig mfirebaseRemoteConfig;
    private FirebaseAnalytics mFirebaseAnalytics;
    CountDownTimer countDownTimer;
    private boolean mTimerRunning=false;
    boolean firstStart;
    private FrameLayout fragmentContainer;
    private long backPressedTime;
    private Toast backToast;
    private String stringFeedback;
    private LinearLayout Banner_layout;
    private com.google.android.gms.ads.InterstitialAd admobInt;
    boolean connected= false;
    private int AD_COUNT = 0;
    private int AD_COUNT2 = 0;
    private Fragment selectedFragment=null;
    Fragment selected_frag = null;
    Social_code social_code;
    RemoteConfig_Result config_result;

    public Fragment getCurrentFragment() {
        return this.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=(DrawerLayout)findViewById(R.id.drwerID);
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navigationView =(NavigationView)findViewById(R.id.navigationID);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        mfirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        social_code=new Social_code(MainActivity.this);
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        config_result= new RemoteConfig_Result(MainActivity.this);

        SharedPreferences sharedPreferences = getSharedPreferences("team_item_count",MODE_PRIVATE);
        AD_COUNT= sharedPreferences.getInt("count_key",0);

//        AudienceNetworkAds.initialize(this);
        MobileAds.initialize(this,getResources().getString(R.string.admobAppID));
        admobInt = new com.google.android.gms.ads.InterstitialAd(this);
        admobInt.setAdUnitId(getResources().getString(R.string.admobIntAdsID));
        LoadInt();

        admobInt.setAdListener(new com.google.android.gms.ads.AdListener(){
            @Override
            public void onAdClosed() {
                LoadInt();
            }
        });

        long cacheExpiration = 0;
        mfirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
//                            Toast.makeText(MainActivity.this, "Fetch Succeeded",
//                                    Toast.LENGTH_SHORT).show();

                            mfirebaseRemoteConfig.activateFetched();
                        }
                        config_result.displayWelcomeMessage();
                    }
                });


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Home()).commit();
//            navigationView.setCheckedItem(R.id.homeId);
        }


    }


    private void LoadInt() {
        if (config_result.isAdEnabale()){
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("0E055679FF1AD081660BA48B7139264F").build();
            admobInt.loadAd(adRequest);
        }

    }

    @Override
    protected void onDestroy() {
        /*if (adView != null) {
            adView.destroy();
        } if (interstitialAd != null) {
            interstitialAd.destroy();
        }*/
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void countTimer(){
        mTimerRunning=true;
        Toast.makeText(MainActivity.this,"Time started",Toast.LENGTH_LONG).show();
        countDownTimer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
            }
        }.start();

    }

    private void checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if(Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)).getState() == NetworkInfo.State.CONNECTED ||
                    Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)).getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
            else
                connected = false;
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean check=false;
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        if (item.getItemId()==R.id.action_aboutus){
            selected_frag=new about();
        }
        else if (item.getItemId()==R.id.action_rating){
            check=true;
            social_code.Rating_method();
        }
        else if (item.getItemId()==R.id.action_feedback){
            check=true;
            social_code.submit_feedback();

        }
        else if (item.getItemId()==R.id.action_privacypolicy){
            selected_frag=new Privacy_Policy();
        }
        if(!check){
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                    .replace(R.id.fragment_container,selected_frag).addToBackStack(null).commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        boolean check = true;

        if(menuItem.getItemId()==R.id.homeId){
            selected_frag=new Home();
        }
        else if(menuItem.getItemId()==R.id.scheduleId){
            check=false;
            startActivity( new Intent(MainActivity.this,ScheduleList.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        else if(menuItem.getItemId()==R.id.playerlistId){
            selected_frag=new Team();
        }
        else if(menuItem.getItemId()==R.id.LivescoreId){
            selected_frag=new LiveScore();
        }
        else if(menuItem.getItemId()==R.id.winnerID){
            selected_frag=new WinnerList();
        }
        else if(menuItem.getItemId()==R.id.point_tableId){
            selected_frag=new PointTable();
        }
        else if(menuItem.getItemId()==R.id.playquizId){
            selected_frag=new Venues();
        }else if (menuItem.getItemId()==R.id.record_corner){
            selected_frag=new Records();
        }else if (menuItem.getItemId()==R.id.play_crickert){
            selected_frag=new Play_Crickert();
        }
        else if(menuItem.getItemId()==R.id.shareId){
            check=false;

            social_code.Share_method();
        }
        else if(menuItem.getItemId()==R.id.ratingId){
            check=false;
            social_code.Rating_method();

        }
        else if(menuItem.getItemId()==R.id.aboutUSId){
            selected_frag=new about();
        }
        else if(menuItem.getItemId()==R.id.feedbackId){
            check = false;
            social_code.submit_feedback();

        }
        else if(menuItem.getItemId()==R.id.privacyId){
            selected_frag= new Privacy_Policy();
        }

        if(check){
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                    .replace(R.id.fragment_container,selected_frag).addToBackStack(null).commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    Fragment fragment= null;

    public void showHome(){
        fragment = new Home();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Home()).commit();
    }
    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
        Fragment current = getCurrentFragment();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if(current instanceof Home){
            /*if(Banner_layout.getVisibility() == View.VISIBLE){
                Banner_layout.setVisibility(View.GONE);
            }*/
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                backToast.cancel();
                super.onBackPressed();
                return;
            } else {
                backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
                backToast.show();
            }
            backPressedTime = System.currentTimeMillis();
        }else if(current instanceof WinnerList){
            SharedPreferences sharedPreferences = getSharedPreferences("home_item_count",MODE_PRIVATE);
            AD_COUNT2= sharedPreferences.getInt("item_countKEY",0);
            if(AD_COUNT2>=3){
                if(admobInt.isLoaded()){
                    admobInt.show();
                }else LoadInt();
                /*if(interstitialAd.isAdLoaded()){
                    interstitialAd.show();
                }else interstitialAd.loadAd();*/
                AD_COUNT2=0;
                SharedPreferences sharedPreference = getSharedPreferences("home_item_count",MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreference.edit();
                editor1.putInt("item_countKEY",AD_COUNT2+1);
                editor1.apply();
                super.onBackPressed();
            }else{
                SharedPreferences sharedPreference = getSharedPreferences("home_item_count",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreference.edit();
                editor.putInt("item_countKEY",AD_COUNT2+1);
                editor.apply();
                super.onBackPressed();
            }
        }else if(current instanceof LiveScore){
            SharedPreferences sharedPreferences = getSharedPreferences("home_item_count",MODE_PRIVATE);
            AD_COUNT2= sharedPreferences.getInt("item_countKEY",0);
            if(AD_COUNT2>=3){
                if(admobInt.isLoaded()){
                    admobInt.show();
                }else LoadInt();
                /*if(interstitialAd.isAdLoaded()){
                    interstitialAd.show();
                }else interstitialAd.loadAd();*/
                AD_COUNT2=0;
                SharedPreferences sharedPreference = getSharedPreferences("home_item_count",MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreference.edit();
                editor1.putInt("item_countKEY",AD_COUNT2+1);
                editor1.apply();
                super.onBackPressed();
            }else{
                SharedPreferences sharedPreference = getSharedPreferences("home_item_count",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreference.edit();
                editor.putInt("item_countKEY",AD_COUNT2+1);
                editor.apply();
                super.onBackPressed();
            }
        }else if(current instanceof PointTable){
            SharedPreferences sharedPreferences = getSharedPreferences("home_item_count",MODE_PRIVATE);
            AD_COUNT2= sharedPreferences.getInt("item_countKEY",0);
            if(AD_COUNT2>=3){
                if(admobInt.isLoaded()){
                    admobInt.show();
                }else LoadInt();
                /*if(interstitialAd.isAdLoaded()){
                    interstitialAd.show();
                }else interstitialAd.loadAd();*/
                AD_COUNT2=0;
                SharedPreferences sharedPreference = getSharedPreferences("home_item_count",MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreference.edit();
                editor1.putInt("item_countKEY",AD_COUNT2+1);
                editor1.apply();
                super.onBackPressed();
            }else{
                SharedPreferences sharedPreference = getSharedPreferences("home_item_count",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreference.edit();
                editor.putInt("item_countKEY",AD_COUNT2+1);
                editor.apply();
                super.onBackPressed();
            }
        }else if(current instanceof Play_Crickert){
            SharedPreferences sharedPreferences = getSharedPreferences("home_item_count",MODE_PRIVATE);
            AD_COUNT2= sharedPreferences.getInt("item_countKEY",0);
            if(AD_COUNT2>=1){
                if(admobInt.isLoaded()){
                    admobInt.show();
                }else LoadInt();
                /*if(interstitialAd.isAdLoaded()){
                    interstitialAd.show();
                }else interstitialAd.loadAd();*/
                AD_COUNT2=0;
                SharedPreferences sharedPreference = getSharedPreferences("home_item_count",MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreference.edit();
                editor1.putInt("item_countKEY",AD_COUNT2+1);
                editor1.apply();
                super.onBackPressed();
            }else{
                SharedPreferences sharedPreference = getSharedPreferences("home_item_count",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreference.edit();
                editor.putInt("item_countKEY",AD_COUNT2+1);
                editor.apply();
                super.onBackPressed();
            }
        }else if(current instanceof Records){
            SharedPreferences sharedPreferences = getSharedPreferences("home_item_count",MODE_PRIVATE);
            AD_COUNT2= sharedPreferences.getInt("item_countKEY",0);
            if(AD_COUNT2>=3){
                if(admobInt.isLoaded()){
                    admobInt.show();
                }else LoadInt();
                /*if(interstitialAd.isAdLoaded()){
                    interstitialAd.show();
                }else interstitialAd.loadAd();*/
                AD_COUNT2=0;
                SharedPreferences sharedPreference = getSharedPreferences("home_item_count",MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreference.edit();
                editor1.putInt("item_countKEY",AD_COUNT2+1);
                editor1.apply();
                super.onBackPressed();
            }else{
                SharedPreferences sharedPreference = getSharedPreferences("home_item_count",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreference.edit();
                editor.putInt("item_countKEY",AD_COUNT2+1);
                editor.apply();
                super.onBackPressed();
            }
        }else if(current instanceof Venues){
            SharedPreferences sharedPreferences = getSharedPreferences("home_item_count",MODE_PRIVATE);
            AD_COUNT2= sharedPreferences.getInt("item_countKEY",0);
            if(AD_COUNT2>=3){
                if(admobInt.isLoaded()){
                    admobInt.show();
                }else LoadInt();
                /*if(interstitialAd.isAdLoaded()){
                    interstitialAd.show();
                }else interstitialAd.loadAd();*/
                AD_COUNT2=0;
                SharedPreferences sharedPreference = getSharedPreferences("home_item_count",MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreference.edit();
                editor1.putInt("item_countKEY",AD_COUNT2+1);
                editor1.apply();
                super.onBackPressed();
            }else{
                SharedPreferences sharedPreference = getSharedPreferences("home_item_count",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreference.edit();
                editor.putInt("item_countKEY",AD_COUNT2+1);
                editor.apply();
                super.onBackPressed();
            }
        }
        else if(current instanceof SquadList){
            checkSquad();

        }else {
            super.onBackPressed();
        }

    }
    public void  rateus(){
        int rating_count ;
        SharedPreferences preferences = getSharedPreferences("rating_dialog",MODE_PRIVATE);
        rating_count = preferences.getInt("rating_count",0);
        if (rating_count>=10){
            rating_count = 0;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("rating_count",rating_count);
            editor.apply();
            new AlertDialog.Builder(this).setTitle("Rate us").
                    setMessage("Please, give us your feedback to improve our app user experience")
                    .setPositiveButton("Rate now", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            social_code.Rating_method();
                        }
                    }).setNeutralButton("Alredy i gave", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).setNegativeButton("Later", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).create().show();
        }else {
            rating_count = rating_count+1;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("rating_count",rating_count);
            editor.apply();
        }
    }

    private void checkSquad() {
        SharedPreferences sharedPreferences = getSharedPreferences("team_item_count",MODE_PRIVATE);
        AD_COUNT= sharedPreferences.getInt("count_key",0);
        if(AD_COUNT>=3){
            if(admobInt.isLoaded()){
                admobInt.show();
            }else LoadInt();
            /*if(interstitialAd.isAdLoaded()){
                interstitialAd.show();
            }else interstitialAd.loadAd();*/

            AD_COUNT=0;
            SharedPreferences sharedPreference = getSharedPreferences("team_item_count",MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedPreference.edit();
            editor1.putInt("count_key",AD_COUNT+1);
            editor1.apply();
            super.onBackPressed();
        }
        else{
            SharedPreferences sharedPreference = getSharedPreferences("team_item_count",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreference.edit();
            editor.putInt("count_key",AD_COUNT+1);
            editor.apply();
            super.onBackPressed();
        }
    }

    FragmentManager fragmentManager=getSupportFragmentManager();


    @Override
    public void onBackStackChanged() {

        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
        currentFragment.onResume();
        Fragment current = getCurrentFragment();
        /*if(current instanceof Team){
            interstitialAd.show();
        }*/
    }
}
