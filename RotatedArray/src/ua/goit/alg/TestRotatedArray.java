package ua.goit.alg;

import org.junit.Assert;
import org.junit.Test;

public class TestRotatedArray{

    @Test
    public void issearchInRotatedArrayWorks() {
	int Array[] = {5, 6, 7, 8, 1, 2, 3, 4};
	int actualIndex = RotatedArray.searchInRotatedArray(Array, 8, 8);
	int expectedIndex = 3;
	Assert.assertEquals(expectedIndex, actualIndex);
    }
}