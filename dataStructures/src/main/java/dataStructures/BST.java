package dataStructures;

import java.util.List;

public interface BST<T extends Comparable<T>> {

	BTNode<T> getRootNode();

	long getDepth();

	void addElements(List<T> asList);

	BTNode<T> addElement(T element);

	List<T> traverseInOrder();

	int getWidthForLevel(int i);

	long getMaxWidthUsingPreOrderTraversal();

	long getMaxWidthUsingGetWidthMethod();

	boolean contains(T element);

	BTNode<T> searchElement(T element);

	void deleteElement(T elementToBeDeleted);

	void removeDuplicates();

	T findClosestCommonAncestor(T element1, T element2);

}
