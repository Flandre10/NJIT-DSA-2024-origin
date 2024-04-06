package oy.tol.tra;

public class Algorithms {
    public static <T extends Comparable<T>> void sort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static <T> void reverse(T[] array) {
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            T temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
   
    
    public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
        while (fromIndex <= toIndex) {
            int mid = (fromIndex + toIndex) / 2;
            int compareResult = aValue.compareTo(fromArray[mid]);
    
            if (compareResult == 0) {
                return mid;
            } else if (compareResult < 0) {
                toIndex = mid - 1;
            } else {
                fromIndex = mid + 1;
            }
        }
    
        return -1; 
    }


    public static <E extends Comparable<E>> void slowSort(E[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].compareTo(array[j]) > 0) {
                    E temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
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
        int i = begin - 1;
    
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