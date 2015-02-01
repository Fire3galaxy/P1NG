package com.characters;


import android.app.Activity;

import com.daniel.framework.MyBluetooth;

/**
 * Created by Daniel on 1/31/2015.
 */
public class Ball {
    int centerX, centerY,speedX, speedY,radius;
    final int GRAVITY = 3, FLOOR = 854; // positive = DOWN
    final int STARTX = 1920, STARTY = 360; // FIXME: replace with function
    boolean animation;

    int prevX = -1, totalEventsX = 0;
    int prevY = -1, totalEventsY = 0;

    MyBluetooth myBlue;
    Activity a;

    public Ball(int sX) {
        centerX = STARTX;
        centerY = STARTY;
        speedX = sX; //default: 15?
        speedY = GRAVITY; // v_f = v_0 + aT
        animation = false;  // True when ball not dragged

//        new Thread() {
//            public void run() {
//                a = new Activity();
//
//                a.runOnUiThread(new Runnable() {
//                    public void run() {
//                        myBlue = new MyBluetooth();
//                    }
//                });
//            }
//        }.start();

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

    public void addVelocity(int currX, int currY) {
        if (prevX != -1) {
            speedX = currX - prevX;
            speedY = currY - prevY;
        }
        prevX = currX;
        prevY = currY;
    }

    public void resetPrevCoord() {
        prevX = -1;
        prevY = -1;
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
