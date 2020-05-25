package com.example.emotionalbook;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    EmotionalDatabaseAdapter dbInstance;
    LocalDate date;
    String state;
    long intensity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView intensitàText=findViewById(R.id.textViewIntensità);
        intensitàText.setText(String.valueOf(0));
        SeekBar seekBar=findViewById(R.id.seekBar);
        seekBar.setMax(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                intensitàText.setText(String.valueOf(progress));
                intensity=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("INTENSITàPROVA", " valore: "+intensity);
            }
        });

    }

    public void onAddItem(View v) {
        //Set State Column DB
        ToggleButtonGroupTableLayout radioGroup=findViewById(R.id.radGroup1);
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radioButtonHappy:
                state = "felice";
                break;
            case R.id.radioButtonAngry:
                state = "arrabbiato";
                break;
            case R.id.radioButtonSad:
                state = "triste";
                break;
            case R.id.radioButtonStressed:
                state = "stressato";
                break;
            default:
                Log.i("ERRORE", "NESSUNO STATO SEGNALATO");
                break;
        }
        //Create the Row
        date=LocalDate.now(ZoneId.systemDefault());
        EmotionalRow item = new EmotionalRow(state,intensity,date);
        Log.i("OGGETTO CREATO",item.toString());
        // create and open DB
        dbInstance=EmotionalDatabaseAdapter.getInstance(this);
        dbInstance.open();
        // add to the DB and set the item idx
        item.set_id(dbInstance.insert(item));
        dbInstance.close();

        Intent intent=new Intent(MainActivity.this,StatisticsActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuhome,menu);
        return true;
    }
}
