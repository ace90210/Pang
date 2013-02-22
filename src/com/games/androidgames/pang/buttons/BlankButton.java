package com.games.androidgames.pang.buttons;

import com.games.androidgames.framework.GLText;
import com.games.androidgames.framework.Game;
import com.games.androidgames.pang.HelpScreen;

public class BlankButton extends MenuButton {

	public BlankButton(String text, GLText glText, float x, float y,	float scale) {
		super(text, glText, x, y, scale);
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
