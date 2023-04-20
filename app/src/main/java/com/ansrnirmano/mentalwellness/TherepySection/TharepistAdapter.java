package com.ansrnirmano.mentalwellness.TherepySection;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ansrnirmano.mentalwellness.R;

import java.util.ArrayList;

public class TharepistAdapter extends RecyclerView.Adapter<TharepistAdapter.Holder> {
    Context context;
    ArrayList<ModalForTharepist> list;

    public TharepistAdapter(Context context, ArrayList<ModalForTharepist> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TharepistAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.therapist_row, parent, false);
        return new TharepistAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TharepistAdapter.Holder holder, int position) {
        ModalForTharepist item = list.get(position);
//        holder.title.setText(item.getTitle());
//        holder.experience.setText("Experience"+item.getExperience());
//        holder.expect.setText("Expect of xxxxxxxxx");
//        holder.price.setText("Rs."+item.getPrice());
//        holder.language.setText("Languages "+item.getLanguages());
//        Glide.with(context).load(item.getImage()).into(holder.img);
        holder.btn.setOnClickListener(v -> {
            Intent intent = new Intent(context,profile_page1.class);
            intent.putExtra("qualification",item.qualification);
            intent.putExtra("price",item.price);
            intent.putExtra("offerTherapy",item.offerTherapy);
            intent.putExtra("languages",item.languages);
            intent.putExtra("experience",item.experience);
            intent.putExtra("expect",item.expect);
            intent.putExtra("about",item.about);
            intent.putExtra("title",item.title);
            intent.putExtra("img",item.image);
            intent.putExtra("name",item.name);

            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class Holder extends RecyclerView.ViewHolder{
        TextView title,price,experience,language,expect;
        Button btn;
        ImageView img;
        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            experience = itemView.findViewById(R.id.experience);
            language = itemView.findViewById(R.id.lang);
            expect = itemView.findViewById(R.id.expect);
            img = itemView.findViewById(R.id.profile);
            btn = itemView.findViewById(R.id.view_profile);
        }
    }
}
