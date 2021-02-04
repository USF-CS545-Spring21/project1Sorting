
import org.junit.Assert;
import org.junit.Test;
import sorting.Elem;
import sorting.SortingImplementation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

/** Test file for Project 2, sorting.
 * Provides only the most basic testing.
 * You are responsible for thoroughly testing your code on your own. */
public class Project4Test {
    public final static int NUM_ITERS = 10; // how many times to test it
    public final static int SIZE = 32; // number of elements in the list
    public final static String INPUT_FILE = "inputFile";
    public final static String SORTED_FILE = "sortedFile";


    @Test
    public void testInsertionSort() {
        SortingImplementation listSorter = new SortingImplementation();
        boolean isSorted = testComparisonSortMethod(listSorter, "insertion");
        if (!isSorted)
            Assert.fail();
    }

    @Test
    public void testShakerSort() {
        SortingImplementation listSorter = new SortingImplementation();
        boolean isSorted = testComparisonSortMethod(listSorter, "shaker");
        if (!isSorted)
            Assert.fail();
    }


    @Test
    public void testRandomizedQuicksort(){
        SortingImplementation listSorter = new SortingImplementation();
        boolean isSorted = testComparisonSortMethod(listSorter, "randomizedQuick");
        if (!isSorted)
            Assert.fail();
    }

    @Test
    public void testHybrid() {
        SortingImplementation listSorter = new SortingImplementation();
        boolean isSorted = testComparisonSortMethod(listSorter, "hybrid");
        if (!isSorted)
            Assert.fail();
    }


    @Test
    public void testBucketSort() {
        int n = 15;
        SortingImplementation listSorter = new SortingImplementation();
        Elem[] arr = new Elem[n];
        Random r = new Random();
        for (int i = 0; i < NUM_ITERS; i++) {
            int lowindex = r.nextInt(arr.length / 2);
            int highindex = arr.length / 2 + r.nextInt(arr.length / 2);
            for (int j = 0; j < n; j++) {
                int key = r.nextInt(40);
                int data = r.nextInt(1000);
                arr[j] = new Elem(key, new Integer(data));
            }
            listSorter.bucketSort(arr, lowindex, highindex, false);

            if (!areElemsSorted(arr, lowindex, highindex)) {
                System.out.println("In Bucket Sort Test: Not sorted correctly");
                for (Elem rr : arr) {
                    System.out.print(rr.key() + " ");
                }
                System.out.println();
                System.out.println("Testing for range: low = " + lowindex + " high = " + highindex);

                Assert.fail("Bucket sort test failed.");
            }

        }
    }

    @Test
    public void testRadixSort() {
        int[] arr1 = {145, 892, 246, 863, 978, 267, 203, 278, 192, 177, 526, 636, 258, 466, 329, 316};
        SortingImplementation listSorter = new SortingImplementation();
        Random r = new Random();
        int lowindex = r.nextInt(arr1.length / 2);
        int highindex = arr1.length / 2 + r.nextInt(arr1.length / 2);
        listSorter.radixSort(arr1, lowindex, highindex, false);
        if (!areIntegersSorted(arr1, lowindex, highindex)) {
            System.out.println("------- Not sorted correctly---------");
            System.out.println("Before radix sort: arr1 = " + Arrays.toString(arr1));
            System.out.println("After radix sort, for range: low = " +  lowindex + " high = "
                    + highindex + ", arr1 = " + Arrays.toString(arr1));
            Assert.fail();
        }
    }

    @Test
    public void testSortAndFindWinner() {
        String[] votes1 = { "A", "B", "A", "C", "A", "A", "A", "B", "C", "A", "B"};
        SortingImplementation listSorter = new SortingImplementation();
        String res = listSorter.sortAndFindWinner(votes1);
        Assert.assertEquals("The winner for the array votes1  was supposed to be \"A\".", "A", res);

        String[] votes2 = { "C", "B", "B", "A", "B", "C", "C", "A", "B", "A", "B", "C", "A", "A", "B", "C", "A", "B"};
        String res2 = listSorter.sortAndFindWinner(votes2);
        Assert.assertEquals("The winner for the array votes2  was supposed to be \"B\".", "B", res);
    }

