package Sorts;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/*3 steps;
    1. Chose any number in the array to be the pivot.
            best implementation is to choose a pivot at random
                then just swap that element with the last element, and partition from there
    2. Partitioning: Move all numbers in the array that are less than the pivot, to the left of the pivot, and rightwards for numbers higher
            means pivot will now be at its final correct index in the array
    3. Recursively quicksort the subarrays with the values to left of pivot, and right of pivot
       keep going until get to single element subarrays, which are by definition already sorted
*/
public class QuickSort {   //regarded as the fastest sorting algorithm

    public static void main(String[] args) {
        int[] myArray = generateRandomArray();
        System.out.println("Before quicksort:");
        Arrays.stream(myArray).forEach(i -> System.out.print(i + ","));
        quickSort(myArray);
        System.out.println("\nAfter quicksort:");
        Arrays.stream(myArray).forEach(i -> System.out.print(i + ","));
    }

    public static void quickSort (int[] arr){       //an overloaded wrapper method for easy consumption, that calls the actual implementing method
        quickSort(arr, 0, arr.length-1);   //so user doesnt have to worry about start & end indices
    }

    private static void quickSort(int[] arr, int startIndex, int endIndex){   //takes start & end due to recursion; after partitioning, we recursively quicksort all elements in left, and the right. these ranges specify the subarrays

        if (startIndex>=endIndex) {          //after recursive calls made on the subarrays, must stop recursive calls on subarrays of 1 element and just return
            return;                          // this is the base case for the recursion
        }

        //chose a random number as pivot
        int pivotIndex = new Random().nextInt(endIndex-startIndex) + startIndex; //returns a random index somewhere between the start and end
        int pivot = arr[pivotIndex];   //pivotIndex will change with each recursive call. pivot itself is the actual value at the index

        swap(arr, pivotIndex, endIndex);   //swap the pivotindex with the endindex, so te pivotindex will be moved to the end of the array

        //partition the array
        int leftPointer = partition(arr, startIndex, endIndex, pivot);

        //recursively quicksort the left & right subarrays        // left start is always index 0, end is 1 before where pivot was swapped in
        // right start is 1 after where the pivot has been swapped in, and endindex is always just the last index for the new subarray
        quickSort(arr, startIndex, leftPointer-1);   //as leftpointer is currently pointing to the pivot, and left sub ends 1 before pivot
        quickSort(arr, leftPointer+1, endIndex);    //start & end indices will change for every subarray
    }

    private static int partition(int[] arr, int startIndex, int endIndex, int pivot) {
        //partitioning
        //create 2 pointers for all the non-pivot elements (ie all element to left of the last index of current array
        // 1st pointer starts at leftmost index, 2nd starts at rightmost index
        int leftPointer = startIndex;
        int rightPointer = endIndex;
        //walk the left pointer through the array until find element larger than pivot. once found, leave pointer there
        // walk the right pointer leftwards until find element less than pivot
        while (leftPointer<rightPointer) {  //keeps going until pointers meet, meaning all elements in array have been sorted relative to pivot
            while (arr[leftPointer] <= pivot && leftPointer < rightPointer) {//while te value at te leftpointer is less than at pivot
                leftPointer++;
            } //shifts leftpointer rightwards until value its at exceed pivot, or it meets rightpointer
            while (arr[rightPointer] >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }
            //now have leftpointer pointing to a number exceeding pivot and right pointer has number less than pivot, so must swap them
            swap(arr, leftPointer, rightPointer);
            //swap(arr[leftPointer], arr[rightPointer]); alternate swap method
            //above while loops will keep moving the left and right pointers inwards, swapping values, until they meet
        }
        //once pointers have met, exit the while loop, and swap the pivot with the value at the left pointer

        //logic for swapping the pivot (as arr[endIndex]) with the leftPointer
        //it is not always absolute that the pivotindex will be smaller than endindex, so need an if check, for if the pivotindex is already at the end
                // When the chosen pivot is the highest number it'll end up in the wrong
                // position when swapped from endIndex position to leftPointer position
        if (arr[leftPointer] > arr[endIndex]) {   //if the value at the left pointer is greater than at the end index (aka the pivot value), then swap them
            swap(arr, leftPointer, endIndex);  //pivot is just the value at the specified endIndex location
        } else {
            leftPointer = endIndex;     //otherwise, set the leftpointer to point to the last index
        }
        //partitioning completed
        return leftPointer;

        //aka once left pointer points to a number greater than pivot, and right pointer has number less than pivot
        //swap the values at the pointers
        //repeat process of moving pointers inwards and swapping when both have greater/lesser values
        //once pointers meet, and point at same index, stop moving them, and swap pivot with the current index of the pointers
    }

    public static void swap(int a, int b){
        int temp = a;
        a = b;
        b = temp;
    }

    public static void swap (int[] arr, int index1, int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }


    public static int[] quickSort1(int [] arr) {
        int pivot = arr[arr.length-1];  //set last element as pivot
        for (int i : arr){     //traverse the array
            if (arr[i] < arr[pivot]) {   //if current value is less than pivot value, must make sure its to the left of pivot
                if(i>pivot) {//check if index i is to right of pivot. if so, swap them. else do nothing as its already to the left
                    int temp = i;
                    arr[temp] = arr[pivot];
                    arr[pivot] = arr[i];
                    arr[i] = arr[temp];
                }
            }
            if (arr[i] > arr[pivot]) { //if current value exceed pivot, make sure its to the right of pivot
                if(i<pivot) {   //check if index i is to left of pivot. if so, swap them
                    int temp = i;
                    arr[temp] = arr[pivot];
                    arr[pivot] = arr[i];
                    arr[i] = arr[temp];
                }
            } //pivot now in correct final index,
        }
        //create the 2 subarrays
        int[] leftSub = new int[pivot]; //size of left subarray is just from 0th index up until the 1 just before pivot, so is size equal to pivot index
        int[] rightSub = new int[(arr.length-1) - pivot];

        //copy appropriate values from starting array to subarrays
        System.arraycopy(arr,0,leftSub,0,leftSub.length);
        System.arraycopy(arr,pivot+1,rightSub,0,rightSub.length);
        //recursively run quicksort on left and right subarrays
        quickSort1(leftSub);
        quickSort1(rightSub);

        return arr;  //just meaningless placeholder as I didn't complete the implementation
    }

    public static int[] generateRandomArray(){
        Random random = new Random();
        int randomArray[] = new int[50];    //creating an array of 50 elements, each from 0-100
        for (int i =0; i<randomArray.length; i++){
            randomArray[i] = random.nextInt(100);
        }
        return randomArray;
    }

}

