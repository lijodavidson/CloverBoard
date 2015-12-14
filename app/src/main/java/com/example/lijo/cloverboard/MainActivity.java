package com.example.lijo.cloverboard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.lijo.cloverboard.Fragments.BedroomFragment;
import com.example.lijo.cloverboard.Fragments.LivingFragment;
import com.example.lijo.cloverboard.Fragments.StarredFragment;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;


public class MainActivity extends AppCompatActivity  {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    final Context context = this;
    EditText Dialougeedit;
    final      Firebase ref = new Firebase("https://cloverboard.firebaseio.com");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }

        setupNavigationDrawerContent(navigationView);
        //displays first in nav view

        setFragment(0);




        ref.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    // user is logged in
                } else {
                    // user is not logged in
                }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_bedroom:
                                menuItem.setChecked(true);
                                setFragment(0);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_starred:
                                menuItem.setChecked(true);
                                setFragment(1);

                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;


                            case R.id.item_navigation_living_room:
                                menuItem.setChecked(true);
                                setFragment(2);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;


                            case R.id.item_navigation_drawer_logout:
                                menuItem.setChecked(true);
                                ref.unauth();
                                Controller.clearCache(getApplicationContext());
                                Intent z = new Intent(getApplicationContext(), login.class);
                                startActivity(z);
                                finish();



                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                        /*    case R.id.item_navigation_drawer_settings:
                                menuItem.setChecked(true);

                                Toast.makeText(MainActivity.this, "Launching " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                                startActivity(intent);
                                return true;*/

                            case R.id.item_navigation_addnew:
                                menuItem.setChecked(true);

                                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                                View subView = inflater.inflate(R.layout.dilog_item, null);
                                final EditText subEditText = (EditText)subView.findViewById(R.id.editdilog);





                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this,R.style.AppCompatAlertDialogStyle);
                                builder.setTitle("Add Room");

                                builder.setMessage("Press the Configuration Button on the CloverBoard ");
                                builder.setPositiveButton("ADD", null);
                                builder.setNegativeButton("CANCEL", null);
                                builder.setView(subView);
                                builder.show();






                            case R.id.item_navigation_drawer_help_and_feedback:
                                menuItem.setChecked(true);
                                Toast.makeText(MainActivity.this, menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                        }
                        return true;
                    }
                });
    }


    public void setFragment(int position) {
        android.support.v4.app.FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        switch (position) {
            case 0:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                BedroomFragment bedroomFragment = new BedroomFragment();
                fragmentTransaction.replace(R.id.fragment, bedroomFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                StarredFragment starredFragment = new StarredFragment();
                fragmentTransaction.replace(R.id.fragment, starredFragment);
                fragmentTransaction.commit();
                break;


            case 2:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
               /* StarredFragment starredFragment = new StarredFragment();*/
                LivingFragment livingFragment=new LivingFragment();
                fragmentTransaction.replace(R.id.fragment, livingFragment);
               /* fragmentTransaction.replace(R.id.fragment, starredFragment);*/
                fragmentTransaction.commit();
                break;



        }
    }



}


