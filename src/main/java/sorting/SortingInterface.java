package sorting;

/** An interface that describes several algorithms for sorting a list */
public interface SortingInterface {
    void insertionSort(Comparable[] array, int lowindex, int highindex,
                              boolean reversed);
    void shakerSort(Comparable[] array, int lowindex, int highindex, boolean reversed);


    void randomizedQuickSort(Comparable[] array, int lowindex, int highindex);

    void hybridSort(Comparable[] array, int lowindex, int highindex);

    void bucketSort(Elem[] array, int lowindex, int highindex, boolean reversed);

    void radixSort(int[] array, int lowindex, int highindex, boolean reversed);

    void externalSort(String inputFile, String outputFile, int k, int m);

    String sortAndFindWinner (String[] votes);

}
