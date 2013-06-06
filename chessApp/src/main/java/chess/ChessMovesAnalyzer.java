package chess;

import java.util.List;

public interface ChessMovesAnalyzer {

	List<BoardPosition> getPossibleDestinations(ChessPiece chessPiece, BoardPosition nodeData);

}
