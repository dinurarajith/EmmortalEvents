package com.example.emmortalevents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class SelectedPerson extends AppCompatActivity {

    TextView eventEt,nameEt,purposeEt,phoneEt,emailEt,priceEt,addressEt;
    Button deleteBtn;
    private DocumentReference employeeReference;
    private FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_person);

        Intent intent = getIntent();
        String empId = intent.getStringExtra("employeeId");
        Log.i("empId", empId);

        database = FirebaseFirestore.getInstance();
        employeeReference = database.collection("employees").document(empId);


        eventEt = findViewById(R.id.eevent_et);
        nameEt = findViewById(R.id.ename_et);
        purposeEt = findViewById(R.id.epurpose_et);
        phoneEt = findViewById(R.id.ephone_et);
        addressEt = findViewById(R.id.eaddress_et);
        priceEt = findViewById(R.id.eprice_et);
        emailEt = findViewById(R.id.eemail_et);
        deleteBtn = findViewById(R.id.delete_btn);


        employeeReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                eventEt.setText(documentSnapshot.getString("Event"));
                nameEt.setText(documentSnapshot.getString("PersonName"));
                purposeEt.setText(documentSnapshot.getString("Purpose"));
                phoneEt.setText(documentSnapshot.getString("Phone"));
                priceEt.setText(documentSnapshot.getString("Price"));
                addressEt.setText(documentSnapshot.getString("Address"));
                emailEt.setText(documentSnapshot.getString("Email"));
            }
        });
        
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employeeReference.delete();
                Toast.makeText(SelectedPerson.this, "Employee Profile Successfully Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), EnrolledPeople.class));
            }



        });

    }





}
