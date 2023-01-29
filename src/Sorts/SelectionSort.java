package Sorts;

import java.util.Arrays;

import static Sorts.QuickSort.generateRandomArray;
import static Sorts.QuickSort.swap;

//search for lowest element and assign to correct position; swapping current index with next lowest number
//2 subarrays; left is already sorted
                //right is unsorted
//every iteration has 1 element move from unsorted to the sorted
    //find min element in right array and move the end of left array
//O(n^2), O(1)
/*PROCESS
    treat collection as 2 collections; left half being sorted, right half being unsorted.
    scan elements left-to-right, starting from index 0 of whole collection, find the smallest one
    minElementIndex variable finds the index of the minimum unsorted element
    Swaps with the index of the start of the unsorted right collection
    each pass requires 1 less comparison; comparison count - unsorted array length - 1

 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] myArr = generateRandomArray();
        System.out.println("Array before sorting");
        Arrays.stream(myArr).forEach(i -> System.out.print(i + ", "));

        selectionSortIntArray(myArr);

        System.out.println("\nArray after sorting");
        Arrays.stream(myArr).forEach(i -> System.out.print(i + ", "));

    }

    private static void selectionSortIntArray(int[] myArr) {
        for (int unsortedStart = 0; unsortedStart<myArr.length; unsortedStart++){
            int minIndex = unsortedStart;
            for (int currentIndex = unsortedStart+1; currentIndex<myArr.length; currentIndex++){
                if (myArr[minIndex] > myArr[currentIndex]) {
                    minIndex = currentIndex;    //retrieving the index of the lowest value
                }
            }
            int nextSmallest = myArr[minIndex];
            myArr[minIndex] = myArr[unsortedStart];
            myArr[unsortedStart] = nextSmallest;
        }
    }

    //selection sort to operate on any class the implements Comparable, so uses its compareTo
    private static void selectionSortObjectArray(Comparable[] array){
        int minIndex;
        Comparable nextSmallest;
        for (int unsortedStart = 0; unsortedStart < array.length-1; unsortedStart++){
            minIndex = unsortedStart;
            for (int currentIndex = unsortedStart+1; currentIndex < array.length-1; currentIndex++) {
                if (array[currentIndex].compareTo(array[minIndex]) < 0 ) {
                    minIndex = currentIndex;
                }
            }
            nextSmallest = array[minIndex];
            array[minIndex] = array[unsortedStart];
            array[unsortedStart] = nextSmallest;
        }
    }





}
