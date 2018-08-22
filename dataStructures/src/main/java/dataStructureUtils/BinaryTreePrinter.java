package dataStructureUtils;

import java.util.LinkedList;
import java.util.Queue;

import dataStructures.BST;
import dataStructures.BTNode;

public class BinaryTreePrinter {
	
	private String delimiter = "   ";
	private String emptyNode = "___";

	public <T extends Comparable<T>> void printLevelOrder(BST<T> binaryTree) {
		Queue<BTNode<T>> levelOrderQueue = new LinkedList<BTNode<T>>();
		levelOrderQueue.add(binaryTree.getRootNode());
		long nodesInCurrentLevel = 1;
		long currentLevel = 1;
		long depth = binaryTree.getDepth();
		long currentFloor = depth - currentLevel + 1;
		long numOfStartingDelimiters = (long)Math.pow(2, currentFloor-1) - 1;
		long numOfSeparatingDelimiters = 0;
		printDelimiters(numOfStartingDelimiters);
		
		while(!levelOrderQueue.isEmpty() && currentFloor >0){
			
			BTNode<T> currentNode = levelOrderQueue.poll();
			nodesInCurrentLevel--;
			
			if(currentNode !=null){
				levelOrderQueue.add(currentNode.getLeftChildNode());
				levelOrderQueue.add(currentNode.getRightChildNode());
				System.out.print(currentNode);
			}else {
				levelOrderQueue.add(null);
				levelOrderQueue.add(null);
				System.out.print(emptyNode);
			}
			if(nodesInCurrentLevel>0)
				printDelimiters(numOfSeparatingDelimiters);
			
			if(nodesInCurrentLevel == 0){
				currentLevel++;
				currentFloor--;
				nodesInCurrentLevel = (long)Math.pow(2, currentLevel-1);
				numOfStartingDelimiters = (long)Math.pow(2, currentFloor-1) - 1;
				numOfSeparatingDelimiters = (long)Math.pow(2, currentFloor) -1;
				System.out.print("\n");
				printDelimiters(numOfStartingDelimiters);
			}
		}
	}

	private void printDelimiters(long numOfDelimiters) {
		for(int i = 0 ; i < numOfDelimiters ; i++)
			System.out.print(delimiter);
	}

}
