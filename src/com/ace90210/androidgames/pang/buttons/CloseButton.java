package com.ace90210.androidgames.pang.buttons;

import com.ace90210.androidgames.framework.GLText;
import com.ace90210.androidgames.framework.Game;
import com.ace90210.androidgames.pang.Resources;

public class CloseButton extends MenuButton {

	public CloseButton(String text, GLText glText, float x, float y,	float scale) {
		super(text, glText, x, y, scale);
	}

	@Override
	public void action(Game game) {
		Resources.unload();
		System.exit(0);	
	}

}
