package Concepts.Comparable_Comparator_Iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorInterfaceClass {

    public static void main(String[] args) {

        //COMPARATOR INTERFACE
        //Comparator Functional Interface -> used for custom sorting e.g. sort numbers based on their last digit
        // must override the compare() method defined by the type in your list e.g. defined by Integer class
        //Comparator object contains logic for sorting; but no inbuilt classes implement Comparator
        //     so must create own class via manually, or via lambda/anonymous applied to its SAM compare() method
        List<Integer> threeDigitsList = new ArrayList<>();
        threeDigitsList.add(392); threeDigitsList.add(618); threeDigitsList.add(107); threeDigitsList.add(901); threeDigitsList.add(409);

        //Anonymous Comparator implementation
        Comparator<Integer> myAnonymousComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer i, Integer j) {
                return i%10 > j%10 ? 1 : -1;     //will compare last digit of 3 digit number, for sorting ascending

            }
        };

        Collections.sort(threeDigitsList, myAnonymousComparator);   //pass in list, and object of Comparator which contains the logic for sorting; swaps elements if return from compare() is 1. won't swap if -1
        for (Integer i : threeDigitsList) {
            System.out.println(i);}

        //Lambda Comparator implementation
        Comparator<Integer> myLambdaComparator = (i, j) -> i%10 < j%10 ? 1 : -1;  //in lambda, don't have to specify type of subjects being compared, and 'return' is assumed from the ->
        threeDigitsList.sort(myLambdaComparator);   //this is sorting descending
        for (Integer i : threeDigitsList) {
            System.out.println(i);}

        //Lambda Comparator implementation directly in the sort() method, rather than passing in Comparator object
        Collections.sort(threeDigitsList, (i, j) -> i%10 >j%10 ? 1 : -1);
        for (Integer i : threeDigitsList) {
            System.out.println(i);}

        threeDigitsList.sort(Integer::compareTo);  //just sorts the list using the simple pre-defined compareTo for Integers
        for (Integer i : threeDigitsList) {
            System.out.println(i);}

        threeDigitsList.sort((i,j) -> Integer.compare(i%10, j%10));
        for (Integer i : threeDigitsList) {
            System.out.println(i);}

        threeDigitsList.sort(Comparator.comparingInt(i -> i%10));
        for (Integer i : threeDigitsList) {
            System.out.println(i);}

    }
}
