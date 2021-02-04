package sorting;

/**  A class that implements SortingInterface. Has various methods
 *   to sort a list of elements. */
public class SortingImplementation  implements SortingInterface {

    /**
     * Sorts the sublist of the given list (from lowindex to highindex)
     * using insertion sort
     * @param array array of Comparable-s
     * @param lowindex the beginning index of a sublist
     * @param highindex the end index of a sublist
     * @param reversed if true, the list should be sorted in descending order
     */
    @Override
    public void insertionSort(Comparable[] array, int lowindex, int highindex, boolean reversed) {
        // FILL ON CODE
    }

    /**
     * Sorts the sublist of the given list (from lowindex to highindex)
     *  using the shaker sort (see pdf for description)
     * @param array array of Comparable-s
     * @param lowindex the beginning index of a sublist
     * @param highindex the end index of a sublist
     * @param reversed if true, the list should be sorted in descending order
     */
    public void shakerSort(Comparable[] array, int lowindex, int highindex, boolean reversed) {

    }


    /**
     * Sorts the sublist of the given list (from lowindex to highindex)
     * using the randomizedQuickSort
     * @param array array to sort
     * @param lowindex the beginning index of a sublist
     * @param highindex the end index of a sublist
     */
    @Override
    public void randomizedQuickSort(Comparable[] array, int lowindex, int highindex) {
        // FILL ON CODE
    }

    /**
     * Sorts a given sublist using hybrid sort that combines insertion sort and randomized quick sort.
     * See pdf for details.
     * @param array array of Comparable-s to sort
     * @param lowindex the beginning index of the sublist
     * @param highindex the end index of the sublist
     */
    @Override
    public void hybridSort(Comparable[] array, int lowindex, int highindex) {
        // FILL ON CODE

    }

    /**
     * Sorts a sub-array of records using bucket sort.
     * @param array array of records
     * @param lowindex the beginning index of the sublist to sort
     * @param highindex the end index of the sublist to sort
     * @param reversed if true, sort in descending (decreasing) order, otherwise ascending
     */
    @Override
    public void bucketSort(Elem[] array, int lowindex, int highindex, boolean reversed) {
        // FILL IN CODE

    }

    /**
     * Sorts a sub-array of integers using a radix sort (you may use any base). You may
     * assume that all elements of the array have the same # of digits.
     * @param array array of records
     * @param lowindex the beginning index of the sublist to sort
     * @param highindex the end index of the sublist to sort
     * @param reversed if true, sort in descending (decreasing) order, otherwise ascending
     */
    @Override
    public void radixSort(int[] array, int lowindex, int highindex, boolean reversed) {
        // FILL IN CODE

    }

    /**
     * Implements external sort method
     * @param inputFile The file that contains the input list
     * @param outputFile The file where to output the sorted list
     * @param k number of elements that fit into memory at once
     * @param m number of chunks
     */
    public void externalSort(String inputFile, String outputFile, int k, int m) {
        // FILL IN CODE

    }

    /**
     * Suppose some city has n people, and these people need to vote to select a mayor of the city. There are three candidates for a mayor: "A",  "B" and "C".  We are given an array of n Strings where each element represents a vote for either candidate "A" or candidate "B", or candidate "C". For the purpose of this problem, let's assume there is a clear winner (so one candidate has more votes than the other two).
     * Design and implement (in Java) an in-place algorithm for sorting this array and determining who wins the election, "A", "B" or "C".
     * Example: if we are given the following array that represents votes of 11 people:
     *             ["A", "B", "A", "C", "A", "A", "A", "B", "C", "A", "B"],
     *     your method should return "A" and change the array so that it is sorted:
     *             ["A", "A", "A", "A", "A", "A", "B", "B", "B",  "C", "C"]
     *  The algorithm must satisfy the following requirements:
        - Use the variation of the partition method of quicksort)
        - Should run in linear time
        - Use no extra memory (except for two integer indices and a tmp variable for swapping).
        - Run in two passes
     * Important: Do NOT just iterate over the array and count the number of "A"s, "B"s and "C"s  - such solutions will get 0 points.
     * Do NOT use counting sort.
     *
     * @param votes input array of votes
     * @return winner
     */
    public String sortAndFindWinner (String[] votes) {
        // FILL IN CODE

        return ""; // replace
    }


    // FILL IN CODE:
    // Research and implement one more sorting method that we did not discuss in class.
    // Do not copy code from the web. Implement the algorithm yourself.
    // Describe it in a Readme file.

}