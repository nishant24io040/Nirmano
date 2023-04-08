package com.mental.mentalwellness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mental.Globals;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class feedback extends AppCompatActivity {
    EditText feedbackBox;
    RatingBar ratingBar;
    ExtendedFloatingActionButton feedbackSubmitBtn,rating;
    TextView feedbackReview,feedbackHeading;
    String starRate;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    MaterialCardView relief,pricing,services,user_exp,group,emotion,feed,community,cwe,emotion1,therapy,mental;
    TextView relief_t,pricing_t,services_t,user_exp_t,group_t,emotion_t,feed_t,community_t,cwe_t,emotion1_t,therapy_t,mental_t;
    ConstraintLayout lowRating, highRating;
    List<String> storeFeedBtns;
    ImageButton close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
//        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));

        storeFeedBtns = new ArrayList<>();
        relief_t = findViewById(R.id.relief_t);
        pricing_t = findViewById(R.id.pricing_t);
        services_t= findViewById(R.id.services_t);
        user_exp_t= findViewById(R.id.user_exp_t);
        group_t= findViewById(R.id.group_t);
        emotion_t = findViewById(R.id.emotion_t);
        feed_t= findViewById(R.id.feed_t);
        community_t = findViewById(R.id.community_t);
        cwe_t= findViewById(R.id.cwe_t);
        emotion1_t = findViewById(R.id.emotion1_t);
        therapy_t= findViewById(R.id.therapy_t);
        mental_t= findViewById(R.id.mental_t);
        relief = findViewById(R.id.relief);
        pricing = findViewById(R.id.pricing);
        services= findViewById(R.id.services);
        user_exp= findViewById(R.id.user_exp);
        group= findViewById(R.id.group);
        emotion = findViewById(R.id.emotion);
        feed= findViewById(R.id.feed);
        community = findViewById(R.id.community);
        cwe= findViewById(R.id.cwe);
        emotion1 = findViewById(R.id.emotion1);
        therapy= findViewById(R.id.therapy);
        mental= findViewById(R.id.mental);
        feedbackBox = findViewById(R.id.suggestion_input);
        ratingBar = findViewById(R.id.ratingBar);
        feedbackSubmitBtn = findViewById(R.id.submit_button);
        feedbackReview = findViewById(R.id.rating_title);
        lowRating = findViewById(R.id.low_rating_options);
        highRating = findViewById(R.id.high_rating_options);
        feedbackHeading = findViewById(R.id.heading);

        TextView playStore = findViewById(R.id.play_store);
        TextView plstro = findViewById(R.id.plstro);
        plstro.setOnClickListener(view -> {
            Intent viewIntent =
                    new Intent("android.intent.action.VIEW",
                            Uri.parse("https://play.google.com/store/apps/details?id=com.mental.mentalwellness"));
            startActivity(viewIntent);
        });
        playStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        close = findViewById(R.id.close_button);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(feedback.this,HomePage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("uid","a");
                startActivity(i);
            }
        });




        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                playStore.setVisibility(View.GONE);
                plstro.setVisibility(View.GONE);
                feedbackSubmitBtn.setVisibility(View.VISIBLE);
                feedbackReview.setVisibility(View.VISIBLE);
                feedbackBox.setVisibility(View.VISIBLE);
                if (v==1){
                    feedbackReview.setText("Bad!");
                    starRate = String.valueOf(v);
                    lowRating.setVisibility(View.VISIBLE);
                    highRating.setVisibility(View.GONE);
                    feedbackHeading.setVisibility(View.VISIBLE);
                    playStore.setVisibility(View.GONE);


                }
                else if(v==0.5){
                    feedbackReview.setText("Not Good!");
                    starRate = String.valueOf(v);
                    lowRating.setVisibility(View.VISIBLE);
                    highRating.setVisibility(View.GONE);
                    feedbackHeading.setVisibility(View.VISIBLE);
                    playStore.setVisibility(View.GONE);

                }
                else if(v==1.5){
                    feedbackReview.setText("Not Good!");
                    starRate = String.valueOf(v);
                    lowRating.setVisibility(View.VISIBLE);
                    highRating.setVisibility(View.GONE);
                    feedbackHeading.setVisibility(View.VISIBLE);
                    playStore.setVisibility(View.GONE);

                }
                else if(v==2){
                    feedbackReview.setText("Not Good!");
                    starRate = String.valueOf(v);
                    lowRating.setVisibility(View.VISIBLE);
                    highRating.setVisibility(View.GONE);
                    feedbackHeading.setVisibility(View.VISIBLE);
                    playStore.setVisibility(View.GONE);

                }
                else if(v==2.5){
                    feedbackReview.setText("Not Good!");
                    starRate = String.valueOf(v);
                    lowRating.setVisibility(View.VISIBLE);
                    highRating.setVisibility(View.GONE);
                    feedbackHeading.setVisibility(View.VISIBLE);
                    playStore.setVisibility(View.GONE);

                }
                else if(v==3){
                    feedbackReview.setText("Average!");
                    starRate = String.valueOf(v);
                    lowRating.setVisibility(View.VISIBLE);
                    highRating.setVisibility(View.GONE);
                    feedbackHeading.setVisibility(View.VISIBLE);
                    feedbackHeading.setText("Which features we can improve?");
                    playStore.setVisibility(View.GONE);

                }
                else if(v==3.5){
                    feedbackReview.setText("Good!");
                    starRate = String.valueOf(v);
                    highRating.setVisibility(View.VISIBLE);
                    lowRating.setVisibility(View.GONE);
                    feedbackHeading.setVisibility(View.VISIBLE);
                    feedbackHeading.setText("What features do you want next?");
                    playStore.setVisibility(View.GONE);


                }
                else if(v==4){
                    feedbackReview.setText("Good!");
                    starRate = String.valueOf(v);
                    highRating.setVisibility(View.VISIBLE);
                    lowRating.setVisibility(View.GONE);
                    feedbackHeading.setVisibility(View.VISIBLE);
                    feedbackHeading.setText("What features do you want next?");
                    playStore.setVisibility(View.GONE);


                } else if(v==4.5){
                    feedbackReview.setText("Good!");
                    starRate = String.valueOf(v);
                    highRating.setVisibility(View.VISIBLE);
                    lowRating.setVisibility(View.GONE);
                    feedbackHeading.setVisibility(View.VISIBLE);
                    feedbackHeading.setText("What features do you want next?");
                    playStore.setVisibility(View.GONE);


                }
                else if(v==5){
                    feedbackReview.setText("Excellent!");
                    starRate = String.valueOf(v);
                    highRating.setVisibility(View.VISIBLE);
                    lowRating.setVisibility(View.GONE);
                    feedbackHeading.setVisibility(View.VISIBLE);
                    feedbackHeading.setText("What features do you want next?");
                }
            }
        });
        feedbackSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeRating(starRate , feedbackBox.getText().toString() , storeFeedBtns);
                Intent i = new Intent(feedback.this,Journal.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        //Rating sugggestion Buttons
        relief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (relief.isSelected()){
                    relief.setSelected(false);
                    storeFeedBtns.remove(relief_t.getText().toString());
                    relief_t.setTextColor(getResources().getColor(R.color.fill));
                    relief.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                }
                else{
                    relief.setSelected(true);
                    storeFeedBtns.add((String) relief_t.getText());
                    relief_t.setTextColor(getResources().getColor(R.color.white));
                    relief.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.fill)));
                }
            }
        });
        pricing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pricing.isSelected()){
                    pricing.setSelected(false);
                    storeFeedBtns.remove(pricing_t.getText().toString());
                    pricing_t.setTextColor(getResources().getColor(R.color.fill));
                    pricing.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                }
                else{
                    pricing.setSelected(true);
                    storeFeedBtns.add((String) pricing_t.getText());
                    pricing_t.setTextColor(getResources().getColor(R.color.white));
                    pricing.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.fill)));
                }}
        });
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (services.isSelected()){
                    services.setSelected(false);
                    storeFeedBtns.remove(services_t.getText().toString());
                    services_t.setTextColor(getResources().getColor(R.color.fill));
                    services.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                }
                else{
                    services.setSelected(true);
                    storeFeedBtns.add((String) services_t.getText());
                    services_t.setTextColor(getResources().getColor(R.color.white));
                    services.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.fill)));
                }
            }
        });
        user_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user_exp.isSelected()){
                    user_exp.setSelected(false);
                    storeFeedBtns.remove(user_exp_t.getText().toString());
                    user_exp_t.setTextColor(getResources().getColor(R.color.fill));
                    user_exp.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                }
                else{
                    user_exp.setSelected(true);
                    storeFeedBtns.add((String) user_exp_t.getText());
                    user_exp_t.setTextColor(getResources().getColor(R.color.white));
                    user_exp.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.fill)));
                }


            }
        });
        group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (group.isSelected()){
                    group.setSelected(false);
                    storeFeedBtns.remove(group_t.getText().toString());
                    group_t.setTextColor(getResources().getColor(R.color.fill));
                    group.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                }
                else{
                    group.setSelected(true);
                    storeFeedBtns.add((String) group_t.getText());
                    group_t.setTextColor(getResources().getColor(R.color.white));
                    group.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.fill)));
                }

            }
        });
        emotion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emotion1.isSelected()){
                    emotion1.setSelected(false);
                    storeFeedBtns.remove(emotion1_t.getText().toString());
                    emotion1_t.setTextColor(getResources().getColor(R.color.fill));
                    emotion1.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                }
                else{
                    emotion1.setSelected(true);
                    storeFeedBtns.add((String) emotion1_t.getText());
                    emotion1_t.setTextColor(getResources().getColor(R.color.white));
                    emotion1.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.fill)));
                }

            }
        });
        emotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emotion.isSelected()){
                    emotion.setSelected(false);
                    storeFeedBtns.remove(emotion_t.getText().toString());
                    emotion_t.setTextColor(getResources().getColor(R.color.fill));
                    emotion.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                }
                else{
                    emotion.setSelected(true);
                    storeFeedBtns.add((String) emotion_t.getText());
                    emotion_t.setTextColor(getResources().getColor(R.color.white));
                    emotion.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.fill)));
                }

            }
        });
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (feed.isSelected()){
                    feed.setSelected(false);
                    storeFeedBtns.remove(feed_t.getText().toString());
                    feed_t.setTextColor(getResources().getColor(R.color.fill));
                    feed.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                }
                else{
                    feed.setSelected(true);
                    storeFeedBtns.add((String) feed_t.getText());
                    feed_t.setTextColor(getResources().getColor(R.color.white));
                    feed.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.fill)));
                }

            }
        });
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (community.isSelected()){
                    community.setSelected(false);
                    storeFeedBtns.remove(community_t.getText().toString());
                    community_t.setTextColor(getResources().getColor(R.color.fill));
                    community.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                }
                else{
                    community.setSelected(true);
                    storeFeedBtns.add((String) community_t.getText());
                    community_t.setTextColor(getResources().getColor(R.color.white));
                    community.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.fill)));
                }

            }
        });
        cwe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cwe.isSelected()){
                    cwe.setSelected(false);
                    storeFeedBtns.remove(cwe_t.getText().toString());
                    cwe_t.setTextColor(getResources().getColor(R.color.fill));
                    cwe.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                }
                else{
                    cwe.setSelected(true);
                    storeFeedBtns.add((String) cwe_t.getText());
                    cwe_t.setTextColor(getResources().getColor(R.color.white));
                    cwe.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.fill)));
                }

            }
        });
        mental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mental.isSelected()){
                    mental.setSelected(false);
                    storeFeedBtns.remove(mental_t.getText().toString());
                    mental_t.setTextColor(getResources().getColor(R.color.fill));
                    mental.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                }
                else{
                    mental.setSelected(true);
                    storeFeedBtns.add((String) mental_t.getText());
                    mental_t.setTextColor(getResources().getColor(R.color.white));
                    mental.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.fill)));
                }
            }
        });
        therapy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (therapy.isSelected()){
                    therapy.setSelected(false);
                    storeFeedBtns.remove(therapy_t.getText().toString());
                    therapy_t.setTextColor(getResources().getColor(R.color.fill));
                    therapy.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                }
                else{
                    therapy.setSelected(true);
                    storeFeedBtns.add((String) therapy_t.getText());
                    therapy_t.setTextColor(getResources().getColor(R.color.white));
                    therapy.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.fill)));
                }

            }
        });




    }
    public void storeRating(String starRate , String otherSuggestion , List<String> storeFeedBtns){
        this.mAuth = FirebaseAuth.getInstance();
        this.db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userUid = user.getUid();
        UUID uuid = UUID.randomUUID();
        Map<String,Object> ratingStore = new HashMap<>();
        ratingStore.put("stars",starRate);
        ratingStore.put("otherSuggestion" , otherSuggestion);
        ratingStore.put("selectedSuggestions" , storeFeedBtns);
        db.collection("storeFeedback").document(userUid + uuid).set(ratingStore).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(feedback.this, "Thanks For Your Valuable Feedback It has Been Stored.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(feedback.this, "Some ERRor :- " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(feedback.this,HomePage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("uid","a");
        startActivity(i);
    }

}