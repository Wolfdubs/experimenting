package Concepts;

import java.util.Arrays;

public class InBuiltMethods {
    public static void main(String[] args) {

        int[] myArr = new int[]{55, 9, 3, -6, 2, 238, 0, 11, 23, -82, -4, 17};
        Arrays.sort(myArr);
        int position = Arrays.binarySearch(myArr, 11);
    }
}
