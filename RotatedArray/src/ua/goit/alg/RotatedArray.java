package ua.goit.alg;

public class RotatedArray {

  public static int searchInRotatedArray(int Array[], int N, int key) {
	int Left = 0;
	int Right = N - 1;

	while (Left <= Right) {
	    int Middle = Right >>> 1;
	    if (Array[Middle] == key) return Middle;
	    
	    if (Array[Left] <= Array[Middle]) {
		if (Array[Left] <= key && key < Array[Middle])
		    Right = Middle - 1;
		else
		    Left = Middle + 1;
	    }
	    else {
		if (Array[Middle] < key && key <= Array[Right])
		    Left = Middle + 1;
		else 
		    Right = Middle - 1;
	    }
	}
	return -1;
    }
}
