package com.nirman.mentalwellness.ClickOnFeeling.ClickOnAngry.DepthOfAngryLavel1;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.nirman.mentalwellness.ClickOnFeeling.ClickOnAngry.DepthOfAngryLavel1.Level2.Disgust;
import com.nirman.mentalwellness.ClickOnFeeling.ClickOnAngry.DepthOfAngryLavel1.Level2.Envy;
import com.nirman.mentalwellness.ClickOnFeeling.ClickOnAngry.DepthOfAngryLavel1.Level2.Exasperated;
import com.nirman.mentalwellness.ClickOnFeeling.ClickOnAngry.DepthOfAngryLavel1.Level2.Irritable;
import com.nirman.mentalwellness.ClickOnFeeling.ClickOnAngry.DepthOfAngryLavel1.Level2.Rage;
import com.nirman.mentalwellness.ClickOnFeeling.CommonForAllFeelings.BeforeSolution;
import com.nirman.mentalwellness.R;

public class AngryLevel1 extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5;
    ImageView blurbg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angry_level1);
        btn1 = findViewById(R.id.rage);
        btn2 = findViewById(R.id.exasperated);
        btn3 = findViewById(R.id.irritable);
        btn4 = findViewById(R.id.envy);
        btn5 = findViewById(R.id.disgust);
        blurbg = findViewById(R.id.blurebg3);

        btn1.setOnClickListener(view -> {
            rage();
        });
        btn2.setOnClickListener(view -> {
            exasperated();
        });
        btn3.setOnClickListener(view -> {
            irritable();
        });
        btn4.setOnClickListener(view -> {
            envy();
        });
        btn5.setOnClickListener(view -> {
            disgust();
        });
    }
    private void rage(){
        Button btncontinue,knowmore;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(AngryLevel1.this);
        dialog.setContentView(R.layout.custom_dialog_layout_for_level2);

        btncontinue = dialog.findViewById(R.id.btncontinue);
        knowmore = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        title.setBackgroundColor(getResources().getColor(R.color.angry));
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);
        blurbg.setVisibility(View.VISIBLE);
        title.setText("Rage");
        btncontinue.setText("continue with Rage");
        knowmore.setText("know more");
        firstbody.setText("A feeling of violent anger that is difficult to control");
        secbody.setText(R.string.rage);
        knowmore.setOnClickListener(view -> {
            Intent intent = new Intent(AngryLevel1.this, Disgust.class);
            startActivity(intent);
        });
        btncontinue.setOnClickListener(view -> {

            SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling","rage");
            ed.commit();

            Intent intent = new Intent(AngryLevel1.this, BeforeSolution.class);
            startActivity(intent);
        });
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> {
            blurbg.setVisibility(View.GONE);
        });
    }
    private void exasperated(){
        Button btncontinue,knowmore;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(AngryLevel1.this);
        dialog.setContentView(R.layout.custom_dialog_layout_for_level2);

        btncontinue = dialog.findViewById(R.id.btncontinue);
        knowmore = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);
        blurbg.setVisibility(View.VISIBLE);

        SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
        SharedPreferences.Editor ed = depth.edit();
        ed.putString("feeling","exasperated");
        ed.commit();

        title.setText("EXASPERATED");
        title.setBackgroundColor(getResources().getColor(R.color.angry));
        btncontinue.setText("continue with Exasperated");
        knowmore.setText("know more");
        firstbody.setText("Having or showing strong feelings of irritation or annoyance.");
        secbody.setText(R.string.exaserated);
        knowmore.setOnClickListener(view -> {
            Intent intent = new Intent(AngryLevel1.this, Envy.class);
            startActivity(intent);
        });
        btncontinue.setOnClickListener(view -> {
            Intent intent = new Intent(AngryLevel1.this, BeforeSolution.class);
            startActivity(intent);
        });
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> {
            blurbg.setVisibility(View.GONE);
        });

    }
    private void irritable(){
        Button btncontinue,knowmore;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(AngryLevel1.this);
        dialog.setContentView(R.layout.custom_dialog_layout_for_level2);

        btncontinue = dialog.findViewById(R.id.btncontinue);
        knowmore = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);

        blurbg.setVisibility(View.VISIBLE);
        title.setText("IRRITABLE");
        btncontinue.setText("continue with IRRITABLE");
        knowmore.setText("know more");

        title.setBackgroundColor(getResources().getColor(R.color.angry));
        firstbody.setText("Irritation is a feeling of annoyance, especially when something is happening that you cannot easily stop or control");
        secbody.setText(R.string.irritable);
        knowmore.setOnClickListener(view -> {
            Intent intent = new Intent(AngryLevel1.this, Rage.class);
            startActivity(intent);
        });
        btncontinue.setOnClickListener(view -> {

            SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling","irritable");
            ed.commit();

            Intent intent = new Intent(AngryLevel1.this, BeforeSolution.class);
            startActivity(intent);
        });
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> {
            blurbg.setVisibility(View.GONE);
        });
    }
    private void envy(){
        Button btncontinue,knowmore;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(AngryLevel1.this);
        dialog.setContentView(R.layout.custom_dialog_layout_for_level2);

        btncontinue = dialog.findViewById(R.id.btncontinue);
        knowmore = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);
        blurbg.setVisibility(View.VISIBLE);
        title.setText("ENVY");
        btncontinue.setText("continue with Envy");
        knowmore.setText("know more");
        title.setBackgroundColor(getResources().getColor(R.color.angry));
        firstbody.setText("To wish that you had a quality or possession that another person has");
        secbody.setText(R.string.envy);
        knowmore.setOnClickListener(view -> {
            Intent intent = new Intent(AngryLevel1.this, Exasperated.class);
            startActivity(intent);
        });
        btncontinue.setOnClickListener(view -> {
            SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling","envy");
            ed.commit();
            Intent intent = new Intent(AngryLevel1.this, BeforeSolution.class);
            startActivity(intent);
        });
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> {
            blurbg.setVisibility(View.GONE);
        });
    }
    private void disgust(){
        Button btncontinue,knowmore;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(AngryLevel1.this);
        dialog.setContentView(R.layout.custom_dialog_layout_for_level2);

        btncontinue = dialog.findViewById(R.id.btncontinue);
        knowmore = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);
        blurbg.setVisibility(View.VISIBLE);
        title.setText("Disgust");
        title.setBackgroundColor(getResources().getColor(R.color.angry));
        btncontinue.setText("Continue with Disgust");
        knowmore.setText("know more");
        firstbody.setText("The feeling of intense displeasure or revulsion in response to an offensive or revolting object, person or behavior");
        secbody.setText(R.string.disgust);
        knowmore.setOnClickListener(view -> {
            Intent intent = new Intent(AngryLevel1.this, Irritable.class);
            startActivity(intent);
        });
        btncontinue.setOnClickListener(view -> {
            SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling","disgust");
            ed.commit();
            Intent intent = new Intent(AngryLevel1.this, BeforeSolution.class);
            startActivity(intent);
        });
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> {
            blurbg.setVisibility(View.GONE);
        });
    }
}