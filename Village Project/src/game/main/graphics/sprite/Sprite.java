package game.main.graphics.sprite;

import java.util.ArrayList;
import java.util.List;

import game.main.entity.conveyor.Conveyor;
import game.main.graphics.ui.uiobject.UIObject;

public class Sprite {

	public int[] pixels;
	public int xsize, ysize;
	public int bound;
	private Spritesheet sheet;
	
	
	//Random Color Sprites
	public static Sprite dirt1, debug1;
	public static Sprite testPlayer, blackSprite, basicConveyor, longConveyor, priceBox;
	
	public static Sprite[] letters;
	public static Sprite semicolon;
	

	
	public static void initialize() {
		dirt1 = new Sprite(0, 0, 16, 16, 0, Spritesheet.tiles1);
		debug1 = new Sprite(16, 0, 16, 16, 0, Spritesheet.tiles1);
		testPlayer = new Sprite(32, 0, 16, 16, 3, Spritesheet.tiles1);
		blackSprite = new Sprite(16, 0, 16, 16, 0, Spritesheet.tiles1);
		
		basicConveyor = new Sprite(0, 0, 16, 16, 0, Spritesheet.conveyors);
		longConveyor = new Sprite(0, 0, 16, 32, 0, Spritesheet.conveyors);
		
		priceBox = new Sprite(0, 0, 64, 16, 0, Spritesheet.ui);
		
		letters = new Sprite[26];
		for(int i = 0; i < letters.length; i++) letters[i] = new Sprite(65 + (i * 8), 0, 8, 8, 0, Spritesheet.ui);
		
		semicolon = new Sprite(353, 0, 8, 8, 0, Spritesheet.ui);
	}
	
	public Sprite(int x, int y, int xsize, int ysize, int bound, Spritesheet sheet) {
		this.pixels = new int[xsize * ysize];
		//System.out.println(xsize * ysize);
		this.xsize = xsize;
		this.ysize = ysize;
		this.bound = bound;
		
		for(int yy = 0; yy < ysize; yy++) {
			for(int xx = 0; xx < xsize; xx++) {
				pixels[xx + yy * xsize] = sheet.pixels[(x + xx) + (y + yy) * sheet.getWidth()];
			}
		}
	}
	
	public Sprite(int x, int y, int size, int col) {
		pixels = new int[size * size];
		for(int yy = 0; yy < size; yy++) {
			for(int xx = 0; xx < size; xx++) {
				pixels[xx + yy * size] = col;
			}
		}
	}
	
	public Sprite(int xsize, int ysize, int[] array) {
		this.xsize = xsize;
		this.ysize = ysize;
		this.pixels = array;
	}
	
	public Sprite(int[] array) {
		pixels = new int[array.length];
		for(int i = 0; i < pixels.length; i++) pixels[i] = array[i];
	}
	
	public int getBound() {
		return bound;
	}
	
	
	public Sprite rotateConveyor(Conveyor c) {
		int[] shifted = new int[c.getSprite().pixels.length];
		int iteration = 0;
		int spriteTick = 1;
		for(int i = 0; i < this.pixels.length; i++) {
			System.out.println((c.getXLength() * c.getYLength()) - (spriteTick * c.getXLength()) + iteration);
			shifted[i] = this.pixels[(c.getXLength() * c.getYLength()) - (spriteTick * c.getXLength()) + iteration];
			spriteTick++;
            if(spriteTick > c.getYLength()) {
            	System.out.println("NEW ROW");
            	iteration++;
            	spriteTick = 1;
            }
		}

		int temp = c.getYLength();
		c.setYLength(c.getXLength());
		c.setXLength(temp);
		Sprite returnSprite = new Sprite(shifted);
		return returnSprite;
	}
	
	public Sprite magnifyUIObject(UIObject u) {
		//int[] magnify = new int[(int) (this.pixels.length * Math.pow(multiplier, 2))];
		return new Sprite(this.pixels);
	}
	
	public Sprite magnifySprite(int m1) { //m1 is magnification
		if(m1 == 1) return this; //if m1 is 1, return nothing
		
		int[] pixel = new int[(int) (this.pixels.length * Math.pow(m1, 2))]; //make new pixel array the size of the "bigger" sprite
		
		for(int i = 0; i < pixel.length; i++) {
			int index = ((int) (i / (this.getXSize() * Math.pow(m1, 2))) * this.getXSize()) + ((i % (this.getXSize() * m1)) / m1);
			pixel[i] = this.pixels[index];
			//System.out.println((i % (m1 * this.getSprite().getXSize())) / m1);
		}
		
		
		Sprite s = new Sprite(this.getXSize() * m1, this.getYSize() * m1, pixel);
		//System.out.println("magnified!");
		
		return s;
		
	}
	
	public int getXSize() {
		return xsize;
	}
	
	public int getYSize() {
		return ysize;
	}
	
	
	
	
}
