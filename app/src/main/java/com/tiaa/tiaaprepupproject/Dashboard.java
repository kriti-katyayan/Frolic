package com.tiaa.tiaaprepupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    TextView profile, liveEvents, organise, stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        profile = findViewById(R.id.profile);
        liveEvents = findViewById(R.id.liveEvents);
        organise = findViewById(R.id.organise);
        stats = findViewById(R.id.stats);

        //goto profile activity
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(Dashboard.this, Profile.class);
                startActivity(profile);
            }
        });

        //goto live events
        liveEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recAct = new Intent(Dashboard.this, FirebaseRecycleActivity.class);
                startActivity(recAct);
            }
        });

        //goto organise an event
        organise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent organise=new Intent(Dashboard.this,Organiser.class);
                startActivity(organise);
            }
        });

        //goto user statistics
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stats=new Intent(Dashboard.this,UserStats.class);
                startActivity(stats);
            }
        });
    }
}