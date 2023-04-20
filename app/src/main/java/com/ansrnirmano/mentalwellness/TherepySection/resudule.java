package com.ansrnirmano.mentalwellness.TherepySection;

import android.os.Bundle;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;

import com.ansrnirmano.mentalwellness.R;

public class resudule extends AppCompatActivity {

    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resudul);
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) ->{

        });
        calendarView.setSelectedWeekBackgroundColor(getResources().getColor(R.color.wallet_holo_blue_light));

    }
}