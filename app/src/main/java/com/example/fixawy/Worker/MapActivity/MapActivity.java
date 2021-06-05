package com.example.fixawy.Worker.MapActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fixawy.Pojos.HistoryWorker;
import com.example.fixawy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity {

   private DatabaseReference databaseReference;
   private  DatabaseReference databaseReference2;
    List<HistoryWorker>historyWorkers;
    String worker_jobTitle,Worker_phone,phone;
    long maxid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
         Worker_phone = intent.getStringExtra("phone_worker");
         worker_jobTitle = intent.getStringExtra("jobTitle_worker");



        String name = intent.getStringExtra("dataHistoryName");
        phone = intent.getStringExtra("dataHistoryPhone");
        String date = intent.getStringExtra("dataHistoryDate");
        String time = intent.getStringExtra("dataHistoryTime");
        String address = intent.getStringExtra("dataHistoryLocation");

        Toast.makeText(this, Worker_phone, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, worker_jobTitle, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, phone, Toast.LENGTH_SHORT).show();



        historyWorkers = new ArrayList<>();
        HistoryWorker historyWorker =new HistoryWorker();
        historyWorker.setName(name);
        historyWorker.setPhone(phone);
        historyWorker.setDate(date);
        historyWorker.setTime(time);
        historyWorker.setAddress(address);

        historyWorkers.add(historyWorker);





        //to add history data
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Worker");

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference2.child(worker_jobTitle).child("Data").child(Worker_phone).child("HistoryWorker").push().setValue(historyWorkers);


        //to delete data from request
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Worker").child(worker_jobTitle).child("Data");
        databaseReference2.child(Worker_phone).child("Job Accepted").child(phone).setValue(null);







    }



}