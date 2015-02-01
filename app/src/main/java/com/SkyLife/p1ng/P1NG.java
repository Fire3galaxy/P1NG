package com.SkyLife.p1ng;

import com.daniel.framework.Screen;
import com.daniel.framework.implementation.AndroidGame;

/**
 * Created by Daniel on 1/31/2015.
 */
public class P1NG extends AndroidGame{
    @Override
    public Screen getInitScreen() {
        return new LoadingScreen(this);
    }
}
