package com.nirman.mentalwellness.ClickOnFeeling.ClickOnSad.DepthOfEmotionLevel1;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nirman.mentalwellness.ClickOnFeeling.ClickOnSad.DepthOfEmotionLevel1.Level2.Despair;
import com.nirman.mentalwellness.ClickOnFeeling.ClickOnSad.DepthOfEmotionLevel1.Level2.Disappointed;
import com.nirman.mentalwellness.ClickOnFeeling.ClickOnSad.DepthOfEmotionLevel1.Level2.Neglected;
import com.nirman.mentalwellness.ClickOnFeeling.ClickOnSad.DepthOfEmotionLevel1.Level2.Shameful;
import com.nirman.mentalwellness.ClickOnFeeling.ClickOnSad.DepthOfEmotionLevel1.Level2.Suffring;
import com.nirman.mentalwellness.ClickOnFeeling.ClickOnSad.DepthOfEmotionLevel1.Level2.Upset;
import com.nirman.mentalwellness.ClickOnFeeling.CommonForAllFeelings.BeforeSolution;
import com.nirman.mentalwellness.R;

public class SadLevel1 extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5,btn6;
    ImageView blurbg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sad_level1);
        btn1 = findViewById(R.id.upset);
        btn2 = findViewById(R.id.suffering);
        btn3 = findViewById(R.id.disappointed);
        btn4 = findViewById(R.id.shameful);
        btn5 = findViewById(R.id.negeleted);
        btn6 = findViewById(R.id.despair);
        blurbg = findViewById(R.id.blurebg);

       btn1.setOnClickListener(view -> {
            upset();
        });
        btn2.setOnClickListener(view -> {
            suffering();
        });
        btn3.setOnClickListener(view -> {
            disappointed();
        });
        btn4.setOnClickListener(view -> {
            shamful();
        });
        btn5.setOnClickListener(view -> {
            negleted();
        });
        btn6.setOnClickListener(view -> {
            despair();
        });
    }
    private void upset(){
        Button btncontinue,knowmore;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(SadLevel1.this);
        dialog.setContentView(R.layout.custom_dialog_layout_for_level2);

        btncontinue = dialog.findViewById(R.id.btncontinue);
        knowmore = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);
        blurbg.setVisibility(View.VISIBLE);
        title.setText("UPSET");
        btncontinue.setText("continue with upset");
        knowmore.setText("know more");
        firstbody.setText("You are unhappy or disappointed because something bad has happened to you.");
        secbody.setText(R.string.upset);
        knowmore.setOnClickListener(view -> {
            Intent intent = new Intent(SadLevel1.this, Upset.class);
            startActivity(intent);
        });
        btncontinue.setOnClickListener(view -> {
            SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling","upset");
            ed.commit();
            Intent intent = new Intent(SadLevel1.this, BeforeSolution.class);
            startActivity(intent);
        });
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> {
            blurbg.setVisibility(View.GONE);
        });
    }
    private void suffering(){
        Button btncontinue,knowmore;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(SadLevel1.this);
        dialog.setContentView(R.layout.custom_dialog_layout_for_level2);

        btncontinue = dialog.findViewById(R.id.btncontinue);
        knowmore = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);
        blurbg.setVisibility(View.VISIBLE);
        knowmore.setOnClickListener(view -> {
            Intent intent = new Intent(SadLevel1.this, Suffring.class);
            startActivity(intent);
        });
        title.setText("Suffering");
        title.setAllCaps(true);
        btncontinue.setText("continue with Suffring");
        knowmore.setText("know more");
        btncontinue.setOnClickListener(view -> {
            SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling","suffering");
            ed.commit();
            Intent intent = new Intent(SadLevel1.this, BeforeSolution.class);
            startActivity(intent);
        });
        firstbody.setText(R.string.suf);
        secbody.setText(R.string.suffering);
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> {
            blurbg.setVisibility(View.GONE);
        });


    }
    private void disappointed(){
        Button btncontinue,knowmore;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(SadLevel1.this);
        dialog.setContentView(R.layout.custom_dialog_layout_for_level2);

        btncontinue = dialog.findViewById(R.id.btncontinue);
        knowmore = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);
        title.setAllCaps(true);
        blurbg.setVisibility(View.VISIBLE);
        title.setText("Disappointed");
        btncontinue.setText("continue with Disappointed");
        knowmore.setText("know more");
        btncontinue.setOnClickListener(view -> {
            SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling","disappointed");
            ed.commit();
            Intent intent = new Intent(SadLevel1.this, BeforeSolution.class);
            startActivity(intent);
        });
        firstbody.setText("Feeling unhappy because someone or something was not as good as you hoped or expected");
        secbody.setText(R.string.disappointed);
        knowmore.setOnClickListener(view -> {

            Intent intent = new Intent(SadLevel1.this, Disappointed.class);
            startActivity(intent);
        });
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> {
            blurbg.setVisibility(View.GONE);
        });
    }
    private void shamful(){
        Button btncontinue,knowmore;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(SadLevel1.this);
        dialog.setContentView(R.layout.custom_dialog_layout_for_level2);

        btncontinue = dialog.findViewById(R.id.btncontinue);
        knowmore = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);
        blurbg.setVisibility(View.VISIBLE);
        title.setText("Shameful");
        title.setAllCaps(true);

        btncontinue.setText("continue with shameful");
        knowmore.setText("know more");
        firstbody.setText("A feeling of embarrassment or humiliation that arises from the perception of having done something dishonorable, immoral, or improper");
        secbody.setText(R.string.shameful);
        knowmore.setOnClickListener(view -> {
            SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling","shameful");
            ed.commit();
            Intent intent = new Intent(SadLevel1.this, Shameful.class);
            startActivity(intent);
        });
        btncontinue.setOnClickListener(view -> {
            Intent intent = new Intent(SadLevel1.this, BeforeSolution.class);
            startActivity(intent);
        });
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> {
            blurbg.setVisibility(View.GONE);
        });
    }
    private void negleted(){
        Button btncontinue,knowmore;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(SadLevel1.this);
        dialog.setContentView(R.layout.custom_dialog_layout_for_level2);

        btncontinue = dialog.findViewById(R.id.btncontinue);
        knowmore = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);
        blurbg.setVisibility(View.VISIBLE);
        title.setText("NEGLECTED");
        btncontinue.setText("continue with NEGLECTED");
        knowmore.setText("know more");
        firstbody.setText(R.string.negle);
        secbody.setText(R.string.negleted);
        knowmore.setOnClickListener(view -> {
            Intent intent = new Intent(SadLevel1.this, Neglected.class);
            startActivity(intent);
        });
        btncontinue.setOnClickListener(view -> {
            SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling","neglected");
            ed.commit();
            Intent intent = new Intent(SadLevel1.this, BeforeSolution.class);
            startActivity(intent);
        });
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> {
            blurbg.setVisibility(View.GONE);
        });
    }
    private void despair() {
        Button btncontinue,knowmore;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(SadLevel1.this);
        dialog.setContentView(R.layout.custom_dialog_layout_for_level2);

        btncontinue = dialog.findViewById(R.id.btncontinue);
        knowmore = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);
        blurbg.setVisibility(View.VISIBLE);
        title.setText("DESPAIR");
        btncontinue.setText("continue with despair");
        knowmore.setText("know more");
        firstbody.setText("Feeling a complete loss of hope, usually accompanied by desperation, anguish and sadness");
        secbody.setText(R.string.dispair);
        knowmore.setOnClickListener(view -> {
            Intent intent = new Intent(SadLevel1.this, Despair.class);
            startActivity(intent);
        });
        btncontinue.setOnClickListener(view -> {
            SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling","despair");
            ed.commit();
            Intent intent = new Intent(SadLevel1.this, BeforeSolution.class);
            startActivity(intent);
        });
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> {
            blurbg.setVisibility(View.GONE);
        });
    }
}