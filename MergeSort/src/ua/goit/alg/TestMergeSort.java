package ua.goit.alg;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestMergeSort {

  @Test
  public void testMergeSort() {
	MergeSort ms = new MergeSort();
	int[] input = {1, 5, 4, 3, 6, 7, 8, 23, 2};
	int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 23};
	int[] actual = ms.mergeSort(input);
	assertArrayEquals(expected, actual);
  }
  
  @Test
  public void testMergeSortForEmtyArray() {
	MergeSort ms = new MergeSort();
	int[] input = {};
	int[] expected = {};
	int[] actual = ms.mergeSort(input);
	assertArrayEquals(expected, actual);
  }
  
  @Test
  public void testMergeSortForSingleElement() {
	MergeSort ms = new MergeSort();
	int[] input = {1};
	int[] expected = {1};
	int[] actual = ms.mergeSort(input);
	assertArrayEquals(expected, actual);
  }
  
  @Test
  public void testMergeSortForTwoElements() {
	MergeSort ms = new MergeSort();
	int[] input = {10, 5};
	int[] expected = {5, 10};
	int[] actual = ms.mergeSort(input);
	assertArrayEquals(expected, actual);
  }
}