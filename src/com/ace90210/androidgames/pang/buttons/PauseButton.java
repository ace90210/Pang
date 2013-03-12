package com.ace90210.androidgames.pang.buttons;

import com.ace90210.androidgames.framework.GLText;
import com.ace90210.androidgames.framework.Game;
import com.ace90210.androidgames.pang.Settings;

public class PauseButton extends MenuButton {

	public PauseButton(String text, GLText glText, float x, float y, float scale) {
		super(text, glText, x, y, scale);
		
	}

	@Override
	public void action(Game game) {
		if(this.ready()){
			if(Settings.gamePaused) {
				Settings.gamePaused = false;
				this.text = "Resume";
				this.setRectangle(position.x, position.y);
			} else {
				Settings.gamePaused = true;
				this.text = "Pause";
				this.heightLighted = false;
				this.setRectangle(this.position.x, this.position.y);
			}
			this.use();
		}
	}
	
}
