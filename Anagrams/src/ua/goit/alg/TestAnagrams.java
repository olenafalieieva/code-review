package ua.goit.alg;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

public class TestAnagrams {

	@Test
	public void twoWords() {
		//Copy this to console:hello world!
		String expected = " olleh !dlrow";
		assertOutput(expected);
	}

	@Test
	public void threeWords() {
		//Copy this to console:My name is:
		String expected = " yM  eman :si";
		assertOutput(expected);
	}

	private void assertOutput(String expected) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		Anagrams.Anagram(System.in);
		System.out.flush();
		System.setOut(old);
		String actual = baos.toString();
		assertEquals(expected, actual);
	}
}