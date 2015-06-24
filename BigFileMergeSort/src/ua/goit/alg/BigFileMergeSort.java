package ua.goit.alg;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BigFileMergeSort {
    static final String property = "java.io.tmpdir";
    static String tempDir = System.getProperty(property);

    public static File mergeSort(File file, int blocksize) throws IOException {
        ArrayList<File> partList = splitSort(file, blocksize);
        File result = File.createTempFile("result", ".txt", new File(tempDir));
        result.deleteOnExit(); 
        result = mergeParts(partList).get(0);
        return result;
    }

    private static ArrayList<File> mergeParts(ArrayList<File> partList) throws IOException {
        ArrayList<File> result;
        if (partList.size() <= 1) {
            result = partList;
        } else {
            if (partList.size() % 2 == 1) {
                File merged = mergeFiles(partList.get(0), partList.get(1));
                partList.add(merged);
                partList.remove(1);
                partList.remove(0);
            }
            ArrayList<File> tempList = new ArrayList<File>();
            while (partList.size() > 1) {
                for (int i = 0; i < partList.size()-1; i+=2) {
                    tempList.add(mergeFiles(partList.get(i), partList.get(i + 1)));
                }
                partList = tempList;
                tempList = new ArrayList<File>();
            }
        } 

        return partList;
    }

    private static ArrayList<File> splitSort(File file, int blocksize) throws IOException {
        int tmpint = 0;
        int countInt = 0;
        Scanner scInt = new Scanner(file);
        while (scInt.hasNextInt()) {
            tmpint = scInt.nextInt();
            countInt++;
        }
        scInt.close();
        
        Scanner sc = new Scanner(file);
        ArrayList<File> filelist = new ArrayList<>();
        long blocksQ = countInt / blocksize;
        int rest = (int) (countInt % blocksize);
        int blocksCount = 0;

        while (sc.hasNextInt()) { 
            if (blocksCount < blocksQ) { 
                int[] array = new int[blocksize]; 
                for (int i = 0; i < blocksize; i++) {
                    tmpint = sc.nextInt();
                    array[i] = tmpint; 
                }
                blocksCount++;

                Arrays.sort(array);
                filelist.add(writeToTempFile(array));
            }
            else if ((blocksCount == blocksQ) && (rest != 0)) {
                int[] endArray  = new int[rest]; 
                for (int i = 0; i < rest; i++) {
                    tmpint = sc.nextInt();
                    endArray[i] = tmpint; 
                }
                Arrays.sort(endArray);
                filelist.add(writeToTempFile(endArray));
            } 
        } 
        sc.close();
        return filelist; 
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
        RandomAccessFile source1 = new RandomAccessFile(firstFile, "r");
        RandomAccessFile source2 = new RandomAccessFile(secondFile, "r");
        RandomAccessFile result = new RandomAccessFile(file, "rw");
        firstInt = readIntFromFile(source1, firstPos);
        secondInt = readIntFromFile(source2, secondPos);
        long endFirst = firstFile.length() - 4;
        long endSecond = secondFile.length() - 4;

        while ((firstPos <= endFirst) && (secondPos <= endSecond)) {
            if ((firstInt <= secondInt) && (firstPos <= endFirst)) {
                writeIntToFile(result, firstInt, writePos);
                writePos = moveOnNextPos(writePos);
                firstPos = moveOnNextPos(firstPos);
                if (firstPos <= endFirst) {
                    firstInt = readIntFromFile(source1, firstPos);
                } else {
                    while (secondPos <= endSecond) {
                        writeIntToFile(result, readIntFromFile(source2, secondPos),  writePos);
                        secondPos = moveOnNextPos(secondPos); 
                        writePos = moveOnNextPos(writePos);
                    }
                }
            } else if ((firstInt > secondInt) && (secondPos <= endSecond)) {
                writeIntToFile(result, secondInt, writePos);
                secondPos = moveOnNextPos(secondPos); 
                writePos = moveOnNextPos(writePos);
                if (secondPos <= endSecond) {
                    secondInt = readIntFromFile(source2, secondPos);
                } else {
                    while (firstPos <= endFirst) {
                        writeIntToFile(result, readIntFromFile(source1, firstPos),  writePos);
                        firstPos = moveOnNextPos(firstPos); 
                        writePos = moveOnNextPos(writePos);
                    }
                }
            }
        } 
        source1.close();
        source2.close();
        result.close();
        return file;
    }

    private static long moveOnNextPos(long pos) {
        return pos+4;
    }
}