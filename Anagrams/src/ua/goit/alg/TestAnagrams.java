package ua.goit.alg;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import org.junit.Test;

public class TestAnagrams {

    @Test
    public void twoWords() throws UnsupportedEncodingException {
        String input = "hello world!";
        String expected = " olleh !dlrow";
        String actual = outputToString(input);
        assertEquals(expected, actual);
    }

    @Test
    public void threeWords() throws UnsupportedEncodingException {
        String expected = " yM  eman :si";
        String input = "My name is:";
        String actual = outputToString(input);
        assertEquals(expected, actual);
    }

    private String outputToString(String input) throws UnsupportedEncodingException {
        InputStream bais = new ByteArrayInputStream(input.getBytes("UTF-8"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        Anagrams.anagram(bais);
        System.out.flush();
        System.setOut(old);
        String result = baos.toString();
        
        return result;
    }
}