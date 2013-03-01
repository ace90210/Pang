package com.games.androidgames.pang;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.games.androidgames.framework.Screen;
import com.games.androidgames.framework.impl.GLGame;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Start extends GLGame {

	boolean firstTimeCreate = true;
	    
	public Screen getStartScreen() {
        Settings.SCALE_HEIGHT = ( getGLGraphics().getHeight() / Settings.WORLD_HEIGHT);
        Settings.SCALE_WIDTH = ( getGLGraphics().getWidth() / Settings.WORLD_WIDTH);
        Settings.currentLevel = 1;
		Resources.load(this);
	    return new MainMenuScreen(this);
	}
	    
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {         
        super.onSurfaceCreated(gl, config);
        Settings.SCALE_HEIGHT = ( getGLGraphics().getHeight() / Settings.WORLD_HEIGHT);
        Settings.SCALE_WIDTH = ( getGLGraphics().getWidth() / Settings.WORLD_WIDTH);
        Resources.reload();
    }   
    
    @Override
    public void onPause() {
        super.onPause();
    }
}