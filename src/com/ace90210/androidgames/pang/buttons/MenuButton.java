package com.ace90210.androidgames.pang.buttons;

import java.util.List;

import com.ace90210.androidgames.framework.GLText;
import com.ace90210.androidgames.framework.Game;
import com.ace90210.androidgames.framework.math.Rectangle;
import com.ace90210.androidgames.framework.math.Vector2;

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
	
	public static int findNextButton(int start, List<MenuButton> items){
		if(start == -1 || start >= items.size()){
			start = 0;
			while(!items.get(start).enabled){
				if(start == items.size() - 1){
					start = 0;
				} else {
						start++;
				}
			}
		} else {
			do
			{
				items.get(start).heightLighted = false;
				if(start == items.size() - 1){
					start = 0;
				} else {
						start++;
				}
			}while(!items.get(start).enabled);	
		}
		return start;
	}
	
	public static int findPreviousButton(int start, List<MenuButton> items){
		if(start < 1){
			start = items.size() - 1;
			
			while(!items.get(start).enabled){
				if(start == 0){
					start = items.size() - 1;
				} else {
						start--;
				}
			}
		} else { 
			do
			{
				items.get(start).heightLighted = false;
				if(start == 0){
					start = items.size() - 1;
				} else {
						start--;
				}
			}while(!items.get(start).enabled);
	 	}
		return start;
	}
	
	public abstract void action(Game game);
}
