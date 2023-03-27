package com.mental.mentalwellness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.WindowCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mental.Globals;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.HashMap;
import java.util.Map;

public class cohort extends AppCompatActivity {
    ExtendedFloatingActionButton bookSession;
    ImageButton back;
    TextView title,dateTime,profName,profDesignation,mainPara,topicCover,fee;
    ImageView profImage;
    FirebaseFirestore db;
    StorageReference storageReference;
    String link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cohort);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));

        title = findViewById(R.id.cohortTitle);
        dateTime = findViewById(R.id.cohort_date);
        profName = findViewById(R.id.prof_name);
        profDesignation = findViewById(R.id.prof_post);
        mainPara = findViewById(R.id.main_para);
        topicCover = findViewById(R.id.topic_cover);
        fee = findViewById(R.id.session_fee);
        profImage = findViewById(R.id.cohort_image);
        bookSession = findViewById(R.id.book);

        callFirebase();
        back = findViewById(R.id.cohort_back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(cohort.this,HomePage.class));
            }
        });



    }
    public void callFirebase(){
        try {
        String docID = getIntent().getStringExtra("id");

            db = FirebaseFirestore.getInstance();

            db.collection("cohortActivity")
                    .document(docID)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                    Map<String, Object> sample = new HashMap<>();
                                    sample = task.getResult().getData();
                                    if (!sample.isEmpty()) {
                                        title.setText((String) sample.get("cohortTitle"));
                                        dateTime.setText((String) sample.get("cohortDateAndTime"));
                                        profName.setText((String) sample.get("profName"));
                                        profDesignation.setText((String) sample.get("profTitle"));
                                        mainPara.setText((String) sample.get("mainPara"));
                                        topicCover.setText((String) sample.get("topicCovers"));
                                        fee.setText((String) sample.get("sessionFees"));
                                        String cohortUrl = (String) sample.get("cohortSpeakerImageUrl");
                                        String zoom = (String) sample.get("cohortZoom");

                                        bookSession.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                // Direct To zoom Activity to be handled by web person
                                                openWebPage(zoom);
                                            }
                                        });

                                        storageReference = FirebaseStorage.getInstance().getReference();
                                        StorageReference profileRef = storageReference.child(cohortUrl);
                                        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                Glide.with(getApplicationContext())
                                                        .load(uri)
                                                        .circleCrop()
                                                        .into(profImage);
                                            }
                                        });
                                    } else {
                                        Toast.makeText(cohort.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(cohort.this, HomePage.class));
                                    }



                            } else {
                                Toast.makeText(cohort.this, "Unable To Retrive Data From The Server", Toast.LENGTH_SHORT).show();

                            }


                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(cohort.this,HomePage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}