package com.daniel.framework;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.Set;

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

        if (myBlue == null) {   // checks if system has bluetooth
            Log.i(TAG, "Bluetooth not available.");
            android.os.Process.killProcess(android.os.Process.myPid());
        }

        bluetoothOn();
    }

    void bluetoothOn() { // checks if bluetooth is on and turns it on if not
        final int REQUEST_ENABLE_BT = 1;

        if (!myBlue.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            a.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    String getBluetoothDevice(String device_name) {
        Set<BluetoothDevice> pairedDevices = myBlue.getBondedDevices();
        // If there are paired devices
//        ArrayAdapter<BluetoothDevice> mArrayAdapter = new ArrayAdapter<BluetoothDevice>(a, );
        // Array adapter
        String s = "";

        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
//                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                s = device.getName() + "," + device.getAddress();
                Log.i(TAG, s);
            }
        }

        return s;
    }
}
