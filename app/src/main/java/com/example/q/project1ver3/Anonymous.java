package com.example.q.project1ver3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Anonymous extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.anonymous, container, false);
        Button gotobtn = (Button) rootView.findViewById(R.id.worldcupbtn);
        Button mapBtn =(Button) rootView.findViewById(R.id.map_btn);

        gotobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                Intent intent = new Intent(context, VideoDemoActivity.class);
                context.startActivity(intent);
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                Intent intent = new Intent(context, MapsActivityCurrentPlace.class);
                context.startActivity(intent);
            }
        });

        return rootView;
    }
}