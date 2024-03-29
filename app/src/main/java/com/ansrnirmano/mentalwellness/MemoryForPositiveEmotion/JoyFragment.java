package com.ansrnirmano.mentalwellness.MemoryForPositiveEmotion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ansrnirmano.adapters.MemoryAdapter;
import com.ansrnirmano.mentalwellness.ModalforMemoryBox;
import com.ansrnirmano.mentalwellness.R;

import java.util.ArrayList;
import java.util.Collections;


public class JoyFragment extends Fragment {
    RecyclerView rcview;
    ArrayList<ModalforMemoryBox> list;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    MemoryAdapter adapter;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_joy, container, false);
        rcview = view.findViewById(R.id.joyrc);
        list = new ArrayList<>();
        rcview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        adapter = new MemoryAdapter(getContext(),list,1);
        rcview.setAdapter(adapter);

        database.getReference().child("Memories").child(mAuth.getUid()).child("joy")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    list.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ModalforMemoryBox user2 = dataSnapshot.getValue(ModalforMemoryBox.class);
                        list.add(user2);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}