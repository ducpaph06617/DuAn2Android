package com.dev.duan2android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.daimajia.numberprogressbar.NumberProgressBar;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private ImageView imgLogo;
    private NumberProgressBar numberProgressBar;
    public Animation animationIMG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imgLogo = findViewById(R.id.imgLogo);
        numberProgressBar = findViewById(R.id.number_progress_bar);
        animationIMG = AnimationUtils.loadAnimation(this, R.anim.anim3);
        imgLogo.setAnimation(animationIMG);
        RunAble runAble = new RunAble(5, this);
        new Thread(runAble).start();

    }

    public class RunAble implements Runnable {

        int seconds;
        Context context;

        public RunAble(int seconds, Context context) {
            this.seconds = seconds;
            this.context = context;
        }

        @Override
        public void run() {

            for (int i = 0; i <= 4; i++) {
                Handler handler = new Handler(Looper.getMainLooper());
                final int intI = i;
                handler.post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {
                        if (intI == 4) {
                            RunAble1 runAble1 = new RunAble1(5,SplashActivity.this);
                            new Thread(runAble1).start();
                        }
                    }

                });

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public class RunAble1 implements Runnable {
        int seconds;
        Context context;

        public RunAble1(int seconds, Context context) {
            this.seconds = seconds;
            this.context = context;
        }

        @Override
        public void run() {
            for (int i = 0; i <= 100; i++) {
                Handler handler = new Handler(Looper.getMainLooper());
                final int intI = i;
                handler.post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {
                        numberProgressBar.setVisibility(View.VISIBLE);
                        numberProgressBar.setProgress(intI);
                        if (intI == 100) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                });

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        }
    }


    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
