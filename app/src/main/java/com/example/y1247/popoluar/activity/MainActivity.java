package com.example.y1247.popoluar.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.y1247.popoluar.R;
import com.example.y1247.popoluar.view.ListFragment;
import com.example.y1247.popoluar.view.SettingsFragment;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager = getFragmentManager();
    ListFragment listFragment;
    SettingsFragment settingsFragment;

    int backPressFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = this.getFragmentManager();
        FragmentTransaction  transaction = fragmentManager.beginTransaction();
        listFragment = new ListFragment();
        settingsFragment = new SettingsFragment();
        transaction.add(R.id.fl_mainLayout,listFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            FragmentTransaction  transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fl_mainLayout,settingsFragment);
            transaction.commit();
            backPressFlag ++;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if(backPressFlag == 0) {
            super.onBackPressed();
        }else{
            FragmentTransaction  transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fl_mainLayout,listFragment);
            transaction.commit();
            backPressFlag --;
        }
    }
}
