package game.main.entity;

import java.util.ArrayList;
import java.util.List;

import game.main.entity.conveyor.Conveyor;
import game.main.entity.dropper.Dropper;

public class Inventory {

	protected List<Integer> inv;
	
	public Inventory() {
		this.inv = new ArrayList<Integer>();
	}
	
	public void add(int quantity, int id) {
		for(int i = 0; i < quantity; i++) inv.add(id);
	}
	
	public void remove(Entity e) {
		for(int i = 0; i < inv.size(); i++) {
			if(inv.get(i) == e.getID()) {
				inv.remove(i);
				return;
			}
		}
	}
	
	public boolean itemExists(Entity e) {
		for(int i : inv) if(i == e.getID()) return true;
		return false;
	}
	
}
