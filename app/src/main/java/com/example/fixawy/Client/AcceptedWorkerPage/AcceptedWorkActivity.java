package com.example.fixawy.Client.AcceptedWorkerPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fixawy.Pojos.MakeOrder;
import com.example.fixawy.Pojos.User;
import com.example.fixawy.R;
import com.example.fixawy.Worker.WorkerProfilePage.WorkerProfileActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class AcceptedWorkActivity extends AppCompatActivity {

    ImageView imageViewWorker;
    TextView textViewNameOfWorker,textViewNumOfJobs,textViewNumOfLikes,textViewNumOfDisLike,textViewAddress,textViewPhone;
    RatingBar ratingBar;
    Button buttonAccept,buttonCancel;
    String phoneWorker,phoneClient,date,time,location,phoneClientNum,nameClient,typeOfOrder,jobTitle,phoneWorkerNum,nameOfWorker,workerJobTitle;
    DatabaseReference reference1,reference2,reference4,reference5;
    DatabaseReference referenceDelete1,referenceDelete2;
    AcceptWorkModel acceptWorkModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepted_work);

        imageViewWorker = findViewById(R.id.emp_img);
        textViewNameOfWorker = findViewById(R.id.emp_name);
        textViewNumOfJobs = findViewById(R.id.num_of_emp_jobs);
        textViewNumOfLikes = findViewById(R.id.num_of_emp_likes);
        textViewNumOfDisLike = findViewById(R.id.num_of_emp_dislike);
        textViewAddress = findViewById(R.id.emp_address);
        textViewPhone = findViewById(R.id.emp_phone);
        ratingBar = findViewById(R.id.emp_rate);
        buttonAccept = findViewById(R.id.accept_btn);
        buttonCancel = findViewById(R.id.cancel_btn);

        phoneWorker = getIntent().getStringExtra("phoneWorker");
        phoneClient = getIntent().getStringExtra("phoneClient");
        workerJobTitle = getIntent().getStringExtra("workerJobTitle");


        Toast.makeText(this, phoneClient, Toast.LENGTH_SHORT).show();

        reference1 = FirebaseDatabase.getInstance().getReference().child("Client").child("make order").child(phoneClient).child("Accepted").child(phoneWorker);
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String url = snapshot.child("image").getValue(String.class);
                String nameOfWorker = snapshot.child("nameOfWorker").getValue(String.class);
                String numOfJob = snapshot.child("numOfJob").getValue(String.class);
                String numOfLike = snapshot.child("numOfLike").getValue(String.class);
                String numOfDisLike = snapshot.child("numOfDisLike").getValue(String.class);
                String addressOfWorker = snapshot.child("addressOfWorker").getValue(String.class);
                String phoneOfWorker = snapshot.child("phoneOfWorker").getValue(String.class);
                String rating = snapshot.child("rating").getValue(String.class);

                Picasso.get().load(url).into(imageViewWorker);
                textViewNameOfWorker.setText(nameOfWorker);
                textViewNumOfJobs.setText(numOfJob);
                textViewNumOfLikes.setText(numOfLike);
                textViewNumOfDisLike.setText(numOfDisLike);
                textViewAddress.setText(addressOfWorker);
                textViewPhone.setText(phoneOfWorker);
                ratingBar.setRating(Float.parseFloat(rating));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference4 = FirebaseDatabase.getInstance().getReference();

                reference5 = FirebaseDatabase.getInstance().getReference().child("Client").child("make order").child(phoneClient).child("Accepted").child(phoneWorker);
                reference5.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        reference2 = FirebaseDatabase.getInstance().getReference().child("Worker").child(workerJobTitle).child("order Details").child(phoneClient);
                        reference2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                // get data to restore it to another path
                                date = snapshot.child("date").getValue(String.class);
                                time = snapshot.child("time").getValue(String.class);
                                location = snapshot.child("location").getValue(String.class);
                                phoneClientNum = snapshot.child("requestedPhone").getValue(String.class);
                                nameClient = snapshot.child("userName").getValue(String.class);
                                typeOfOrder = snapshot.child("typeOfOrder").getValue(String.class);
                                jobTitle = workerJobTitle;
                                phoneWorkerNum = phoneWorker;
                                // way to get name of worker
                                nameOfWorker = "testForNow";

                                //set time & date & location & phone for client & name of client for job accepted
                                MakeOrder order = new MakeOrder(time, date, location, phoneClientNum, nameClient);
                                reference4.child("Worker").child(workerJobTitle).child("Data").child(phoneWorker).child("Job Accepted").child(phoneClient).setValue(order);
                                MakeOrder historyOrder = new MakeOrder(time, date, typeOfOrder, jobTitle, nameOfWorker, phoneWorkerNum);
                                reference4.child("Client").child("Data").child(phoneClient).child("History Jobs").child(phoneWorker).setValue(historyOrder);


                                //delete

                                referenceDelete1 = FirebaseDatabase.getInstance().getReference().child("Client").child("make order");
                                referenceDelete1.child(phoneClient).child("Accepted").child(phoneWorkerNum).setValue(null);

                                referenceDelete1.child(phoneClient).child(workerJobTitle).child("order Details").setValue(null);


                                referenceDelete2 = FirebaseDatabase.getInstance().getReference().child("Worker").child(workerJobTitle).child("order Details");
                                referenceDelete2.child(phoneClient).setValue(null);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AcceptedWorkActivity.this, workerJobTitle, Toast.LENGTH_SHORT).show();
                referenceDelete1 = FirebaseDatabase.getInstance().getReference().child("Client").child("make order");
                referenceDelete1.child(phoneClient).child("Accepted").setValue(null);

                referenceDelete1.child(phoneClient).child(workerJobTitle).child("order Details").setValue(null);


                referenceDelete2 = FirebaseDatabase.getInstance().getReference().child("Worker").child(workerJobTitle).child("order Details");
                referenceDelete2.child(phoneClient).setValue(null);
            }
        });



    }
}