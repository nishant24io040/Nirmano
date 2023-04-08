package com.mental.mentalwellness;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mental.Globals;
import com.mental.adapters.SessionAdapter;

import com.mental.mentalwellness.TherepySection.TherepyActivity;
import com.mental.models.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class HomePage extends AppCompatActivity {
    // Other Required IDs
    FirebaseAuth mAuth;
    FirebaseFirestore db;


    // Main Home Button Ids
    TextView skipBottom;
    TextView name, accountName ,accountEmail;
    String username;


    //RP Stands For Right navigation view  window when we click on EditProfilePhoto
    TextView editProfileRP;
    TextView feedbackRP;
    TextView logOutRP;
    TextView termsAndConditionsRP;
    ImageView rightNavigationRP;
    LinearLayout sidePanel;
    View sidePanelDismiss;
    public int counter;
    // Linear Bottom Layouts Buttons for LP
    LinearLayoutCompat LP;
    LinearLayout homeLP;
    LinearLayout reliefLP;
    LinearLayout journalLP;
    LinearLayout feedLP;
    ImageView homeLPIcon;

    //Pop UP ids used  as PU
    TextView headPU,skip;
    TextView bodyPU;
    ConstraintLayout homePopUp,skipb;

    //Feeling Buttons as FB
    RecyclerView feelingRecyclerView;
    RecyclerView.Adapter feelingAdapter;

    RecyclerView sessionRecyclerView;
    RecyclerView.Adapter sessionAdapter;


    StorageReference storageReference;
    ImageView profilePhoto, sidebarProfilePhoto;


    String CHANNEL_ID = "my_channel_01";
    public static boolean isNew;
    List<String> emojiServer;
    ProgressBar progressBar;

    boolean newG,newR;
    Bundle bundle;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        final String PREFS_NAME = "MyPrefsFile";
        newG = HomePage.this.getIntent().getBooleanExtra("isNewG",false);
        newR = HomePage.this.getIntent().getBooleanExtra("new",false);
        isNew = newG||newR;
        storageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        feelingRecyclerView = findViewById(R.id.home_feeling_recycler_view);
//        sessionRecyclerView = findViewById(R.id.home_session_recycler_view);
        profilePhoto = findViewById(R.id.profile_photo_home);
        sidebarProfilePhoto = findViewById(R.id.side_panel_profile);
        getCohortDetails();
//try {
//    db = FirebaseFirestore.getInstance();
//    emojiServer = new ArrayList<>();
//    db.collection("emoji")
//            .get()
//            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    if (task.isSuccessful()) {
//                        if(!task.getResult().isEmpty()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Map<String, Object> sample = new HashMap<>();
//                                sample = document.getData();
//                                emojiServer.add((String) sample.get("emotion"));
//                            }
//                            addEmoji(emojiServer);
//                        }
//                        else feelingRecyclerView.setVisibility(View.GONE);
//                    } else {
//                        Toast.makeText(HomePage.this, "Unable To Retrive Data From The Server", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                }
//            });
//
//}catch (Exception e){
//    e.printStackTrace();
//}

        //Pop Up Ids
        headPU = findViewById(R.id.popup_heading);
        bodyPU = findViewById(R.id.popup_description);
        homePopUp = findViewById(R.id.home_popup);
        if (true) {
            headPU.setText("Emotion Management");
            bodyPU.setText("Choose how you are feeling right now and we are there to help you out!");
        }

        //LP IDs
        LP = findViewById(R.id.bottom_navbar);
        homeLP = findViewById(R.id.home_button);
        reliefLP = findViewById(R.id.relief_button);
        journalLP = findViewById(R.id.journal_button);
        feedLP = findViewById(R.id.feed_button);
        homeLPIcon = homeLP.findViewById(R.id.home_button_icon);
        homeLPIcon.setImageResource(R.drawable.ic_home_selected);


        //Rp IDs
        editProfileRP = findViewById(R.id.edit_button);
        feedbackRP = findViewById(R.id.feedback_button);

        logOutRP = findViewById(R.id.logout_button);
        termsAndConditionsRP = findViewById(R.id.tc_button);
        sidePanel = findViewById(R.id.side_panel_view);
        sidePanelDismiss = findViewById(R.id.side_panel_dismiss_view);

        //Main Page IDs
        skipBottom = findViewById(R.id.popup_skip_button);
        name = findViewById(R.id.name);
        accountEmail = findViewById(R.id.account_email);
        accountName = findViewById(R.id.account_name);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentcons,new homefrag());
        fragmentManager.popBackStack();
        fragmentTransaction.commit();

        bundle = getIntent().getExtras();
        skipb = findViewById(R.id.skipb);
        homePopUp.setVisibility(View.GONE);
        skip = findViewById(R.id.skit);
        try {
            if (bundle.getString("uid").equals("a")){
                LP.setVisibility(View.VISIBLE);
            }
            else{
                skipb.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){
            LP.setVisibility(View.VISIBLE);
        }


        skip.setOnClickListener(view -> {
            skipb.setVisibility(View.GONE);
            LP.setVisibility(View.VISIBLE);
        });

        final String pref = "MyPrefsFile";
        SharedPreferences newReg = getSharedPreferences(pref, 0);

        notification1();

        // TODO: get session info from admin panel

        try {
            StorageReference profileRef = storageReference.child("loginUserDetails/" + mAuth.getCurrentUser().getUid()+"/profile.jpg");
            profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(getApplicationContext())
                            .load(uri)
                            .circleCrop()
                            .into(profilePhoto);


                    Glide.with(getApplicationContext())
                            .load(uri)
                            .circleCrop()
                            .into(sidebarProfilePhoto);
                }
            });

        }
        catch (Exception e){
            e.printStackTrace();
        }



        //Firebase auth

        if (user.getDisplayName()!=null && user.getDisplayName().length()>2){
            name.setText("Hey " + user.getDisplayName()+"," );
            accountName.setText(user.getDisplayName());

        }
        if(user.getEmail()!=null&& user.getEmail().length()>2){
            accountEmail.setText(user.getEmail());
        }
        else{
            this.db = FirebaseFirestore.getInstance();
            assert user.getPhoneNumber() != null;
            db.collection("phoneLoginDetails").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if (documentSnapshot.exists()){
                            Map<String,Object> map = new HashMap<>();
                            map = documentSnapshot.getData();
                            username = String.valueOf(map.get("usernameList"));
                            name.setText("Hey "+username);
                            accountName.setText(username);
                        }
                        else Toast.makeText(HomePage.this, "Document not exists", Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(HomePage.this, "Username Not Found", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(HomePage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
        //LP set on click listener
        homeLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,HomePage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        feedLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,feed.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("pop",isNew);
                startActivity(i);
            }
        });
        journalLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,Journal.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("pop",isNew);
                startActivity(i);
            }
        });
        reliefLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, Tharapytemp.class);
                startActivity(intent);
            }
        });

       // RP set on click listners
