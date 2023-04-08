package com.mental.mentalwellness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mental.Globals;

public class YoutubeVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_video);
//        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));

    }
}