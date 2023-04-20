package com.ansrnirmano.mentalwellness.ClickOnFeeling.CommonForAllFeelings;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;


import com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnAngry.DepthOfAngryLavel1.AngryLevel1;
import com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnFear.DepthOfFearlevel1.FearLevel1;
import com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnSad.DepthOfEmotionLevel1.SadLevel1;
import com.ansrnirmano.mentalwellness.R;

public class AfterLastQuestionActivity extends AppCompatActivity {
    public int counter;
    Bundle bundle;
    CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_last_question);
        bundle = getIntent().getExtras();
        timer = new CountDownTimer(5000, 1000){
            public void onTick(long millisUntilFinished){
                counter++;
            }
            public  void onFinish(){
//                SharedPreferences sd = getSharedPreferences("option", Context.MODE_PRIVATE);
//                SharedPreferences.Editor ed = sd.edit();
//                ed.remove("q");
//                ed.remove("con");
//                ed.remove("con3");
//                ed.remove("q3");
//                ed.remove("con4");
//                ed.remove("q4");
//                ed.remove("q5");
//                ed.commit();

                String p = bundle.getString("depth");
                String s = bundle.getString("feeling");
                if(p!=null) {
                    if (p.equals("continue")) {
                        Intent intent = new Intent(AfterLastQuestionActivity.this, ChoosWhatYouEnjoy.class);
                        startActivity(intent);
                    } else {
                        switch (s) {
                            case "sad":
                                Intent intent = new Intent(AfterLastQuestionActivity.this, SadLevel1.class);
                                startActivity(intent);
                                break;
                            case "angry":
                                Intent intent1 = new Intent(AfterLastQuestionActivity.this, AngryLevel1.class);
                                startActivity(intent1);
                                break;
                            case "fear":
                                Intent intent2 = new Intent(AfterLastQuestionActivity.this, FearLevel1.class);
                                startActivity(intent2);
                                break;
                        }
                    }

                }
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        timer.cancel();
        super.onBackPressed();
    }
}