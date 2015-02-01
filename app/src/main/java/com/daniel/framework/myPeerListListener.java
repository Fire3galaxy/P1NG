package com.daniel.framework;

import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;

/**
 * Created by Daniel on 2/1/2015.
 */
public class myPeerListListener implements WifiP2pManager.PeerListListener{
    @Override
    public void onPeersAvailable(WifiP2pDeviceList peers) {}
}
