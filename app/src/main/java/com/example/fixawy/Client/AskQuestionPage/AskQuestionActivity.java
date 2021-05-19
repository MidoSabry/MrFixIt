package com.example.fixawy.Client.AskQuestionPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fixawy.Client.PreviousQuestionPage.PreviousQuestionActivity;
import com.example.fixawy.Pojos.Questions;
import com.example.fixawy.Pojos.User;
import com.example.fixawy.R;
import com.example.fixawy.Share.VerifyCode.VerificationCode;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class AskQuestionActivity extends AppCompatActivity {

    EditText editTextQuestion;
    Button buttonPostQuestion;
    String question,phoneNum;
    AskQuestionViewModel askQuestionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);

        editTextQuestion=findViewById(R.id.worker_rating_comment_et);
        buttonPostQuestion=findViewById(R.id.postQuestion);
        askQuestionViewModel = new AskQuestionViewModel();
        phoneNum=getIntent().getStringExtra("phone");

        buttonPostQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question = editTextQuestion.getText().toString().trim();
                Questions userQuestions = new Questions(phoneNum,question);

                askQuestionViewModel.addClientQuestion(userQuestions);
                Toast.makeText(AskQuestionActivity.this, "your question is added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AskQuestionActivity.this, PreviousQuestionActivity.class));

             /*   FirebaseDatabase.getInstance().getReference("Client").child("Questions").child(phoneNum).setValue(userQuestions)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                Toast.makeText(AskQuestionActivity.this, "your question is added", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(AskQuestionActivity.this, "Faillllllllllllllllled", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });*/

            }
        });
    }
}

