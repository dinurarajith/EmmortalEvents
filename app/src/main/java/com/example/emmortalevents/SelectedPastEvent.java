package com.example.emmortalevents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectedPastEvent extends AppCompatActivity {

    TextView eventTv,dateTv,clientTv,venueTv,decoTv,cakeTv,musicTv,flowerTv,lightsTv;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_past_event);
        eventTv = findViewById(R.id.event_name_tv);
        dateTv = findViewById(R.id.date_tv);
        clientTv = findViewById(R.id.client_name_tv);
        venueTv = findViewById(R.id.venue_tv);
        decoTv = findViewById(R.id.deco_tv);
        cakeTv = findViewById(R.id.cake_tv);
        musicTv = findViewById(R.id.music_tv);
        lightsTv = findViewById(R.id.lights_tv);
        flowerTv = findViewById(R.id.flower_tv);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        Intent intent = getIntent();
        String eventId = intent.getStringExtra("eventId");
        Log.i("evId", eventId);


        DocumentReference documentReference = fStore.collection("events").document(eventId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = formatter.format(new Date(documentSnapshot.getLong("Date")));

                eventTv.setText(documentSnapshot.getString("EventName"));
                dateTv.setText(dateString);
                clientTv.setText(documentSnapshot.getString("Client"));
                venueTv.setText(documentSnapshot.getString("Venue"));
                cakeTv.setText(documentSnapshot.getString("Cake"));
                decoTv.setText(documentSnapshot.getString("Deco"));
                musicTv.setText(documentSnapshot.getString("Music"));
                lightsTv.setText(documentSnapshot.getString("Lights"));
                flowerTv.setText(documentSnapshot.getString("Flowers"));
            }
        });

    }



}