package com.games.androidgames.pang.elements;

import java.util.List;

import com.games.androidgames.framework.GameObject;

public class Tile extends GameObject {

	public Tile(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean collision(List<GameObject>... objects) {
		// TODO Auto-generated method stub
		return false;
	}

}
