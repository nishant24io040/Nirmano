package com.nirman.mentalwellness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LovedActivity extends AppCompatActivity {
    CardView peaceful,caring,passinate,affectionate,longing,desire,attracted,satisfied;
    TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loved);
        peaceful = findViewById(R.id.peaceful);
        caring = findViewById(R.id.caring);
        passinate = findViewById(R.id.passinate);
        affectionate = findViewById(R.id.affectionate);
        longing = findViewById(R.id.longing);
        desire = findViewById(R.id.desire);
        attracted = findViewById(R.id.attrected);
        satisfied = findViewById(R.id.satisfied);
        back = findViewById(R.id.backd);
        back.setOnClickListener(view -> {
            onBackPressed();
        });

        peaceful.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","loved");
            intent.putExtra("feeling","Peaceful");
            startActivity(intent);
        });
        caring.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","loved");
            intent.putExtra("feeling","Caring");
            startActivity(intent);
        });
        passinate.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","loved");
            intent.putExtra("feeling","Passionate");
            startActivity(intent);
        });
        affectionate.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","loved");
            intent.putExtra("feeling","Affectionate");
            startActivity(intent);
        });
        longing.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","loved");
            intent.putExtra("feeling","Longing");
            startActivity(intent);
        });
        desire.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","loved");
            intent.putExtra("feeling","Desire");
            startActivity(intent);
        });
        attracted.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","loved");
            intent.putExtra("feeling","Attracted");
            startActivity(intent);
        });
        satisfied.setOnClickListener(view -> {
            Intent intent = new Intent(this,CreateMemory.class);
            intent.putExtra("emotion","loved");
            intent.putExtra("feeling","Satisfied");
            startActivity(intent);
        });
    }
}