package com.mental.mentalwellness.TherepySection;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mental.mentalwellness.R;

public class TherepyActivity extends AppCompatActivity {


    Button reschedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therepy);
        reschedule = findViewById(R.id.reschdl);
        findViewById(R.id.view_profile).setOnClickListener(v -> {
            startActivity(new Intent(this, profile_page1.class));
        });
        reschedule.setOnClickListener(v -> {
            opendialog();
        });
    }

    private void opendialog() {
        Button rescadule,decline;

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.reminder);

        rescadule = dialog.findViewById(R.id.button9);
        decline = dialog.findViewById(R.id.button8);
        dialog.show();

        decline.setOnClickListener(v -> {
            dialog.dismiss();
        });
        rescadule.setOnClickListener(v -> {
            Intent intent = new Intent(this,resudule.class);
            startActivity(intent);
        });

    }
}