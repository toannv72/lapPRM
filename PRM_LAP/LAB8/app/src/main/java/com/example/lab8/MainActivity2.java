package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.Date;
import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {
    private static final String CHANNEL_ID = "my_channel";

    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).setTitle("NotificationAndroid");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#057508")));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        createNotification2();
    }

    public void showNotification2(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        Notification.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Title push notification")
                    .setContentText("Message push notification")
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
        }
        Notification notification;
        notification = builder.build();
        notificationManager.notify(getNotificationID(), notification);
    }

    public int getNotificationID() {
        return (int) new Date().getTime();
    }

    public void createNotification2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            CharSequence channelName = "My Channel";
            String channelDiscription = "My Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription(channelDiscription);
            notificationManager.createNotificationChannel(channel);
        }
    }


}