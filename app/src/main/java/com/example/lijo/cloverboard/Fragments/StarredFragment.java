package com.example.lijo.cloverboard.Fragments;

/**
 * Created by LIJO on 11/20/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lijo.cloverboard.MainActivity;
import com.example.lijo.cloverboard.OnFragmentSelectedListener;
import com.example.lijo.cloverboard.R;


public class StarredFragment extends Fragment {

    public StarredFragment(OnFragmentSelectedListener onFragmentSelectedListener) {

    }

    public StarredFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_starred, container, false);

        /*((MainActivity) getActivity()).getSupportActionBar().setTitle("Kitchen");*/




        return view;
    }

}