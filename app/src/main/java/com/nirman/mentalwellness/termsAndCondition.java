package com.nirman.mentalwellness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class termsAndCondition extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_condition);
        text = findViewById(R.id.text);
//        Spanned sp = Html.fromHtml( getString(R.string.terms_and_condition));
//        text.setText(sp);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        boolean bool = getIntent().getBooleanExtra("bool",true);
        if (bool){
            Intent i = new Intent(termsAndCondition.this,WelcomePage.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
        else{
            Intent i = new Intent(termsAndCondition.this,HomePage.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("uid","a");
            startActivity(i);
        }


    }
}