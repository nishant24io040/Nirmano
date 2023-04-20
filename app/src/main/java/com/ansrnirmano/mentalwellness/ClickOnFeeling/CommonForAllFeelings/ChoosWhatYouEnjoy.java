package com.ansrnirmano.mentalwellness.ClickOnFeeling.CommonForAllFeelings;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ansrnirmano.mentalwellness.Journal;
import com.ansrnirmano.mentalwellness.R;
import com.ansrnirmano.mentalwellness.Releaf;
import com.ansrnirmano.mentalwellness.breathInBreathOut;
import com.ansrnirmano.mentalwellness.letter;
import com.ansrnirmano.mentalwellness.recordActivity;

public class ChoosWhatYouEnjoy extends AppCompatActivity {

    Button btnnext;
    ConstraintLayout read,mindful,write,speak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choos_what_you_enjoy);
        btnnext = findViewById(R.id.next);
        read = findViewById(R.id.mind);
        mindful = findViewById(R.id.read);
        write = findViewById(R.id.write);
        speak = findViewById(R.id.speak);
        write.setOnClickListener(view -> {
            Intent intent = new Intent(this, Releaf.class);
            startActivity(intent);
        });

        btnnext.setOnClickListener(view -> {

        });
        read.setOnClickListener(view -> {
            read();
        });
        speak.setOnClickListener(view -> {
            Intent intent = new Intent(this, recordActivity.class);
            startActivity(intent);
        });
        mindful.setOnClickListener(view -> {
            Intent intent = new Intent(this, breathInBreathOut.class);
            startActivity(intent);
        });
        write.setOnClickListener(view -> {
            Intent intent = new Intent(this,Releaf.class);
            startActivity(intent);
        });


    }
    private void read(){
        TextView moti,revisit;
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.last_dialog);
        moti = dialog.findViewById(R.id.moti);
        revisit = dialog.findViewById(R.id.revisit);
        moti.setOnClickListener(view -> {
            Intent intent = new Intent(this, letter.class);
            startActivity(intent);
        });
        revisit.setOnClickListener(view -> {
            Intent intent = new Intent(this, Journal.class);
            startActivity(intent);
        });

        dialog.show();
    }
}