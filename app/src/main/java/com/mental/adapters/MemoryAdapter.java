package com.mental.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.mental.mentalwellness.EditMemory;
import com.mental.mentalwellness.ModalforMemoryBox;
import com.mental.mentalwellness.R;
import com.mental.mentalwellness.SavedMemory;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.Holder> {

    Context context;
    ArrayList<ModalforMemoryBox> list;
    int n;
    String en;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public MemoryAdapter(Context context, ArrayList<ModalforMemoryBox> list, int n) {
        this.context = context;
        this.list = list;
        this.n = n;
    }

    @NonNull
    @Override
    public MemoryAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (n==1) {
            en = "joy";
            View view = LayoutInflater.from(context).inflate(R.layout.joy_memory_row, parent, false);
            return new Holder(view);
        }
        else if (n==2) {
            en = "loved";
            View view = LayoutInflater.from(context).inflate(R.layout.loved_memory_row, parent, false);
            return new Holder(view);
        }
        else if (n==3) {
            en = "surprised";
            View view = LayoutInflater.from(context).inflate(R.layout.surprised_memory_row, parent, false);
            return new Holder(view);
        }
        else {
            return null;
        }    }

    @Override
    public void onBindViewHolder(@NonNull MemoryAdapter.Holder holder, int position) {
        ModalforMemoryBox memoryBox = list.get(position);

        AtomicInteger i= new AtomicInteger();
        holder.title.setText(memoryBox.getTitle());
        holder.date.setText(memoryBox.getDate());
        holder.feeling.setText(memoryBox.getFeeling());
        holder.option.setOnClickListener(view -> {
            i.set(1);
            holder.optionmenu.setVisibility(View.VISIBLE);
        });
        holder.cwd.setOnClickListener(view -> {
            if (i.get() != 1) {
                Intent intent = new Intent(context, SavedMemory.class);
                intent.putExtra("title",memoryBox.getTitle());
                intent.putExtra("date",memoryBox.getDate());
                intent.putExtra("image",memoryBox.getImage());
                intent.putExtra("body",memoryBox.getBody());
                intent.putExtra("feeling",memoryBox.getFeeling());

                context.startActivity(intent);
            }
            else {
                holder.optionmenu.setVisibility(View.GONE);
                i.set(0);
            }

        });
        holder.edit.setOnClickListener(view -> {
            Intent intent = new Intent(context, EditMemory.class);
            intent.putExtra("photo",memoryBox.getImage());
            intent.putExtra("title",memoryBox.getTitle());
            intent.putExtra("date",memoryBox.getDate());
            intent.putExtra("body",memoryBox.getBody());
            intent.putExtra("feeling",memoryBox.getFeeling());
            intent.putExtra("key",memoryBox.getKey());
            if (n==1){
                intent.putExtra("emotion","joy");
            }
            else if (n==2){
                intent.putExtra("emotion","loved");
            }
            else if (n==3){
                intent.putExtra("emotion","surprised");
            }
            context.startActivity(intent);
        });
        holder.delete.setOnClickListener(view -> {
            if (list.size()==1){
                database.getReference("Memories").child(mAuth.getCurrentUser().getUid())
                        .child(en).child(memoryBox.getKey()).removeValue();
                holder.itemView.setVisibility(View.INVISIBLE);
                notifyDataSetChanged();
            }
            database.getReference("Memories").child(mAuth.getCurrentUser().getUid())
                    .child(en).child(memoryBox.getKey()).removeValue();
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{
        TextView title,date,feeling,delete,edit;
        ImageView option;
        LinearLayout optionmenu;
        ConstraintLayout cwd;
        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titl);
            date = itemView.findViewById(R.id.date1);
            option = itemView.findViewById(R.id.optionbtn);
            feeling = itemView.findViewById(R.id.feeelin);
            optionmenu = itemView.findViewById(R.id.optionmanu);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
            cwd = itemView.findViewById(R.id.cwd);



        }
    }
}
