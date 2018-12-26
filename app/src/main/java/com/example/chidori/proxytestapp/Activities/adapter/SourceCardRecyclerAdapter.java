package com.example.chidori.proxytestapp.Activities.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chidori.proxytestapp.Activities.EntryBySourceActivity;
import com.example.chidori.proxytestapp.Activities.SourceActivity;
import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.Activities.fragment.NavHomeFragment;
import com.example.chidori.proxytestapp.Activities.fragment.SourceFragment;
import com.example.chidori.proxytestapp.Activities.util.StaticTool;
import com.example.chidori.proxytestapp.R;

import java.util.List;

public class SourceCardRecyclerAdapter extends RecyclerView.Adapter<SourceCardRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Source> cardList;
    private int option;
    private String opId;

    public static final int home = 0;
    public static final int list = 1;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView card_id;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            card_id = (TextView)view.findViewById(R.id.card_source_id);
        }
    }

    public SourceCardRecyclerAdapter(List<Source> list,int option) {
        this.cardList = list;
        this.option = option;
    }

    @NonNull
    @Override
    public SourceCardRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.card_source,parent,false);
        return new SourceCardRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Source sourceCard = cardList.get(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(context, EntryBySourceActivity.class);
                intent.putExtra("id", sourceCard.getSourceId());
                intent.putExtra("title",sourceCard.getName());
                opId = sourceCard.getSourceId();
                StaticTool.opPosition = position;
                context.startActivity(intent);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                setDialog(position);
                opId = sourceCard.getSourceId();
                StaticTool.opPosition = position;
                return true;
            }
        });
        holder.card_id.setText(sourceCard.getName());
    }

    private void setDialog(int position){
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("是否取消订阅:"+cardList.get(position).getName())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (option){
                            case home:{
                                SourceFragment.getPresenter().deleteSource(opId);
                                break;
                            }
                            case list:{
                                SourceActivity.getPresenter().deleteSource(opId);
                                break;
                            }
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public void resetCardList(List<Source> list){
        this.cardList = list;
        notifyDataSetChanged();
    }
}
