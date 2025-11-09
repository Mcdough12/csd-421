import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedListTimingTest {

    /**
     * This program:
     * 1. Stores 50,000 and 500,000 integers in a LinkedList.
     * 2. Measures the time to traverse the list using:
     *      - an iterator
     *      - the get(index) method
     * 3. Prints timings for both sizes and both approaches.
     * 4. Includes simple test checks to ensure the traversal logic is correct.
     *
     * In comments at the bottom, there is a discussion of the expected results.
     */
    public static void main(String[] args) {
        int[] sizes = {50_000, 500_000};

        for (int n : sizes) {
            System.out.println("======================================");
            System.out.println("Testing with n = " + n + " elements");
            System.out.println("======================================");

            List<Integer> list = new LinkedList<>();
            fillList(list, n);

            // Simple sanity test: size should be n
            if (list.size() != n) {
                throw new IllegalStateException("List size test failed: expected " + n + " but got " + list.size());
            }

            // Traverse with iterator
            long startIterator = System.nanoTime();
            long iteratorSum = traverseWithIterator(list);
            long endIterator = System.nanoTime();
            long iteratorTime = endIterator - startIterator;

            // Traverse with get(index)
            long startGet = System.nanoTime();
            long getSum = traverseWithGet(list);
            long endGet = System.nanoTime();
            long getTime = endGet - startGet;

            // Test that both traversals produced the same result
            if (iteratorSum != getSum) {
                throw new IllegalStateException("Traversal test failed: iteratorSum != getSum");
            }

            // Optional: test against the expected arithmetic sum: 0 + 1 + ... + (n - 1)
            long expectedSum = arithmeticSeriesSum(n - 1);
            if (iteratorSum != expectedSum) {
                throw new IllegalStateException(
                        "Sum test failed for n = " + n +
                        " expected " + expectedSum +
                        " but got " + iteratorSum
                );
            }

            System.out.println("Iterator traversal sum: " + iteratorSum);
            System.out.println("get(index) traversal sum: " + getSum);
            System.out.println("Iterator time:   " + iteratorTime + " ns");
            System.out.println("get(index) time: " + getTime + " ns");
            System.out.println();
        }

        System.out.println("All tests passed successfully.");
    }

    /**
     * Fill the list with integers 0, 1, 2, ..., n-1.
     */
    private static void fillList(List<Integer> list, int n) {
        list.clear();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
    }

    /**
     * Traverse the list using a ListIterator and return the sum of all elements.
     */
    private static long traverseWithIterator(List<Integer> list) {
        long sum = 0;
        ListIterator<Integer> it = list.listIterator();
        while (it.hasNext()) {
            sum += it.next();
        }
        return sum;
    }

    /**
     * Traverse the list using get(index) and return the sum of all elements.
     */
    private static long traverseWithGet(List<Integer> list) {
        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum;
    }

    /**
     * Returns 0 + 1 + 2 + ... + n as a long.
     */
    private static long arithmeticSeriesSum(int n) {
        // sum = n * (n + 1) / 2
        return (long) n * (n + 1L) / 2L;
    }
}

/*
Discussion / Explanation (for your comments section):

When we traverse a LinkedList using an iterator, we are walking through the list node by node in O(n) time.
The iterator always knows where it is and can move to the next element in constant time per step.

When we traverse a LinkedList using get(index), each call to get(i) has to walk from the beginning (or middle)
of the list to the i-th element. This is O(n) per get, so doing this for all indices from 0 to n - 1 results
in O(n^2) time overall.

Because of this difference in time complexity:
- For 50,000 elements, the iterator approach should already be noticeably faster than get(index).
- For 500,000 elements, the gap becomes much larger. The iterator still runs in O(n), while get(index)
  becomes very slow because it repeatedly walks the list for each index.

In actual runs on your machine, you should see:
- Iterator time grows roughly linearly with n.
- get(index) time grows much faster than linear (closer to n^2), so the 500,000 test will be dramatically slower.
*/
