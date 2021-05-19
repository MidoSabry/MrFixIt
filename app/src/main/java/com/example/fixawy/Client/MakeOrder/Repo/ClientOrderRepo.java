package com.example.fixawy.Client.MakeOrder.Repo;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClientOrderRepo {
   public DatabaseReference addData() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Client").child("Order details").child("test").child("make order");
        //TODO //child(FirebaseAuth.getInstance().getCurrentUser().getUid())
       //TODO //FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()
        return myRef;
    }
}
