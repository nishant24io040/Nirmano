package com.ansrnirmano.mentalwellness.ClickOnFeeling.CommonForAllFeelings;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.ansrnirmano.mentalwellness.R;

public class IfContinueWithFeeling extends AppCompatActivity {
    public int counter;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_if_continue_with_feeling);
        bundle = getIntent().getExtras();
        new CountDownTimer(5000, 1000){
            public void onTick(long millisUntilFinished){
                counter++;
            }
            public  void onFinish(){
                String s = bundle.getString("feeling");
                Intent intent = new Intent(IfContinueWithFeeling.this, ReasonOfFeeling.class);
                intent.putExtra("depth","continue");
                intent.putExtra("feeling",s);
                startActivity(intent);
            }
        }.start();
    }
}