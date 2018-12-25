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
import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.R;

import java.util.List;

public class CollectionCardRecyclerAdapter extends RecyclerView.Adapter<CollectionCardRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Collection> cardList;
    private boolean option;
    private String opId;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView card_title;
        TextView card_level;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            card_title = (TextView)view.findViewById(R.id.card_collection_title);
            card_level = (TextView)view.findViewById(R.id.card_collection_level);
        }
    }

    public CollectionCardRecyclerAdapter(List<Collection> list,boolean option) {
        this.cardList = list;
        this.option = option;
    }

    @NonNull
    @Override
    public CollectionCardRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.card_collection,parent,false);
        return new CollectionCardRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Collection collectionCard = cardList.get(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(context, ListActivity.class);
                intent.putExtra("type",ListActivity.collection_entry);
                intent.putExtra("title",collectionCard.getName());
                intent.putExtra("id", collectionCard.getCollectionId());
                StaticTool.opPosition=position;
                context.startActivity(intent);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if(option){
                    StaticTool.opPosition = position;
                    opId = collectionCard.getCollectionId();
                    setDeleteDialog(collectionCard.getName());
                }
                return true;
            }
        });
        holder.card_title.setText(collectionCard.getName());
        holder.card_level.setText(collectionCard.getPublicStatus());
    }

    private void setDeleteDialog(String name){
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("是否删除收藏夹:"+name)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ListActivity.getPresenter().deleteCollection(opId);
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
