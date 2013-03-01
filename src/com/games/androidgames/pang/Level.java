package com.games.androidgames.pang;

import java.util.ArrayList;
import java.util.List;

import com.games.androidgames.framework.DynamicGameObject;
import com.games.androidgames.framework.Game;
import com.games.androidgames.framework.GameObject;
import com.games.androidgames.framework.gl.Texture;
import com.games.androidgames.framework.gl.TextureRegion;
import com.games.androidgames.pang.elements.Ball;
import com.games.androidgames.pang.elements.Ladder;
import com.games.androidgames.pang.elements.Platform;

public class Level {
	private static float BALL_RADIUS = 16.0f;
	public static final float SMALLEST_BALL = 16.0f;
	public static final float BUMP = 0.7f;
	public static final int NUMBER_OF_LEVELS = 7;
	
	public Texture background, textureSet;
	public TextureRegion backgroundRegion;
	public List<DynamicGameObject> balls;
	public List<GameObject> platforms;
	public List<Ladder> ladders;
	
	public Level(Texture textureSet){
		balls = new ArrayList<DynamicGameObject>();
		ladders  = new ArrayList<Ladder>();
		platforms = new ArrayList<GameObject>();	
		this.textureSet = textureSet;
	}
	
	public void load(Game game, int levelId){
		switch(levelId){
			case 1:	{					
						balls.add(new Ball(Settings.WORLD_WIDTH / 4, Settings.WORLD_HEIGHT - 32.0f, BALL_RADIUS * 2));
		
						for(DynamicGameObject ball: balls) {
							ball.velocity.x = 125;
						}						
						
						background = Resources.background;
						backgroundRegion =  Resources.backgroundRegion;
						
					} break;
			case 2: {					
						balls.add(new Ball(Settings.WORLD_WIDTH / 4 * 3, Settings.WORLD_HEIGHT - 32.0f, BALL_RADIUS * 2));
						balls.get(0).velocity.x = -125;
						balls.add(new Ball(Settings.WORLD_WIDTH / 4, Settings.WORLD_HEIGHT - 32.0f, BALL_RADIUS * 2));
						balls.get(1).velocity.x = 125;			
						
						background = Resources.background;
						backgroundRegion =  Resources.backgroundRegion;
					} break;
			case 3: {					
						balls.add(new Ball(Settings.WORLD_WIDTH / 3, Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 + (BALL_RADIUS * 2.5f), Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 + (BALL_RADIUS * 2.5f * 2), Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 + (BALL_RADIUS * 2.5f * 3), Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						
						for(DynamicGameObject ball: balls) {
							ball.velocity.x = 155;
							((Ball)ball).bounce = 450;
						}						
						
						background = Resources.backgroundMenu;
						backgroundRegion =  Resources.backgroundMenuRegion;
					} break;
			case 4:  {					
						balls.add(new Ball(Settings.WORLD_WIDTH / 3, Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 + (BALL_RADIUS * 2.5f), Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 + (BALL_RADIUS * 2.5f * 2), Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 + (BALL_RADIUS * 2.5f * 3), Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						
						for(DynamicGameObject ball: balls) {
							ball.velocity.x = 155;
							((Ball)ball).bounce = 450;
						}						
						
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 * 2, Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 * 2 - (BALL_RADIUS * 2.5f), Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 * 2 - (BALL_RADIUS * 2.5f * 2), Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 * 2 - (BALL_RADIUS * 2.5f * 3), Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						
						for(int i = 4; i < 8; i++) {
							balls.get(i).velocity.x = -155;
							((Ball)balls.get(i)).bounce = 450;
						}		
						
						background = Resources.background;
						backgroundRegion =  Resources.backgroundRegion;
					} break;
			case 5:	{					
						balls.add(new Ball(Settings.WORLD_WIDTH / 3, Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 + (BALL_RADIUS * 2.5f), Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 + (BALL_RADIUS * 2.5f * 2), Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 + (BALL_RADIUS * 2.5f * 3), Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						
						for(DynamicGameObject ball: balls) {
							ball.velocity.x = 155;
							((Ball)ball).bounce = 450;
						}						
						
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 * 2, Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 * 2 - (BALL_RADIUS * 2.5f), Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 * 2 - (BALL_RADIUS * 2.5f * 2), Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						balls.add(new Ball(Settings.WORLD_WIDTH / 3 * 2 - (BALL_RADIUS * 2.5f * 3), Settings.WORLD_HEIGHT - 50.0f, BALL_RADIUS));
						
						for(int i = 4; i < 8; i++) {
							balls.get(i).velocity.x = -155;
							((Ball)balls.get(i)).bounce = 450;
						}	
						
						balls.add(new Ball(Settings.WORLD_WIDTH / 2, Settings.WORLD_HEIGHT - 182.0f, BALL_RADIUS * 2));
						
						background = Resources.backgroundMenu;
						backgroundRegion =  Resources.backgroundMenuRegion;
					} break;
			case 6: {					
						balls.add(new Ball(Settings.WORLD_WIDTH / 4, Settings.WORLD_HEIGHT - 32.0f, BALL_RADIUS * 2));

						for(DynamicGameObject ball: balls) {
							ball.velocity.x = 125;
						}
						ladders.add(new Ladder(textureSet, 250, 100, 50, 200));
						ladders.add(new Ladder(textureSet, 450, 145, 50, 300));
						platforms.add(new Platform(textureSet, 300, 288, 300, 16));	
						
						background = Resources.background;
						backgroundRegion =  Resources.backgroundRegion;
					} break;
			case 7: {
						balls.add(new Ball(Settings.WORLD_WIDTH / 4 * 3, Settings.WORLD_HEIGHT - 32.0f, BALL_RADIUS * 2));
						balls.get(0).velocity.x = -125;
						balls.add(new Ball(Settings.WORLD_WIDTH / 4, Settings.WORLD_HEIGHT - 32.0f, BALL_RADIUS * 2));
						balls.get(1).velocity.x = 125;
						
						ladders.add(new Ladder(textureSet, 175, 100, 50, 216));
						ladders.add(new Ladder(textureSet, 600, 100, 50, 216));
						platforms.add(new Platform(textureSet, 400, 200, 500, 16));	
						
						background = Resources.backgroundMenu;
						backgroundRegion =  Resources.backgroundMenuRegion;
					}
			default:
		}
	}
}
