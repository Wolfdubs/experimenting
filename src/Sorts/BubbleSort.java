package Sorts;

import java.util.Arrays;
import java.util.Random;

/*is quite slow: O(n^2)
1. Iterate through the list, comparing each number with the number after it
2. If those 2 numbers are out-of-order, it swaps them
    Thus the highest number bubbles to the farthest right
3. Once it reaches end of array, it starts over again, but stops before the last element it moved right (as knows it is sorted as the highest)
4. Stops when it goes through the list and hasn't had to swap anything
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] myArr = generateRandomArray();
        System.out.println("Array before sorting");
        Arrays.stream(myArr).forEach(i -> System.out.print(i + ", "));

        bubbleSort(myArr);

        System.out.println("\nArray after sorting");
        Arrays.stream(myArr).forEach(i -> System.out.print(i + ", "));

    }

    private static void bubbleSort(int[] myArr) {
        boolean swapMade = true;
        int lastUnsorted = myArr.length-1;
        while (swapMade){
            swapMade = false;   //before every loop through the program, set that there have been no swaps
            for (int i = 0; i < lastUnsorted; i++) {     //because want to stop when comparing the 2nd last element to the last element itself
                if (myArr[i] > myArr[i + 1]) {
                    swap(myArr, i);
                    swapMade = true;   //once a swap is made, set it to true, so it will rerun the loop
                }                        //but if nothing was out of order during the loop, then swapMade will remain false and loop will exit
            }
            lastUnsorted--;   //here as do it once per entire while loop (a full traversal of the array
        }
    }

    private static void swap(int[] inputArr, int num){
        int temp = inputArr[num];
        inputArr[num] = inputArr[num+1];
        inputArr[num+1] = temp;
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
