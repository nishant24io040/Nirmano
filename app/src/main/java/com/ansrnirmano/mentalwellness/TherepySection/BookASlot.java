package com.ansrnirmano.mentalwellness.TherepySection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.ansrnirmano.mentalwellness.R;

public class BookASlot extends AppCompatActivity {

    CalendarView calendarView;
    TextView time1,time2;
    Button btnpay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_aslot);
        calendarView = findViewById(R.id.calendarView);
        btnpay = findViewById(R.id.pay);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) ->{

        });
        calendarView.setSelectedWeekBackgroundColor(getResources().getColor(R.color.wallet_holo_blue_light));
        time1 = findViewById(R.id.time1);
        time2 = findViewById(R.id.time2);
        time1.setOnClickListener(v -> {
            time1.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            time1.setTextColor(getResources().getColor(R.color.white));
            time2.setBackground(getResources().getDrawable(R.drawable.bluebordorandwhite));
            time2.setTextColor(getResources().getColor(R.color._bg___1_first_screen_color));
        });
        time2.setOnClickListener(v -> {
            time2.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            time2.setTextColor(getResources().getColor(R.color.white));
            time1.setBackground(getResources().getDrawable(R.drawable.bluebordorandwhite));
            time1.setTextColor(getResources().getColor(R.color._bg___1_first_screen_color));
        });
        btnpay.setOnClickListener(v -> {
            Intent intent = new Intent(this, TherepyActivity.class);
            SharedPreferences depth = this.getSharedPreferences("datafile1",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("payment","complete");
            ed.commit();

            SharedPreferences sd = this.getSharedPreferences("noti", Context.MODE_PRIVATE);
            SharedPreferences.Editor eid = sd.edit();
            eid.remove("yes");
            eid.commit();
            startActivity(intent);
        });
    }
}