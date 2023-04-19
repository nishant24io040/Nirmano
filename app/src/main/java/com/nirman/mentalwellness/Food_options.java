package com.nirman.mentalwellness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.core.view.WindowCompat;

import com.nirman.Globals;

public class Food_options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_options);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));

    }
}