package chess;

import java.util.Arrays;
import java.util.List;

public enum ChessPiece {
	
	KNIGHT(
			MovementType.KNIGHT_1LEFT_2DOWN,
			MovementType.KNIGHT_1LEFT_2UP,
			MovementType.KNIGHT_1RIGHT_2DOWN,
			MovementType.KNIGHT_1RIGHT_2UP,
			MovementType.KNIGHT_2LEFT_1DOWN,
			MovementType.KNIGHT_2LEFT_1UP,
			MovementType.KNIGHT_2RIGHT_1DOWN,
			MovementType.KNIGHT_2RIGHT_1UP
			)
	;
	
	
	private List<MovementType> possibleMoves;
	
	private ChessPiece(MovementType ...movementTypes ){
		this.possibleMoves = Arrays.asList(movementTypes);
	}

	public List<MovementType> getPossibleMoves() {
		return possibleMoves;
	}

	public void setPossibleMoves(List<MovementType> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}

}
