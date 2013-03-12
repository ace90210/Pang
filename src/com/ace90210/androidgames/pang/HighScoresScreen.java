package com.ace90210.androidgames.pang;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.view.View.OnTouchListener;
import android.view.View.OnKeyListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.ace90210.androidgames.framework.GLText;
import com.ace90210.androidgames.framework.Game;
import com.ace90210.androidgames.framework.Screen;
import com.ace90210.androidgames.framework.gl.Camera2D;
import com.ace90210.androidgames.framework.gl.SpriteBatcher;
import com.ace90210.androidgames.framework.impl.GLGame;
import com.ace90210.androidgames.framework.impl.GLGraphics;
import com.ace90210.androidgames.framework.math.OverlapTester;
import com.ace90210.androidgames.framework.math.Vector2;
import com.ace90210.androidgames.pang.buttons.BlankButton;
import com.ace90210.androidgames.pang.buttons.MainMenuButton;
import com.ace90210.androidgames.pang.buttons.MenuButton;

public class HighScoresScreen extends Screen implements OnTouchListener, OnKeyListener {
	private static int SPRITE_LIMIT = 100;
	
	private Camera2D camera;
	private GLGraphics glGraphics;
	private GL10 gl;
	private GLText glText;
	
	private int selectedMenu;
	private int[] scores;
	
	private List<MenuButton> items;
	private SpriteBatcher batcher;
	
	
	public HighScoresScreen(Game game){
		super(game);
		glGraphics = ((GLGame)game).getGLGraphics();
		glGraphics.getView().setOnTouchListener(this);
		glGraphics.getView().setOnKeyListener(this);
		gl = glGraphics.getGL();
		glText = Resources.glButtonText;
		camera = new Camera2D(glGraphics, Settings.WORLD_WIDTH, Settings.WORLD_HEIGHT);
		
		scores = Resources.getScores((GLGame)game);
		
		selectedMenu = -1;
		items = new ArrayList<MenuButton>();

		for(int i =0; i < scores.length; i++){
			items.add(new BlankButton(scores[i] + "", glText, Settings.WORLD_WIDTH / 2, Settings.WORLD_HEIGHT / 12 * (11 - i), camera.zoom));
			if(i % 2 == 1){
				items.get(items.size() - 1).r = 0.0f;
				items.get(items.size() - 1).g = 0.25f;
				items.get(items.size() - 1).b = 0.0f;
			}
		}
		items.add(new MainMenuButton("back", glText, Settings.WORLD_WIDTH / 2, Settings.WORLD_HEIGHT / 15 , camera.zoom * 1.4f));
		items.get(items.size() - 1).setAltRGBA(0.1f, 0.9f, 0.0f, 1.0f);
		
		batcher = new SpriteBatcher(gl, SPRITE_LIMIT);		
	}
	
	@Override
	public void update(float deltaTime) {
		game.getInput().getKeyEvents();		
		game.getInput().getTouchEvents();
		if(selectedMenu != -1){
			for(int i = 0; i < items.size(); i++){
				items.get(i).heightLighted = false;
			}
			items.get(selectedMenu).heightLighted = true;
		}
	}

