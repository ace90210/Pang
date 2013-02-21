package com.games.androidgames.pang.buttons;

import com.games.androidgames.framework.GLText;
import com.games.androidgames.framework.Game;
import com.games.androidgames.pang.SettingsScreen;

public class SettingsButton extends MenuButton {

	public SettingsButton(String text, GLText glText, float x, float y,	float scale) {
		super(text, glText, x, y, scale);
	}

	@Override
	public void action(Game game) {
		game.setScreen(new SettingsScreen(game));		
	}

}
