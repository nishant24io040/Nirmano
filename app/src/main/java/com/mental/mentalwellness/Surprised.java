package com.mental.mentalwellness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Surprised extends AppCompatActivity {

    CardView surprise,ammazed,overcome,touched,stunning;
    TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surprised);
        ammazed = findViewById(R.id.amazed);
        surprise = findViewById(R.id.surprise);
        overcome = findViewById(R.id.overcome);
        touched = findViewById(R.id.touched);
        stunning = findViewById(R.id.stunning);
        back = findViewById(R.id.backd3);
        back.setOnClickListener(view -> {
            onBackPressed();
        });

        ammazed.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","surprised");
            intent.putExtra("feeling","Ammazed");
            startActivity(intent);
        });
        surprise.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","surprised");
            intent.putExtra("feeling","Surprise");
            startActivity(intent);
        });
        overcome.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","surprised");
            intent.putExtra("feeling","Overcome");
            startActivity(intent);
        });
        touched.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","surprised");
            intent.putExtra("feeling","Touched");
            startActivity(intent);
        });
        stunning.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","surprised");
            intent.putExtra("feeling","Stunning");
            startActivity(intent);
        });
    }
}