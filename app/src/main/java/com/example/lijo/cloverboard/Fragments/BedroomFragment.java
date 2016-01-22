package com.example.lijo.cloverboard.Fragments;

/**
 * Created by LIJO on 11/20/2015.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.lijo.cloverboard.CardItemModel;
import com.example.lijo.cloverboard.MainActivity;
import com.example.lijo.cloverboard.OnFragmentSelectedListener;
import com.example.lijo.cloverboard.R;
import com.example.lijo.cloverboard.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class BedroomFragment extends Fragment {


    /*private List<CardItemModel> cardItems = new ArrayList<>(30);*/
    /*private MainActivity mainActivity;*/
    private MainActivity mainActivity;
    protected BedroomFragment bedroomFragment;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private View view;
    Context thiscontext;
    /*private OnFragmentSelectedListener onFragmentSelectedListener;*/
    public BedroomFragment(OnFragmentSelectedListener onFragmentSelectedListener) {
      /*  this.onFragmentSelectedListener = onFragmentSelectedListener;*/

    }

    public BedroomFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        thiscontext = container.getContext();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bedroom, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);

        setupRecyclerView();

     /*   ((MainActivity) getActivity()).getSupportActionBar().setTitle("Bedroom new");*/

        /*onFragmentSelectedListener.onFragmentSelected(1);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bedroom, container, false);*/



        return view;
    }

    private void setupRecyclerView(){
       /* recyclerView.setLayoutManager(new LinearLayoutManager(thiscontext));
        recyclerView.setHasFixedSize(true);

        recyclerAdapter = new RecyclerAdapter(carditemmodel);
        recyclerView.setAdapter(recyclerAdapter);*/
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView
                .setLayoutManager(new LinearLayoutManager(getActivity()));
        initializeCardItemList();

    }

    private void initializeCardItemList() {

        ArrayList <CardItemModel> carditemmodel=new ArrayList<>();
        carditemmodel.add(new CardItemModel("lijo", "Light"));
        carditemmodel.add(new CardItemModel("sijo","Fan"));
        carditemmodel.add(new CardItemModel("sijo", "Light"));
RecyclerAdapter adapter=new RecyclerAdapter(carditemmodel);
        recyclerView.setAdapter(adapter);


    }

    /* CardItemModel cardItemModel;
            String[] cardTitles = getResources().getStringArray(R.array.card_titles);
            String[] cardContents = getResources().getStringArray(R.array.card_contents);
            final int length = cardTitles.length;
            for(int i=0;i<length;i++){
                cardItemModel = new CardItemModel(cardTitles[i],cardContents[i]);
                cardItems.add(cardItemModel);
            }*//*

    }
*/
   /* public void addItem(String title,String content){
        recyclerAdapter.cardItems.add(new CardItemModel(title,content));
        recyclerAdapter.notifyDataSetChanged();
    }*/

    /*public void removeItem(){
        recyclerAdapter.cardItems.remove(recyclerAdapter.cardItems.size() - 1);
        recyclerAdapter.notifyDataSetChanged();
    }
*/

}