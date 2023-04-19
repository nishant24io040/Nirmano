package com.nirman.mentalwellness;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.nirman.Globals;
import com.google.firebase.auth.FirebaseAuth;

public class Releaf extends AppCompatActivity {
    EditText paper;
    LottieAnimationView fire;
    LinearLayout bottomBar , bottomLP;
    LinearLayout homeLP;
    LinearLayout reliefLP;
    LinearLayout journalLP;
    LinearLayout feedLP;
    ImageView reliefLPIcon;
    ConstraintLayout infoPopup;
    TextView popupSkipButton;
    ImageButton burnButton , backBtn;
    MediaPlayer fire_sound_effect;
    FirebaseAuth mAuth;
    final String PREFS_NAME2 = "MyPrefsFile2";
    public int counter;
    HomePage hp;
    boolean hello;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relief);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));
        SharedPreferences settings = getSharedPreferences(PREFS_NAME2, 0);
        backBtn = findViewById(R.id.relief_back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(Releaf.this,HomePage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("uid","a");
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        paper = findViewById(R.id.relief_text);
        fire = findViewById(R.id.fire_animation);
        infoPopup = findViewById(R.id.relief_popup);
        bottomBar = findViewById(R.id.relief_bottom_bar);
        bottomBar.setVisibility(View.GONE);
        popupSkipButton = infoPopup.findViewById(R.id.popup_skip_button);
        burnButton = findViewById(R.id.relief_burn_button);
        infoPopup.setVisibility(View.GONE);

        homeLP = findViewById(R.id.home_button);
        reliefLP = findViewById(R.id.relief_button);
        journalLP = findViewById(R.id.journal_button);
        feedLP = findViewById(R.id.feed_button);

        final String pref = "MyPrefsFile2";


        hello = HomePage.isNew;
        if (hello){
            infoPopup.setVisibility(View.VISIBLE);
        }



        if (settings.getBoolean("my_first_time", true)) {
            infoPopup.setVisibility(View.VISIBLE);
            //the app is being launched for first time, do something


            // first time task

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
        }

        fire_sound_effect = MediaPlayer.create(this, R.raw.sound_fire);

        setListeners();
    }

    private void setListeners () {
        bottomBar.setVisibility(View.VISIBLE);
        paper.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                KeyboardUtils.addKeyboardToggleListener(Releaf.this, new KeyboardUtils.SoftKeyboardToggleListener()
                {
                    @Override
                    public void onToggleSoftKeyboard(boolean isVisible)
                    {
                        if (isVisible){

                            bottomBar.setVisibility(View.GONE);
                        }
                        else {
                            if (!paper.getText().toString().trim().isEmpty()){
                            bottomBar.setVisibility(View.VISIBLE);}
                            else {
                                bottomBar.setVisibility(View.VISIBLE);
                                Toast.makeText(Releaf.this, "Write what's in your mind first! ", Toast.LENGTH_SHORT).show();


                        }}
                    }
                });

            }
        });


        burnButton.setOnClickListener(this::burn);

        popupSkipButton.setOnClickListener(view ->
                infoPopup.animate()
                        .translationY(infoPopup.getHeight() + 10)
                        .withEndAction(() -> infoPopup.setVisibility(View.GONE))
                        .setDuration(300)
                        .start()
        );

          }

    private void burn(View view) {
        if (!paper.getText().toString().isEmpty()){
        bottomBar.setVisibility(View.GONE);
        fire.setVisibility(View.VISIBLE);

        fire.animate()
                .withStartAction(() -> {
                    fire_sound_effect.setLooping(true);
                    fire_sound_effect.start();
                    fire.setVisibility(View.VISIBLE);
                })
                .translationY(0)
                .setDuration(10000)
                .withEndAction(() -> fire.setVisibility(View.GONE))
                .withEndAction(() -> fire_sound_effect.stop())
                .start();

        paper.animate()
                .translationY(paper.getHeight() + 100)
                .withEndAction(() -> paper.setVisibility(View.GONE))
                .setDuration(10000)
                .start();

        new CountDownTimer(10000, 1000){
            public void onTick(long millisUntilFinished){
                counter++;
            }
            public  void onFinish(){
                finish();
                Intent i = new Intent(Releaf.this,EmotionFeedback.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("uid","a");
                startActivity(i);
            }
        }.start();
    }
    else Toast.makeText(this, "Write Down Your Frustation First", Toast.LENGTH_SHORT).show();}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}