package com.games.androidgames.pang.buttons;

import com.games.androidgames.framework.GLText;
import com.games.androidgames.framework.Game;
import com.games.androidgames.pang.Settings;

public class TouchVisibleButton extends MenuButton {

	public TouchVisibleButton(String text, GLText glText, float x, float y, float scale) {
		super(text, glText, x, y, scale);
		
	}

	@Override
	public void action(Game game) {
		if(Settings.displayTouchControls) {
			Settings.displayTouchControls = false;
			this.text = "Hide Touch Controls";
			this.setRectangle(position.x, position.y);
		} else {
			Settings.displayTouchControls = true;
			this.text = "Show Touch Controls";
			this.setRectangle(this.position.x, this.position.y);
		}
	}
	
}
