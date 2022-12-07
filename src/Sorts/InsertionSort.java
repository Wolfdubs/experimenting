package Sorts;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] myArray = {5,1,8,4,0,8,3,2,8,9,6,1,4,2,0,4,1,8,5,4,7,9,3,2,3,8,8,5,0,6};
        insertionSort(myArray);
        Arrays.stream(myArray).forEach(System.out::print);
    }

    public static int[] insertionSort(int[] arr){    //O(n^2)
        for (int i=1; i< arr.length; i++){    //start at i=1 to start with 2nd element of array, aka 1st element of unsorted array, as 1st element of whole array is sorted with itself already
            int currentTemp = arr[i];
            //walk back through the array from the current temp position
            int j = i - 1;     //sets j as the index just before i
            while (j >= 0 && arr[j] > currentTemp) {    //means loop will run as long as the element before our current is greater, and will stop once j gets to 0th index. so while runs until get to 0th element, or find value that is less than/equal to current value.
                arr[j+1] = arr[j];                       //shift the jth element rightwards
                j--;
            }
            //once while loop exits, it means currentTemp is at correct index, so must insert it there
            arr[j+1] = currentTemp;
        }
        return arr;
    }

}
