package com.mental.mentalwellness;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.WindowCompat;

import com.mental.Globals;
import com.google.firebase.auth.FirebaseAuth;

import pl.droidsonroids.gif.GifImageButton;

public class recordActivity extends AppCompatActivity {
    ConstraintLayout layout;
    public int counter;
    ImageButton record , delete, reliefBack , restore, pause;
    TextView skip;
    ConstraintLayout reliefPop;
    ImageView broken,normal;
    MediaPlayer break_sound_effect;
    final String PREFS_NAME1 = "MyPrefsFile1";
    FirebaseAuth mAuth;
    GifImageButton recording_gif;
    CountDownTimer con;



// Releaf should come from backend

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));

        SharedPreferences settings = getSharedPreferences(PREFS_NAME1, 0);
        layout = findViewById(R.id.page_root_view);
        reliefBack = findViewById(R.id.relief_back_button);
        record = findViewById(R.id.record_button);
        reliefPop = findViewById(R.id.relief_popup);
        pause = findViewById(R.id.pause_button);
        delete = findViewById(R.id.delete_button);
        skip = findViewById(R.id.popup_skip_button);
        recording_gif = findViewById(R.id.recording_indicator);
        broken = findViewById(R.id.cassette_broken);
        restore = findViewById(R.id.retry_button);
        normal = findViewById(R.id.cassette_normal);
        reliefPop.setVisibility(View.GONE);
        delete.setEnabled(false);
        boolean hello = HomePage.isNew;
        if (hello){
            reliefPop.setVisibility(View.VISIBLE);
        }

        if (settings.getBoolean("my_first_time", true)) {
            reliefPop.setVisibility(View.VISIBLE);
            //the app is being launched for first time, do something


            // first time task

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).apply();
        }
        break_sound_effect = MediaPlayer.create(this, R.raw.sound_cassette_break);
        setListeners();
    }

    private void setListeners () {
        restore.setOnClickListener(view -> {
            restore.setVisibility(View.GONE);
            record.setVisibility(View.VISIBLE);
            broken.setVisibility(View.GONE);
            normal.setVisibility(View.VISIBLE);
            delete.setEnabled(false);
        });

        record.setOnClickListener(view -> {
            recording_gif.setVisibility(View.VISIBLE);
            normal.setVisibility(View.GONE);
            record.setVisibility(View.GONE);
            pause.setVisibility(View.VISIBLE);
            delete.setEnabled(false);

        });

        pause.setOnClickListener(view -> {
            delete.setEnabled(true);
            pause.setSelected(true);
            record.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
            pause.setVisibility(View.GONE);
            normal.setVisibility(View.VISIBLE);
            recording_gif.setVisibility(View.GONE);
        });

        delete.setOnClickListener(this::breakCassette);

        skip.setOnClickListener(view ->
                reliefPop.animate()
                        .translationY(reliefPop.getHeight() + 10)
                        .withEndAction(() -> reliefPop.setVisibility(View.GONE))
                        .setDuration(300)
                        .start()
        );

        reliefBack.setOnClickListener(view -> {
            finish();
            Intent i = new Intent(recordActivity.this,EmotionFeedback.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        });
    }

    private void breakCassette(View view) {
        delete.setVisibility(View.VISIBLE);
        record.setVisibility(View.GONE);
        pause.setVisibility(View.GONE);
        break_sound_effect.start();
//        recording_gif.animate().alpha(0).withEndAction(() -> recording_gif.setVisibility(View.GONE)).setDuration(300).setStartDelay(100).start();
//        broken.animate().alpha(1).withStartAction(() -> broken.setVisibility(View.VISIBLE)).withEndAction(this::deleteBtn).setDuration(300).setStartDelay(100).start();
        normal.setVisibility(View.GONE);
        broken.setVisibility(View.VISIBLE);
        restore.setVisibility(View.VISIBLE);
        con = new CountDownTimer(6000, 1000){
            public void onTick(long millisUntilFinished){
                counter++;
            }
            public  void onFinish(){
                Intent i = new Intent(recordActivity.this,EmotionFeedback.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };
        con.start();

    }
    private void deleteBtn(){
        restore.setVisibility(View.VISIBLE);
        record.setVisibility(View.GONE) ;
        pause.setVisibility(View.GONE);
        normal.setVisibility(View.GONE);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        con.cancel();
        finish();
}
}