package com.nirman.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nirman.mentalwellness.R;
import com.nirman.models.Comment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentItem> {
    private final Context context;
    private final List<Comment> comments;

    public CommentAdapter(Context context, List<Comment> comments){
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @NotNull
    @Override
    public CommentItem onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new CommentItem(LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CommentItem holder, int position) {
        Comment comment = comments.get(position);
        holder.setUsername(comment.username);
        holder.setComment(comment.comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}

class CommentItem extends RecyclerView.ViewHolder{
    private final TextView username;
    private final TextView comment;

    public CommentItem(@NonNull @NotNull View itemView) {
        super(itemView);
        username = itemView.findViewById(R.id.comment_username);
        comment = itemView.findViewById(R.id.comment_comment);
    }

    public void setUsername(String username){
        this.username.setText(username);
    }

    public void setComment(String comment){
        this.comment.setText(comment);
    }
}