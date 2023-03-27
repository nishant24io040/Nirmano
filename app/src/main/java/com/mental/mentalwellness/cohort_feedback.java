package com.mental.mentalwellness;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.WindowCompat;

import com.mental.Globals;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class cohort_feedback extends AppCompatActivity {
    ImageButton thumbsUp, thumbsDown ;
    TextView skip;
    RatingBar topic,instructor;
    EditText suggestion;
    ExtendedFloatingActionButton submitBtn;
    String starRate , starRate1;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    String strThum;
    String heading;
    TextView profName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cohort_feedback2);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));

        thumbsDown = findViewById(R.id.thumbs_down);
        thumbsUp = findViewById(R.id.thumbs_up);
        skip = findViewById(R.id.skip);
        topic = findViewById(R.id.rating_topic);
        instructor = findViewById(R.id.rating_instructor);
        suggestion = findViewById(R.id.suggestion_input);
        submitBtn = findViewById(R.id.submit_button);
        profName = findViewById(R.id.prof_name);
        String prof = getIntent().getStringExtra("prof");
        profName.setText(prof);
        heading = getIntent().getStringExtra("heading");

        thumbsUp.setSelected(false);
        thumbsDown.setSelected(false);

        thumbsDown.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (thumbsDown.isSelected()){
                    thumbsDown.setSelected(false);
                    thumbsDown.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.fui_transparent)));

                }
                else if (thumbsUp.isSelected()){
                    thumbsUp.setSelected(false);
                    thumbsDown.setSelected(true);
                    thumbsDown.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.ratingthums)));
                    thumbsUp.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.fui_transparent)));
                    strThum ="DOWN";
                }
                else {
                    thumbsDown.setSelected(true);
                    thumbsDown.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.ratingthums)));
                    strThum ="DOWN";
                }
            }
        });
        thumbsUp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (thumbsUp.isSelected()){
                    thumbsUp.setSelected(false);
                    thumbsUp.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.fui_transparent)));
                }
                else if (thumbsDown.isSelected()){
                    thumbsDown.setSelected(false);
                    thumbsUp.setSelected(true);
                    thumbsUp.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.ratingthums)));
                    thumbsDown.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.fui_transparent)));
                    strThum ="UP";
                }
                else {
                    thumbsUp.setSelected(true);
                    thumbsUp.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.ratingthums)));
                    strThum ="UP";
                }
            }
        });

        instructor.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if (v==1){
                    starRate = String.valueOf(v);
                }
                else if(v==2){
                    starRate = String.valueOf(v);

                }
                else if(v==3){
                    starRate = String.valueOf(v);

                }
                else if(v==4){
                    starRate = String.valueOf(v);

                }
                else if(v==5){
                    starRate = String.valueOf(v);

                }
            }
        });

        topic.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if (v==1){
                    starRate1 = String.valueOf(v);
                }
                else if(v==2){
                    starRate1 = String.valueOf(v);

                }
                else if(v==3){
                    starRate1 = String.valueOf(v);

                }
                else if(v==4){
                    starRate1 = String.valueOf(v);

                }
                else if(v==5){
                    starRate1 = String.valueOf(v);

                }
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(cohort_feedback.this,HomePage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (thumbsUp.isSelected()||thumbsDown.isSelected()||!starRate.isEmpty()||!starRate1.isEmpty()){
                storeRating(starRate,suggestion.getText().toString(),starRate1,strThum,heading,prof);
            }}
        });



    }
    public void storeRating(String starRate , String otherSuggestion , String starRate1,String thumsUP,String heading,String prof){
        this.mAuth = FirebaseAuth.getInstance();
        this.db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userUid = user.getUid();
        UUID uuid = UUID.randomUUID();
        Map<String,Object> ratingStore = new HashMap<>();
        ratingStore.put("Thumb",thumsUP);
        ratingStore.put("Instructor Rate",starRate);
        ratingStore.put("otherSuggestion" , otherSuggestion);
        ratingStore.put("Topic Rate" ,starRate1);
        ratingStore.put("Heading", heading);
        ratingStore.put("prof",prof);
        db.collection("CohortStoreFeedback").document(userUid + uuid).set(ratingStore).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(cohort_feedback.this , HomePage.class));
                Toast.makeText(cohort_feedback.this, "Thanks For Your Valuable Feedback It has Been Stored.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(cohort_feedback.this, "Some ERRor :- " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(cohort_feedback.this,HomePage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}