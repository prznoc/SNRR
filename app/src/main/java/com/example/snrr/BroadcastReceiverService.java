package com.example.snrr;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class BroadcastReceiverService extends Service {
    private BroadcastReceiver br;
    private LocalBroadcastManager manager;
    private IntentFilter filter;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        br = new OrderBroadcastReceiver();
        manager = LocalBroadcastManager.getInstance(this);
        filter = new IntentFilter();
        filter.addAction("New Order");
        manager.registerReceiver(br, filter);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
