package chess;

import java.util.ArrayList;
import java.util.List;

public class MyTree<E> {
	
	E nodeData;
	
	List<MyTree<E>> childrenTrees;
	MyTree<E> parentTree;
	
	public MyTree (E e){
		this.nodeData = e;
	}
	public MyTree (E e , MyTree<E> parent){
		this.nodeData = e;
		this.parentTree = parent;
	}
	
	public void addChildTree(E e) {
		if(childrenTrees==null)
			childrenTrees=new ArrayList<MyTree<E>>();
		childrenTrees.add(new MyTree<E>(e,this));
		
	}
	
	public E getNodeData() {
		return nodeData;
	}
	public void setNodeData(E nodeData) {
		this.nodeData = nodeData;
	}
	public List<MyTree<E>> getChildrenTrees() {
		return childrenTrees;
	}
	public void setChildrenTrees(List<MyTree<E>> childrenTrees) {
		this.childrenTrees = childrenTrees;
	}
	public MyTree<E> getParentTree() {
		return parentTree;
	}
	

}
