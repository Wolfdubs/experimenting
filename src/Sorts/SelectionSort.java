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
public class SelectionSort {
    public static void main(String[] args) {
        int[] myArr = generateRandomArray();
        System.out.println("Array before sorting");
        Arrays.stream(myArr).forEach(i -> System.out.print(i + ", "));

        selectionSort(myArr);

        System.out.println("\nArray after sorting");
        Arrays.stream(myArr).forEach(i -> System.out.print(i + ", "));

    }

    private static void selectionSort(int[] myArr) {
        for (int i = 0; i<myArr.length; i++){
            int minIndex = i;
            for (int j = i+1; j<myArr.length; j++){
                if (myArr[minIndex] > myArr[j]) {
                    minIndex = j;    //retrieving the lowest index
                }
            }
            int temp = myArr[minIndex];
            myArr[minIndex] = myArr[i];
            myArr[i] = temp;
        }
    }




}
