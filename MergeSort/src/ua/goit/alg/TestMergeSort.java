package ua.goit.alg;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestMergeSort {

  @Test
  public void testMergeSort() {
	MergeSort ms = new MergeSort();
	int[] input = {1,5,4,3,6,7,8,23,2};
	int[] expected = {1,2,3,4,5,6,7,8,23};
	int[] actual = ms.mergeSort(input);
	assertArrayEquals(expected, actual);
  }
}