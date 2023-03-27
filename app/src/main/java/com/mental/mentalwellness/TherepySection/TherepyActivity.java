package com.mental.mentalwellness.TherepySection;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mental.mentalwellness.R;

public class TherepyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therepy);

        findViewById(R.id.view_profile).setOnClickListener(v -> {
            startActivity(new Intent(this, BookASlot.class));
        });
    }
}