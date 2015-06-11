package ua.goit.alg;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAnagrams {

  @Test
  public void twoWords() {
	String input = "hello world!";
	String expected = "olleh !dlrow";
	String actual = Anagrams.doAnagram(input);
	assertEquals(expected, actual);
  }
  
  @Test
  public void threeWords() {
	String input = "My name is:";
	String expected = "yM eman :si";
	String actual = Anagrams.doAnagram(input);
	assertEquals(expected, actual);
  }
}