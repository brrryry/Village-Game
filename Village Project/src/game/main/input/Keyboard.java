package game.main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener { //yay! interfaces!

	private boolean[] keys = new boolean[120]; //this'll keep track of what keys are pressed
	public boolean up, down, left, right; //tells us if any important keys are pressed
	
	public void update() { //this is called in the main update method 60 times per second
		//update the boolean values (KeyEvent.VK_W is basically the ASCII value)
		up = keys[KeyEvent.VK_W]; 
		left = keys[KeyEvent.VK_A];
		down = keys[KeyEvent.VK_S];
		right = keys[KeyEvent.VK_D];
	}
	
	@Override
	public void keyTyped(KeyEvent e) { //ew no one cares about this method but it's required because of interface yada yada
		
	}

	@Override
	public void keyPressed(KeyEvent e) { //WHEN A KEY IS PRESSED
		keys[e.getKeyCode()] = true; //set the boolean of that key index to true
		//System.out.println(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) { //WHEN A KEY IS RELEASED
		keys[e.getKeyCode()] = false; //set the boolean of that key index to false
	}

}
