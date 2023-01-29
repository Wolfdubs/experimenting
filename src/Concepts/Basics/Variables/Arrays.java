package Concepts.Basics.Variables;

public class Arrays {

    //arrays
    //default value for ints = 0, booleans = false, objects = null
    //forEach: null elements throw NullPointerException

    public static void main(String[] args) {
        int[][] ints = {{1, 2, 3, 4},
                {9, 8, 7, 6},
                {5, 10, 20, 25}};
        //2D array traversal: Row-major order; loop across elements in a row, then move to start of next row - horizontal movement
        for (int row = 0; row < ints.length; row++) {   //iterates rows
            for (int col = 0; col < ints[row].length; col++) {  //iterates columns within a row
                System.out.print(ints[row][col] + " ");
            }
            System.out.println();
        }

        //Column-major traversal; loops all elements of a set column index across all rows - vertical movement
        for (int col = 0; col < ints[0].length; col++){
            for (int row = 0; row < ints.length; row++) {
                System.out.println(ints[row][col] + " ");
            }
            System.out.println();
        }

        //Jagged arrays; rows of different lengths; nonsymmetric arrays

    }
}
