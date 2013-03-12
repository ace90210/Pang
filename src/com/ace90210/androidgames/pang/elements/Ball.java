package com.ace90210.androidgames.pang.elements;

import java.util.List;

import com.ace90210.androidgames.framework.DynamicGameObject;
import com.ace90210.androidgames.framework.GameObject;
import com.ace90210.androidgames.framework.math.OverlapTester;
import com.ace90210.androidgames.pang.Settings;

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
