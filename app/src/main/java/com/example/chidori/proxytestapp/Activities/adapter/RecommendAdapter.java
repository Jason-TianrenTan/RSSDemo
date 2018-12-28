package com.example.chidori.proxytestapp.Activities.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chidori.proxytestapp.Activities.ReaderActivity;
import com.example.chidori.proxytestapp.Activities.entity.Recommend;
import com.example.chidori.proxytestapp.R;

import java.util.ArrayList;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    private ArrayList<Recommend> recommends;
    Context mContext;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null)
            mContext = viewGroup.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.recommend_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                String url = recommends.get(position).getUrl();
                Intent intent = new Intent(mContext, ReaderActivity.class);
                intent.putExtra("link", url);
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Recommend recommend = recommends.get(i);
        viewHolder.recTitle.setText(recommend.getTitle());
        viewHolder.recDate.setText(recommend.getDate());
        viewHolder.recDesc.setText(recommend.getDesc());
        viewHolder.recSource.setText(recommend.getSource());
    }

    @Override
    public int getItemCount() {
        return recommends.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView recTitle, recDate, recSource, recDesc;
        CardView rootView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = (CardView)itemView;
            recTitle = (TextView)itemView.findViewById(R.id.rec_title);
            recDate = (TextView)itemView.findViewById(R.id.rec_date);
            recSource = (TextView)itemView.findViewById(R.id.rec_source);
            recDesc = (TextView)itemView.findViewById(R.id.rec_desc);
        }
    }

    public RecommendAdapter(ArrayList<Recommend> recommends) {
        this.recommends = recommends;
    }
}
