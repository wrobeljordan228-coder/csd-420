import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * =========================================================================================
 * PERFORMANCE ANALYSIS & DISCUSSION OF RESULTS:
 * =========================================================================================
 * 
 * 1. LinkedList Internal Structure:
 *    A Java LinkedList is implemented as a doubly-linked list. Each element resides in a Node 
 *    object containing pointers to its previous and next neighbors. It does NOT provide 
 *    contiguous memory storage or direct index-based random access.
 * 
 * 2. Iterator Traversal - O(n) Time Complexity:
 *    - An Iterator keeps an internal pointer/reference to the current Node. Calling next() 
 *      simply advances the pointer to currentNode.next, which is an O(1) constant-time step.
 *    - Traversing all n elements requires n consecutive pointer steps.
 *    - Overall Time Complexity: O(n) linear time.
 *    - Performance: Extremely fast for both 50,000 (< 5 ms) and 500,000 (< 25 ms).
 * 
 * 3. get(index) Traversal - O(n^2) Time Complexity:
 *    - LinkedList does not have array offsets. To reach index i, get(i) must start from the 
 *      head (or tail) of the list and walk step-by-step through i nodes.
 *    - Calling get(i) inside a loop from 0 to n - 1 means:
 *        get(0)   traverses 0 nodes
 *        get(1)   traverses 1 node
 *        ...
 *        get(n-1) traverses roughly n/2 nodes (due to bidirectional head/tail optimization)
 *    - Total pointer hops = Sum_{i=0}^{n-1} min(i, n - 1 - i) ~= (n^2) / 4 hops.
 *    - Overall Time Complexity: O(n^2) quadratic time.
 * 
 * 4. Comparison Between 50,000 and 500,000 Elements:
 *    - For 50,000 elements:
 *        * Iterator: Completes in mere milliseconds (~1-5 ms).
 *        * get(index): Requires ~625 million pointer hops, typically taking 1 to 3 seconds.
 *    - For 500,000 elements:
 *        * Iterator: Scales linearly by 10x, finishing in ~15-30 ms.
 *        * get(index): The input size n increases by a factor of 10, meaning operations scale 
 *          quadratically by roughly 10^2 = 100x. Total pointer hops swell to ~62.5 billion, 
 *          typically taking 1.5 to 3 minutes or more to execute.
 * 
 * CONCLUSION:
 * Never use get(index) in a loop to sequentially traverse a LinkedList. Always use an 
 * Iterator, an enhanced for-loop (for-each), or switch to an ArrayList if random index 
 * access is necessary.
 * =========================================================================================
 */
public class LinkedListTest {

    public static void main(String[] args) {
        // Run automated functional tests first
        System.out.println("--- Running Verification Tests ---");
        runTests();
        System.out.println("All verification tests passed successfully.\n");

        System.out.println("=========================================================");
        System.out.println("       LINKEDLIST TRAVERSAL BENCHMARK (ms)");
        System.out.println("=========================================================");

        // Test with 50,000 elements
        benchmark(50_000);

        System.out.println("---------------------------------------------------------");

        // Test with 500,000 elements
        benchmark(500_000);

        System.out.println("=========================================================");
    }

    /**
     * Executes the benchmark for a specified list size.
     */
    public static void benchmark(int size) {
        System.out.printf("Initializing LinkedList with %,d integers...%n", size);
        LinkedList<Integer> list = createList(size);

        // 1. Measure Iterator Traversal
        long startTime = System.currentTimeMillis();
        long sumIterator = traverseWithIterator(list);
        long iteratorTime = System.currentTimeMillis() - startTime;

        System.out.printf("  [Iterator]   Time: %d ms (Checksum: %d)%n", iteratorTime, sumIterator);

        // 2. Measure get(index) Traversal
        System.out.println("  [get(index)] Running traversal (this will take longer)...");
        startTime = System.currentTimeMillis();
        long sumGet = traverseWithGetIndex(list);
        long getTime = System.currentTimeMillis() - startTime;

        System.out.printf("  [get(index)] Time: %d ms (Checksum: %d)%n", getTime, sumGet);

        // Discrepancy ratio
        if (iteratorTime > 0) {
            double ratio = (double) getTime / iteratorTime;
            System.out.printf("  -> get(index) was roughly %.1fx slower than Iterator.%n", ratio);
        } else {
            System.out.println("  -> Iterator executed in < 1 ms.");
        }
    }

    /**
     * Populates a LinkedList with sequential integers.
     */
    public static LinkedList<Integer> createList(int size) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * Traverses the list sequentially using an explicit Iterator.
     */
    public static long traverseWithIterator(List<Integer> list) {
        long sum = 0;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            sum += iterator.next();
        }
        return sum;
    }

    /**
     * Traverses the list by querying each node by index.
     */
    public static long traverseWithGetIndex(List<Integer> list) {
        long sum = 0;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sum += list.get(i);
        }
        return sum;
    }

    /**
     * Verification test suite to ensure the creation and traversal methods work accurately.
     */
    public static void runTests() {
        // Test 1: Verify correct list generation
        int testSize = 100;
        LinkedList<Integer> testList = createList(testSize);
        if (testList.size() != testSize) {
            throw new AssertionError("Test 1 Failed: List size does not match expected " + testSize);
        }
        if (testList.getFirst() != 0 || testList.getLast() != testSize - 1) {
            throw new AssertionError("Test 1 Failed: List elements not initialized properly.");
        }
        System.out.println("  [PASS] Test 1: List generation is correct.");

        // Test 2: Verify Iterator traversal calculates the exact arithmetic sum
        // Sum formula: n * (n - 1) / 2
        long expectedSum = (long) testSize * (testSize - 1) / 2;
        long iteratorSum = traverseWithIterator(testList);
        if (iteratorSum != expectedSum) {
            throw new AssertionError("Test 2 Failed: Iterator sum (" + iteratorSum 
                    + ") did not match expected sum (" + expectedSum + ")");
        }
        System.out.println("  [PASS] Test 2: Iterator traversal outputs the correct sum.");

        // Test 3: Verify get(index) traversal matches Iterator output
        long getSum = traverseWithGetIndex(testList);
        if (getSum != expectedSum) {
            throw new AssertionError("Test 3 Failed: get(index) sum (" + getSum 
                    + ") did not match expected sum (" + expectedSum + ")");
        }
        System.out.println("  [PASS] Test 3: get(index) traversal outputs the correct sum.");

        // Test 4: Verify edge cases (empty list)
        LinkedList<Integer> emptyList = new LinkedList<>();
        if (traverseWithIterator(emptyList) != 0 || traverseWithGetIndex(emptyList) != 0) {
            throw new AssertionError("Test 4 Failed: Non-zero sum returned on an empty list.");
        }
        System.out.println("  [PASS] Test 4: Empty list edge cases handled correctly.");
    }
}