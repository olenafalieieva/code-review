package ua.goit.alg;
import java.io.InputStream;
import java.util.Scanner;

public class Anagrams {

	public static void Anagram(InputStream in) {
		Scanner sc = new Scanner(System.in);
		String input = null;
		int j = 0;
		int i = 0;
		input = sc.nextLine(); 
		while (i < input.length()) {
			if (input.charAt(i) == ' ') {
				j++;
			}
			i++;
		}
		sc.close();
		int wordsQ = j+1;
		j = 0;
		i = 0;
		int countChars = 0;
		int start = 0;
		while (wordsQ > i) {
			while ((j < input.length() - 1) && (input.charAt(j) != ' ')) {
				countChars++;
				j++;
			}
			if ((j == input.length() - 1) && (wordsQ > 1)) {
				while (countChars < j) {
					countChars++;
				}
			}
			for (int k = countChars; k >= start; --k) {
				System.out.print(input.charAt(k));
			}
			countChars = countChars + 1;
			start = countChars;
			i++;
			j++;
			if (wordsQ > i) {
				System.out.print(" ");
			}
		}
	}
}