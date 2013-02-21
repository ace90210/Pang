package com.games.androidgames.pang;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;


import android.view.MenuItem;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.view.View;

import com.games.androidgames.framework.impl.GLGame;
import com.games.androidgames.framework.impl.GLGraphics;
import com.games.androidgames.framework.GLText;
import com.games.androidgames.framework.Input.TouchEvent;
import com.games.androidgames.framework.Pool;
import com.games.androidgames.framework.Screen;
import com.games.androidgames.framework.Game;
import com.games.androidgames.framework.impl.TouchHandler;
import com.games.androidgames.framework.math.OverlapTester;
import com.games.androidgames.framework.math.Rectangle;
import com.games.androidgames.framework.math.Vector2;
import com.games.androidgames.framework.gl.Camera2D;
import com.games.androidgames.framework.gl.SpriteBatcher;
import com.games.androidgames.pang.elements.MenuButton;
import com.games.androidgames.pang.elements.SettingsButton;

public class MainMenuScreen extends Screen implements OnTouchListener {
	private static int SPRITE_LIMIT = 100;
	private static float WORLD_WIDTH = 840, WORLD_HEIGHT = 460;
	
	private Camera2D camera;
	private GLGraphics glGraphics;
	private GL10 gl;
	private GLText glText;
	
	
	private List<MenuButton> items;
	private SpriteBatcher batcher;
	
	
	public MainMenuScreen(Game game){
		super(game);
		glGraphics = ((GLGame)game).getGLGraphics();
		glGraphics.getView().setOnTouchListener(this);
		gl = glGraphics.getGL();
		glText = Resources.glText;
		camera = new Camera2D(glGraphics, WORLD_WIDTH, WORLD_HEIGHT);

		items = new ArrayList<MenuButton>();
		items.add(new SettingsButton("Hello Test", glText, WORLD_WIDTH / 2, WORLD_HEIGHT / 2, camera.zoom * 1.5f));

		items.add(new SettingsButton("Button 2 Test", glText, WORLD_WIDTH / 2, WORLD_HEIGHT / 3, camera.zoom));
		items.get(1).setAltRGBA(0.0f, 0.3f, 1.0f, 1.0f);
		batcher = new SpriteBatcher(gl, SPRITE_LIMIT);		
	}
	
	@Override
	public void update(float deltaTime) {
		game.getInput().getKeyEvents();		
		game.getInput().getTouchEvents();
		
	}

	@Override
	public void present(float deltaTime) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.setViewportAndMatrices(0.0f, 0.0f, 0.6f);
		
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL10.GL_TEXTURE_2D);		  
		
		
		batcher.beginBatch(Resources.background);
		batcher.drawSprite(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, WORLD_WIDTH, WORLD_HEIGHT, Resources.backgroundRegion);
		batcher.endBatch();
		
		for(MenuButton item1: items) {
			glText.setScale(item1.scale);
			if(item1.heightLighted){
				glText.begin(item1.altR, item1.altG, item1.altB, item1.altA);
				glText.draw(item1.text, item1.bounds.lowerLeft.x , item1.bounds.lowerLeft.y);
				glText.end();
			} else {
				glText.begin(item1.r, item1.g, item1.b, item1.a);
				glText.draw(item1.text, item1.bounds.lowerLeft.x , item1.bounds.lowerLeft.y);
				glText.end();
			}
		}
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {

		
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		 synchronized (this) {
	            int action = event.getAction() & MotionEvent.ACTION_MASK;
	            int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
	            int pointerCount = event.getPointerCount();
	            TouchEvent touchEvent;
	            for (int i = 0; i < pointerCount; i++) {
	                int pointerId = event.getPointerId(i);
	                if (event.getAction() != MotionEvent.ACTION_MOVE && i != pointerIndex) {
	                    // if it's an up/down/cancel/out event, mask the id to see if we should process it for this touch
	                    // point
	                    continue;
	                }
	                switch (action) {
		                case MotionEvent.ACTION_DOWN:
		                case MotionEvent.ACTION_POINTER_DOWN:
		                {
		                	for(MenuButton item1: items) {
		                		if(OverlapTester.pointInRectangle(item1.bounds, new Vector2( event.getX(i),  glGraphics.getHeight() - event.getY(i)))){
		                			if(item1.heightLighted == false) {
		                				Resources.playSound(Resources.BUTTON_HEIGHTLIGHT);
		                				item1.heightLighted = true;
		                			}			                    	
			                    } 
		                	}
		                }  break;
	
		                case MotionEvent.ACTION_UP:
		                case MotionEvent.ACTION_POINTER_UP:
		                case MotionEvent.ACTION_CANCEL:
		                {
		                	for(MenuButton item1: items) {
			                	if(OverlapTester.pointInRectangle(item1.bounds, new Vector2( event.getX(i), glGraphics.getHeight() - event.getY(i)))){
			                		item1.action(game);			                    	
			                    }
		                	}
		                } break;
	
		                case MotionEvent.ACTION_MOVE:
		                {
		                	for(MenuButton item1: items) {
			                	if(OverlapTester.pointInRectangle(item1.bounds, new Vector2( event.getX(i), glGraphics.getHeight() - event.getY(i)))){
			                		if(item1.heightLighted == false) {
		                				Resources.playSound(Resources.BUTTON_HEIGHTLIGHT);
		                				item1.heightLighted = true;
		                			}
			                    } else {
			                    	item1.heightLighted = false;
			                    }
		                	}
		                } break;
	                }
	            }
	            return true;
	        }
	    }

}
