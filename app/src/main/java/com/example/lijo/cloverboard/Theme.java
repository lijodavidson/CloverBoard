package com.example.lijo.cloverboard;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;

public class Theme extends AppCompatActivity {
    private GridView gridViewObj;
    private ArrayList<ClipData.Item> themeArray = new ArrayList<>();
    private ArrayList<Integer> dialogArray = new ArrayList<>();
    /*private ThemesAdapter themesAdapter;
    private DialogImagesAdapter dialogImagesAdapter;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        //set Main grid view item
      /*  themeArray.add(new ThemeItems(R.drawable.morning, "Evening"));
        themeArray.add(new ThemeItems(R.drawable.re, "Morning"));
        themeArray.add(new ThemeItems(R.drawable.morning, "Night"));

        //set Dialog grid view item
        dialogArray.add(R.drawable.morning);
        dialogArray.add(R.drawable.re);
        dialogArray.add(R.drawable.morning);
        dialogArray.add(R.drawable.re);
        dialogArray.add(R.drawable.morning);
        dialogArray.add(R.drawable.re);
        dialogArray.add(R.drawable.morning);
        dialogArray.add(R.drawable.re);
        dialogArray.add(R.drawable.morning);
        dialogArray.add(R.drawable.morning);
        dialogArray.add(R.drawable.re);
        dialogArray.add(R.drawable.morning);
*/




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_theme, menu);
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
