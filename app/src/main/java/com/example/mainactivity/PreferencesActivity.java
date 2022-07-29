package com.example.mainactivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class PreferencesActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener // just in case
{

    private int defaultTheme;
    private int oldTheme;
    private boolean isLight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String lister = pref.getString("pref_theme", "1");
        oldTheme = Integer.parseInt(lister);





//
        toggleTheme();
//
//        setContentView(R.layout.activity_fragment);
//
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//
//        if (fragment == null) {
//            fragment = new MainActivityFrag();
//            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
//        }
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new Pref_fragment())
                .commit();
    }

    protected void onResume() {
        super.onResume();

//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
//        pref.registerOnSharedPreferenceChangeListener(this);
//        boolean notif = pref.getBoolean("pref_notif",false);

        // pref.unregisterOnSharedPreferenceChangeListener(this);

        toggleTheme();
    }

    // Method that changes theme
    private void toggleTheme()
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String lister = pref.getString("pref_theme", "1");

        defaultTheme = Integer.parseInt(lister);
        if(defaultTheme == 2)
        {
            isLight = false;
        }
        else
        {
            isLight = true;
        }

        if(isLight)
        {
            setTheme(R.style.AppTheme);
        }
        else
        {
            setTheme(R.style.DarkTheme);
        }

        if(oldTheme!= defaultTheme) {
            oldTheme = defaultTheme;


            Intent k = new Intent(this, MainActivity.class);
            k.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(k);
        }
    }
    // Method Listener when the Switch preference moves and starts the service
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
         Intent service = new Intent(this,OpenLabService.class);
        if(key.equals("pref_notif"))
        {
             startService(service);
        }
    }
}
