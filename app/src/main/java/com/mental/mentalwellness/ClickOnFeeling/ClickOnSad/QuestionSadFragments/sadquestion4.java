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
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mental.mentalwellness.R;


public class sadquestion4 extends Fragment {

    EditText et;
    int a,n,b=0;
    TextView tv1,tv2;
    Button next,next2,goback;
    String q,s;
    View view;
    SharedPreferences sd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sadquestion4, container, false);
        tv1 = view.findViewById(R.id.tvq1);
        tv2 = view.findViewById(R.id.tvq2);
        et = view.findViewById(R.id.typehere);
        next2 = view.findViewById(R.id.btnnext8);
        next = view.findViewById(R.id.btnnext9);
        goback = view.findViewById(R.id.goback5);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager() ;

        tv1.setOnClickListener(view -> {
            et.setVisibility(View.VISIBLE);
            q="yes";
            tv1.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            next.setVisibility(View.VISIBLE);
            next2.setVisibility(View.INVISIBLE);
        });

        tv2.setOnClickListener(view -> {
            et.setVisibility(View.INVISIBLE);
            q="no";
            s = "no";
            n=9;
            tv2.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv1.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            next.setVisibility(View.VISIBLE);
            next2.setVisibility(View.INVISIBLE);
        });
        et.addTextChangedListener(new TextWatcher() {
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
            sendmasg2();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fram,new sadquestion3());
            fragmentTransaction.commit();
        });
        next.setOnClickListener(view1 -> {
            if (n!=9){
                s = et.getText().toString();
            }
            sendmasg();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fram,new sadquestion5());
            fragmentTransaction.commit();
        });
        return view;
    }
    private void changebackground(TextView v){

        if(a==2){
            a=3;
            v.setBackground(getResources().getDrawable(R.drawable.bluebordor));
        }
        else {
            a=2;
            v.setBackground(getResources().getDrawable(R.drawable.onselectbg));
        }
    }
    public void onStart() {
        super.onStart();
        sd = getContext().getSharedPreferences("option", Context.MODE_PRIVATE);
        if (sd.getString("q4", "").equals("yes")){
            if(b==0){
                et.setVisibility(View.VISIBLE);
                q="yes";
                changebackground(tv1);
                b++;
                et.setText(sd.getString("con4",""));
            }else if (sd.getString("q4", "").equals("no")) {
                if(b==0){
                    et.setVisibility(View.INVISIBLE);
                    changebackground(tv2);
                    q="no";
                    next.setVisibility(View.VISIBLE);
                    next2.setVisibility(View.INVISIBLE);
                    b++;
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sd = getContext().getSharedPreferences("option", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sd.edit();
        ed.putString("q4",q);
        ed.putString("con4",et.getText().toString());
        ed.commit();

    }
    private void sendmasg (){
        Intent in = new Intent("data");
        in.putExtra("msg",4);
        in.putExtra("option",s);
        getActivity().sendBroadcast(in);
    }
    private void sendmasg2 (){
        Intent in = new Intent("data");
        in.putExtra("msg",3);
        getActivity().sendBroadcast(in);
    }
}