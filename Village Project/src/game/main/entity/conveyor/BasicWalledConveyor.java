package game.main.entity.conveyor;

public class BasicWalledConveyor extends Conveyor {

	private int speed = 1;
	
	public BasicWalledConveyor(int x, int y) {
		super(x, y);
		this.setSpeed(speed);
	}
	
	public boolean walled() {
		return true;
	}
	
}
