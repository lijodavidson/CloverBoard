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
import com.example.lijo.cloverboard.R;


public class StarredFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_starred, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("starred");

        Button buttonChangeText = (Button) view.findViewById(R.id.buttonFragmentStarred);

        final TextView textViewstarredFragment = (TextView) view.findViewById(R.id.textViewStFragment);

        buttonChangeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textViewstarredFragment.setText("This is the starred Fragment");
                textViewstarredFragment.setTextColor(getResources().getColor(R.color.md_yellow_800));

            }
        });

        return view;
    }

}