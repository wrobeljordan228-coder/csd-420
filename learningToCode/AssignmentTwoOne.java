import java.util.Arrays;
import java.util.Random;

public class AssignmentTwoOne {
    public static void main(String[] args) {
        Random random = new Random();

        // 1. Declare and populate an array of five random integers (1 to 100)
        int[] intArray = new int[5];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = random.nextInt(100) + 1;
        }

        // 2. Declare and populate an array of five random double values (0.0 to 100.0)
        double[] doubleArray = new double[5];
        for (int i = 0; i < doubleArray.length; i++) {
            // Generates a random double and rounds to 2 decimal places
            doubleArray[i] = Math.round((random.nextDouble() * 100.0) * 100.0) / 100.0;
        }

        // 3. Display the arrays
        System.out.println("Stored Integers: " + Arrays.toString(intArray));
        System.out.println("Stored Doubles:  " + Arrays.toString(doubleArray));
    }
}