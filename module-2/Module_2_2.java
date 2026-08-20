/**
 * Jordan Wrobel
 * CSD-420
 * Module 2.2 
 * Write a program that stores sets of random integers and doubles in a file titled [yourname] datafile.dat.
 * If there is no file, the file is created, if there is a file, the data is appended.
 */

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Module_2_2 {
    public static void main(String[] args) {
        // Create file with my name in it.
        String fileName = "JordanWrobeldatafile.dat";
        Random rand = new Random();

        // 1. Create an array with 5 random integers
        int[] intArray = new int[5];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = rand.nextInt(100) + 1; // 1 to 100
        }

        // 2. Create an array with 5 random double values
        double[] doubleArray = new double[5];
        for (int i = 0; i < doubleArray.length; i++) {
            // Rounds to 2 decimal places for clean data
            doubleArray[i] = Math.round((rand.nextDouble() * 100.0) * 100.0) / 100.0;
        }

        // Display the data
        System.out.println("Generated Integers: " + Arrays.toString(intArray));
        System.out.println("Generated Doubles:  " + Arrays.toString(doubleArray));

        // 3. Write data to a binary file (true parameter enables appending)
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(fileName, true)))) {

            // Write the integers
            for (int val : intArray) {
                out.writeInt(val);
            }

            // Write the doubles
            for (double val : doubleArray) {
                out.writeDouble(val);
            }

            System.out.println("Successfully written and appended to " + fileName);

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}