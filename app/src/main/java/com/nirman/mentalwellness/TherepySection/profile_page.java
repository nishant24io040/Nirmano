package com.nirman.mentalwellness.TherepySection;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nirman.mentalwellness.R;

public class profile_page extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        button=findViewById(R.id.button_submit);

        button.setOnClickListener(v->{
            startActivity(new Intent(this,profile_page1.class));
        });
    }
}