package ua.goit.alg;

import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.*;

public class TestBigFileMergeSort {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    static String property = "java.io.tmpdir";
    static String tempDir = System.getProperty(property);

    @Test
    public void test() throws IOException {

	File file = folder.newFile("file.txt");
	FileWriter fw1 = new FileWriter(file);
	String cq1 = "9 9 9 1 3 5 7 2 3 5 8 1 8 5 3 1 8 4 5 7";
	fw1.append(cq1);
	fw1.flush();
	fw1.close();
	File fileExpected = folder.newFile("expectedfile.txt");
	FileWriter fw2 = new FileWriter(fileExpected);
	String cq2 = "1 1 1 2 3 3 3 4 5 5 5 5 7 7 8 8 8 9 9 9 ";
	fw2.append(cq2);
	fw2.flush();
	fw2.close();
	
	File resultFile = folder.newFile("fileResult.txt");
	resultFile = Arrays.mergeSort(file, 5);
	StringBuilder string = new StringBuilder();
	DataInputStream fin = new DataInputStream(new FileInputStream(resultFile));
	while (fin.available() > 0) {
	    string.append((fin.readInt()))
	    .append(" ");
	} 
	fin.close();
	string.toString().trim();

	File  fileActual = folder.newFile("fileActual.txt");
	FileWriter fw3 = new FileWriter(fileActual);
	fw3.append(string);
	fw3.flush();
	fw3.close();
	
	String s1 = FileUtils.readFileToString(fileExpected);
	String s2 = FileUtils.readFileToString(fileActual);
	
	assertEquals(s1, s2);
    }
}