package Sorts;

import java.util.Arrays;
import java.util.Random;

//O(nlog2(n)) -log2 due to splitting into halves
//divide array into halves until get to base case of 1 element, then sort each pair while combining
//divide array in half, and then recursively call mergeSort on each half
//once at 1 element arrays, each is sorted (as 1 element array is by definition sorted).
// Then merge each pair of arrays, by comparing 1st element of both arrays, and adding the smaller one first to the merged array
//for the array where the smaller value was taken, move the front pointer along to the next element
//once one of the arrays is empty, just add all the remaining elements of the remaining array to the merged array in order
public class MergeSort {
    public static void main(String[] args) {
        int[] myArray = generateRandomArray();
        System.out.println("Array before sorting: ");
        Arrays.stream(myArray).forEach(i -> System.out.print(i + ", "));  //turn the array into a steam using the Arrays class stream() method

        mergeSort(myArray);

        System.out.println("\nArray after merge sort: ");
        Arrays.stream(myArray).forEach(i -> System.out.print(i + ", "));
    }

    private static void mergeSort(int[] inputArray){
        int inputLength = inputArray.length;                 //will need to use the length of the input array many times
        if (inputLength<=1)                                //set base case for the recursive call on input arrays. So this codes the middle stage of having series of 1 element arrays
            return;
        //divide the array into 1 arrays (unless we hit the above return)
        int midpoint = inputLength/2;
        int[] leftHalf = new int[midpoint];   //creating array with half the length of the input array
        int[] rightHalf = new int[inputLength - midpoint];  //cannot just use midpoint as this fails for odd-numbered arrays, because the inputLength/2 truncates any decimals so 9 would be 4 and 4

        //populate the new arrays
        for (int i=0; i < midpoint; i++){  //looping the length of leftHalf
            leftHalf[i] = inputArray[i];
        }
        for (int i=midpoint; i < inputLength; i++){    //lopping through the 2nd half of the inputArray
            rightHalf[i-midpoint] = inputArray[i];    //do i-midpoint so that the rightHalf doesn't start at e.g. index 10 if that is where the midpoint was
        }

        mergeSort(leftHalf);  //each half array now must themselves be mergesorted
        mergeSort(rightHalf);
        //now should have 2 completely sorted halves, as this recursion runs until it produces a series of 1 element arrays
        //so now merge those sorted arrays
        merge(inputArray, leftHalf, rightHalf);




    }
    private static void merge(int[] mergedArray, int[] leftHalf, int[] rightHalf){   //must take in the original array to merge the 2 halves into
        int leftLength = leftHalf.length;
        int rightLength = rightHalf.length;

        //loop through the already sorted elements in the left and right arrays, and combine them into the combined array in order
        //needs 3 iterator variables to walk through the 3 arrays
        int leftIterator = 0, rightIterator = 0, mergedIterator = 0;

        while (leftIterator<leftLength && rightIterator<rightLength){   //loops as long as the 2 half array iterators have not reached the end of their arrays
            if (leftHalf[leftIterator] <= rightHalf[rightIterator]){    //find the smaller of the 2 numbers, by checking if 1st element of left half is smaller
                mergedArray[mergedIterator] = leftHalf[leftIterator];   //then add that element to the mergedArray
                leftIterator++;         //increment the leftHalf incrementor to look at the next element along
            } else {   //then the 1st element in the rightHalf must be smaller
                mergedArray[mergedIterator] = rightHalf[rightIterator];
                rightIterator++;
            }
            mergedIterator++;   //regardless of if we added from lefthalf or righthalf, need to increment the mergediterator to point to next empty index
        }
        //above while loop will exit once 1 of the half arrays is empty,
        //so still need to add the remaining elements from either of the sorted half arrays
        while (leftIterator < leftLength){  //if it is the left iterator that didn't reach the end of its array
            mergedArray[mergedIterator] = leftHalf[leftIterator];  //keeps looping through all elements left in left half and adds the to mergedArray
            leftIterator++;
            mergedIterator++;
        }
        while (rightIterator < rightLength){    //only 1 of these 2 while loops will be entered and executed; whichever half still had elements
            mergedArray[mergedIterator] = rightHalf[rightIterator];
            rightIterator++;
            mergedIterator++;
        }
        //now mergedArray has all the elements from the 2 halves in sorted order
    }

    private static int[] generateRandomArray(){
        int[] myArray = new int[40];
        Random random = new Random();
        for (int i =0; i<myArray.length; i++){
            myArray[i] = random.nextInt(0,100);
        }
        return myArray;
    }
}


