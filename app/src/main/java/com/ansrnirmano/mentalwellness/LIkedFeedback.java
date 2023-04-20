package com.ansrnirmano.mentalwellness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class LIkedFeedback extends AppCompatActivity {

    ExtendedFloatingActionButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_emotion_feedback);
        btn = findViewById(R.id.joinnow);
        btn.setOnClickListener(view -> {
            Intent i = new Intent(this,Tharapytemp.class);
            startActivity(i);
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this,HomePage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
    }
}