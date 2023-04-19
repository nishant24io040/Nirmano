package com.nirman.mentalwellness;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.FirebaseDatabase;
import com.nirman.mentalwellness.ClickOnFeeling.CommonForAllFeelings.IfContinueWithFeeling;
import com.nirman.mentalwellness.ClickOnFeeling.CommonForAllFeelings.ReasonOfFeeling;
import com.nirman.mentalwellness.TherepySection.BookASlot;

public class homefrag extends Fragment {

    View view ;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ConstraintLayout sad,angry,feared;
    CardView loved,joy,surprised;
    ImageView blurbg;
    ScrollView mainbg;

    //login problm
    //mindful problm done
    //2nd level crash problem done
    //Memory box edit problems
    Bundle bundle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homefrag, container, false);
        sad = view.findViewById(R.id.sadcons);
        angry = view.findViewById(R.id.angry);
        feared = view.findViewById(R.id.feared);
        loved = view.findViewById(R.id.loved);
        joy = view.findViewById(R.id.joyful);
        surprised = view.findViewById(R.id.surprised);
        bundle = getActivity().getIntent().getExtras();


        blurbg = view.findViewById(R.id.blurbg);
        mainbg = view.findViewById(R.id.scrollView2);
        SharedPreferences sd = getContext().getSharedPreferences("noti", Context.MODE_PRIVATE);
        if (sd.getString("yes", "").equals("callme")){
            slotreminder();
        }
        sad.setOnClickListener(view1 -> {
            sadfeeling();
        });
        angry.setOnClickListener(view1 -> {
            angryfeeling();
        });
        feared.setOnClickListener(view1 -> {
            Feared();
        });
        TempModal tempModal = new TempModal(" "," "," "," "," "," ");
        joy.setOnClickListener(view1 -> {
//            database.getReference("Mindfulness").child("Fear").child("Feared").setValue(tempModal);
//            database.getReference("Mindfulness").child("Fear").child("Frightened").setValue(tempModal);
//            database.getReference("Mindfulness").child("Fear").child("Helpless").setValue(tempModal);
//            database.getReference("Mindfulness").child("Fear").child("Panic").setValue(tempModal);
//            database.getReference("Mindfulness").child("Fear").child("Hysterical").setValue(tempModal);
//            database.getReference("Mindfulness").child("Fear").child("Inferior").setValue(tempModal);
//            database.getReference("Mindfulness").child("Fear").child("Incomplete").setValue(tempModal);
//            database.getReference("Mindfulness").child("Fear").child("Worried").setValue(tempModal);
//            database.getReference("Mindfulness").child("Fear").child("Anxious").setValue(tempModal);
//            database.getReference("Mindfulness").child("Fear").child("Humiliate").setValue(tempModal);
//            database.getReference("Mindfulness").child("Fear").child("Dread").setValue(tempModal);
//            database.getReference("Mindfulness").child("Fear").child("Scared").setValue(tempModal);
//            database.getReference("Mindfulness").child("Fear").child("Terror").setValue(tempModal);
//            database.getReference("Mindfulness").child("Fear").child("Insecure").setValue(tempModal);
//            database.getReference("Mindfulness").child("Fear").child("Nervous").setValue(tempModal);
//            database.getReference("Mindfulness").child("Fear").child("Horror").setValue(tempModal);
            Intent intent = new Intent(getContext(),JoyActivity.class);
            startActivity(intent);
        });
        loved.setOnClickListener(view1 -> {

            Intent intent = new Intent(getContext(),LovedActivity.class);
            startActivity(intent);
        });
        surprised.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(),Surprised.class);
            startActivity(intent);
        });
        return view;
    }

    private void slotreminder(){
        Button book,decline,later;

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.slotbookreminder);

        book = dialog.findViewById(R.id.button9);
        decline = dialog.findViewById(R.id.button8);
        later = dialog.findViewById(R.id.button7);
        dialog.show();

        book.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), BookASlot.class);
            getContext().startActivity(intent);
        });
        decline.setOnClickListener(v -> {
            SharedPreferences sd = getContext().getSharedPreferences("noti", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = sd.edit();
            ed.remove("yes");
            ed.commit();
            dialog.dismiss();
        });
        later.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }
    private void sadfeeling(){

        Button continueWithFeeling,goInDepth;

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.custom_dialog_layout);

        continueWithFeeling = dialog.findViewById(R.id.btncontinue);
        goInDepth = dialog.findViewById(R.id.knowmore);

        mainbg.setVisibility(View.GONE);
        blurbg.setVisibility(View.VISIBLE);

        dialog.show();

        continueWithFeeling.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), IfContinueWithFeeling.class);

            SharedPreferences depth = getContext().getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("feeling","sad");
            ed.putString("depth","continue");
            ed.commit();

            intent.putExtra("feeling","sad");
            intent.putExtra("depth","continue");

            startActivity(intent);
        });

        goInDepth.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), ReasonOfFeeling.class);
            SharedPreferences depth = getContext().getSharedPreferences("datafile",MODE_PRIVATE);
            SharedPreferences.Editor ed = depth.edit();
            ed.putString("depth","depth");
            ed.putString("feeling","sad");
            ed.commit();
            intent.putExtra("feeling","sad");
            startActivity(intent);
        });

        dialog.setOnDismissListener(dialogInterface -> {
            mainbg.setVisibility(View.VISIBLE);
            blurbg.setVisibility(View.GONE);

        });
    }
    private void angryfeeling(){

        Button btncontinueWithFeeling,btngoInDepth;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.custom_dialog_layout);

        btncontinueWithFeeling = dialog.findViewById(R.id.btncontinue);
        btngoInDepth = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);

        firstbody.setText(R.string.angryfirstbody);
        secbody.setText(R.string.angrysecbody);
        title.setText("Angry");
        title.setBackgroundColor(getResources().getColor(R.color.angry));

        mainbg.setVisibility(View.GONE);
        blurbg.setVisibility(View.VISIBLE);

        dialog.show();
        btncontinueWithFeeling.setText("Continue With Angry");
        btncontinueWithFeeling.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), IfContinueWithFeeling.class);
            SharedPreferences sd;
            sd = getContext().getSharedPreferences("datafile", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = sd.edit();
            ed.putString("feeling","angry");
            ed.commit();
            intent.putExtra("feeling","angry");
            startActivity(intent);
        });

        btngoInDepth.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), ReasonOfFeeling.class);
            intent.putExtra("feeling","angry");
            startActivity(intent);
        });
        dialog.setOnDismissListener(dialogInterface -> {
            mainbg.setVisibility(View.VISIBLE);
            blurbg.setVisibility(View.GONE);

        });
    }
    private void Feared(){

        Button btncontinueWithFeeling,btngoInDepth;
        TextView title,firstbody,secbody;

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.custom_dialog_layout);

        btncontinueWithFeeling = dialog.findViewById(R.id.btncontinue);
        btngoInDepth = dialog.findViewById(R.id.knowmore);
        title = dialog.findViewById(R.id.feeling);
        firstbody = dialog.findViewById(R.id.firstdiscription);
        secbody = dialog.findViewById(R.id.secbody);

        firstbody.setText(R.string.fearfirstbody);
        secbody.setText(R.string.fearsecbody);
        title.setText("FEAR");
        title.setBackgroundColor(getResources().getColor(R.color.fear));

        mainbg.setVisibility(View.GONE);
        blurbg.setVisibility(View.VISIBLE);

        dialog.show();

        btncontinueWithFeeling.setText("Continue With Fear");
        btncontinueWithFeeling.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), IfContinueWithFeeling.class);
            SharedPreferences sd;
            sd = getContext().getSharedPreferences("datafile", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = sd.edit();
            ed.putString("feeling","fear");
            ed.commit();
            intent.putExtra("feeling","feared");
            startActivity(intent);
        });

        btngoInDepth.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), ReasonOfFeeling.class);
            intent.putExtra("feeling","feared");
            startActivity(intent);
        });
        dialog.setOnDismissListener(dialogInterface -> {
            mainbg.setVisibility(View.VISIBLE);
            blurbg.setVisibility(View.GONE);

        });
    }
}