package com.nirman.mentalwellness;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.core.view.WindowCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.nirman.Globals;


public class MainActivity extends AppCompatActivity {
    public int counter;
    Bundle bundle;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bundle = getIntent().getExtras();
        try {
            if (bundle.get("thisdata").toString().equals("yes")){
                SharedPreferences sd = this.getSharedPreferences("noti", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = sd.edit();
                ed.putString("yes","callme");
                ed.commit();
            }
        }catch (Exception e){
            Log.e("expt",e.toString());
        }

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));

        new CountDownTimer(3000, 1000){
            public void onTick(long millisUntilFinished){
                counter++;
            }
            public  void onFinish(){
                if (mAuth.getCurrentUser()!=null){
                    Intent i = new Intent(MainActivity.this,HomePage.class);
                    i.putExtra("uid","a");
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }else {
                    Intent i = new Intent(MainActivity.this, WelcomePage.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
            }
        }.start();



}}