package com.example.chidori.proxytestapp.Activities.util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.content.Context;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chidori.proxytestapp.Activities.ElseActivity;
import com.example.chidori.proxytestapp.Activities.entity.IntroCard;
import com.example.chidori.proxytestapp.R;

import java.util.List;

public class IntroCardRecyclerAdapter extends RecyclerView.Adapter<IntroCardRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<IntroCard> cardList;
    private int option; //staticData.WDDR-不显示收藏按钮; default-显示收藏按钮;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView card_id;
        TextView card_title;
        TextView card_detail;
        ImageButton card_star_btn;
        boolean stared;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            card_id = (TextView)view.findViewById(R.id.card_intro_id);
            card_title = (TextView)view.findViewById(R.id.card_intro_title);
            card_detail = (TextView)view.findViewById(R.id.card_intro_detail);
            card_star_btn = (ImageButton)view.findViewById(R.id.card_intro_btn);
        }
    }

    public IntroCardRecyclerAdapter(List<IntroCard> list, int option) {
        this.cardList = list;
        this.option = option;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.card_intro,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final IntroCard introCard = cardList.get(position);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(context, ElseActivity.class);
                intent.putExtra("id",introCard.getId());
                context.startActivity(intent);
            }
        });

        holder.card_id.setText(introCard.getId());
        holder.card_title.setText(introCard.getTitle());
        holder.card_detail.setText(introCard.getDetail());

        if(option == staticData.WDDR) {
            holder.card_star_btn.setVisibility(View.GONE);
            holder.cardView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    setDialog(position);
                    return true;
                }
            });
        }
        else {
            holder.stared = staticData.starList.contains((String)holder.card_id.getText());
            if(holder.stared) holder.card_star_btn.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.stared));
            else holder.card_star_btn.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.star));
            holder.card_star_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.stared){
                        //从收藏表中删除
                        Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();
                        holder.stared = false;
                        staticData.starList.remove((String)holder.card_id.getText());
                        ((ImageButton)v).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.star));
                    }
                    else {
                        //在收藏表中插入
                        Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                        holder.stared = true;
                        staticData.starList.add((String)holder.card_id.getText());
                        ((ImageButton)v).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.stared));
                    }
                }
            });
        }
    }

    private void setDialog(int position){
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("是否删除已导入文件:"+cardList.get(position).getId())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        staticData.WDDRList.remove(position);
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

    public void resetCardList(List<IntroCard> list){
        if(cardList==list) return;
        cardList.clear();
        cardList.addAll(list);
        notifyDataSetChanged();
    }
}
