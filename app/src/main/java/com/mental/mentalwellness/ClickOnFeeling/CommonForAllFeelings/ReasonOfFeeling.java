package com.mental.mentalwellness.ClickOnFeeling.CommonForAllFeelings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mental.mentalwellness.ClickOnFeeling.ClickOnAngry.QuestionAngryActivity;
import com.mental.mentalwellness.ClickOnFeeling.ClickOnFear.QuestionFearActivity;
import com.mental.mentalwellness.ClickOnFeeling.ClickOnSad.QuesionsSadActivity;
import com.mental.mentalwellness.R;


public class ReasonOfFeeling extends AppCompatActivity {
    Button btn1;
    Bundle bundle;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv15,tv17,tv22,reson;
    int a=0;
    int b=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reason_of_feeling);
        btn1 = findViewById(R.id.btn1);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv15 = findViewById(R.id.textView15);
        tv17 = findViewById(R.id.textView17);
        reson = findViewById(R.id.reson);
        tv22 = findViewById(R.id.textView22);
        bundle = getIntent().getExtras();
        String s = bundle.getString("feeling");
        String p = bundle.getString("depth");

        if (s.equals("sad")){
        } else if (s.equals("angry")) {
            tv15.setBackgroundColor(getResources().getColor(R.color.angry));
            tv17.setBackgroundColor(getResources().getColor(R.color.angry));
            tv22.setBackgroundColor(getResources().getColor(R.color.angry));
            reson.setText("Whom are you angry at ? ");
        }else if (s.equals("feared")) {
            tv15.setBackgroundColor(getResources().getColor(R.color.fear));
            tv17.setBackgroundColor(getResources().getColor(R.color.fear));
            tv22.setBackgroundColor(getResources().getColor(R.color.fear));
            reson.setText("What type of fear you have?");

        }
        tv1.setOnClickListener(view -> {
            tv1.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv3.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv4.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv5.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv6.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv7.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            btn1.setVisibility(View.VISIBLE);

        });
        tv2.setOnClickListener(view -> {
            tv2.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv1.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv3.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv4.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv5.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv6.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv7.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            btn1.setVisibility(View.VISIBLE);

        });
        tv3.setOnClickListener(view -> {
            tv3.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv1.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv4.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv5.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv6.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv7.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            btn1.setVisibility(View.VISIBLE);

        });
        tv4.setOnClickListener(view -> {
            tv4.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv3.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv1.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv5.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv6.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv7.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            btn1.setVisibility(View.VISIBLE);

        });
        tv5.setOnClickListener(view -> {
            tv5.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv3.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv4.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv1.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv6.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv7.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            btn1.setVisibility(View.VISIBLE);

        });
        tv6.setOnClickListener(view -> {
            tv6.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv3.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv4.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv5.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv1.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv7.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            btn1.setVisibility(View.VISIBLE);

        });
        tv7.setOnClickListener(view -> {
            tv7.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv3.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv4.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv5.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv6.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv1.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            btn1.setVisibility(View.VISIBLE);
        });
        btn1.setOnClickListener(view -> {
            try {
                if (s.equals("sad")){
                    Intent intent = new Intent(this, QuesionsSadActivity.class);
                    intent.putExtra("depth",p);
                    startActivity(intent);
                } else if (s.equals("angry")) {
                    Intent intent = new Intent(this, QuestionAngryActivity.class);
                    intent.putExtra("depth",p);
                    startActivity(intent);
                }else if (s.equals("feared")) {
                    Intent intent = new Intent(this, QuestionFearActivity.class);
                    intent.putExtra("depth",p);
                    startActivity(intent);
                }
            }catch (Exception e)
            {
                Toast.makeText(this, "error here", Toast.LENGTH_SHORT).show();
            }

        });

    }
    private void changebackground(TextView v){

        if(a==2){
            a=3;
            v.setBackground(getResources().getDrawable(R.drawable.bluebordor));

        }
        else {
            a=2;
            v.setBackground(getResources().getDrawable(R.drawable.onselectbg));
        }
    }
}