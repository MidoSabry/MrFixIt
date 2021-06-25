package com.example.fixawy.Client.ReplyForMyQuestion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fixawy.Client.AllPreviousQuestions.AllPreviousQuestionsActivity;
import com.example.fixawy.Client.ReplyQuestions.AnswerAdapter;
import com.example.fixawy.Pojos.Answer;
import com.example.fixawy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import static com.example.fixawy.Share.VerifyCode.VerificationCode.EXTR_USER_NAME;

public class ReplyForMyQuestionActivity extends AppCompatActivity {

    DatabaseReference mRef;
    String phoneClient,jobTitle,phoneWorker,reply,clientName;
    Answer answer;
    ReplyForMyQuestionAdapter replyQuestionsAdapter;
    RecyclerView mRecyclerView;
    ImageView imageViewBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_for_my_question);

        imageViewBack = findViewById(R.id.backToPrevious);

        phoneClient = getIntent().getStringExtra("phone");
        phoneWorker = getIntent().getStringExtra("phoneWorker");
        jobTitle = getIntent().getStringExtra("jobTitle");
        reply = getIntent().getStringExtra("reply");
        clientName = getIntent().getStringExtra(EXTR_USER_NAME);


        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReplyForMyQuestionActivity.this, AllPreviousQuestionsActivity.class)
                        .putExtra("phone",phoneClient)
                        .putExtra(EXTR_USER_NAME,clientName));
                //  .putExtra("phoneWorker",phoneWorker)
                //  .putExtra("jobTitle",jobTitle));
            }
        });

        mRef = FirebaseDatabase.getInstance().getReference();
        replyQuestionsAdapter = new ReplyForMyQuestionAdapter(this,jobTitle,clientName);
        mRecyclerView = findViewById(R.id.questionsList);
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
    //backButton
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);

        return;
    }
}
