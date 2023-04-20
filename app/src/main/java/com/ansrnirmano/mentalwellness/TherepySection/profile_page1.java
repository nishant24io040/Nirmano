package com.ansrnirmano.mentalwellness.TherepySection;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.ansrnirmano.mentalwellness.R;

public class profile_page1 extends AppCompatActivity {

    TextView title,about,lang,offer,expectIn,expirence,qualification,rate;
    Button btn_submit;
    ImageView imageView;
    Bundle bundle;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page1);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        bundle = getIntent().getExtras();
        title = findViewById(R.id.title);
        about = findViewById(R.id.about);
        lang = findViewById(R.id.lang);
        offer = findViewById(R.id.offer);
        expectIn = findViewById(R.id.expect);
        expirence = findViewById(R.id.experience);
        qualification = findViewById(R.id.qualification);
        rate = findViewById(R.id.rate);
        imageView = findViewById(R.id.img);
        btn_submit = findViewById(R.id.button_submit);

        btn_submit.setOnClickListener(v -> {
            Intent intent = new Intent(this, BookATharepist.class);
            startActivity(intent);
        });
        ModalForTharepist user = new ModalForTharepist(bundle.getString("name"),bundle.getString("title")
                ,bundle.getString("image"),bundle.getString("about"),bundle.getString("expect"),
                bundle.getString("experience"),bundle.getString("languages"),bundle.getString("offerTherapy"),
                bundle.getString("price"),bundle.getString("qualification"));
        title.setText(user.getName());
        about.setText(user.getAbout());
        lang.setText(user.getLanguages());
        offer.setText(user.getOfferTherapy());
        expectIn.setText(user.getExpect());
        expirence.setText(user.getExperience());
        qualification.setText(user.getQualification());
        rate.setText("Rs."+user.getPrice()+"/");


    }
}