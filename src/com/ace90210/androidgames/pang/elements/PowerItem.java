package com.ace90210.androidgames.pang.elements;

import java.util.List;

import com.ace90210.androidgames.framework.DynamicGameObject;
import com.ace90210.androidgames.framework.GameObject;
import com.ace90210.androidgames.framework.gl.TextureRegion;
import com.ace90210.androidgames.framework.math.OverlapTester;
import com.ace90210.androidgames.framework.math.Rectangle;
import com.ace90210.androidgames.framework.math.Vector2;
import com.ace90210.androidgames.pang.Settings;
import com.ace90210.androidgames.pang.elements.Weapon.MODE;

public class PowerItem extends DynamicGameObject {
	public float fallSpeed, lifeLimit, life;
	public int value;
	public MODE mode;
	public TextureRegion region;
	public boolean alive;
	
	public PowerItem(TextureRegion region, float x, float y, float width, float height, float life, MODE mode) {
		super(x, y, width, height);
		this.region = region;
		this.alive = false;
		this.fallSpeed = -200;
		switch(mode){
		case SINGLE: value = 100; break;
		case DOUBLE: value = 400; break;
		case STICKY: value = 1500; break;
		}
		this.mode = mode;
		this.life = life;
		lifeLimit = life;
	}
	
	public void update(float deltaTime, List<GameObject> ... objects) {
		if(alive) {
			life -= deltaTime;
			if(life < 0) {
				alive = false;
			}		
			if(!collision(objects)) {
				position.add(0, fallSpeed * deltaTime);
				bounds.lowerLeft.add(0, fallSpeed * deltaTime);
			}
		}
	}
	
	public void setPosition(Vector2 pos) {
		life = lifeLimit;
		position.set(pos.x, pos.y);
		updateBounds(pos.x, pos.y, bounds.width, bounds.height);
	}

	public boolean collision(List<GameObject> ... objects) {
		boolean collision = false;
		if(bounds.lowerLeft.y <= 0) {
			position.y = bounds.height / 2;
			collision = true;
		}
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
