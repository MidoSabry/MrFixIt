package com.example.fixawy.Client.ReplyQuestions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.fixawy.Share.VerifyCode.VerificationCode.EXTR_USER_NAME;
import com.example.fixawy.Client.PreviousQuestionPage.PreviousQuestionActivity;
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
    String phoneClient,jobTitle,phoneOfCard,reply,clientName;
    ImageView imageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        imageViewBack = findViewById(R.id.backToPrevious);

        phoneClient = getIntent().getStringExtra("phoneClient");
        jobTitle = getIntent().getStringExtra("jobTitle");
        phoneOfCard = getIntent().getStringExtra("phoneOfCard");
        reply = getIntent().getStringExtra("reply");
        clientName = getIntent().getStringExtra(EXTR_USER_NAME);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnswerActivity.this, PreviousQuestionActivity.class)
                        .putExtra("phoneClient",phoneClient)
                        .putExtra("CategoryType",jobTitle)
                        .putExtra("phoneOfCard",phoneOfCard)
                        .putExtra(EXTR_USER_NAME,clientName));
            }
        });

        mRef = FirebaseDatabase.getInstance().getReference();
        answerAdapter = new AnswerAdapter(this,jobTitle,phoneClient,clientName);
        mRecyclerView = findViewById(R.id.questionsList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(answerAdapter);
        // readReply();
        read();
        Toast.makeText(this, "all replies of your questions", Toast.LENGTH_SHORT).show();
    }

    public void read(){
        mRef.child("Client").child("Questions").child("Replies").child(jobTitle).child(phoneOfCard).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
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