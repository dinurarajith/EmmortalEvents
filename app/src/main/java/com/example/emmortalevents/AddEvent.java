package com.example.emmortalevents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.type.DateTime;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddEvent extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText eventEt,dateEt,clientEt,venueEt,decoEt,cakeEt,musicEt,flowerEt,lightsEt;
    Button addBtn;
    FirebaseFirestore fStore;
    CheckBox venueCb, cakeCb,musicCb,decoCb,lightsCb,flowerCb;
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
        setContentView(R.layout.activity_add_event);



        lightsEt = findViewById(R.id.lights_et);
        flowerEt = findViewById(R.id.flower_et);
        musicEt = findViewById(R.id.music_et);
        cakeEt = findViewById(R.id.cake_et);
        decoEt = findViewById(R.id.deco_et);
        venueEt = findViewById(R.id.venue_et);
        clientEt = findViewById(R.id.client_et);
        dateEt = findViewById(R.id.date_et);
        eventEt = findViewById(R.id.event_et);
        addBtn = findViewById(R.id.add_btn);
        venueCb = findViewById(R.id.venue_cb);
        cakeCb = findViewById(R.id.cake_cb);
        musicCb = findViewById(R.id.music_cb);
        decoCb = findViewById(R.id.deco_cb);
        lightsCb = findViewById(R.id.lights_cb);
        flowerCb = findViewById(R.id.flower_cb);
        fStore = FirebaseFirestore.getInstance();

        dateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new Date();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        venueCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                venueEt.setEnabled(venueCb.isChecked());
            }
        });

        cakeCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cakeEt.setEnabled(cakeCb.isChecked());
            }
        });

        musicCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicEt.setEnabled(musicCb.isChecked());
            }
        });

        decoCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decoEt.setEnabled(decoCb.isChecked());
            }
        });

        lightsCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightsEt.setEnabled(lightsCb.isChecked());
            }
        });

        flowerCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowerEt.setEnabled(flowerCb.isChecked());
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


    }

    private void addEvent() {
        String event = eventEt.getText().toString().trim();
//        String date = dateEt.getText().toString().trim();
        String client = clientEt.getText().toString().trim();
        String venue = venueEt.getText().toString().trim();
        String cake = cakeEt.getText().toString().trim();
        String deco = decoEt.getText().toString().trim();
        String music = musicEt.getText().toString().trim();
        String lights = lightsEt.getText().toString().trim();
        String flower = flowerEt.getText().toString().trim();

        Toast.makeText(AddEvent.this, "Event Created", Toast.LENGTH_SHORT).show();

        CollectionReference collectionReference = fStore.collection("events");

        Map<String,Object> e = new HashMap<>();
        e.put("EventName",event);
        e.put("Date",date);
        e.put("Client",client);
        e.put("Venue",venue);
        e.put("Cake",cake);
        e.put("Deco",deco);
        e.put("Music",music);
        e.put("Lights",lights);
        e.put("Flowers",flower);

        collectionReference.add(e);
    }
}