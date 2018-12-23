package com.example.chidori.proxytestapp.Activities.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.chidori.proxytestapp.Activities.entity.CollectionCard;
import com.example.chidori.proxytestapp.R;

import java.util.List;

public class CollectionRadioRecyclerAdapter extends RecyclerView.Adapter<CollectionRadioRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<CollectionCard> cardList;
    private int checkedPosition = -1;
    private RadioButton checked_btn;

    static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout cardView;
        TextView card_title;
        TextView card_level;
        RadioButton card_btn;

        public ViewHolder(View view) {
            super(view);
            cardView = (LinearLayout)view;
            card_title = (TextView)view.findViewById(R.id.card_collection_title);
            card_level = (TextView)view.findViewById(R.id.card_collection_level);
            card_btn = (RadioButton)view.findViewById(R.id.card_collection_btn);
        }
    }

    public CollectionRadioRecyclerAdapter(List<CollectionCard> list) {
        this.cardList = list;
    }

    @NonNull
    @Override
    public CollectionRadioRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.card_collection_radio,parent,false);
        return new CollectionRadioRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CollectionCard collectionCard = cardList.get(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(checkedPosition != position){
                    if(checked_btn != null) checked_btn.setChecked(false);
                    checkedPosition = position;
                    checked_btn = holder.card_btn;
                    checked_btn.setChecked(true);
                }
            }
        });
        holder.card_title.setText(collectionCard.getTitle());
        holder.card_level.setText(collectionCard.getLevel());
    }

    public String getCheckedCollectionId() {
        if(checkedPosition == -1) return null;
        else return cardList.get(checkedPosition).getId();
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
