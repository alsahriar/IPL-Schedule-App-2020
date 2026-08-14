package com.softtechbd.iplschedule2020;

import android.app.Application;

import com.onesignal.OneSignal;

public class MyApplication extends Application {
    private static MyApplication mInstant;

    public MyApplication() {
        mInstant = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstant = this;
        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
    public static synchronized MyApplication getmInstant(){
        return mInstant;
    }
}
