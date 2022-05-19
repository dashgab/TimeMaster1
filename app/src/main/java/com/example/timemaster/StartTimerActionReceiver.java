package com.example.timemaster;

import static com.example.timemaster.utils.Constants.INTENT_NAME_ACTION;
import static com.example.timemaster.utils.Constants.INTENT_VALUE_LONG_BREAK;
import static com.example.timemaster.utils.Constants.INTENT_VALUE_SHORT_BREAK;
import static com.example.timemaster.utils.Constants.INTENT_VALUE_START;
import static com.example.timemaster.utils.Constants.LONG_BREAK;
import static com.example.timemaster.utils.Constants.SHORT_BREAK;
import static com.example.timemaster.utils.Constants.TAMETU;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.timemaster.utils.Utils;


import static com.example.timemaster.utils.StartTimerUtils.startTimer;

public class StartTimerActionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String receivedIntent = intent.getStringExtra(INTENT_NAME_ACTION);
        SharedPreferences prefences = PreferenceManager.getDefaultSharedPreferences(context);
        switch (receivedIntent) {
            case INTENT_VALUE_START:
                long workDuration = Utils.getCurrentDurationPreferenceOf(prefences, context,
                        TAMETU);
                startTimer(workDuration, context);
                Log.d("TIMER was started with", String.valueOf(workDuration));
                break;
            case INTENT_VALUE_SHORT_BREAK:
                long shortBreakDuration = Utils.getCurrentDurationPreferenceOf(prefences, context,
                        SHORT_BREAK);
                startTimer(shortBreakDuration, context);
                Log.d("SHRT_BRK started with", String.valueOf(shortBreakDuration));
                break;
            case INTENT_VALUE_LONG_BREAK:
                long longBreakDuration = Utils.getCurrentDurationPreferenceOf(prefences, context,
                        LONG_BREAK);
                startTimer(longBreakDuration, context);
                Log.d("LONG_BRK started with", String.valueOf(longBreakDuration));
        }
    }
}