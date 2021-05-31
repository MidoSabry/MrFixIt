package com.example.fixawy.Client.AcceptedWorkerPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    DatabaseReference reference1,reference2,reference3,reference4,reference5;

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

                                    //set time & date & location & phone for client & name of client for job accepted
                                    MakeOrder order = new MakeOrder(time, date, location, phoneClientNum, nameClient);
                                    reference4.child("Worker").child(workerJobTitle).child("Data").child(phoneWorker).child("Job Accepted").child(phoneClient).setValue(order);
                                    MakeOrder historyOrder = new MakeOrder(time, date, typeOfOrder, jobTitle, nameOfWorker, phoneWorkerNum);
                                    reference4.child("Client").child("Data").child(phoneClient).child("History Jobs").child(phoneWorker).setValue(historyOrder);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                            reference3 = FirebaseDatabase.getInstance().getReference().child("Worker").child(workerJobTitle).child("Data").child(phoneWorker);
                            reference3.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    // get data to restore it to another path
                                    jobTitle = snapshot.child("jobTitle").getValue(String.class);
                                    phoneWorkerNum = snapshot.child("phone").getValue(String.class);
                                    nameOfWorker = snapshot.child("userName").getValue(String.class);
                                    // set these data in History path for each client
                                    MakeOrder historyOrder = new MakeOrder(time, date, typeOfOrder, jobTitle, nameOfWorker, phoneWorkerNum);
                                    reference4.child("Client").child("Data").child(phoneClient).child("History Jobs").child(phoneWorker).setValue(historyOrder);
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
            }
        });



    }
}


/*

  buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference4 = FirebaseDatabase.getInstance().getReference();

                reference5 = FirebaseDatabase.getInstance().getReference().child("Client").child("make order").child(phoneClient).child("Accepted").child(phoneWorker);
                reference5.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String jobOfWorker = snapshot.child("jobTitle").getValue(String.class);
                        if (jobOfWorker.equals("Electricity")){
                            reference2 = FirebaseDatabase.getInstance().getReference().child("Worker").child("Electricity").child("order Details").child(phoneClient);
                            reference2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    date = snapshot.child("date").getValue(String.class);
                                    time = snapshot.child("time").getValue(String.class);
                                    location = snapshot.child("location").getValue(String.class);
                                    phoneClientNum = snapshot.child("requestedPhone").getValue(String.class);
                                    nameClient = snapshot.child("userName").getValue(String.class);
                                    typeOfOrder = snapshot.child("typeOfOrder").getValue(String.class);

                                    MakeOrder order = new MakeOrder(time,date,location,phoneClientNum,nameClient);
                                    reference4.child("Worker").child("Electricity").child("Data").child(phoneWorker).child("Job Accepted").child(phoneClient).setValue(order);
                                    MakeOrder historyOrder = new MakeOrder(time,date,typeOfOrder,jobTitle,nameOfWorker,phoneWorkerNum);
                                    reference4.child("Client").child("Data").child(phoneClient).child("History Jobs").child(phoneWorker).setValue(historyOrder);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                            reference3 = FirebaseDatabase.getInstance().getReference().child("Worker").child("Electricity").child("Data").child(phoneWorker);
                            reference3.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    jobTitle = snapshot.child("jobTitle").getValue(String.class);
                                    phoneWorkerNum = snapshot.child("phone").getValue(String.class);
                                    nameOfWorker = snapshot.child("userName").getValue(String.class);

                                    MakeOrder historyOrder = new MakeOrder(time,date,typeOfOrder,jobTitle,nameOfWorker,phoneWorkerNum);
                                    reference4.child("Client").child("Data").child(phoneClient).child("History Jobs").child(phoneWorker).setValue(historyOrder);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                        else if (jobOfWorker.equals("Plumber")){
                            reference2 = FirebaseDatabase.getInstance().getReference().child("Worker").child("Plumber").child("order Details").child(phoneClient);
                            reference2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    date = snapshot.child("date").getValue(String.class);
                                    time = snapshot.child("time").getValue(String.class);
                                    location = snapshot.child("location").getValue(String.class);
                                    phoneClientNum = snapshot.child("requestedPhone").getValue(String.class);
                                    nameClient = snapshot.child("userName").getValue(String.class);
                                    typeOfOrder = snapshot.child("typeOfOrder").getValue(String.class);

                                    MakeOrder order = new MakeOrder(time,date,location,phoneClientNum,nameClient);
                                    reference4.child("Worker").child("Plumber").child("Data").child(phoneWorker).child("Job Accepted").child(phoneClient).setValue(order);
                                    MakeOrder historyOrder = new MakeOrder(time,date,typeOfOrder,jobTitle,nameOfWorker,phoneWorkerNum);
                                    reference4.child("Client").child("Data").child(phoneClient).child("History Jobs").child(phoneWorker).setValue(historyOrder);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                            reference3 = FirebaseDatabase.getInstance().getReference().child("Worker").child("Plumber").child("Data").child(phoneWorker);
                            reference3.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    jobTitle = snapshot.child("jobTitle").getValue(String.class);
                                    phoneWorkerNum = snapshot.child("phone").getValue(String.class);
                                    nameOfWorker = snapshot.child("userName").getValue(String.class);

                                    MakeOrder historyOrder = new MakeOrder(time,date,typeOfOrder,jobTitle,nameOfWorker,phoneWorkerNum);
                                    reference4.child("Client").child("Data").child(phoneClient).child("History Jobs").child(phoneWorker).setValue(historyOrder);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                        else if (jobOfWorker.equals("Carpenter")){
                            reference2 = FirebaseDatabase.getInstance().getReference().child("Worker").child("Carpenter").child("order Details").child(phoneClient);
                            reference2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    date = snapshot.child("date").getValue(String.class);
                                    time = snapshot.child("time").getValue(String.class);
                                    location = snapshot.child("location").getValue(String.class);
                                    phoneClientNum = snapshot.child("requestedPhone").getValue(String.class);
                                    nameClient = snapshot.child("userName").getValue(String.class);
                                    typeOfOrder = snapshot.child("typeOfOrder").getValue(String.class);

                                    MakeOrder order = new MakeOrder(time,date,location,phoneClientNum,nameClient);
                                    reference4.child("Worker").child("Carpenter").child("Data").child(phoneWorker).child("Job Accepted").child(phoneClient).setValue(order);
                                    MakeOrder historyOrder = new MakeOrder(time,date,typeOfOrder,jobTitle,nameOfWorker,phoneWorkerNum);
                                    reference4.child("Client").child("Data").child(phoneClient).child("History Jobs").child(phoneWorker).setValue(historyOrder);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                            reference3 = FirebaseDatabase.getInstance().getReference().child("Worker").child("Carpenter").child("Data").child(phoneWorker);
                            reference3.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    jobTitle = snapshot.child("jobTitle").getValue(String.class);
                                    phoneWorkerNum = snapshot.child("phone").getValue(String.class);
                                    nameOfWorker = snapshot.child("userName").getValue(String.class);

                                    MakeOrder historyOrder = new MakeOrder(time,date,typeOfOrder,jobTitle,nameOfWorker,phoneWorkerNum);
                                    reference4.child("Client").child("Data").child(phoneClient).child("History Jobs").child(phoneWorker).setValue(historyOrder);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
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
                //delete order.....



            }
        });




 */