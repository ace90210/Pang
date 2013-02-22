package com.games.androidgames.pang.buttons;

import com.games.androidgames.framework.GLText;
import com.games.androidgames.framework.Game;
import com.games.androidgames.pang.HelpScreen;
import com.games.androidgames.pang.Resources;

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
