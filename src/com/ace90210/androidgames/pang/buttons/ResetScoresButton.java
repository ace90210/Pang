package com.ace90210.androidgames.pang.buttons;

import com.ace90210.androidgames.framework.GLText;
import com.ace90210.androidgames.framework.Game;
import com.ace90210.androidgames.framework.impl.GLGame;
import com.ace90210.androidgames.pang.Resources;

public class ResetScoresButton extends MenuButton {

	public ResetScoresButton(String text, GLText glText, float x, float y,	float scale) {
		super(text, glText, x, y, scale);
	}

	@Override
	public void action(Game game) {
		Resources.resetTopTen((GLGame)game);	
		this.text = "Scores Reset";
		setRectangle(this.position.x, this.position.y);
	}

}
