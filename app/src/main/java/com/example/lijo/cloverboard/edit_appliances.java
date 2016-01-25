package com.example.lijo.cloverboard;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class edit_appliances extends AppCompatActivity {
    private String[] state1 = { "Dimmable", "Type 1", "Type 2"};
    private Spinner spin1;
    @Bind(R.id.edit_appliance_toolbar)
    Toolbar toolbar;

    @Bind(R.id.edit_appliance_collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.edit_appliance_recyclerView)
    RecyclerView recyclerView;

    private RecyclerViewEditApp adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appliances);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
     // collapsingToolbarLayout.setTitle("Bedroom Light");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitleEnabled(false);
        getSupportActionBar().setTitle("Edit Appliances");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeViewsAdapter();
        loadData();

      /*  spin1=(Spinner)

                findViewById(R.id.spinner);

        ArrayAdapter<String> adapter_state1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, state1);
        adapter_state1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter_state1);*/
    }

    private void loadData() {
        List<Radio> radioList = new ArrayList<>();
        radioList.add(new Radio("Bedroom Fan",R.drawable.fan));
        adapter.setRadioList(radioList);
    }

    private void initializeViewsAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerViewEditApp(getApplicationContext());
        recyclerView.setAdapter(adapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_appliances, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
