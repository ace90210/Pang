package com.ace90210.androidgames.pang.buttons;

import com.ace90210.androidgames.framework.GLText;
import com.ace90210.androidgames.framework.Game;
import com.ace90210.androidgames.pang.MainMenuScreen;

public class MainMenuButton extends MenuButton {

	public MainMenuButton(String text, GLText glText, float x, float y,	float scale) {
		super(text, glText, x, y, scale);
	}

	@Override
	public void action(Game game) {
		game.setScreen(new MainMenuScreen(game));		
	}

}
