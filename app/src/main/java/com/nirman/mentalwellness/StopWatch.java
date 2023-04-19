package com.nirman.mentalwellness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

public class StopWatch extends AppCompatActivity {

    Button btnpause,restart;
    TextView tv1;
    ImageButton back;
    CountDownTimer countDownTimer;
    Bundle bundle;
    ProgressBar progressBar;
    long i,timeinmillisec,times;
    boolean timerunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        bundle = getIntent().getExtras();
        back = findViewById(R.id.backarrow);
        tv1 = findViewById(R.id.timing);
        btnpause = findViewById(R.id.pause);
        restart = findViewById(R.id.restart);
        progressBar = findViewById(R.id.progressBar);

        i = bundle.getLong("time");
        timeinmillisec = i*60;
        timeinmillisec = timeinmillisec*1000;
        back.setOnClickListener(view -> {
            Intent intent = new Intent(this, EmotionFeedback.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
        progressBar.setMax(((int)i*60)*100);
        btnpause.setOnClickListener(view -> {
            if(timerunning){
                pause();
                btnpause.setText("Play");
            }
            else {
                play();
                btnpause.setText("Pause");
            }
        });
        restart.setOnClickListener(view -> {
            setRestart();
        });
        starttimmer();
    }
    public void starttimmer(){
        countDownTimer = new CountDownTimer(timeinmillisec,1000) {
            @Override
            public void onTick(long l) {
                timeinmillisec = l;
                updatecountdown();
            }
            @Override
            public void onFinish() {
                timerunning = false;
                Intent intent = new Intent(StopWatch.this, EmotionFeedback.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }.start();
        timerunning = true;
    }

    private void updatecountdown() {
        int minutes = (int) (timeinmillisec/1000)/60;
        int seconds = (int) (timeinmillisec/1000)%60;
        progressBar.setProgress(((int)i*60)*100-(int)timeinmillisec/10);
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        tv1.setText(timeLeftFormatted);
    }
    long ni;
    public void pause(){
         ni = timeinmillisec;
        countDownTimer.cancel();
        timerunning = false;

    }
    public void play(){
        times = ni;
        countDownTimer = new CountDownTimer(times,1000) {
            @Override
            public void onTick(long l) {
                timeinmillisec = l;
                updatecountdown();
            }

            @Override
            public void onFinish() {
                timerunning = false;
                Intent intent = new Intent(StopWatch.this, EmotionFeedback.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }.start();
        timerunning = true;

    }

    public void setRestart(){
        countDownTimer.cancel();
        timeinmillisec = i*60;
        timeinmillisec = timeinmillisec*1000;
        updatecountdown();
        starttimmer();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(StopWatch.this, EmotionFeedback.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
}