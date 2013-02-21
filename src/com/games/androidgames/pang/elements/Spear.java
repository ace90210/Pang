package com.games.androidgames.pang.elements;

import java.util.List;

import com.games.androidgames.framework.DynamicGameObject;
import com.games.androidgames.framework.GameObject;
import com.games.androidgames.framework.gl.TextureRegion;
import com.games.androidgames.framework.gl.Texture;
import com.games.androidgames.framework.gl.Animation;
import com.games.androidgames.framework.math.OverlapTester;
import com.games.androidgames.pang.Resources;

public class Spear extends DynamicGameObject {	
	private float speed, movingTime;
	private float realHeight;	
	private Animation anim;
	private float life, heightLimit;
	public float activeTime;
	public boolean alive, stick;
	public TextureRegion region, stickRegion;
	
	//life > 0 = sticky
	public Spear(float x, float y, float width, float height, float speed, float life, Texture texture) {
		super(x, y, width, height);
		this.alive = false;
		if(life > 0) {
			this.anim =   Resources.animStickySpear;
			this.stickRegion = Resources.stickySpear;
		} else {
			this.anim =  Resources.animNormalSpear;			
		}

		this.region =  Resources.normalSpear;
		this.realHeight = height;
		this.heightLimit = 460;
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
			realHeight += speed * deltaTime * 2;
			position.y += speed * deltaTime;
			updateBounds(position.x, position.y, bounds.width, realHeight );
		}
	}
	
	public void shoot(float x, float y) {	
		stick = false;
		alive = true;
		this.realHeight = 64;
		this.position.set(x, y);
		updateBounds(position.x, position.y, bounds.width, realHeight );	
	}
	
	public void stop() {
		activeTime = life;		
		if(activeTime <= 0) {
			stick = false;
			alive = false;
		} else {
			stick = true;
			Resources.playSound(Resources.STICK);
		}
	}
	
	public void kill(){
		alive = false;
	}

	@Override
	public boolean collision(List<GameObject>... objects) {
		boolean collision = false;
		for(List<GameObject> objectList: objects) {
			for(GameObject object: objectList) {
				if(OverlapTester.overlapRectangle(this.bounds, object.bounds)) {					
					collision = true;
				}			
			}	
		}
		return collision;
	}
}
