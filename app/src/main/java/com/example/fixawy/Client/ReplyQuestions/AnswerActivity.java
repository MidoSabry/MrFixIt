package com.example.fixawy.Client.ReplyQuestions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fixawy.Client.PreviousQuestionPage.PreviousQuestionAdapter;
import com.example.fixawy.Pojos.Answer;
import com.example.fixawy.Pojos.Questions;
import com.example.fixawy.Pojos.Reply;
import com.example.fixawy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AnswerActivity extends AppCompatActivity {
    DatabaseReference mRef;
    Answer answer;
    AnswerAdapter answerAdapter;
    RecyclerView mRecyclerView;
    String phoneNum,jobTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        mRef = FirebaseDatabase.getInstance().getReference();
        answerAdapter = new AnswerAdapter();
        mRecyclerView = findViewById(R.id.questionsList);

        phoneNum = getIntent().getStringExtra("phone");
        jobTitle = getIntent().getStringExtra("jobTitle");

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(answerAdapter);
        // readReply();
        read();
        Toast.makeText(this, "all replies of your questions", Toast.LENGTH_SHORT).show();
    }

    public void read(){
        mRef.child("Client").child("Questions").child("Replies").child(jobTitle).child(phoneNum).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        answer = dataSnapshot.getValue(Answer.class);
                        answerAdapter.add(answer);
                    }
                }
            }
        });

    }


    public void readReply(){
        mRef.child("Client").child("Questions").child(jobTitle).child(phoneNum).child("Reply").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    answer = snapshot.getValue(Answer.class);
                    answerAdapter.add(answer);
                }
            }
        });

    }
}