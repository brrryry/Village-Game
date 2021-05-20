package game.main.graphics;

import java.awt.Color;
import java.util.Objects;

import game.main.entity.mob.Mob;
import game.main.entity.mob.Player;
import game.main.map.Map;
import game.main.tile.Tile;

public class Window { //this class takes care of all the rendering and sets its pixels to the screen pixels
	
	public int width, height; //width and height of the screen
	public int xOffset, yOffset; //this will tell us how much the screen is offset!
	public int[] pixels; //pixel array that will be sent to the main class's pixels
	
	public Window(int width, int height) { //constructor
		this.width = width; //sets width
		this.height = height; //sets height
		pixels = new int[width * height]; //sets pixels array
	}
	
	
	public void clear() { //clears the screen
		for(int i = 0; i < pixels.length; i++) pixels[i] = 0x000000;
	}
	
	
	public void render(Map map) { //render stuff on the screen! (We'll probably change this later)
		//render tiles
		
		for(int y = 0; y < map.mapTiles.length; y += 1) {
			for(int x = 0; x < map.mapTiles[0].length; x += 1) {
				renderTile(x << 4, y << 4, map.mapTiles[x][y]);
			}
		}
		
		
		/*
		 * Possibly something like
		 * renderMobs();
		 * renderStuff();
		 * renderThings();
		 */
	}
	
	public void renderTile(int xpos, int ypos, Tile tile) {
		xpos -= xOffset; //set the offset of the map
		ypos -= yOffset; //set the offset of the map
		for(int y = 0; y < 16; y++) {
			for(int x = 0; x < 16; x++) {
				int yy = y + ypos;
				int xx = x + xpos;
				if(yy < 0 || yy >= height || xx < 0 || xx >= width) continue;
				pixels[(xpos + x) + (y + ypos) * width] = tile.tileSprite.pixels[x + y * 16];
			} 
		}
	}
	
	public void renderMob(int xpos, int ypos, Mob m) {
		
	}
	
	public void renderPlayer(Player p) {
		for(int y = 0; y < 16; y++) {
			for(int x = 0; x < 16; x++) {
				if(p.sprite.pixels[x + y * 16] == 0xffffffff) continue;
				pixels[(x + (width / 2)) + ((y + (height / 2)) * width)] = p.sprite.pixels[x + y * 16];
			}
		}
	}
	
	public void setOffset(int x, int y) {
		this.xOffset = x;
		this.yOffset = y;
	}
	

}
