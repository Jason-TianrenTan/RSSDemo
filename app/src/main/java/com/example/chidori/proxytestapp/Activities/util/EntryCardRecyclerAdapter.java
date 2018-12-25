package com.example.chidori.proxytestapp.Activities.util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
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

import com.example.chidori.proxytestapp.Activities.ListActivity;
import com.example.chidori.proxytestapp.Activities.ReaderActivity;
import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.R;

import java.util.List;

public class EntryCardRecyclerAdapter extends RecyclerView.Adapter<EntryCardRecyclerAdapter.ViewHolder>{
    private Context context;
    private List<Entry> cardList;
    private int option;

    public static final int tabAC = 0;
    public static final int listAC = 1;
    public static final int home = 2;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView card_title;
        TextView card_detail;
        ImageButton card_star_btn;
        boolean stared;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            card_title = (TextView)view.findViewById(R.id.card_intro_title);
            card_detail = (TextView)view.findViewById(R.id.card_intro_detail);
            card_star_btn = (ImageButton)view.findViewById(R.id.card_intro_btn);
        }
    }

    public EntryCardRecyclerAdapter(List<Entry> list,int option) {
        this.cardList = list;
        this.option = option;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.card_entry,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Entry entryCard = cardList.get(position);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(context, ReaderActivity.class);
                intent.putExtra("link", entryCard.getEntryLink());
                context.startActivity(intent);
            }
        });

        holder.card_title.setText(entryCard.getTitle());
        holder.card_detail.setText(entryCard.getSourceName());

        holder.stared = StaticTool.starList.contains(entryCard.getEntryId());
        if(holder.stared) holder.card_star_btn.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.stared));
        else holder.card_star_btn.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.star));

        holder.card_star_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.stared){
                    Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();
                    switch (option){
                        case tabAC:{
                            //TabFragment.getTabACPresenter()
                            break;
                        }
                        case listAC:{
                            //ListActivity.getPresenter()
                            break;
                        }
                        case home:{
                            //TabFragment.getHomePresenter()
                            break;
                        }
                    }
                }
                else {
                    StaticTool.opPosition = position;
                    StaticTool.temp = entryCard.getEntryId();
                    setInputDialog("添加到收藏夹","请选择收藏夹");
                }
            }
        });
    }

    private void setInputDialog(String title,String message){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title).setMessage(message);
        View v = View.inflate(context,R.layout.view_dialog_collection_radio, null);

        CollectionRadioRecyclerAdapter recyclerAdapter = new CollectionRadioRecyclerAdapter(StaticTool.collectionCardList);

        RecyclerView recyclerView=(RecyclerView)v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(recyclerAdapter);
        dialog.setView(v);

        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                String input = recyclerAdapter.getCheckedCollectionId();
                if (input == null) {
                    Toast.makeText(context, "请选择收藏夹", Toast.LENGTH_SHORT).show();
                }
                else {
                    switch (option){
                        case tabAC:{
                            TabFragment.getTabACPresenter().doAddEntry(input,StaticTool.temp);
                            break;
                        }
                        case listAC:{
                            ListActivity.getPresenter().doAddEntryToCollection(input,StaticTool.temp);
                            break;
                        }
                        case home:{
                            TabFragment.getHomePresenter().doAddEntry(input,StaticTool.temp);
                            break;
                        }
                    }
                }
            }
        }).setNegativeButton("取消", null).show();
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
