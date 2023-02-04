package Concepts.FunctionalProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class PassingFunctionAsParameter {
    //Higher order functions are those that accept another function as an argument, or return another function post execution
        //can Function, Consumer, Supplier or Predicate

    public static void main(String[] args) {
        //1. Use the Function interface to create this function object
        Function<Integer, Integer> function = n -> (n + 3) * 4;
        int returnedValue = higherOrderFunction(5, function);
        System.out.println(returnedValue);

        Predicate<Integer> predicate = n -> n > 10 && n <100;
        boolean isBetween10And100 = higherOrderPredicate(29, predicate);
        System.out.println(isBetween10And100);

        //2. Use lambda/MR
        new ArrayList<String>(List.of("womble","mungo","sita","kato")).forEach(s -> System.out.println(s));

        //3. Passing in a custom method
        Doable doa = s -> s + "is a pekingese";
        show("womble", doa);

        //Similarly, but as MR
        Thread thread = new Thread(PassingFunctionAsParameter::print);
        thread.start();



    }
    private static int higherOrderFunction(int value, Function<Integer, Integer> function) {
        return function.apply(value);
    }

    private static boolean higherOrderPredicate(int value, Predicate<Integer> predicate){
        return predicate.test(value);
    }



    private interface Doable {
        String doSomething(String string);
    }
    public static void show(String string, Doable doable) {
        String message = doable.doSomething(string);
        System.out.println(message);
    }
    public static void print(){
        System.out.println("stuff");
    }



}
