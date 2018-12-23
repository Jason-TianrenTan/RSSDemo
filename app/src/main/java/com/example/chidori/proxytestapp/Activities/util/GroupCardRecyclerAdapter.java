package com.example.chidori.proxytestapp.Activities.util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chidori.proxytestapp.Activities.ListActivity;
import com.example.chidori.proxytestapp.Activities.TabActivity;
import com.example.chidori.proxytestapp.Activities.entity.GroupCard;
import com.example.chidori.proxytestapp.R;

import java.util.List;

public class GroupCardRecyclerAdapter extends RecyclerView.Adapter<GroupCardRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<GroupCard> cardList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView card_id;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            card_id = (TextView) view.findViewById(R.id.card_group_id);
            imageView =(ImageView)view.findViewById(R.id.card_group_head);
        }
    }

    public GroupCardRecyclerAdapter(List<GroupCard> list) {
        this.cardList = list;
    }

    @NonNull
    @Override
    public GroupCardRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.card_group,parent,false);
        return new GroupCardRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final GroupCard groupCard = cardList.get(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(context,TabActivity.class);
                intent.putExtra("title",groupCard.getTitle());
                intent.putExtra("id", groupCard.getId());
                Toast.makeText(context, groupCard.getId(), Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });

        holder.card_id.setText(groupCard.getId());
    }


    @Override
    public int getItemCount() {
        return cardList.size();
    }
}

