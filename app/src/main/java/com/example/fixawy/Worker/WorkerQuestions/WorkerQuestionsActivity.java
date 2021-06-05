package com.example.fixawy.Worker.WorkerQuestions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fixawy.Client.AskQuestionPage.AskQuestionActivity;
import com.example.fixawy.Client.PreviousQuestionPage.PreviousQuestionActivity;
import com.example.fixawy.Client.PreviousQuestionPage.PreviousQuestionAdapter;
import com.example.fixawy.Pojos.Questions;
import com.example.fixawy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WorkerQuestionsActivity extends AppCompatActivity {

    String jobTitle,phoneWorker;
    RecyclerView mRecyclerView;
    DatabaseReference mRef;
    WorkerQuestionsAdapter workerQuestionsAdapter;
    Questions questions;
    TextView textViewJobTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_questions);

        mRecyclerView = findViewById(R.id.questionsList);
        textViewJobTitle=findViewById(R.id.txt_job_title);

        jobTitle = getIntent().getStringExtra("jobTitle");
        phoneWorker = getIntent().getStringExtra("phoneWorker");

        textViewJobTitle.setText("All Questions for "+jobTitle);

        mRef = FirebaseDatabase.getInstance().getReference();


        workerQuestionsAdapter = new WorkerQuestionsAdapter(WorkerQuestionsActivity.this,phoneWorker);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(workerQuestionsAdapter);
        readData();
        Toast.makeText(this, "all questions", Toast.LENGTH_SHORT).show();
    }
    public void readData(){
        mRef.child("Worker").child(jobTitle).child("Questions").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        questions = dataSnapshot.getValue(Questions.class);
                        workerQuestionsAdapter.add(questions);
                    }
                }
            }
        });

    }
}