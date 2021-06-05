package com.example.fixawy.Client.PreviousQuestionPage;
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
import com.example.fixawy.Pojos.Questions;
import com.example.fixawy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PreviousQuestionActivity extends AppCompatActivity {

    FloatingActionButton floatingButtonAsk;
    String phoneNum,jobTitle;
    RecyclerView mRecyclerView;
    DatabaseReference mRef;
    PreviousQuestionAdapter questionAdapter;
    Questions questions;
    TextView textViewJobTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_question);
        mRecyclerView = findViewById(R.id.questionsList);
        floatingButtonAsk = findViewById(R.id.addQuestion);
        textViewJobTitle=findViewById(R.id.txt_job_title);
        phoneNum = getIntent().getStringExtra("phone");
        jobTitle = getIntent().getStringExtra("CategoryType");

        textViewJobTitle.setText("All Questions for "+jobTitle);

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
    // return all questions for specific job...
    public void readData(){
        mRef.child("Client").child("Questions").child("Question Category").child(jobTitle).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
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