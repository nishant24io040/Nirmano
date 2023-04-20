package com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnSad.QuestionSadFragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ansrnirmano.mentalwellness.R;


public class sadquestion1 extends Fragment {

    View view;

    int a =0,b=0,c=0;
    String s;
    Button next,next2,goback;
    TextView tv1,tv2,tv3,tv4;
    SharedPreferences sd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sadquestion1, container, false);
        tv1 = view.findViewById(R.id.tvq1);
        tv2 = view.findViewById(R.id.tvq2);
        tv3 = view.findViewById(R.id.tvq3);
        tv4 = view.findViewById(R.id.tvq4);
        next = view.findViewById(R.id.btnnext);
        next2 = view.findViewById(R.id.btnnext3);
        goback = view.findViewById(R.id.goback);
        sendmasg2();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager() ;
//        try {
//            if (getArguments().getInt("option")==1){
//                Toast.makeText(getContext(), "love you nishant", Toast.LENGTH_SHORT).show();
//            }
//        }catch (Exception e){
//            Toast.makeText(getContext(), "hate you ", Toast.LENGTH_SHORT).show();
//        }

        tv1.setOnClickListener(view -> {
            tv1.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv3.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv4.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            next.setVisibility(View.VISIBLE);
            next2.setVisibility(View.GONE);
            s = tv1.getText().toString();
        });
        tv2.setOnClickListener(view -> {
            tv2.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv1.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv3.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv4.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            next.setVisibility(View.VISIBLE);
            next2.setVisibility(View.GONE);
            s = tv2.getText().toString();

        });
        tv3.setOnClickListener(view -> {
            tv3.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv1.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv4.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            next.setVisibility(View.VISIBLE);
            next2.setVisibility(View.GONE);
            s = tv3.getText().toString();
        });
        tv4.setOnClickListener(view -> {
            tv4.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv1.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv3.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            next.setVisibility(View.VISIBLE);
            next2.setVisibility(View.GONE);
            s = tv4.getText().toString();
        });
        goback.setOnClickListener(view1 -> {
            getActivity().onBackPressed();
        });
        next.setOnClickListener(view1 -> {
            sendmasg();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.setCustomAnimations(R.anim.card_flip_down,R.anim.card_flip_down);
            fragmentTransaction.replace(R.id.fram,new sadquestion2());
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

    @Override
    public void onStart() {
        super.onStart();
        sd = getContext().getSharedPreferences("option",Context.MODE_PRIVATE);
        if (sd.getInt("q",0)==1){
            changebackground(tv1);
            c=1;
            next.setVisibility(View.VISIBLE);
            next2.setVisibility(View.GONE);
            b++;
        } else if (sd.getInt("q",0)==2) {
            changebackground(tv2);
            c=2;
            next.setVisibility(View.VISIBLE);
            next2.setVisibility(View.GONE);
            b++;
        }else if (sd.getInt("q",0)==3) {
            changebackground(tv3);
            c=3;
            next.setVisibility(View.VISIBLE);
            next2.setVisibility(View.GONE);
            b++;
        }else if (sd.getInt("q",0)==4) {
            changebackground(tv4);
            c=3;
            next.setVisibility(View.VISIBLE);
            next2.setVisibility(View.GONE);
            b++;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sd = getContext().getSharedPreferences("option",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sd.edit();
        ed.putInt("q",c);
        ed.commit();

    }
    private void sendmasg (){
        Intent in = new Intent("data");
        in.putExtra("msg",1);
        in.putExtra("option",s);
        getActivity().sendBroadcast(in);
    }
    private void sendmasg2 (){
        Intent in = new Intent("data");
        in.putExtra("msg",0);
        getActivity().sendBroadcast(in);
    }

}