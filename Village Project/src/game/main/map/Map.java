package game.main.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.main.entity.Entity;
import game.main.entity.conveyor.Conveyor;
import game.main.entity.dropper.Dropper;
import game.main.entity.mob.Mob;
import game.main.entity.ore.Ore;
import game.main.graphics.Window;
import game.main.tile.Tile;

public class Map {

	public static final int MAPSIZE = 64; //typical size of map in tiles
	
	public static Map m = new Map();
	
	public static int mapTick = 0;
	
	
	public Tile[][] mapTiles = new Tile[MAPSIZE][MAPSIZE];
	
	public List<Mob> mobs = new ArrayList<Mob>();
	public List<Dropper> droppers = new ArrayList<Dropper>();
	public List<Conveyor> conveyors = new ArrayList<Conveyor>();
	public List<Ore> ores = new ArrayList<Ore>();
	
	public Map(String filepath) {
		
	}
	
	public Map() {
		generateRandomMap();
	}
	
	public void generateRandomMap() {
		Random random = new Random();
		
		for(int y = 0; y < mapTiles.length; y++) {
			for(int x = 0; x < mapTiles[0].length; x++) {
				mapTiles[y][x] = Tile.dirtTile;
			}
		}
		
	}
	
	public void setTile(Tile t, int x, int y) {
		if(x < 0 || x >= mapTiles.length || y < 0 || y >= mapTiles.length) return;
		this.mapTiles[x][y] = t;
	}
	
	public void add(Entity e) {
		if(e instanceof Mob) mobs.add((Mob) e);
		else if(e instanceof Dropper) droppers.add((Dropper) e);
		else if(e instanceof Ore) ores.add((Ore) e);
		else if(e instanceof Conveyor) conveyors.add((Conveyor) e);
	}
	
	public void remove(Entity e) {
		if(e instanceof Mob) {
			e.remove();
			mobs.remove((Mob) e);
		}
		
	}
	
	public boolean dropperOrConveyorExists(int x, int y) {
		for(Dropper d : droppers) {
			if(d.getX() / 16 == x / 16 && d.getY() / 16 == y / 16) return true;
		}
		for(Conveyor c : conveyors) {
			if(c.getX() / 16 == x / 16 && c.getY() / 16 == y / 16) return true;
		}
		return false;
	}
	
	public void update() {
		mapTick++;
		if(mapTick >= 60000) mapTick = 0;
		
		for(int i = 0; i < mobs.size(); i++) mobs.get(i).update();
		
		for(int i = 0; i < droppers.size(); i++) {
			if(mapTick % droppers.get(i).getDroprate() == 0) {
				System.out.println(ores.size());
				droppers.get(i).dropOre();
			}
		}
	}
	
}
