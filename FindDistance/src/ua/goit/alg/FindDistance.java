package ua.goit.alg;

public class FindDistance {
	
	public static int minDistance(int[] input) {
		int distance;
		if (input.length == 1) {
			distance = 0;
			return distance;
		} else if (input.length == 2) {
			distance = 1;
			return distance;
		}
		
		int min1 = input[0];
		int minIndex1 = 0;
		int min2 = input[0];
		int minIndex2 = 0;
		for (int i = 1; i < input.length; i++) {
			if (min1 > input[i]) {
				min2 = min1;
				minIndex2 = minIndex1;
				min1 = input[i];
				minIndex1 = i;
			} 
		}
		if (min2 == min1) {
			min2 = input[1];
			for (int j = 1; j < input.length; j++) {
				if (min2 > input[j]) {
					min2 = input[j];
					minIndex2 = j;
				}
			}
		}
		distance = Math.abs(minIndex1 - minIndex2);	
		
		return distance;
	}
}