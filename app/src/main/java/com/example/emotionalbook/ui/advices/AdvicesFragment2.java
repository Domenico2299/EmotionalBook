package com.example.emotionalbook.ui.advices;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emotionalbook.R;

public class AdvicesFragment2 extends Fragment {

    private AdvicesFragment2ViewModel mViewModel;

    public static AdvicesFragment2 newInstance() {
        return new AdvicesFragment2();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.advices_fragment2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AdvicesFragment2ViewModel.class);
        // TODO: Use the ViewModel
    }

}
