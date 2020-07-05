package com.tiaa.tiaaprepupproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FirebaseRecycleActivity extends AppCompatActivity {

    private RecyclerView mFireStoreList;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_recycle);

        firebaseFirestore = FirebaseFirestore.getInstance();
        mFireStoreList = findViewById(R.id.firestore_list);

        //Steps for connecting
        //Query
        Query query = firebaseFirestore.collection("Events");
        //RecyclerOptions
        FirestoreRecyclerOptions<EventModel> options = new FirestoreRecyclerOptions.Builder<EventModel>()
                .setQuery(query, EventModel.class)
                .build();
        //ViewHolder
        adapter = new FirestoreRecyclerAdapter<EventModel, EventHolder>(options) {
            @NonNull
            @Override
            public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view, parent, false);
                return new EventHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull EventHolder holder, int position, @NonNull EventModel model) {

                holder.eventName.setText(model.getEventName());
                holder.eventDesc.setText(model.getEventDesc());
            }
        };

        mFireStoreList.setHasFixedSize(true);
        mFireStoreList.setLayoutManager(new LinearLayoutManager(this));
        mFireStoreList.setAdapter(adapter);
    }


        private class EventHolder extends RecyclerView.ViewHolder {

            private TextView eventName,eventDesc;
            public EventHolder(@NonNull View itemView) {
                super(itemView);

                eventName = itemView.findViewById(R.id.eventTitle);
                eventDesc = itemView.findViewById(R.id.eventDesc);
            }


        }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}


