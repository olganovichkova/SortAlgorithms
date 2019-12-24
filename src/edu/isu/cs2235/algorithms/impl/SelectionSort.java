package edu.isu.cs2235.algorithms.impl;

import edu.isu.cs2235.algorithms.ArraySort;

public class SelectionSort implements ArraySort {

    public <E extends Comparable> void sort(E[] array) {
        if (array == null)
            throw new IllegalArgumentException();
        int max = array.length - 1;
        E nextValue = array[max];

        for (int i = max - 1; i >= 0; i--) {
            if (array[i].compareTo(nextValue) > 0) {
                nextValue = array[i];
            }
        }
        while (max > 0 && array[max].compareTo(nextValue) == 0) {
            max = max - 1;
        }
        while (max > 0) {
            E value = nextValue;
            nextValue = array[max];
            for (int i = max - 1; i >= 0; i--) {
                if (array[i].compareTo(value) == 0) {
                    swap(array, i, max);
                    max--;
                } else if (array[i].compareTo(nextValue) > 0) {
                    nextValue = array[i];
                }
            }
            while (max > 0 && array[max].compareTo(nextValue) == 0) {
                max--;
            }
        }
    }

    private <E extends Comparable> void swap(E[] array, int first, int second) {
        E temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
}
