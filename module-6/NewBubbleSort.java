    /**
     * CSD-420 Module 6.2
     * Use generic methods and a bubble sort to sort elements
     * using the Comparable interface and the Comparator interface.
     * Then write test code to ensure it functions correctly.
     */

import java.util.Arrays;
import java.util.Comparator;

public class NewBubbleSort {

    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        boolean needNextPass = true;
        for (int k = 1; k < list.length && needNextPass; k++) {
            needNextPass = false;
            for (int i = 0; i < list.length - k; i++) {
                if (list[i].compareTo(list[i + 1]) > 0) {
    // swap list[i] with list[i + 1]
                    E temp = list[i];
                    list[i] = list[i +1];
                    list[i + 1] = temp;
                    needNextPass = true;
                }
            }
        }
    }
    /**
     * The Bubble sort using an explicit Comparator.
     */
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        boolean needNextPass = true;
        for (int k = 1; k < list.length && needNextPass; k++) {
            needNextPass = false;
            for (int i = 0; i < list.length - k; i++) {
                if (comparator.compare(list[i], list[i + 1]) > 0) {
                    // Swap list[i] with list[i + 1]
                    E temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    needNextPass = true;
                }
            }
        }
    }
    public static void main(String[] args) {
        // test the bubble sort with Integers
        Integer[] intArray = {25, -24, 51, 0, 16, 9, 4};
        System.out.println("Original Integer Array:   " + Arrays.toString(intArray));
        bubbleSort(intArray);
        System.out.println("Sorted (Comparable):   " + Arrays.toString(intArray));
        System.out.println();

        // test the comparable bubble sort with Strings
        String[] strArray = {"Blueberry", "Peach", "Apple", "Strawberry", "Mango"};
        System.out.println("Original String Array:   " + Arrays.toString(strArray));
        bubbleSort(strArray);
        System.out.println("Sorted (Comparable):   " + Arrays.toString(strArray));
        System.out.println();

        // test the Comparator bubble sort in descending order
        Double[] doubleArray = {2.11, 4.64, 1.46, 7.53, 0.44};
        System.out.println("Original Double Array:  " + Arrays.toString(doubleArray));
        bubbleSort(doubleArray, (d1, d2) -> Double.compare(d2, d1));
        System.out.println("Sorted (Comparator - Descending):   " + Arrays.toString(doubleArray));
        System.out.println();

        // test the Comparator bubble sort with custom logic (String length)
        String[] words = {"dog", "cat", "hyenna", "bluebird", "squirrel"};
        System.out.println("Original words Array:   " + Arrays.toString(words));
        bubbleSort(words, Comparator.comparingInt(String::length));
        System.out.println("Sorted (Comparator - by length):   " + Arrays.toString(words));
    }
}