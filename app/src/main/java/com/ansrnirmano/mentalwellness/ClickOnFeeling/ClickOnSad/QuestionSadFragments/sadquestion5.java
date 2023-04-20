package com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnSad.QuestionSadFragments;

import static android.content.Context.MODE_PRIVATE;

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

import com.ansrnirmano.mentalwellness.ClickOnFeeling.CommonForAllFeelings.AfterLastQuestionActivity;
import com.ansrnirmano.mentalwellness.R;


public class sadquestion5 extends Fragment {
    View view;
    String s;
    int a = 0,b=0,c=0;
    TextView tv1,tv2,tv3,tv4;
    Button next,next2,goback;
    SharedPreferences sd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sadquestion5, container, false);
        tv1 = view.findViewById(R.id.tvq1);
        tv2 = view.findViewById(R.id.tvq2);
        tv3 = view.findViewById(R.id.tvq3);
        tv4 = view.findViewById(R.id.tvq4);
        next = view.findViewById(R.id.btnnext10);
        next2 = view.findViewById(R.id.btnnext11);
        goback = view.findViewById(R.id.goback6);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager() ;

        tv1.setOnClickListener(view -> {

                next.setVisibility(View.VISIBLE);
                next2.setVisibility(View.GONE);
            tv1.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv3.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv4.setBackground(getResources().getDrawable(R.drawable.bluebordor));
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

            next.setVisibility(View.VISIBLE);
            next2.setVisibility(View.GONE);
            tv3.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv1.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv4.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            s = tv3.getText().toString();

        });
        tv4.setOnClickListener(view -> {
            tv4.setBackground(getResources().getDrawable(R.drawable.onselectbg));
            tv1.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv3.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            tv2.setBackground(getResources().getDrawable(R.drawable.bluebordor));
            s = tv4.getText().toString();
                next.setVisibility(View.VISIBLE);
                next2.setVisibility(View.GONE);
                c=4;
                b++;

        });
        goback.setOnClickListener(view1 -> {
            sendmasg2();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fram,new sadquestion4());
            fragmentTransaction.commit();
        });
        next.setOnClickListener(view1 -> {
            sendmasg();
            Intent intent = new Intent(getContext(), AfterLastQuestionActivity.class);
            SharedPreferences depth = getContext().getSharedPreferences("datafile",MODE_PRIVATE);
            String s = depth.getString("depth","");
            intent.putExtra("depth",s);
            intent.putExtra("feeling","sad");
//            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });

        return view;
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        sd = getContext().getSharedPreferences("option",Context.MODE_PRIVATE);
//        if (sd.getInt("q5",0)==1){
//            changebackground(tv1);
//            c=1;
//            next.setVisibility(View.VISIBLE);
//            next2.setVisibility(View.GONE);
//            b++;
//        } else if (sd.getInt("q",0)==2) {
//            changebackground(tv2);
//            c=2;
//            next.setVisibility(View.VISIBLE);
//            next2.setVisibility(View.GONE);
//            b++;
//        }else if (sd.getInt("q",0)==3) {
//            changebackground(tv3);
//            c=3;
//            next.setVisibility(View.VISIBLE);
//            next2.setVisibility(View.GONE);
//            b++;
//        }else if (sd.getInt("q",0)==4) {
//            changebackground(tv4);
//            c=3;
//            next.setVisibility(View.VISIBLE);
//            next2.setVisibility(View.GONE);
//            b++;
//        }
//    }
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
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        sd = getContext().getSharedPreferences("option", Context.MODE_PRIVATE);
//        SharedPreferences.Editor ed = sd.edit();
//        ed.putInt("q5",c);
//        ed.commit();
//    }

    private void sendmasg (){
        Intent in = new Intent("data");
        in.putExtra("msg",5);
        in.putExtra("option",s);
        getActivity().sendBroadcast(in);
    }
    private void sendmasg2 (){
        Intent in = new Intent("data");
        in.putExtra("msg",4);
        getActivity().sendBroadcast(in);
    }

}