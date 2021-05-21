package game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.net.URLClassLoader;

import javax.print.DocFlavor.URL;
import javax.swing.JFrame;

import game.main.entity.mob.Player;
import game.main.graphics.Window;
import game.main.graphics.sprite.Sprite;
import game.main.input.Keyboard;
import game.main.input.Mouse;
import game.main.map.Map;

public class Game extends Canvas implements Runnable { //making a class

	public static final String TITLE = "Village Game"; //title
	
	//setting dimensions
	
	/*
	 * We use a scale so that we can change screen size later!
	 */
	public static final int WIDTH = 400;
	public static final int HEIGHT = WIDTH * 9 / 16;
	public static final int SCALE = 4;
	
	public static final int UPS = 60;
	public static final int FPS = 120;
	
	//creating Java Components
	private JFrame frame; //This is the "frame", or the "window" of the screen
	private Thread thread; //This is a process.
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); //This is an image that we'll be drawing onto the JFrame!
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); //this is the array of pixels that will be shown on the screen
	
	//custom class components
	private Window window;
	private Map map; //create a new map (current map that the player is on)
	private Keyboard keyboard; //This is a keyboard (CTRL + Click "Keyboard" for reference)
	private Mouse mouse; //This is a mouse (CTRL + Click "Mouse" for reference)
	private Player player;
	
	private boolean running = false; //This keeps track of whether the game is running or not
	
	public Game() { //constructor!
		frame = new JFrame(TITLE); //init the JFrame
		Dimension res = new Dimension(WIDTH * SCALE, HEIGHT * SCALE); //create a new Dimension named res to set screen size
		
		map = new Map();
		
		//setting screen size
		frame.setMinimumSize(res);
		frame.setPreferredSize(res);
		frame.setMaximumSize(res);
		frame.setResizable(false); //changing this later obviously
		
		//create window
		window = new Window(WIDTH, HEIGHT);
		
		//init keyboard and mouse (again, go check the classes for reference)
		keyboard = new Keyboard();
		mouse = new Mouse();
		
		player = new Player("Bryan", 0, 0, Sprite.testPlayer, map, keyboard);
		
		//adding the input components to the object (Game)
		addKeyListener(keyboard);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	public synchronized void start() { //start method (called whenever we do Game.start())
		thread = new Thread(this); //init thread
		running = true; //we are running!
		thread.start(); //start the thread process (continuously loops the run method because of the "Runnable" implementation)
	}
	
	public synchronized void stop() { //stop method
		running = false; //we are NOT running!
		try {
			thread.join(); //yeet the threads
		} catch (Exception e) { //if there's an exception print it out
			e.printStackTrace();
		}
	}
	
	public void run() {

		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / UPS;
		final double timeF = 1000000000 / FPS;
		double deltaU = 0, deltaF = 0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();

		    while (running) {

		        long currentTime = System.nanoTime();
		        deltaU += (currentTime - initialTime) / timeU;
		        deltaF += (currentTime - initialTime) / timeF;
		        initialTime = currentTime;

		        if (deltaU >= 1) {
		            update();
		            ticks++;
		            deltaU--;
		        }

		        if (deltaF >= 1) {
		            render();
		            frames++;
		            deltaF--;
		        }

		        if (System.currentTimeMillis() - timer > 1000) {
		            frame.setTitle(TITLE + " | " + String.format("UPS: %s, FPS: %s", ticks, frames));
		       
		            frames = 0;
		            ticks = 0;
		            timer += 1000;
		        }
		    }
		}
	

	
	public void update() { //update components (keybaord input, mouse input, etcetc)
		keyboard.update();
		player.update();
	}
	
	public void render() { //render componenets
		BufferStrategy bs = getBufferStrategy(); //BufferStrategy = a strategy to render images
		if(bs == null) { //if it doesn't exist
			createBufferStrategy(3); //create 3 "spots" or "backup images". 
			return;
		}
		
		/*
		 * BufferStrategy is used to have images layed out back-to-back so that there is no lag. This way, when an image is loaded,
		 * the next image is already up for preperation.
		 */
		
		window.clear();
		
		//set screen pixels to actual pixel array
		window.setOffset(player.getX() - window.width / 2, player.getY() - window.height / 2);
		window.render(map); //calls the render method
		window.renderPlayer(player);
		for(int i = 0; i < pixels.length; i++) pixels[i] = window.pixels[i]; //sets stuff
		
		
		Graphics g = bs.getDrawGraphics(); //graphics of the BufferStrategy
		
	
		g.setColor(Color.blue); //set the drawing color to black
		g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE); //BIG RECTANGLE BOIS
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null); //DRAW IMAGE BOIS (this draws the game)
		
		g.dispose(); //yeet graphics
		bs.show(); //present the image to the screen
		
	}
	
	public void setMap(Map m) { //possibly setting a new map?
		this.map = m; //this initializes the map to the new map that is being set
	}
	
	public static void main(String[] args) throws IOException { //main method
		
		Game game = new Game(); //new object!
		game.frame.setLocationRelativeTo(null); //center screen
		game.frame.add(game); //add game component
		game.frame.pack(); //idek what this does
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets the "x" button
		game.frame.setVisible(true); //makes the frame, ya'know, VISIBLE
		game.start(); //start method!
		
		
	}
	
}
