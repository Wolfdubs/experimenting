package Concepts.FunctionalProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class PassingFunctionAsParameter {
    //Higher order functions are those that accept another function as an argument, or return another function post execution
        //can Function, Consumer, Supplier or Predicate
    //or custom Interfaces, where you pass in the defined implementation

    public static void main(String[] args) {
        //1. Use the Function interface to create this function object
        Function<Integer, Integer> function = n -> (n + 3) * 4;
        int returnedValue = higherOrderFunction(5, function);
        System.out.println(returnedValue);

        Predicate<Integer> predicate = n -> n > 10 && n <100;
        boolean isBetween10And100 = higherOrderPredicate(29, predicate);
        System.out.println(isBetween10And100);

        //2. Use lambda/MR
        new ArrayList<String>(List.of("womble","mungo","sita","kato")).forEach(System.out::println);

        //3. Passing in a custom method
        Doable doa = s -> s + " is a pekingese";
        show("womble", doa);

        //Similarly, but as MR
        Thread thread = new Thread(PassingFunctionAsParameter::print);
        thread.start();

        main2(null);


    }
    private static int higherOrderFunction(int value, Function<Integer, Integer> function) {
        return function.apply(value);
    }

    private static boolean higherOrderPredicate(int value, Predicate<Integer> predicate){
        return predicate.test(value);
    }


    @FunctionalInterface
    public interface Doable {
        String doSomething(String string);
    }

    public static void show(String string, Doable doable) {
        String message = doable.doSomething(string);
        System.out.println(message);
    }
    public static void print(){
        System.out.println("stuff");
    }



    void otherMethodParameterTesting(Integer myInt, Consumer<String> myCon){
        myCon.andThen(s -> {
            var sub = s.substring(1, 4);
        }).accept(String.valueOf(myInt));
    }


    static Double methodReferenceParameter(Integer int1, Integer int2, BiFunction<Integer,Integer,Double> bif){
        return bif.apply(int1, int2);
    }

    public static void main2(String[] args) {
        var myPowered = methodReferenceParameter(5,9,Math::pow);
        System.out.println(myPowered);
    }



}


