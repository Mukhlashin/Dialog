package com.example.dialog;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class SplashScreenActivity extends AppCompatActivity {

    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        pb = findViewById(R.id.pb_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pb.setVisibility(View.VISIBLE);
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            }
        }, 4000);

    }

    @Override
    protected void onStop() {
        super.onStop();
        pb.setVisibility(View.INVISIBLE);
    }
}
