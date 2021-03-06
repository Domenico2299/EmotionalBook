package com.example.emotionalbook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentWeek#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentWeek extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentWeek() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentWeek.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentWeek newInstance(String param1, String param2) {
        FragmentWeek fragment = new FragmentWeek();
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
        View view = inflater.inflate(R.layout.fragment_week, container, false);

        //CREAZIONE SEEKBAR
        SeekBar seekBar1=view.findViewById(R.id.seekBar1);
        SeekBar seekBar2=view.findViewById(R.id.seekBar2);
        SeekBar seekBar3=view.findViewById(R.id.seekBar3);
        SeekBar seekBar4=view.findViewById(R.id.seekBar4);

        //DISABILITAZIONE CURSORE SEEKBAR
        seekBar1.setEnabled(false);
        seekBar2.setEnabled(false);
        seekBar3.setEnabled(false);
        seekBar4.setEnabled(false);

        //INDICE MASSIMO SEEKBAR
        seekBar1.setMax(100);
        seekBar2.setMax(100);
        seekBar3.setMax(100);
        seekBar4.setMax(100);

        //INDICE RECUPERATO DAL MANAGER
        seekBar1.setProgress(DatabaseManager.valWeek[0].intValue());
        seekBar2.setProgress(DatabaseManager.valWeek[1].intValue());
        seekBar3.setProgress(DatabaseManager.valWeek[2].intValue());
        seekBar4.setProgress(DatabaseManager.valWeek[3].intValue());

        return view;
    }
}
