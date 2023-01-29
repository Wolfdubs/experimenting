package Searches;


import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

public class ArraySearch {
    private int[] arr = new int[20];
    int arrSize = 0;
    private void generateRandomArray(){
        for (int i=0; i<arr.length; i++) {
            arr[i] = (int) (Math.random()*10 + 10);
        }
    }

    public static void main(String[] args) {
        ArraySearch as = new ArraySearch();
        as.generateRandomArray();
        as.printArray(as.arr);
        as.linearSearch(11);
        as.binarySearch(14);

    }

    public void printArray(int[] a){
        System.out.println("----------");
        for (int i=0; i<a.length; i++){
            System.out.print("| " + i + " | ");
            System.out.println(a[i] + " |");
            System.out.println("----------");
        }
    }

    public boolean linearSearch(int target){
        boolean found = false;
        String positionsFound = "Target found at index: ";
        for (int i = 0; i<arr.length; i++){
            if (arr[i] == target){
                found = true;
                positionsFound += i + ", ";
            }
        }
        if (found)
            System.out.println(positionsFound);
        else System.out.println("Target not found in array");
        return found;
    }

    /*pre-condition; elements must be sorted
    Process;
        compare middle element to target
        if equal, terminate
        if target is less, set highindex bound to mid-1 (aka repeat steps for left subhalf)
        if target is more, repeat steps for right subhalf from mid+1
        repeat until target found, or have single element array
    best case; O(1) element at mid
    worst; O(log2(n))
    */
    public boolean binarySearch(int target){
        int[] sortedArr = streamSort();
        System.out.println("The sorted array");
        printArray(sortedArr);

        int lowIndex = 0;
        int highIndex = sortedArr.length-1;

        while (lowIndex < highIndex) {
            int midpoint = (highIndex + lowIndex) / 2;
            if (sortedArr[midpoint] == target) {
                System.out.println("The value " + target + " was found at sorted index " +  midpoint);
                return true;
            } else if (sortedArr[midpoint] > target){
                highIndex = midpoint - 1;
            } else if (sortedArr[midpoint] < target){
                lowIndex = midpoint + 1;
            }
        }
        System.out.println("Target not found");
        return false;
    }


    public int[] streamSort(){
        IntStream intStream = Arrays.stream(arr).sorted();
        return intStream.toArray();

    }
}




















