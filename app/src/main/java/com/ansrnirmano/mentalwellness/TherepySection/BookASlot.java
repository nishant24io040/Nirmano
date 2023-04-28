package com.ansrnirmano.mentalwellness.TherepySection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.ansrnirmano.mentalwellness.ModalForBookedSession;
import com.ansrnirmano.mentalwellness.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class BookASlot extends AppCompatActivity {

    CalendarView calendarView;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    TextView time1,time2;
    Button btnpay;
    String s;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_aslot);
        calendarView = findViewById(R.id.calendarView);
        btnpay = findViewById(R.id.pay);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) ->{
            date = dayOfMonth+"/"+month+"/"+year;
        });
        calendarView.setSelectedWeekBackgroundColor(getResources().getColor(R.color.wallet_holo_blue_light));
        time1 = findViewById(R.id.time1);
        time2 = findViewById(R.id.time2);
        time1.setOnClickListener(v -> {
            s=time1.getText().toString();
            time1.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            time1.setTextColor(getResources().getColor(R.color.white));
            time2.setBackground(getResources().getDrawable(R.drawable.bluebordorandwhite));
            time2.setTextColor(getResources().getColor(R.color._bg___1_first_screen_color));
        });
        time2.setOnClickListener(v -> {
            s=time1.getText().toString();
            time2.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            time2.setTextColor(getResources().getColor(R.color.white));
            time1.setBackground(getResources().getDrawable(R.drawable.bluebordorandwhite));
            time1.setTextColor(getResources().getColor(R.color._bg___1_first_screen_color));
        });
        btnpay.setOnClickListener(v -> {
            ModalForBookedSession session = new ModalForBookedSession(s,date,"","");
            database.getReference("UpcomingSession").child(mAuth.getCurrentUser().getUid()).setValue(session).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(BookASlot.this, "good", Toast.LENGTH_SHORT).show();
                }
            });
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