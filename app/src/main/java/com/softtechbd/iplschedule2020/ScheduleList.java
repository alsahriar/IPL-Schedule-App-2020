package com.softtechbd.iplschedule2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import com.futuremind.recyclerviewfastscroll.FastScroller;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.softtechbd.iplschedule2020.Adapter.ScheduleAdapter;
import com.softtechbd.iplschedule2020.Extra_code.RemoteConfig_Result;
import com.softtechbd.iplschedule2020.Model.ModelClass1;

import java.util.ArrayList;
import java.util.List;

public class ScheduleList extends AppCompatActivity {
    private ScheduleAdapter adapter;
    private List<ModelClass1> scheduleList;
    private AdView adView;
    private com.google.android.gms.ads.AdView mAdView;
    private InterstitialAd admobInt;
    FloatingActionButton fab;
    RecyclerView recyclerView;

//    private InterstitialAd interstitialAd;


    public ScheduleList() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);
        this.setTitle("Schedule");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        addlist();
        setUpRecylerView();

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.hide();
        MobileAds.initialize(this,getResources().getString(R.string.admobAppID));
        adView = findViewById(R.id.adView_schedule);

        admobInt = new com.google.android.gms.ads.InterstitialAd(this);
        admobInt.setAdUnitId(getResources().getString(R.string.admobIntAdsID));


        RemoteConfig_Result result = new RemoteConfig_Result();
        if (result.isAdEnabale()){
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("0E055679FF1AD081660BA48B7139264F").build();
            adView.loadAd(adRequest);
            admobInt.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("0E055679FF1AD081660BA48B7139264F").build());
        }
        adView.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
                adapter.notifyDataSetChanged();
                fab.hide();

            }
        });
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (adView.getVisibility() == View.GONE){
                    adView.setVisibility(View.VISIBLE);
                }else {
                    adView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });



        /*AudienceNetworkAds.initialize(this);
        adView = new AdView(this,getResources().getString(R.string.ban_placementID), AdSize.BANNER_HEIGHT_50);
//        interstitialAd = new InterstitialAd(this,getResources().getString(R.string.int_placementID));
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container2);

        // Add the ad view to your activity layout
        adContainer.addView(adView);
        adView.loadAd();*/
