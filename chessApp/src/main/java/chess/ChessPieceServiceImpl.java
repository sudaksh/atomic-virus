package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ChessPieceServiceImpl {
	
//	private ChessMovesAnalyzer movesAnalyzer;

	public void getShortestPath(ChessPiece chessPiece , BoardPosition startingPosition , BoardPosition endingPosition){
		
		MyTree<BoardPosition> possibleMoves = new MyTree<BoardPosition>(startingPosition);
		MyTree<BoardPosition> desiredLeaf = createAndTraverseTree(chessPiece, possibleMoves,endingPosition);
		printTillRoot(desiredLeaf);
		
	}

	private void printTillRoot(MyTree<BoardPosition> desiredLeaf) {
		System.out.println(desiredLeaf.getNodeData());
		if(desiredLeaf.getParentTree() != null)
			printTillRoot(desiredLeaf.getParentTree());
	}

	private MyTree<BoardPosition> createAndTraverseTree(ChessPiece chessPiece,
			MyTree<BoardPosition> possibleMoves, BoardPosition endingPosition) {

		Queue<MyTree<BoardPosition>> breadthFirstQueue = new LinkedBlockingQueue<MyTree<BoardPosition>>();
		breadthFirstQueue.add(possibleMoves);
		boolean[][] visitedMap = new boolean[3][3];
		while(!breadthFirstQueue.isEmpty()){
			MyTree<BoardPosition> node = breadthFirstQueue.poll();
			if(node.getNodeData().equals(endingPosition))
				return node;
			List<BoardPosition> possibleDestinations = getPossibleDestinations(chessPiece,node.getNodeData());

			for (BoardPosition boardPosition : possibleDestinations){
				if (!visitedMap[boardPosition.getxCordinate()][boardPosition.getyCordinate()]){
					node.addChildTree(boardPosition);
					visitedMap[boardPosition.getxCordinate()][boardPosition.getyCordinate()] = true;
				}
				
			}
			if(node.getChildrenTrees()!=null)
				breadthFirstQueue.addAll(node.getChildrenTrees());

		}
		
		
		return null;
	}

	private List<BoardPosition> getPossibleDestinations(ChessPiece chessPiece,BoardPosition nodeData) {
		List<BoardPosition> positions = new ArrayList<BoardPosition>();
		for(MovementType movementType : chessPiece.getPossibleMoves()){
			BoardPosition reachablePosition = ChessBoard.getInstance().getBoardPosition(nodeData,movementType.getDeltaX(),movementType.getDeltaY());
			if(reachablePosition != null)
				positions.add(reachablePosition);
		}
		return positions;
	}

}
