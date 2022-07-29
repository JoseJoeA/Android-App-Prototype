package com.example.mainactivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

    import java.util.Calendar;
    import java.util.GregorianCalendar;
    import java.util.List;
    import java.util.Timer;
import java.util.TimerTask;

    public class OpenLabService extends Service {
        // private NewsReaderApp app; //This a variable for a class that extends application
        private Timer timer;
        private OpenLab lab;

        public void onCreate() {
            Log.d("Open Lab", "Service created");
            // app = (NewsReaderApp)getApplication();
            OpenLab.get(getApplicationContext());


            startTimer();
        }
        // Compares the dates if they are the same, then sends the notification
        private void startTimer() {
            TimerTask task = new TimerTask() {
                public void run() {
                    Log.d("Open Lab", "Timer task executed");
                    Calendar myCal = new GregorianCalendar();
                    List<OpenLabSession> sessions = lab.getSessions();
                    for (OpenLabSession session : sessions ) {
                        if (session.getStartDate().get(Calendar.DAY_OF_WEEK) == myCal.get(Calendar.DAY_OF_WEEK)
                                && session.getStartDate().before(myCal)
                                && session.getStartDate().getTimeInMillis() - myCal.getTimeInMillis() < 10 *60 * 1000) {
                            sendNotification("View the open lab!");
                        }
                    }

                        //Log.d("News Reader", "Updated feed NOT available.");

                }
            };

            timer = new Timer(true);
            int delay = 1000 * 10;
            int interval = 1000 * 60 * 60; // An hour interval
            timer.schedule(task, delay, interval);
        }
        // Services the start, and stop timers
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.d("Open Lab", "Service started");
            return START_STICKY;
        }
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        public void onDestroy() {
            Log.d("Open Lab Stop","Service destroyed");
            stopTimer();
        }

        private void stopTimer() {
            if (timer != null) {
                timer.cancel();
            }
        }
        // Notification part of code
        private void sendNotification(String text) {
            Intent notificationIntent = new Intent(this,MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            int flags = PendingIntent.FLAG_UPDATE_CURRENT;
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent,flags);
            int icon = R.drawable.ic_stat_desktop_windows;
            CharSequence tickerText = "Open Lab is available";
            CharSequence contentTitle = getText(R.string.app_name);
            CharSequence contentText = text;
            Notification notification = new Notification.Builder(this)
                    .setSmallIcon(icon)
                    .setTicker(tickerText)
                    .setContentTitle(contentTitle)
                    .setContentText(contentText)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            final int NOTIFICATION_ID = 1;
            manager.notify(NOTIFICATION_ID,notification);
        }
    }