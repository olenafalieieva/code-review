package ua.goit.alg;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Arrays {
	static final String property = "java.io.tmpdir";
	static String tempDir = System.getProperty(property);

	public static File mergeSort(File file, int blocksize) throws IOException {
		ArrayList<File> partList = splitSort(file, blocksize);
		File result = File.createTempFile("result", ".txt", new File(tempDir));
		result = mergeParts(partList).get(0);
		return result;
	}

	private static ArrayList<File> mergeParts(ArrayList<File> partList) throws IOException {
		ArrayList<File> result = null;	
		if (partList.size() <= 1) {
			result = partList;
		} else {
			result =  mergePartsProcess(partList);
		} 
		return result;
	}

	private static ArrayList<File> mergePartsProcess(ArrayList<File> partList)
			throws IOException {
		ArrayList<File> resultList = new ArrayList<File>();
		if (partList.size() % 2 == 1) {
			File merged = mergeFiles(partList.get(0), partList.get(1));
			partList.add(merged);
			partList.remove(1);
			partList.remove(0);
		}
		for (int i = 0; i < partList.size()-1; i+=2) {
			resultList.add(mergeFiles(partList.get(i), partList.get(i+1)));
		}
		resultList = mergeParts(resultList);
		return resultList;
	}

	private static ArrayList<File> splitSort(File file, int blocksize) throws IOException {
		int counterOfElements = 0;
		int tmpint = 0;
		Scanner sc = new Scanner(file);
		ArrayList<File> filelist = new ArrayList<File>();
		while (sc.hasNextInt()) { 
			int[] array = new int[blocksize]; 
			int i = 0;
			while ((i < blocksize) && sc.hasNextInt()){
				tmpint = sc.nextInt();
				array[i] = tmpint;
				counterOfElements++;
				i++;
			}
			array=sortArray(array);
			filelist.add(writeToTempFile(array));
		} 
		sc.close();
		removeExtraZeros(blocksize, counterOfElements, filelist);
		return filelist; 
	}

	private static void removeExtraZeros(int blocksize, int counterOfElements,
			ArrayList<File> filelist) throws FileNotFoundException, IOException {

		if (counterOfElements % blocksize != 0) {
			int modulo_start = (blocksize - counterOfElements % blocksize) * 4; 
			int modulo_end = (blocksize) * 4 - 4;
			int[] tmparray = new int[counterOfElements % blocksize];
			int fileNumber = filelist.size() - 1;
			File fileWhithOverZeros = filelist.get(fileNumber);
			RandomAccessFile run1 = new RandomAccessFile(fileWhithOverZeros, "rw");
			int tmpint;
			int j = 0;
			while (modulo_start <= modulo_end)  {
				try {
					tmpint = readIntFromFile(run1, modulo_end);
					tmparray[j] = tmpint;
					j++;
					modulo_end -= 4;
				} finally {
					run1.close();
				}
			}
			filelist.remove(fileNumber);
			filelist.add(writeToTempFile(tmparray));
		}
	}  

	private static File writeToTempFile(int[] array) throws IOException {
		DataOutputStream fdo = null;
		File newtmpfile = File.createTempFile("text", ".txt", new File(tempDir));
		newtmpfile.deleteOnExit(); 
		try {
			fdo = new DataOutputStream(new FileOutputStream(newtmpfile));
			for(int i = 0; i < array.length; i++) {
				fdo.writeInt(array[i]);
			}
		} finally {
			fdo.close();
		}
		return newtmpfile;
	}

	private static int readIntFromFile(RandomAccessFile run, long pointer) throws IOException {
		run.seek(pointer);
		int i = run.readInt();
		return i;
	}

	private static void writeIntToFile(RandomAccessFile run, int i, long pointer) throws IOException {
		run.seek(pointer);
		run.writeInt(i);
	}

	private static File mergeFiles(File firstFile, File secondFile) throws IOException {
		int firstInt; 
		int secondInt; 
		long firstPos = 0;
		long secondPos = 0; 
		long writePos = 0; 

		File file = File.createTempFile("TMPFile", ".txt", new File(tempDir));
		file.deleteOnExit(); 
		RandomAccessFile run1 = new RandomAccessFile(firstFile, "rw");
		RandomAccessFile run2 = new RandomAccessFile(secondFile, "rw");
		RandomAccessFile run3 = new RandomAccessFile(file, "rw");
		firstInt = readIntFromFile(run1, firstPos);
		secondInt = readIntFromFile(run2, secondPos);
		long endFirst = firstFile.length() - 4;
		long endSecond = secondFile.length() - 4;

		while ((firstPos <= endFirst) && (secondPos <= endSecond)) {
			if ((firstInt <= secondInt) && (firstPos <= endFirst)) {
				writeIntToFile(run3, firstInt, writePos);
				writePos = byteInc(writePos);
				firstPos = byteInc(firstPos);
				if (firstPos <= endFirst) {
					firstInt = readIntFromFile(run1, firstPos);
				} else {
					while (secondPos <= endSecond) {
						writeIntToFile(run3, readIntFromFile(run2, secondPos),  writePos);
						secondPos = byteInc(secondPos); 
						writePos = byteInc(writePos);
					}
				}
			} else if ((firstInt > secondInt) && (secondPos <= endSecond)) {
				writeIntToFile(run3, secondInt, writePos);
				secondPos = byteInc(secondPos); 
				writePos = byteInc(writePos);
				if (secondPos <= endSecond) {
					secondInt = readIntFromFile(run2, secondPos);
				} else {
					while (firstPos <= endFirst) {
						writeIntToFile(run3, readIntFromFile(run1, firstPos),  writePos);
						firstPos = byteInc(firstPos); 
						writePos = byteInc(writePos);
					}
				}
			}

		} 
		run1.close();
		run2.close();
		run3.close();
		return file;
	}

	private static long byteInc(long pos) {
		return pos+4;
	}

	private static int[] sortArray(int[] array) {
		for(int i = array.length - 1 ; i > 0 ; i--){
			for(int j = 0; j < i ; j++){
				if( array[j] > array[j + 1] ){
					int tmp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = tmp;
				}
			}
		}
		return array;
	}
}