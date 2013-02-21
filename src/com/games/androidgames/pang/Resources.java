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
	public static GLText glText, glButtonText;
	
	public static TextureRegion backgroundRegion;
	public static TextureRegion ball;
	public static TextureRegion platform;
	public static TextureRegion ladder;
	public static TextureRegion stickySpear;
	public static TextureRegion normalSpear;
	
	public static TextureRegion playerShooting;
	public static TextureRegion playerStanding;
	public static TextureRegion playerHurt;
	
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
	public static Animation animNormalSpear;
	public static Animation animStickySpear;
	
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
		
		ball = new TextureRegion(gameItems, 0, 0, 128, 128);
		powerupSingle =  new TextureRegion(gameItems, 50, 133, 8, 16);
		powerupDouble = new TextureRegion(gameItems, 50, 133, 16, 16);
		powerupSticky = new TextureRegion(gameItems, 31, 133, 13, 16);
		
		normalSpear = new TextureRegion(gameItems, 368,  0, 9, 245);
		stickySpear =  new TextureRegion(gameItems, 453,  0, 9, 245);
		animNormalSpear =  new Animation( 0.01f,
				  new TextureRegion(gameItems, 261,   0, 9, 245),
				  new TextureRegion(gameItems, 279,  0, 9, 245),
				  new TextureRegion(gameItems, 297,  0, 9, 245),
				  new TextureRegion(gameItems, 315, 0, 9, 245),
				  new TextureRegion(gameItems, 333,  0, 9, 245),
				  new TextureRegion(gameItems, 351, 0, 9, 245),
				  new TextureRegion(gameItems, 368,  0, 9, 245));
		
		animStickySpear = new Animation( 0.01f,
				  new TextureRegion(gameItems, 262,   247, 9, 245),
				  new TextureRegion(gameItems, 280,  247, 9, 245),
				  new TextureRegion(gameItems, 298,  247, 9, 245),
				  new TextureRegion(gameItems, 316, 247, 9, 245),
				  new TextureRegion(gameItems, 334,  247, 9, 245),
				  new TextureRegion(gameItems, 352, 247, 9, 245),
				  new TextureRegion(gameItems, 369,  247, 9, 245));
		
		playerWalkLeft = new Animation( 0.1f,
				  new TextureRegion(gameItems, 0,   349, 26, 33),
				  new TextureRegion(gameItems, 36,  349, 26, 33),
				  new TextureRegion(gameItems, 68,  349, 26, 33),
				  new TextureRegion(gameItems, 101, 349, 26, 33),
				  new TextureRegion(gameItems, 98,  317, 26, 33));
		playerWalkRight = new Animation( 0.1f,
					  new TextureRegion(gameItems, 0,   248, 26, 33),
					  new TextureRegion(gameItems, 34,  248, 26, 33),
					  new TextureRegion(gameItems, 69,  248, 26, 33),
					  new TextureRegion(gameItems, 103, 247, 26, 33),
					  new TextureRegion(gameItems, 1,   282, 26, 33));
		playerClimb =    new Animation( 0.2f,
					  new TextureRegion(gameItems, 163, 248, 26, 33),
					  new TextureRegion(gameItems, 69,  281, 26, 33),
					  new TextureRegion(gameItems, 1,   316, 26, 33));
		playerStanding = new TextureRegion(gameItems, 68, 350, 26, 33);
		playerShooting = new TextureRegion(gameItems, 33, 280, 26, 33);
		playerHurt = new TextureRegion(gameItems, 196, 251, 26, 33);
		
		controlsA = new TextureRegion(gameItems, 128, 0, 64, 64); 
		controlsB = new TextureRegion(gameItems, 192, 0, 64, 64);
		controlsStart = new TextureRegion(gameItems, 194, 69, 63, 26);
		controlsJoystick = new TextureRegion(gameItems, 194, 130, 62, 63); 
		
		glText = new GLText( game.getGLGraphics().getGL(), game.getAssets() );
		glButtonText = new GLText( game.getGLGraphics().getGL(), game.getAssets() );
		
		glText.load( "ARIAL.TTF", 18, 2, 2 ); 
		glButtonText.load( "AgentOrange.ttf", 28, 2, 2 ); 
	}
	
	public static void reload() {
		glText.load( "ARIAL.TTF", 18, 2, 2 ); 
		glButtonText.load( "AgentOrange.ttf", 28, 2, 2 ); 
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
