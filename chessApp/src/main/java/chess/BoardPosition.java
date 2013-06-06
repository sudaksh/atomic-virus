package chess;

public class BoardPosition {
	
	private int xCordinate;
	private int yCordinate;
	
	public BoardPosition(int x, int y) {
		this.xCordinate = x;
		this.yCordinate = y;
	}
	public int getxCordinate() {
		return xCordinate;
	}
	public int getyCordinate() {
		return yCordinate;
	}
	
	@Override
	public String toString() {
		return "(" + xCordinate + " , " + yCordinate + ")";
	
	}
	
	public boolean equals(BoardPosition obj) {
		return this.xCordinate == obj.xCordinate && this.yCordinate == obj.yCordinate;
	}
	
}
