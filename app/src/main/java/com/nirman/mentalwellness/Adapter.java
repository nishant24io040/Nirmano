package com.nirman.mentalwellness;



import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.nirman.models.Feed;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.util.List;

class Adapter extends RecyclerView.Adapter<Adapter.MainViewHolder> {
    LayoutInflater inflater;
    List<Feed> feeds;
    final Context context;
    private String feedId;
//    HashMap<String,String> modelList;


    public Adapter(Context context, List<Feed> feeds) {
        this.inflater = LayoutInflater.from(context);
//        this.modelList = modelList;
        this.feeds = feeds;
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.feed_item, parent, false);
        return new MainViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        Feed feed = feeds.get(position);
        feedId = feed.id;
        //holder.bindData();
        holder.username.setText(feed.username);
        holder.feedData.setText(feed.feed);
        holder.setCommentListener(feedId);
        holder.time_text.setText(DateFormat.getDateTimeInstance().format(feed.time));

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageReference.child("loginUserDetails/" + feed.uid+"/profile.jpg");

        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context.getApplicationContext())
                        .load(uri)
                        .circleCrop()
                        .into(holder.profileImage);


//                Picasso.get().load(uri).rotate(90).resize(40, 40)
//                        .into(holder.profileImage, new Callback() {
//                            @Override
//                            public void onSuccess() {
//                                Bitmap imageBitmap = ((BitmapDrawable) holder
//                                        .profileImage.getDrawable()).getBitmap();
//                                RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem(), imageBitmap);
//                                imageDrawable.setCircular(true);
//                                imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
//                                holder.profileImage.setImageDrawable(imageDrawable);
//                            }
//
//                            @Override
//                            public void onError(Exception e) {
//                                e.printStackTrace();
//                            }
//                        });

            }
        });



    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {

        TextView username,feedData , time_text;
        View itemView;
        Boolean showFullFeed = false;
        ImageView profileImage;
        ImageButton commentButton, viewButton;

        public MainViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            username = itemView.findViewById(R.id.username_out);
            feedData = itemView.findViewById(R.id.feed_box);
            profileImage = itemView.findViewById(R.id.profile_image);
            commentButton = itemView.findViewById(R.id.comment_button);
            viewButton = itemView.findViewById(R.id.view_button);
            time_text = itemView.findViewById(R.id.time);
        }


        public void setViewListener(){
            viewButton.setOnClickListener(view -> {
                showFullFeed = !showFullFeed;
                // change the feed view...
            });
        }

        public void setCommentListener(String feed){
            commentButton.setOnClickListener(view -> {
                // pass the feed id
                new CommentDialog(itemView.getContext(), feed).show();
            });
        }

    }
    public void getImage(){

    }
}
