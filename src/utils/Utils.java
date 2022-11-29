package utils;

public class Utils {

    public static boolean printArray(int[] arr){
        int[] printableArray = new int[10];
        for (int i=0; i<arr.length; i++){
            System.out.print(arr[i] + ",");
        }
        return true;
    }

    public static int swap(int a, int b){
        return a;
    }
}
