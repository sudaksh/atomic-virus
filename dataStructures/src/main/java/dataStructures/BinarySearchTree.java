package dataStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class BinarySearchTree<T extends Comparable<T>> implements BST<T> {
	
	private BinarySearchTree<T>.BSTNode rootNode ;
	
	public class BSTNode implements BTNode<T> {
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
		
		public void setValue(T value) {
			this.value = value;
		}
		
		public int compareTo(BSTNode o) {
			return value.compareTo(o.getValue());
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return value.toString();
		}

		public void setLeftNode(BTNode<T> leftNode) {
			// TODO Auto-generated method stub
			
		}

		public void setRightNode(BTNode<T> leftNode) {
			// TODO Auto-generated method stub
			
		}

		public int compareTo(T element) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	

	public void addElements(List<T> elements) {
		for(T element : elements)
			addElement(element);
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
		if(currentNode != null){
			inOrderTraversal(list, currentNode.getLeftChildNode());
			list.add(currentNode.getValue());
			inOrderTraversal(list, currentNode.getRightChildNode());
		}
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

	public int getWidthForLevel(int level ) {
		return getWidth(rootNode,level);
	}

	private int getWidth(BSTNode currentNode, int level) {
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
			widthPerLevel.add( Integer.valueOf(getWidthForLevel(i+1)).longValue());
		return Collections.max(widthPerLevel);
	}

	public void removeDuplicates() {
		removeDuplicatesPreOrderTraversal(rootNode);
	}

	private void removeDuplicatesPreOrderTraversal(BSTNode currentNode) {
		if(currentNode != null){
			searchAndDeleteNextDuplicate(currentNode);
			removeDuplicatesPreOrderTraversal(currentNode.getLeftChildNode());
			removeDuplicatesPreOrderTraversal(currentNode.getRightChildNode());
		}
	}

	private void searchAndDeleteNextDuplicate(BSTNode originalNode) {
		if(originalNode.getRightChildNode() != null){
			BSTNode currentNode = originalNode.getRightChildNode();
			BSTNode parentOfDuplicate = originalNode;
			boolean isLeftChild = false;
			boolean hasDuplicate = false;
			while(currentNode.getLeftChildNode() != null){
				parentOfDuplicate = currentNode;
				currentNode = currentNode.getLeftChildNode();
				isLeftChild = true;
			}
			if(currentNode.getValue().compareTo(originalNode.getValue()) == 0){
				hasDuplicate = true;
				deleteNode(currentNode, parentOfDuplicate, isLeftChild);
			}
			if(hasDuplicate)
				searchAndDeleteNextDuplicate(originalNode);
		}
	}


	public boolean contains(T val) {
		return (searchElement(val) !=null);
	}

	public BSTNode searchElement(T searchElement) {
		BSTNode currentNode = rootNode;
		while(currentNode !=null && currentNode.getValue().compareTo(searchElement) !=0 ){
			if(searchElement.compareTo(currentNode.getValue()) >0){
				currentNode = currentNode.getRightChildNode();
			}else {
				currentNode = currentNode.getLeftChildNode();
			}
		}
		return currentNode;
	}

	public void deleteElement(T element) {
		BSTNode nodeToBeDeleted = rootNode;
		BSTNode parentNode = null;
		boolean isLeftChild = false;
		while(nodeToBeDeleted !=null && nodeToBeDeleted.getValue().compareTo(element) !=0 ){
			parentNode = nodeToBeDeleted;
			if(element.compareTo(nodeToBeDeleted.getValue()) >0){
				nodeToBeDeleted = nodeToBeDeleted.getRightChildNode();
				isLeftChild = false;
			}else {
				nodeToBeDeleted = nodeToBeDeleted.getLeftChildNode();
				isLeftChild = true;
			}
		}
		if(nodeToBeDeleted != null)
			deleteNode(nodeToBeDeleted, parentNode, isLeftChild);
		
	}

	private void deleteNode(BSTNode nodeToBeDeleted, BSTNode parentNode, boolean isLeftChild) {
		BSTNode replacementNode = null;
		if(nodeToBeDeleted.getLeftChildNode() == null){
			replacementNode = nodeToBeDeleted.getRightChildNode();
		}else if (nodeToBeDeleted.getRightChildNode() == null) {
			replacementNode = nodeToBeDeleted.getLeftChildNode();
		}else {
			//Both children are present.
			replacementNode = getSuccessorForReplacement(nodeToBeDeleted);
		}
		if(nodeToBeDeleted != rootNode){
			if(isLeftChild)
				parentNode.leftChildNode = replacementNode;
			else
				parentNode.rightChildNode = replacementNode;
		}else {
			rootNode = replacementNode;
		}
	}

	private BSTNode getSuccessorForReplacement(BSTNode nodeToBeDeleted) {
		BSTNode replacementNode;
		replacementNode = nodeToBeDeleted.getRightChildNode();
		BSTNode parentOfReplacementNode = nodeToBeDeleted;
		while(replacementNode.getLeftChildNode() != null){
			parentOfReplacementNode = replacementNode;
			replacementNode = replacementNode.getLeftChildNode();
		}
		if(parentOfReplacementNode == nodeToBeDeleted){
			replacementNode.leftChildNode = nodeToBeDeleted.getLeftChildNode();
			
		}else {
			parentOfReplacementNode.leftChildNode = replacementNode.getRightChildNode();
			replacementNode.leftChildNode = nodeToBeDeleted.getLeftChildNode();
			replacementNode.rightChildNode = nodeToBeDeleted.getRightChildNode();
		}
		return replacementNode;
	}
	
}
