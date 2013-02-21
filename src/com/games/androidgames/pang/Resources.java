package com.games.androidgames.pang;

import com.games.androidgames.framework.GLText;
import com.games.androidgames.framework.gl.Texture;
import com.games.androidgames.framework.gl.TextureRegion;
import com.games.androidgames.framework.impl.GLGame;
import com.games.androidgames.framework.gl.Animation;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class Resources {	
	private static SoundPool sounds;
	public static int FIRE, POP, STICK, HIT, BUTTON_HEIGHTLIGHT;
	public static Texture background;
	public static Texture gameItems;
	public static GLText glText;
	
	public static TextureRegion backgroundRegion;
	public static TextureRegion ball;
	public static TextureRegion platform;
	public static TextureRegion ladder;
	public static TextureRegion stickSpear;

	public static TextureRegion playerShooting;
	public static TextureRegion playerStanding;
	
	public static TextureRegion powerupSingle;
	public static TextureRegion powerupDouble;
	public static TextureRegion powerupSticky;

	public static TextureRegion controlsJoystick;
	public static TextureRegion controlsStart;
	public static TextureRegion controlsA;
	public static TextureRegion controlsB;
	
	
	public static Animation playerWalkLeft;
	public static Animation playerWalkRight;
	public static Animation playerClimb;
	public static Animation spearNormal;
	public static Animation spearSticky;
	
	public static void load(GLGame game){
		game.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		sounds = new SoundPool(20,AudioManager.STREAM_MUSIC, 1);
		background = new Texture(game, "background.png");
		gameItems = new Texture(game, "BasicPangSet2.png");
		
		try {
			AssetManager assetManager = game.getAssets();
			
			FIRE = sounds.load(assetManager.openFd("fire.wav"), 1);			
			POP = sounds.load(assetManager.openFd("pop.mp3"), 1);						
			STICK= sounds.load(assetManager.openFd("stick.wav"), 1);			
			HIT = sounds.load( assetManager.openFd("hit.mp3"), 1);
			BUTTON_HEIGHTLIGHT = sounds.load(assetManager.openFd("button.mp3"), 1);
		} catch(IOException e){
			throw new RuntimeException(e);
		}
		
		backgroundRegion = new TextureRegion(background, 0, 0, 512, 256);
		
		glText = new GLText( game.getGLGraphics().getGL(), game.getAssets() );
		glText.load( "AgentOrange.ttf", 28, 2, 2 ); 
	}
	
	public static void reload() {
		glText.load( "AgentOrange.ttf", 28, 2, 2 ); 
        background.reload();
        gameItems.reload();
        
    }
	
	public static void playSound(int sound) {
		if(!Settings.mute){
			sounds.play(sound, 1.0f, 1.0f, 0, 0, 1.0f);
		}
	}	
	
	public static void playSound(int sound, float volume) {
		if(!Settings.mute){
			sounds.play(sound, volume, volume, 0, 0, 1.0f);
		}
	}
	
	public static void playSound(int sound, float left, float right) {
		if(!Settings.mute){
			sounds.play(sound, left, right, 0, 0, 1.0f);
		}
	}
}
