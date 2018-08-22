package dataStructures;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import dataStructureUtils.BinaryTreePrinter;
import junit.framework.TestCase;

public class BinarySearchTree2ndAttemptTest extends TestCase {

	
	BST<Integer> binarySearhTree;
	BST<Integer> binarySearhTreeDepthFive;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		binarySearhTreeDepthFive = new BinarySearchTree2ndAttempt<Integer>();
		binarySearhTreeDepthFive.addElements(Arrays.asList(new Integer[] {500,333,444,123,764,888,333,1000,378,600,200}));
		binarySearhTree = new BinarySearchTree2ndAttempt<Integer>();
		Random random = new Random();
		for(int i = 0 ; i< 50 ; i++){
			Integer element = (int)(random.nextFloat() * 1000);
			binarySearhTree.addElement(element);
		}
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
		binarySearhTreeDepthFive.addElements(Arrays.asList(new Integer[] {100,445,550,650}));
		assertEquals(7, binarySearhTreeDepthFive.getMaxWidthUsingPreOrderTraversal());
	}
	
	@Test
	public void testGetMaxWidthWithoutUsingRecursiveArray() {
		assertEquals(4, binarySearhTreeDepthFive.getMaxWidthUsingGetWidthMethod());
		binarySearhTreeDepthFive.addElements(Arrays.asList(new Integer[] {100,445,550,650}));
		assertEquals(7, binarySearhTreeDepthFive.getMaxWidthUsingGetWidthMethod());
	}
	
	
	@Test
	public void testContainsElement() {
		assertTrue("API not able to detect element 200", binarySearhTreeDepthFive.contains(200));
		assertTrue("API not able to detect element 600", binarySearhTreeDepthFive.contains(600));
		assertTrue("API not able to detect element 500", binarySearhTreeDepthFive.contains(500));
		assertFalse("API detecting element which is not present", binarySearhTreeDepthFive.contains(2000));
	}
	
	@Test
	public void testSearchElement() {
		assertEquals(Integer.valueOf(764), binarySearhTreeDepthFive.searchElement(764).getValue());
		assertEquals(Integer.valueOf(600), binarySearhTreeDepthFive.searchElement(600).getValue());
		assertEquals(Integer.valueOf(500), binarySearhTreeDepthFive.searchElement(500).getValue());
		assertNull("API detecting a node which is not present", binarySearhTreeDepthFive.searchElement(6666));
	}
	
	@Test
	public void testDeleteElement() {
		Integer[] elementsToBeAdded = {550,650,625,675,570};
		binarySearhTreeDepthFive.addElements(Arrays.asList(elementsToBeAdded));
		List<Integer> inOrderTraversalList = binarySearhTreeDepthFive.traverseInOrder();
		int[] elementsToBeDeleted = {1000,123,764,600,500,888};
		removeElementsFromTreeAndList(binarySearhTreeDepthFive , inOrderTraversalList , elementsToBeDeleted);
		for(int deletedElement : elementsToBeDeleted)
			assertFalse("API unable to delete node "+ deletedElement, binarySearhTreeDepthFive.contains(deletedElement));
		assertTrue("Elements deleted incorrectly.Tree corrupted", inOrderTraversalList.equals(binarySearhTreeDepthFive.traverseInOrder()));
	}
	
	private void removeElementsFromTreeAndList(BST<Integer> binaryTree, List<Integer> inOrderTraversalList, int[] elementsToBeDeleted) {
		for(int elementToBeDeleted : elementsToBeDeleted){
			binaryTree.deleteElement(elementToBeDeleted);
			inOrderTraversalList.remove(Integer.valueOf(elementToBeDeleted));
		}
	}
	
	
	@Test
	public void testRemoveDuplicates() {
		binarySearhTreeDepthFive.addElements(Arrays.asList(new Integer[] {500,333,500,444,200,200,500}));
//		printTreeToConsole(binarySearhTreeDepthFive);
		binarySearhTreeDepthFive.removeDuplicates();
//		printTreeToConsole(binarySearhTreeDepthFive);
		assertTrue("Tree still contains some duplicates", !doesSortedListContainDuplicates(binarySearhTreeDepthFive.traverseInOrder()));
	}
	
	@Test
	public void testFindClosestCommonAncestor() {
		assertEquals(Integer.valueOf(764),binarySearhTreeDepthFive.findClosestCommonAncestor(600,888));
		assertEquals(Integer.valueOf(333),binarySearhTreeDepthFive.findClosestCommonAncestor(123,378));
		assertEquals(Integer.valueOf(333),binarySearhTreeDepthFive.findClosestCommonAncestor(333,378));
		assertEquals(Integer.valueOf(764),binarySearhTreeDepthFive.findClosestCommonAncestor(600,1000));
		assertEquals(null,binarySearhTreeDepthFive.findClosestCommonAncestor(90000,9000000));

	}
	

	private <T extends Comparable<? super T>> boolean doesSortedListContainDuplicates(List<T> sortedList) {
		Iterator<T> iter = sortedList.iterator();
		if(!iter.hasNext())
			return false;
		T t = iter.next();
		while(iter.hasNext()){
			T t2 = iter.next();
			if (t2.compareTo(t)==0)
				return true;
			t = t2;
		}
		return false;
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
//	
	private <T extends Comparable<T>> void printTreeToConsole(BST<T> tree) {
		BinaryTreePrinter printer = new BinaryTreePrinter();
		printer.printLevelOrder(tree);
		System.out.println("*********************************************************************************************************");
	}


}
