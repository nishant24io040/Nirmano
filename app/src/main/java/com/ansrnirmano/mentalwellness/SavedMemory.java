package com.ansrnirmano.mentalwellness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SavedMemory extends AppCompatActivity {
    Bundle bundle;
    TextView title,body,date,back;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_memory);
        bundle = getIntent().getExtras();
        title=findViewById(R.id.title1);
        date=findViewById(R.id.date2);
        body=findViewById(R.id.discr);
        imageView = findViewById(R.id.imgselect);
        back = findViewById(R.id.back);
        title.setText(bundle.getString("title"));
        body.setText(bundle.getString("body"));
        date.setText(bundle.getString("date"));
        String s = bundle.getString("image");
        if (s.equals("joy")){
            Glide.with(this).load(R.drawable.joyfulbg2).into(imageView);
        }
        else if(s.equals("loved")){
            Glide.with(this).load(R.drawable.lovedbg2).into(imageView);
        } else if (s.equals("surprised")){
            Glide.with(this).load(R.drawable.surprisedbg1).into(imageView);
        }
        else {
            Glide.with(this).load(bundle.getString("image")).into(imageView);
        }
        back.setOnClickListener(view -> {
            onBackPressed();
        });

    }
}