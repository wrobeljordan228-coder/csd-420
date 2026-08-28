/**
 * Module 3.2
 * Jordan Wrobel
 * Write a test program that contains a static method that returns a new ArrayList:
 * The new ArrayList returned will contain all original values with no duplicates from the original ArrayList.
 * Fill the Original ArrrayList with 50 random values from 1 to 20.
 * public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list)
 */

import java.util.ArrayList;
import java.util.Random;

public class NoDuplicates {

    public static void main(String[] args) {
        ArrayList<Integer> originalList = new ArrayList<>();
        Random random = new Random();

        // 1. Fill the original list with 50 random values from 1 to 20.
        for (int i = 0; i < 50; i++) {
            originalList.add(random.nextInt(20) + 1);
        }

        // 2. Display original list.
        System.out.println("Original ArrayList (Size: " + originalList.size() + "):");
        System.out.println(originalList);

        // 3. Remove the duplicates using the generic static method.
        ArrayList<Integer> noDuplicatesList = removeDuplicates(originalList);

        // 4. Display the distinct list.
        System.out.println("\nArrayList with Duplicates Removed (Size: " + noDuplicatesList.size() + "):");
        System.out.println(noDuplicatesList);
    }

    /**
     * Generic static method that returns a new ArrayList containing
     * all the elements from the original list without duplicates.
     */
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> result = new ArrayList<>();

        for (E element : list) {
            if (!result.contains(element)) {
                result.add(element);
            }
        }

        return result;
    }
}