package com.mental.mentalwellness.ClickOnFeeling.ClickOnSad.QuestionSadFragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mental.mentalwellness.ClickOnFeeling.CommonForAllFeelings.ReasonOfFeeling;
import com.mental.mentalwellness.R;


public class sadquestion2 extends Fragment {
    View view;
    EditText etv;
    Button next,next2,goback;
    SharedPreferences sd;
    String s;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sadquestion2, container, false);
        etv=view.findViewById(R.id.etv);
        next = view.findViewById(R.id.btnnext5);
        next2 = view.findViewById(R.id.btnnext4);
        goback = view.findViewById(R.id.goback3);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager() ;

        etv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                next.setVisibility(View.VISIBLE);
                next2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        goback.setOnClickListener(view1 -> {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fram,new sadquestion1());
            fragmentTransaction.commit();
            sendmasg2();
        });
        next.setOnClickListener(view1 -> {
            s = etv.getText().toString();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fram,new sadquestion3());
            fragmentTransaction.commit();
            sendmasg();
        });

        return view;
    }
    public void onStart() {
        super.onStart();
        sd = getContext().getSharedPreferences("option", Context.MODE_PRIVATE);
        if (sd.contains("con")){
            etv.setText(sd.getString("con",""));
        }

    }

        @Override
    public void onDestroy() {
        super.onDestroy();
        sd = getContext().getSharedPreferences("option", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sd.edit();
        ed.putString("con",etv.getText().toString());
        ed.commit();

    }

    private void sendmasg (){
        Intent in = new Intent("data");
        in.putExtra("msg",2);
        in.putExtra("option",s);
        getActivity().sendBroadcast(in);
    }
    private void sendmasg2 (){
        Intent in = new Intent("data");
        in.putExtra("msg",1);
        getActivity().sendBroadcast(in);
    }
}