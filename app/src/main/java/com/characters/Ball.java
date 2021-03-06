package com.characters;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;

import com.SkyLife.p1ng.Assets;
import com.daniel.framework.WiFiDirectBroadcastReceiver;

/**
 * Created by Daniel on 1/31/2015.
 */
public class Ball {
    int centerX, centerY,speedX, speedY,radius;
    final int GRAVITY = 2, FLOOR = 854, // positive = DOWN
        STARTX = 1920, STARTY = 360; // FIXME: replace with function
    boolean animation;

    public Ball(int sX, Context context) {
        centerX = STARTX;
        centerY = STARTY;
        speedX = sX; //default: 15?
        speedY = GRAVITY; // v_f = v_0 + aT
        animation = false;  // True when ball not dragged

        Activity a = new Activity();

        WifiP2pManager wP2p = (WifiP2pManager) context.getSystemService(Context.WIFI_P2P_SERVICE);
        WifiP2pManager.Channel wPChan = wP2p.initialize(context, context.getMainLooper(), null);
        WiFiDirectBroadcastReceiver wDBR = new WiFiDirectBroadcastReceiver(wP2p, wPChan, a);

        wDBR.onReceive(context,);
    }

    // FIXME: Must consider position of avatar in trajectory
    public void update() {
        int friction = 6;

        if (animation) {    // Calculate and change position if animation in progress
            if (centerY < FLOOR) { // in the air
                centerY += speedY; // Pos. change accordingly...
                speedY += GRAVITY; // faster Down Velocity

                if (centerY >= FLOOR) { // ...unless, the floor.
                    centerY = FLOOR;
                    speedY = 0;
                    animation = false;  // Turn off animation after this cycle

                    speedX += friction; // X on floor -> (friction)
                    if (speedX > 0) speedX = 0; // stop
                    else centerX += speedX; // slower
                } else {
                    centerX += speedX;
                }
            } else if (speedX < 0) {    // on floor, moving
                speedX += friction; // friction
                if (speedX > 0) speedX = 0; // stop
                else centerX += speedX; // slower
            }
            // else, speedy <= 0 and speedx >= 0, so ball stay still
        }
    }

    public void sendBall() {

    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isAnimation() {
        return animation;
    }

    public void setAnimation(boolean animation) {
        this.animation = animation;
    }

    public int getFLOOR() {
        return FLOOR;
    }
}
