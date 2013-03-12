package com.ace90210.androidgames.framework;

import com.ace90210.androidgames.framework.math.Vector2;

public abstract class DynamicGameObject extends GameObject {
	public final Vector2 velocity;
	public final Vector2 accel;
	
	public DynamicGameObject(float x, float y, float width, float height) {
		super(x, y, width, height);
		velocity = new Vector2();
		accel = new Vector2();
	}
	
	public DynamicGameObject(float x, float y, float radius) {
		super(x, y, radius);
		velocity = new Vector2();
		accel = new Vector2();
	}
	
}
