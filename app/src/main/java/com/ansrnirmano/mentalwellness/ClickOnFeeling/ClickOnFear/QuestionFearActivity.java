package com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnFear;

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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnFear.QuestionFearFragments.Fearquestion1;
import com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnFear.QuestionFearFragments.Fearquestion2;
import com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnFear.QuestionFearFragments.Fearquestion3;
import com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnFear.QuestionFearFragments.Fearquestion4;
import com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnFear.QuestionFearFragments.Fearquestion5;
import com.ansrnirmano.mentalwellness.ClickOnFeeling.CommonForAllFeelings.AfterLastQuestionActivity;
import com.ansrnirmano.mentalwellness.ClickOnFeeling.CommonForAllFeelings.ReasonOfFeeling;
import com.ansrnirmano.mentalwellness.R;

public class QuestionFearActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    DatabaseReference reference;
    Bundle bundle;
    Button next,goback;
    TextView status;
    ProgressBar progressBar;
    ModalForFearQuestion template;
    int counter =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_fear);
        frameLayout = findViewById(R.id.fram);
        next = findViewById(R.id.btnnext2);
        goback = findViewById(R.id.goback2);
        bundle = getIntent().getExtras();
        progressBar =findViewById(R.id.progressBar4);
        status = findViewById(R.id.status);

        template = new ModalForFearQuestion();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fram,new Fearquestion1());
        fragmentManager.popBackStack();
        fragmentTransaction.commit();
        reference = database.getReference().child("Questions").child(mAuth.getCurrentUser().getUid()).child("fear");
        next.setOnClickListener(view -> {
            if (counter == 1){
                replaceFragment(new Fearquestion2());
                status.setText("This is meant to help you realize how much influence and duration this emotion took away from you");
                counter++;
            } else if (counter == 2) {
                replaceFragment(new Fearquestion3());
                status.setText("This is meant to help you realize how much this emotion influenced you and in what way.");
                counter++;
            }else if (counter == 3) {
                replaceFragment(new Fearquestion4());
                status.setText("Answering this question will assist you in determining the intensity of your Fear emotion. which will assist in giving you a precise solution.");
                counter++;
            }else if (counter == 4) {
                replaceFragment(new Fearquestion5());
                status.setText("Answering this question is the first step on your path to emotional self-awareness.");
                counter++;
            }else if (counter == 5) {
                reference.push().setValue(template);
                String s = bundle.getString("depth");
                Intent intent = new Intent(this, AfterLastQuestionActivity.class);
                if (s!=null){
                    intent.putExtra("depth",s);
                }
                else {
                    intent.putExtra("depth"," ");
                }
                intent.putExtra("feeling","fear");
                startActivity(intent);
            }
            progressBar.setProgress(counter);
            progressBar.setMax(5);
        });
        IntentFilter filter = new IntentFilter("data1");
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
                Intent intent = new Intent(this, ReasonOfFeeling.class);
                intent.putExtra("feeling","feared");
                startActivity(intent);
            }else if (counter == 2) {
                replaceFragment(new Fearquestion1());
                counter--;
            }else if (counter == 3) {
                replaceFragment(new Fearquestion2());
                counter--;
            }else if (counter == 4) {
                replaceFragment(new Fearquestion3());
                counter--;
            }else if (counter == 5) {
                replaceFragment(new Fearquestion4());
                counter--;
            }
            progressBar.setProgress(counter);
            progressBar.setMax(5);
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sd = getSharedPreferences("option2", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sd.edit();
        ed.remove("con8");
        ed.remove("con1");
        ed.remove("con9");
        ed.remove("con5");
        ed.commit();
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fram,fragment);
        fragmentManager.popBackStack();
        fragmentTransaction.commit();
    }
}