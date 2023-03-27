package com.mental.mentalwellness.ClickOnFeeling.ClickOnFear.DepthOfFearlevel1.Level2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mental.mentalwellness.ClickOnFeeling.ClickOnFear.DepthOfFearlevel1.Level2.Nervousfrag.Anxious;
import com.mental.mentalwellness.ClickOnFeeling.ClickOnFear.DepthOfFearlevel1.Level2.Nervousfrag.Worried;
import com.mental.mentalwellness.ClickOnFeeling.ClickOnSad.DepthOfEmotionLevel1.Level2.Upsetfrag.Depressed;
import com.mental.mentalwellness.ClickOnFeeling.ClickOnSad.DepthOfEmotionLevel1.Level2.Upsetfrag.Sorrow;
import com.mental.mentalwellness.ClickOnFeeling.CommonForAllFeelings.LastActivity;
import com.mental.mentalwellness.R;


public class Nervous extends AppCompatActivity {
    Button btnnext,goBack;
    TextView sorrow,depressed;
    int count=1;
    String s="Anxious";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nervous);
        btnnext = findViewById(R.id.btnnext2);
        goBack = findViewById(R.id.goback2);
        sorrow = findViewById(R.id.sorrow);
        depressed = findViewById(R.id.depressed);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fram,new Anxious());
        fragmentManager.popBackStack();
        fragmentTransaction.commit();
        depressed.setOnClickListener(view -> {
            replaceFragment(new Worried());
            depressed.setBackground(getResources().getDrawable(R.drawable.level2_frag_btn_bg));
            sorrow.setBackground(getResources().getDrawable(R.drawable.level2_frag_btn_inactive_bg));
            count++;
            s="Worried";
        });
        sorrow.setOnClickListener(view -> {
            replaceFragment(new Anxious());
            depressed.setBackground(getResources().getDrawable(R.drawable.level2_frag_btn_inactive_bg));
            sorrow.setBackground(getResources().getDrawable(R.drawable.level2_frag_btn_bg));
            count--;
            s="Anxious";
        });
        btnnext.setOnClickListener(view -> {
            SharedPreferences depth = this.getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling",s);
            ed.commit();
            Intent intent = new Intent(this, LastActivity.class);
            startActivity(intent);
        });
        goBack.setOnClickListener(view -> {
            onBackPressed();
        });

    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fram,fragment);
        fragmentManager.popBackStack();
        fragmentTransaction.commit();
    }
}