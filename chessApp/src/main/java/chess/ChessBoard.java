package chess;

public class ChessBoard {
	
	
	private static ChessBoard chessBoard;
	private BoardPosition[][] board ;
	
	private ChessBoard(){
		
	}
	
	public static ChessBoard getInstance(){
		if(chessBoard == null){
			chessBoard = new ChessBoard();
			chessBoard.board = new BoardPosition[3][3];
			for (int i =0 ; i < 3; i++){
				chessBoard.board[i][0] = new BoardPosition(i,0);
				chessBoard.board[i][1] = new BoardPosition(i,1);
				chessBoard.board[i][2] = new BoardPosition(i,2);
			}
		}
		return chessBoard;
	}

	public BoardPosition[][] getBoard() {
		return board;
	}

	public BoardPosition getBoardPosition(BoardPosition startPosition, int deltaX, int deltaY) {
		int destinationXcordinate = startPosition.getxCordinate() + deltaX;
		if(destinationXcordinate > 3 -1 || destinationXcordinate < 0)
			return null;
		int destinationYcordinate = startPosition.getyCordinate() + deltaY;
		if(destinationYcordinate > 3 -1 || destinationYcordinate < 0)
			return null;
		return this.board[destinationXcordinate][destinationYcordinate];
	}



}
