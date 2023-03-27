package com.mental.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mental.mentalwellness.R;
import com.mental.models.Negative;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NegativeAdapter extends RecyclerView.Adapter<NegativeItem> {
    private final Context context;
    private final List<Negative> negatives;

    public NegativeAdapter(Context context, List<Negative> negatives){
        this.context = context;
        this.negatives = negatives;
    }

    @NonNull
    @NotNull
    @Override
    public NegativeItem onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new NegativeItem(LayoutInflater.from(context).inflate(R.layout.negative_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NegativeItem holder, int position) {
        Negative negative = negatives.get(position);
        holder.setName(negative.name);
        holder.setDuration(negative.duration);

        if (negative.imageUrl != null)
            holder.setImage(negative.imageUrl);
        else
            holder.setImage(negative.imageResource);
    }

    @Override
    public int getItemCount() {
        return negatives.size();
    }
}

class NegativeItem extends RecyclerView.ViewHolder{

    private final View itemView;
    private final TextView nameTextView;
    private final TextView durationTextView;
    private final ImageView imageImageView;

    public NegativeItem(@NonNull @NotNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        nameTextView = itemView.findViewById(R.id.negative_card_title);
        durationTextView = itemView.findViewById(R.id.negative_card_duration);
        imageImageView = itemView.findViewById(R.id.negative_card_image);
    }

    public void setName(String name){
        nameTextView.setText(name);
    }

    public void setDuration(String duration){
        durationTextView.setText(duration);
    }

    public void setImage(String imageUrl){
        Picasso.get().load(imageUrl).into(imageImageView);
    }

    public void setImage(int imageResource){
        imageImageView.setImageResource(imageResource);
    }

    public void setOnClick(Context context, Activity act){
        // TODO: redirect
        final Intent intent;

    }

}