package com.example.ogatafumitoshi.gcmtest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

//GCMサーバからの通知を受信する
public  class GCMBroadcastReceiver extends WakefulBroadcastReceiver {

    public GCMBroadcastReceiver() {
        super();
    }

    //受信されたとき
    @Override
    public void onReceive(Context context, Intent intent) {
        ComponentName comp =
                new ComponentName(context.getPackageName(), GCMIntentService.class.getName());
        startWakefulService(context, intent.setComponent(comp));
        setResultCode(Activity.RESULT_OK);
    }
}