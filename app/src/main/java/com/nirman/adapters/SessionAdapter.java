package com.nirman.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nirman.mentalwellness.R;
import com.nirman.mentalwellness.cohort;
import com.nirman.mentalwellness.cohort_feedback;
import com.nirman.models.Session;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionItem> {
    private final Context context;
    private final List<Session> sessions;

    public SessionAdapter(Context context, List<Session> sessions){
        this.context = context;
        this.sessions = sessions;
    }

    @NonNull
    @NotNull
    @Override
    public SessionItem onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new SessionItem(LayoutInflater.from(context).inflate(R.layout.session_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SessionItem holder, int position) {
        Session session = sessions.get(position);
        holder.setHeading(session.heading);
        holder.setSubHeading(session.subHeading);
        holder.setDate(session.date);
        if (session.imageUrl != null)
            holder.setImage(session.imageUrl);
        else
            holder.setImage(session.imageResource);
        holder.setOnClick(context,session.docID,session.feedbackStart,session.heading,session.prof);
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }
}

class SessionItem extends RecyclerView.ViewHolder{

    private final View itemView;
    private final TextView headingTextView;
    private final TextView subHeadingTextView;
    private final TextView dateTextView;
    private final ImageView imageImageView;
    private final ExtendedFloatingActionButton registerButton;


    public SessionItem(@NonNull @NotNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        headingTextView = itemView.findViewById(R.id.session_card_heading);
        subHeadingTextView = itemView.findViewById(R.id.session_card_sub_heading);
        dateTextView = itemView.findViewById(R.id.session_card_date);
        imageImageView = itemView.findViewById(R.id.session_card_image);
        registerButton = itemView.findViewById(R.id.session_card_button);

    }

    public void setHeading(String heading){
        headingTextView.setText(heading);
    }

    public void setSubHeading(String subHeading){
        subHeadingTextView.setText(subHeading);
    }

    public void setDate(String date){
        dateTextView.setText(date);
    }

    public void setImage(Uri imageUrl){
        Picasso.get().load(imageUrl).into(imageImageView);
    }

    public void setImage(int imageResource){
        imageImageView.setImageResource(imageResource);
    }

    public void setOnClick(Context context,String id,boolean feedbackStart,String heading,String prof) {
        // TODO: redirect
        if (feedbackStart) {
            registerButton.setText("Give Your Valuable Feedback");
            registerButton.setOnClickListener(view -> {
                Intent intent = new Intent(context, cohort_feedback.class);
                intent.putExtra("heading", heading);
                intent.putExtra("prof",prof);
                context.startActivity(intent);
            });
        } else {
            registerButton.setOnClickListener(view -> {
                Intent intent = new Intent(context, cohort.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
            });
        }
    }
}