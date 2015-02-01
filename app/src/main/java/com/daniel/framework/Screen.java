package com.daniel.framework;

public abstract class Screen {
    protected final UsersBase u_base;

    public Screen(UsersBase ub) {
        this.u_base = ub;
    }

    public abstract void update(float deltaTime);

    public abstract void paint(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
   
    public abstract void backButton();
}
