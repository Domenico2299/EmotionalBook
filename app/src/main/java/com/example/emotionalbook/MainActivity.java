package com.example.emotionalbook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView textViewAnswer;
    String cambioIdea="Come ti senti oggi?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbInstance=EmotionalDatabaseAdapter.getInstance(this);

        textViewAnswer=findViewById(R.id.textView);
        textViewAnswer.setText(cambioIdea);

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
        boolean wrongChoise=true;
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
                Toast toast=Toast. makeText(getApplicationContext(),"Inserisci uno stato emotivo!",Toast. LENGTH_SHORT);
                toast.setMargin(100,100);
                toast.show();
                wrongChoise=false;
                break;
        }
        if(intensity==0){
            Toast toastIntensity=Toast. makeText(getApplicationContext(),"Inserisci l'intensità!",Toast. LENGTH_SHORT);
            toastIntensity.setMargin(100,100);
            toastIntensity.show();
            wrongChoise=false;
        }

        if(wrongChoise) {
            //Create the Row
            date=LocalDate.now(ZoneId.systemDefault());
            EmotionalRow item = new EmotionalRow(state,intensity,date);
            Log.i("OGGETTO CREATO",item.toStringOne());
            //open DB and Manager
            dbInstance.open();
            //dbInstance.deleteAll(); NEL CASO IN CUI SI VOLESSE ELIMINARE IL DB
            // add to the DB and set the item idx
            item.set_id(dbInstance.insert(item));
            DatabaseManager.setStatistics(dbInstance.getYear(),dbInstance.getMonth(),dbInstance.getWeek());
            dbInstance.close();

            Intent intent=new Intent(MainActivity.this,StatisticsActivity.class);
            startActivity(intent);
            cambioIdea="Cambiato idea?";
            textViewAnswer.setText(cambioIdea);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menuhome, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.statistics:

                dbInstance.open();
                DatabaseManager.setStatistics(dbInstance.getYear(),dbInstance.getMonth(),dbInstance.getWeek());
                dbInstance.close();
                Intent statistics = new Intent(MainActivity.this, StatisticsActivity.class);
                statistics.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(statistics, 0);
                return true;

            case R.id.advices:

                Intent advices = new Intent(this, AdvicesActivity.class);
                startActivity(advices);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
