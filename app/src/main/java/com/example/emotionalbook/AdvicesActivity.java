package com.example.emotionalbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class AdvicesActivity extends AppCompatActivity implements View.OnClickListener{

    EmotFragmentFirst one=new EmotFragmentFirst();
    EmotFragmentSecond two=new EmotFragmentSecond();
    EmotFragmentThird three=new EmotFragmentThird();
    Fragment[] advices={one,two,three};
    FragmentManager fragmentManager = getSupportFragmentManager();
    int counter=0;
    EmotionalDatabaseAdapter dbInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advices_activity);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button rightArrow=(Button) findViewById(R.id.advicesRightArrow);
        rightArrow.setOnClickListener(this);
        Button leftArrow=(Button) findViewById(R.id.advicesLeftArrow);
        leftArrow.setOnClickListener(this);
        fragmentManager.beginTransaction().add(R.id.container,one).commit();
        getSupportActionBar().setTitle("Consigli");
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.advicesRightArrow:
                if(counter==2)counter=0;
                else counter++;
                fragmentManager.beginTransaction().replace(R.id.container,advices[counter]).commit();
                Log.i("INCREMENTO","VALORE: "+ String.valueOf(counter));
                break;

            case R.id.advicesLeftArrow:
                if(counter==0)counter=2;
                else counter--;
                fragmentManager.beginTransaction().replace(R.id.container,advices[counter]).commit();
                Log.i("DECREMENTO","VALORE: "+ String.valueOf(counter));
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menuconsigli, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:

                Intent home = new Intent(AdvicesActivity.this, MainActivity.class);
                home.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(home, 0);
                return true;

            case R.id.statistics:

                dbInstance=EmotionalDatabaseAdapter.getInstance(this);
                dbInstance.open();
                DatabaseManager.setStatistics(dbInstance.getYear(),dbInstance.getMonth(),dbInstance.getWeek());
                dbInstance.close();
                Intent statistics = new Intent(AdvicesActivity.this, StatisticsActivity.class);
                statistics.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(statistics, 0);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
