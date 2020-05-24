package com.example.emotionalbook;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button buttonToAdvices= findViewById(R.id.buttonToAdvices);
        buttonToAdvices.setOnClickListener(this);
        Button buttonToStatistiche= findViewById(R.id.buttonStatistiche);
        buttonToStatistiche.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonToAdvices:
                Intent intent=new Intent(MainActivity.this,AdvicesActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonStatistiche:
                Intent intent2=new Intent(MainActivity.this,StatisticsActivity.class);
                startActivity(intent2);
                break;
        }
    }

}
