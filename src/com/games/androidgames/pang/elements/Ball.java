package com.games.androidgames.pang.elements;

import java.util.List;

import com.games.androidgames.framework.DynamicGameObject;
import com.games.androidgames.framework.GameObject;
import com.games.androidgames.framework.math.OverlapTester;
import com.games.androidgames.pang.Settings;

public class Ball extends DynamicGameObject{
	public float bounce;
	
	public Ball(float x, float y, float width, float height) {
		super(x, y, width, height);
		bounce = 500.0f;
	}
	
	public Ball(float x, float y, float radius) {
		super(x, y, radius);
		bounce = 500.0f;
	}
}
