package com.games.androidgames.pang.elements;

import java.util.List;

import com.games.androidgames.framework.DynamicGameObject;
import com.games.androidgames.framework.GameObject;
import com.games.androidgames.framework.math.OverlapTester;

public class Ball extends DynamicGameObject{

	public Ball(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public Ball(float x, float y, float radius) {
		super(x, y, radius);
	}

	@Override
	public boolean collision(List<GameObject>... objects) {
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
