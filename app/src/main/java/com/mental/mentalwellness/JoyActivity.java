package com.mental.mentalwellness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class JoyActivity extends AppCompatActivity {
    CardView charming,enthusiastic,optimistic,proud,cheerful,happy;
    TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joy);
        charming = findViewById(R.id.Chaeming);
        enthusiastic = findViewById(R.id.Enthu);
        optimistic = findViewById(R.id.optimistic);
        proud = findViewById(R.id.pround);
        cheerful = findViewById(R.id.cheerful);
        happy = findViewById(R.id.happy);
        back = findViewById(R.id.backd1);
        back.setOnClickListener(view -> {
            onBackPressed();
        });

        charming.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","joy");
            intent.putExtra("feeling","Charming");
            startActivity(intent);
        });
        enthusiastic.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","joy");
            intent.putExtra("feeling","Enthusiastic");
            startActivity(intent);
        });
        optimistic.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","joy");
            intent.putExtra("feeling","Optimistic");
            startActivity(intent);
        });
        proud.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","joy");
            intent.putExtra("feeling","Proud");
            startActivity(intent);
        });
        cheerful.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","joy");
            intent.putExtra("feeling","Cheerful");
            startActivity(intent);
        });
        happy.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","joy");
            intent.putExtra("feeling","Happy");
            startActivity(intent);
        });


    }
}