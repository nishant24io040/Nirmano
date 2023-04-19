package com.nirman.mentalwellness.ClickOnFeeling.ClickOnAngry.QuestionAngryFragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.nirman.mentalwellness.R;


public class Angryquestion2 extends Fragment {

    View view;
    EditText etv;
    String s;


    SharedPreferences sd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_angryquestion2, container, false);
        etv=view.findViewById(R.id.etv);
        etv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                sendmasg();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        sd = getContext().getSharedPreferences("option1", Context.MODE_PRIVATE);
        if (sd.contains("con1")){
            etv.setText(sd.getString("con1",""));
            sendmasg();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sd = getContext().getSharedPreferences("option1", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sd.edit();
        ed.putString("con1",etv.getText().toString());
        ed.commit();
        sendmasg2();
    }
    private void sendmasg (){
        Intent in = new Intent("data");
        in.putExtra("msg","1");
        in.putExtra("no",2);
        s=etv.getText().toString();
        in.putExtra("option",s);
        getActivity().sendBroadcast(in);
    }
    private void sendmasg2 (){
        Intent in = new Intent("data");
        in.putExtra("msg","2");
        getActivity().sendBroadcast(in);
    }
}