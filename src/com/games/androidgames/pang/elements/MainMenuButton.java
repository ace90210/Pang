package com.games.androidgames.pang.elements;

import com.games.androidgames.framework.GLText;
import com.games.androidgames.framework.Game;
import com.games.androidgames.pang.MainMenuScreen;

public class MainMenuButton extends MenuButton {

	public MainMenuButton(String text, GLText glText, float x, float y,	float scale) {
		super(text, glText, x, y, scale);
	}

	@Override
	public void action(Game game) {
		game.setScreen(new MainMenuScreen(game));		
	}

}
