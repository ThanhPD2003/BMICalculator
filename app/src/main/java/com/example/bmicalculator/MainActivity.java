package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.bmicalculator.models.User;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnOpenBmiCalculator;
    Button btnHistory;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingView();
        bindingAction();
        createNotificationChannel();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Intent intent = new Intent(MainActivity.this, MemoBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }

    private void bindingView() {
        btnOpenBmiCalculator = findViewById(R.id.btnOpenBmiCalculator);
        btnHistory = findViewById(R.id.btnHistory);
    }

    private void bindingAction() {
        btnOpenBmiCalculator.setOnClickListener(this::onBtnOpenBmiCalculator);
        btnHistory.setOnClickListener(this::onBtnHistory);
    }

    public void onBtnOpenBmiCalculator(View view) {
        Log.d("MainActivity", "Opening BMI Calculator");
        Intent intent = new Intent(this, BMICalculator.class);
        startActivity(intent);
    }

    public void onBtnHistory(View view) {
        Log.d("MainActivity", "Opening History Display");
        Intent intent = new Intent(this, HistoryDisplay.class);
        startActivity(intent);
    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "BMICalculator";
            String description = "BMI Calculator";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Notification", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }
    }
}

