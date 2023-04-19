package com.nirman.mentalwellness.ClickOnFeeling.ClickOnAngry.DepthOfAngryLavel1.Level2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nirman.mentalwellness.ClickOnFeeling.ClickOnAngry.DepthOfAngryLavel1.Level2.Ragefrag.AGGRAVATED;
import com.nirman.mentalwellness.ClickOnFeeling.ClickOnAngry.DepthOfAngryLavel1.Level2.Ragefrag.Annoyed;
import com.nirman.mentalwellness.ClickOnFeeling.CommonForAllFeelings.LastActivity;
import com.nirman.mentalwellness.R;


public class Rage extends AppCompatActivity {
    Button btnnext,goBack;
    TextView sorrow,depressed;
    int count=1;
    String s ="AGGRAVATED" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rage);
        btnnext = findViewById(R.id.btnnext2);
        goBack = findViewById(R.id.goback2);
        sorrow = findViewById(R.id.sorrow);
        depressed = findViewById(R.id.depressed);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fram,new AGGRAVATED());
        fragmentManager.popBackStack();
        fragmentTransaction.commit();
        depressed.setOnClickListener(view -> {
            replaceFragment(new Annoyed());
            depressed.setBackground(getResources().getDrawable(R.drawable.level2_frag_btn_bg));
            sorrow.setBackground(getResources().getDrawable(R.drawable.level2_frag_btn_inactive_bg));
            count++;
            s="Annoyed";
        });
        sorrow.setOnClickListener(view -> {
            replaceFragment(new AGGRAVATED());
            depressed.setBackground(getResources().getDrawable(R.drawable.level2_frag_btn_inactive_bg));
            sorrow.setBackground(getResources().getDrawable(R.drawable.level2_frag_btn_bg));
            count--;
            s="AGGRAVATED";
        });
        btnnext.setOnClickListener(view -> {
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