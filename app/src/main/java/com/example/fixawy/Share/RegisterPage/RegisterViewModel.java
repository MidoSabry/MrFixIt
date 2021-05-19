package com.example.fixawy.Share.RegisterPage;

import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fixawy.Firebase.FirebaseHandlerClient;
import com.example.fixawy.Firebase.FirebaseHandlerWorker;
import com.example.fixawy.Pojos.User;
import com.example.fixawy.Share.Homes.OwnerHome;
import com.example.fixawy.Worker.SelectJobPage.SelectJobActivity;
//import com.example.fixawy.Share.VerifyCode.VerificationCode;

import static androidx.core.content.ContextCompat.startActivity;
import static com.facebook.FacebookSdk.getApplicationContext;

public class RegisterViewModel extends ViewModel {
    private FirebaseHandlerClient firebaseHandlerClient;
    private FirebaseHandlerWorker firebaseHandlerWorker;

    public void registerClient(User user){
        firebaseHandlerClient = new FirebaseHandlerClient();
        firebaseHandlerClient.addClientData(user,user.phone).addOnSuccessListener(suc->{

        });
    }

    public void registerWorker(User user){
        firebaseHandlerWorker = new FirebaseHandlerWorker();
        firebaseHandlerWorker.addWorkerData(user,user.phone,user.jobTitle).addOnSuccessListener(suc->{


        });
    }

}
