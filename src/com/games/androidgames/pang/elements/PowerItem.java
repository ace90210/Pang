package com.games.androidgames.pang.elements;

import java.util.List;

import com.games.androidgames.framework.DynamicGameObject;
import com.games.androidgames.framework.math.OverlapTester;
import com.games.androidgames.framework.math.Rectangle;
import com.games.androidgames.framework.math.Vector2;
import com.games.androidgames.framework.GameObject;
import com.games.androidgames.framework.gl.TextureRegion;
import com.games.androidgames.pang.elements.Weapon.MODE;

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

	@Override
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