    @Test
    public void testExternalSort() {
        SortingImplementation listSorter = new SortingImplementation();
        try {
            Files.deleteIfExists(Paths.get(INPUT_FILE));
            Files.deleteIfExists(Paths.get(SORTED_FILE));
            for (int i = 0; i < 100; i++) {
                Files.deleteIfExists(Paths.get("temp" + i + ".txt"));
            }

        } catch (IOException e1) {
            System.out.println("inputFile did not exist, no need to remove");
        }
        generateLargeFile(INPUT_FILE, 1000000); // total number of values is n = 1000000

        // Important note: in the externalSort method, we are NOT passing n, the total number of values,
        // we are passing k (how many elements can fit into memory at once)
        // and m = the number of chunks of size k in the file (the last chunk may contain less than k elements).
        listSorter.externalSort(INPUT_FILE, SORTED_FILE, 10000, 100);
        try (BufferedReader br = new BufferedReader(new FileReader(SORTED_FILE))) {
            String line;
            int num = 0;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                int nextNum = Integer.parseInt(line);
                lineNum++;
                if ((nextNum < num) || (nextNum == Integer.MAX_VALUE)) {
                    Assert.fail("External file is not sorted properly. See line # " + lineNum);
                }
                num = nextNum;
            }
            // Delete temp files
            for (int i = 0; i < 100; i++) {
                Files.deleteIfExists(Paths.get("temp" + i + ".txt"));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Could not open file: " + e);
            Assert.fail();
        } catch (IOException e) {
            System.out.println("Could not read from file: " + e);
            Assert.fail();
        }
    }

    /** Helper method for bucket sort.
     * Checks if the subarray (from startIndex to endIndex)
     * of records is sorted by key in ascending order.
     * @param arr array of records (of type Elem)
     * @param startIndex the starting index of a subarray
     * @param endIndex the end index of a subarray
     * @return true if sorted
     */
    private static boolean areElemsSorted(Elem[] arr, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (!(arr[i].key() <= arr[i + 1].key())) {
                return false;
            }
        }
        return true;
    }

    private static boolean areIntegersSorted(int[] arr, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method
     * @param listSorter reference to SortingAlgorithms
     * @param methodName the name of the method
     * @return whether the test was successful
     */
    public static boolean testComparisonSortMethod(SortingImplementation listSorter, String methodName) {
        Comparable[] arr1 = new Comparable[SIZE];
        Comparable[] arr2 = new Comparable[SIZE];
        Comparable[] arr1Copy = new Comparable[SIZE];
        Comparable[] arr2Copy = new Comparable[SIZE];
        Random r = new Random();
        for (int i = 0; i < NUM_ITERS; i++) {
            for (int j = 0; j < SIZE; j++) {
                arr1[j] = r.nextInt(40);
                arr1Copy[j] = arr1[j];
                arr2[j] = r.nextInt(40);
                arr2Copy[j] = arr2[j];

            }

            // Randomly generate low and high
            // Not trying to explore the full range of lowindex/highindex, just
            // some range
            int lowindex = r.nextInt(arr2.length / 2);
            int highindex = arr2.length / 2 + r.nextInt(arr2.length / 2);
            switch (methodName) {
                case "insertion":
                    listSorter.insertionSort(arr1, 0, arr1.length - 1, false);
                    listSorter.insertionSort(arr2, lowindex, highindex, false);
                    break;

                case "shaker":
                    listSorter.shakerSort(arr1, 0, arr1.length - 1, false);
                    listSorter.shakerSort(arr2, lowindex, highindex, false);
                    ;
                    break;

                case "hybrid":
                    listSorter.hybridSort(arr1, 0, arr1.length - 1);
                    listSorter.hybridSort(arr2, lowindex, highindex);
                    break;

                case "randomizedQuick":
                    listSorter.randomizedQuickSort(arr1, 0, arr1.length - 1);
                    listSorter.randomizedQuickSort(arr2, lowindex, highindex);
                    break;

            }

            if (!isSorted(arr1, 0, arr1.length - 1)) {
                System.out.println("------- Not sorted correctly---------");
                System.out.println("Before " + methodName + " sort: arr1 = " + Arrays.toString(arr1Copy));
                System.out.println("After " + methodName + " sort, for range: low =  0, high = " + (arr1.length - 1)
                        + ", arr1 =" + Arrays.toString(arr1));

                return false;
            }

            if (!isSorted(arr2, lowindex, highindex)) {
                System.out.println("------- Not sorted correctly---------");
                System.out.println("Before " + methodName + " sort: arr2 = " + Arrays.toString(arr2Copy));
                System.out.println("After " + methodName + " sort, for range: low = " + +lowindex + " high = "
                        + highindex + ", arr2 = " + Arrays.toString(arr2));

                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the part of the array from startIndex to endIndex (inclusive)
     * is sorted
     */
    public static boolean isSorted(Comparable[] arr, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            System.out.println("startIndex < endIndex");
            return false;
        }
        if (startIndex == endIndex - 1)
            return true;

        if ((arr[startIndex].compareTo(arr[startIndex + 1]) <= 0)) {
            return isSorted(arr, startIndex + 1, endIndex);
        } else
            return false;

    }


    /** Used for testing external sort. Generates a large file of ints. */
    public static void generateLargeFile(String filename, int n) {
        Random rand = new Random();
        try (PrintWriter pw = new PrintWriter(filename)) {
            for (int i = 0; i < n; i++)
                pw.println(rand.nextInt(1000) + rand.nextInt(100));
        } catch (IOException e) {
            System.out.println("Error writing to a file " + filename);
        }

    }
}