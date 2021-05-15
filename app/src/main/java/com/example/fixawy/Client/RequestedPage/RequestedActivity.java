package com.example.fixawy.Client.RequestedPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fixawy.R;

public class RequestedActivity extends AppCompatActivity {

    RecyclerView requestedRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested);

        requestedRecyclerView = findViewById(R.id.requestedrv);
    }
}