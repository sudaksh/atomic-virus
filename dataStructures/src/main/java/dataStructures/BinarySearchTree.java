package dataStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class BinarySearchTree<T extends Comparable<T>> {
	
	private BinarySearchTree<T>.BSTNode rootNode ;
	
	public class BSTNode {
		private T value;
		private BSTNode leftChildNode , rightChildNode;
		
		public BSTNode(T element) {
			this.value = element;
		}
		
		public BSTNode getLeftChildNode() {
			return leftChildNode;
		}
		
		public BSTNode getRightChildNode() {
			return rightChildNode;
		}
		
		public T getValue() {
			return value;
		}
		
		public int compareTo(BSTNode o) {
			return value.compareTo(o.getValue());
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return value.toString();
		}
		
	}

	public BSTNode addElement(T element) {
		BSTNode newNode = new BSTNode(element);
		if(rootNode == null)
			rootNode = newNode;
		else
			insertNode(rootNode,newNode);
		
		return newNode;
	}

	private BSTNode insertNode(BSTNode currentNode, BSTNode newChildNode) {
		if(currentNode == null)
			currentNode = newChildNode;
		else if(newChildNode.compareTo(currentNode) >= 0)
			currentNode.rightChildNode = insertNode(currentNode.getRightChildNode(), newChildNode);
		else
			currentNode.leftChildNode = insertNode(currentNode.getLeftChildNode(), newChildNode);
		
		return currentNode;
	}

	public BinarySearchTree<T>.BSTNode getRootNode() {
		return rootNode;
	}


	public List<T> traverseInOrder() {
		List<T> list = new LinkedList<T>();
		inOrderTraversal(list , this.rootNode);
		return list;
	}

	private void inOrderTraversal(List<T> list , BSTNode currentNode) {
		if(currentNode.getLeftChildNode() != null)
			inOrderTraversal(list, currentNode.getLeftChildNode());
		list.add(currentNode.getValue());
		if(currentNode.getRightChildNode() != null)
			inOrderTraversal(list, currentNode.getRightChildNode());
	}

	public long getDepth() {
		return getDepth(rootNode);
	}

	private long getDepth(BSTNode node) {
		if(node == null)
			return 0;
		else
			return Math.max(getDepth(node.getLeftChildNode()), getDepth(node.getRightChildNode())) + 1;
	}

	public long getWidthForLevel(long level ) {
		return getWidth(rootNode,level);
	}

	private long getWidth(BSTNode currentNode, long level) {
		if(currentNode == null)
			return 0;
		if(level == 1)
				return 1;
		else
			return getWidth(currentNode.getLeftChildNode(), level -1) + getWidth(currentNode.getRightChildNode(), level - 1);
	}

	public long getMaxWidthUsingPreOrderTraversal() {
		Long[] nodesPerLevel = new Long[(int)getDepth()];
		for(int i=0 ; i < getDepth() ; i++)
			nodesPerLevel[i] = 0l;
		populateLevelWidths(rootNode, 0, nodesPerLevel);
		return Collections.max(Arrays.asList(nodesPerLevel));
	}

	private void populateLevelWidths(BSTNode node, int currentLevel , Long[] nodesPerLevel) {
		if(node != null){
			nodesPerLevel[currentLevel]++;
			populateLevelWidths(node.getLeftChildNode(), currentLevel + 1, nodesPerLevel);
			populateLevelWidths(node.getRightChildNode(), currentLevel + 1, nodesPerLevel);
		}
	}

	public long getMaxWidthUsingGetWidthMethod() {
		long depth = getDepth();
		List<Long> widthPerLevel = new ArrayList<Long>((int)getDepth());
		for(int i =0 ; i< depth-1; i++)
			widthPerLevel.add(getWidthForLevel(i+1));
		return Collections.max(widthPerLevel);
	}
	
}
