/*
 * Author: Reed A. Bunnell
 * Date: November 09, 2025
 * Course: CSD-421 Module 3
 * Assignment: ArrayList Duplicate Remover
 */

import java.util.ArrayList;
import java.util.Random;

public class Bunnell_Module3_Assignment {
    
    // Generic static method that removes duplicates and returns a new ArrayList
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> newList = new ArrayList<>();
        for (E element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> originalList = new ArrayList<>();
        Random random = new Random();

        // Fill the list with 50 random integers between 1 and 20
        for (int i = 0; i < 50; i++) {
            originalList.add(random.nextInt(20) + 1);
        }

        // Print the original list
        System.out.println("Original ArrayList (with duplicates):");
        System.out.println(originalList);

        // Create a new list without duplicates
        ArrayList<Integer> noDuplicates = removeDuplicates(originalList);

        // Print the new list
        System.out.println("\nNew ArrayList (duplicates removed):");
        System.out.println(noDuplicates);
    }
}
