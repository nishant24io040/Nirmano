package com.mental.mentalwellness.ClickOnFeeling.ClickOnFear.QuestionFearFragments;

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

import com.mental.mentalwellness.R;


public class Fearquestion3 extends Fragment {
    EditText et;
    View view;
    String s;
    SharedPreferences sd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fearquestion3, container, false);
        et = view.findViewById(R.id.typehere);
        et.addTextChangedListener(new TextWatcher() {
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
        sd = getContext().getSharedPreferences("option2", Context.MODE_PRIVATE);
        if (sd.contains("con1")){
            et.setText(sd.getString("con1",""));
            sendmasg();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sd = getContext().getSharedPreferences("option2", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sd.edit();
        ed.putString("con1",et.getText().toString());
        ed.commit();
//        sendmasg2();
    }
    private void sendmasg (){
        Intent in = new Intent("data1");
        in.putExtra("msg","1");
        in.putExtra("no",3);
        s=et.getText().toString();
        in.putExtra("option",s);
        getActivity().sendBroadcast(in);
    }
    private void sendmasg2 (){
        Intent in = new Intent("data1");
        in.putExtra("msg","2");
        getActivity().sendBroadcast(in);
    }

}