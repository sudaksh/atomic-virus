package dataStructures;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import org.junit.Test;

import dataStructureUtils.BinaryTreePrinter;

public class BinarySearchTreeTest extends TestCase{
	
	BinarySearchTree<Integer> binarySearhTree;
	BinarySearchTree<Integer> binarySearhTreeDepthFive;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		binarySearhTree = new BinarySearchTree<Integer>();
		Random random = new Random();
		for(int i = 0 ; i< 15 ; i++){
			Integer element = (int)(random.nextFloat() * 1000);
			binarySearhTree.addElement(element);
		}
		binarySearhTreeDepthFive = new BinarySearchTree<Integer>();
		binarySearhTreeDepthFive.addElement(500);
		binarySearhTreeDepthFive.addElement(333);
		binarySearhTreeDepthFive.addElement(444);
		binarySearhTreeDepthFive.addElement(123);
		binarySearhTreeDepthFive.addElement(764);
		binarySearhTreeDepthFive.addElement(888);
		binarySearhTreeDepthFive.addElement(333);
		binarySearhTreeDepthFive.addElement(1000);
		binarySearhTreeDepthFive.addElement(378);
		binarySearhTreeDepthFive.addElement(600);
		binarySearhTreeDepthFive.addElement(200);
	}
	
	@Test
	public void testAddElement() {
		Integer newElement = 1000;
		assertEquals(newElement , binarySearhTree.addElement(newElement).getValue());
	}

	@Test
	public void testInOrderTraversal() {
		List<Integer> inOrderTraversalResult = binarySearhTree.traverseInOrder();
		assertTrue("List is not sorted", isListSorted(inOrderTraversalResult));
	}
	
	@Test
	public void testGetDepth(){
		assertEquals(5, binarySearhTreeDepthFive.getDepth());
	}
	
	@Test
	public void testBinaryTreePrinter(){
		printTreeToConsole(binarySearhTreeDepthFive);
		printTreeToConsole(binarySearhTree);
	}
	
	@Test
	public void testGetWidthForLevel(){
		assertEquals(2, binarySearhTreeDepthFive.getWidthForLevel(2));
		assertEquals(4, binarySearhTreeDepthFive.getWidthForLevel(3));
		assertEquals(3, binarySearhTreeDepthFive.getWidthForLevel(4));
		assertEquals(1, binarySearhTreeDepthFive.getWidthForLevel(5));
		assertEquals(0, binarySearhTreeDepthFive.getWidthForLevel(10));
	}
	
	@Test
	public void testGetMaxWidthUsingPreOrderTraversal(){
		assertEquals(4, binarySearhTreeDepthFive.getMaxWidthUsingPreOrderTraversal());
		binarySearhTreeDepthFive.addElement(100);
		binarySearhTreeDepthFive.addElement(445);
		binarySearhTreeDepthFive.addElement(550);
		binarySearhTreeDepthFive.addElement(650);
//		printTreeToConsole(binarySearhTreeDepthFive);
		assertEquals(7, binarySearhTreeDepthFive.getMaxWidthUsingPreOrderTraversal());
	}
	
	@Test
	public void testGetMaxWidthWithoutUsingRecursiveArray() {
		assertEquals(4, binarySearhTreeDepthFive.getMaxWidthUsingGetWidthMethod());
		binarySearhTreeDepthFive.addElement(100);
		binarySearhTreeDepthFive.addElement(445);
		binarySearhTreeDepthFive.addElement(550);
		binarySearhTreeDepthFive.addElement(650);
		assertEquals(7, binarySearhTreeDepthFive.getMaxWidthUsingGetWidthMethod());
	}
	
	private <T extends Comparable<? super T>> boolean isListSorted(Iterable<T> iterable) {
		Iterator<T> iter = iterable.iterator();
		if (!iter.hasNext()) {
			return true;
		}
		T t = iter.next();
		while (iter.hasNext()) {
			T t2 = iter.next();
			if (t.compareTo(t2) > 0) {
				return false;
			}
			t = t2;
		}
		return true;
	}
	
	private <T extends Comparable<T>> void printTreeToConsole(BinarySearchTree<T> tree) {
		BinaryTreePrinter printer = new BinaryTreePrinter();
		printer.printLevelOrder(tree);
		System.out.println("*********************************************************************************************************");
	}
}
