package com.ace90210.androidgames.pang.buttons;

import com.ace90210.androidgames.framework.GLText;
import com.ace90210.androidgames.framework.Game;
import com.ace90210.androidgames.pang.Settings;

public class TouchVisibleButton extends MenuButton {

	public TouchVisibleButton(String text, GLText glText, float x, float y, float scale) {
		super(text, glText, x, y, scale);
		
	}

	@Override
	public void action(Game game) {
		if(Settings.displayTouchControls) {
			Settings.displayTouchControls = false;
			this.text = "Show Touch Controls";
			this.setRectangle(position.x, position.y);
		} else {
			Settings.displayTouchControls = true;
			this.text = "Hide Touch Controls";
			this.setRectangle(this.position.x, this.position.y);
		}
	}
	
}
