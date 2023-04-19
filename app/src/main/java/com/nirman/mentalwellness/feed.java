package com.nirman.mentalwellness;

import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nirman.Globals;

import com.nirman.models.Feed;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class feed extends AppCompatActivity {
    FirebaseFirestore db;
    ImageButton addFeed , back;
    RecyclerView feedRecycleView;
    Adapter adapter;
//    List<String> usernameList,FeedBoxList;

    LinearLayout homeLP;
    LinearLayout reliefLP;
    LinearLayout journalLP;
    LinearLayout feedLP;
    ImageView feedLPIcon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));

        back = findViewById(R.id.feed_back_button);

        homeLP = findViewById(R.id.home_button);
        reliefLP = findViewById(R.id.relief_button);
        journalLP = findViewById(R.id.journal_button);
        feedLP = findViewById(R.id.feed_button);
        feedLPIcon = feedLP.findViewById(R.id.feed_button_icon);
        feedLPIcon.setImageResource(R.drawable.ic_feed_selected);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        feedRecycleView = findViewById(R.id.feed_recycler_view);
        addFeed = findViewById(R.id.feed_add_button);
        FirebaseFirestore.getInstance();
        getFeedData();
        addFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(feed.this,addFeed.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        homeLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        feedLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(feed.this,feed.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        journalLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(feed.this,Journal.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        reliefLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(feed.this, Releaf.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

    }
    public void getFeedData(){
        this.db = FirebaseFirestore.getInstance();
        db.collection("feedDataCollection")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Feed> feeds = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String,Object> sample = new HashMap<>();
                                sample = document.getData();
                                feeds.add(new Feed((String) sample.get("usernameList"),(String) sample.get("feedBoxList"),(Long) sample.get("time"),(String) sample.get("uid"),(String) sample.get("feedID")));
                        }
                            sortFeeds(feeds);
                            showFeed(feeds);
                        } else {
                            Toast.makeText(feed.this, "Unable To Retrive Data From The Server", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


    }
    public void showFeed(List<Feed> feeds){
        adapter = new Adapter(this, feeds);
        feedRecycleView.setLayoutManager(new LinearLayoutManager(this));
        feedRecycleView.setAdapter(adapter);

    }

    public void sortFeeds(List<Feed> feeds){
        for(int i = 0; i < feeds.size() - 1; i++){
            for (int j = 0; j < feeds.size() - i - 1; j++){
                if (feeds.get(j).time < feeds.get(j+1).time){
                    Feed temp = feeds.get(j+1);
                    feeds.set(j+1, feeds.get(j));
                    feeds.set(j, temp);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(feed.this,HomePage.class);
        i.putExtra("uid","a");
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

}