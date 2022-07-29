package com.example.mainactivity;


import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

// Open Lab
public class OpenLab
{
    private static OpenLab openLab;
    private Context oContext;
    private List<OpenLabSession> sessions;

    public static OpenLab get(Context context)
    {
        if (openLab == null)
        {
            openLab = new OpenLab(context);
        }
        return openLab;
    }
    // Dates for when the open lab schedule is open
    private OpenLab(Context context)
    {
        oContext = context.getApplicationContext();
        Calendar sessionStart = new GregorianCalendar(2019,5,2,9,30); // Thru
        sessions = new ArrayList<OpenLabSession>();
        sessions.add(new OpenLabSession(sessionStart));

        sessionStart = new GregorianCalendar(2019,5,6,14,0); // Mon
        sessions.add(new OpenLabSession(sessionStart));

        sessionStart = new GregorianCalendar(2019,5,7,9,30); // Tue AM
        sessions.add(new OpenLabSession(sessionStart));

        sessionStart = new GregorianCalendar(2019,5,7,13,0); // Tue PM
        sessions.add(new OpenLabSession(sessionStart));

        sessionStart = new GregorianCalendar(2019,5,8,13,0); // Wed
        sessions.add(new OpenLabSession(sessionStart));
    }
    public List<OpenLabSession> getSessions()
    {
        return openLab.sessions;
    }

}
