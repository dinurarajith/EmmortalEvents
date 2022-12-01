package com.example.emmortalevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;


public class PastEvents extends AppCompatActivity {

    private FirebaseFirestore db;
    private CollectionReference notebookRef;
    ListView past_list;
    ArrayList<String> events = new ArrayList<>();
    ArrayList<String> eventIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_events);
        past_list = findViewById(R.id.past_list);

        db = FirebaseFirestore.getInstance();
        notebookRef = db.collection("events");
        loadNotes();

        past_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), SelectedPastEvent.class);
                intent.putExtra("eventId", eventIds.get(i));
                startActivity(intent);
            }

        });
    }

    public void loadNotes(){
        notebookRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots){

                            Long currentTime = Calendar.getInstance().getTimeInMillis();

                            String eventId = documentSnapshots.getId();
                            Event event = documentSnapshots.toObject(Event.class);
                            String eventName = event.getEventName();
                            Long date =event.getDate();

                            if (date < currentTime) {
                                events.add(eventName);
                                eventIds.add(eventId);
                            }

                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, events);
                        past_list.setAdapter(adapter);
                    }
                });



    }
}