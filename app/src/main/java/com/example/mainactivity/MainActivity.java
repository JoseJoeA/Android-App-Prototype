package com.example.mainactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity // This helps display the action bar.
{
    private int defaultTheme;
    private int oldTheme;
    private boolean isLight;
    private boolean notif = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        notif = pref.getBoolean("pref_notif",false);
        String lister = pref.getString("pref_theme", "1");
        oldTheme = Integer.parseInt(lister);
//
        toggleTheme();
//
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new MainActivityFrag();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }
    }

    protected void onResume() {
        super.onResume();
        toggleTheme();
    }


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
}


