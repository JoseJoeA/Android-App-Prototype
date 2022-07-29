package com.example.mainactivity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class OpenLabSession {

    private UUID mid;
    private Calendar startDateCal;


    OpenLabSession() {

        startDateCal = new GregorianCalendar();

    }

    OpenLabSession (Calendar sessionStart) { startDateCal = sessionStart;}



    Calendar getStartDate() {
        return startDateCal;
    }

}
