package com.example.fixawy.Client.HomePageClient;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.fixawy.Firebase.FirebaseHandlerWorker;
import com.example.fixawy.Pojos.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomePageReposatory {
    private User user = new User();
    private static HomePageReposatory instance;
    private DatabaseReference database;




    public static HomePageReposatory getInstance(){
        if(instance == null){
            instance = new HomePageReposatory();
        }
        return instance;
    }



    public List<User> getEmployeeData(String jobTitle, List<User> employees){
        database = FirebaseDatabase.getInstance().getReference("Worker").child(jobTitle).child("Data");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User employeeData = (User)dataSnapshot.getValue(User.class);
                    Log.d("ssssss","user"+employeeData.getUserName());
                    employees.add( employeeData);
                }
                //HomePageClientActivity.mainRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return  employees;

    }


}



























