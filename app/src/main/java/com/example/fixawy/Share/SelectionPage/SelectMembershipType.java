package com.example.fixawy.Share.SelectionPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fixawy.R;
import com.example.fixawy.Share.Homes.OwnerHome;
import com.example.fixawy.Share.Homes.WorkerHome;
import com.example.fixawy.Share.RegisterPage.RegisterActivity;
import com.example.fixawy.Share.Session.SharedPreferencesConfig;
import com.example.fixawy.Worker.SelectJobPage.SelectJobActivity;


public class SelectMembershipType extends AppCompatActivity {

    Button btnOwner,btnWorker;
  //  SharedPreferencesConfig preferencesConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_membership_type);

        btnOwner=findViewById(R.id.client_btn);
        btnWorker=findViewById(R.id.worker_btn);


      /*  preferencesConfig = new SharedPreferencesConfig(getApplicationContext());

        if (preferencesConfig.readUserLoginStatus()) {
            Intent intent = new Intent(SelectMembershipType.this, OwnerHome.class);
            startActivity(intent);
            finish();
        }
        if (preferencesConfig.readWorkerLoginStatus()) {
            Intent intent = new Intent(SelectMembershipType.this, WorkerHome.class);
            startActivity(intent);
            finish();
        }*/


        btnOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)
                        .putExtra("type", "Owner"));
              //  preferencesConfig.writeUserLoginStatus(true);
            }
        });

        btnWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SelectJobActivity.class)
                        .putExtra("type", "Worker"));
                //preferencesConfig.writeWorkerLoginStatus(true);
            }
        });


    }
}