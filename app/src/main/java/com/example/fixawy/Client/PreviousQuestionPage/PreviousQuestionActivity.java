package com.example.fixawy.Client.PreviousQuestionPage;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.fixawy.Client.AskQuestionPage.AskQuestionActivity;
import com.example.fixawy.Pojos.Questions;
import com.example.fixawy.Pojos.User;
import com.example.fixawy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class PreviousQuestionActivity extends AppCompatActivity {

    FloatingActionButton floatingButtonAsk;
    String phoneNum,jobTitle;
    RecyclerView mRecyclerView;
    DatabaseReference mRef;
    PreviousQuestionAdapter questionAdapter;
    Questions questions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_question);
        mRecyclerView = findViewById(R.id.questionsList);
        floatingButtonAsk = findViewById(R.id.addQuestion);
        phoneNum = getIntent().getStringExtra("phone");
        jobTitle = getIntent().getStringExtra("categoryName");

        floatingButtonAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreviousQuestionActivity.this, AskQuestionActivity.class);
                intent.putExtra("phone", phoneNum);
                intent.putExtra("categoryName",jobTitle);
                startActivity(intent);
            }
        });

        mRef = FirebaseDatabase.getInstance().getReference();
        questionAdapter = new PreviousQuestionAdapter();

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(questionAdapter);
        readData();
        Toast.makeText(this, "all questions", Toast.LENGTH_SHORT).show();
    }

    // return all questions for specific phoneNumber...
    public void readData() {
        mRef.child("Client").child("Questions").child("Electricity").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    questions = snapshot.getValue(Questions.class);
                    questionAdapter.add(questions);
                }
            }
        });

        mRef.child("Client").child("Questions").child("Carpenter").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    questions = snapshot.getValue(Questions.class);
                    questionAdapter.add(questions);
                }
            }
        });

        mRef.child("Client").child("Questions").child("Plumber").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    questions = snapshot.getValue(Questions.class);
                    questionAdapter.add(questions);
                }
            }
        });

        mRef.child("Client").child("Questions").child("Painter").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    questions = snapshot.getValue(Questions.class);
                    questionAdapter.add(questions);
                }
            }
        });

        mRef.child("Client").child("Questions").child("TilesHandyMan").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    questions = snapshot.getValue(Questions.class);
                    questionAdapter.add(questions);
                }
            }
        });

        mRef.child("Client").child("Questions").child("Mason").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    questions = snapshot.getValue(Questions.class);
                    questionAdapter.add(questions);
                }
            }
        });

        mRef.child("Client").child("Questions").child("Smith").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    questions = snapshot.getValue(Questions.class);
                    questionAdapter.add(questions);
                }
            }
        });

        mRef.child("Client").child("Questions").child("Parquet").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    questions = snapshot.getValue(Questions.class);
                    questionAdapter.add(questions);
                }
            }
        });

        mRef.child("Client").child("Questions").child("Gypsum").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    questions = snapshot.getValue(Questions.class);
                    questionAdapter.add(questions);
                }
            }
        });

        mRef.child("Client").child("Questions").child("Marble").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    questions = snapshot.getValue(Questions.class);
                    questionAdapter.add(questions);
                }
            }
        });

        mRef.child("Client").child("Questions").child("Alumetal").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    questions = snapshot.getValue(Questions.class);
                    questionAdapter.add(questions);
                }
            }
        });

        mRef.child("Client").child("Questions").child("Glasses").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    questions = snapshot.getValue(Questions.class);
                    questionAdapter.add(questions);
                }
            }
        });

        mRef.child("Client").child("Questions").child("WoodPainter").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    questions = snapshot.getValue(Questions.class);
                    questionAdapter.add(questions);
                }
            }
        });

        mRef.child("Client").child("Questions").child("Curtains").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    questions = snapshot.getValue(Questions.class);
                    questionAdapter.add(questions);
                }
            }
        });

        mRef.child("Client").child("Questions").child("Appliances Maintenance").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    questions = snapshot.getValue(Questions.class);
                    questionAdapter.add(questions);
                }
            }
        });
        mRef.child("Client").child("Questions").child("Satellite").child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    questions = snapshot.getValue(Questions.class);
                    questionAdapter.add(questions);
                }
            }
        });



    }
}