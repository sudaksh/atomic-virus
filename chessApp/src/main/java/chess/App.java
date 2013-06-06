package chess;

public class App {

	public static void main(String[] args) {
		
		ChessPieceServiceImpl impl = new ChessPieceServiceImpl();
		impl.getShortestPath(ChessPiece.KNIGHT, ChessBoard.getInstance().getBoard()[0][0], ChessBoard.getInstance().getBoard()[1][0]);
		
	}
}
