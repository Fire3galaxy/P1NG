package com.SkyLife.p1ng;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.daniel.framework.Graphics;
import com.daniel.framework.Screen;
import com.daniel.framework.UsersBase;

/**
 * Created by Daniel on 1/31/2015.
 */
public class LoadingScreen extends Screen {
    Context context; //

    public LoadingScreen(UsersBase game, Context c) {
        super(game);
        context = c;
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = u_base.getGraphics();
        Assets.bg1 = g.newImage("pingbg.png", Graphics.ImageFormat.RGB565);
        Assets.avatar = g.newImage("avatarbasic.png", Graphics.ImageFormat.RGB565);
        Assets.movingball = g.newImage("firstfly.png", Graphics.ImageFormat.RGB565);
        Assets.ball = g.newImage("firstball.png", Graphics.ImageFormat.RGB565);
        Assets.whizzing = g.newImage("whix.png", Graphics.ImageFormat.RGB565);
        //Assets.click = game.getAudio().createSound("explode.ogg");

        u_base.setScreen(new MainMenuScreen(u_base, context));
    }


    @Override
    public void paint(float deltaTime) {


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


    }
}
