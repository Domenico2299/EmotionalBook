package com.example.emotionalbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.util.Log;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advices_activity);
        Button rightArrow=(Button) findViewById(R.id.advicesRightArrow);
        rightArrow.setOnClickListener(this);
        Button leftArrow=(Button) findViewById(R.id.advicesLeftArrow);
        leftArrow.setOnClickListener(this);
        fragmentManager.beginTransaction().add(R.id.container,one).commit();
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
}
