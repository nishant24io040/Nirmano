package com.mental.mentalwellness.TherepySection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.CalendarView;

import com.mental.mentalwellness.R;

import java.util.Calendar;

public class BookASlot extends AppCompatActivity {

    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_aslot);
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) ->{

        });
        calendarView.setSelectedWeekBackgroundColor(getResources().getColor(R.color.wallet_holo_blue_light));

    }
}