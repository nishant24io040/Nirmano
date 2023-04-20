package com.ansrnirmano.mentalwellness.TherepySection;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ansrnirmano.mentalwellness.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class TherepyActivity extends AppCompatActivity {


    Button reschedule;
    CardView upsession;
    RecyclerView rcv;
    TharepistAdapter adapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ArrayList<ModalForTharepist> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therepy);
        upsession = findViewById(R.id.cardviewm1);
        list = new ArrayList<>();
        rcv = findViewById(R.id.rcvt);
        rcv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new TharepistAdapter(TherepyActivity.this,list);
        rcv.setAdapter(adapter);

        SharedPreferences sd = this.getSharedPreferences("datafile1", Context.MODE_PRIVATE);
        if (sd.getString("payment", "").equals("complete")){
            upsession.setVisibility(View.VISIBLE);
        }
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