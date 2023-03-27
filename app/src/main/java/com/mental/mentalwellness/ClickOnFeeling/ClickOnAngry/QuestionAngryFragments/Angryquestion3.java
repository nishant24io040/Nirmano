package com.mental.mentalwellness.ClickOnFeeling.ClickOnAngry.QuestionAngryFragments;

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
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.mental.mentalwellness.R;


public class Angryquestion3 extends Fragment {
    EditText et;
    int a,n,b=0;
    TextView tv1,tv2;
    View view;
    String s;
    SharedPreferences sd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_angryquestion3, container, false);
        tv1 = view.findViewById(R.id.tvq1);
        tv2 = view.findViewById(R.id.tvq2);
        et = view.findViewById(R.id.typehere);
        tv1.setOnClickListener(view -> {
            if(b==0){
                if (n == 1) {
                    n = 2;
                } else {
                    n = 1;
                }
                et.setVisibility(View.VISIBLE);
                changebackground(tv1);
                b++;
            }
            et.setVisibility(View.VISIBLE);
            tv1.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
        });

        tv2.setOnClickListener(view -> {
            s="no";
            if(b==0){
                if (n != 1) {
                    et.setVisibility(View.INVISIBLE);
                    changebackground(tv2);
                }
                b++;
            }
            et.setVisibility(View.INVISIBLE);
            tv2.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv1.setBackground(getResources().getDrawable(R.drawable.bluebordor));
        });
//        if (!s.equals("no")){
//            s=et.getText().toString();
//        }
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                s=et.getText().toString();
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
        if (sd.contains("con0")){
            et.setText(sd.getString("con0",""));
            sendmasg();
        }
    }

    private void changebackground(TextView v){
        if(a==2){
            a=3;
            v.setBackground(getResources().getDrawable(R.drawable.bluebordor));
        }
        else {
            a=2;
            v.setBackground(getResources().getDrawable(R.drawable.onselectbg));

            sendmasg();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sd = getContext().getSharedPreferences("option1", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sd.edit();
        ed.putString("con0",et.getText().toString());
        ed.commit();
//        sendmasg2();
    }
    private void sendmasg (){
        Intent in = new Intent("data");
        in.putExtra("msg","1");
        in.putExtra("no",3);
        in.putExtra("option",s);
        getActivity().sendBroadcast(in);

    }
    private void sendmasg2 (){
        Intent in = new Intent("data");
        in.putExtra("msg","2");
        getActivity().sendBroadcast(in);
    }
}