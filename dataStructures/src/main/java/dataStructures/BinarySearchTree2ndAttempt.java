package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree2ndAttempt<T extends Comparable<T>> implements BST<T> {
	
	private BTNode<T> rootNode;

	public class Node implements BTNode<T> {
		
		private T value;
		private BTNode<T> leftNode;
		private BTNode<T> rightNode;
		
		public Node(T value) {
			this.setValue(value);
		}
		
		@Override
		public String toString() {
			return getValue().toString();
		}
		
		public int compareTo(T element) {
			// TODO Auto-generated method stub
			return this.getValue().compareTo(element);
		}

		public BTNode<T> getLeftChildNode() {
			return leftNode;
		}

		public void setLeftNode(BTNode<T> leftNode) {
			this.leftNode = leftNode;
		}

		public BTNode<T> getRightChildNode() {
			return rightNode;
		}

		public void setRightNode(BTNode<T> rightNode) {
			this.rightNode = rightNode;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}
		
	}

	public void addElements(List<T> asList) {
		for(T element : asList) {
			addElement(element);
		}
	}

	public BTNode<T> addElement(T element) {
		if(this.rootNode == null) {
			this.rootNode = new Node(element);
			return rootNode;
		} else {
			return addNode(rootNode,element);
		}
		
		
	}

	private Node addNode(BTNode<T> node, T element) {
		if(node.compareTo(element) > 0) {
			if(node.getLeftChildNode() != null) {
				return addNode(node.getLeftChildNode(), element);
			} else {
				Node newNode = new Node(element);
				node.setLeftNode(newNode);
				return newNode;
			}
		} else {
			if(node.getRightChildNode() != null) {
				return addNode(node.getRightChildNode(), element);
			} else {
				Node newNode = new Node(element);
				node.setRightNode(newNode);
				return newNode;
			}
			
		}
	}

	public BTNode<T> getRootNode() {
		// TODO Auto-generated method stub
		return rootNode;
	}

	public long getDepth() {
		
		return getDepth(rootNode);
	}

	private long getDepth(BTNode<T> node) {
		if(node == null)
			return 0;
		return Math.max(getDepth(node.getLeftChildNode()),getDepth(node.getRightChildNode())) + 1;
	}

	public List<T> traverseInOrder() {
		List<T> elements = new ArrayList<T>();
		traverseInOrder(rootNode , elements);
		return elements;
	}

	private void traverseInOrder(BTNode<T> node, List<T> elements) {
		if(node == null)
			return;
		traverseInOrder(node.getLeftChildNode(), elements);
		elements.add(node.getValue());
		traverseInOrder(node.getRightChildNode(), elements);
	}

	public int getWidthForLevel(int i) {
		
		return getWidth(rootNode , i);
		
	}

	private int getWidth(BTNode<T> node, int level) {
		if(node == null)
			return 0;
		if(level == 1)
			return 1;
		return getWidth(node.getLeftChildNode(), level -1) + getWidth(node.getRightChildNode(), level - 1);
	}

	public long getMaxWidthUsingPreOrderTraversal() {
		Long depth = getDepth();
		long[] widthArr = new long[depth.intValue()];
		getWidths(rootNode , widthArr , 0);
		
		long maxWidth = 0;
		for(long width : widthArr) {
			if(width > maxWidth)
				maxWidth = width;
		}
		return maxWidth;
	}

	private void getWidths(BTNode<T> node, long[] widthArr, int levelIndex) {
		if(node == null)
			return;
		else
			widthArr[levelIndex] ++;
		getWidths(node.getLeftChildNode(), widthArr, levelIndex + 1);
		getWidths(node.getRightChildNode(), widthArr, levelIndex + 1);

	}
	
	public long getMaxWidthUsingGetWidthMethod() {
		Long depth = getDepth();
		int[] widths = new int[depth.intValue()];
		for(int i = 0 ; i < depth-1 ; i ++) {
			widths[i] = getWidthForLevel(i + 1);
		}
		int maxWidth = 0;
		for(int width : widths) {
			if(width > maxWidth)
				maxWidth = width;
		}
		return Integer.valueOf(maxWidth).longValue();
	}
	
	public boolean contains(T element) {
		return contains(rootNode , element);
	}

	private boolean contains(BTNode<T> node, T element) {
		if(node == null) {
			return false;
		}
		int compareTo = node.getValue().compareTo(element);
		if(compareTo == 0) {
			return true;
		} else if( compareTo < 0) {
			return contains(node.getRightChildNode() , element);
		} else {
			return contains(node.getLeftChildNode(), element);
		}
	}
	
	public BTNode<T> searchElement(T element) {
		return searchElement(rootNode , element);
	}

	private BTNode<T> searchElement(BTNode<T> node, T element) {
		if(node == null) {
			return null;
		}
		int compareTo = node.getValue().compareTo(element);
		if(compareTo == 0) {
			return node;
		} else if( compareTo < 0) {
			return searchElement(node.getRightChildNode() , element);
		} else {
			return searchElement(node.getLeftChildNode(), element);
		}
	}
	
	public void deleteElement(T elementToBeDeleted) {
		rootNode = deleteElement(rootNode , elementToBeDeleted);
	}

	private BTNode<T> deleteElement(BTNode<T> node, T elementToBeDeleted) {
		
		if(node == null) {
			return null;
		}
		
		int compareTo = node.getValue().compareTo(elementToBeDeleted);
		
		if(compareTo > 0) {
			node.setLeftNode(deleteElement(node.getLeftChildNode(), elementToBeDeleted));
		} else if(compareTo < 0) {
			node.setRightNode(deleteElement(node.getRightChildNode(), elementToBeDeleted));
		} else {
			// this is the node to be deleted
			if(node.getLeftChildNode() == null)
				return node.getRightChildNode();
			else if(node.getRightChildNode() == null) {
				return node.getLeftChildNode();
			} else {
				// has two children
				
				BTNode<T> replacementSuccesorNode = getMinNode(node.getRightChildNode());
				node.setValue(replacementSuccesorNode.getValue());
				node.setRightNode(deleteElement(node.getRightChildNode(), replacementSuccesorNode.getValue()));
				return node;
				
			}
		}
		return node;

	}

	private BTNode<T> getMinNode(BTNode<T> node) {
		
		BTNode<T> temp = node;
		while(temp.getLeftChildNode() != null) {
			temp = temp.getLeftChildNode();
		}
		return temp;
		
	}
	
	
	public void removeDuplicates() {
		
		removeDuplicates(rootNode);
		
	}

	private void removeDuplicates(BTNode<T> node) {
		if(node != null) {
			searchAndDeleteDuplicate(node);
			removeDuplicates(node.getLeftChildNode());
			removeDuplicates(node.getRightChildNode());
		}
	}

	private void searchAndDeleteDuplicate(BTNode<T> node) {
		// TODO Auto-generated method stub
		BTNode<T> rightChild = node.getRightChildNode();
		if(rightChild != null) {

			BTNode<T> temp = rightChild;
			BTNode<T> tempParent = null;
			while(temp.getLeftChildNode() != null) {
				tempParent = temp;
				temp = temp.getLeftChildNode();
			}

			if(temp.compareTo(node.getValue()) == 0) {
				// duplicate !
				if(tempParent == null) {
					// right child of starting node is the left most node
					node.setRightNode(temp.getRightChildNode()); // deleted duplicate
				} else {
					// left most node of the right child found
					tempParent.setLeftNode(temp.getRightChildNode());
				}
				searchAndDeleteDuplicate(node);
			}

		}

	}
	
	
	public T findClosestCommonAncestor(T element1, T element2) {
		return findClosestCommonAncestor(rootNode , element1 , element2);
	}

	private T findClosestCommonAncestor(BTNode<T> node, T element1, T element2) {
		if(node == null)
			return null;

		int compareToElement1 = node.compareTo(element1);
		int compareToElement2 = node.compareTo(element2);
		int compareToSum = compareToElement1 + compareToElement2;

		if(compareToSum == 2) {
			return findClosestCommonAncestor(node.getLeftChildNode(), element1, element2);
		} else if (compareToSum == -2) {
			return findClosestCommonAncestor(node.getRightChildNode(), element1, element2);
		} else if (compareToSum == 0 || compareToSum == 1 || compareToSum == -1) {
			return node.getValue();
		}

		return null;
	}
	
}
