package com.example.mainactivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.v7.preference.PreferenceFragmentCompat;

public class Pref_fragment extends PreferenceFragmentCompat
{
    private SharedPreferences prefs;
    private int theme;
    public SwitchPreference notifSwitch;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

//        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.preferences);
        //prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }


}
