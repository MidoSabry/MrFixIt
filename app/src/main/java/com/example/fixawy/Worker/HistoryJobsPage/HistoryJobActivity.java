package com.example.fixawy.Worker.HistoryJobsPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fixawy.Client.PreviousQuestionPage.PreviousQuestionAdapter;
import com.example.fixawy.Pojos.Questions;
import com.example.fixawy.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HistoryJobActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    HistoryJobAdapter historyJobAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_job);

        mRecyclerView = findViewById(R.id.historyJobList);


        historyJobAdapter = new HistoryJobAdapter();

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(historyJobAdapter);
    }
}