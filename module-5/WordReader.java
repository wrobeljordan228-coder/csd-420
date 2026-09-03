import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Word Order Processor
 * Read words from a local text file, filter duplicates,
 * and display unique words in ascending and descending alphabetical order.
 */
public class WordReader {

    private static final String FILE_NAME = "collection_of_words.txt";

    public static void main(String[] args) {
        // Step 1: Run automated setup/test code to generate the required file if missing
        ensureTestFileExists(FILE_NAME);

        // Step 2: Read words into a TreeSet (to handle uniqueness and ascending sorting)
        TreeSet<String> ascendingWords = readUniqueWordsFromFile(FILE_NAME);

        if (ascendingWords.isEmpty()) {
            System.out.println("No words found in " + FILE_NAME);
            return;
        }

        // Step 3: Display non-duplicate words in Ascending Order
        System.out.println("==================================================");
        System.out.println("  NON-DUPLICATE WORDS IN ASCENDING ORDER");
        System.out.println("==================================================");
        for (String word : ascendingWords) {
            System.out.println(word);
        }

        // Step 4: Display non-duplicate words in Descending Order
        // TreeSet.descendingSet() returns a reverse-order view
        Set<String> descendingWords = ascendingWords.descendingSet();

        System.out.println("\n==================================================");
        System.out.println("  NON-DUPLICATE WORDS IN DESCENDING ORDER");
        System.out.println("==================================================");
        for (String word : descendingWords) {
            System.out.println(word);
        }

        // Step 5: Execute verification tests
        runSelfTests(ascendingWords);
    }

    /**
     * Reads words from a text file, strips punctuation, normalizes case,
     * and returns a TreeSet containing only unique sorted words.
     */
    public static TreeSet<String> readUniqueWordsFromFile(String filename) {
        TreeSet<String> uniqueWords = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String token = scanner.next();
                
                // Clean punctuation around words (e.g., "words," -> "words")
                String cleaned = token.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

                if (!cleaned.isEmpty()) {
                    uniqueWords.add(cleaned);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + e.getMessage());
        }

        return uniqueWords;
    }

    /**
     * Test code helper: Generate collection_of_words.txt if it does not already exist.
     */
    private static void ensureTestFileExists(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(file)) {
                // Sample text containing repeated words and varying cases
                writer.println("Java python apple banana cherry apple orange banana");
                writer.println("JAVA treeSet hashSet orange python algorithm Data");
                writer.println("generics interface collection data structures Java");
                System.out.println("[Test Setup] Created sample test file: " + filename);
            } catch (FileNotFoundException e) {
                System.err.println("Unable to create test file: " + e.getMessage());
            }
        }
    }

    /**
     * Automated validation tests to verify sorting and uniqueness.
     */
    private static void runSelfTests(TreeSet<String> words) {
        System.out.println("\n--- Running Automated Verification Tests ---");

        // Test 1: Uniqueness check
        boolean uniqueCheckPassed = true;
        String prev = null;
        for (String w : words) {
            if (prev != null && prev.equalsIgnoreCase(w)) {
                uniqueCheckPassed = false;
                break;
            }
            prev = w;
        }
        System.out.println("Test 1 (Zero Duplicates): " + (uniqueCheckPassed ? "PASSED" : "FAILED"));

        // Test 2: Ascending Order validation
        boolean ascendingCheckPassed = true;
        prev = null;
        for (String w : words) {
            if (prev != null && prev.compareToIgnoreCase(w) > 0) {
                ascendingCheckPassed = false;
                break;
            }
            prev = w;
        }
        System.out.println("Test 2 (Ascending Alphabetical Order): " + (ascendingCheckPassed ? "PASSED" : "FAILED"));

        // Test 3: Descending Order validation
        boolean descendingCheckPassed = true;
        prev = null;
        for (String w : words.descendingSet()) {
            if (prev != null && prev.compareToIgnoreCase(w) < 0) {
                descendingCheckPassed = false;
                break;
            }
            prev = w;
        }
        System.out.println("Test 3 (Descending Alphabetical Order): " + (descendingCheckPassed ? "PASSED" : "FAILED"));
    }
}