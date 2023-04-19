package com.nirman.mentalwellness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nirman.Globals;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class addFeed extends AppCompatActivity {
    EditText feedData;
    ImageView emoji , clearAll;
    Button saveFeed;
    FirebaseFirestore db;
    String userName;
    ImageView feedBackBtn;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feed);
//        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));
        feedData = findViewById(R.id.feed_input);
        saveFeed = findViewById(R.id.done_button);
        feedBackBtn = findViewById(R.id.feed_back_button);

//        emoji = findViewById(R.id.emojiBtn);
        clearAll = findViewById(R.id.feed_delete_button);

        feedBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(addFeed.this,feed.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedData.setText("");
            }
        });
        saveFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (feedData.getText().toString().isEmpty()){
                    Toast.makeText(addFeed.this, "Empty Feed Not Allowed", Toast.LENGTH_SHORT).show();
                }
                else {
                    saveFeed.setEnabled(false);
                    getUserName();
            }}
        });
    }
    private void getUserName(){
        if (Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).isEmailVerified()){
            gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
            gsc = GoogleSignIn.getClient(this,gso);
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
            assert account != null;
            userName = account.getDisplayName();
            saveFeedData(userName);

        }
        else {
            this.db = FirebaseFirestore.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String phoneNumber = user.getPhoneNumber();
            assert phoneNumber != null;
            db.collection("phoneLoginDetails").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot document =  task.getResult();
                    Map<String,Object> map = new HashMap<>();
                    map = document.getData();
                    userName = (String) map.get("usernameList");
                    saveFeedData(userName);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("fetch",e.getMessage());
                }
            });
        }



    }
    private void saveFeedData(String userName){
        this.db = FirebaseFirestore.getInstance();
        String uuid = UUID.randomUUID().toString();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Map<String,Object> storeData = new HashMap<>();
        storeData.put("usernameList" , userName);
        storeData.put("feedBoxList" , feedData.getText().toString());
        storeData.put("time", System.currentTimeMillis());
        storeData.put("uid",user.getUid());
        storeData.put("feedID",uuid);
        db.collection("feedDataCollection").document(userName + " " + UUID.randomUUID().toString()).set(storeData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(addFeed.this, "Your Feed Added Successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(addFeed.this,feed.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(addFeed.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(addFeed.this,feed.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}