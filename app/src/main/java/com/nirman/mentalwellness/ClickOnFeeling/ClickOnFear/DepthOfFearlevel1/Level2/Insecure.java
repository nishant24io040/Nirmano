package com.nirman.mentalwellness.ClickOnFeeling.ClickOnFear.DepthOfFearlevel1.Level2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nirman.mentalwellness.ClickOnFeeling.ClickOnFear.DepthOfFearlevel1.Level2.Insecurefrag.Incomplete;
import com.nirman.mentalwellness.ClickOnFeeling.ClickOnFear.DepthOfFearlevel1.Level2.Insecurefrag.Inferior;
import com.nirman.mentalwellness.ClickOnFeeling.CommonForAllFeelings.LastActivity;
import com.nirman.mentalwellness.R;


public class Insecure extends AppCompatActivity {
    Button btnnext,goBack;
    TextView sorrow,depressed;
    int count=1;
    String s="Incomplete";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insecure);
        btnnext = findViewById(R.id.btnnext2);
        goBack = findViewById(R.id.goback2);
        sorrow = findViewById(R.id.sorrow);
        depressed = findViewById(R.id.depressed);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fram,new Incomplete());
        fragmentManager.popBackStack();
        fragmentTransaction.commit();
        depressed.setOnClickListener(view -> {
            replaceFragment(new Inferior());
            depressed.setBackground(getResources().getDrawable(R.drawable.level2_frag_btn_bg));
            sorrow.setBackground(getResources().getDrawable(R.drawable.level2_frag_btn_inactive_bg));
            count++;
            s="Inferior";
        });
        sorrow.setOnClickListener(view -> {
            replaceFragment(new Incomplete());
            depressed.setBackground(getResources().getDrawable(R.drawable.level2_frag_btn_inactive_bg));
            sorrow.setBackground(getResources().getDrawable(R.drawable.level2_frag_btn_bg));
            count--;
            s="Incomplete";
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