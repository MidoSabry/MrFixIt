package com.example.fixawy.Share.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.fixawy.R;
import com.example.fixawy.Share.Homes.OwnerHome;
import com.example.fixawy.Share.LoginPage.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                finish();

            },2000);
        }else {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashScreen.this, OwnerHome.class));
                finish();

            },2000);
        }
    }
}