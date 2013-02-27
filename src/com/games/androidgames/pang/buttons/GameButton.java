package com.games.androidgames.pang.buttons;

import com.games.androidgames.framework.GLText;
import com.games.androidgames.framework.Game;
import com.games.androidgames.pang.GameScreen;
import com.games.androidgames.pang.Settings;

public class GameButton extends MenuButton {
	public boolean active = false;
	public GameButton(String text, GLText glText, float x, float y,	float scale) {
		super(text, glText, x, y, scale);
	}

	@Override
	public void action(Game game) {
		Settings.gamePaused = false;
		game.setScreen(new GameScreen(game));		
	}
}
