package com.mental.mentalwellness.ClickOnFeeling.ClickOnSad.DepthOfEmotionLevel1.Level2.Upsetfrag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mental.mentalwellness.R;


public class Sorrow extends Fragment {

   View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sorrow, container, false);
        return view;
    }
}