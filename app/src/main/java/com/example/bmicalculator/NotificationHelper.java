package com.example.bmicalculator;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import java.util.Calendar;


public class NotificationHelper {

    private static final String TAG = "NotificationHelper";
    private static final String PREF_KEY_NOTIFICATION_ACCESS = "notification_access";
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "bmi_calculator_channel";

    private Context context;
    private NotificationManager notificationManager;




    public boolean hasNotificationPermission() {
        Toast.makeText(context, "hasNotificationPermission",Toast.LENGTH_LONG).show();
        return notificationManager.areNotificationsEnabled(); // This might not be perfect on all devices (reference in a note below)
    }

    public void scheduleDailyNotification() {
        if (hasNotificationPermission()) {
            createNotificationChannel();
            // Schedule notification directly for 5pm
            scheduleNotificationForFivePM();
        } else {
            //  Request user to grant permission (indirectly)
            requestNotificationPermission();
        }
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.app_name); // Set a user-friendly channel name in strings.xml
            String description = context.getString(R.string.channel_description); // Set a channel description in strings.xml
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);

        }
    }

    private void scheduleNotificationForFivePM() {
        // Get the time for 5:00 p.m. today
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 22); // Set for 5 p.m.
        calendar.set(Calendar.MINUTE, 42);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Intent intent = new Intent(context, MemoBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher_foreground) // Replace with your app icon
                .setContentTitle("BMI Calculator Reminder")
                .setContentText("Don't forget to track your BMI today!")
                .setPriority(Notification.PRIORITY_DEFAULT);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void requestNotificationPermission() {
        Toast.makeText(context, "Daily BMI reminders require notification permission. Grant permission in Settings to receive reminders.", Toast.LENGTH_LONG).show();
        //  Open notification settings intent
        Intent intent = new Intent();
        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
        intent.putExtra("android.package_name", context.getPackageName());
        context.startActivity(intent);
    }


}
