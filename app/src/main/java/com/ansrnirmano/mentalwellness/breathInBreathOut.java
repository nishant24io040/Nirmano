package com.ansrnirmano.mentalwellness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.view.WindowCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ansrnirmano.Globals;

public class breathInBreathOut extends AppCompatActivity {
    ImageButton back;
    TextView duration,label,title1,title2,content1,content2;
    Button btnstart;
    TempModal tempModal;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference referenceangry,referencesad,referencefear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath_in_breath_out);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));
        duration = findViewById(R.id.negative_redirect_section_2_text2);
        btnstart = findViewById(R.id.start);
        back = findViewById(R.id.bibo_back_button);
        label = findViewById(R.id.lebel);
        title1 = findViewById(R.id.title12);
        title2 = findViewById(R.id.title21);
        content1 = findViewById(R.id.content);
        content2 = findViewById(R.id.content2);

        tempModal = new TempModal();
        referenceangry = database.getReference("Mindfulness").child("Angry");
        referencesad = database.getReference("Mindfulness").child("Sad");
        referencefear = database.getReference("Mindfulness").child("Fear");
        SharedPreferences sd;
        sd = this.getSharedPreferences("datafile",Context.MODE_PRIVATE);
        switch (sd.getString("feeling", "")) {
            case "angry":
                referenceangry.child("anger").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "rage":
                referenceangry.child("Rage").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "exasperated":
                referenceangry.child("Exasperated").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "irritable":
                referenceangry.child("Irritable").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "envy":
                referenceangry.child("Envy").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "disgust":
                referenceangry.child("Disgust").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "AGGRAVATED":
                referenceangry.child("Aggravated").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Annoyed":
                referenceangry.child("Annoyed").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Frustrated":
                referenceangry.child("Frustrated").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Agitated":
                referenceangry.child("Agitated").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Hostile":
                referenceangry.child("Hostile").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Hate":
                referenceangry.child("Hate").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Jealous":
                referenceangry.child("Jealous").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Resentful":
                referenceangry.child("Resentful").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Revolted":
                referenceangry.child("Revolted").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Contempt":
                referenceangry.child("Contempt").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "sad":
                referencesad.child("Sadness").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
             case "upset":
                referencesad.child("Upset").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
             case "suffering":
                referencesad.child("Suffering").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
             case "disappointed":
                referencesad.child("Disappointed").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
             case "shameful":
                referencesad.child("Shameful").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "neglected":
                referencesad.child("Neglected").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "despair":
                referencesad.child("Despair").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Regretful":
                referencesad.child("Regretful").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Guilty":
                referencesad.child("Guilty").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Hurt":
                referencesad.child("Hurt").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Sorrow":
                referencesad.child("Sorrow").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Depressed":
                referencesad.child("Depressed").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Powerless":
                referencesad.child("Powerless").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Grief":
                referencesad.child("Grief").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Displeased":
                referencesad.child("Displeased").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Dismayed":
                referencesad.child("Dismayed").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Lonly":
                referencesad.child("Lonely").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Isolated":
                referencesad.child("Isolated").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Horror":
                referencefear.child("Horror").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "NERVOUS":
                referencefear.child("Nervous").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "INSECURE":
                referencefear.child("Insecure").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Terror":
                referencefear.child("Terror").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Scared":
                referencefear.child("Scared").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Dread":
                referencefear.child("Dread").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Humiliate":
                referencefear.child("Humiliate").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Incomplete":
                referencefear.child("Incomplete").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Inferior":
                referencefear.child("Inferior").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Worried":
                referencefear.child("Worried").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Anxious":
                referencefear.child("Anxious").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Helpless":
                referencefear.child("Helpless").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Frightended":
                referencefear.child("Frightened").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Hysterical":
                referencefear.child("Hysterical").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "Panic":
                referencefear.child("Panic").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            case "fear":
                referencefear.child("Feared").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            tempModal = snapshot.getValue(TempModal.class);
                            setcontents(tempModal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
            }
        duration.setText(tempModal.getContent1());
//        database.getReference("Mindfulness").child("").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//               if (snapshot.exists()){
//                   duration.setText(snapshot.getValue().toString());
//                   s = snapshot.getValue().toString();
//               }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnstart.setOnClickListener(view -> {
            Intent intent = new Intent(this,StopWatch.class);
            long i = Long.parseLong(duration.getText().toString());
            intent.putExtra("time",i);
            startActivity(intent);
        });
    }
    private void setcontents(TempModal tempModal){
        duration.setText(tempModal.getDuration());
        label.setText(tempModal.getLabel());
        title2.setText(tempModal.getTitle2());
        title1.setText(tempModal.getTitle1());
        content2.setText(tempModal.getContent2());
        content1.setText(tempModal.getContent1());
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}