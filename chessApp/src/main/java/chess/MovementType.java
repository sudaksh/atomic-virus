package chess;

public enum MovementType {
	KNIGHT_1LEFT_2UP(-1,2),
	KNIGHT_1LEFT_2DOWN(-1,-2),
	KNIGHT_1RIGHT_2UP(1,2),
	KNIGHT_1RIGHT_2DOWN(1,-2),
	KNIGHT_2LEFT_1UP(-2,1),
	KNIGHT_2LEFT_1DOWN(-2,-1),
	KNIGHT_2RIGHT_1UP(2,1),
	KNIGHT_2RIGHT_1DOWN(2,-1)
	;
	
	private int deltaX;
	private int deltaY;
	
	private MovementType(int x , int y){
		this.deltaX = x;
		this.deltaY = y;
	}

	public int getDeltaX() {
		return deltaX;
	}
	public int getDeltaY() {
		return deltaY;
	}
}
