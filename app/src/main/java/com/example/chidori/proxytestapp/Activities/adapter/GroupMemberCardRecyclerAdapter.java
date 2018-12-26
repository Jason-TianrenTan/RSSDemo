package com.example.chidori.proxytestapp.Activities.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chidori.proxytestapp.Activities.entity.GroupMember;
import com.example.chidori.proxytestapp.R;

import java.util.List;

public class GroupMemberCardRecyclerAdapter extends RecyclerView.Adapter<GroupMemberCardRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<GroupMember> cardList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView card_id;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            card_id = (TextView) view.findViewById(R.id.card_member_id);
            imageView =(ImageView)view.findViewById(R.id.card_member_head);
        }
    }

    public GroupMemberCardRecyclerAdapter(List<GroupMember> list) {
        this.cardList = list;
    }

    @NonNull
    @Override
    public GroupMemberCardRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.card_user,parent,false);
        return new GroupMemberCardRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final GroupMember groupCard = cardList.get(position);
        holder.card_id.setText(groupCard.getUsername());
    }


    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public void resetCardList(List<GroupMember> list){
        this.cardList = list;
        notifyDataSetChanged();
    }
}

