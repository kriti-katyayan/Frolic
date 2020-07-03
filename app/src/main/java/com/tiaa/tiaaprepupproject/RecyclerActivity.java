package com.tiaa.tiaaprepupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        items=new ArrayList<>();
        items.add("First card view item");
        items.add("Second card view item");
        items.add("Third card view item");
        items.add("Fourth card view item");
        items.add("Fifth card view item");
        items.add("Sixth  card view item");
        items.add("Seventh card view item");
        items.add("Eighth card view item");

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Adapter(this,items);
        recyclerView.setAdapter(adapter);
    }
}