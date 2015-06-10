package com.example.ogatafumitoshi.gcmtest;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.gcm.GoogleCloudMessaging;

//GCMサーバから受け取った通知を制御
public class GCMIntentService extends IntentService {

    public GCMIntentService() {
        super(GCMIntentService.class.getName());
    }

    //処理時に呼ばれる
    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        Context context = getApplicationContext();

        //GCMのメッセージ種別の取得
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {
            //error
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                String message = extras.toString();
                MainActivity.showMessage(context, message);
                showNotification(context, R.drawable.cat,
                        "message_type_send_error", message, "");
            }
            //delete
            else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                String message = extras.toString();
                MainActivity.showMessage(context, message);
                showNotification(context, R.drawable.cat,
                        "message_type_deleted", message, "");
            }
            //get message
            else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                String message = extras.getString("message");
                MainActivity.showMessage(context, message);
                showNotification(context, R.drawable.cat,
                        "message_type_message", message, "");
            }
        }

        //サービスの処理の終了をGCMBroadcastReceiverに通知
        GCMBroadcastReceiver.completeWakefulIntent(intent);
    }

    private static void showNotification(Context context,
                                         int iconId, String title, String text, String info) {
        Notification.Builder builder=new Notification.Builder(context);
        builder.setWhen(System.currentTimeMillis());
        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setContentInfo(info);
        builder.setSmallIcon(iconId);
        Intent intent=new Intent(context,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        builder.setContentIntent(PendingIntent.getActivity(
                context, 0, intent, PendingIntent.FLAG_ONE_SHOT));
        NotificationManager nm=(NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(0);
        nm.notify(0,builder.getNotification());
    }
}

