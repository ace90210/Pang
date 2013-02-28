package com.games.androidgames.pang.elements;

import com.games.androidgames.framework.GameObject;
import com.games.androidgames.framework.gl.TextureRegion;
import com.games.androidgames.framework.gl.Texture;
import com.games.androidgames.pang.Settings;

import java.util.List;
import java.util.ArrayList;

public class Platform extends GameObject {
	private static int TILE_WIDTH = 16;
	public TextureRegion tileRegion;
	public List<GameObject> tiles;
	
	public Platform(Texture texture, float x, float y, float width, float height) {
		super(x, y, width, height);
		
		int remainder = (int)width % TILE_WIDTH;
		tiles = new ArrayList<GameObject>();
		
		if(width >= 16) {
			tileRegion = new TextureRegion(texture, 33, 155, 16, 8);
			int numTiles = (int)width / TILE_WIDTH;
					
			for(int i = 0; i < numTiles; i++) {
				//position y - half full height and count down from there by i x tile height
				tiles.add(new Tile((position.x + width / 2) - (i * TILE_WIDTH) - (TILE_WIDTH / 2), position.y, TILE_WIDTH, height));
			}		
			tiles.add(new Tile((position.x - width / 2) + remainder, position.y, TILE_WIDTH, height));
		}
	}
}
