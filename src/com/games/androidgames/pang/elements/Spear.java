package com.games.androidgames.pang.elements;

import java.util.List;

import com.games.androidgames.framework.DynamicGameObject;
import com.games.androidgames.framework.GameObject;
import com.games.androidgames.framework.gl.TextureRegion;
import com.games.androidgames.framework.gl.Texture;
import com.games.androidgames.framework.gl.Animation;
import com.games.androidgames.framework.math.OverlapTester;
import com.games.androidgames.pang.Resources;
import com.games.androidgames.pang.Settings;

public class Spear extends DynamicGameObject {	
	private float speed, movingTime;
	private float realHeight;	
	private Animation anim;
	private float life, heightLimit;
	public float activeTime;
	public boolean alive, stick;
	public TextureRegion region, stickRegion;
	
	//life > 0 = sticky
	public Spear(float x, float y, float width, float height, float speed, float life, float screenHeight, Texture texture) {
		super(x, y, width * Settings.SCALE_WIDTH, height * Settings.SCALE_HEIGHT);
		this.alive = false;
		if(life > 0) {
			this.anim =   Resources.animStickySpear;
			this.stickRegion = Resources.stickySpear;
		} else {
			this.anim =  Resources.animNormalSpear;			
		}

		this.region =  Resources.normalSpear;
		this.realHeight = height;
		this.heightLimit = screenHeight * Settings.SCALE_HEIGHT;
		this.speed = speed;
		this.movingTime = 0;
		this.life = life;
		this.stick = false;
	}
	
	public void setHeightLimit(float limit) {
		this.heightLimit = limit;
	}
	
	public void update(float deltaTime) {			
		movingTime += deltaTime;			
		
		if(stick) {
			activeTime -= deltaTime;
			region = stickRegion;
			if(activeTime <= 0) {
				stick = false;
				alive = false;
			}
		} else {
			if(position.y + (bounds.height / 2) >= heightLimit) {
				position.y = heightLimit - (bounds.height / 2);
				stop();
			}
			region = anim.getKeyFrame(movingTime, Animation.ANIMATION_LOOPING);
			realHeight += speed * deltaTime  * Settings.SCALE_HEIGHT * 2;
			position.y += speed * deltaTime * Settings.SCALE_HEIGHT;
			updateBounds(position.x, position.y, bounds.width, realHeight );
		}
	}
	
	public void shoot(float x, float y) {	
		stick = false;
		alive = true;
		this.realHeight = 64 * Settings.SCALE_HEIGHT;
		this.position.set(x, y);
		updateBounds(position.x, position.y, bounds.width, realHeight );	
	}
	
	public void stop() {
		if(alive){
			activeTime = life;		
			if(activeTime <= 0) {
				stick = false;
				alive = false;
			} else {
				stick = true;
				Resources.playSound(Resources.STICK);
			}
		}
	}
	
	public void kill(){
		alive = false;
	}

}
