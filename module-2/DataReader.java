/**
 * Jordan Wrobel
 * CSD-420
 * Module 2.2 
 * Write a second program that reads the file and displays the data.
 */

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataReader {
    public static void main(String[] args) {
        // Match the filename used in module2_2.java
        String fileName = "JordanWrobeldatafile.dat";

        try (DataInputStream in = new DataInputStream(
                new BufferedInputStream(new FileInputStream(fileName)))) {

            int runCount = 1;

            System.out.println("--- Reading from " + fileName + " ---");

            // Loop continuously
            while (true) {
                int[] intArray = new int[5];
                for (int i = 0; i < 5; i++) {
                    intArray[i] = in.readInt();
                }

                double[] doubleArray = new double[5];
                for (int i = 0; i < 5; i++) {
                    doubleArray[i] = in.readDouble();
                }

                // Display the current record set
                System.out.println("\n[Record Set #" + runCount + "]");
                System.out.print("Integers: ");
                for (int val : intArray) {
                    System.out.print(val + " ");
                }
                System.out.print("\nDoubles:  ");
                for (double val : doubleArray) {
                    System.out.print(val + " ");
                }
                System.out.println();

                runCount++;
            }

        } catch (EOFException e) {
            System.out.println("\n--- Reached End of File ---");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName + ". Run module2_2 first.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}