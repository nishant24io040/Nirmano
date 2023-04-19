package com.nirman.mentalwellness.ClickOnFeeling.ClickOnAngry.DepthOfAngryLavel1.Level2.Ragefrag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.nirman.mentalwellness.R;


public class AGGRAVATED extends Fragment {

   View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_aggraveted, container, false);
        return view;
    }
}