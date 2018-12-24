package com.example.chidori.proxytestapp.Activities.util;

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
import android.widget.Toast;

import com.example.chidori.proxytestapp.Activities.ListActivity;
import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.R;

import java.util.List;

public class SourceCardRecyclerAdapter extends RecyclerView.Adapter<SourceCardRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Source> cardList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView card_id;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            card_id = (TextView)view.findViewById(R.id.card_source_id);
        }
    }

    public SourceCardRecyclerAdapter(List<Source> list) {
        this.cardList = list;
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
                Intent intent = new Intent(context, ListActivity.class);
                intent.putExtra("type",ListActivity.source_entry);
                intent.putExtra("id", sourceCard.getSourceId());
                intent.putExtra("title",sourceCard.getName());
                StaticTool.opId=sourceCard.getSourceId();
                StaticTool.opPosition=position;
                context.startActivity(intent);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                setDialog(position);
                StaticTool.opId=sourceCard.getSourceId();
                StaticTool.opPosition=position;
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
                        StaticTool.sourceCardList.remove(position);
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
}
