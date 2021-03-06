package com.ace90210.androidgames.pang.elements;

import com.ace90210.androidgames.framework.DynamicGameObject;
import com.ace90210.androidgames.framework.GameObject;
import com.ace90210.androidgames.framework.gl.Animation;
import com.ace90210.androidgames.framework.gl.Texture;
import com.ace90210.androidgames.framework.gl.TextureRegion;
import com.ace90210.androidgames.framework.math.OverlapTester;
import com.ace90210.androidgames.framework.math.Vector2;
import com.ace90210.androidgames.pang.CurrentGameDetails;
import com.ace90210.androidgames.pang.Resources;
import com.ace90210.androidgames.pang.Settings;

import java.util.List;
import java.util.ArrayList;

public class Player extends DynamicGameObject {
	private static int IMMUNE_LIMIT = 2; //seconds to remain immune after hit
	private float walkingTime, immuneTime;
	private Vector2 gravity;
	private Animation walkingLeft, walkingRight, climbing;
	private TextureRegion standing, shooting, hurt, currentState;
	private boolean immune;
	
	public static enum PlayerState { WALKING_RIGHT, WALKING_LEFT, STANDING, SHOOTING, HURT, CLIMBING};
	private PlayerState playerState, lastState;
	
	public Player(float x, float y, float width, float height, int life, Texture texture) {
		super(x, y, width, height);
		walkingLeft = Resources.playerWalkLeft;
		walkingRight =  Resources.playerWalkRight;
		climbing =   Resources.playerClimb;
		
		standing =  Resources.playerStanding;
		shooting =  Resources.playerShooting;
		hurt =  Resources.playerHurt;
		
		walkingTime = 0;
		immune = false;
		immuneTime = IMMUNE_LIMIT;
		this.currentState = standing;
		this.gravity = new Vector2(0, 0);
		this.playerState = PlayerState.STANDING;
		this.lastState = PlayerState.STANDING;
	}
	
	public void update(float deltaTime, PlayerState state, List<Ladder> ladders, List<GameObject> ... objects) {
		walkingTime += deltaTime;		
		if(immune){
			immuneTime -= deltaTime;
			if(immuneTime <= 0) {
				immune = false;
				immuneTime = IMMUNE_LIMIT;
				playerState = state;
			}
		} else {			
			playerState = state;				
		}	
			
		if(velocity.y <= 0) {
			velocity.y = 0;
		} else {
			position.add((velocity.x -gravity.x) * deltaTime, (velocity.y - gravity.y) * deltaTime);
			bounds.lowerLeft.add((velocity.x -gravity.x) * deltaTime, (velocity.y - gravity.y) * deltaTime);
			velocity.add(gravity.x * 3 * deltaTime, gravity.y * 3 * deltaTime);
		}
		
		boolean onFloor = onFloor(deltaTime, ladders, objects);
		if(bounds.lowerLeft.y <= 0) {
			position.y = bounds.height / 2;
			bounds.lowerLeft.y = 0;
			velocity.y = 0;
			onFloor = true;
		}
		
		if(!onFloor || velocity.y > 0) {
			position.add(gravity.x * deltaTime, gravity.y * deltaTime);		
			bounds.lowerLeft.add(gravity.x * deltaTime, gravity.y * deltaTime);
		} 
	
		switch(state){
			case SHOOTING: currentState = shooting; break;
			case HURT: currentState = hurt; break;
			case WALKING_LEFT: 	{
									this.lastState = PlayerState.WALKING_LEFT;
									currentState = walkingLeft.getKeyFrame(walkingTime, Animation.ANIMATION_LOOPING);
								} break;
			case WALKING_RIGHT: {
									this.lastState = PlayerState.WALKING_RIGHT;
									currentState = walkingRight.getKeyFrame(walkingTime, Animation.ANIMATION_LOOPING);
								} break;
			case CLIMBING: 		{
									currentState = climbing.getKeyFrame(walkingTime, Animation.ANIMATION_LOOPING);
								} break;
			default: {
						if(lastState == PlayerState.WALKING_LEFT) {
							currentState = walkingLeft.getKeyFrame(0, Animation.ANIMATION_LOOPING);
						} else {
							currentState = walkingRight.getKeyFrame(0, Animation.ANIMATION_LOOPING);
						}						
					}
		}
		
	}
	
	public void setVelocity(float x, float y) {
		velocity.set(x, y);
	}
	
	public boolean onFloor(float deltaTime,  List<Ladder> ladders, List<GameObject> ... objects) {
		boolean onFloor = false;
		if(bounds.lowerLeft.y <= 0) {
			onFloor = true;
		}
		for(Ladder ladder: ladders) {
			if(position.x - bounds.width / 4 < ladder.bounds.lowerLeft.x + ladder.bounds.width 		&&
			   position.x + bounds.width / 4 > ladder.bounds.lowerLeft.x 							&&
			   position.y - bounds.height / 2 > ladder.bounds.lowerLeft.y 							&&
			   position.y + bounds.height / 2 < (ladder.position.y + ladder.bounds.height / 2) + bounds.height) {					
			   onFloor = true;
			}			
		}	
		for(List<GameObject> objectList: objects) {
			for(GameObject object: objectList) {
				if(position.x - bounds.width / 4 < object.bounds.lowerLeft.x + object.bounds.width 		&&
				   position.x + bounds.width / 4 > object.bounds.lowerLeft.x 							&&
				   position.y >= object.bounds.lowerLeft.y + object.bounds.height	&&
				   position.y + bounds.height / 2 <= (object.position.y + object.bounds.height / 2) + bounds.height) {	
					//if on ladder onFloor will be true
					if(!onFloor) {
						position.y = (object.position.y + object.bounds.height / 2) + bounds.height / 2;		//update position and bounds
						bounds.lowerLeft.set(position.x - bounds.width / 2, position.y - bounds.height / 2);
					}				   
				   onFloor = true;
				}			
			}	
		}
		return onFloor;
	}
	
	public void setGravity(float x, float y) {
		this.gravity.x = x;
		this.gravity.y = y;
	}
	
	public void movePlayer(float x, float y, float deltaTime) {
		position.add(x * deltaTime, y * deltaTime);
		bounds.lowerLeft.add(x * deltaTime, y * deltaTime);
	}
	
	public TextureRegion getKeyFrame() {
		return currentState;
	}
	
	public boolean hit(){
		if(!immune){			
			immune = true;
			playerState = PlayerState.HURT;
			CurrentGameDetails.lives--;
		}		
		return alive();
	}
	
	public boolean alive(){
		if(CurrentGameDetails.lives <= 0) {
			return false;
		}
		return true;
	}
	
	public float getImmuneTime() {
		return immuneTime;
	}
	
	public void reset(float x, float y, int life) {
		this.position.set(x, y);
		this.updateBounds(x, y, this.bounds.width, this.bounds.height);
		this.velocity.set(0, 0);
		walkingTime = 0;
		immune = false;
		immuneTime = IMMUNE_LIMIT;
		this.currentState = standing;
		this.playerState = PlayerState.STANDING;
		CurrentGameDetails.lives = life;
		
	}
	
	public void reset() {
		walkingTime = 0;
		this.velocity.set(0, 0);
		immune = false;
		immuneTime = IMMUNE_LIMIT;
		this.currentState = standing;
		this.playerState = PlayerState.STANDING;
	}
	
	public boolean hurt() {
		if( playerState == PlayerState.HURT ) {
			return true;
		}
		return false;
	}
}
