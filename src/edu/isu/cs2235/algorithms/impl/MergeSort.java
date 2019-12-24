package edu.isu.cs2235.algorithms.impl;

import edu.isu.cs2235.algorithms.ArraySort;
import java.util.Arrays;

public class MergeSort implements ArraySort {
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
        mergeSort(copy, array, 0, array.length - 1);
    }

    private <E extends Comparable> void mergeSort(E[] array, E[] result, int start, int end){
        if(end == start){
            return;
        }
        if((end-start) < 2){
            if(result[start].compareTo(result[end]) > 0){
                swap(result, start, end);
            }
            return;
        }
        int mid = (end - start)/2 + start;
        mergeSort(result, array, start, mid);
        mergeSort(result, array, mid + 1, end);

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

    private <E extends Comparable> void swap(E[] array, int start, int end){
        E temp = array[start];
        array[start] = array[end];
        array[end] = temp;
    }
}

