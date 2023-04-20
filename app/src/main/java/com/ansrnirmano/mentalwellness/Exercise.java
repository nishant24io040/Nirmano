package com.ansrnirmano.mentalwellness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.core.view.WindowCompat;

import com.ansrnirmano.Globals;

public class Exercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));

        //To Update The Branch
    }

}