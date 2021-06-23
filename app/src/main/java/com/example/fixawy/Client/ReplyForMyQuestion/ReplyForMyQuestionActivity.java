package com.example.fixawy.Client.ReplyForMyQuestion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.fixawy.Client.ReplyQuestions.AnswerAdapter;
import com.example.fixawy.Pojos.Answer;
import com.example.fixawy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReplyForMyQuestionActivity extends AppCompatActivity {

    DatabaseReference mRef;
    String phoneClient,jobTitle,clientQuestion;
    Answer answer;
    ReplyForMyQuestionAdapter replyQuestionsAdapter;
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_for_my_question);

        mRef = FirebaseDatabase.getInstance().getReference();
        replyQuestionsAdapter = new ReplyForMyQuestionAdapter();
        mRecyclerView = findViewById(R.id.questionsList);

        phoneClient = getIntent().getStringExtra("phone");
        jobTitle = getIntent().getStringExtra("jobTitle");

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(replyQuestionsAdapter);
        read();
        Toast.makeText(this, "all replies of your questions", Toast.LENGTH_SHORT).show();
    }

    public void read(){
        mRef.child("Client").child("Questions").child("Replies").child(jobTitle).child(phoneClient).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Iterable<DataSnapshot> children = task.getResult().getChildren();
                for (DataSnapshot snapshot : children) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        answer = dataSnapshot.getValue(Answer.class);
                        replyQuestionsAdapter.add(answer);
                    }
                }
            }
        });

    }

}
