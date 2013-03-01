package com.games.androidgames.pang.buttons;

import com.games.androidgames.framework.GLText;
import com.games.androidgames.framework.Game;
import com.games.androidgames.framework.math.Rectangle;
import com.games.androidgames.framework.math.Vector2;

public abstract class MenuButton {
	protected final Vector2 position;
	public Rectangle bounds;
	public float r, g, b ,a, altR, altG, altB, altA, scale, waitTime, lastTimeUsed;
	public String text;
	public boolean heightLighted, soundEnabled, enabled;
	private final GLText glText;
	
	public MenuButton(String text, GLText glText, float x, float y, float r, float g, float b, float a, float scale){
		this.text = text;
		this.heightLighted = false;
		this.soundEnabled = true;
		this.position = new Vector2(x, y);
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		this.altR = 0.8f;
		this.altG = 0.0f;
		this.altB = 0.0f;
		this.altA = 1.0f;
		this.scale = scale;
		this.waitTime = 200;
		this.lastTimeUsed = 0;
		this.glText = glText;
		this.enabled = true;
		setRectangle(x, y);
	}
	
	public MenuButton(String text, GLText glText, float x, float y, float scale){
		this.text = text;
		this.heightLighted = false;
		this.soundEnabled = true;
		this.position = new Vector2(x, y);
		this.r = 0.1f;
		this.g = 0.1f;
		this.b = 0.7f;
		this.a = 1;
		this.altR = 0.2f;
		this.altG = 0.5f;
		this.altB = 1.0f;
		this.altA = 1.0f;
		this.scale = scale;
		this.glText = glText;	
		this.enabled = true;
		glText.setScale(scale);
		this.waitTime = 200;
		this.lastTimeUsed = 0;
		float width = glText.getLength(text) ;
		this.bounds = new Rectangle(x - width / 2, y, width , glText.getHeight());
	}

	public void setAltRGBA(float r, float g, float b, float a){
		this.altR = r;
		this.altG = g;
		this.altB = b;
		this.altA = a;
	}
	
	protected void setRectangle(float x, float y) {
		glText.setScale(scale);
		float width = glText.getLength(text);
		this.bounds = new Rectangle(x - width / 2, y, width , this.glText.getHeight());
	}

	protected void use(){
		this.lastTimeUsed = System.nanoTime() / 1000000;
	}
	
	public boolean ready(){
		float currTime = System.nanoTime() / 1000000;
		if(currTime - lastTimeUsed > waitTime){
			return true;
		}
		return false;
	}
	public abstract void action(Game game);
}
