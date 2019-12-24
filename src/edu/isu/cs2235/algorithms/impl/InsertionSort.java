package edu.isu.cs2235.algorithms.impl;

import edu.isu.cs2235.algorithms.ArraySort;

import java.util.Arrays;

public class InsertionSort implements ArraySort {
    public <E extends Comparable> void sort(E[] array){
        if(array == null)
            throw new IllegalArgumentException();
        for(int i = 1; i < array.length; i++){
            insert(array, i, array[i]);
        }
    }

    private <E extends Comparable> void insert(E[] array, int pos, E value) {
        int i = pos - 1;
        while(i >= 0 && array[i].compareTo(value) > 0){
            array[i+1] = array[i];
            i--;
        }
        array[i + 1] = value;
    }
}

