package game.main.map;

import java.util.Random;

import game.main.graphics.sprite.Sprite;
import game.main.tile.DirtTile;
import game.main.tile.Tile;

public class Map {

	public static final int MAPSIZE = 64; //typical size of map in tiles
	
	public Tile[][] mapTiles = new Tile[MAPSIZE][MAPSIZE];
	
	public Map(String filepath) {
		
	}
	
	public Map() {
		generateRandomMap();
	}
	
	public void generateRandomMap() {
		Random random = new Random();
		
		for(int y = 0; y < mapTiles.length; y++) {
			for(int x = 0; x < mapTiles[0].length; x++) {
				int choice = random.nextInt(10);
				if (choice == 0) mapTiles[y][x] = Tile.debugTile;
				else mapTiles[y][x] = Tile.dirtTile;
			}
		}
		
	}
	
}