	@Override
	public void present(float deltaTime) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.setViewportAndMatrices(0.0f, 0.0f, 0.6f);
		
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL10.GL_TEXTURE_2D);		  
		
		
		batcher.beginBatch(Resources.backgroundMenu);
		batcher.drawSprite(Settings.WORLD_WIDTH / 2, Settings.WORLD_HEIGHT / 2, Settings.WORLD_WIDTH, Settings.WORLD_HEIGHT, Resources.backgroundMenuRegion);
		batcher.endBatch();
		synchronized(items){
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
	            for (int i = 0; i < pointerCount; i++) {
	                int pointerId = event.getPointerId(i);
	                if (event.getAction() != MotionEvent.ACTION_MOVE && i != pointerIndex) {
	                    // if it's an up/down/cancel/out event, mask the id to see if we should process it for this touch
	                    // point
	                    continue;
	                }
	                float x = event.getX(i) / Settings.SCALE_WIDTH;
	                float y =  (glGraphics.getHeight() - event.getY(i)) / Settings.SCALE_HEIGHT;
	                switch (action) {
		                case MotionEvent.ACTION_DOWN:
		                case MotionEvent.ACTION_POINTER_DOWN:
		                {
		                	synchronized(this){
			                	for(MenuButton item1: items) {
			                		if(OverlapTester.pointInRectangle(item1.bounds, new Vector2(x, y ))){
			                			if(item1.heightLighted == false) {
			                				Resources.playSound(Resources.BUTTON_HEIGHTLIGHT);
			                				selectedMenu = -1;
			                				item1.heightLighted = true;
			                			}			                    	
				                    } 
			                	}
		                	}
		                }  break;
	
		                case MotionEvent.ACTION_UP:
		                case MotionEvent.ACTION_POINTER_UP:
		                case MotionEvent.ACTION_CANCEL:
		                {
		                	synchronized(this){
			                	for(MenuButton item1: items) {
				                	if(OverlapTester.pointInRectangle(item1.bounds, new Vector2( x, y )) && item1.enabled){				                		
				                		item1.action(game);	
				                		selectedMenu = -1;
				                		item1.heightLighted = false;
				                    }
			                	}
		                	}
		                } break;
	
		                case MotionEvent.ACTION_MOVE:
		                {
		                	synchronized(this){
			                	for(MenuButton item1: items) {
				                	if(OverlapTester.pointInRectangle(item1.bounds, new Vector2( x, y ))){
				                		if(item1.heightLighted == false) {
			                				Resources.playSound(Resources.BUTTON_HEIGHTLIGHT);
			                				selectedMenu = -1;
			                				item1.heightLighted = true;
			                			}
				                    } else {
				                    	item1.heightLighted = false;
				                    }
			                	}
		                	}
		                } break;
	                }
	            }
	            return true;
	        }
	    }

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		boolean validKey = true;
		
		if(event.getAction() == KeyEvent.ACTION_DOWN) {
			switch(event.getKeyCode())
			{
				case KeyEvent.KEYCODE_DPAD_CENTER: 	{	//x
														if(selectedMenu != -1){
															items.get(selectedMenu).action(game);	
							                				Resources.playSound(Resources.BUTTON_HEIGHTLIGHT);
														}
													}
												break;
					case KeyEvent.KEYCODE_BACK:  {
													//Circle Pressed		
													game.setScreen(new MainMenuScreen(game));
												 }
												break;
				case KeyEvent.KEYCODE_BUTTON_X:	{
														
												}
												break;
				case KeyEvent.KEYCODE_BUTTON_Y: {
														//Triangle pressed																	
												}													
												break;
				case KeyEvent.KEYCODE_DPAD_UP:  {
													selectedMenu = MenuButton.findPreviousButton(selectedMenu, items);
													Resources.playSound(Resources.BUTTON_HEIGHTLIGHT);		
												}
												break;
				case KeyEvent.KEYCODE_DPAD_DOWN: {
													selectedMenu = MenuButton.findNextButton(selectedMenu, items);											
													Resources.playSound(Resources.BUTTON_HEIGHTLIGHT);	
												 }
												
												break;
				case KeyEvent.KEYCODE_DPAD_RIGHT: 	{
														
													}
													
												break;
				case KeyEvent.KEYCODE_DPAD_LEFT:  	{
														
													}
												break;
				case KeyEvent.KEYCODE_BUTTON_SELECT: 
														{
															
														}
												break;
				case KeyEvent.KEYCODE_BUTTON_START: 
													
												break;
				case KeyEvent.KEYCODE_BUTTON_L1: 
												
												break;
				case KeyEvent.KEYCODE_BUTTON_R1: 
												
												break;
			}
		}else if(event.getAction() == KeyEvent.ACTION_UP) {
			
		}
		//catch circle button (prevent back key + alt quiting)
		if(!event.isAltPressed()) {
			validKey = false;
		}
		return validKey;
	}
}
