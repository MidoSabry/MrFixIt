package com.example.fixawy.Firebase;

import com.example.fixawy.Pojos.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHandlerClient {
    private DatabaseReference databaseReference;


    public FirebaseHandlerClient() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Users").child("Client");

    }
    public Task<Void> addClientData(User user,String phonNum)
    {
       return databaseReference.child("Data").child(phonNum).setValue(user);
    }
}
