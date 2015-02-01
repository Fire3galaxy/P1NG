package com.SkyLife.p1ng;

import com.daniel.framework.Screen;
import com.daniel.framework.implementation.AndroidGame;

/**
 * Created by Daniel on 1/31/2015.
 */
public class P1NG extends AndroidGame{
    @Override
    public Screen getInitScreen() {
        return new LoadingScreen(this, this.getApplicationContext());
    }
    // Pass Context all the way up to GameScreen so that Ball can have activity that searches for peers
}
