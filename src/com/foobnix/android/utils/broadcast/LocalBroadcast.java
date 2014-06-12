package com.foobnix.android.utils.broadcast;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

public class LocalBroadcast {
    public static String LOCAL_NOTIFY_ACTION = "LOCAL_NOTIFY_ACTION";
    public static IntentFilter INTENT_FILTER = new IntentFilter(LocalBroadcast.LOCAL_NOTIFY_ACTION);

    public static void send(Context context, Intent intent) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

}
