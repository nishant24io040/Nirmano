package com.mental.mentalwellness.ClickOnFeeling.CommonForAllFeelings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.mental.mentalwellness.ClickOnFeeling.ClickOnAngry.DepthOfAngryLavel1.AngryLevel1;
import com.mental.mentalwellness.ClickOnFeeling.ClickOnFear.DepthOfFearlevel1.FearLevel1;
import com.mental.mentalwellness.ClickOnFeeling.ClickOnSad.DepthOfEmotionLevel1.SadLevel1;
import com.mental.mentalwellness.R;

public class BeforeSolution extends AppCompatActivity {

    public int counter;
    Bundle bundle;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_solution);
        bundle = getIntent().getExtras();
        s=bundle.getString("feeling");
        new CountDownTimer(5000, 1000){
            public void onTick(long millisUntilFinished){
                counter++;
            }
            public  void onFinish(){
                Intent intent = new Intent(BeforeSolution.this,ChoosWhatYouEnjoy.class);
                intent.putExtra("feeling",s);
                startActivity(intent);
            }
        }.start();
    }
}