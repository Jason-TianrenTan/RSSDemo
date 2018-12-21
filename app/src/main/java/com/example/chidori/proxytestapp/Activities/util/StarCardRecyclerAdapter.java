package com.example.chidori.proxytestapp.Activities.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chidori.proxytestapp.Activities.ListActivity;
import com.example.chidori.proxytestapp.Activities.entity.StarCard;
import com.example.chidori.proxytestapp.R;

import java.util.List;

public class StarCardRecyclerAdapter extends RecyclerView.Adapter<StarCardRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<StarCard> cardList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView card_id;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            card_id = (TextView)view.findViewById(R.id.card_star_id);
        }
    }

    public StarCardRecyclerAdapter(List<StarCard> list) {
        this.cardList = list;
    }

    @NonNull
    @Override
    public StarCardRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.card_star,parent,false);
        return new StarCardRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final StarCard starCard = cardList.get(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(context, ListActivity.class);
                Bundle args = new Bundle();
                args.putInt("type",staticData.SCJ);
                args.putString("id",(String)holder.card_id.getText());
                intent.putExtras(args);
                context.startActivity(intent);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                setDialog(position);
                return true;
            }
        });
        holder.card_id.setText(starCard.getId());
    }

    private void setDialog(int position){
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("是否删除收藏夹:"+cardList.get(position).getId())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        staticData.SCJList.remove(position);
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public void resetCardList(List<StarCard> list){
        this.cardList.clear();
        this.cardList.addAll(list);
        notifyDataSetChanged();
    }
}
