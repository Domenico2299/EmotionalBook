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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*CONTROLLO RADIOBUTTON: L'utilizzo di un radiogroup non si è rilevato efficace, dato il suo layout lineare, non ho trovato nemmeno soluzioni efficaci
        nell'utilizzo di due RadioGroup paralleli, ho quindi optato per creare una versione Custom di un RadioGroup*/
        final RadioButton radioHappy= findViewById(R.id.radioButtonHappy);
        final RadioButton radioAngry= findViewById(R.id.radioButtonAngry);
        final RadioButton radioStressed= findViewById(R.id.radioButtonStressed);
        final RadioButton radioSad= findViewById(R.id.radioButtonSad);
        radioHappy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                radioAngry.setChecked(false);
                radioSad.setChecked(false);
                radioStressed.setChecked(false);
            }
        });
        radioSad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                radioAngry.setChecked(false);
                radioHappy.setChecked(false);
                radioStressed.setChecked(false);
            }
        });
        radioAngry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                radioHappy.setChecked(false);
                radioSad.setChecked(false);
                radioStressed.setChecked(false);
            }
        });
        radioStressed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                radioAngry.setChecked(false);
                radioSad.setChecked(false);
                radioHappy.setChecked(false);
            }
        });

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
