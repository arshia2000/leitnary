package com.avmhl.leitnary.start;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.avmhl.leitnary.MainActivity;
import com.avmhl.leitnary.R;

import com.avmhl.leitnary.information.WelcomeActivity;
import com.avmhl.leitnary.sharedPreferences.Save;

public class Loading_splash extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;



    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressBar = findViewById(R.id.progress);
        textView = findViewById(R.id.textView6);
        progressBar.setMax(100);
        progressBar.setScaleY(3f);
        ProgressAnimation();


        new CountDownTimer(9000, 1000) {


            @Override
            public void onTick(long millisUntilFinished)
            {

            }

            @Override
            public void onFinish() {


                        Intent intent = new Intent(Loading_splash.this, WelcomeActivity.class);
                        startActivity(intent);
                         finish();
            }
        }.start();




    }


    public void ProgressAnimation() {

        Loading anim = new Loading(this, progressBar, textView, 0f, 100f);
        anim.setDuration(7000);
        progressBar.setAnimation(anim);


    }

}
