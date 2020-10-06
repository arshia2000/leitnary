package com.avmhl.leitnary.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

import com.avmhl.leitnary.MainActivity;
import com.avmhl.leitnary.R;
import com.avmhl.leitnary.view.SplashView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    Timer timer;
   SplashView splashView;
    ImageView imlogo;
    int splashspeed = 18;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
        splashmove();
        showlogo();

        new CountDownTimer(2500,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                Intent intent=new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();


    }



    private void init() {


        splashView = findViewById(R.id.splash_view);
        imlogo = findViewById(R.id.splash_img);



    }

    private void splashmove() {


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (splashView.height > splashView.bottom) {
                    splashView.top += splashspeed;
                    splashView.bottom += splashspeed;

                    splashView.invalidate();
                }

            }
        }, 0, 50);


    }

    private void showlogo() {

        imlogo.animate().alpha(1f).setDuration(2000);


    }
}