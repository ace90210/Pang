package com.games.androidgames.pang.elements;

import com.games.androidgames.framework.GLText;
import com.games.androidgames.framework.Game;
import com.games.androidgames.pang.Settings;

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
