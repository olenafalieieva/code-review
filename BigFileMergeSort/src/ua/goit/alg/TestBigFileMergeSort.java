package ua.goit.alg;

import static org.junit.Assert.assertEquals;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TestBigFileMergeSort {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	static String property = "java.io.tmpdir";
	static String tempDir = System.getProperty(property);

	@Test
	public void shouldReturnFileWithSortedIntegers() throws IOException {
		String sequence1 = "9 9 9 1 3 5 7 2 3 5 8 1 8 5 3 1 8 4 5 7";
		File file = doFileOfIntegers("file.txt", sequence1);
		String sequence2 = "1 1 1 2 3 3 3 4 5 5 5 5 7 7 8 8 8 9 9 9 ";
		File fileExpected = doFileOfIntegers("expectedfile.txt", sequence2);

		File resultFile = folder.newFile("fileResult.txt");
		resultFile = BigFileMergeSort.mergeSort(file, 5);
		
		String sequence3 = doStringFromFileData(resultFile);
		File fileActual = doFileOfIntegers("fileActual.txt", sequence3);

		String s1 = FileUtils.readFileToString(fileExpected);
		String s2 = FileUtils.readFileToString(fileActual);

		assertEquals(s1, s2);
	}

	private String doStringFromFileData(File resultFile)
			throws FileNotFoundException, IOException {
		StringBuilder string = new StringBuilder();
		DataInputStream fin = new DataInputStream(new FileInputStream(resultFile));
		while (fin.available() > 0) {
			string.append((fin.readInt()))
			.append(" ");
		} 
		fin.close();
		String result = string.toString();
		return result;
	}

	private File doFileOfIntegers(String fileName, String intSequence) throws IOException {
		File fileExpected = folder.newFile(fileName);
		FileWriter fw = new FileWriter(fileExpected);
		String cq = intSequence;
		fw.append(cq);
		fw.flush();
		fw.close();
		return fileExpected;
	}
}