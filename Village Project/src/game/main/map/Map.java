package game.main.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import game.main.entity.Entity;
import game.main.entity.conveyor.Conveyor;
import game.main.entity.dropper.Dropper;
import game.main.entity.mob.Mob;
import game.main.entity.ore.Ore;
import game.main.tile.Tile;

public class Map {

	public static final int MAPSIZE = 64; //typical size of map in tiles
	
	public static Map m = new Map();
	
	protected int oreLimit = 250;
	public static int mapTick = 0;
	
	
	public Tile[][] mapTiles = new Tile[MAPSIZE][MAPSIZE];
	
	public List<Mob> mobs = new ArrayList<Mob>();
	public List<Dropper> droppers = new ArrayList<Dropper>();
	public List<Conveyor> conveyors = new ArrayList<Conveyor>();
	public List<Ore> ores = new ArrayList<Ore>();
	
	public HashMap<Integer, Integer> blockCoords = new HashMap<Integer, Integer>();
	
	public Map(String filepath) {
		
	}
	
	public Map() {
		generateRandomMap();
	}
	
	public void generateRandomMap() {
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
		if(e instanceof Mob) mobs.remove((Mob) e);
		if(e instanceof Dropper) droppers.remove((Dropper) e);
		if(e instanceof Conveyor) conveyors.remove((Conveyor) e);
		if(e instanceof Ore) ores.remove((Ore) e);
		e.remove();
		
	}
	
	public boolean blockExists(int x, int y, Conveyor c) {
		
		for(Dropper d : droppers) {
			if(x == d.getX() && y == d.getY()) return true;
			if(x >= d.getX() && x < d.getX() + d.getXLength() && y >= d.getY() && y < d.getY() + d.getYLength()) return true;
			if(x + c.getXLength() - 1 >= d.getX() && x + c.getXLength() - 1 <= d.getX() + d.getXLength() - 1 && y + c.getYLength() - 1 >= d.getY() && y + c.getYLength() - 1 <= d.getY() + d.getYLength() - 1) return true;
		}
		for(Conveyor cx : conveyors) {
			if(x == cx.getX() && y == cx.getY()) return true;
			if(x >= cx.getX() && x < cx.getX() + cx.getXLength() && y >= cx.getY() && y < cx.getY() + cx.getYLength()) return true;
			if(x + c.getXLength() - 1 >= cx.getX() && x + c.getXLength() - 1 <= cx.getX() + cx.getXLength() - 1 && y + c.getYLength() - 1 >= cx.getY() && y + c.getYLength() - 1 <= cx.getY() + cx.getYLength() - 1) return true;
		}
		return false;
	}
	
	public boolean blockExists(int x, int y, Dropper dx) {
		for(Dropper d : droppers) {
			if(x == d.getX() && y == d.getY()) return true;
			if(x >= d.getX() && x < d.getX() + d.getXLength() && y >= d.getY() && y < d.getY() + d.getYLength()) return true;
			if(x + dx.getXLength() - 1 >= d.getX() && x + dx.getXLength() - 1 <= d.getX() + d.getXLength() - 1 && y + dx.getYLength() - 1 >= d.getY() && y + dx.getYLength() - 1 <= d.getY() + d.getYLength() - 1) return true;
		}
		for(Conveyor cx : conveyors) {
			if(x == cx.getX() && y == cx.getY()) return true;
			if(x >= cx.getX() && x < cx.getX() + cx.getXLength() && y >= cx.getY() && y < cx.getY() + cx.getYLength()) return true;
			if(x + dx.getXLength() - 1 >= cx.getX() && x + dx.getXLength() - 1 <= cx.getX() + cx.getXLength() - 1 && y + dx.getYLength() - 1 >= cx.getY() && y + dx.getYLength() - 1 <= cx.getY() + cx.getYLength() - 1) return true;
		}
		
		
		return false;
	}
	
	public Conveyor oreOnConveyor(Ore o) {
		for(Conveyor c : conveyors) {
			if((o.getX() + o.getSize() > c.getX() && o.getX() < c.getX() + c.getXLength()) && (o.getY() + o.getSize() > c.getY() && o.getY() < c.getY() + c.getYLength())) return c;
		}
		
		return null;
	}
	
	public void update() {
		removeProcess();
		mapTick++;
		if(mapTick >= 60000) mapTick = 0;
		//update mobs
		for(Mob m : mobs) m.update();
		
		//update droppers
		for(Dropper d : droppers) d.update();
		
		//update ores on conveyors
		for(Ore o : ores) o.update();
	}
	
	public int getOreSize() {
		return this.ores.size();
	}
	
	public boolean getOreLimit() {
		if(ores.size() >= oreLimit) return true;
		else return false;
	}
	
	public void insertCoords(int x, int y) {
		blockCoords.put(x, y);
	}
	
	public void removeProcess() {
		for(Mob m : mobs) if(m.removed()) remove(m);
		
		List<Dropper> removedDroppers = new ArrayList<Dropper>();
		for(Dropper d : droppers) if(d.removed()) removedDroppers.add(d);
		for(Dropper d : removedDroppers) remove(d);
		
		List<Ore> removedOres = new ArrayList<Ore>();
		for(Ore o : ores) if(o.removed()) removedOres.add(o);
		for(Ore r : removedOres) remove(r);
	
		//for(Ore o : ores) if (o.removed()) remove(o);
		List<Conveyor> removedConveyors = new ArrayList<Conveyor>();
		for(Conveyor c : conveyors) if (c.removed()) removedConveyors.add(c);
		for(Conveyor c : removedConveyors) remove(c);
	}
	
}
