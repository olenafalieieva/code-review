package ua.goit.alg;

import org.junit.Assert;
import org.junit.Test;

public class TestRotatedArray{

    @Test
    public void shouldReturnSearchResult() {
	int Array[] = {5, 6, 7, 8, 1, 2, 3, 4};
	int actualIndex = RotatedArray.searchInRotatedArray(Array, 8, 8);
	int expectedIndex = 3;
	Assert.assertEquals(expectedIndex, actualIndex);
    }
    
    @Test
    public void shouldNotReturnSearchResult() {
	int Array[] = {5, 6, 7, 8, 1, 2, 3, 4};
	int actualIndex = RotatedArray.searchInRotatedArray(Array, 8, 9);
	int expectedIndex = -1;
	Assert.assertEquals(expectedIndex, actualIndex);
    }
    
    @Test
    public void shouldReturnResultForEmtyArr() {
	int Array[] = {};
	int actualIndex = RotatedArray.searchInRotatedArray(Array, 0, 0);
	int expectedIndex = -1;
	Assert.assertEquals(expectedIndex, actualIndex);
    }
    
    @Test
    public void shouldReturnResultForOneElementArr() {
	int Array[] = {100500};
	int actualIndex = RotatedArray.searchInRotatedArray(Array, 1, 100500);
	int expectedIndex = 0;
	Assert.assertEquals(expectedIndex, actualIndex);
    }
    
       @Test
    public void shouldReturnResultForTwoElementArr() {
	int Array[] = {100500, 8};
	int actualIndex = RotatedArray.searchInRotatedArray(Array, 2, 100500);
	int expectedIndex = 0;
	Assert.assertEquals(expectedIndex, actualIndex);
    }
}