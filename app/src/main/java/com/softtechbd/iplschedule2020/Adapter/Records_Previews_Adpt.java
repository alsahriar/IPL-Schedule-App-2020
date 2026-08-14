package com.softtechbd.iplschedule2020.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softtechbd.iplschedule2020.Model.Records_Previews_M;
import com.softtechbd.iplschedule2020.R;

import java.util.List;

public class Records_Previews_Adpt extends RecyclerView.Adapter<Records_Previews_Adpt.MyViewHolder> {
    private Context context;
    private List<Records_Previews_M> list;

    public Records_Previews_Adpt() {
    }

    public Records_Previews_Adpt(Context context, List<Records_Previews_M> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.records_preview_item,parent,false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int Position =position+1;
        if(Position%2==0){
          holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.divider));
        }
        holder.Pos.setText(Position+".");
        holder.textView1.setText(list.get(position).getTopPlayerName());
        holder.textView2.setText(list.get(position).getTopRecords());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView Pos, textView1;
        private TextView textView2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Pos=(TextView)itemView.findViewById(R.id.top_recorderPos);
            textView1=(TextView)itemView.findViewById(R.id.top_recorderName);
            textView2=(TextView)itemView.findViewById(R.id.record_score);
        }
    }
}
