package ua.goit.alg;

public class RotatedArray {

    public static int searchInRotatedArray(int array[], int length, int value) {
	int left = 0;
	int right = length - 1;

	while (left < right) {
	    int middle = left + right / 2;
	    if (array[middle] == value) 
		return middle;
	    if (array[left] <= array[middle]) {
		if (array[left] <= value && value < array[middle])
		    right = middle - 1;
		else
		    left = middle + 1;
	    }
	    else {
		if (array[middle] < value && value <= array[right])
		    left = middle + 1;
		else 
		    right = middle - 1;
	    }
	}
	return -1;
    }
}