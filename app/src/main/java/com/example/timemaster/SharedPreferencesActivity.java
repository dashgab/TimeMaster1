package com.example.timemaster;


import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SharedPreferencesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SharedPreferencesFragment())
                .commit();
    }
}
