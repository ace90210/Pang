package com.games.androidgames.pang.elements;

import java.util.List;

import com.games.androidgames.framework.DynamicGameObject;
import com.games.androidgames.framework.GameObject;
import com.games.androidgames.framework.math.OverlapTester;
import com.games.androidgames.pang.Settings;

public class Ball extends DynamicGameObject{

	public Ball(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public Ball(float x, float y, float radius) {
		super(x, y, radius);
	}
}
