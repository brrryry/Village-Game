package game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;

import game.main.entity.Inventory;
import game.main.entity.mob.Player;
import game.main.graphics.Window;
import game.main.graphics.sprite.Sprite;
import game.main.graphics.ui.UI;
import game.main.input.Keyboard;
import game.main.input.Mouse;
import game.main.map.Map;

/**
 * The Game Class is used as the main class that starts the game. This class contains the start/stop methods for the main thread as well as the universal run/update/render methods.
 * 
 * @author bryan.chan
 * @version 0.0.1
 *
 */

public class Game extends Canvas implements Runnable { //making a class
	
	//Name and Dimensions
	public static final String TITLE = "Village Game";
	public static final int WIDTH = 400;
	public static final int HEIGHT = WIDTH * 9 / 16;
	public static final int SCALE = 2;
	
	//Updates/Frames
	public static final int UPS = 60;
	public static final int FPS = 60;
	

	//Runtime Statistic Report
	public static final boolean REPORTFILE = true; //should we have a report file?
	
	private static File reportFile;
	private static double minRuntime = Double.MAX_VALUE;
	private static double maxRuntime = 0;
	public static int lss = 0; //loops since start
	public static double loopTime = 0; //amount of time it takes
	
	//Container and Thread
	private JFrame frame;
	private Thread thread;
	
	//Image and Pixel Array of said image
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); //This is an image that we'll be drawing onto the JFrame!
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	//Custom Class Components
	private Window window;
	private Map map;
	private Keyboard keyboard;
	private Mouse mouse;
	private Player player;
	private UI ui;
	
	//Game Status
	private boolean running = false;
	
	
	//Constructor
	public Game() throws IOException { 
		
		//JFrame Creation
		frame = new JFrame(TITLE);
		Dimension res = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		
		//Screen Size
		frame.setMinimumSize(res);
		frame.setPreferredSize(res);
		frame.setMaximumSize(res);
		frame.setResizable(false); //changing this later obviously
		
		//Initialize Resources
		Sprite.initialize();
		
		//Initialize Components
		map = new Map();
		window = new Window(WIDTH, HEIGHT);
		keyboard = new Keyboard();
		mouse = new Mouse();
		
		addKeyListener(keyboard);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	
				
		
		//Load/Build Player and UI 
		Inventory i = new Inventory();
		i.add(1000, 10001);
		i.add(1000, 0);
		
		player = new Player("YouLikeCats", 0, 0, Sprite.testPlayer, map, keyboard, mouse, i);
		
		ui = new UI(player, map);
		
		System.out.println(Game.SCALE);
		
		//Report File
		if(REPORTFILE) reportFile = getReportFile();
		
	}
	
	/**
	 * This method initializes and starts a thread.
	 * @param Nothing
	 * @return Nothing
	 */
	public synchronized void start() {
		thread = new Thread(this); //"this" allows us to use the run method in this class
		running = true;
		thread.start();
	}
	
	/**
	 * This method stops the thread and ends the game.
	 * @param Nothing
	 * @return Nothing
	 */
	public synchronized void stop() {
		running = false;
		
		try {
			thread.join();
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	/**
	 * This method is called whenever the thread starts.
	 * @param Nothing
	 * @return Nothing
	 */
	public void run() {
		
		//Initialize Variables
		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / UPS;
		final double timeF = 1000000000 / FPS;
		double deltaU = 0, deltaF = 0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();
		
		//This while loop continues until the game is closed
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
		        requestFocus();
		        frames = 0;
		        ticks = 0;
		        timer += 1000;
		    }
		    
		    //Edit Runtime Statistics
		    long endTime = System.nanoTime();
			double runTime = (endTime - currentTime) / 1e6;
			if(runTime > Game.maxRuntime) Game.maxRuntime = runTime;
			if(runTime < Game.minRuntime) Game.minRuntime = runTime;
			loopTime = (loopTime + runTime) / 2;
		}
	}
	
	/**
	 * This method is called when exiting the game.
	 * @param Nothing
	 * @return Nothing
	 * @throws FileNotFoundException
	 * @see FileNotFoundException
	 */
	public static void exit() throws FileNotFoundException {
		if(REPORTFILE) saveReportFile(reportFile);
		System.exit(0);
	}
	

	/**
	 * This method is used as a universal update method for the game.
	 * @param Nothing
	 * @return Nothing
	 */
	public void update() {
		keyboard.update();
		map.update();
	}
	
	/**
	 * This method is used as a universal render method for the game.
	 * @param Nothing
	 * @return Nothing
	 */
	public void render() {
		//Buffer Strategy
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		//Manipulate Window
		window.clear();
		window.setOffset(player.getX() - window.width / 2, player.getY() - window.height / 2);
		window.render(map, ui);
		for(int i = 0; i < pixels.length; i++) pixels[i] = window.pixels[i];
		
		//Graphics Drawing
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.blue);
		g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null); //Draw Game Image
		
		//Image Presentation
		g.dispose();
		bs.show();
		
	}
	
	/**
	 * This method creates a file using the UUID of the client computer that will be used to store Runtime Statistics of the game experience.
	 * @param Nothing
	 * @return A file to store Runtime Statistics
	 * @throws IOException
	 * @see IOException
	 */
	public static File getReportFile() throws IOException {
		//Get Computer UUID
		final Process p = Runtime.getRuntime().exec("wmic csproduct get UUID");
		BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		
		while((line = output.readLine()) != null) {
			line = line.trim();
			
			//this "if" statement is used because of the way that the BufferedReader takes in the output.
			if(line.length() > 4) {
				break;
			}
		}
		
		//Create File Name
		String filePath = "./res/reports/" + line + "-0.txt";
		int id = 0;
		
		boolean notFound = false;
		File file = new File(filePath);
		
		while(!notFound) {
			file = new File(filePath);
			if(file.exists()) {
				filePath = filePath.substring(0, filePath.length() - 4 - Integer.toString(id).length());
				id++;
				filePath += id + ".txt";
			} else notFound = true;
		}
		
		
		file.createNewFile();
		return file;
	}
	
	/**
	 * This method writes the Runtime Statistics to the report file.
	 * @param File used to write statistics
	 * @return Nothing
	 * @throws FileNotFoundException
	 * @see FileNotFoundException
	 */
	public static void saveReportFile(File f) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(f);
		pw.println("Minimum Run Time: " + minRuntime + "ms");
		pw.println("Maximum Run Time: " + maxRuntime + "ms");
		pw.println("Average Run Time: " + loopTime + "ms");
		pw.close();
	}
	
	/**
	 * This method is the main method. This is used to create the instance of the game that the user will use to play.
	 * @param args (unused)
	 * @return Nothing
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException { //main method
		Game game = new Game();
		game.frame.setLocationRelativeTo(null);
		game.frame.add(game); 
		game.frame.pack();
		
		//This lets the exit method work at the end of the game.
		game.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		game.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					exit();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		game.frame.setVisible(true);
		game.start(); 
	}
	
}
