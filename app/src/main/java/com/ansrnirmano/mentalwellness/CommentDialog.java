package com.ansrnirmano.mentalwellness;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ansrnirmano.adapters.CommentAdapter;

import com.ansrnirmano.models.Comment;
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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class CommentDialog extends Dialog {
    private final String feedID;
    private final List<Comment> comments;
    private EditText commentInput;
    private RecyclerView commentsRecyclerView;
    private RecyclerView.Adapter commentAdapter;
    FirebaseFirestore db;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private String uuid;

    public CommentDialog(@NonNull Context context, String feedID) {
        super(context);
        this.feedID = feedID;
        this.comments = new ArrayList<>();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_comment_section);
        Window window = getWindow();
        window.setBackgroundDrawable(null);
        setCanceledOnTouchOutside(true);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ImageButton sendButton = findViewById(R.id.comment_send_button);
        commentInput = findViewById(R.id.comment_input);
        commentsRecyclerView = findViewById(R.id.comment_recycler_view);
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        commentAdapter = new CommentAdapter(getContext(), comments);
        commentsRecyclerView.setAdapter(commentAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (commentInput.getText().toString().trim().length() <= 0){
                    Toast.makeText(CommentDialog.this.getContext(), "Fill Down Your Comment", Toast.LENGTH_SHORT).show();
                }
                else{
                    sendButton.setEnabled(false);
                getUserName(view,feedID,uuid,commentInput.getText().toString());
                    commentInput.setText("");
                sendButton.setEnabled(true);

            }}
        });

        populateComments(feedID,uuid);
    }

    private void handleSend(String userName , View view, String feedID,String comment) {
        this.db = FirebaseFirestore.getInstance();
        uuid =UUID.randomUUID().toString();

        Map<String,Object> storeData = new HashMap<>();
        storeData.put("usernameList" , userName);
        storeData.put("comment" , comment);
        storeData.put("time",System.currentTimeMillis());
        storeData.put("feedID",feedID+" "+uuid);
        storeData.put("uid",uuid);
        assert feedID!= null;
        db.collection("feedCommentCollection").document(feedID+" "+ uuid).set(storeData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(view.getContext(), "Your Comment Added Successfully", Toast.LENGTH_SHORT).show();

//                    restartActivity(CommentDialog.this, feedID);
                    comments.clear();
//                    CommentDialog.this.cancel();
//                    new CommentDialog(CommentDialog.this.getContext(), feedID).show();
//                    UUID uuid2 = UUID.randomUUID();
                    populateComments(feedID,uuid);

                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//        comments.clear();
//        populateComments(feedID,uuid);
    }



    private void populateComments(String feedID,String uuid) {
        // get all the comments using feedID
try{
        this.db = FirebaseFirestore.getInstance();
        db.collection("feedCommentCollection")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String,Object> sample = new HashMap<>();
                                sample = document.getData();
                                String feedI = (String)sample.get("feedID");
                                String uuid2 = (String)sample.get("uid");
                                String com = (String) sample.get("comment");
                                if (feedI.equals(feedID+" "+uuid2))
                                    if (com.trim().length()>0){
                                    comments.add(new Comment(feedID+" "+uuid2, (String)sample.get("usernameList"),(String) sample.get("comment"), (Long) sample.get("time")));

                            }
                            }
                            sortFeeds(comments);
                            commentAdapter.notifyDataSetChanged();


                        }

                    }
                });}catch (Exception e){
    e.printStackTrace();
}

    }

    private void getUserName(View view,String feedID,String uuid,String comment){
        if (Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).isEmailVerified()){
            gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
            gsc = GoogleSignIn.getClient(view.getContext(),gso);
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(view.getContext());
            assert account != null;
            String userName = account.getDisplayName();
            handleSend(userName , view , feedID,comment);

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
                    String userName = (String) map.get("usernameList");
                    handleSend(userName , view,feedID,comment );

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("fetch",e.getMessage());
                }
            });
        }



    }


    public void sortFeeds(List<Comment> comments){
        for(int i = 0; i < comments.size() - 1; i++){
            for (int j = 0; j < comments.size() - i - 1; j++){
                if (comments.get(j).time < comments.get(j+1).time){
                    Comment temp = comments.get(j+1);
                    comments.set(j+1, comments.get(j));
                    comments.set(j, temp);
                }
            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CommentDialog dialog = new CommentDialog(getContext().getApplicationContext(), feedID);
        dialog.dismiss();
        dialog.cancel();

    }
}
