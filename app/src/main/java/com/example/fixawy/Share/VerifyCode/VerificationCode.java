package com.example.fixawy.Share.VerifyCode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fixawy.R;
import com.example.fixawy.Share.Homes.OwnerHome;
import com.example.fixawy.Share.Homes.WorkerHome;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class VerificationCode extends AppCompatActivity {

    private Button mVerifyCodeBtn;
    private TextInputLayout otpEdit;
    private String OTP;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    String userName,email,phoneNum,address,password,verification_code,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);

        otpEdit = findViewById(R.id.edit_code);
        mVerifyCodeBtn = findViewById(R.id.btn_signUp);

        progressBar=findViewById(R.id.progress);
        mAuth = FirebaseAuth.getInstance();


        OTP = getIntent().getStringExtra("auth");
        userName=getIntent().getStringExtra("userName");
        email=getIntent().getStringExtra("email");
        phoneNum=getIntent().getStringExtra("phone");
        address=getIntent().getStringExtra("address");
        type=getIntent().getStringExtra("type");
        password=getIntent().getStringExtra("password");


        mVerifyCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification_code = otpEdit.getEditText().getText().toString();
                if(!verification_code.isEmpty()){
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(OTP , verification_code);
                    signIn(credential);
                }else{
                    Toast.makeText(VerificationCode.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void signIn(PhoneAuthCredential credential){
        sendToMain();
    }

    private void sendToMain(){
        com.example.fixawy.Pojos.User user = new com.example.fixawy.Pojos.User(userName,email,phoneNum,address,type,password);
        FirebaseDatabase.getInstance().getReference("Users").child(phoneNum)
                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(VerificationCode.this, type, Toast.LENGTH_SHORT).show();
                    if (type.equals("Owner")){
                        startActivity(new Intent(VerificationCode.this, OwnerHome.class));

                    }
                    else if (type.equals("Worker")){
                        startActivity(new Intent(VerificationCode.this, WorkerHome.class));

                    }
                }
                else {
                    Toast.makeText(VerificationCode.this, "Faillllllllllllllllled", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}