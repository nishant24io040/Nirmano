package com.ansrnirmano.mentalwellness.ClickOnFeeling.ClickOnFear.DepthOfFearlevel1.Level2.Horrorfrag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ansrnirmano.mentalwellness.R;


public class Dread extends Fragment {

   View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dread, container, false);
        return view;
    }
}