//        interstitialAd.loadAd();
        /*new AlertDialog.Builder(ScheduleList.this).setTitle("Update Notice!").
                setMessage("The IPL 2020 Schedule has not yet been announced.IPL Governing Council (IGC) meeting soon to take final decision on the IPL 2020 dates. Schedule will be updated when BCCI is released")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();*/

    }

    @Override
    public void finish() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        super.finish();
        /*if(interstitialAd.isAdLoaded()){
            interstitialAd.show();
        }*/
        if(admobInt.isLoaded()){
            admobInt.show();
        }else
            Log.d("TAG","The interstitial wasn't loaded yet");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_option,menu);

        MenuItem searchitem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchitem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    public void addlist(){
        scheduleList = new ArrayList<>();
        scheduleList.add(new ModelClass1("1st Match",R.drawable.micon,R.drawable.cskicon,"Vanule: Wankhede Stadium, Mumbai, Maharashtra.","29 March 2020, Sunday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("2nd Match",R.drawable.dcicon,R.drawable.kxiiicon,"Vanule: Arun Jaitley Stadium, Dhelhi.","30 March 2020, Monday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("3rd Match",R.drawable.rcbion,R.drawable.kkricon,"Vanule: M.Chinnaswamy Stadium, Bengaluru, Karnataka.","31 March 2020, Tuesday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("4th Match",R.drawable.srhicon,R.drawable.micon,"Vanule: RGI Stadium, Hyderabad, Telangana.","1 April 2020, Wednesday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("5th Match",R.drawable.cskicon,R.drawable.rricon,"Vanule: M.A.Chidambaram Stadium, Chennai, Tamilnadu.","2 April 2020, Thursday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("6th Match",R.drawable.kkricon,R.drawable.dcicon,"Vanule: Eden Gardens Stadium, Kolkata, WB.","3 April 2020, Friday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("7th Match",R.drawable.kxiiicon,R.drawable.srhicon,"Vanule: PCA Stadium, Mohali, Punjab.","4 April 2020, Saturday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("8th Match",R.drawable.micon,R.drawable.rcbion,"Vanule: Wankhede Stadium, Mumbai, Maharashtra.","5 April 2020, Sunday - 4:00 PM TST"));
        scheduleList.add(new ModelClass1("9th Match",R.drawable.rricon,R.drawable.dcicon,"Vanule: Jaipur/ Guwahati","5 April 2020, Sunday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("10th Match",R.drawable.kkricon,R.drawable.cskicon,"Vanule: Eden Gardens Stadium, Kolkata.","6 April 2020, Monday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("11th Match",R.drawable.rcbion,R.drawable.srhicon,"Vanule: M.Chinnaswamy Stadium, Bangalore, Karnataka.","7 April 2020, Tuesday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("12th Match",R.drawable.kxiiicon,R.drawable.micon,"Vanule: PCA Stadium, Mohali, Punjab., Tamilnadu.","8 April 2020, Wednesday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("13th Match",R.drawable.rricon,R.drawable.kkricon,"Vanule: Jaipur/ Guwahati","9 April 2020, Thursday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("14th Match",R.drawable.dcicon,R.drawable.rcbion,"Vanule: Arun Jaitley Stadium, Dhelhi.","10 April 2020, Frisday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("15th Match",R.drawable.cskicon,R.drawable.kxiiicon,"Vanule: M.A.Chidambaram Chepauk Stadium, Chennai, Tamilnadu.","11 April 2020, Saturday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("16th Match",R.drawable.srhicon,R.drawable.rricon,"Vanule: RGI Stadium, Hyderabad, Telangana.","12 April 2020, Sunday - 4:00 PM IST"));
        scheduleList.add(new ModelClass1("17th Match",R.drawable.kkricon,R.drawable.micon,"Vanule: Eden Gardens Stadium, Kolkata, WB.","12 April 2020, Sunday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("18th Match",R.drawable.dcicon,R.drawable.cskicon,"Vanule: Arun Jaitley Stadium, Dhelhi.","13 April 2020, Monday, 8:00 PM IST"));
        scheduleList.add(new ModelClass1("19th Match",R.drawable.kxiiicon,R.drawable.rcbion,"Vanule: PCA Stadium, Mohali, Punjab.","14 April 2020, Tuesday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("20th Match",R.drawable.micon,R.drawable.rricon,"Vanule: Wankhede Stadium, Mumbai, Maharashtra.","15 April 2020, Wednesday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("21st Match",R.drawable.srhicon,R.drawable.kkricon,"Vanule: RGI Stadium, Hyderabad, Telangana.","16 April 2020, Thursday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("22th Match",R.drawable.kxiiicon,R.drawable.cskicon,"Vanule: PCA Stadium, Mohali, Punjab.","17 April 2020, Friday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("23rd Match",R.drawable.rcbion,R.drawable.rricon,"Vanule: M.Chinnaswamy Stadium, Bangalore, Karnataka.","18 April 2020, Saturday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("24h Match",R.drawable.dcicon,R.drawable.kkricon,"Vanule: Arun Jaitley Stadium, Dhelhi.","19 April 2020, Sunday - 4:00 PM IST"));
        scheduleList.add(new ModelClass1("25th Match",R.drawable.cskicon,R.drawable.srhicon,"Vanule: M.A.Chidambaram Chepauk Stadium, Chennai, Tamilnadu.","19 April 2020, Sunday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("26th Match",R.drawable.micon,R.drawable.kxiiicon,"Vanule: Wankhede Stadium, Mumbai, Maharashtra.","20 April 2020, Monday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("27th Match",R.drawable.rricon,R.drawable.srhicon,"Vanule: Sawai Mansingh Stadium, Jaipur, Rajasthan.","21 April 2020, Tuesday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("28th Match",R.drawable.rcbion,R.drawable.dcicon,"Vanule: M.Chinnaswamy Stadium, Bengaluru, Karnataka.","22 April 2020, Wednesday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("29th Match",R.drawable.kkricon,R.drawable.kxiiicon,"Vanule: Eden Gardens Stadium, Kolkata, WB.","23 April 2020, Thursday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("30st Match",R.drawable.cskicon,R.drawable.micon,"Vanule: M.A.Chidambaram Chepauk Stadium, Chennai, Tamilnadu.","24 April 2020, Friday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("31nd Match",R.drawable.rricon,R.drawable.kxiiicon,"Vanule: Sawai Mansingh Stadium, Jaipur, Rajasthan.","25 April 2020, Saturday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("32rd Match",R.drawable.kxiiicon,R.drawable.kkricon,"Vanule: PCA Stadium, Mohali, Punjab.","26 April 2020, Sunday - 4:00 PM IST"));
        scheduleList.add(new ModelClass1("33th Match",R.drawable.srhicon,R.drawable.dcicon,"Vanule: RGI Stadium, Hyderabad, Telangana.","26 April 2020, Sunday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("34th Match",R.drawable.cskicon,R.drawable.rcbion,"Vanule: M.A.Chidambaram Chepauk Stadium, Chennai, Tamilnadu.","27 April 2020,Monday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("35th Match",R.drawable.micon,R.drawable.kkricon,"Vanule: Wankhede Stadium, Mumbai, Maharashtra.","28 April 2020, Tuesday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("36th Match",R.drawable.rricon,R.drawable.kxiiicon,"Vanule: Sawai Mansingh Stadium, Jaipur, Rajasthan.","29 April 2020, Wednesday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("37th Match",R.drawable.srhicon,R.drawable.cskicon,"Vanule: R.G.I. Cricket Stadium, Hyderabad, Telangana.","30 April 2020, Thursday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("38th Match",R.drawable.micon,R.drawable.dcicon,"Vanule: Wankhede Stadium, Mumbai, Maharashtra.","1 May 2020, Friday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("39th Match",R.drawable.kkricon,R.drawable.rricon,"Vanule: Eden Gardens, Kolkata, West Bengal.","2 May 2020, Saturday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("40st Match",R.drawable.rcbion,R.drawable.kxiiicon,"Vanule: M.Chinnaswamy Stadium, Bangalore, Karnataka.","3 May 2020, Sunday - 4:00 PM IST"));
        scheduleList.add(new ModelClass1("41nd Match",R.drawable.dcicon,R.drawable.srhicon,"Vanule: Arun Jaitley Stadium, Dhelhi.","3 May 2020, Sunday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("42rd Match",R.drawable.rricon,R.drawable.cskicon,"Vanule: Sawai Mansingh Stadium, Jaipur, Rajasthan.","4 May 2020, Monday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("43th Match",R.drawable.srhicon,R.drawable.rcbion,"Vanule: R.G.I. Cricket Stadium, Hyderabad, Telangana.","5 May 2020, Tuesday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("44th Match",R.drawable.dcicon,R.drawable.micon,"Vanule: Arun Jaitley Stadium, Dhelhi.","6 May 2020, Wednesday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("45th Match",R.drawable.cskicon,R.drawable.kkricon,"Vanule: M.A.Chidambaram Chepauk Stadium, Chennai, Tamilnadu.","7 May 2020, Thursday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("46th Match",R.drawable.kxiiicon,R.drawable.rricon,"Vanule: PCA Stadium, Mohali, Punjab.","8 May 2020, Friday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("47th Match",R.drawable.micon,R.drawable.srhicon,"Vanule: Wankhede Stadium, Mumbai, Maharashtra.","9 May 2020, Saturday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("48th Match",R.drawable.cskicon,R.drawable.dcicon,"Vanule: M.A.Chidambaram Chepauk Stadium, Chennai, Tamilnadu.","10 May 2020, Sunday - 4:00 PM IST"));
        scheduleList.add(new ModelClass1("49th Match",R.drawable.kkricon,R.drawable.rcbion,"Vanule: Eden Gardens, Kolkata, West Bengal.","9 May 2020, Sunday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("50st Match",R.drawable.rricon,R.drawable.micon,"Vanule: Sawai Mansingh Stadium, Jaipur, Rajasthan.","11 May 2020, Monday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("51nd Match",R.drawable.srhicon,R.drawable.kxiiicon,"Vanule: R.G.I. Cricket Stadium, Hyderabad, Telangana.","12 May 2020, Tuesday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("52rd Match",R.drawable.dcicon,R.drawable.rricon,"Vanule: Arun Jaitley Stadium, Dhelhi.","13 May 2020, Wednesday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("53th Match",R.drawable.rcbion,R.drawable.cskicon,"Vanule: M.Chinnaswamy Stadium, Bengaluru, Karnataka.","14 May 2020, Thursday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("54th Match",R.drawable.kkricon,R.drawable.srhicon,"Vanule: Eden Gardens, Kolkata, West Bengal.","15 May 2020, Friday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("55th Match",R.drawable.kxiiicon,R.drawable.dcicon,"Vanule: PCA Stadium, Mohali, Punjab.","16 May 2020, Saturday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("56th Match",R.drawable.rcbion,R.drawable.micon,"Vanule: M.Chinnaswamy Stadium, Bengaluru, Karnataka.","17 May 2020, Sunday - 8:00 PM IST"));

        /*scheduleList.add(new ModelClass1("Qualifier-1",R.drawable.help,R.drawable.ic_help,"Vanule: Eden Gardens, Kolkata, West Bengal.","19 May 2020, Thursday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("Eliminator",R.drawable.ic_help,R.drawable.help,"Vanule: M.Chinnaswamy Stadium, Bengaluru, Karnataka.","20 May 2020, Friday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("Qualifier-2",R.drawable.help,R.drawable.ic_help,"Vanule: M.A.Chidambaram Chepauk Stadium, Chennai, Tamilnadu.","21 May 2020, Saturday - 8:00 PM IST"));
        scheduleList.add(new ModelClass1("FINAL",R.drawable.ic_help,R.drawable.help,"Vanule: Wankhede Stadium, Mumbai, Maharashtra.","24 May 2020, Sunday - 8:00 PM IST"));*/


        /*RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recylerviewID);
        ScheduleAdapter myadpter = new ScheduleAdapter(this,scheduleList);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadpter);*/
    }

    public void setUpRecylerView(){
        recyclerView = findViewById(R.id.recylerviewID);
        FastScroller fastScroller = (FastScroller) findViewById(R.id.fastscroll);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ScheduleAdapter(this,scheduleList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        fastScroller.setRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    fab.hide();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy<0){
                    ScheduleList.this.fab.show();
                }
                if(dy>0){
                    ScheduleList.this.fab.hide();
                }
                /*if (dy==recyclerView.getMeasuredHeight() - recyclerView.getChildAt(0).getMeasuredHeight()){
                    MainActivity.this.FAb.hide();
                }*/
                if(dy==0){
                    ScheduleList.this.fab.hide();
                }
            }
        });
    }

}
