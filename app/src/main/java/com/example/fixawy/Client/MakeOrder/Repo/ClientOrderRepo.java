package com.example.fixawy.Client.MakeOrder.Repo;

import com.example.fixawy.Client.MakeOrder.pojos.OrderTree;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class ClientOrderRepo {
   public DatabaseReference addData(String phoneNum) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Client").child("Order details").child(phoneNum).child("make order");
        //TODO //child(FirebaseAuth.getInstance().getCurrentUser().getUid())
       //TODO //FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()
        return myRef;
    }


}
