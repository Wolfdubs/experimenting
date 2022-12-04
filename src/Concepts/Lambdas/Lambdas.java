package Concepts.Lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lambdas {



    public static void main(String[] args) {

/*        InterfacesClass ic = new InterfacesClass();       //lambdas mean you don't need a class that implements the interface, with a custom implementation
        ic.methodThatAllImplementingClassesMustAlsoImplement();*/    //and a created object, and then that method called on the object

    //Instead, pass the custom implementation directly into a method that accepts an interface object as a parameter
    //	Use when you want to pass an implementation into a function that accepts an object of a functional interface
        //	1. need functional interface
        //	2. need a method that accepts an object of the functional interface type, can then calls the SAM on it
        //can then pass lambda function directly into that method

        methodThatAcceptsObjectOfInterface( () -> {System.out.println("lambda works");} );
        methodThatAcceptsObjectOfInterface( () -> System.out.println("lambda works 2"));   // if implementation passed in is just 1 expression, dont need the {}
        myInterface lambdaVariable = () -> System.out.println("lambda works 3");
        methodThatAcceptsObjectOfInterface(lambdaVariable);


     //Lambdas can only be used in the context of a functional interface (only 1 Single Abstract Method).
        //because lambdas define the implementation of only 1 method, so if interface has multiple abstract methods, then some of them could be undefined if you implement the body of that method via lambdas
        //so need a new interface for every new lambda implementation
        // EXCEPT: most lambda usecases can just use built in Java interfaces in java.util.function https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
        myInterface2 lambdaInterface = (s) -> System.out.println("output" + s);   //dont have to specifying parameter type as java infers it from interface method source
        printSuffix(lambdaInterface);

    // event for interface methods requiring a return type, dont have to specify the return if only 1 line body; java will infer it
        myInterface3 lambdaInterface3 = (s) -> "input string: " + s;   //so this is just like (s) -> {return "input string: " + s;   but this wont actually print it, just returns the string value
        lambda3(lambdaInterface3);
        myInterface3 lambdaInterface3Print = ((s) ->
            {System.out.println("input string: " + s);
            return "input string" + s;}
            );
        lambda3(lambdaInterface3Print);


        myInterface4 lambdaWithParameters = ((int1, int2) ->
            {System.out.println(int1 + int2);
            return int1 + int2;}
            );
        myInterface4 lambdaWithParameters2 = ((int1, int2) ->
            {System.out.println(int1 * int2);
            return int1 * int2;}
            );
        myInterface4 lambdaWithParameters3 = ((int1, int2) ->
            {System.out.println(int1/int2);
            return int1 / int2;}
            );
        lambda4(lambdaWithParameters);
        lambda4(lambdaWithParameters2);
        lambda4(lambdaWithParameters3);

        List<myInterface4> interface3List = new ArrayList<>(Arrays.asList(lambdaWithParameters, lambdaWithParameters2, lambdaWithParameters3));
        lambda4(interface3List);

        //Making a lambda using pre-existing java interfaces. so don't have to create my own interface and SAM
        java.util.function.IntBinaryOperator preLambda = (i, j) -> (i+j);
        java.util.function.IntBinaryOperator preLambda2 = ((i, j) ->
            {System.out.println(i+j);
            return i+j;
        });
        java.util.function.IntBinaryOperator preLambda3 = Integer::sum; //alternatively can replace lambda with method reference
        lambda5(preLambda2);


        //Lambda in an enhanced for loop
        List<Integer> integerList = Arrays.asList(4,1,9,3,7,5,6);
        integerList.forEach(i -> System.out.println(i));
        //replacing same lambda with a method reference
        integerList.forEach(System.out::println);
    }

    static void methodThatAcceptsObjectOfInterface(myInterface ds){
        ds.methodThatAllImplementingClassesMustAlsoImplement();
    }

    static void printSuffix(myInterface2 it){
        it.printSuffix("!!!!");
    }

    static void lambda3(myInterface3 it) {
        it.interfaceMethod3("*****");
    }

    static void lambda4(myInterface4 it) {
        it.InterfaceMethod4(10, 5);
    }

    static void lambda4(List<myInterface4> it){
        for (myInterface4 i : it) {
            i.InterfaceMethod4(12,4);
        }
    }

    static void lambda5(java.util.function.IntBinaryOperator it){
        it.applyAsInt(33,100);
    }


}
