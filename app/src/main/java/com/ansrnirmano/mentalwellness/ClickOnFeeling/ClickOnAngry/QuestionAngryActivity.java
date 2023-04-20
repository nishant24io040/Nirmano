package com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnAngry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnAngry.QuestionAngryFragments.Angryquestion1;
import com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnAngry.QuestionAngryFragments.Angryquestion2;
import com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnAngry.QuestionAngryFragments.Angryquestion3;
import com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnAngry.QuestionAngryFragments.Angryquestion4;
import com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnAngry.QuestionAngryFragments.Angryquestion5;
import com.ansrnirmano.mentalwellness.ClickOnFeeling.CommonForAllFeelings.AfterLastQuestionActivity;
import com.ansrnirmano.mentalwellness.R;


public class QuestionAngryActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    DatabaseReference reference;
    Bundle bundle;
    TextView status;
    ModalForAngryQuestion template;
    Button next,goback;
    ProgressBar progressBar;
    CardView backcard;
    int counter=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_angry);
        status = findViewById(R.id.status);
        frameLayout = findViewById(R.id.fram);
        next = findViewById(R.id.btnnext2);
        backcard =findViewById(R.id.cdv);
        progressBar =findViewById(R.id.progressBar3);
        goback = findViewById(R.id.goback2);
        bundle = getIntent().getExtras();
        template = new ModalForAngryQuestion();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fram,new Angryquestion1());
        fragmentManager.popBackStack();
        fragmentTransaction.commit();
        reference = database.getReference().child("Questions").child(mAuth.getCurrentUser().getUid()).child("angry");
        next.setOnClickListener(view -> {
            if (counter == 1){
                replaceFragment(new Angryquestion2());
                status.setText("You will begin to understand how it occurred, why it happened, and under what circumstances as you record and recall all the moments filled with fear.");
                counter++;
            } else if (counter == 2) {
                replaceFragment(new Angryquestion3());
                status.setText("Answering this question is the first step on your path to emotional self-awareness.");
                counter++;
            }else if (counter == 3) {
                replaceFragment(new Angryquestion4());
                status.setText("Answering this question will help you realize the events that cause you to feel angry. which will assist in giving you a precise solution.");
                counter++;
            }else if (counter == 4) {
                replaceFragment(new Angryquestion5());
                status.setText("By responding to this, you'll be able to better understand the emotions you experience in relation to your symptoms.");
                counter++;
                backcard.setVisibility(View.INVISIBLE);
            }else if (counter == 5) {
                String s = bundle.getString("depth");
                reference.push().setValue(template);
                Intent intent = new Intent(this, AfterLastQuestionActivity.class);
                if (s!=null){
                    intent.putExtra("depth",s);
                }
                else {
                    intent.putExtra("depth"," ");
                }
                intent.putExtra("feeling","angry");
                startActivity(intent);
            }
            progressBar.setProgress(counter);
        });
        progressBar.setProgress(counter);
        progressBar.setMax(5);
        IntentFilter filter = new IntentFilter("data");
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getExtras().getString("msg").equals("1")){
                    next.setVisibility(View.VISIBLE);
                } else if (intent.getExtras().getString("msg").equals("2")) {
                    next.setVisibility(View.INVISIBLE);
                }
                int i =intent.getExtras().getInt("no");
                if (i == 0){
                } else if (i==1) {
                    template.setQ1(intent.getExtras().getString("option"));
                }else if (i==2) {
                    template.setQ2(intent.getExtras().getString("option"));
                }else if (i==3) {
                    template.setQ3(intent.getExtras().getString("option"));
                }else if (i==4) {
                    template.setQ4(intent.getExtras().getString("option"));
                }else if (i==5) {
                    template.setQ5(intent.getExtras().getString("option"));
                }
            }
        };
        registerReceiver(receiver,filter);
        goback.setOnClickListener(view -> {
            if (counter == 1){
               onBackPressed();
            }else if (counter == 2) {
                replaceFragment(new Angryquestion1());
                status.setText("This is meant to help you realize how much this emotion affected you till now.");
                counter--;
            }else if (counter == 3) {
                replaceFragment(new Angryquestion2());
                status.setText("You will begin to understand how it occurred, why it happened, and under what circumstances as you record and recall all the moments filled with fear.");
                counter--;
            }else if (counter == 4) {
                replaceFragment(new Angryquestion3());
                status.setText("Answering this question is the first step on your path to emotional self-awareness.");
                counter--;
            }else if (counter == 5) {
                replaceFragment(new Angryquestion4());
                status.setText("Answering this question will help you realize the events that cause you to feel angry. which will assist in giving you a precise solution.");
                counter--;
            }
            progressBar.setProgress(counter);
            progressBar.setMax(5);
        });
    }

    @Override
    protected void onDestroy() {
        SharedPreferences sd = getSharedPreferences("option1", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sd.edit();
        ed.remove("con0");
        ed.remove("con1");
        ed.remove("con2");
        ed.commit();
        super.onDestroy();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fram,fragment);
        fragmentManager.popBackStack();
        fragmentTransaction.commit();
    }
}