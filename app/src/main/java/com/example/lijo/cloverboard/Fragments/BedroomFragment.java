package com.example.lijo.cloverboard.Fragments;

/**
 * Created by LIJO on 11/20/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lijo.cloverboard.MainActivity;
import com.example.lijo.cloverboard.R;


public class BedroomFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_bedroom, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Bedroom new");



        return view;
    }

}