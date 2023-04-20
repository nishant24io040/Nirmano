package com.ansrnirmano.mentalwellness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.ansrnirmano.Globals;

import java.util.HashMap;
import java.util.Map;

public class letter extends AppCompatActivity {
    ImageView openButton;
    ConstraintLayout open,close;
    TextView text;
    FirebaseFirestore db;
    public int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));

        open = findViewById(R.id.envelope_open);
        openButton = findViewById(R.id.envelope_open_button);
        close = findViewById(R.id.envelope_close);
        text = findViewById(R.id.text);
        String name = getIntent().getStringExtra("name");
//        emotionTextManager(name);
        callFirebase(name);

        text.setText("“When things change inside you, things change around you.”");
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open.setVisibility(View.VISIBLE);
                close.setVisibility(View.GONE);
                openButton.setVisibility(View.GONE);
                if (open.getVisibility()==View.VISIBLE){
                    initClosing();
                }
            }
        });
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close.setVisibility(View.VISIBLE);
                openButton.setVisibility(View.VISIBLE);
                open.setVisibility(View.GONE);
            }

        });

    }
    public void initClosing(){
        new CountDownTimer(6000, 1000){
            public void onTick(long millisUntilFinished){
                counter++;
            }
            public  void onFinish(){
                Intent i = new Intent(letter.this,EmotionFeedback.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        }.start();
    }
    public void callFirebase(String emotion){
        try {


            db = FirebaseFirestore.getInstance();

            db.collection("letter")
                    .document(emotion)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                    Map<String, Object> sample = new HashMap<>();
                                    sample = task.getResult().getData();
                                    if (!sample.isEmpty()) {
                                        String data = (String) sample.get("letter");
                                        text.setText(data);


                                    } else {
                                        Toast.makeText(letter.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(letter.this, HomePage.class));
                                    }



                            } else {
                                Toast.makeText(letter.this, "Unable To Retrive Data From The Server", Toast.LENGTH_SHORT).show();

                            }


                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(letter.this,EmotionFeedback.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }
}