package com.nirman.mentalwellness.TherepySection;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nirman.adapters.MemoryAdapter;
import com.nirman.mentalwellness.ModalforMemoryBox;
import com.nirman.mentalwellness.R;

import java.util.ArrayList;
import java.util.Collections;

public class TherepyActivity extends AppCompatActivity {


    Button reschedule;
    RecyclerView rcv;
    TharepistAdapter adapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ArrayList<ModalForTharepist> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therepy);
        list = new ArrayList<>();
        rcv = findViewById(R.id.rcvt);
        rcv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapter = new TharepistAdapter(TherepyActivity.this,list);
        rcv.setAdapter(adapter);

        database.getReference().child("Therapist").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ModalForTharepist user2 = dataSnapshot.getValue(ModalForTharepist.class);
                        list.add(user2);
                    }
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        }

    private void opendialog() {
        Button rescadule,decline;

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.reminder);

        rescadule = dialog.findViewById(R.id.button9);
        decline = dialog.findViewById(R.id.button8);
        dialog.show();

        decline.setOnClickListener(v -> {
            dialog.dismiss();
        });
        rescadule.setOnClickListener(v -> {
            Intent intent = new Intent(this,resudule.class);
            startActivity(intent);
        });

    }
}