package com.softtechbd.iplschedule2020.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.softtechbd.iplschedule2020.Adapter.SquadAdapter;
import com.softtechbd.iplschedule2020.Extra_code.RemoteConfig_Result;
import com.softtechbd.iplschedule2020.Model.ModelClass2;
import com.softtechbd.iplschedule2020.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class SquadList extends Fragment {
//    private static final String Args = "Team";

    View view;
    Context mContext;
    private List<ModelClass2> list;
    private String TeamName="CSK";
    private Dialog alertDialog;
    private AdView mAdView;

    SquadAdapter adapterf = new SquadAdapter();

    private static final int ITEMS_PER_AD = 8;
    int Iteam_per_int =0;

   /* public static SquadList instence (String teamName){
        SquadList fragment = new SquadList();
        Bundle args = new Bundle();
        args.putString(Args,teamName);
        fragment.setArguments(args);
        return fragment;
    }*/

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    SquadList(Context mcontext) {
    }

    public SquadList() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_squad_list,container,false);

        CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.player_profile);
        ImageView teamicon = (ImageView) view.findViewById(R.id.teamicon);
        TextView teamname = (TextView) view.findViewById(R.id.teamname);
        TextView teamowner = (TextView) view.findViewById(R.id.teamowner);
        TextView teamcoach = (TextView) view.findViewById(R.id.teamcoach);
        TextView teamvanue = (TextView) view.findViewById(R.id.teamvanue);
        LinearLayout bglayout = (LinearLayout) view.findViewById(R.id.bgid);
        mAdView = view.findViewById(R.id.adView_squad);
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



        LinearLayout layoutbg = (LinearLayout)view.findViewById(R.id.layoutBG);
        if(getArguments()!=null){
            TeamName = getArguments().getString("Team");

            assert TeamName != null;
            switch (TeamName) {
                case "CSK":
                    bglayout.setBackgroundResource(R.drawable.bg_csk);
                    teamicon.setImageResource(R.drawable.cskicon);
                    teamname.setText("Chennai Super Kings");
                    teamowner.setText(teamowner.getText() + "Chennai Super Kings Cricket ltd.");
                    teamcoach.setText(teamcoach.getText() + "Stephen Fleming");
                    teamvanue.setText(teamvanue.getText() + "M. A. Chidambaram Stadium");
                    add_CSK_Data();
                    setUpRecylerView();

                    break;
                case "MI":
                    bglayout.setBackgroundResource(R.drawable.bg_mi);
                    teamicon.setImageResource(R.drawable.micon);
                    teamname.setText("Mumbai Indians");
                    teamowner.setText(teamowner.getText() + "Indiawin Sports Pvt. Ltd");
                    teamcoach.setText(teamcoach.getText() + "Mahela Jayawardene");
                    teamvanue.setText(teamvanue.getText() + "Wankhede Stadium");
                    add_Mumbai_Data();
                    setUpRecylerView();

                    break;
                case "KKR":
                    bglayout.setBackgroundResource(R.drawable.bg_kkr);
                    teamicon.setImageResource(R.drawable.kkricon);
                    teamname.setText("Kolkata Knight Riders");
                    teamowner.setText(teamowner.getText() + "Knight Riders Sports Private Ltd");
                    teamcoach.setText(teamcoach.getText() + "Jacques Kallis");
                    teamvanue.setText(teamvanue.getText() + "Eden Gardens");
                    add_KKR_Data();
                    setUpRecylerView();

                    break;
                case "SRH":
                    bglayout.setBackgroundResource(R.drawable.bg_srh);
                    teamicon.setImageResource(R.drawable.srhicon);
                    teamname.setText("Sunrisers Hyderabad");
                    teamowner.setText(teamowner.getText() + "SUN TV Network");
                    teamcoach.setText(teamcoach.getText() + "Tom Moody");
                    teamvanue.setText(teamvanue.getText() + "Rajiv Gandhi Intl. Cricket Stadium");
                    add_SRH_Data();
                    setUpRecylerView();

                    break;
                case "RCB":
                    bglayout.setBackgroundResource(R.drawable.bg_rcb);
                    teamicon.setImageResource(R.drawable.rcbion);
                    teamname.setText("Royal Challengers Bangalore");
                    teamowner.setText(teamowner.getText() + "Royal Challengers Sports Private Ltd");
                    teamcoach.setText(teamcoach.getText() + "Daniel Vettori");
                    teamvanue.setText(teamvanue.getText() + "M. Chinnaswamy Stadium");
                    add_RCB_Data();
                    setUpRecylerView();
                    break;
                case "DC":
//                circleImageView.setBorderColor(getResources().getColor(R.color.dc));
                    bglayout.setBackgroundResource(R.drawable.bg_dc);
                    teamicon.setImageResource(R.drawable.dcicon);
                    teamname.setText("Delhi Capitals");
                    teamowner.setText(teamowner.getText() + "GMR Sports Pvt .Ltd & JSW Sports Pvt Ltd");
                    teamcoach.setText(teamcoach.getText() + "Ricky Ponting");
                    teamvanue.setText(teamvanue.getText() + "Feroz Shah Kotla Ground");
                    add_DC_Data();
                    setUpRecylerView();
                    break;
                case "KXIP":
//                circleImageView.setBorderColor(getResources().getColor(R.color.kxip));
                    bglayout.setBackgroundResource(R.drawable.bg_kxip);

                    teamicon.setImageResource(R.drawable.kxiiicon);
                    teamname.setText("Kings XI Punjab");
                    teamowner.setText(teamowner.getText() + "KPH Dream Cricket Private Limited");
                    teamcoach.setText(teamcoach.getText() + "Mike Hesson");
                    teamvanue.setText(teamvanue.getText() + "IS Bindra Stadium");
                    add_KXIP_Data();
                    setUpRecylerView();
                    break;
                case "RR":
//                circleImageView.setBorderColor(getResources().getColor(R.color.rr));
                    bglayout.setBackgroundResource(R.drawable.bg_rr);
                    teamicon.setImageResource(R.drawable.rricon);
                    teamname.setText("Rajasthan Royals");
                    teamowner.setText(teamowner.getText() + "Royal Multisport Pvt. Ltd");
                    teamcoach.setText(teamcoach.getText() + "Rahul Dravid");
                    teamvanue.setText(teamvanue.getText() + "Sawai Mansingh Stadium");
                    add_RR_Data();
                    setUpRecylerView();
                    break;
            }
        }else{
            add_Mumbai_Data();
            setUpRecylerView();
            Toast.makeText(getContext(),"failed to get data",Toast.LENGTH_SHORT).show();
        }
        /*recyclerView=(RecyclerView)view.findViewById(R.id.squad_recylerViewID);
        SquadAdapter squadAdapter = new SquadAdapter(getContext(),list);*/
        return view;
    }

    private void addBannerAds() {
        // Loop through the items array and place a new banner ad in every ith position in
        // the items List.
        for (int i = 0; i <= list.size(); i += ITEMS_PER_AD) {
//            adView = new AdView(mContext,getResources().getString(R.string.ban_placementID), AdSize.BANNER_HEIGHT_50);

//            list.add(i, adView);
        }
    }

    private void setUpRecylerView(){
        RecyclerView recyclerView = view.findViewById(R.id.squad_recylerViewID);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        SquadAdapter adapter = new SquadAdapter(getContext(), list);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }



    private void add_Mumbai_Data(){
        list = new ArrayList<ModelClass2>();
        list.add(new ModelClass2(R.drawable.mi1_rohit,"Rohit Sharma","Price : ₹15 crore",R.drawable.captain," Batsman","Right-handed ","  Right-arm off-spin  ","Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.empty_profile,"Digvijay Deshmukh","Price : ₹20 lakh", R.drawable.blankbg,"Bowler", "Right-handed", "  Right-arm fast-medium  ", "Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi3_dekock,"Quinton de Kock","Price : ₹2.8 crore", R.drawable.plane, "  Wicketkeeper batsman  ", "Left-handed", "", "South African",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi4_tare,"Aditya Tare","Price : ₹20 lakh", R.drawable.blankbg, "  Wicketkeeper batsman  ", "Right-handed", "", "Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi5_tiwary,"Saurabh Tiwary","Price : ₹50 lakh", R.drawable.blankbg, " Batsman", "  Left-handed  ", "", "Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi6_bumrah,"Jasprit Bumrah","Price : ₹7 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm medium  ", "Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi7_kulkarni,"Dhawal Kulkarni","Price : ₹75 lakh", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm medium fast  ", "Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi8_coulter_nile,"Nathan Coulter-Nile","Price : ₹8 crore", R.drawable.plane, "Bowler", "Right-handed", "  Right-arm fast  ", "Australian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi9_boult,"Trent Boult","Price : ₹2.2 crore", R.drawable.plane, "Bowler", "Right-handed", "  Left-arm fast-medium  ", "New Zealander",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi10_jayant,"Jayant Yadav","Price : ₹50 lakh", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm off-spin  ", "Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi11_suryakumar,"Suryakumar Yadav","Price : ₹3.2 crore", R.drawable.blankbg, "Batsman", "Right-handed", "  Right-arm medium  ", "Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi12_krunal,"Krunal Pandya","Price : ₹8.8 crore", R.drawable.blankbg, "All-rounder", "Left-handed", "  Left-arm orthodox  ", "Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi13_pollard,"Kieron Pollard","Price : ₹5.4 crore", R.drawable.plane, "All-rounder", "Right-handed", "  Right-arm medium  ", "West Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi14_malinga,"Lasith Malinga","Price : ₹2 crore", R.drawable.plane, "Bowler", "Right-handed", "  Right-arm fast  ", "Sri Lankan",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi15_chahar,"Rahul Chahar","Price : ₹1.9 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Leg break googly  ", "Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi16_lynn,"Chris Lynn","Price : ₹2 crore", R.drawable.plane, "Batsman", "Right-handed", "  left-arm orthodox  ", "Australian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi17_hardik,"Hardik Pandya","Price : ₹11 crore", R.drawable.blankbg, "All-rounder", "Right-handed", "  Right-arm medium fast  ", "Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi18_rutherford,"Sherfane Rutherford","Price : ₹2 crore", R.drawable.plane, "Batsman", "Left-handed", "  Right-arm fast-medium  ", "West Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi19_singh,"Anmolpreet Singh","Price : ₹80 lakh", R.drawable.blankbg, "Batsman", "Right-handed", "  Right-arm off-break  ", "Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi20_msohin,"Mohsin Khan","Price : ₹20 lakh", R.drawable.blankbg, "Batsman", "Left-handed", "  Left-arm medium-fast  ", "Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi21_mcclenaghan,"Mitchell McClenaghan","Price : ₹1 crore", R.drawable.blankbg, "Bowler", "Left-handed", "  Left-arm medium-fast  ", "New Zealander",R.color.mi));
        list.add(new ModelClass2(R.drawable.empty_profile,"Balwant Rai Singh","Price : ₹20 lakh", R.drawable.blankbg, "All-rounder", "Right-handed", "  Leg-break googly  ", "Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi23_roy,"Anukul Roy","Price : ₹20 lakh", R.drawable.blankbg, "All-rounder", "Left-handed", "  Slow left-arm orthodox  ", "Indian",R.color.mi));
        list.add(new ModelClass2(R.drawable.mi24_kishan,"Ishan Kishan","Price : ₹6.2 crore", R.drawable.blankbg,"Batsman", "Left-handed", "  Right-arm medium  ", "Indian",R.color.mi));

    }

    private void add_CSK_Data(){
        list = new ArrayList<ModelClass2>();
        list.add(new ModelClass2(R.drawable.csk1_dhoni,"MS Dhoni","Price : ₹15 crore",R.drawable.captain, "  Wicketkeeper batsman  ", "Right-handed", "  Right-arm medium  ", "Indian",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk2_rayudu,"Ambati Rayudu","Price : ₹2.2 crore", R.drawable.blankbg, "  Wicketkeeper batsman  ", "Right-handed", "  Right-arm off-spin  ", "Indian",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk3_asif,"KM Asif","Price : ₹40 lakh", R.drawable.blankbg, "All-rounder", "Right-handed", "  Right-arm off-spin  ", "Indian",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk4_chahar,"Deepak Chahar","Price : ₹80 lakh", R.drawable.blankbg, "All-rounder", "Right-handed", "  Right-arm medium  ", "Indian",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk5_bravo,"Dwayne Bravo","Price : ₹6.4 crore", R.drawable.plane, "All-rounder", "Right-handed", "  Right-arm medium fast  ", "West Indian",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk6_duplasis,"Faf du Plessis","Price : ₹1.6 crore", R.drawable.plane, "Batsman", "Right-handed", "  Right-arm leg spin  ", "South African",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk7_harbhajan,"Harbhajan Singh","Price : ₹2 crore", R.drawable.blankbg, "All-rounder", "Right-handed", "  Right-arm off-spin  ", "Indian",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk8_tahir,"Imran Tahir","Price : ₹1 crore", R.drawable.plane, "Bowler", "Right-handed", "  Leg-spinner  ", "South African",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk9_narayan,"Narayan Jagadeesan","Price : ₹20 lakh", R.drawable.blankbg, "  Wicket-keeper batsman  ", "Right-handed", "", "Indian",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk10_karn,"Karn Sharma","Price : ₹5 crore", R.drawable.blankbg, " All-rounder", " Left-handed", "  Right-arm leg spin  ", "Indian",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk11_kedar,"Kedar Jadhav","Price : ₹7.8 crore", R.drawable.blankbg, "All-rounder", "Right-handed", "  Right-arm off-spin  ", "Indian",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk12_ngidi,"Lungi Ngidi","Price : ₹50 lakh", R.drawable.plane, " Bowler", "Right-handed", "  Right-arm fast  ", "South African",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk13_santner,"Mitchell Santner","Price : ₹50 lakh", R.drawable.plane, "Bowling all-rounder", " Left-handed", "  Left-arm orthodox  ", " New Zealander",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk14_kumar,"Monu Kumar","Price : ₹20 lakh", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm Medium  ", "Indian",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk15_vijoy,"Murali Vijay","Price : ₹2 crore", R.drawable.blankbg, "Batsman", "Right-handed", "  Right-arm off-spin  ", "Indian",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk16_jadeja,"Ravindra Jadeja","Price : ₹7 crore", R.drawable.blankbg, "All-rounder", "Left-handed", "  Left-arm orthodox spin  ", "Indian",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk17_gaikwad,"Ruturaj Gaikwad","Price : ₹20 lakh", R.drawable.blankbg, "Batsman", "Right-handed", "  Right-arm offbreak  ", "Indian",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk18_watson,"Shane Watson","Price : ₹4 crore", R.drawable.plane, "All-rounder", "Right-handed", "  Right-arm medium fast  ", "Australian",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk19_thakur,"Shardul Thakur","Price : ₹2.6 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm medium  ", "Indian",R.color.csk));
        list.add(new ModelClass2(R.drawable.csk20_raina,"Suresh Raina","Price : ₹11 crore", R.drawable.blankbg, "Batsman", "Left-handed", "  Right-arm off-spin  ", "Indian",R.color.csk));
        list.add(new ModelClass2(R.drawable.empty_profile,"Sam Curran","Price : ₹5.5 crore", R.drawable.plane, "Bowling All-rounder", "Left-handed", "  Left-arm medium-fast  ", "English",R.color.csk));
        list.add(new ModelClass2(R.drawable.empty_profile,"Piyush Chawla","Price : ₹6.75 crore", R.drawable.blankbg, "Bowling All-rounder", "Left-handed", "  Right-arm leg spin  ", "Indian",R.color.csk));
        list.add(new ModelClass2(R.drawable.empty_profile,"Josh Hazlewood","Price : ₹2 crore", R.drawable.plane, "Bowler", "Left-handed", "  Right-arm fast-medium  ", "Australian",R.color.csk));
        list.add(new ModelClass2(R.drawable.empty_profile,"R Sai Kishore","Price : ₹20 lakh", R.drawable.blankbg, "Bowler", "Left-handed", "  Left-arm Leg Spin  ", "Indian",R.color.csk));

    }

    private void add_RCB_Data(){
        list = new ArrayList<ModelClass2>();
        list.add(new ModelClass2(R.drawable.rcb1_virat,"Virat Kohli","Price : ₹17 crore",R.drawable.captain, "Batsman", "Right-handed", "  Right-arm medium  ", "Indian",R.color.rcb));
        list.add(new ModelClass2(R.drawable.rcb2_abd,"AB de Villiers","Price : ₹11 crore", R.drawable.plane, "Wicketkeeper batsman", "Right-handed", "  Right-arm medium  ", "South African",R.color.rcb));
        list.add(new ModelClass2(R.drawable.rcb3_padikkal,"Devdutt Padikkal","Price : ₹20 lakh", R.drawable.blankbg, "Batsman", "Left-handed", "  Right-arm offbreak  ", "Indian",R.color.rcb));
        list.add(new ModelClass2(R.drawable.rcb4_mannsingh,"Gurkeerat Mann Singh","Price : ₹50 lakh", R.drawable.blankbg, "Batsman", "Right-handed", "  Right-arm off-spin  ", "Indian",R.color.rcb));
        list.add(new ModelClass2(R.drawable.rcb5_moeen,"Moeen Ali","Price : ₹1.7 crore", R.drawable.plane, "Batting all-rounder", "Left-handed", "  Right-arm off-break  ", "English",R.color.rcb));
        list.add(new ModelClass2(R.drawable.rcb6_siraj,"Mohammed Siraj","Price : ₹2.6 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm medium-fast  ", "Indian",R.color.rcb));
        list.add(new ModelClass2(R.drawable.rcb7_saini,"Navdeep Saini","Price : ₹3 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm fast  ", "Indian",R.color.rcb));
        list.add(new ModelClass2(R.drawable.rcb8_patel,"Parthiv Patel","Price : ₹1.7 crore", R.drawable.blankbg, "  Wicketkeeper batsman  ", "Left-handed", "", "Indian",R.color.rcb));
        list.add(new ModelClass2(R.drawable.rcb9_negi,"Pawan Negi","Price : ₹1 crore", R.drawable.blankbg, "Bowling All-rounder", "Left-handed", "  Left-arm orthodox spin  ", "Indian",R.color.rcb));
        list.add(new ModelClass2(R.drawable.rcb10_dube,"Shivam Dube","Price : ₹5 crore", R.drawable.blankbg, " All-rounder", " Left-handed", "  Right-arm medium-fast  ", "Indian",R.color.rcb));
        list.add(new ModelClass2(R.drawable.rcb11_umesh,"Umesh Yadav","Price : ₹4.2 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm medium fast  ", "Indian",R.color.rcb));
        list.add(new ModelClass2(R.drawable.rcb12_sundar,"Washington Sundar","Price : ₹3.2 crore", R.drawable.blankbg, "All-rounder", "Left-handed", "  Right-arm off-break   ", "Indian",R.color.rcb));
        list.add(new ModelClass2(R.drawable.rcb13_chahal,"Yuzvendra Chahal","Price : ₹6 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm leg spin  ", "Indian",R.color.rcb));
        list.add(new ModelClass2(R.drawable.rcb14_finch,"Aaron Finch","Price : ₹4.4 crore", R.drawable.plane, "Batsman", "Right-handed", "  Left-arm medium  ", "Australian",R.color.rcb));
        list.add(new ModelClass2(R.drawable.rcb15_morris,"Chris Morris","Price : ₹10 crore", R.drawable.plane, "All-rounder", "Right-handed", "  Right-arm medium fast  ", "South African",R.color.rcb));
        list.add(new ModelClass2(R.drawable.empty_profile,"Josh Philippe","Price : ₹20 lakh", R.drawable.plane, "  Wicketkeeper batsman  ", "Right-handed", "", "Australian",R.color.rcb));
        list.add(new ModelClass2(R.drawable.empty_profile,"Kane Richardson","Price : ₹4 crore", R.drawable.plane, "Bowler", "Right-handed", "  Right-arm medium fast  ", "Australian",R.color.rcb));
        list.add(new ModelClass2(R.drawable.rcb18_pavan,"Pavan Deshpande","Price : 5 Core rs", R.drawable.blankbg, "All-rounder", "Left-handed", "  Right-arm offbreak  ", "Indian",R.color.rcb));
        list.add(new ModelClass2(R.drawable.rcb19_steyn,"Dale Steyn","Price : ₹2 crore", R.drawable.plane, "Bowler", "Left-handed", "  Right-arm fast  ", "South African",R.color.rcb));
        list.add(new ModelClass2(R.drawable.empty_profile,"Shahbaz Ahmed","Price : ₹20 lakh", R.drawable.blankbg, "", "Left-handed", "  Left-arm orthodox  ", "Indian",R.color.rcb));
        list.add(new ModelClass2(R.drawable.empty_profile,"Isuru Udana","Price : ₹50 lakh", R.drawable.plane, "Bowling All-rounder", "Right-handed", "  Left-arm fast-medium  ", "Sri Lankan",R.color.rcb));


    }

    private void add_KKR_Data(){
        list = new ArrayList<ModelClass2>();
        list.add(new ModelClass2(R.drawable.kkr1_dk,"Dinesh Karthik","Price : ₹7.4 crore",R.drawable.captain, "  Wicketkeeper batsman  ", "Right-handed", "", "Indian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.kkr2_mavi,"Shivam Mavi","Price : ₹3 crore", R.drawable.blankbg, "All-rounder", "Right-handed", "  Right-arm fast  ", "Indian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.kkr3_warrier,"Sandeep Warrier","Price : ₹20 lakh", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm medium  ", "Indian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.kkr4_kuldeep,"Kuldeep Yadav","Price : ₹5.8 crore", R.drawable.blankbg, "Bowler", "Left-handed", "  Left-arm orthodox spin  ", "Indian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.empty_profile,"Eoin Morgan","Price : ₹5.25 crore", R.drawable.plane, "Batsman", "Left-handed", "  Right-arm medium  ", "English",R.color.kkr));
        list.add(new ModelClass2(R.drawable.empty_profile,"Pat Cummins","Price : ₹15.5 crore", R.drawable.plane, "Bowler", "Right-handed", "  Right-arm fast  ", "Australian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.kkr7_gurney,"Harry Gurney","Price : ₹75 lakh", R.drawable.plane, "Bowler", "Right-handed", "  Left-arm fast-medium  ", "English",R.color.kkr));
        list.add(new ModelClass2(R.drawable.kkr8_narine,"Sunil Narine","Price : ₹8.5 crore", R.drawable.plane, "Bowler", "Left-handed", "  Right-arm off-spin  ", "West Indian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.empty_profile,"Varun Chakravarthy","Price : ₹4 crore", R.drawable.blankbg, "Bowler", "   Right-handed   ", "Leg-break", "Indian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.empty_profile,"Rahul Tripathi","Price : ₹60 lakh", R.drawable.blankbg, "Batsman", "   Right-handed   ", "", "Indian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.kkr11_russel,"Andre Russell","Price : ₹7 crore", R.drawable.plane, "All-rounder", "Right-handed", "   Right-arm fast   ", "West Indian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.kkr12_ferguson,"Lockie Ferguson","Price : ₹1.6 crore", R.drawable.plane, "Bowler", "Right-handed", "   Right-arm fast   ", "New Zealander",R.color.kkr));
        list.add(new ModelClass2(R.drawable.kkr13_krishna,"Prasidh Krishna","Price : ₹20 lakh", R.drawable.blankbg, "Bowler", "Right-handed", "   Right-arm fast   ", "Indian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.kkr14_gill,"Shubman Gill","Price : ₹1.8 crore", R.drawable.blankbg, "Batsman", "   Right-handed   ", "", "Indian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.kkr15_rana,"Nitish Rana","Price : ₹3.4 crore", R.drawable.blankbg, "Batsman", "Left-handed", "  Right-arm off-break  ", "Indian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.kkr16_lad,"Siddhesh Lad","Price : ₹20 lakh", R.drawable.blankbg, "Batsman", "Right-handed", "  Right-arm off-spin  ", "Indian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.kkr17_nagarkoti,"Kamlesh Nagarkoti","Price : ₹3.2 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm fast  ", "Indian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.kkr18_rinku,"Rinku Singh","Price : ₹80 lakh", R.drawable.blankbg, "Batsman", "Right-handed", "  Right-arm off break  ", "Indian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.empty_profile,"M Siddharth","Price : ₹20 lakh", R.drawable.blankbg, "Bowler", "Right-handed", "  Left-arm orthodox  ", "Indian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.empty_profile,"Chris Green","Price : ₹20 lakh", R.drawable.plane, "Bowling All-rounder", "Right-handed", "  Right-arm off-break  ", "Australian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.empty_profile,"Tom Banton","Price : ₹1 crore", R.drawable.plane, "  Wicketkeeper-Batsman  ", "Left-handed", "", "English",R.color.kkr));
        list.add(new ModelClass2(R.drawable.empty_profile,"Pravin Tambe","Price : ₹20 lakh", R.drawable.blankbg, "All-rounder", "Right-handed", "  Right-arm leg spin  ", "Indian",R.color.kkr));
        list.add(new ModelClass2(R.drawable.empty_profile,"Nikhil Naik","Price :  ₹20 lakh", R.drawable.blankbg, "  Wicketkeeper-batsman  ", "Right-handed", "", "Indian",R.color.kkr));

    }

    private void add_RR_Data(){
        list = new ArrayList<ModelClass2>();
        list.add(new ModelClass2(R.drawable.rr13_smith,"Steve Smith","Price : ₹12 crore", R.drawable.captain, "All-rounder", "Right-handed", "  Right-arm leg spin  ", "Australian",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr1_rajpoot,"Ankit Rajpoot","Price : ₹3 crore",R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm medium  ", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr2_stokes,"Ben Stokes","Price : ₹12.5 crore", R.drawable.plane, "All-rounder", "Left-handed", "  Right-arm fast-medium  ", "English",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr3_archer,"Jofra Archer","Price : ₹7.2 crore", R.drawable.plane, "Bowler", "Right-handed", "  Right-arm fast  ", "English",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr4_buttler,"Jos Buttler","Price : ₹4.4 crore", R.drawable.plane, "  Wicketkeeper-batsman  ", "Right-handed", "", "English",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr5_lomror,"Mahipal Lomror","Price : ₹20 lakh", R.drawable.blankbg, "All-rounder", "Left-handed", "  Left-arm spin  ", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr6_vohra,"Manan Vohra","Price : ₹20 lakh", R.drawable.blankbg, "Batsman", "Right-handed", "  Right-arm medium  ", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr7_markande,"Mayank Markande","Price : ₹2 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm leg break  ", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr8_tewatia,"Rahul Tewatia","Price : ₹3 crore", R.drawable.blankbg, "Bowler", "   Right-handed   ", "Leg-spinner", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr9_parag,"Riyan Parag","Price : ₹20 lakh", R.drawable.blankbg, "Batsman", "   Right-handed   ", "Leg break", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr10_sanju,"Sanju Samson","Price : ₹8 crore", R.drawable.blankbg, "  Wicketkeeper batsman  ", "Right-handed", "", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr11_singh,"Shashank Singh","Price : ₹30 lakh", R.drawable.blankbg, "Batsman", "  Right-handed  ", "", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr12_gopal,"Shreyas Gopal","Price : ₹20 lakh", R.drawable.blankbg, "All-rounder", "Right-handed", "  Right-arm leg spin  ", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr14_aaron,"Varun Aaron","Price : ₹2.4 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm fast  ", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr15_uthappa,"Robin Uthappa","Price : ₹3 crore", R.drawable.blankbg, "Batsman", "Right-handed", "  Right-arm medium  ", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr16_unadkat,"Jaydev Unadkat","Price : ₹3 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Left-arm medium fast  ", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.empty_profile,"Yashasvi Jaiswal","Price : ₹2.4 crore", R.drawable.blankbg, "Batsman", "   Left-handed   ", " Leg break", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.empty_profile,"Anuj Rawat","Price : ₹80 lakh", R.drawable.blankbg, "  Wicketkeeper batsman  ", "Left-handed", "", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.empty_profile,"Akash Singh","Price : ₹20 lakh", R.drawable.blankbg, "Bowler", "Right-handed", "  Left-arm medium-fast  ", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.empty_profile,"Kartik Tyagi","Price : ₹1.3 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm Medium Fast  ", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr21_miller,"David Miller","Price : ₹75 lakh", R.drawable.plane, "Batsman", "Left-handed", "  Right-arm off-spin  ", "South African",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr22_thomas,"Oshane Thomas","Price : ₹50 lakh", R.drawable.plane, "Bowler", "Left-handed", "  Right-arm fast  ", "West Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.empty_profile,"Anirudha Joshi","Price : ₹20 lakh", R.drawable.blankbg, "Bowler", "Left-handed", "  Right-arm off-spin  ", "Indian",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr24_tye,"Andrew Tye","Price : ₹1 crore", R.drawable.plane, "All-rounder", "Right-handed", "  Right-arm medium fast  ", "Australian",R.color.rr));
        list.add(new ModelClass2(R.drawable.rr25_tomcurran,"Tom Curran","Price : ₹1 crore", R.drawable.plane, "All-rounder", "Right-handed", "  Right-arm medium fast  ", "English",R.color.rr));
    }

    private void add_DC_Data(){
        list = new ArrayList<ModelClass2>();
        list.add(new ModelClass2(R.drawable.dc1_iyer,"Shreyas Iyer","Price : ₹7 crore",R.drawable.captain, "Batsman", "Right-handed", "  Right-arm off-break  ", "Indian",R.color.dc));
        list.add(new ModelClass2(R.drawable.dc2_rabada,"Kagiso Rabada","Price : ₹4.2 crore", R.drawable.plane, "Bowler", "Right-handed", "  Right-arm fast  ", "South African",R.color.dc));
        list.add(new ModelClass2(R.drawable.empty_profile,"Jason Roy","Price : ₹1.5 crore", R.drawable.plane, "Batsman", "   Right-handed   ", "", "English",R.color.dc));
        list.add(new ModelClass2(R.drawable.dc4_lamichhane,"Sandeep Lamichhane","Price : ₹20 lakh", R.drawable.plane, "Bowler", "Right-handed", "  Right-arm leg-spin  ", " Nepalese",R.color.dc));
        list.add(new ModelClass2(R.drawable.dc5_ishant,"Ishant Sharma","Price : ₹1.1 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm medium fast  ", "Indian",R.color.dc));
        list.add(new ModelClass2(R.drawable.dc6_rahane,"Ajinkya Rahane","Price : ₹4 crore", R.drawable.blankbg, "Batsman", "Right-handed", "  Right-arm medium  ", "Indian",R.color.dc));
        list.add(new ModelClass2(R.drawable.empty_profile,"Chris Woakes","Price : ₹1.5 crore", R.drawable.plane, "All-rounder", "Right-handed", "  Right-arm fast-medium  ", "English",R.color.dc));
        list.add(new ModelClass2(R.drawable.dc8_ashwin,"Ravichandran Ashwin","Price : ₹7.6 crore", R.drawable.blankbg, "Bowling all-rounder", "Right-handed", "  Right-arm off-spin  ", "Indian",R.color.dc));
        list.add(new ModelClass2(R.drawable.dc9_dhawan,"Shikhar Dhawan","Price : ₹5.2 crore", R.drawable.blankbg, "Batsman", "   Left-handed   ", "", "Indian",R.color.dc));
        list.add(new ModelClass2(R.drawable.empty_profile,"Alex Carey","Price : ₹2.4 crore", R.drawable.plane, "  Wicketkeeper batsman  ", " Left-handed", "", "Australian",R.color.dc));
        list.add(new ModelClass2(R.drawable.empty_profile,"Shimron Hetmyer","Price : ₹7.75 crore", R.drawable.plane, "Batsman", "   Left-handed   ", "", "west Indian",R.color.dc));
        list.add(new ModelClass2(R.drawable.empty_profile,"Marcus Stoinis","Price : ₹4.8 crore", R.drawable.plane, "All-rounder", "Right-handed", "  Right-arm medium-fast  ", "Australian",R.color.dc));
        list.add(new ModelClass2(R.drawable.dc13_mohit,"Mohit Sharma","Price : ₹50 lakh", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm medium  ", "Indian",R.color.dc));
        list.add(new ModelClass2(R.drawable.dc14_shaw,"Prithvi Shaw","Price : ₹1.2 crore", R.drawable.blankbg, "Batsman", "   Right-handed   ", "", "Indian",R.color.dc));
        list.add(new ModelClass2(R.drawable.empty_profile,"Tushar Deshpande","Price : ₹20 lakh", R.drawable.blankbg, "Bowler", "Right-handed", "   Right-arm Medium   ", "Indian",R.color.dc));
        list.add(new ModelClass2(R.drawable.dc16_khan,"Avesh Khan","Price : ₹70 lakh", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm Medium Fast  ", "Indian",R.color.dc));
        list.add(new ModelClass2(R.drawable.dc17_axar,"Axar Patel","Price : ₹5 crore", R.drawable.blankbg, "All-rounder", "Left-handed", "  Left-arm orthodox spin  ", "Indian",R.color.dc));
        list.add(new ModelClass2(R.drawable.empty_profile,"Lalith Yadav","Price : ₹20 lakh", R.drawable.blankbg, "All-rounder", "Right-handed", "  Right-arm off-break  ", "Indian",R.color.dc));
        list.add(new ModelClass2(R.drawable.dc19_rishabh,"Rishabh Pant","Price : ₹8 crore", R.drawable.blankbg, "  Wicketkeeper-batsman  ", "Left-handed", "", "Indian",R.color.dc));
        list.add(new ModelClass2(R.drawable.dc20_harshal,"Harshal Patel","Price : ₹20 lakh", R.drawable.blankbg, "Batsman", "Right-handed", "   Right-arm medium   ", "Indian",R.color.dc));
        list.add(new ModelClass2(R.drawable.dc21_keemo,"Keemo Paul","Price : ₹50 lakh", R.drawable.plane, "All-rounder", "Right-handed", "  Right-arm medium-fast  ", "West Indian",R.color.dc));
        list.add(new ModelClass2(R.drawable.dc22_amit,"Amit Mishra","Price : ₹4 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm leg spin  ", "Indian",R.color.dc));


    }

    private void add_SRH_Data(){
        list = new ArrayList<ModelClass2>();
        list.add(new ModelClass2(R.drawable.srh1_williamson,"Kane Williamson","Price : ₹3 crore",R.drawable.captain, "Batsman", "Right-handed", "  Right-arm off-spin  ", "New Zealander",R.color.srh));
        list.add(new ModelClass2(R.drawable.srh2_abhishek,"Abhishek Sharma","Price : ₹55 lakh", R.drawable.blankbg, "Batsman", "Left-handed", "  Left-arm orthodox  ", "Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.srh3_thampi,"Basil Thampi","Price : ₹95 lakh", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm fast medium  ", "Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.srh4_bhuvi,"Bhuvneshwar Kumar","Price : ₹8.5 crore", R.drawable.blankbg, "Bowler", "Right-handed", "   Right-arm medium   ", "Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.srh5_stanlake,"Billy Stanlake","Price : ₹50 lakh", R.drawable.plane, "Bowler", "Left-handed", "   Right-arm fast   ", "Australian",R.color.srh));
        list.add(new ModelClass2(R.drawable.srh6_warner,"David Warner","Price : ₹12 crore", R.drawable.plane, "Batsman", "Left-handed", "  Right-arm leg spin  ", "Australian",R.color.srh));
        list.add(new ModelClass2(R.drawable.srh7_jonny,"Jonny Bairstow","Price : ₹2.2 crore", R.drawable.plane, "  Wicketkeeper Batsman  ", "Right-handed", "", "English",R.color.srh));
        list.add(new ModelClass2(R.drawable.srh8_manish,"Manish Pandey","Price : ₹11 crore", R.drawable.blankbg, "Batsman", "Right-handed", "  Right-arm off-spin  ", "Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.srh9_nabi,"Mohammad Nabi","Price : ₹1 crore", R.drawable.plane, "All-rounder", "Right-handed", "  Right-arm off break  ", "Afghan",R.color.srh));
        list.add(new ModelClass2(R.drawable.srh10_rashid,"Rashid Khan","Price : ₹9 crore", R.drawable.plane, "All-rounder", "Right-handed", "  Right-arm leg spin  ", "Afghan",R.color.srh));
        list.add(new ModelClass2(R.drawable.srh11_sandeep,"Sandeep Sharma","Price : ₹3 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm medium  ", "Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.srh12_nadeem,"Shahbaz Nadeem","Price : ₹3.2 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Left-arm orthodox spin  ", "Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.srh13_goswami,"Shreevats Goswami","Price : ₹1 crore", R.drawable.blankbg, "Wicketkeeper batsman", " Left-handed", "  Right-arm off-spin  ", "Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.srh14_kaul,"Siddarth Kaul","Price : ₹3.8 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm medium fast  ", "Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.srh15_khaleel,"Khaleel Ahmed","Price : ₹3 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Left-arm medium pace  ", "Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.srh16_natarajan,"T Natarajan","Price : ₹50 lakh", R.drawable.blankbg, "Bowler", "Left-handed", "  Left-arm medium-fast  ", "Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.srh17_shankar,"Vijay Shankar","Price : ₹3.2 crore", R.drawable.blankbg, "All-rounder", "Right-handed", "  Right-arm off-break  ", "Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.empty_profile,"Wriddhiman Saha","Price : ₹1.2 crore", R.drawable.blankbg, "  Wicketkeeper batsman  ", "Right-handed", "", "Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.empty_profile,"Virat Singh","Price : ₹1.9 crore", R.drawable.blankbg, "Batsman", "Left-handed", "  Right-arm leg spin  ", "Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.empty_profile,"Priyam Garg","Price : ₹1.9 crore", R.drawable.blankbg, "Batsman", "   Right-handed   ", "", "Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.empty_profile,"Mitchell Marsh","Price : ₹2 crore", R.drawable.plane, "All-rounder", "Right-handed", "   Right-arm medium   ", "Australian",R.color.srh));
        list.add(new ModelClass2(R.drawable.empty_profile,"Sandeep Bavanaka","Price : ₹20 lakh", R.drawable.blankbg, "All-rounder", "Left-handed", "  Left-arm orthodox  ", "Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.empty_profile,"Fabian Allen","Price : ₹50 lakh", R.drawable.plane, "All-rounder", "Right-handed", "  Left-arm orthodox  ", "West Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.empty_profile,"Abdul Samad","Price : ₹20 lakh", R.drawable.blankbg, "", "  Right-handed  ", "Legbreak", "Indian",R.color.srh));
        list.add(new ModelClass2(R.drawable.empty_profile,"Sanjay Yadav","Price : ₹20 lakh", R.drawable.blankbg, "Batsman", "  Left-handed  ", "", "Indian",R.color.srh));

    }

    private void add_KXIP_Data(){
        list = new ArrayList<ModelClass2>();
        list.add(new ModelClass2(R.drawable.kxip1_rahul,"KL Rahul","Price : ₹11 crore",R.drawable.captain, "  Wicketkeeper batsman  ", "Right-handed", "", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.kxip2_brar,"Harpreet Brar","Price : ₹20 lakh", R.drawable.blankbg, "Bowler", "Left-handed", "  Left-arm orthodox  ", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.empty_profile,"Deepak Hooda","Price : ₹50 lakh", R.drawable.blankbg, "Bowling All-rounder", "Right-handed", "  Right-arm off-spin  ", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.kxip4_mandeep,"Mandeep Singh","Price : ₹1.4 crore", R.drawable.blankbg, "All-rounder", "Right-handed", "  Right-arm medium  ", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.empty_profile,"Glenn Maxwell","Price : ₹10.75 crore", R.drawable.plane, "All-rounder", "Right-handed", "  Right-arm off-spin  ", "Australian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.empty_profile,"Sheldon Cottrell","Price : ₹8.5 crore", R.drawable.plane, "Bowler", "Right-handed", "  Left-arm fast-medium  ", "West Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.empty_profile,"Ishan Porel","Price : ₹20 lakh", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm fast-medium  ", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.kxip8_nair,"Karun Nair","Price : ₹5.6 crore", R.drawable.plane, "Batting All-rounder", "Right-handed", "  Right-arm off-spin  ", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.empty_profile,"Tajinder Singh","Price : ₹20 lakh", R.drawable.blankbg, "All-rounder", "Right-handed", "  Right-arm off-break  ", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.empty_profile,"Ravi Bishnoi","Price : ₹2 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm leg spin  ", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.kxip11_arshdeep,"Arshdeep Singh","Price : ₹20 lakh", R.drawable.blankbg, "Bowler", "Left-handed", "  Left-arm medium-fast  ", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.empty_profile,"Jimmy Neesham","Price : ₹50 lakh", R.drawable.plane, "All-rounder", "Left-handed", "  Right-arm medium  ", "New Zealander",R.color.kxip));
        list.add(new ModelClass2(R.drawable.empty_profile,"Chris Jordan","Price : ₹3 crore", R.drawable.plane, "Bowler", "Right-handed", "  Right-arm medium fast  ", "English",R.color.kxip));
        list.add(new ModelClass2(R.drawable.kxip13_mujeeb,"Mujeeb Ur Rahman","Price : ₹4 crore", R.drawable.plane, "Bowler", "Right-handed", "  Right-arm off break  ", "Afghan",R.color.kxip));
        list.add(new ModelClass2(R.drawable.kxip14_sarfaraz,"Sarfaraz Khan","Price : ₹25 lakh", R.drawable.blankbg, "Batsman", "   Right-handed   ", "Legbreak", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.empty_profile,"PrabhSimran Singh","Price : ₹55 lakh", R.drawable.blankbg, "  Wicketkeeper batsman  ", "Right-handed", "", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.kxip16_maynak,"Mayank Agarwal","Price : ₹1 crore", R.drawable.blankbg, "Batsman", "   Right-handed   ", "", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.kxip17_shami,"Mohammad Shami","Price : ₹4.8 crore", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm medium  ", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.kxip18_nalkande,"Darshan Nalkande","Price : ₹30 lakh", R.drawable.blankbg, "Bowler", "Right-handed", "  Right-arm fast-medium  ", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.kxip19_pooran,"Nicholas Pooran","Price : ₹4.2 crore", R.drawable.plane, "Batsman", "   Left-handed   ", "", "West Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.kxip20_gayle,"Chris Gayle","Price : ₹2 crore", R.drawable.plane, "All-rounder", "Left-handed", "   Right-arm off-spin   ", "West Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.kxip21_murugan,"Murugan Ashwin","Price : ₹20 lakh", R.drawable.blankbg, "Bowler", "   Right-handed   ", "Leg-break", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.kxip22_suchith,"Jagadeesha Suchith","Price : ₹20 lakh", R.drawable.blankbg, "Bowler", "Left-handed", "  Slow left-arm orthodox  ", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.kxip23_gowtham,"Krishnappa Gowtham","Price : ₹6.2 crore", R.drawable.blankbg, "All-rounder", "Right-handed", "  Right-arm off break  ", "Indian",R.color.kxip));
        list.add(new ModelClass2(R.drawable.kxip24_viljoen,"Hardus Viljoen","Price : ₹75 lakh", R.drawable.plane, "Bowler", "Right-handed", "  Right-arm fast  ", "South African",R.color.kxip));

    }
    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setTitle("Squad of "+TeamName);

        }

    }

}
