package Sorts;

public class Sort {

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

    public static int[] mergeSort(int[] arr){
        return arr;
    }

    public static int[] bubbleSort(int[] arr){
        return arr;
    }



}
