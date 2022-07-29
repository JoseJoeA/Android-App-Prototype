package com.example.mainactivity;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;

// Main activity into a fragment!
public class MainActivityFrag extends Fragment
    implements OnClickListener
{


    private Button openButton; // Button created to open the secondary activity (For now at least)

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
        View view = inflater.inflate(R.layout.main_activity,
                container, false);


        openButton = (Button) view.findViewById(R.id.openButton);

        openButton.setOnClickListener(this);

        // return the View for the layout
        return view;
    }
    // Menu created for the app
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
    // When clicked does this event
    @Override
    public void onClick(View v)
    {
        openLab();
    }
    // Method created to open the secondary activity when clicked.
    public void openLab()
    {
        startActivity(new Intent(getActivity(), secondary_activity.class));
    }
    // Want to make the button appear in the certain days and times where the open lab is live,
    // though I still do not have enough knowledge to make that at the moment.
}

