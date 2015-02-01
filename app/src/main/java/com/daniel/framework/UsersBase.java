package com.daniel.framework;

public interface UsersBase {
//	public Audio getAudio();
	public Input getInput();
	public FileIO getFileIO();
	public Graphics getGraphics();
	public void setScreen(Screen screen);
	public Screen getCurrentScreen();
    public Screen getInitScreen();
}
