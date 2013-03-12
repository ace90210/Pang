package com.ace90210.androidgames.pang.elements;

import java.util.List;
import java.util.ArrayList;

import com.ace90210.androidgames.framework.gl.Texture;
import com.ace90210.androidgames.framework.gl.TextureRegion;
import com.ace90210.androidgames.pang.Resources;
import com.ace90210.androidgames.pang.Settings;

public class TouchControls {
	public List<TouchElement> touchElements;
	public enum ButtonList {STICK, A, B, START };
	private TextureRegion aRegion, bRegion, startRegion, stickRegion;
	public boolean[] activeButtons;
	public int left, right, up, down;
	public float DEADZONE = 25;
	
	public TouchControls(Texture texture, float worldWidth, float worldHeight) {
		touchElements = new ArrayList<TouchElement>();
		
		aRegion = Resources.controlsA;
		bRegion = Resources.controlsB;
		startRegion = Resources.controlsStart;
		stickRegion = Resources.controlsJoystick;	
		
		touchElements.add(new TouchElement(100, 110,  175,  175, stickRegion));			//joystick	
		touchElements.get(0).setBothBoundsBuffers(10);		
		
		touchElements.add(new TouchElement(worldWidth - 220, 42, 70, 70, aRegion));		//A
		touchElements.get(1).setBothBoundsBuffers(15);
		touchElements.get(1).setWait(200);
		
		touchElements.add(new TouchElement(worldWidth - 92, 42, 70, 70, bRegion));		//B
		touchElements.get(2).setBothBoundsBuffers(5);
		touchElements.get(2).setWait(300);
		
		touchElements.add(new TouchElement(worldWidth / 2, 42, 128, 50, startRegion));	//Start
		touchElements.get(3).setBoundsWidthBuffer(40);
		touchElements.get(3).setBoundsHeightBuffer(20);
		touchElements.get(3).setWait(300);	
		
		this.left = -1;
		this.right = -1;
		this.up = -1;
		this.down = -1;
		activeButtons = new boolean[touchElements.size()];
		resetActiveButtons();
	}

	public TouchElement getElement(ButtonList button) {
		TouchElement element;
		switch(button) {
			case STICK:	element = touchElements.get(0); break;			
			case A:		element = touchElements.get(1); break;
			case B:		element = touchElements.get(2); break;
			default: 	element = touchElements.get(3); break;
		}
		return element;
	}
	
	public void resetActiveButtons() {
		for(int i = 0; i < activeButtons.length; i++) {
			activeButtons[i] = false;
		}
	}
}
