package com.ace90210.androidgames.pang.buttons;

import com.ace90210.androidgames.framework.GLText;
import com.ace90210.androidgames.framework.Game;
import com.ace90210.androidgames.pang.Settings;

public class MuteButton extends MenuButton {

	public MuteButton(String text, GLText glText, float x, float y, float scale) {
		super(text, glText, x, y, scale);
		
	}

	@Override
	public void action(Game game) {
		if(Settings.mute) {
			Settings.mute = false;
			this.text = "mute";
			this.setRectangle(position.x, position.y);
		} else {
			Settings.mute = true;
			this.text = "un-mute";
			this.setRectangle(this.position.x, this.position.y);
		}
	}
	
}
