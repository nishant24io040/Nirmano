package com.ansrnirmano.mentalwellness.TherepySection;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ansrnirmano.mentalwellness.HomePage;
import com.ansrnirmano.mentalwellness.R;

import java.util.Random;

public class SetNotification extends FirebaseMessagingService {
    static final String channel_id = "id_of_noti";
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        SharedPreferences sd = this.getSharedPreferences("noti", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sd.edit();
        ed.putString("yes","callme");
        ed.commit();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, HomePage.class);
        int nitiId = new Random().nextInt();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pintent = PendingIntent.getActivities(this,0,new Intent[]{intent},PendingIntent.FLAG_MUTABLE);
        notifictionchennel(notificationManager);
        Notification.Builder notification = new Notification.Builder(this,channel_id)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(message.getNotification().getTitle())
                .setContentText(message.getNotification().getBody())
                .setAutoCancel(true)
                .setColor(Color.BLUE)
                .setContentIntent(pintent);
        notificationManager.notify(nitiId,notification.build());
    }

    private void notifictionchennel(NotificationManager manager){
        NotificationChannel channel = new NotificationChannel(channel_id,"slotnoti",NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription("testing it");
        channel.enableLights(true);
        manager.createNotificationChannel(channel);

    }
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }
//    Intent intent1 = new Intent(getApplicationContext(), Releaf.class);
//    PendingIntent pendingIntent = PendingIntent.getActivity(Notification.this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//    Intent intent2 = new Intent(Notification.this, Tharapytemp.class);
//    PendingIntent pendingIntent2 = PendingIntent.getActivity(Notification.this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
//
//    Intent cancelIntent = new Intent(this, NotificationReceiver.class);
//    PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, cancelIntent, 0);

//    private void notifyUser(String title, String messageBody) {
//        Intent intent = new Intent(this, SadLevel1.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 100 , intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        String channelId = getString(R.string.default_notification_channel_id);
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder =
//                new NotificationCompat.Builder(this, channelId)
//                        .setSmallIcon(R.drawable.icon)
//                        .setContentTitle(title)
//                        .setContentText(messageBody)
//                        .setAutoCancel(true)
//                        .setColor(Color.BLUE)
//                        .setSound(defaultSoundUri)
//                        .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        // For android Oreo and above  notification channel is needed.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channelId,
//                    "Fcm notifications",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        notificationManager.notify(100 , notificationBuilder.build());
//    }

//    public void getmasg(String title, String masg){
//        NotificationCompat.Builder bulder = new NotificationCompat.Builder(this,"session")
//                .setSmallIcon(R.drawable.icon)
//                .setContentTitle(title)
////                .addAction(android.R.drawable.ic_menu_view, "VIEW", pendingIntent)
////                .addAction(android.R.drawable.ic_delete, "DISMISS", pendingIntent2)
//                .setContentText(masg)
//                .setAutoCancel(true);
//        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
//        manager.notify(100,bulder.build());
//    }
}
