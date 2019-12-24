package edu.isu.cs2235.algorithms.impl;

import edu.isu.cs2235.algorithms.ArraySort;

public class HybridSort implements ArraySort {
    public <E extends Comparable> void sort(E[] array){
        if(array == null)
            throw new IllegalArgumentException();
        hybridsort(array, 0, array.length - 1);
    }

    private <E extends Comparable> void hybridsort(E[] array, int left, int right){
        if((right - left) < 10 || (left - right) < 10){
            InsertionSort insertionSort = new InsertionSort();
            insertionSort.sort(array);
            return;
        } else if(left < right){
            int pi = partition(array, left, right);
            hybridsort(array, left, pi - 1);
            hybridsort(array, pi + 1, right);
        }
    }

    private <E extends Comparable> int partition(E[] array, int left, int right){
        int pivotIndex = selectPivot(array, left, right);
        E pivot = array[pivotIndex];
        int store = left;
        //swap(array, store, pivotIndex);
        for(int i = (left + 1); i <= right; i++){
            if(array[i].compareTo(pivot) < 0){
                store++;
                swap(array, i, store);
            }
        }
        swap(array, store, pivotIndex);
        return store;
    }

    private <E extends Comparable> int selectPivot(E[] array, int left, int right){
        return left;
    }

    private <E extends Comparable> void swap(E[] array, int first, int second){
        E temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
}
