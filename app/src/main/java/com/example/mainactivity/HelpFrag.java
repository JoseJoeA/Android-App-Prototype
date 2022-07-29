package com.example.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class HelpFrag extends Fragment
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Had trouble getting the menu to appear, however found the answer at https://stackoverflow.com/questions/12090335/menu-in-fragments-not-showing
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.project_menu, menu);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.help_activity,
                container, false);

        // return the View for the layout
        return view;
    }
    // Same options menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.proj_about_menu:
                startActivity(new Intent(getActivity(), HelpActivity.class));
                return true;
            case R.id.proj_settings_menu:
                startActivity(new Intent(getActivity(), PreferencesActivity.class));
                return true;
            case R.id.proj_main_menu:
                startActivity(new Intent(getActivity(), MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
