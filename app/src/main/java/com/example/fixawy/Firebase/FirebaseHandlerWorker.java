package com.example.fixawy.Firebase;

import com.example.fixawy.Pojos.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHandlerWorker {
    private DatabaseReference databaseReference;


    public FirebaseHandlerWorker() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Worker");

    }
    public Task<Void> addWorkerData(User user, String phonNum)
    {
        return databaseReference.child("Data").child(phonNum).setValue(user);
    }
}
