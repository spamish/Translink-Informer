package com.spamish.project.translinkinformer.net_tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class AlarmReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "com.spamish.project.translinkinformer.net_tools";

    public void onReceive(Context context, Intent intent) {
        WakefulBroadcastReceiver.completeWakefulIntent(intent);
        Intent i = new Intent(context, CheckerService.class);
        context.startService(i);
    }
}