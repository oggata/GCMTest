package com.example.ogatafumitoshi.gcmtest;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class MainActivity extends Activity {

    //https://console.developers.google.com/ で取得する「プロジェクト番号」を入れる
    public final static String
        SENDER_ID = "0000000000000000000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable(){public void run(){
            try{
                //まず初めにRegistedIDを取得する
                GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(MainActivity.this);
                final String regId = gcm.register(SENDER_ID);
                showMessage(MainActivity.this,"RegistedID.."+regId);
            }catch(Exception e){
                android.util.Log.e("debug",e.toString());
            }
        }}).start();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    public static void showMessage(Context context, String message){
        android.util.Log.e("debug", message);
    }
}
