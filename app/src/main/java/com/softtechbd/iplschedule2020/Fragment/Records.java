package com.softtechbd.iplschedule2020.Fragment;


import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.softtechbd.iplschedule2020.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class Records extends Fragment {
    View view;
    private String input;



    public Records() {
        // Required empty public constructor
    }
    public interface FragmentAlistner{
        void onInputAsent(CharSequence input);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_records,container,false);
        ListView listView = (ListView) view.findViewById(R.id.list_view);

        final String[] values = new String[]{
                "Most Run",
                "Most Sixes",
                "Highest Scores",
                "Best Batting Strike Rate",
                "Most Fifties",
                "Most Centuries",
                "Fastest Fifties",
                "Fastest Centuries",
                "Most Wickets",
                "Best Bowling Economy"};

        ListAdapter adapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()),android.R.layout.simple_list_item_1,android.R.id.text1,values);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String inp= values[position];
                if(position==0){
                    input=inp;
                }else if(position==1){
                    input=inp;
                }else if(position==2){
                    input=inp;
                }else if(position==3){
                    input=inp;
                }else if(position==4){
                    input=inp;
                }else if(position==5){
                    input=inp;
                }else if(position==6){
                    input=inp;
                }else if(position==7){
                    input=inp;
                }else if(position==8){
                    input=inp;
                }else {
                    input=inp;
                }
                Fragment f = new Records_Previews();
                Bundle args = new Bundle();
                args.putString("Record Name",input);
                f.setArguments(args);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().addToBackStack(null)
                        .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                        .replace(R.id.fragment_container,f).commit();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setTitle("Record Corner");
        }
    }
}
