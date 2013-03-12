package com.ace90210.androidgames.pang;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.ace90210.androidgames.framework.Screen;
import com.ace90210.androidgames.framework.impl.GLGame;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Start extends GLGame {

	boolean firstTimeCreate = true;
	    
	public Screen getStartScreen() {
        Settings.SCALE_HEIGHT = ( getGLGraphics().getHeight() / Settings.WORLD_HEIGHT);
        Settings.SCALE_WIDTH = ( getGLGraphics().getWidth() / Settings.WORLD_WIDTH);
		Resources.load(this);
		//wait for resources to be ready
		while(!Resources.ready);
	    return new MainMenuScreen(this);
	}
	    
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {         
        super.onSurfaceCreated(gl, config);
        Settings.SCALE_HEIGHT = ( getGLGraphics().getHeight() / Settings.WORLD_HEIGHT);
        Settings.SCALE_WIDTH = ( getGLGraphics().getWidth() / Settings.WORLD_WIDTH);
        
        if(!Resources.ready){
        	Resources.reload(); 
        	//wait for resources to be ready
        	while(!Resources.ready);
        }     
    }   
    
    @Override
    public void onPause() {
        super.onPause();
        Resources.unload();
        if(this.isFinishing()){
        	//Resources.unload();
        }
    }
}