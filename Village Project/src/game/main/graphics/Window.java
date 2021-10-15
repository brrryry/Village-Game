package game.main.graphics;

import game.main.entity.conveyor.Conveyor;
import game.main.entity.dropper.Dropper;
import game.main.entity.mob.Mob;
import game.main.entity.mob.Player;
import game.main.entity.ore.Ore;
import game.main.graphics.sprite.Sprite;
import game.main.graphics.ui.UI;
import game.main.graphics.ui.uiobject.UIObject;
import game.main.map.Map;
import game.main.tile.Tile;

/**
 * The Window Class is used to take care of rendering stuff on the screen.
 * @author bryan.chan
 */

public class Window {
	
	//Window Size
	public int width, height;
	
	//Offset of the Map (based on the player)
	public int xOffset, yOffset;
	
	//Pixel array that will be written to the screen
	public int[] pixels; 
	
	//Constructor
	public Window(int width, int height) { 
		this.width = width;
		this.height = height; 
		pixels = new int[width * height]; 
	}
	
	
	/**
	 * This method turns all of the pixels to a singular color.
	 * @param Nothing
	 * @return Nothing
	 */
	public void clear() { //clears the screen
		for(int i = 0; i < pixels.length; i++) pixels[i] = 0x5997CC;
	}
	
	/**
	 * This method is the main render method. Every other render method should be called in here.
	 * @param The map
	 * @param The player's UI
	 * @return Nothing
	 */
	public void render(Map map, UI ui) { //render stuff on the screen! (We'll probably change this later)
		
		//Render Tiles every 16 pixels
		for(int y = 0; y < map.mapTiles.length; y += 1) {
			for(int x = 0; x < map.mapTiles[0].length; x += 1) {
				renderTile(x << 4, y << 4, map.mapTiles[x][y]);
			}
		}
		
		//Render Map Components
		for(Dropper d : map.droppers) renderDropper(d);
		for(Conveyor c : map.conveyors) renderConveyor(c);
		for(Ore o : map.ores) renderOre(o);
		for(Mob m : map.mobs) renderMob(m.getX(), m.getY(), m);
		
		//Render UI Components
		for(UIObject u : ui.getUIObjects()) renderUIObject(u);
	}
	
	
	
	public void renderTile(int xpos, int ypos, Tile tile) {
		xpos -= xOffset; //set the offset of the map
		ypos -= yOffset; //set the offset of the map
		for(int y = 0; y < tile.tileSprite.getYSize(); y++) {
			for(int x = 0; x < tile.tileSprite.getXSize(); x++) {
				int yy = y + ypos;
				int xx = x + xpos;
				if(yy < 0 || yy >= height || xx < 0 || xx >= width) continue;
				pixels[(xpos + x) + (y + ypos) * width] = tile.tileSprite.pixels[x + y * tile.tileSprite.getXSize()];
			} 
		}
	}
	
	public void renderDropper(Dropper dropper) {
		int xc = dropper.getX() - xOffset; //set the offset of the map
		int yc = dropper.getY() - yOffset; //set the offset of the map
		for(int y = 0; y < dropper.getYLength(); y++) {
			for(int x = 0; x < dropper.getXLength(); x++) {
				int yy = y + yc;
				int xx = x + xc;
				if(yy < 0 || yy >= height || xx < 0 || xx >= width) continue;
				pixels[(xc + x) + (y + yc) * width] = dropper.getSprite().pixels[x + y * dropper.getXLength()];
			} 
		}
	}
	
	public void renderConveyor(Conveyor c) {
		int xc = c.getX() - xOffset; //set the offset of the map
		int yc = c.getY() - yOffset; //set the offset of the map
		for(int y = 0; y < c.getYLength(); y++) {
			for(int x = 0; x < c.getXLength(); x++) {
				int yy = y + yc;
				int xx = x + xc;
				if(yy < 0 || yy >= height || xx < 0 || xx >= width) continue;
				pixels[(xc + x) + (y + yc) * width] = c.getSprite().pixels[x + y * c.getXLength()];
			} 
		}
	}
	
	public void renderOre(Ore ore) {
		int xc = ore.getX() - xOffset; //set the offset of the map
		int yc = ore.getY() - yOffset; //set the offset of the map
		for(int y = 0; y < ore.getSize(); y++) {
			for(int x = 0; x < ore.getSize(); x++) {
				int yy = y + yc;
				int xx = x + xc;
				if(yy < 0 || yy >= height || xx < 0 || xx >= width) continue;
				pixels[(xc + x) + (y + yc) * width] = ore.getColor();
			} 
		}
	}
	
	public void renderMob(int xpos, int ypos, Mob m) {
		xpos -= xOffset; //set the offset of the map
		ypos -= yOffset; //set the offset of the map
		for(int y = 0; y < 16; y++) {
			for(int x = 0; x < 16; x++) {
				int yy = y + ypos;
				int xx = x + xpos;
				if(yy < 0 || yy >= height || xx < 0 || xx >= width) continue;
				if(m.sprite.pixels[x + y * 16] == 0xffffffff) continue;
				pixels[(xpos + x) + (y + ypos) * width] = m.sprite.pixels[x + y * 16];
			} 
		}
	}
	
	public void renderPlayer(Player p) {
		for(int y = 0; y < 16; y++) {
			for(int x = 0; x < 16; x++) {
				if(p.sprite.pixels[x + y * 16] == 0xffffffff) continue;
				p.sprite.pixels[x + y * 16] = pixels[(x + (width / 2)) + ((y + (height / 2)) * width)] = p.sprite.pixels[x + y * 16];
			}
		}
	}
	
	public void renderUIObject(UIObject u) {
		if(!u.visible()) return;
		for(int yy = 0; yy < u.getSprite().getYSize(); yy++) {
			for(int xx = 0; xx < u.getSprite().getXSize(); xx++) {
				if(u.getSprite().pixels[xx + yy * (u.getSprite().getXSize())] == 0xff0094FF) continue;
				pixels[(xx + u.getX()) + (yy + u.getY()) * width] = u.getSprite().pixels[xx + yy * (u.getSprite().getXSize())];	
			}
		}
	}
	
	//Set the Player's Offset
	public void setOffset(int x, int y) {
		this.xOffset = x;
		this.yOffset = y;
	}
	
	
	

}
