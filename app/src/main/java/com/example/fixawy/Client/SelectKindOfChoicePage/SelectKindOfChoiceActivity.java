package com.example.fixawy.Client.SelectKindOfChoicePage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fixawy.Client.PreviousQuestionPage.PreviousQuestionActivity;
import com.example.fixawy.R;

public class SelectKindOfChoiceActivity extends AppCompatActivity {

    String phoneNum,jobTitle;
    Button askQuestionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_kind_of_choice);
        askQuestionBtn = findViewById(R.id.ask_question_btn);
        phoneNum=getIntent().getStringExtra("phone");
        jobTitle=getIntent().getStringExtra("categoryName");

        askQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectKindOfChoiceActivity.this, PreviousQuestionActivity.class);
                intent.putExtra("phone", phoneNum);
                intent.putExtra("categoryName",jobTitle);
                Toast.makeText(SelectKindOfChoiceActivity.this, jobTitle, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}