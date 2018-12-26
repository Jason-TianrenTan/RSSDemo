package com.example.chidori.proxytestapp.Activities.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chidori.proxytestapp.Activities.CollectionActivity;
import com.example.chidori.proxytestapp.Activities.GroupActivity;
import com.example.chidori.proxytestapp.Activities.LoginActivity;
import com.example.chidori.proxytestapp.Activities.SourceActivity;
import com.example.chidori.proxytestapp.Config;
import com.example.chidori.proxytestapp.R;

public class NavUserFragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_user, null);

        Button button=(Button)view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        TextView tx = (TextView)view.findViewById(R.id.textView);
        tx.setText(Config.username);
        LinearLayout my_import=(LinearLayout)view.findViewById(R.id.my_source);
        my_import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SourceActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout my_favor=(LinearLayout)view.findViewById(R.id.my_collection);
        my_favor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),CollectionActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout my_group=(LinearLayout)view.findViewById(R.id.my_group);
        my_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),GroupActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}