//        profilePhoto.setOnClickListener(view -> sidePanel.animate().translationX(0).setDuration(300).start());



        editProfileRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,editProfile.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });

        logOutRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(HomePage.this,WelcomePage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        termsAndConditionsRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,termsAndCondition.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("bool",false);
                startActivity(i);
            }
        });
        feedbackRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,feedback.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        skipBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homePopUp.setVisibility(View.GONE);
                LP.setVisibility(View.VISIBLE);
            }
        });

        profilePhoto.setOnClickListener(view -> {
            sidePanel
                    .animate()
                    .withStartAction(() -> sidePanel.setVisibility(View.VISIBLE))
                    .alpha(1)
                    .setDuration(300)
                    .start();

            sidePanelDismiss
                    .animate()
                    .withStartAction(() -> sidePanelDismiss.setVisibility(View.VISIBLE))
                    .alpha(1)
                    .setDuration(300)
                    .start();
        });

        sidePanelDismiss.setOnClickListener(view -> {
            sidePanel
                    .animate()
                    .alpha(0)
                    .setDuration(300)
                    .withEndAction(() -> sidePanel.setVisibility(View.GONE))
                    .start();
            sidePanelDismiss
                    .animate()
                    .alpha(0)
                    .setDuration(300)
                    .withEndAction(() -> sidePanelDismiss.setVisibility(View.GONE))
                    .start();
        });
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                        String msg = "Subscribed";
//                        Toast.makeText(HomePage.this, msg, Toast.LENGTH_SHORT).show();
//                        if (!task.isSuccessful()) {
//                            msg = "Subscribe failed";
//                            Toast.makeText(HomePage.this, msg, Toast.LENGTH_SHORT).show();
//                        }
                    }
                });


    }

    private void setUpNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("200", "General", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendNotification() {
        Intent intent = new Intent(this, HomePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "200")
                    .setSmallIcon(R.drawable.icon)
                    .setContentTitle("Got a problem?")
                    .setContentText("Hey, there is something new for your self care, so check it out!")
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Hey, there is something new for your self care, so check it out!"))
                    .setContentIntent(pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(new Random().nextInt(100), builder.build());
        } else{

            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, "CHANNEL_ID", NotificationManager.IMPORTANCE_HIGH);
            Notification notification =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.icon)
                            .setContentTitle("Got a problem?")
                            .setContentText("Hey, there is something new for your self care, so check it out!")
                            .setChannelId(CHANNEL_ID).build();
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(mChannel);

// Issue the notification.
            mNotificationManager.notify(1 , notification);

        }
    }
    public void init(){

        isNew = newG || newR;
    }
    public boolean isNewInit(){
        init();
        return isNew;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void notification1(){
        if (isNewInit()){
            LP.setVisibility(View.GONE);
            homePopUp.setVisibility(View.VISIBLE);

            setUpNotificationChannel();
            sendNotification();
        }
    }
    public void addCohort(String cohortHead,String cohortSubHeading, String cohortDate, Uri cohorturl,List<Session> sessions,String docID,boolean feedbackStart,String prof){
        sessions.add(new Session(cohortHead,cohortSubHeading,cohortDate,cohorturl,docID,feedbackStart,prof));
        sessionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sessionAdapter = new SessionAdapter(this, sessions);
        sessionRecyclerView.setAdapter(sessionAdapter);

        new CountDownTimer(3000, 1000){
            public void onTick(long millisUntilFinished){
                counter++;
            }
            public  void onFinish(){
          progressBar.setVisibility(View.GONE);
            }
        }.start();

    }
    public void getCohortDetails(){
        db = FirebaseFirestore.getInstance();
        List<Session> sessions = new ArrayList<>();
        db.collection("cohortActivity")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> sample = new HashMap<>();
                                sample = document.getData();
                                if (!sample.isEmpty()) {
                                    String cohortHead = (String) sample.get("cohortHead");
                                    String cohortSubHeading = (String) sample.get("cohortSubHead");
                                    String cohortDate = (String) sample.get("cohortDate");
                                    String cohortUrl = (String) sample.get("cohortUrl");
                                    String prof = (String) sample.get("profName");
                                    boolean feedbackStart = (boolean) sample.get("feedbackStart");
                                    storageReference = FirebaseStorage.getInstance().getReference();
                                    StorageReference profileRef = storageReference.child(cohortUrl);
                                    profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            addCohort(cohortHead, cohortSubHeading, cohortDate, uri,sessions,document.getId(),feedbackStart,prof);
                                        }
                                    });
                                }
                                else sessionRecyclerView.setVisibility(View.GONE);
                            }


                        } else {
                            Toast.makeText(HomePage.this, "Unable To Retrive Data From The Server", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

//    public void addEmoji(List<String> emojiServer){
//        //Feelings Buttons
//
//        // get all these from firebase
//        List<Feeling> feelings = new ArrayList<>();
//        if (emojiServer.contains("Happy")) {
//            feelings.add(new Feeling("Happy", R.drawable.feeling_happy, true));
//        }
//        if (emojiServer.contains("Sad")) {
//            feelings.add(new Feeling("Sad", R.drawable.feeling_sad, false));
//        }
//        if (emojiServer.contains("Anger")) {
//            feelings.add(new Feeling("Anger", R.drawable.feeling_anger, false));
//        }
//        if (emojiServer.contains("Love")) {
//            feelings.add(new Feeling("Love", R.drawable.feeling_love, true));
//        }
//        if (emojiServer.contains("Rage")) {
//            feelings.add(new Feeling("Rage", R.drawable.feeling_rage, false));
//        }
//        if (emojiServer.contains("Surprise")) {
//            feelings.add(new Feeling("Surprise", R.drawable.feeling_surprise, true));
//        }
//        if (emojiServer.contains("Grief")) {
//            feelings.add(new Feeling("Grief", R.drawable.feeling_grief, false));
//        }
//        if (emojiServer.contains("Gloomy")) {
//            feelings.add(new Feeling("Gloomy", R.drawable.feeling_gloomy, false));
//        }
//        if (emojiServer.contains("Anxiety")){
//        feelings.add(new Feeling("Anxiety", R.drawable.feeling_anxiety, false));
//        }
//        if (emojiServer.contains("Annoyance")) {
//            feelings.add(new Feeling("Annoyance", R.drawable.feeling_annoyance, false));
//        }
//        if (emojiServer.contains("Upset")) {
//            feelings.add(new Feeling("Upset", R.drawable.feeling_upset, false));
//        }
//        if (emojiServer.contains("Trauma")) {
//            feelings.add(new Feeling("Trauma", R.drawable.feeling_trauma, false));
//        }
//        if (emojiServer.contains("Shock")) {
//            feelings.add(new Feeling("Shock", R.drawable.feeling_shock, false));
//        }
//        if (emojiServer.contains("Excited")) {
//            feelings.add(new Feeling("Excited", R.drawable.feeling_excited, true));
//        }
//        if (emojiServer.contains("Optimistic")) {
//            feelings.add(new Feeling("Optimistic", R.drawable.feeling_optimistic, true));
//        }
//        if (emojiServer.contains("Confident")) {
//            feelings.add(new Feeling("Confident", R.drawable.feeling_confident, true));
//        }
//        if (emojiServer.contains("Irritation")) {
//            feelings.add(new Feeling("Irritation", R.drawable.feeling_irritation, false));
//        }
//        if (emojiServer.contains("Jealousy")) {
//            feelings.add(new Feeling("Jealousy", R.drawable.feeling_jealousy, false));
//        }
//        if (emojiServer.contains("Fear")) {
//            feelings.add(new Feeling("Fear", R.drawable.feeling_fear, false));
//        }
//        if (emojiServer.contains("Uncertain")) {
//            feelings.add(new Feeling("Uncertain", R.drawable.feeling_uncertain, false));
//        }
//        if (emojiServer.contains("Hopeful")) {
//            feelings.add(new Feeling("Hopeful", R.drawable.feeling_hopeful, true));
//        }
//        if (emojiServer.contains("Panic")) {
//            feelings.add(new Feeling("Panic", R.drawable.feeling_panic, false));
//        }
//        if (emojiServer.contains("Paranoid")) {
//            feelings.add(new Feeling("Paranoid", R.drawable.feeling_paranoid, false));
//        }
//        if (emojiServer.contains("Regret")) {
//            feelings.add(new Feeling("Regret", R.drawable.feeling_regret, false));
//        }
//        if (emojiServer.contains("Possessive")) {
//            feelings.add(new Feeling("Possessive", R.drawable.feeling_possessive, false));
//        }
//        if (emojiServer.contains("Guilt")) {
//            feelings.add(new Feeling("Guilt", R.drawable.feeling_guilt, false));
//        }
//        if (emojiServer.contains("Terror")) {
//            feelings.add(new Feeling("Terror", R.drawable.feeling_terror, false));
//        }
//        if (emojiServer.contains("Motivated")) {
//            feelings.add(new Feeling("Motivated", R.drawable.feeling_motivated, true));
//        }
////        else{
////            Toast.makeText(this, "No Emotion For Today", Toast.LENGTH_SHORT).show();
////        }
//
//        feelingRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
//        feelingAdapter = new FeelingAdapter(this, feelings);
//        feelingRecyclerView.setAdapter(feelingAdapter);
//
//    }

    @Override
    public void onBackPressed() {
        this.finish();
        System.exit(1);
    }
}
