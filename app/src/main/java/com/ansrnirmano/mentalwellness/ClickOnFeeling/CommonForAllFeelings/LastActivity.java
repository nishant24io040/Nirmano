package com.ansrnirmano.mentalwellness.ClickOnFeeling.CommonForAllFeelings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ansrnirmano.mentalwellness.R;
import com.ansrnirmano.mentalwellness.Tharapytemp;

public class LastActivity extends AppCompatActivity {
    TextView contactpro,selfhelp,recommend;
    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        contactpro = findViewById(R.id.contact);
        selfhelp = findViewById(R.id.selfhelp);
        recommend =findViewById(R.id.warning);
        selfhelp.setOnClickListener(view -> {
            if (i==1){
                recommend.setVisibility(View.VISIBLE);
                i++;
            }
            else {
                startActivity(new Intent(this,ChoosWhatYouEnjoy.class));
            }
        });
        contactpro.setOnClickListener(view -> {
            Intent i = new Intent(this, Tharapytemp.class);
            startActivity(i);
        });
    }
}