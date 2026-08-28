import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class AssignmentTwoThree {
    public static void main(String[] args) {
        String fileName = "JordanRobeldatafile.dat";
        Random random = new Random();

        // 1. Declare and populate an array of five random integers (1 to 100)
        int[] intArray = new int[5];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = random.nextInt(100) + 1;
        }

        // 2. Declare and populate an array of five random double values (0.0 to 100.0)
        double[] doubleArray = new double[5];
        for (int i = 0; i < doubleArray.length; i++) {
            doubleArray[i] = Math.round((random.nextDouble() * 100.0) * 100.0) / 100.0;
        }

        // Display current generated data
        System.out.println("Generated Integers: " + Arrays.toString(intArray));
        System.out.println("Generated Doubles:  " + Arrays.toString(doubleArray));

        // 3. Write data to the binary file (append mode set to true)
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(fileName, true)))) {

            // Write all 5 integers
            for (int val : intArray) {
                out.writeInt(val);
            }

            // Write all 5 doubles
            for (double val : doubleArray) {
                out.writeDouble(val);
            }

            System.out.println("Data successfully written/appended to " + fileName);

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}