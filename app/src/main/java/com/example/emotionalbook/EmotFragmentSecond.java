package com.example.emotionalbook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmotFragmentSecond#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmotFragmentSecond extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EmotFragmentSecond() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmoFragmentSecond.
     */
    // TODO: Rename and change types and number of parameters
    public static EmotFragmentSecond newInstance(String param1, String param2) {
        EmotFragmentSecond fragment = new EmotFragmentSecond();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int happy=DatabaseManager.valWeek[0].intValue();
        int sad=DatabaseManager.valWeek[1].intValue();
        int stressed=DatabaseManager.valWeek[2].intValue();
        int angry=DatabaseManager.valWeek[3].intValue();
        int maxValue;
        View view=inflater.inflate(R.layout.fragment_emo_second, container, false);

        TextView advices=view.findViewById(R.id.advices2);
        maxValue=Math.max(Math.max(happy,sad),Math.max(stressed,angry));

        if(maxValue==happy)
            advices.setText(R.string.happy_advice_2);
        if(maxValue==angry)
            advices.setText(R.string.angry_advice_2);
        if(maxValue==sad)
            advices.setText(R.string.sad_advice_2);
        if(maxValue==stressed)
            advices.setText(R.string.stress_advice_2);

        return view;
    }
}
