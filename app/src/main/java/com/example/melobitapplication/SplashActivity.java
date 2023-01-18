package com.example.melobitapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    AlertDialog alertDialog ;
    TextView btn_retry,btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // TODO: Your application init goes here.

                Intent mInHome = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mInHome);

                SplashActivity.this.finish();


            }
        }, 3000);
    }








}

