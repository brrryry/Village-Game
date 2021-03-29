package game.main.graphics;

public class Window { //this class takes care of all the rendering and sets its pixels to the screen pixels
	
	private int width, height; //width and height of the screen
	public int[] pixels; //pixel array that will be sent to the main class's pixels
	
	public Window(int width, int height) { //constructor
		this.width = width; //sets width
		this.height = height; //sets height
		pixels = new int[width * height]; //sets pixels array
	}
	
	public void render() { //render stuff on the screen! (We'll probably change this later)
		/*
		 * Possibly something like
		 * renderMobs();
		 * renderStuff();
		 * renderThings();
		 */
	}

}
