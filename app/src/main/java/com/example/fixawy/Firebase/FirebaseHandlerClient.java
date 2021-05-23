package com.example.fixawy.Firebase;

import com.example.fixawy.Pojos.Questions;
import com.example.fixawy.Pojos.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHandlerClient {
    private DatabaseReference databaseReference;


    public FirebaseHandlerClient() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Client");

    }
    public Task<Void> addClientrData(User user, String phonNum)
    {
        return databaseReference.child("Data").child(phonNum).setValue(user);
    }

    public Task<Void> addClientQuestion(Questions question, String phone,String jobTitle)
    {
        return databaseReference.child("Questions").child(jobTitle).child(phone).child("Data").setValue(question);

    }

    public Task<Void> addClientQuestionForCategory(Questions question, String phone,String jobTitle)
    {
        return databaseReference.child("Question Category").child(jobTitle).child(phone).setValue(question);

    }

}
