package com.ace90210.androidgames.pang;

import com.ace90210.androidgames.framework.GLText;
import com.ace90210.androidgames.framework.gl.Animation;
import com.ace90210.androidgames.framework.gl.Texture;
import com.ace90210.androidgames.framework.gl.TextureRegion;
import com.ace90210.androidgames.framework.impl.GLGame;

import java.io.IOException;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

public class Resources {	
	private static SoundPool sounds;
	private static final String PREFS_NAME = "PangScores";
	public static boolean ready;
	public static int FIRE, POP, STICK, HIT, BUTTON_HEIGHTLIGHT;
	public static Texture background;
	public static Texture level2;
	public static Texture level3;
	public static Texture level4;	
	public static Texture backgroundMenu;
	public static Texture gameItems;
	public static GLText glText, glButtonText, glScoreText;
	
	public static TextureRegion backgroundRegion;
	public static TextureRegion level2Region;
	public static TextureRegion level3Region;
	public static TextureRegion level4Region;
	public static TextureRegion backgroundMenuRegion;
	
	
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
	
	public static TextureRegion life;
	
	public static Animation playerWalkLeft;
	public static Animation playerWalkRight;
	public static Animation playerClimb;
	public static Animation animNormalSpear;
	public static Animation animStickySpear;	
	
	public static void load(GLGame game){
		ready = false;
		game.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		sounds = new SoundPool(20,AudioManager.STREAM_MUSIC, 1);
		background = new Texture(game, "background.png");
		level2 = new Texture(game, "leveltwo.png");
		level3 = new Texture(game, "levelthree.png");
		level4 = new Texture(game, "levelfour.png");
		backgroundMenu = new Texture(game, "desert.png");
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
		level2Region = new TextureRegion(level2, 0, 0, 351, 245);
		level3Region = new TextureRegion(level3, 0, 0, 351, 245);
		level4Region = new TextureRegion(level4, 0, 0, 351, 245);
		backgroundMenuRegion = new TextureRegion(backgroundMenu, 0, 0, 512, 256);
		
		ball = new TextureRegion(gameItems, 0, 0, 128, 128);
		powerupSingle =  new TextureRegion(gameItems, 50, 133, 8, 16);
		powerupDouble = new TextureRegion(gameItems, 50, 133, 16, 16);
		powerupSticky = new TextureRegion(gameItems, 31, 133, 13, 16);
		
		life = new TextureRegion(gameItems, 193, 286, 25, 25);
		
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
		glScoreText = new GLText(game.getGLGraphics().getGL(), game.getAssets() );
		
		glText.load( "ARIAL.TTF", 18, 2, 2 ); 
		glButtonText.load( "burgerfont.ttf", 28, 2, 2 ); 
		glScoreText.load("score2.ttf", 22, 2, 2 ); 
		ready = true;
	}
	
	public static void reload() {
		ready = false;
		glText.load( "ARIAL.TTF", 18, 2, 2 ); 
		glButtonText.load( "burgerfont.ttf", 28, 2, 2 ); 
		glScoreText.load( "score2.ttf", 22, 2, 2 );
        background.reload();
        level2.reload();
        level3.reload();
        level4.reload();
        backgroundMenu.reload();
        gameItems.reload();
        ready = true;
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
	
	public static void unload(){
		ready = false;
		sounds.release();
		background.dispose();
		gameItems.dispose();
		level2.dispose();
        level3.dispose();
        level4.dispose();
        backgroundMenu.dispose();
	}
	
	/*
	 * returns top ten player slot, 0 if not in top ten
	 */
	public static int checkScore(GLGame game, int score){
		SharedPreferences settings = game.getSharedPreferences(PREFS_NAME, 0);
		int[] scores = new int[10];
		for(int i =0;i<scores.length; i++){
			scores[i] = settings.getInt("score" + i, -1);
			if(score > scores[i]){
			    SharedPreferences.Editor editor = settings.edit();		
			    
			    //shift rest down one
			    for(int j = scores.length - 1; j > i; j--){			    	
			    	editor.putInt("score" + j,  settings.getInt("score" + (j - 1), -1));
				    editor.commit();
			    }
			    editor.putInt("score" + i, score);
			    editor.commit();
				return i + 1;
			}
		}
	    return -1;
	}
	
	public static int[] getScores(GLGame game){
		SharedPreferences settings = game.getSharedPreferences(PREFS_NAME, 0);
		int[] scores = new int[10];
		for(int i =0;i<scores.length; i++){
			scores[i] = settings.getInt("score" + i, -1);
		}
		return scores;
	}
	
	public static void resetTopTen(GLGame game){
		SharedPreferences settings = game.getSharedPreferences(PREFS_NAME, 0);
		int[] scores = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		for(int i =0;i<scores.length; i++){
		    SharedPreferences.Editor editor = settings.edit();
		    editor.putInt("score" + i, scores[i]);
		    editor.commit();
		}
	}
}
