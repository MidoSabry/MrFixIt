package com.example.fixawy.Share.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.fixawy.Activity_Tips;
import com.example.fixawy.R;
import com.example.fixawy.Share.Homes.OwnerHome;
import com.example.fixawy.Share.LoginPage.LoginActivity;
import com.example.fixawy.Share.SelectionPage.SelectMembershipType;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    Animation top_down , down_top;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        top_down = AnimationUtils.loadAnimation(this,R.anim.top_down);
        imageView = findViewById(R.id.splash_image);
        imageView.setAnimation(top_down);
        final int secondsDelayed = 5000;
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashScreen.this,Activity_Tips.class));
                finish();

            },2000);
        }else {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashScreen.this, SelectMembershipType.class));
                finish();

            },500);
        }
    }
}