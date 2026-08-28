import java.util.Arrays;
import java.util.Random;

public class AssignmentTwo {
    public static void main(String[] args) {
        // 1. Declare and allocate an array for five integers
        int[] numbers = new int[5];
        Random random = new Random();

        // 2. Populate the array with random integers (range: 1 to 100)
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100) + 1;
        }

        // 3. Display the stored array
        System.out.println("Stored Random Integers: " + Arrays.toString(numbers));
    }
}