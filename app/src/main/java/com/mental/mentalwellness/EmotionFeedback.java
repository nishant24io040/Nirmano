package com.mental.mentalwellness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.chooser.ChooserTarget;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.mental.mentalwellness.ClickOnFeeling.CommonForAllFeelings.ChoosWhatYouEnjoy;

public class EmotionFeedback extends AppCompatActivity {
    ImageView likeinactive,dislikeinactive;
    ExtendedFloatingActionButton btnSubmit;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth mauth = FirebaseAuth.getInstance();
    String like;
    int b = 0,c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_feedback);
        likeinactive = findViewById(R.id.like1);
        dislikeinactive = findViewById(R.id.dislike1);
        likeinactive.setOnClickListener(view -> {
            if (b==0) {
                Glide.with(this).load(R.drawable.likeactive).into(likeinactive);
                b++;
                c=1;
            }
//            likeinactive.setBackground(getResources().getDrawable(R.drawable.likeactive));
        });

        dislikeinactive.setOnClickListener(view -> {
            if (b==0) {
                Glide.with(this).load(R.drawable.dislikeactive).into(dislikeinactive);
                b++;
            }
//            dislikeinactive.setBackground(getResources().getDrawable(R.drawable.dislikeactive));
        });

        btnSubmit = findViewById(R.id.btnsub);
        btnSubmit.setOnClickListener(view -> {
            if (c==1){
                like = "liked";
                database.getReference("SolutionFeedback").child(mauth.getUid()).child("feedback").setValue(like);
                Intent intent = new Intent(this, DislikedFeedback.class);
                startActivity(intent);
            }
            else{
                like = "dislike";
                database.getReference("SolutionFeedback").child(mauth.getUid()).child("feedback").setValue(like);
                Intent intent = new Intent(this, LIkedFeedback.class);
                startActivity(intent);
            }

        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, HomePage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}