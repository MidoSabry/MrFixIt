package com.example.fixawy.Client.RatingPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.example.fixawy.R;

public class RatingActivity extends AppCompatActivity {

    LottieAnimationView lottieLike,lottieDislike;
    private boolean btnLike = false;
    private boolean btnDislike = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        lottieLike = findViewById(R.id.Likebtn);
        lottieDislike = findViewById(R.id.Dislikebtn);

        lottieLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lottieLike.playAnimation();
            }
        });

        lottieDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lottieDislike.playAnimation();
            }
        });

    }
}