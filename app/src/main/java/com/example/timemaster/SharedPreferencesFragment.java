package com.example.timemaster;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import com.example.timemaster.R;


public class SharedPreferencesFragment extends PreferenceFragment {

    public SharedPreferencesFragment() {
        // Required empty public constructor
    }

    public static SharedPreferencesFragment newInstance() {
        SharedPreferencesFragment fragment = new SharedPreferencesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

}