package com.daniel.framework.implementation;

/**
 * Created by Daniel on 1/31/2015.
 */
import java.util.List;

import android.view.View.OnTouchListener;

import com.daniel.framework.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<TouchEvent> getTouchEvents();
}
