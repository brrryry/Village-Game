package game.main.graphics.ui;

import java.util.ArrayList;
import java.util.List;

import game.main.entity.mob.Player;
import game.main.graphics.sprite.Sprite;
import game.main.graphics.ui.uiobject.UIGroup;
import game.main.graphics.ui.uiobject.UIObject;
import game.main.map.Map;

public class UI {

	protected Player player;
	protected Map map;
	
	protected List<UIObject> uiobjects = new ArrayList<UIObject>();
	
	UIObject moneyBox = new UIObject(0, 0, 1, Sprite.priceBox);
	UIGroup moneyString = UIObject.stringToSprite("money:", 3, 3, 1);
	
	protected Sprite s = Sprite.priceBox;
	
	public UI(Player p, Map m) {
		this.player = p;
		this.map = m;
		
		//permanent objects
		
	}
	
	public void update() {
		//if something on the UI was clicked, change something for the player
	}
	
	public List<UIObject> getUIObjects() {
		return uiobjects;
	}
	
	public void addObject(UIObject u) {
		this.uiobjects.add(u);
	}
	
	public void addGroup(UIGroup u) {
		this.uiobjects.addAll(u.getObjects());
	}
	
	public void addObjects(List<UIObject> u) {
		uiobjects.addAll(u);
	}

}
