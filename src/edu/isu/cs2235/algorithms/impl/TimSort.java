package edu.isu.cs2235.algorithms.impl;

import edu.isu.cs2235.algorithms.ArraySort;

import java.util.Arrays;

public class TimSort implements ArraySort {

    /** Constructs an exact copy of the input array E The type parameter of the data in the array
     * @param otherArray The array to copy
     * @return A copy of the provided array
     */
    public <E extends Comparable> E[] copyArray (E[] otherArray ) {
        return Arrays.copyOf(otherArray, otherArray.length);
    }

    public <E extends Comparable> void sort(E[] array){
        if(array == null)
            throw new IllegalArgumentException();
        E[] copy = copyArray(array);
        timSort(copy, array, 0, array.length - 1);
    }

    private <E extends Comparable> void timSort(E[] array, E[] result, int start, int end){
        if(end == start){
            return;
        }
        if((end-start) < 10){
            InsertionSort insertionSort = new InsertionSort();
            insertionSort.sort(result);
            return;
        }
        int mid = (end - start)/2 + start;
        timSort(result, array, start, mid);
        timSort(result, array, mid + 1, end);

        int i = start;
        int j = mid + 1;
        for (int k = start; k <= end ; k++){
            if(j > end || (i <= mid && array[i].compareTo(array[j]) < 0)){
                result[k] = array[i];
                i++;
            } else {
                result[k] = array[j];
                j++;
            }
        }
    }
}
