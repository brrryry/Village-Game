package game.main.entity.conveyor;

public class BasicConveyor extends Conveyor {

	private int speed = 1;
	
	public BasicConveyor(int x, int y) {
		super(x, y);
		this.setSpeed(speed);
	}
	
}
