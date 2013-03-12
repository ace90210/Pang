package com.ace90210.androidgames.pang.buttons;

import com.ace90210.androidgames.framework.GLText;
import com.ace90210.androidgames.framework.Game;
import com.ace90210.androidgames.pang.GameScreen;
import com.ace90210.androidgames.pang.Settings;

public class ContinueButton extends MenuButton {
	public boolean active = false;
	public ContinueButton(String text, GLText glText, float x, float y,	float scale) {
		super(text, glText, x, y, scale);
	}

	@Override
	public void action(Game game) {
		Settings.gamePaused = false;
		game.setScreen(new GameScreen(game));		
	}
}
