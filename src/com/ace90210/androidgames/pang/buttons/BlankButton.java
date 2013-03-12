package com.ace90210.androidgames.pang.buttons;

import com.ace90210.androidgames.framework.GLText;
import com.ace90210.androidgames.framework.Game;

public class BlankButton extends MenuButton {

	public BlankButton(String text, GLText glText, float x, float y,	float scale) {
		super(text, glText, x, y, scale);
		this.r = 1.0f;
		this.g = 0.1f;
		this.b = 0.1f;
		this.altR = this.r;
		this.altG = this.g;
		this.altB = this.b;
		this.altA = this.a;
		this.soundEnabled = false;
		this.enabled = false;
	}

	@Override
	public void action(Game game) {
		//Non needed		
	}

}
