package com.example.emotionalbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.emotionalbook.ui.advices.AdvicesFragment;

public class AdvicesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advices_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AdvicesFragment.newInstance())
                    .commitNow();
        }
    }
}
