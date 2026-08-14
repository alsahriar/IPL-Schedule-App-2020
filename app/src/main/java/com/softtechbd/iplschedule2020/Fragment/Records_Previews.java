package com.softtechbd.iplschedule2020.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.softtechbd.iplschedule2020.Adapter.Records_Previews_Adpt;
import com.softtechbd.iplschedule2020.Extra_code.RemoteConfig_Result;
import com.softtechbd.iplschedule2020.Model.Records_Previews_M;
import com.softtechbd.iplschedule2020.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Records_Previews extends Fragment {
    View view;
    List<Records_Previews_M> list;
    RecyclerView recyclerView;
    Records_Previews_Adpt adapter;
    private String argsName;
    private AdView mAdView;

    public Records_Previews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_records__previews,container,false);
        if(getArguments()!=null) {
            argsName = getArguments().getString("Record Name");
            assert argsName != null;
            switch (argsName) {
                case "Most Run":
                    addMost_runList();
                    break;
                case "Most Sixes":
                    addMost_SixList();
                    break;
                case "Highest Scores":
                    addHighest_ScoreList();
                    break;
                case "Best Batting Strike Rate":
                    addBatting_StrikeData();
                    break;
                case "Most Fifties":
                    addMost_FiftiesList();
                    break;
                case "Most Centuries":
                    addMost_CenturyList();
                    break;
                case "Fastest Fifties":
                    addFastest_Fifties();
                    break;
                case "Fastest Centuries":
                    addFastest_Centuries();
                    break;
                case "Most Wickets":
                    addMost_WktList();
                    break;
                case "Best Bowling Economy":
                    addBestBowl_EcoList();
                    break;
                default:
                    addBestBowl_InnList();
                    break;
            }
            TextView textView=(TextView)view.findViewById(R.id.recordName_txt);
            textView.setText(argsName);
        }

        recyclerView=(RecyclerView)view.findViewById(R.id.record_p_RecylerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter= new Records_Previews_Adpt(getContext(),list);
        recyclerView.setAdapter(adapter);
        mAdView = view.findViewById(R.id.adView_records_previews);
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
        return view;
    }


    private void addBestBowl_InnList() {
        list = new ArrayList<>();
        list.add(new Records_Previews_M("01","Alzarri Joseph","12/6"));
        list.add(new Records_Previews_M("02","Sohail Tanvir","14/6"));
        list.add(new Records_Previews_M("03"," Adam Zampa ","19/6"));
        list.add(new Records_Previews_M("04","Anil Kumble","5/5"));
        list.add(new Records_Previews_M("05","Ishant Sharma","12/5"));
        list.add(new Records_Previews_M("06","Lasith Malinga","13/5"));
        list.add(new Records_Previews_M("07","Ankit Rajpoot","14/5"));
        list.add(new Records_Previews_M("08","James Faulkner","16/5"));
        list.add(new Records_Previews_M("09","Ravindra Jadeja","16/5"));
        list.add(new Records_Previews_M("10","Amit Mishra","17/5"));
    }

    private void addBestBowl_EcoList() {
        list = new ArrayList<>();
        list.add(new Records_Previews_M("01","Rashid Khan","6.55"));
        list.add(new Records_Previews_M("02","Anil Kumble","6.57"));
        list.add(new Records_Previews_M("03","Glenn McGrath","6.61"));
        list.add(new Records_Previews_M("04","Sunil Narine","6.67"));
        list.add(new Records_Previews_M("05","Muttiah Muralitharan","6.67"));
        list.add(new Records_Previews_M("06","Roelof van der Merwe","6.74"));
        list.add(new Records_Previews_M("07","Dale Steyn","6.76"));
        list.add(new Records_Previews_M("08","Rahul Chahar","6.77"));
        list.add(new Records_Previews_M("09","Daniel Vettori","6.78"));
        list.add(new Records_Previews_M("10","Ravichandran Ashwin","6.79"));
    }

    private void addMost_WktList() {
        list = new ArrayList<>();
        list.add(new Records_Previews_M("01","Lasith Malinga","170 Wkt"));
        list.add(new Records_Previews_M("02","Amit Mishra","157 Wkt"));
        list.add(new Records_Previews_M("03","Harbhajan Singh","150 Wkt"));
        list.add(new Records_Previews_M("04","Piyush Chawla","150 Wkt"));
        list.add(new Records_Previews_M("05","Dwayne Bravo","147 Wkt"));
        list.add(new Records_Previews_M("06","Bhuvneshwar Kumar","133 Wkt"));
        list.add(new Records_Previews_M("07","Ravichandran Ashwin","125 Wkt"));
        list.add(new Records_Previews_M("08","Sunil Narine","122 Wkt"));
        list.add(new Records_Previews_M("09","Umesh Yadav","119 Wkt"));
        list.add(new Records_Previews_M("10","Ravindra Jadeja","108 Wkt"));
    }

    private void addFastest_Centuries() {
        list = new ArrayList<>();
        list.add(new Records_Previews_M("01","Chris Gayle","30 Bowl"));
        list.add(new Records_Previews_M("02","Yusuf Pathan","37 Bowl"));
        list.add(new Records_Previews_M("03","David Miller","38 Bowl"));
        list.add(new Records_Previews_M("04","Adam Gilchrist","42 Bowl"));
        list.add(new Records_Previews_M("05","AB de Villiers","43 Bowl"));
        list.add(new Records_Previews_M("06","David Warner","43 Bowl"));
        list.add(new Records_Previews_M("07","Sanath Jayasuriya","45 Bowl"));
        list.add(new Records_Previews_M("08","Murali Vijay","46 Bowl"));
        list.add(new Records_Previews_M("09","Chris Gayle","46 Bowl"));
        list.add(new Records_Previews_M("10","Chris Gayle","46 Bowl"));
    }

    private void addFastest_Fifties() {
        list = new ArrayList<>();
        list.add(new Records_Previews_M("01","KL Rahul","14 Bowl"));
        list.add(new Records_Previews_M("02","Yusuf Pathan","15 Bowl"));
        list.add(new Records_Previews_M("03","Sunil Narine","15 Bowl"));
        list.add(new Records_Previews_M("04","Suresh Raina","16 Bowl"));
        list.add(new Records_Previews_M("05","Chris Gayle","17 Bowl"));
        list.add(new Records_Previews_M("06","Hardik Pandya","17 Bowl"));
        list.add(new Records_Previews_M("07","Adam Gilchrist","17 Bowl"));
        list.add(new Records_Previews_M("08","Chris Morris","17 Bowl"));
        list.add(new Records_Previews_M("09","Ishan Kishan","17 Bowl"));
        list.add(new Records_Previews_M("10","Kieron Pollard","17 Bowl"));
    }

    private void addMost_CenturyList() {
        list = new ArrayList<>();
        list.add(new Records_Previews_M("01","Chris Gayle","6"));
        list.add(new Records_Previews_M("02","Virat Kohli","5"));
        list.add(new Records_Previews_M("03","David Warner","4"));
        list.add(new Records_Previews_M("04","Shane Watson","4"));
        list.add(new Records_Previews_M("05","AB de Villiers","3"));
        list.add(new Records_Previews_M("06","Ajinkya Rahane","2"));
        list.add(new Records_Previews_M("07","Brendon McCullum","2"));
        list.add(new Records_Previews_M("08","Virender Sehwag","2"));
        list.add(new Records_Previews_M("09","Murali Vijay","2"));
        list.add(new Records_Previews_M("10","Sanju Samson","2"));
    }

    private void addMost_FiftiesList() {
        list = new ArrayList<>();
        list.add(new Records_Previews_M("01","David Warner","44"));
        list.add(new Records_Previews_M("02","Suresh Raina","38"));
        list.add(new Records_Previews_M("03","Shikhar Dhawan","37"));
        list.add(new Records_Previews_M("04","Virat Kohli","36"));
        list.add(new Records_Previews_M("05","Rohit Sharma","36"));
        list.add(new Records_Previews_M("06","Gautam Gambhir","36"));
        list.add(new Records_Previews_M("07","AB de Villiers","33"));
        list.add(new Records_Previews_M("08","Chris Gayle","28"));
        list.add(new Records_Previews_M("09","Ajinkya Rahane","27"));
        list.add(new Records_Previews_M("10","Robin Uthappa","24"));
    }

    private void addBatting_StrikeData() {
        list = new ArrayList<>();
        list.add(new Records_Previews_M("01","Andre Russell","186.41"));
        list.add(new Records_Previews_M("02","Sunil Narine","168.34"));
        list.add(new Records_Previews_M("03","Moeen Ali","165.92 *"));
        list.add(new Records_Previews_M("04","Rishabh Pant","162.69*"));
        list.add(new Records_Previews_M("05","Glenn Maxwell","161.13 "));
        list.add(new Records_Previews_M("06","Chris Morris","157.62"));
        list.add(new Records_Previews_M("07","Jonny Bairstow","157.24"));
        list.add(new Records_Previews_M("08","Virender Sehwag","155.44*"));
        list.add(new Records_Previews_M("09","Hardik Pandya","154.78*"));
        list.add(new Records_Previews_M("10","AB de Villiers","151.23"));
    }

    private void addHighest_ScoreList() {
        list = new ArrayList<>();
        list.add(new Records_Previews_M("01","Chris Gayle","175*"));
        list.add(new Records_Previews_M("02","Brendon McCullum","158*"));
        list.add(new Records_Previews_M("03","AB de Villiers","133*"));
        list.add(new Records_Previews_M("04","Rishabh Pant","128*"));
        list.add(new Records_Previews_M("05","Murali Vijay","127"));
        list.add(new Records_Previews_M("06","David Warner","126"));
        list.add(new Records_Previews_M("07","Virender Sehwag","122"));
        list.add(new Records_Previews_M("08","Paul Valthaty","120*"));
        list.add(new Records_Previews_M("09","Shane Watson","117*"));
        list.add(new Records_Previews_M("10","Andrew Symonds","117"));
    }

    private void addMost_SixList() {
        list = new ArrayList<>();
        list.add(new Records_Previews_M("01","Chris Gayle","326"));
        list.add(new Records_Previews_M("02","AB de Villiers","212"));
        list.add(new Records_Previews_M("03","MS Dhoni","209"));
        list.add(new Records_Previews_M("04","Rohit Sharma","194"));
        list.add(new Records_Previews_M("05","Suresh Raina","194"));
        list.add(new Records_Previews_M("06","Virat Kohli","191"));
        list.add(new Records_Previews_M("07","David Warner","181"));
        list.add(new Records_Previews_M("08","Shane Watson","177"));
        list.add(new Records_Previews_M("09","Kieron Pollard","176"));
        list.add(new Records_Previews_M("10","Yusuf Pathan","158"));
    }

    private void addMost_runList() {
        list = new ArrayList<>();
        list.add(new Records_Previews_M("01","Virat Kholi","5412 Run"));
        list.add(new Records_Previews_M("02","Suresh Raina","5368 Run"));
        list.add(new Records_Previews_M("03","Rohit Sharma","4898 Run"));
        list.add(new Records_Previews_M("04","David Warner ","4706 Run"));
        list.add(new Records_Previews_M("05","Shikhar Dhawan","4579 Run"));
        list.add(new Records_Previews_M("06","Chris Gayle ","4484 Run"));
        list.add(new Records_Previews_M("07","MS Dhoni","4432 Run"));
        list.add(new Records_Previews_M("08","Robin Uthappa","4411 Run"));
        list.add(new Records_Previews_M("09","AB de Villiers","4395 Run"));
        list.add(new Records_Previews_M("10","Gautam Gambhir","4217 Run"));
    }

}
