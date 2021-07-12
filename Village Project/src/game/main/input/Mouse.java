package game.main.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener { //ok that's 2 interfaces 0_0

	public int x, y; //give us the x and y coordinates of the mouse (works in a similar way to 2d arrays)
	public int mb = -1; //gives us the mouse button that's being pressed
	
	@Override
	public void mouseClicked(MouseEvent e) { //ew no one cares
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) { //WHEN A MOUSE BUTTON IS PRESSED
		mb = e.getButton(); //set the mouse button variable to the value of that mouse button
	}

	@Override
	public void mouseReleased(MouseEvent e) { //WHEN A MOUSE BUTTON IS RELEASED
		mb = -1; //change it back to default to signify that it's no longer being pressed
	}

	@Override
	public void mouseEntered(MouseEvent e) { //ew no one cares
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) { //ew no one cares
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent e) { //ew no one cares
		x = e.getX(); //set x to the mouse's x position
		y = e.getY(); //set y to the mouse's y posiiton
	}

	@Override
	public void mouseMoved(MouseEvent e) { //WHEN THE MOUSE IS MOVED
		x = e.getX(); //set x to the mouse's x position
		y = e.getY(); //set y to the mouse's y posiiton
	}

}
