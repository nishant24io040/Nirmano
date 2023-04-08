package com.mental.mentalwellness.ClickOnFeeling.CommonForAllFeelings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.mental.mentalwellness.R;

public class BeforeSolution extends AppCompatActivity {

    public int counter;
    Bundle bundle;
    String s;
    CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_solution);
        bundle = getIntent().getExtras();
//        s=bundle.getString("feeling");
        timer = new CountDownTimer(5000, 1000){
            public void onTick(long millisUntilFinished){
                counter++;
            }
            public  void onFinish(){
                Intent intent = new Intent(BeforeSolution.this,ChoosWhatYouEnjoy.class);
//                intent.putExtra("feeling",s);
                startActivity(intent);
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        timer.cancel();
        super.onBackPressed();
    }
}