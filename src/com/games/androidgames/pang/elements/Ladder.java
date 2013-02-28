package com.games.androidgames.pang.elements;

import com.games.androidgames.framework.GameObject;
import com.games.androidgames.framework.gl.TextureRegion;
import com.games.androidgames.framework.gl.Texture;
import com.games.androidgames.pang.Settings;

import java.util.List;
import java.util.ArrayList;

public class Ladder extends GameObject {
	private static int TILE_HEIGHT = 30;
	public TextureRegion tileRegion;
	public List<GameObject> tiles;
	
	public Ladder(Texture texture, float x, float y, float width, float height) {
		super(x, y, width, height);
		int remainder = (int)height % TILE_HEIGHT;
		tiles = new ArrayList<GameObject>();
		
		if(height>= 30) {
			tileRegion = new TextureRegion(texture, 1, 131, 25, TILE_HEIGHT);
			int numTiles = (int)height / TILE_HEIGHT;
					
			for(int i = 0; i < numTiles; i++) {
				//position y - half full height and count down from there by i x tile height
				tiles.add(new Tile(position.x, (position.y + height / 2) - (i * TILE_HEIGHT) - (TILE_HEIGHT / 2), width, TILE_HEIGHT));
			}					
		}
		//create last tile
		tiles.add(new Tile(position.x,  (position.y - height / 2) + remainder, width, TILE_HEIGHT));
	}
	
	public boolean upValid(Player p) {
		if(p.position.x > bounds.lowerLeft.x && p.position.x < bounds.lowerLeft.x + bounds.width && 
		   p.position.y >= ((position.y - bounds.height / 2) + p.bounds.height / 2) - 10 && 
		   p.position.y - p.bounds.height / 2 < (position.y + bounds.height / 2)) {
			return true;
		}
		return false;
	}
	
	public boolean downValid(Player p) {
		if(p.position.x > bounds.lowerLeft.x && p.position.x < bounds.lowerLeft.x + bounds.width) {
			if(p.position.y >= (position.y - bounds.height / 2) + p.bounds.height / 2) {
				if(p.position.y - p.bounds.height / 2 < (position.y + bounds.height / 2)  + 10) {
					return true;
				}
			}
		}
		return false;
	}
}
