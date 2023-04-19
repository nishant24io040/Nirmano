package com.nirman.mentalwellness.ClickOnFeeling.ClickOnFear.DepthOfFearlevel1;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.nirman.mentalwellness.ClickOnFeeling.ClickOnFear.DepthOfFearlevel1.Level2.Horror;
import com.nirman.mentalwellness.ClickOnFeeling.ClickOnFear.DepthOfFearlevel1.Level2.Insecure;
import com.nirman.mentalwellness.ClickOnFeeling.ClickOnFear.DepthOfFearlevel1.Level2.Nervous;
import com.nirman.mentalwellness.ClickOnFeeling.ClickOnFear.DepthOfFearlevel1.Level2.Scared;
import com.nirman.mentalwellness.ClickOnFeeling.ClickOnFear.DepthOfFearlevel1.Level2.Terror;
import com.nirman.mentalwellness.ClickOnFeeling.CommonForAllFeelings.BeforeSolution;
import com.nirman.mentalwellness.R;

public class FearLevel1 extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5;
    ImageView blurbg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fear_level1);
        btn1 = findViewById(R.id.horror);
        btn2 = findViewById(R.id.nervous);
        btn3 = findViewById(R.id.insecure);
        btn4 = findViewById(R.id.terror);
        btn5 = findViewById(R.id.scared);
        blurbg = findViewById(R.id.blurebg2);

        btn1.setOnClickListener(view -> {
            horror();
        });
        btn2.setOnClickListener(view -> {
            nervous();
        });
        btn3.setOnClickListener(view -> {
            insecure();
        });
        btn4.setOnClickListener(view -> {
            terror();
        });
        btn5.setOnClickListener(view -> {
            scared();
        });
    }
    private void horror(){
        Button btncontinue,knowmore;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(FearLevel1.this);
        dialog.setContentView(R.layout.custom_dialog_layout_for_level2);

        btncontinue = dialog.findViewById(R.id.btncontinue);
        knowmore = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        title.setBackgroundColor(getResources().getColor(R.color.fear));
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);
        blurbg.setVisibility(View.VISIBLE);
        title.setText("Horror");
        btncontinue.setText("continue with horror");
        knowmore.setText("know more");
        firstbody.setText("Horror is an intense sensation of shock or terror or the terrifying or shocking aspects of something");
        secbody.setText(R.string.Horror);
        knowmore.setOnClickListener(view -> {
            Intent intent = new Intent(FearLevel1.this, Horror.class);
            startActivity(intent);
        });
        btncontinue.setOnClickListener(view -> {

            SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling","Horror");
            ed.commit();

            Intent intent = new Intent(FearLevel1.this, BeforeSolution.class);
            startActivity(intent);
        });
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> {
            blurbg.setVisibility(View.GONE);
        });
    }
    private void nervous(){
        Button btncontinue,knowmore;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(FearLevel1.this);
        dialog.setContentView(R.layout.custom_dialog_layout_for_level2);

        btncontinue = dialog.findViewById(R.id.btncontinue);
        knowmore = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);
        blurbg.setVisibility(View.VISIBLE);
        title.setBackgroundColor(getResources().getColor(R.color.fear));

        title.setText("NERVOUS");
        btncontinue.setText("continue with nervous");
        knowmore.setText("know more");
        firstbody.setText("Nervousness is a state of restless tension.");
        secbody.setText(R.string.Nervous);
        knowmore.setOnClickListener(view -> {
            Intent intent = new Intent(FearLevel1.this, Nervous.class);
            startActivity(intent);
        });
        btncontinue.setOnClickListener(view -> {
            SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling","NERVOUS");
            ed.commit();
            Intent intent = new Intent(FearLevel1.this, BeforeSolution.class);
            startActivity(intent);
        });
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> {
            blurbg.setVisibility(View.GONE);
        });

    }
    private void insecure(){
        Button btncontinue,knowmore;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(FearLevel1.this);
        dialog.setContentView(R.layout.custom_dialog_layout_for_level2);

        btncontinue = dialog.findViewById(R.id.btncontinue);
        knowmore = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);
        title.setBackgroundColor(getResources().getColor(R.color.fear));

        knowmore.setOnClickListener(view -> {
            Intent intent = new Intent(FearLevel1.this, Insecure.class);
            startActivity(intent);
        });
        blurbg.setVisibility(View.VISIBLE);
        title.setText("INSECURE");
        btncontinue.setText("continue with insecure");
        knowmore.setText("know more");
        firstbody.setText("When you are unsure of your own abilities or worried if other people will like them.");
        secbody.setText(R.string.insecure);
        btncontinue.setOnClickListener(view -> {
            SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling","INSECURE");
            ed.commit();
            Intent intent = new Intent(FearLevel1.this, BeforeSolution.class);
            startActivity(intent);
        });
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> {
            blurbg.setVisibility(View.GONE);
        });
    }
    private void terror(){
        Button btncontinue,knowmore;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(FearLevel1.this);
        dialog.setContentView(R.layout.custom_dialog_layout_for_level2);

        btncontinue = dialog.findViewById(R.id.btncontinue);
        knowmore = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);
        blurbg.setVisibility(View.VISIBLE);
        title.setBackgroundColor(getResources().getColor(R.color.fear));

        title.setText("Terror");
        btncontinue.setText("continue with Terror");
        knowmore.setText("know more");
        firstbody.setText("Intense and overwhelming fear");
        secbody.setText(R.string.terror);
        knowmore.setOnClickListener(view -> {
            Intent intent = new Intent(FearLevel1.this, Terror.class);
            startActivity(intent);
        });
        btncontinue.setOnClickListener(view -> {
            SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling","Terror");
            ed.commit();
            Intent intent = new Intent(FearLevel1.this, BeforeSolution.class);
            startActivity(intent);
        });
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> {
            blurbg.setVisibility(View.GONE);
        });
    }
    private void scared(){
        Button btncontinue,knowmore;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(FearLevel1.this);
        dialog.setContentView(R.layout.custom_dialog_layout_for_level2);


        btncontinue = dialog.findViewById(R.id.btncontinue);
        knowmore = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);
        blurbg.setVisibility(View.VISIBLE);
        title.setText("Scared");
        title.setBackgroundColor(getResources().getColor(R.color.fear));
        btncontinue.setText("Continue with Scared");
        knowmore.setText("know more");
        firstbody.setText("Terrified or concerned");
        secbody.setText(R.string.scared);
        knowmore.setOnClickListener(view -> {
            Intent intent = new Intent(FearLevel1.this, Scared.class);
            startActivity(intent);
        });
        btncontinue.setOnClickListener(view -> {

            SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling","Scared");
            ed.commit();

            Intent intent = new Intent(FearLevel1.this, BeforeSolution.class);
            startActivity(intent);
        });
        dialog.show();
        dialog.setOnDismissListener(dialogInterface -> {
            blurbg.setVisibility(View.GONE);
        });
    }
}

