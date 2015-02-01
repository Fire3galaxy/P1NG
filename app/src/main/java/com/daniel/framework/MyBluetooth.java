package com.daniel.framework;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Daniel on 2/1/2015.
 */
public class MyBluetooth {
    static final String TAG = "MyBluetooth";

    BluetoothAdapter myBlue;
    Activity a;

    public MyBluetooth() {
        myBlue = BluetoothAdapter.getDefaultAdapter();
        a = new Activity();
        final int REQUEST_ENABLE_BT = 1;

        if (myBlue == null) {
            Log.i(TAG, "Bluetooth not available.");
            android.os.Process.killProcess(android.os.Process.myPid());
        }

        if (!myBlue.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            a.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }
}
