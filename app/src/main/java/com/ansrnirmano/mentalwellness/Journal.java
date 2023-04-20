package com.ansrnirmano.mentalwellness;

import android.content.ComponentName;
import android.os.Build;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ansrnirmano.Globals;
import com.google.firebase.auth.FirebaseAuth;
import com.ansrnirmano.mentalwellness.MemoryForPositiveEmotion.JoyFragment;
import com.ansrnirmano.mentalwellness.MemoryForPositiveEmotion.LovedFragment;
import com.ansrnirmano.mentalwellness.MemoryForPositiveEmotion.SurprisedFragment;

import io.realm.RealmConfiguration;

public class Journal extends AppCompatActivity {
//    final String PREFS_NAM3E = "MyPrefsFile3";
    ImageButton back;

    TextView joytv,lovedtv,surprisedtv;
    LinearLayout homeLP;
    LinearLayout reliefLP;
    LinearLayout journalLP;
    LinearLayout feedLP;

    ConstraintLayout joy,loved,surprised,emptymemory,cons;
    ImageView journalLPIcon;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Nullable
    @Override
    public ComponentName getCallingActivity() {
        return super.getCallingActivity();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));

        back = findViewById(R.id.journal_back_button);
        homeLP = findViewById(R.id.home_button);
        reliefLP = findViewById(R.id.relief_button);
        journalLP = findViewById(R.id.journal_button);
        feedLP = findViewById(R.id.feed_button);
        journalLPIcon = journalLP.findViewById(R.id.journal_button_icon);
        journalLPIcon.setImageResource(R.drawable.ic_journal_selected);
        joy = findViewById(R.id.joy);
        cons = findViewById(R.id.cons);
        loved = findViewById(R.id.surprised);
        surprised = findViewById(R.id.loved);

        joytv = findViewById(R.id.joyt);
        lovedtv = findViewById(R.id.surpriset);
        surprisedtv = findViewById(R.id.lovedt);
        emptymemory = findViewById(R.id.emptymemory);
        joy.setOnClickListener(view -> {
            Selectjoy();
            replaceFragment(new JoyFragment());
        });

        loved.setOnClickListener(view -> {
            replaceFragment(new LovedFragment());
            Selectloved();
        });

        surprised.setOnClickListener(view -> {
            Selectsurprised();
            replaceFragment(new SurprisedFragment());
        });
        lovedtv.setOnClickListener(view -> {
            replaceFragment(new LovedFragment());
            Selectloved();
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragplace,new JoyFragment());
        fragmentManager.popBackStack();
        fragmentTransaction.commit();

        database.getReference().child("Memories").child(mAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    cons.setVisibility(View.VISIBLE);
                    emptymemory.setVisibility(View.GONE);
                }
                else {
                    cons.setVisibility(View.INVISIBLE);
                    emptymemory.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Journal.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });








        back.setOnClickListener(view -> {
           onBackPressed();
        });
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUserMetadata metadata = Objects.requireNonNull(mAuth.getCurrentUser()).getMetadata();
//
//        assert metadata != null;
//        if (metadata.getCreationTimestamp() == metadata.getLastSignInTimestamp()) {
//            if (newReg.getBoolean("wow1",true)){
//                newReg.edit().putBoolean("wow1",false).apply();
//            }
//        }
        homeLP.setOnClickListener(view -> {
            Intent i = new Intent(Journal.this,HomePage.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("uid","a");
            startActivity(i);
        });
        feedLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Journal.this,feed.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        reliefLP.setOnClickListener(view -> {
            Intent i = new Intent(Journal.this,Releaf.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragplace,fragment);
        fragmentManager.popBackStack();
        fragmentTransaction.commit();
    }
    private void Selectjoy (){
        joy.setBackground(getResources().getDrawable(R.drawable.nextbuttonbg));
        joytv.setTextColor(getResources().getColor(R.color.white));
        lovedtv.setTextColor(getResources().getColor(R.color.fill));
        loved.setBackground(getResources().getDrawable(R.drawable.bluebordorandwhite));
        surprisedtv.setTextColor(getResources().getColor(R.color.fill));
        surprised.setBackground(getResources().getDrawable(R.drawable.bluebordorandwhite));
    }
    private void Selectloved (){
        loved.setBackground(getResources().getDrawable(R.drawable.nextbuttonbg));
        lovedtv.setTextColor(getResources().getColor(R.color.white));
        joytv.setTextColor(getResources().getColor(R.color.fill));
        joy.setBackground(getResources().getDrawable(R.drawable.bluebordorandwhite));
        surprisedtv.setTextColor(getResources().getColor(R.color.fill));
        surprised.setBackground(getResources().getDrawable(R.drawable.bluebordorandwhite));
    }
    private void Selectsurprised (){
        surprised.setBackground(getResources().getDrawable(R.drawable.nextbuttonbg));
        surprisedtv.setTextColor(getResources().getColor(R.color.white));
        lovedtv.setTextColor(getResources().getColor(R.color.fill));
        loved.setBackground(getResources().getDrawable(R.drawable.bluebordorandwhite));
        joytv.setTextColor(getResources().getColor(R.color.fill));
        joy.setBackground(getResources().getDrawable(R.drawable.bluebordorandwhite));
    }

    public static class RealmUtility {
        private static final int SCHEMA_V_PREV = 1;// previous schema version
        private static final int SCHEMA_V_NOW = 2;// change schema version if any change happened in schema


        public static int getSchemaVNow() {
            return SCHEMA_V_NOW;
        }


        public static RealmConfiguration getDefaultConfig() {
            return new RealmConfiguration.Builder()
                    .schemaVersion(SCHEMA_V_NOW)
                    .deleteRealmIfMigrationNeeded()// if migration needed then this methoud will remove the existing database and will create new database
                    .build();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

//        String act = getIntent().getStringExtra("act");
//        String name = getIntent().getStringExtra("name");
//        if (act!=null){
//        if(act.equals("negative")){
//        Intent i = new Intent(Journal.this,negativeDirectPage.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.putExtra("name",name);
//        startActivity(i);
//    }
//    else if (act.equals("positive")){
//            Intent i = new Intent(Journal.this,positiveDirectPage.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.putExtra("name",name);
//            startActivity(i);
//
//        }
//    else {
            Intent i = new Intent(Journal.this,HomePage.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);


}


}