package oy.tol.tra;

import java.util.Arrays;

public class Algorithms {
    public static <T extends Comparable<T>> int linearSearch(T value, T[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public static <T extends Comparable<T>> int binarySearch(T value, T[] array) {
        return binarySearch(value, array, 0, array.length - 1);
    }

    public static <T extends Comparable<T>> int binarySearch(T value, T[] array, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            return -1;
        }

        int mid = (fromIndex + toIndex) / 2;
        int compareResult = value.compareTo(array[mid]);

        if (compareResult == 0) {
            return mid;
        } else if (compareResult < 0) {
            return binarySearch(value, array, fromIndex, mid - 1);
        } else {
            return binarySearch(value, array, mid + 1, toIndex);
        }
    }

    public static <E extends Comparable<E>> void sort(E[] array) {
        Arrays.sort(array);
    }

    public static <E extends Comparable<E>> void fastSort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static <E extends Comparable<E>> void quickSort(E[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);
            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    private static <E extends Comparable<E>> int partition(E[] array, int begin, int end) {
        E pivot = array[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, end);
        return i + 1;
    }

    private static <E> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}