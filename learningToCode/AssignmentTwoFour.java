import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AssignmentTwoFour {
    public static void main(String[] args) {
        // Must match the exact file name created in your writer program
        String fileName = "JordanRobeldatafile.dat";

        try (DataInputStream in = new DataInputStream(
                new BufferedInputStream(new FileInputStream(fileName)))) {

            int recordSet = 1;
            System.out.println("Reading binary data from: " + fileName);

            // Continuously read records until EOFException is thrown at the end of the file
            while (true) {
                // 1. Read the 5 integers
                int[] intArray = new int[5];
                for (int i = 0; i < 5; i++) {
                    intArray[i] = in.readInt();
                }

                // 2. Read the 5 doubles
                double[] doubleArray = new double[5];
                for (int i = 0; i < 5; i++) {
                    doubleArray[i] = in.readDouble();
                }

                // 3. Display the record set
                System.out.println("\n[Record Set #" + recordSet + "]");
                System.out.print("Integers: ");
                for (int val : intArray) {
                    System.out.print(val + " ");
                }

                System.out.print("\nDoubles:  ");
                for (double val : doubleArray) {
                    System.out.print(val + " ");
                }
                System.out.println();

                recordSet++;
            }

        } catch (EOFException e) {
            // Normal termination: reached the end of the binary file
            System.out.println("\n--- Reached End of File ---");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName + ". Run the writer program first.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}