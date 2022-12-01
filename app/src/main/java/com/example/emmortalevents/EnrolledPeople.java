package com.example.emmortalevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;

public class EnrolledPeople extends AppCompatActivity {

    private FirebaseFirestore fireDb;
    private CollectionReference employeesRef;
    ListView peopleList;
    Button addNewPersonBtn;
    ArrayList<String> employee = new ArrayList<>();
    ArrayList<String> employeeIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled_people);

       peopleList = findViewById(R.id.people_list);
       addNewPersonBtn = findViewById(R.id.new_person_btn);

        fireDb = FirebaseFirestore.getInstance();
        employeesRef = fireDb.collection("employees");
        loadNotes();

        peopleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), SelectedPerson.class);
                intent.putExtra("employeeId", employeeIds.get(i));
                startActivity(intent);
            }

        });

        addNewPersonBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddNewPerson.class));
            }
        }));
    }

    public void loadNotes(){
        employeesRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots){

                            String employeeId = documentSnapshots.getId();
                            Employees employees = documentSnapshots.toObject(Employees.class);
                            String enrolledEvent = employees.getEvent();

                            employee.add(enrolledEvent);
                            employeeIds.add(employeeId);

                        }


                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, employee);
                        peopleList.setAdapter(adapter);
                    }
                });



    }

}
