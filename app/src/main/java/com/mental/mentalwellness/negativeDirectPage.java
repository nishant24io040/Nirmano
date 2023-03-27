package com.mental.mentalwellness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mental.Globals;
import com.mental.adapters.NegativeAdapter;

import com.mental.models.Negative;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class negativeDirectPage extends AppCompatActivity {
    RecyclerView negativeRecycleView;
    RecyclerView.Adapter negativeAdapter;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negative_direct_page);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));

        negativeRecycleView = findViewById(R.id.negative_recycler_view);
        String emotion = getIntent().getStringExtra("name");
        List<Negative> negatives = new ArrayList<>();
        emotionFirebase(emotion,negatives);


        negativeRecycleView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), negativeRecycleView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Negative negative = negatives.get(position);
                Intent i = new Intent(negativeDirectPage.this,negative.act);
                i.putExtra("act","negative");
                i.putExtra("name",emotion);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));



    }
    public void emotionFirebase(String emotion,List<Negative> negatives){
        this.db = FirebaseFirestore.getInstance();
        db.collection("negative").document(emotion).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()){
                        Map<String, Object> sample = new HashMap<>();
                        sample = documentSnapshot.getData();
                        boolean journal = (boolean)sample.get("Journal");
                        boolean ytEmbed = (boolean)sample.get("ytEmbeed");
                        boolean relief  = (boolean)sample.get("Relief");
                        boolean breathe = (boolean) sample.get("breathe");
                        boolean lemon   = (boolean) sample.get("lemon");
                        String breatheTitle = (String) sample.get("breatheTitle");
                        String lemonTitle = (String) sample.get("lemonTitle");
                        String extraCardTitle = (String) sample.get("extraCardTitle");
                        String extraCardTitle2 = (String) sample.get("extraCard2Title");
                        String extraCardTitle3 = (String) sample.get("extraCard3Title");
                        emotionManage(emotion, negatives,journal,relief,ytEmbed,breatheTitle,lemonTitle,breathe,lemon,extraCardTitle,extraCardTitle2,extraCardTitle3);
                    }
                    else {
                        negatives.add(new Negative(1, "Journal", R.drawable.negative_journal_card, "5 mins", Journal.class,emotion));
                        negatives.add(new Negative(2, "Relief", R.drawable.negative_relief_card, "5 mins", Releaf.class,emotion));
                        negatives.add(new Negative(2, "Yt Embedded", R.drawable.negative_yt_card, "5 mins",ytEmbbed.class,emotion));
                    }
                }
                else Toast.makeText(negativeDirectPage.this, "Problem Fetching it", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(negativeDirectPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void emotionManage(String emotion,List<Negative> negatives,boolean journal , boolean relief , boolean yt,String breathe, String lemon,boolean breathe1,boolean lemon2, String card1T,String card2T, String card3T){
        negatives.clear();
        if(emotion.equals("Anger")){
            if (breathe1) {
                negatives.add(new Negative(3, breathe, R.drawable.negative_breathe_card, "5 mins", jogg.class, emotion));
            }
            if (lemon2) {
                negatives.add(new Negative(4, lemon, R.drawable.negative_lemon_card, "5mins", lemonAndCinammonJuice.class, emotion));
            }
            if (journal) {
                negatives.add(new Negative(1, "Journal", R.drawable.negative_journal_card, "5 mins", Journal.class, emotion));
            }
            if (relief) {
                negatives.add(new Negative(2, "Relief", R.drawable.negative_relief_card, "5 mins", Releaf.class, emotion));
            }
            if (yt){
                negatives.add(new Negative(5, "Yt Embedded", R.drawable.negative_yt_card, "5 mins",ytEmbbed.class,emotion));
            }
        }
        else if (emotion.equals("Stressed")){
            if (breathe1) {
                negatives.add(new Negative(3, breathe, R.drawable.negative_breathe_card, "5 mins", jogg.class, emotion));
            }
            if (lemon2) {
                negatives.add(new Negative(4, lemon, R.drawable.negative_lemon_card, "5mins", lemonAndCinammonJuice.class, emotion));
            }
            if (journal) {
                negatives.add(new Negative(1, "Journal", R.drawable.negative_journal_card, "5 mins", Journal.class, emotion));
            }
            if (relief) {
                negatives.add(new Negative(2, "Relief", R.drawable.negative_relief_card, "5 mins", Releaf.class, emotion));
            }
            if (yt){
                negatives.add(new Negative(5, "Yt Embedded", R.drawable.negative_yt_card, "5 mins",ytEmbbed.class,emotion));
            }
        }
        else if (emotion.equals("Sad")){
            if (breathe1) {
                negatives.add(new Negative(3, breathe, R.drawable.negative_breathe_card, "5 mins", jogg.class, emotion));
            }
            if (lemon2) {
                negatives.add(new Negative(4, lemon, R.drawable.negative_lemon_card, "5mins", lemonAndCinammonJuice.class, emotion));
            }
            if (journal) {
                negatives.add(new Negative(1, "Journal", R.drawable.negative_journal_card, "5 mins", Journal.class, emotion));
            }
            if (relief) {
                negatives.add(new Negative(2, "Relief", R.drawable.negative_relief_card, "5 mins", Releaf.class, emotion));
            }
            if (yt){
                negatives.add(new Negative(5, "Yt Embedded", R.drawable.negative_yt_card, "5 mins",ytEmbbed.class,emotion));
            }
        }
        else if (emotion.equals("Rage") ||emotion.equals("Grief") ||emotion.equals("Gloomy") ||emotion.equals("Trauma") ||emotion.equals("Shock") ||emotion.equals("Fear") ||emotion.equals("Panic") ||emotion.equals("Paranoid") ||emotion.equals("Regret") ||emotion.equals("Terror") ||emotion.equals("Guilt")){
            if (breathe1) {
                negatives.add(new Negative(3, breathe, R.drawable.negative_breathe_card, "5 mins", jogg.class, emotion));
            }
            if (lemon2) {
                negatives.add(new Negative(4, lemon, R.drawable.negative_lemon_card, "5mins", lemonAndCinammonJuice.class, emotion));
            }
            if (journal) {
                negatives.add(new Negative(1, "Journal", R.drawable.negative_journal_card, "5 mins", Journal.class, emotion));
            }
            if (relief) {
                negatives.add(new Negative(2, "Relief", R.drawable.negative_relief_card, "5 mins", Releaf.class, emotion));
            }
            if (yt){
                negatives.add(new Negative(5, "Yt Embedded", R.drawable.negative_yt_card, "5 mins",ytEmbbed.class,emotion));
            }
        }
        else if (emotion.equals("Annoyance")){
            if (breathe1) {
                negatives.add(new Negative(3, breathe, R.drawable.negative_breathe_card, "5 mins", jogg.class, emotion));
            }
            if (lemon2) {
                negatives.add(new Negative(4, lemon, R.drawable.negative_lemon_card, "5mins", lemonAndCinammonJuice.class, emotion));
            }
            if (journal) {
                negatives.add(new Negative(1, "Journal", R.drawable.negative_journal_card, "5 mins", Journal.class, emotion));
            }
            if (relief) {
                negatives.add(new Negative(2, "Relief", R.drawable.negative_relief_card, "5 mins", Releaf.class, emotion));
            }
            if (yt){
                negatives.add(new Negative(5, "Yt Embedded", R.drawable.negative_yt_card, "5 mins",ytEmbbed.class,emotion));
            }
        }
        else if (emotion.equals("Irritation")){
            if (breathe1) {
                negatives.add(new Negative(3, breathe, R.drawable.negative_breathe_card, "5 mins", jogg.class, emotion));
            }
            if (lemon2) {
                negatives.add(new Negative(4, lemon, R.drawable.negative_lemon_card, "5mins", lemonAndCinammonJuice.class, emotion));
            }
            if (journal) {
                negatives.add(new Negative(1, "Journal", R.drawable.negative_journal_card, "5 mins", Journal.class, emotion));
            }
            if (relief) {
                negatives.add(new Negative(2, "Relief", R.drawable.negative_relief_card, "5 mins", Releaf.class, emotion));
            }
            if (yt){
                negatives.add(new Negative(5, "Yt Embedded", R.drawable.negative_yt_card, "5 mins",ytEmbbed.class,emotion));
            }
        }
        else if (emotion.equals("Sad")){
            if (breathe1) {
                negatives.add(new Negative(3, breathe, R.drawable.negative_breathe_card, "5 mins", jogg.class, emotion));
            }
            if (lemon2) {
                negatives.add(new Negative(4, lemon, R.drawable.negative_lemon_card, "5mins", lemonAndCinammonJuice.class, emotion));
            }
            if (journal) {
                negatives.add(new Negative(1, "Journal", R.drawable.negative_journal_card, "5 mins", Journal.class, emotion));
            }
            if (relief) {
                negatives.add(new Negative(2, "Relief", R.drawable.negative_relief_card, "5 mins", Releaf.class, emotion));
            }
            if (yt){
                negatives.add(new Negative(5, "Yt Embedded", R.drawable.negative_yt_card, "5 mins",ytEmbbed.class,emotion));
            }
        }
        else if (emotion.equals("Upset")){
            if (breathe1) {
                negatives.add(new Negative(3, breathe, R.drawable.negative_breathe_card, "5 mins", jogg.class, emotion));
            }
            if (lemon2) {
                negatives.add(new Negative(4, lemon, R.drawable.negative_lemon_card, "5mins", lemonAndCinammonJuice.class, emotion));
            }
            if (journal) {
                negatives.add(new Negative(1, "Journal", R.drawable.negative_journal_card, "5 mins", Journal.class, emotion));
            }
            if (relief) {
                negatives.add(new Negative(2, "Relief", R.drawable.negative_relief_card, "5 mins", Releaf.class, emotion));
            }
            if (yt){
                negatives.add(new Negative(5, "Yt Embedded", R.drawable.negative_yt_card, "5 mins",ytEmbbed.class,emotion));
            }
        }
        else if (emotion.equals("Possessive")){
            if (breathe1) {
                negatives.add(new Negative(3, breathe, R.drawable.negative_breathe_card, "5 mins", jogg.class, emotion));
            }
            if (lemon2) {
                negatives.add(new Negative(4, lemon, R.drawable.negative_lemon_card, "5mins", lemonAndCinammonJuice.class, emotion));
            }
            if (journal) {
                negatives.add(new Negative(1, "Journal", R.drawable.negative_journal_card, "5 mins", Journal.class, emotion));
            }
            if (relief) {
                negatives.add(new Negative(2, "Relief", R.drawable.negative_relief_card, "5 mins", Releaf.class, emotion));
            }
            if (yt){
                negatives.add(new Negative(5, "Yt Embedded", R.drawable.negative_yt_card, "5 mins",ytEmbbed.class,emotion));
            }
        }
        else if (emotion.equals("Uncertain")){
            if (breathe1) {
                negatives.add(new Negative(3, breathe, R.drawable.negative_breathe_card, "5 mins", jogg.class, emotion));
            }
            if (lemon2) {
                negatives.add(new Negative(4, lemon, R.drawable.negative_lemon_card, "5mins", lemonAndCinammonJuice.class, emotion));
            }
            if (journal) {
                negatives.add(new Negative(1, "Journal", R.drawable.negative_journal_card, "5 mins", Journal.class, emotion));
            }
            if (relief) {
                negatives.add(new Negative(2, "Relief", R.drawable.negative_relief_card, "5 mins", Releaf.class, emotion));
            }
            if (yt){
                negatives.add(new Negative(5, "Yt Embedded", R.drawable.negative_yt_card, "5 mins",ytEmbbed.class,emotion));
            }
        }
        else if (emotion.equals("Anxiety")){
            if (breathe1) {
                negatives.add(new Negative(3, breathe, R.drawable.negative_breathe_card, "5 mins", jogg.class, emotion));
            }
            if (lemon2) {
                negatives.add(new Negative(4, lemon, R.drawable.negative_lemon_card, "5mins", lemonAndCinammonJuice.class, emotion));
            }
            if (journal) {
                negatives.add(new Negative(1, "Journal", R.drawable.negative_journal_card, "5 mins", Journal.class, emotion));
            }
            if (relief) {
                negatives.add(new Negative(2, "Relief", R.drawable.negative_relief_card, "5 mins", Releaf.class, emotion));
            }
            if (yt){
                negatives.add(new Negative(5, "Yt Embedded", R.drawable.negative_yt_card, "5 mins",ytEmbbed.class,emotion));
            }
        }
        else if (emotion.equals("Jealousy")){
            if (breathe1) {
                negatives.add(new Negative(3, breathe, R.drawable.negative_breathe_card, "5 mins", jogg.class, emotion));
            }
            if (lemon2) {
                negatives.add(new Negative(4, lemon, R.drawable.negative_lemon_card, "5mins", lemonAndCinammonJuice.class, emotion));
            }
            if (journal) {
                negatives.add(new Negative(1, "Journal", R.drawable.negative_journal_card, "5 mins", Journal.class, emotion));
            }
            if (relief) {
                negatives.add(new Negative(2, "Relief", R.drawable.negative_relief_card, "5 mins", Releaf.class, emotion));
            }
            if (yt){
                negatives.add(new Negative(5, "Yt Embedded", R.drawable.negative_yt_card, "5 mins",ytEmbbed.class,emotion));
            }
        }

        else {
            negatives.add(new Negative(1, "Breathe in, Breathe out", R.drawable.negative_breathe_card, "5 mins",breathInBreathOut.class,emotion));
            negatives.add(new Negative(2, "Lemon and cinnamon juice", R.drawable.negative_lemon_card, "5 mins",lemonAndCinammonJuice.class,emotion));
            negatives.add(new Negative(3, "Journal", R.drawable.negative_journal_card, "5 mins", Journal.class,emotion));
            negatives.add(new Negative(5, "Relief", R.drawable.negative_relief_card, "5 mins", Releaf.class,emotion));
            negatives.add(new Negative(4, "Yt Embedded", R.drawable.negative_yt_card, "5 mins",ytEmbbed.class,emotion));
        }
        negativeRecycleView.setLayoutManager(new LinearLayoutManager(this));
        negativeAdapter = new NegativeAdapter(this, negatives);
        negativeRecycleView.setAdapter(negativeAdapter);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(negativeDirectPage.this,HomePage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}