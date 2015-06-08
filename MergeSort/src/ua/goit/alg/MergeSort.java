package ua.goit.alg;

public class MergeSort {

  public static void main(String a[]){

	int[] array = {1,5,4,3,6,7,8,23,2};
	MergeSort mms = new MergeSort();
	mms.mergeSort(array);
	for(int i:array){
	  System.out.print(i);
	  System.out.print(" ");
	}
  }
  
  public int[] mergeSort(int[] array) {
	int length = array.length;
	if (length == 1 ) {
	  return array;
	}

	int[] first = new int[length/2];
	int[] second = new int[length - first.length];
	System.arraycopy(array, 0, first, 0, first.length);
	System.arraycopy(array, first.length, second, 0, second.length);

	mergeSort(first);
	mergeSort(second);
	merge(first, second, array);

	return array;	
  }

  private void merge(int[] first, int[] second, int[] result) {
	int firstInd = 0;
	int secondInd = 0;
	int resInd = 0;

	while ((firstInd < first.length) && (secondInd < second.length)) {
	  if (first[firstInd] < second[secondInd]) {
		result[resInd] = first[firstInd];
		firstInd++;
	  } else {
		result[resInd] = second[secondInd];
		secondInd++;
	  }
	  resInd++;
	}
	System.arraycopy(first, firstInd, result, resInd, first.length - firstInd);
	System.arraycopy(second, secondInd, result, resInd, second.length - secondInd);
  }	
}