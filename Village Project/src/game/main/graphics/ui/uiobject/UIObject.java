package game.main.graphics.ui.uiobject;

import java.util.ArrayList;
import java.util.List;

import game.main.graphics.sprite.Sprite;

public class UIObject {

	protected Sprite sprite;
	protected boolean visible;
	protected int magnification;
	protected int x, y;
	
	public static UIObject priceBox = new UIObject(0, 0, 1, Sprite.priceBox);
	
	public UIObject(int x, int y, int magnification, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.magnification = magnification;
		this.sprite = sprite;
		this.visible = true;
		
		
		
		this.setSprite(this.sprite.magnifySprite(magnification));
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	
	
	public static UIGroup stringToSprite(String s, int x, int y, int magnification) {
		List<UIObject> spriteList = new ArrayList<UIObject>();
		s = s.toUpperCase();
		
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(Character.isDigit(c)) spriteList.add(new UIObject(x + (i * 8 * magnification), y, magnification, Sprite.letters[c - 48]));
			else if(c == ':') spriteList.add(new UIObject(x + (i * 8 * magnification), y, magnification, Sprite.semicolon));
			else spriteList.add(new UIObject(x + (i * 8 * magnification), y, magnification, Sprite.letters[c - 65]));
		}
		return new UIGroup(x, y, spriteList);
	}
	
	public void update(int mouseX, int mouseY) {
		 
	}
	
	public void setSprite(Sprite s) {
		this.sprite = s;
	}
	
	public void setMagnification(int a) {
		this.magnification = a;
	}
	
	public int getMagnification() {
		return magnification;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void show() {
		this.visible = true;
	}
	
	public void hide() {
		this.visible = false;
	}
	
	public boolean visible() {
		return visible;
	}
}
