package game.main.graphics.ui.uiobject;

import java.util.ArrayList;
import java.util.List;

public class UIGroup {

	protected List<UIObject> objects = new ArrayList<UIObject>();
	protected int x, y;
	
	public UIGroup(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public UIGroup(int x, int y, List<UIObject> objects) {
		this.x = x;
		this.y = y;
		this.objects = objects;
	}
	
	public void add(UIObject o) {
		o.setX(o.getX() + this.x);
		o.setY(o.getY() + this.y);
		this.objects.add(o);
	}
	
	public List<UIObject> getObjects() {
		return objects;
	}
	
	
	
}
