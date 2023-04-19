package com.nirman.mentalwellness.TherepySection;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nirman.mentalwellness.R;

public class MainActivity3 extends AppCompatActivity {
    private Button button_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        button_submit=findViewById(R.id.button_submit);
        button_submit.setOnClickListener(v->{
            startActivity(new Intent(this,landing_page.class));
        });
    }
}