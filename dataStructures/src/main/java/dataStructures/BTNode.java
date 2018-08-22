package dataStructures;

public interface BTNode<T extends Comparable<T>> {

	BTNode<T> getLeftChildNode();
	
	void setLeftNode(BTNode<T> leftNode);

	BTNode<T> getRightChildNode();
	
	void setRightNode(BTNode<T> leftNode);
	
	public T getValue();

	public void setValue(T value);

	int compareTo(T element);

}
