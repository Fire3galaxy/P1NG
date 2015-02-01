package com.SkyLife.p1ng;

/**
 * Created by Daniel on 1/31/2015.
 */
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import com.daniel.framework.Graphics;
import com.daniel.framework.Screen;
import com.daniel.framework.Input.TouchEvent;
import com.daniel.framework.UsersBase;


public class MainMenuScreen extends Screen {
    Paint paint;

    public MainMenuScreen(UsersBase game) {
        super(game);

        paint = new Paint();
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
    }


    @Override
    public void update(float deltaTime) {
        Graphics g = u_base.getGraphics();
        List<TouchEvent> touchEvents = u_base.getInput().getTouchEvents();


        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {

                if (inBounds(event, 0, 0, 250, 250)) {
                    //START GAME
                    u_base.setScreen(new GameScreen(u_base));
                }


            }
        }
    }


    private boolean inBounds(TouchEvent event, int x, int y, int width,
                             int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }


    @Override
    public void paint(float deltaTime) {
        Graphics g = u_base.getGraphics();
        g.drawImage(Assets.bg1, 0, 0);

        g.drawARGB(100, 0, 0, 0);
        g.drawString("Tap to start the animation.", 640, 300, paint);

        g.drawRect(0, 0, 250, 250, Color.WHITE);
    }


    @Override
    public void pause() {
    }


    @Override
    public void resume() {


    }


    @Override
    public void dispose() {


    }


    @Override
    public void backButton() {
        //Display "Exit Game?" Box


    }
}
