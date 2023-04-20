package com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnSad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnSad.QuestionSadFragments.sadquestion1;
import com.ansrnirmano.mentalwellness.R;


public class QuesionsSadActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    TextView status;
    ProgressBar progressBar;
    Bundle bundle;
    CardView cad,backcard;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ModalforSadQuestion template;
    int counter=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesions_sad);
//        next = findViewById(R.id.btnnext2);
//        goback = findViewById(R.id.goback2);
        frameLayout = findViewById(R.id.fram);
        cad = findViewById(R.id.cardView0);
        backcard = findViewById(R.id.cardView5);
        status = findViewById(R.id.status);
        progressBar = findViewById(R.id.progressBar2);
        bundle = getIntent().getExtras();
        mAuth = FirebaseAuth.getInstance();
        template = new ModalforSadQuestion();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Questions").child(mAuth.getCurrentUser().getUid()).child("sad");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fram,new sadquestion1());
        fragmentManager.popBackStack();
        fragmentTransaction.commit();

        Animation move = AnimationUtils.loadAnimation(this,R.anim.card_flip_down);

//        next.setOnClickListener(view -> {
//            if (counter == 1){
//                replaceFragment(new sadquestion2());
//                counter++;
//                next.setVisibility(View.INVISIBLE);
//            } else if (counter == 2) {
//                replaceFragment(new sadquestion3());
//                counter++;
//            }else if (counter == 3) {
//                replaceFragment(new sadquestion4());
//                next.setVisibility(View.INVISIBLE);
//                counter++;
//            }else if (counter == 4) {
//                replaceFragment(new sadquestion5());
//                next.setVisibility(View.INVISIBLE);
//                counter++;
//            }else if (counter == 5) {
//                String s = bundle.getString("depth");
//                Intent intent = new Intent(this, AfterLastQuestionActivity.class);
//                intent.putExtra("depth",s);
//                intent.putExtra("feeling","sad");
//
//                startActivity(intent);
//            }
//            progressBar.setProgress(counter);
//            progressBar.setMax(5);
//        });
        IntentFilter filter = new IntentFilter("data");
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int i =intent.getExtras().getInt("msg");
                if (i == 0){
                    status.setText("This is meant to help you realize how much this emotion affected you till now.");
                } else if (i==1) {
                    template.setQ1(intent.getExtras().getString("option"));
                    status.setText("Answering this question will help you understand your level of emotion.");
                }else if (i==2) {
                    template.setQ2(intent.getExtras().getString("option"));
                    status.setText("You'll be able to better understand your emotions and what makes them arise by observing your behavior change and the reasons why it made you feel sad.");
                }else if (i==3) {
                    template.setQ3(intent.getExtras().getString("option"));
                    status.setText("Answering this question will help you realize the events that cause you to feel sad. which will assist in giving you a precise solution.");
                }else if (i==4) {
                    template.setQ4(intent.getExtras().getString("option"));
                    backcard.setVisibility(View.INVISIBLE);
                    status.setText("By responding to this, you'll be able to better understand the emotions you experience in relation to your symptoms.");
                }else if (i==5) {
                    template.setQ5(intent.getExtras().getString("option"));
                    databaseReference.push().setValue(template);
                    status.setText("By responding to this, you'll be able to better understand the emotions you experience in relation to your symptoms.");
                }
                progressBar.setProgress(i);

                   progressBar.setMax(5);
               }
        };

        registerReceiver(receiver,filter);
//        goback.setOnClickListener(view -> {
//            if (counter == 1){
//                onBackPressed();
//            }else if (counter == 2) {
//                replaceFragment(new sadquestion1());
//                counter--;
//            }else if (counter == 3) {
//                replaceFragment(new sadquestion2());
//                counter--;
//            }else if (counter == 4) {
//                replaceFragment(new sadquestion3());
//                counter--;
//            }else if (counter == 5) {
//                replaceFragment(new sadquestion4());
//                counter--;
//            }
//            progressBar.setProgress(counter);
//            progressBar.setMax(5);
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sd = getSharedPreferences("option", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sd.edit();
        ed.remove("q");
        ed.remove("con");
        ed.remove("con3");
        ed.remove("q3");
        ed.remove("con4");
        ed.remove("q4");
        ed.remove("q5");
        ed.commit();
    }

    //    private void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fram,fragment);
//        fragmentManager.popBackStack();
//        fragmentTransaction.commit();
//    }
}