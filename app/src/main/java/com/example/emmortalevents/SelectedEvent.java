package com.example.emmortalevents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SelectedEvent extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    TextView eventEt,dateEt,clientEt,venueEt,decoEt,cakeEt,musicEt,flowerEt,lightsEt;
    Button updateBtn;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String eventId;
    private DocumentReference documentReference;
    private FirebaseFirestore db;
    Long date;

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        date = c.getTimeInMillis();
        dateEt.setText(currentDateString);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_event);

        Intent intent = getIntent();
        String eventId = intent.getStringExtra("eventId");
        Log.i("evId", eventId);

        db = FirebaseFirestore.getInstance();
        documentReference = db.collection("events").document(eventId);


        eventEt = findViewById(R.id.pname_et);
        dateEt = findViewById(R.id.pdate_et);
        clientEt = findViewById(R.id.pclient_et);
        venueEt = findViewById(R.id.pvenue_et);
        decoEt = findViewById(R.id.pdeco_et);
        cakeEt = findViewById(R.id.pcake_et);
        musicEt = findViewById(R.id.pmusic_et);
        lightsEt = findViewById(R.id.plights_et);
        flowerEt = findViewById(R.id.pflowers_et);
        updateBtn = findViewById(R.id.update_btn);


        fStore = FirebaseFirestore.getInstance();




        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                date = documentSnapshot.getLong("Date");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = formatter.format(new Date(date));

                eventEt.setText(documentSnapshot.getString("EventName"));
                dateEt.setText(dateString);
                clientEt.setText(documentSnapshot.getString("Client"));
                venueEt.setText(documentSnapshot.getString("Venue"));
                cakeEt.setText(documentSnapshot.getString("Cake"));
                decoEt.setText(documentSnapshot.getString("Deco"));
                musicEt.setText(documentSnapshot.getString("Music"));
                lightsEt.setText(documentSnapshot.getString("Lights"));
                flowerEt.setText(documentSnapshot.getString("Flowers"));
            }
        });

        dateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new com.example.emmortalevents.Date();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEvent();
                startActivity(new Intent(getApplicationContext(),CurrentEvents.class));
            }
        });

    }



    public void updateEvent() {

        String event = eventEt.getText().toString();
//        String date = dateTv.getText().toString();
        String client = clientEt.getText().toString();
        String venue = venueEt.getText().toString();
        String cake = cakeEt.getText().toString();
        String deco = decoEt.getText().toString();
        String music = musicEt.getText().toString();
        String lights = lightsEt.getText().toString();
        String flower = flowerEt.getText().toString();

        Map<String,Object> events = new HashMap<>();
        events.put("EventName",event);
        events.put("Date",date);
        events.put("Client",client);
        events.put("Venue",venue);
        events.put("Deco",deco);
        events.put("Cake",cake);
        events.put("Music",music);
        events.put("Lights",lights);
        events.put("Flowers",flower);

        documentReference.set(events, SetOptions.merge());
    }


}