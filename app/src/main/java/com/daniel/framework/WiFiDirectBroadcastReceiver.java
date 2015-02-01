package com.daniel.framework;

/**
 * Created by Daniel on 2/1/2015.
 */

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import com.daniel.framework.UsersBase;

/**
 * A BroadcastReceiver that notifies of important Wi-Fi p2p events.
 */
public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "WifiDirectBroadcastReceiver"; // debugging with logcat

    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private Activity mActivity;
    private myPeerListListener mPeerListListen;

    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel,
                                       Activity m) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.mActivity = m;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        WifiP2pDeviceList peers = new WifiP2pDeviceList();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);

            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                // Wifi P2P is enabled
                // Call this method to WIFI_P2P_PEERS_CHANGED_ACTION intent
                mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        // Should be fine to ignore if succeed
                    }

                    @Override
                    public void onFailure(int reasonCode) {
                        Log.i(TAG, "Peers not found.");
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                });

            } else {
                // Wi-Fi P2P is not enabled
                Log.i(TAG, "P2P not enabled");
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            // Call WifiP2pManager.requestPeers() to get a list of current peers
            // request available peers from the wifi p2p manager. This is an
            // asynchronous call and the calling activity is notified with a
            // callback on PeerListListener.onPeersAvailable()
            if (mManager != null) {
                mManager.requestPeers(mChannel, mPeerListListen);
            }

            mPeerListListen.onPeersAvailable(peers); // Returns Device list of peers
            for (WifiP2pDevice d : peers.getDeviceList())
                Log.i(TAG, d.deviceName);

        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            // Respond to new connection or disconnections
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            // Respond to this device's wifi state changing
        }
    }


}
