package ua.goit.alg;
import java.util.Scanner;

public class Anagrams {

    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	String input = sc.nextLine(); 
	sc.close();
	doAnagram(input);
    }

    public static String doAnagram(String input) {
	String[] splitInput = input.split("\\s+");
	StringBuilder anagram = new StringBuilder();
	for (int i = 0; i < splitInput.length; i++) {
	    String temp = splitInput[i];
	    StringBuilder anagramWord = new StringBuilder(temp);
	    anagram.append(anagramWord.reverse()); 
	    if (i != splitInput.length-1) {
		anagram.append(" ");
	    }
	}
	String result = anagram.toString();
	System.out.println(result);
	return result;  
    }  
}