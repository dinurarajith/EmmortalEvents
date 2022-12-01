package com.example.emmortalevents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class AddNewPerson extends AppCompatActivity {

    EditText ePersonName,eEmail,eAddress,ePhone,ePurpose,ePrice,eEvent;
    Button addPersonBtn;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_person);

        ePersonName = findViewById(R.id.new_name_et);
        eEmail = findViewById(R.id.new_email_et);
        eAddress = findViewById(R.id.new_address_et);
        ePhone = findViewById(R.id.new_phone_et);
        ePurpose = findViewById(R.id.purpose_et);
        ePrice = findViewById(R.id.price_et);
        eEvent = findViewById(R.id.new_event_name_et);
        addPersonBtn = findViewById(R.id.add_person_btn);

        fStore = FirebaseFirestore.getInstance();



        addPersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = eEmail.getText().toString().trim();
                String address = eAddress.getText().toString().trim();
                String personName = ePersonName.getText().toString();
                String phone = ePhone.getText().toString().trim();
                String event = eEvent.getText().toString().trim();
                String purpose = ePurpose.getText().toString().trim();
                String price = ePrice.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    eEmail.setError("Email is Required.");
                    return;
                }
               if(TextUtils.isEmpty(purpose)){
                   ePurpose.setError("Purpose is Required.");
                    return;
                }

                if(TextUtils.isEmpty(personName)){
                    ePersonName.setError("Full Name is Required.");
                    return;
                }

                if(TextUtils.isEmpty(phone)){
                    ePhone.setError("Contact Number is Required.");
                    return;
                }

                if(TextUtils.isEmpty(address)){
                    eAddress.setError("Address is Required.");
                    return;
                }

                if(TextUtils.isEmpty(price)){
                    ePrice.setError("Price is Required.");
                    return;
                }

                if(TextUtils.isEmpty(event)){
                    eEvent.setError("Event Name is Required.");
                    return;
                }


                Toast.makeText(AddNewPerson.this, "Employee Profile Successfully Created", Toast.LENGTH_SHORT).show();

                CollectionReference collectionReference = fStore.collection("employees");

                Map<String,Object> e = new HashMap<>();
                e.put("PersonName",personName);
                e.put("Event",event);
                e.put("Purpose",purpose);
                e.put("Address",address);
                e.put("Price",price);
                e.put("Email",email);
                e.put("Phone",phone);

                collectionReference.add(e);

                startActivity(new Intent(getApplicationContext(), EnrolledPeople.class));

            }
        });

    }
}