package ua.goit.alg;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Arrays {

    public static File mergeSort(File file, int blocksize) throws IOException {
	ArrayList<File> partList = splitSort(file, blocksize);
	File result = new File("result.txt");
	result = mergeParts(partList).get(0);
	return result;
    }

    public static ArrayList<File> mergeParts(ArrayList<File> partList) throws IOException {
	if (partList.size() ==1) {
	    return partList;
	}
	if (partList.size() > 1) {
	    ArrayList<File> resultList = new ArrayList<File>();
	    if (partList.size() % 2 == 1) {
		File merged = mergeFiles(partList.get(0), partList.get(1));
		partList.add(merged);
		partList.remove(1);
		partList.remove(0);
	    }
	    for (int i = 0; i < partList.size()-1; i++) {
		resultList.add(mergeFiles(partList.get(i), partList.get(i+1)));
		i++;
	    }
	    resultList = mergeParts(resultList);
	    return resultList;
	} else {
	    return partList;
	}
    }

    private static ArrayList<File> splitSort(File file, int blocksize) throws IOException {
	int counterOfElements = 0;
	int tmpint = 0;
	Scanner sc = null;
	ArrayList<File> filelist = new ArrayList<File>();
	sc = new Scanner(file);
	while(sc.hasNextInt()) { 
	    int[] array = new int[blocksize]; 
	    for (int i = 0; i < blocksize; i++) {
		try {
		    tmpint = sc.nextInt();
		} catch (NoSuchElementException e) {
		    break;
		}						
		array[i] = tmpint;
		counterOfElements++;
	    }
	    array=sortArray(array);
	    filelist.add(writeToTempFile(array));
	} 
	sc.close();

	if (counterOfElements%blocksize != 0) {
	    int i = 0;
	    int modulo = (counterOfElements % blocksize) * 4; 
	    int[] tmparray = new int[blocksize - counterOfElements % blocksize];
	    File file1 = (filelist.get((filelist.size()) - 1));
	    while (modulo != blocksize * 4) {
		try {
		    tmpint = readIntFromFile(file1, modulo);
		    tmparray[i] = tmpint;
		    i++;
		    modulo += 4;
		} catch (NoSuchElementException e) {
		    break;
		}	
	    }
	    filelist.remove(filelist.size() - 1);
	    filelist.add(writeToTempFile(tmparray));
	}
	return filelist; 
    }  

    static String property = "java.io.tmpdir";
    static String tempDir = System.getProperty(property);

    private static File writeToTempFile(int[] array) throws IOException {
	DataOutputStream fdo = null;
	File newtmpfile = File.createTempFile( "text", ".txt", new File(tempDir));
	newtmpfile.deleteOnExit(); 
	try {
	    fdo = new DataOutputStream(new FileOutputStream(newtmpfile));
	    for(int i=0; i<array.length; i++) {
		fdo.writeInt(array[i]);
	    }
	} catch (NoSuchElementException e) {
	    e.printStackTrace();
	} finally {
	    fdo.close();
	}
	return newtmpfile;
    }

    public static int readIntFromFile(File file, long pointer) throws IOException {
	RandomAccessFile run = new RandomAccessFile(file, "rw");
	run.seek(pointer);
	int i = run.readInt();
	run.close();
	return i;
    }

    public static void writeIntToFile(File file, int i, long pointer) throws IOException {
	RandomAccessFile run = new RandomAccessFile(file, "rw");
	run.seek(pointer);
	run.writeInt(i);
	run.close();
    }

    public static File mergeFiles(File firstFile, File secondFile) throws IOException {
	int firstInt; 
	int secondInt; 
	long firstPos = 0;
	long secondPos = 0; 
	long writePos = 0; 
	boolean EOF = false;

	File file = File.createTempFile("TMPFile", ".txt", new File(tempDir));
	file.deleteOnExit(); 
	try {
	    firstInt = readIntFromFile(firstFile, firstPos);
	    secondInt = readIntFromFile(secondFile, secondPos);
	    while (!EOF) {
		if (firstInt < secondInt) {
		    writeIntToFile(file, firstInt, writePos);
		    firstPos=byteInc(firstPos); 
		    writePos=byteInc(writePos);
		    firstInt = readIntFromFile(firstFile, firstPos);

		} else if (firstInt > secondInt) {
		    writeIntToFile(file, secondInt, writePos);
		    secondPos=byteInc(secondPos); 
		    writePos=byteInc(writePos);
		    secondInt = readIntFromFile(secondFile, secondPos);

		} else {
		    writeIntToFile(file, secondInt, writePos);
		    secondPos=byteInc(secondPos);
		    writePos=byteInc(writePos);
		    writeIntToFile(file, firstInt, writePos);
		    firstPos=byteInc(firstPos); 
		    writePos=byteInc(writePos);
		    firstInt = readIntFromFile(firstFile, firstPos);
		    secondInt = readIntFromFile(secondFile, secondPos);
		}
	    }
	} catch (EOFException e) {
	    while (secondPos < secondFile.length()) {
		writeIntToFile(file, readIntFromFile(secondFile, secondPos),  writePos);
		secondPos=byteInc(secondPos); 
		writePos=byteInc(writePos);
	    }
	    while (firstPos < firstFile.length()) {
		writeIntToFile(file, readIntFromFile(firstFile, firstPos), writePos);
		firstPos=byteInc(firstPos); 
		writePos=byteInc(writePos);
	    }
	    EOF=true;
	}
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