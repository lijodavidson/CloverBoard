package com.example.lijo.cloverboard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.lijo.cloverboard.Fragments.BedroomFragment;
import com.example.lijo.cloverboard.Fragments.LivingFragment;
import com.example.lijo.cloverboard.Fragments.StarredFragment;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    private TabLayout tabLayout;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView imageView;
    private ViewPager viewPager;
    final Context context = this;
    EditText Dialougeedit;
    private ViewPagerAdapter adapter;
    private MenuItem menuItem;
    private String temp = "27";
    private String text = temp;
    private int t = 27;
    private NavigationView navigationView;
    long current_time;
    private boolean isLongPressed = false;
    Button blue_button;
  private   Button button;
   /* final Firebase ref = new Firebase("https://cloverboard.firebaseio.com");*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Firebase.setAndroidContext(this);*/

        imageView = (ImageView) findViewById(R.id.header);
        imageView.setImageResource(R.drawable.bedroom_header);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(
                R.id.collapse_toolbar);

        collapsingToolbar.setTitleEnabled(false);

        blue_button=(Button)findViewById(R.id.blue_button);
button=(Button)findViewById(R.id.main_button20);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HOME");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        blueButtonActivity();
        tempup();
        tempdown();
        customizedButton();



        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        imageView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()){

            @Override
            public void onSwipeRight() {

                super.onSwipeRight();

                Log.d("Swipe", "Right");
                int currentItem = viewPager.getCurrentItem();
                if(currentItem > 0){


                    viewPager.setCurrentItem(currentItem - 1);
                }
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Log.d("Swipe", "Left");
                int currentItem = viewPager.getCurrentItem();
                if(currentItem < (viewPager.getChildCount()-1)){

                    viewPager.setCurrentItem(currentItem + 1);
                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.d("Position : ", String.valueOf(position));
//                current_time=System.currentTimeMillis();
            }

            @Override
            public void onPageSelected(final int position) {
                Log.d("Position1 : ", String.valueOf(position));

//                Toast.makeText(getApplicationContext(), String.valueOf(System.currentTimeMillis()-current_time), Toast.LENGTH_SHORT).show();

                switch (position) {


                    case 0:
                        navigationView.setCheckedItem(R.id.item_navigation_bedroom);
                        break;


                    case 1:
                        navigationView.setCheckedItem(R.id.item_navigation_drawer_starred);
                        break;

                    case 2:
                        navigationView.setCheckedItem(R.id.item_navigation_living_room);
                        break;


                }


                navigationView.setCheckedItem(position);


                Animation image_animation_fade_out = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
                final Animation image_animation_fade_in = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);


                image_animation_fade_out.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {


                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        toolbarImage.setVisibility(View.INVISIBLE);
                        switch (position) {

                            case 0:
                                imageView.setImageResource(R.drawable.bedroom_header);
                                break;
                            case 1:
                                imageView.setImageResource(R.drawable.kitchen_header1);
                                break;

                            case 2:
                                imageView.setImageResource(R.drawable.living_header);
                                break;

                            case 3:
                                imageView.setImageResource(R.drawable.bedroom_header);
                                break;

                            case 4:
                                imageView.setImageResource(R.drawable.bedroom_header);
                                break;
                        }

                        imageView.setAnimation(image_animation_fade_in);
                        imageView.startAnimation(image_animation_fade_in);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                imageView.setAnimation(image_animation_fade_out);
                imageView.startAnimation(image_animation_fade_out);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }

        setupNavigationDrawerContent(navigationView);
        //displays first in nav view

      /*  setFragment(0);*/


       /* ref.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    // user is logged in
                } else {
                    // user is not logged in
                }
            }
        });*/


    }

    private void customizedButton() {
        button.setBackgroundResource(R.drawable.off);
        button.setOnClickListener(new View.OnClickListener() {
            boolean isOddClicked = true;
            public void onClick(View v) {

                if (v.getId() == R.id.main_button20) {
                    if (!isLongPressed) {
                        if (isOddClicked) {
                            button.setBackgroundResource(R.drawable.on);
                            isOddClicked = false;

                        } else {
                            button.setBackgroundResource(R.drawable.off);
                            isOddClicked = true;
                        }
                    }
                }
            }
        });
        button.setOnLongClickListener(new View.OnLongClickListener() {
            boolean isLongPressed = true;

            @Override
            public boolean onLongClick(View v) {
                if (isLongPressed) {
                    button.setBackgroundResource(R.drawable.power);
                    isLongPressed = false;

                } else {

                    isLongPressed = true;
                }
                return true;
            }
        });

    }

    private void tempdown() {
        Button tempdown = (Button) findViewById(R.id.dec_temp);
        tempdown.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(t>16)
                {
                    t = Integer.parseInt(temp);
                    t=t-1;
                    temp = String.valueOf(t);
                    text = temp;
                    blue_button.setText(Html.fromHtml(text + "<sup><small>°</small></sup>"+"<sup><sup><small><small><small>c</small></small></small></sup></sup>"));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "This is the minimum limit",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void tempup() {

        blue_button.setText(Html.fromHtml(text + "<sup><small>°</small></sup>" + "<sup><sup><small><small><small>c</small></small></small></sup></sup>"));
        Button tempup = (Button) findViewById(R.id.inc_temp);
        tempup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t < 30) {
                    t = Integer.parseInt(temp);
                    t = t + 1;
                    temp = String.valueOf(t);
                    text = temp;
                    blue_button.setText(Html.fromHtml(text + "<sup><small>°</small></sup>"+"<sup><sup><small><small><small>c</small></small></small></sup></sup>"));
                } else {
                    Toast.makeText(getApplicationContext(), "This is the maximum limit",
                            Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    private void blueButtonActivity() {
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new BedroomFragment(new OnFragmentSelectedListener() {
            @Override
            public void onFragmentSelected(int position) {
            }
        }), "Bedroom");


        adapter.addFrag(new StarredFragment(new OnFragmentSelectedListener() {
            @Override
            public void onFragmentSelected(int position) {

            }
        }), "Kitchen");


        adapter.addFrag(new LivingFragment(new OnFragmentSelectedListener() {
            @Override
            public void onFragmentSelected(int position) {

            }
        }), "Hall");


        viewPager.setAdapter(adapter);


    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


       /* public void addFrag(BedroomFragment bedroomFragment) {
        }

        public void addFrag(StarredFragment starredFragment) {
        }*/
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
                              /*  ref.unauth();*/
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
                                final EditText subEditText = (EditText) subView.findViewById(R.id.editdilog);


                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AppCompatAlertDialogStyle);
                                builder.setTitle("Add Room");

                                builder.setMessage("Press the Configuration Button on the CloverBoard ");
                                builder.setPositiveButton("ADD", null);
                                builder.setNegativeButton("CANCEL", null);
                                builder.setView(subView);
                                builder.show();


                            case R.id.item_navigation_drawer_settings:
                                menuItem.setChecked(true);
                                Toast.makeText(MainActivity.this, menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;


                            case R.id.item_navigation_drawer_theme:
                                menuItem.setChecked(true);
                                Intent the = new Intent(getApplicationContext(), Theme.class);
                                startActivity(the);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;




                        }
                        return true;
                    }
                });
    }


    public void setFragment(int position) {

        viewPager.setCurrentItem(position);
//        android.support.v4.app.FragmentManager fragmentManager;
//        FragmentTransaction fragmentTransaction;
//        fragmentManager = getSupportFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.viewpager, adapter.getItem(position));
//        fragmentTransaction.commit();

//        switch (position) {
//            case 0:
//
//
//
//                fragmentManager = getSupportFragmentManager();
//                fragmentTransaction = fragmentManager.beginTransaction();
//                BedroomFragment bedroomFragment = new BedroomFragment();
//                fragmentTransaction.replace(R.id.fragment, bedroomFragment);
//                fragmentTransaction.commit();
//                adapter.getItem(position);
//
//                break;
//            case 1:
//                fragmentManager = getSupportFragmentManager();
//                fragmentTransaction = fragmentManager.beginTransaction();
//                StarredFragment starredFragment = new StarredFragment( );
//                fragmentTransaction.replace(R.id.fragment, starredFragment);
//                fragmentTransaction.commit();
//                break;
//
//
//            case 2:
//                fragmentManager = getSupportFragmentManager();
//                fragmentTransaction = fragmentManager.beginTransaction();
//
//                LivingFragment livingFragment=new LivingFragment();
//                fragmentTransaction.replace(R.id.fragment, livingFragment);
//
//                fragmentTransaction.commit();
//                break;
//
//
//
//        }
    }


}


