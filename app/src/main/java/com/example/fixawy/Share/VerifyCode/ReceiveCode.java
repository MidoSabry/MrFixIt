package com.example.fixawy.Share.VerifyCode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fixawy.R;
import com.example.fixawy.Share.ChangePassword.ChangePassword;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class ReceiveCode extends AppCompatActivity {

    private Button mVerifyCodeBtn;
    private TextInputLayout otpEdit;
    private String OTP,verification_code;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    String phoneNum,type,jobTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_code);
        otpEdit = findViewById(R.id.edit_code);
        mVerifyCodeBtn = findViewById(R.id.btnNext);

        progressBar=findViewById(R.id.progress);
        mAuth = FirebaseAuth.getInstance();


        OTP = getIntent().getStringExtra("auth");
        phoneNum=getIntent().getStringExtra("phone");
        type=getIntent().getExtras().getString("type");
        jobTitle=getIntent().getExtras().getString("jobTitle");



        mVerifyCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification_code = otpEdit.getEditText().getText().toString();
                if(!verification_code.isEmpty()){
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(OTP , verification_code);
                    signIn(credential);
                }else{
                    Toast.makeText(ReceiveCode.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void signIn(PhoneAuthCredential credential){
        sendToMain();
    }

    private void sendToMain(){
        Intent intentChangePassword=new Intent(ReceiveCode.this, ChangePassword.class);
        intentChangePassword.putExtra("auth" , OTP);
        intentChangePassword.putExtra("verification_code",verification_code);
        intentChangePassword.putExtra("phone",phoneNum);
        intentChangePassword.putExtra("type",type);
        intentChangePassword.putExtra("jobTitle",jobTitle);
        startActivity(intentChangePassword);
        finish();
    }
}