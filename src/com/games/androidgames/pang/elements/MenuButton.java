package com.games.androidgames.pang.elements;

import com.games.androidgames.framework.GLText;
import com.games.androidgames.framework.Game;
import com.games.androidgames.framework.math.Rectangle;
import com.games.androidgames.framework.math.Vector2;

public abstract class MenuButton {
	protected final Vector2 position;
	public Rectangle bounds;
	public float r, g, b ,a, altR, altG, altB, altA, scale;
	public String text;
	public boolean heightLighted;
	private final GLText glText;
	
	public MenuButton(String text, GLText glText, float x, float y, float r, float g, float b, float a, float scale){
		this.text = text;
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
		this.glText = glText;		
		setRectangle(x, y);
	}
	
	public MenuButton(String text, GLText glText, float x, float y, float scale){
		this.text = text;
		this.position = new Vector2(x, y);
		this.r = 1;
		this.g = 1;
		this.b = 1;
		this.a = 1;
		this.altR = 0.8f;
		this.altG = 0.0f;
		this.altB = 0.0f;
		this.altA = 1.0f;
		this.scale = scale;
		this.glText = glText;	
		glText.setScale(scale);
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
	
	public abstract void action(Game game);
}
