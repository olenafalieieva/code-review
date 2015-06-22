package ua.goit.alg;

import org.junit.Assert;
import org.junit.Test;

public class TestRotatedArray{

    @Test
    public void shouldReturnSearchResult() {
	int array[] = {5, 6, 7, 8, 1, 2, 3, 4};
	int actualIndex = RotatedArray.searchInRotatedArray(array, 8, 8);
	int expectedIndex = 3;
	Assert.assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void shouldNotReturnSearchResult() {
	int array[] = {5, 6, 7, 8, 1, 2, 3, 4};
	int actualIndex = RotatedArray.searchInRotatedArray(array, 8, 9);
	int expectedIndex = -1;
	Assert.assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void shouldReturnResultForEmtyArr() {
	int array[] = {};
	int actualIndex = RotatedArray.searchInRotatedArray(array, 0, 0);
	int expectedIndex = -1;
	Assert.assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void shouldReturnResultForOneElementArr() {
	int array[] = {100500};
	int actualIndex = RotatedArray.searchInRotatedArray(array, 1, 100500);
	int expectedIndex = 0;
	Assert.assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void shouldReturnResultForTwoElementArr() {
	int array[] = {100500, 8};
	int actualIndex = RotatedArray.searchInRotatedArray(array, 2, 100500);
	int expectedIndex = 0;
	Assert.assertEquals(expectedIndex, actualIndex);
    }
}