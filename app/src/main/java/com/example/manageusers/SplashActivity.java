package com.example.manageusers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logoImageView = findViewById(R.id.imageView);
        // Load the animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        Animation translateUp = AnimationUtils.loadAnimation(this, R.anim.translate_up);
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);


        // Set up an animation set
        AnimationSet animSet = new AnimationSet(false);
        animSet.addAnimation(fadeIn);
        animSet.addAnimation(scaleUp);
        animSet.addAnimation(translateUp);
        animSet.addAnimation(pulse);

        // Start the animation
       logoImageView.startAnimation(animSet);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        }, 4000);

    }

}
// Test3 - Hasan.
// some comments in splash activity for testing purposes.