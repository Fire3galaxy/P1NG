package com.SkyLife.p1ng;

/**
 * Created by Daniel on 1/31/2015.
 */
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;


import com.characters.Avatar;
import com.characters.Ball;
import com.daniel.framework.Graphics;
import com.daniel.framework.Image;
import com.daniel.framework.Input.TouchEvent;
import com.daniel.framework.Screen;
import com.daniel.framework.UsersBase;

public class GameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver
    }

//    GameState state = GameState.Ready; // FIXME: Redo this with opening welcome thing, once bugs fixed!
    GameState state = GameState.Running;

    // Variable Setup
    // You would create game objects here.
    Avatar av;
    Ball b;

    int livesLeft = 1;
    Paint paint;

    public GameScreen(UsersBase game) {
        super(game);

        // Initialize game objects here
        av = new Avatar();
        b = new Ball(-60); // speedX default value should be -60

        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(20);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        b.setAnimation(true); // Can enable menu later to prevent animation from ALWAYS running first
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = u_base.getInput().getTouchEvents();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different update methods.
        // Refer to Unit 3's code. We did a similar thing without separating the
        // update methods.

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List<TouchEvent> touchEvents) {

        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game begins.
        // state now becomes GameState.Running.
        // Now the updateRunning() method will be called!

        if (touchEvents.size() > 0)
            state = GameState.Running;
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        //This is identical to the update() method from our Unit 2/3 game.

        Graphics g = u_base.getGraphics();
        // 1. All touch input is handled here:
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);

            if (event.type == TouchEvent.TOUCH_DRAGGED) {
                b.setCenterX(event.x);
                b.setCenterY(event.y);
                b.update();
            }

            if (event.type == TouchEvent.TOUCH_DOWN) {
                b.setCenterX(event.x);
                b.setCenterY(event.y);
            }

            if (event.type == TouchEvent.TOUCH_UP) {
                b.setSpeedX(0); // FIXME: Cheating solution.
                b.setAnimation(true);
            }
        }

        // 2. Check miscellaneous events like death:
        if (livesLeft == 0) {
            state = GameState.GameOver;
        }

        // 3. Call individual update() methods here.
        // This is where all the game updates happen.
        // For example, robot.update();
        b.update();
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                state = GameState.Running;
                break;
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 300 && event.x < 980 && event.y > 100
                        && event.y < 500) {
                    nullify();
                    u_base.setScreen(new MainMenuScreen(u_base));
                    return;
                }
            }
        }

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = u_base.getGraphics();

        // First draw the game elements.
        g.drawImage(Assets.bg1, 0, 0);
        g.drawImage(Assets.movingball, b.getCenterX(), b.getCenterY());
        g.drawImage(Assets.avatar, av.getCenterX(), av.getCenterY());

        // Example:
        // g.drawImage(Assets.background, 0, 0);
        // g.drawImage(Assets.character, characterX, characterY);

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();
    }

    private void nullify() {
        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;
        av = null;
        b = null;

        // Call garbage collector to clean up memory.
        System.gc();
    }

    private void drawReadyUI() {
        Graphics g = u_base.getGraphics();

        g.drawARGB(155, 0, 0, 0); // Shaded
        g.drawString("Tap to start the animation.", 640, 300, paint);
    }

    private void drawRunningUI() {
        Graphics g = u_base.getGraphics();
    }

    private void drawPausedUI() {
        Graphics g = u_base.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);
        paint.setTextSize(50);
        g.drawString("Paused", 640, 300, paint);
    }

    private void drawGameOverUI() {
        Graphics g = u_base.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("GAME OVER.", 640, 300, paint);
    }

    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }
}
