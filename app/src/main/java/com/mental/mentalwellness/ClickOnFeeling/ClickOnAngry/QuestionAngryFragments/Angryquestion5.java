package com.mental.mentalwellness.ClickOnFeeling.ClickOnAngry.QuestionAngryFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.mental.mentalwellness.R;


public class Angryquestion5 extends Fragment {
    View view;
    int a = 0,b=0;
    String s;
    TextView tv1,tv2,tv3,tv4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_angryquestion5, container, false);
        tv1 = view.findViewById(R.id.tvq1);
        tv2 = view.findViewById(R.id.tvq2);
        tv3 = view.findViewById(R.id.tvq3);
        tv4 = view.findViewById(R.id.tvq4);

        tv1.setOnClickListener(view -> {            s=tv1.getText().toString();

            if(b==0){
                changebackground(tv1);
                b++;
            }
            tv1.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv3.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv4.setBackground(getResources().getDrawable(R.drawable.bluebordor));
        });
        tv2.setOnClickListener(view -> {            s=tv2.getText().toString();

            if(b==0){
                changebackground(tv2);
                b++;
            }
            tv2.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv1.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv3.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv4.setBackground(getResources().getDrawable(R.drawable.bluebordor));
        });
        tv3.setOnClickListener(view -> {            s=tv3.getText().toString();

            if(b==0){
                changebackground(tv3);
                b++;
            }
            tv3.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv1.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv4.setBackground(getResources().getDrawable(R.drawable.bluebordor));
        });
        tv4.setOnClickListener(view -> {            s=tv4.getText().toString();

            if(b==0){
                changebackground(tv4);
                b++;
            }
            tv4.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv1.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv3.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
        });

        return view;
    }
    public void onDestroyView() {
        super.onDestroyView();
        sendmasg2();
    }

    private void changebackground(TextView v){
            v.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            sendmasg();
    }
    private void sendmasg (){
        Intent in = new Intent("data");
        in.putExtra("msg","1");
        in.putExtra("no",5);
        in.putExtra("option",s);
        getActivity().sendBroadcast(in);
    }
    private void sendmasg2 (){
        Intent in = new Intent("data");
        in.putExtra("msg","2");
        getActivity().sendBroadcast(in);
    }
}
