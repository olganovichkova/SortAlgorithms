package edu.isu.cs2235;

import edu.isu.cs2235.algorithms.impl.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class Driver {
    public static final int MIN_ARRAY_ITEM_VALUE = 1;
    public static final int MAX_ARRAY_ITEM_VALUE = 100000;
    public static final int MIN_ARRAY_SIZE = 10000;
    public static final int INCREMENT_ARRAY_SIZE_BY = 10000;
    public static final int INCREMENT_COUNT = 10;
    public static final int ALGORITHM_RUN_COUNT = 49;

    public static long[] merSortTimes = new long[INCREMENT_COUNT];
    public static long[] insSortTimes = new long[INCREMENT_COUNT];
    public static long[] selSortTimes = new long[INCREMENT_COUNT];
    public static long[] qckSortTimes = new long[INCREMENT_COUNT];
    public static long[] timSortTimes = new long[INCREMENT_COUNT];
    public static long[] hybSortTimes = new long[INCREMENT_COUNT];
    public static int[] arraySizes = new int[INCREMENT_COUNT];

    public static Random random = new Random();

    public static int randomInt(){
        return random.nextInt(MAX_ARRAY_ITEM_VALUE);
    }

    public static Integer[] createArray(int size){
        Integer[] array = new Integer[size];
        for(int i = 0; i < size; i++)
            array[i] = randomInt();
        return array;
    }

    public static void printSimulations() {
        System.out.println("N\tInsSort\tSelSort\tMerSort\tQckSort\tTimSort\tHybSort");
        for (int i = 0; i < INCREMENT_COUNT; i++) {
            System.out.println("" +
                    arraySizes[i] + "\t" +
                    insSortTimes[i] + "\t" +
                    selSortTimes[i] + "\t" +
                    merSortTimes[i] + "  \t" +
                    qckSortTimes[i] + "  \t" +
                    timSortTimes[i] + "  \t" +
                    hybSortTimes[i]);
        }
    }

    public static void saveSimulations() {
        String fileName = "report.csv";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write("N,InsSort,SelSort,MerSort,QckSort,TimSort,HybSort\n");
            for(int i = 0 ; i < INCREMENT_COUNT; i++) {
                bw.write("" + arraySizes[i]
                        + "," + insSortTimes[i]
                        + "," + selSortTimes[i]
                        + "," + merSortTimes[i]
                        + "," + qckSortTimes[i]
                        + "," + timSortTimes[i]
                        + "," + hybSortTimes[i]
                        + "\n"
                );
            }
            bw.flush();
            bw.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void performSort() {
        for (int i = 0; i < INCREMENT_COUNT; i++) {
            int size = MIN_ARRAY_SIZE + (i * INCREMENT_ARRAY_SIZE_BY);
            System.out.println("Doing array size = " + size);


            arraySizes[i] = size;
            merSortTimes[i] = 0;
            selSortTimes[i] = 0;
            qckSortTimes[i] = 0;
            insSortTimes[i] = 0;
            timSortTimes[i] = 0;
            hybSortTimes[i] = 0;
            for (int j = 0; j < ALGORITHM_RUN_COUNT; j++) {
                Integer[] array = createArray(size);
                long timeStart;
                long timeFinish;

                timeStart = System.nanoTime();
                MergeSort mergeSort = new MergeSort();
                mergeSort.sort(array);
                timeFinish = System.nanoTime();
                merSortTimes[i] += timeFinish - timeStart;

                array = createArray(size);
                timeStart = System.nanoTime();
                QuickSort quickSort = new QuickSort();
                quickSort.sort(array);
                timeFinish = System.nanoTime();
                qckSortTimes[i] += timeFinish - timeStart;

                array = createArray(size);
                timeStart = System.nanoTime();
                InsertionSort insertionSort = new InsertionSort();
                insertionSort.sort(array);
                timeFinish = System.nanoTime();
                insSortTimes[i] += timeFinish - timeStart;

                array = createArray(size);
                timeStart = System.nanoTime();
                SelectionSort selectionSort = new SelectionSort();
                selectionSort.sort(array);
                timeFinish = System.nanoTime();
                selSortTimes[i] += timeFinish - timeStart;

                array = createArray(size);
                timeStart = System.nanoTime();
                TimSort timSort = new TimSort();
                timSort.sort(array);
                timeFinish = System.nanoTime();
                timSortTimes[i] += timeFinish - timeStart;

                array = createArray(size);
                timeStart = System.nanoTime();
                HybridSort hybridSort = new HybridSort();
                hybridSort.sort(array);
                timeFinish = System.nanoTime();
                hybSortTimes[i] += timeFinish - timeStart;

            }
            merSortTimes[i] /= ALGORITHM_RUN_COUNT;
            qckSortTimes[i]  /= ALGORITHM_RUN_COUNT;
            insSortTimes[i] /= ALGORITHM_RUN_COUNT;
            selSortTimes[i]  /= ALGORITHM_RUN_COUNT;
            timSortTimes[i]  /= ALGORITHM_RUN_COUNT;
            hybSortTimes[i]  /= ALGORITHM_RUN_COUNT;
        }
    }


    public static void main(String[] args){
        performSort();
        printSimulations();
        saveSimulations();
    }
}

