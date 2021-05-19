package game.main.graphics.sprite;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Spritesheet {

	private String filepath; //file path of the spritesheet file
	private int width, height; //
	public int[] pixels;
	

	public static Spritesheet tiles1 = new Spritesheet("./res/Sprites/tiles/tiles1.png", 256, 256);
	
	public Spritesheet(String filepath, int width, int height) {
		this.filepath = filepath;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		loadSheet();
	}
	
	private void loadSheet() {
		try {
			BufferedImage image = ImageIO.read(new File(filepath)); //get bufferedimage
			System.out.println("Loading Spritesheet at " + filepath + " successfully");
			width = image.getWidth(); //set the width
			height = image.getHeight(); //set the height
			pixels = new int[width * height]; //make the pixel array
			image.getRGB(0, 0, width, height, pixels, 0, width); //https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html#getRGB(int,%20int)
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//getters and setters
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}
