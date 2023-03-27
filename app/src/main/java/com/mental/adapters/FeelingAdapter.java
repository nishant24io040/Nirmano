package com.mental.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mental.mentalwellness.negativeDirectPage;
import com.mental.mentalwellness.positiveDirectPage;
import com.mental.models.Feeling;
import com.mental.mentalwellness.R;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FeelingAdapter extends RecyclerView.Adapter<FeelingItem>{
    private final Context context;
    private final List<Feeling> feelings;

    public FeelingAdapter(Context context, List<Feeling> feelings) {
        this.context = context;
        this.feelings = feelings;
    }

    @NonNull
    @Override
    public FeelingItem onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new FeelingItem(LayoutInflater.from(context).inflate(R.layout.feeling_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FeelingItem holder, int position) {
        Feeling feeling = this.feelings.get(position);
        holder.setName(feeling.name);
        if (feeling.imageUrl != null)
            holder.setImage(feeling.imageUrl);
        else
            holder.setImage(feeling.imageResource);
        holder.setOnClick(context, feeling.positive,feeling.name);
    }

    @Override
    public int getItemCount() {
        return this.feelings.size();
    }
}

class FeelingItem extends RecyclerView.ViewHolder{
    private final View itemView;
    private final TextView nameTextView;
    private final ImageView imageImageView;

    public FeelingItem(@NonNull @NotNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        nameTextView = itemView.findViewById(R.id.feeling_card_text);
        imageImageView = itemView.findViewById(R.id.feeling_card_image);
    }

    public void setName(String name){
        nameTextView.setText(name);
    }

    public void setImage(String imageUrl){
        Picasso.get().load(imageUrl).into(imageImageView);
    }

    public void setImage(int imageResource){
        imageImageView.setImageResource(imageResource);
    }

    public void setOnClick(Context context, Boolean positive,String name){
        itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, positive ? positiveDirectPage.class : negativeDirectPage.class);
            intent.putExtra("name",name);
            context.startActivity(intent);
        });
    }
}