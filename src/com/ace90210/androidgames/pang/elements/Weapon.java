package com.ace90210.androidgames.pang.elements;

import com.ace90210.androidgames.framework.DynamicGameObject;
import com.ace90210.androidgames.framework.GameObject;
import com.ace90210.androidgames.framework.gl.Animation;
import com.ace90210.androidgames.framework.gl.Texture;
import com.ace90210.androidgames.framework.gl.TextureRegion;
import com.ace90210.androidgames.framework.math.Circle;
import com.ace90210.androidgames.framework.math.OverlapTester;
import com.ace90210.androidgames.pang.Settings;


import java.util.List;
import java.util.ArrayList;

public class Weapon {
	public Spear spear, spear2, stickySpear;
	public static enum MODE {SINGLE, DOUBLE, STICKY};
	public MODE mode;
	public boolean alive;
	
	public Weapon(Texture texture, float stickTime, float screenHeight){
		this.spear = new Spear(0, 0, 20, 64, 150, 0, screenHeight, texture);	
		this.spear2 = new Spear(0, 0, 20, 64, 150, 0, screenHeight, texture);	
		this.stickySpear = new Spear(0, 0, 20, 64, 150, stickTime, screenHeight, texture);	
		this.mode = MODE.SINGLE;
		this.spear.alive = false;
		this.spear2.alive = false;
		this.stickySpear.alive = false;
		this.alive = false;
	}
	
	public void update(float deltaTime, List<GameObject> objects) {
		if(!spear.alive && !spear2.alive && !stickySpear.alive) {
			alive = false;
		} else {
			alive = true;
		}
		switch(mode) {
			case DOUBLE: {
							stickySpear.alive = false;
							spear.update(deltaTime);
							if(!spear.stick) {
								for(int i = 0; i < objects.size(); i++) {
									GameObject object = objects.get(i);
									 if(OverlapTester.overlapRectangle(object.bounds, spear.bounds)) {
										 spear.stop();
									 }
								}
							}
							spear2.update(deltaTime);
							if(!spear2.stick) {
								for(int i = 0; i < objects.size(); i++) {
									GameObject object = objects.get(i);
									 if(OverlapTester.overlapRectangle(object.bounds, spear2.bounds)) {
										 spear2.stop();
									 }
								}
							}
						 } break;
			case STICKY: {
							stickySpear.update(deltaTime);
							if(!stickySpear.stick) {
								for(int i = 0; i < objects.size(); i++) {
									GameObject object = objects.get(i);
									 if(OverlapTester.overlapRectangle(object.bounds, stickySpear.bounds)) {
										 stickySpear.stop();
									 }
								}
							}
							spear.alive = false;
							spear2.alive = false;
						 } break;
			default: {
						stickySpear.alive = false;
						spear.update(deltaTime);
						if(!spear.stick) {
							for(int i = 0; i < objects.size(); i++) {
								GameObject object = objects.get(i);
								 if(OverlapTester.overlapRectangle(object.bounds, spear.bounds)) {
									 spear.stop();
								 }
							}
						}
						spear2.alive = false;
					 }
		}
	}
	public boolean checkCollisions(Circle circle) {
		switch(mode) {
		case DOUBLE: {
						if(spear.alive && OverlapTester.overlapCircleRectangle(circle, spear.bounds)) {
							spear.alive = false;
							return true;
						}
						if(spear2.alive && OverlapTester.overlapCircleRectangle(circle, spear2.bounds)) {
							spear2.alive = false;
							return true;
						}
					} break;
		case STICKY: {
						if(stickySpear.alive && OverlapTester.overlapCircleRectangle(circle, stickySpear.bounds)) {
							stickySpear.alive = false;
							return true;
						}
					} break;
			default: {
						if(spear.alive && OverlapTester.overlapCircleRectangle(circle, spear.bounds)) {
							spear.alive = false;
							return true;
						}
					}
		}
		return false;
	}
	
	public void setHeightLimit(float limit) {
		this.spear.setHeightLimit(limit);
		this.spear2.setHeightLimit(limit);
		this.stickySpear.setHeightLimit(limit);
	}
	
	public boolean shoot(float x, float y) {
		alive = true;
		switch(mode) {
			case DOUBLE: {
							if(!stickySpear.alive) {
								if(!spear.alive) {
									spear.shoot(x, y);
									return true;
								} else if(!spear2.alive) {
									spear2.shoot(x, y);
									return true;
								}
							}
						 } break;
			case STICKY: {
							if(!spear.alive && !spear2.alive && !stickySpear.alive) {
								stickySpear.shoot(x, y);
								return true;
							}
						 } break;
			default: {
						if(!spear.alive && !spear2.alive && !stickySpear.alive) {
							spear.shoot(x, y);
							return true;
						}
					 }
		}
		return false;
	}
}
