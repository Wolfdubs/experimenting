package Concepts.Basics.Generics;

import DataStructures.List.LinkedList.LinkedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Placeholder for a class
//used with collections because the collection can hold different data types
class Generics<T> {

    T thingToPrint;

    public Generics(T whatever) {
        this.thingToPrint = whatever;
    }

    public void printGeneric() {
        System.out.println(thingToPrint);
    }

    //the <T,V> is just the generic type parameter declaration, telling the method it will work with some generics
    public static <T, V> void printGenericWithGenericParameter(T input, V input2){    //The <T,V> has nothing to do with return type, it just preps the method to work with 2 generics
        System.out.println(input + " is " + input.getClass() + " and " + input2 + " is " + input2.getClass());
    }

    //Returning a generic type
    public static <T, V> T printGenericWithGenericParameter2(T input, V input2){    //Specifies the lone T as the return type
        System.out.println(input + " is " + input.getClass() + " and " + input2 + " is " + input2.getClass());
        return input;     //returns the object of the generic T, as the method signature return type
    }

    //specifying a generic must extends/implements something
    public static <G extends Object> void myMethod(G g){
        System.out.println(g);
    }

    public static void printList(List<?> list){
        System.out.println(list);
    }

    public static void printListBounded(List<? extends Collections> list){    //means the generic must be child of Collections class
        System.out.println(list);
    }

}

public class GenericsMain {
    public static void main(String[] args) {

        Generics<Integer> genericInt = new Generics<>(18);
        genericInt.printGeneric();

        Generics<Double> genericDouble = new Generics<>(9.3);
        genericDouble.printGeneric();

        Generics<String> genericString = new Generics<>("look");
        genericString.printGeneric();

        Generics.printGenericWithGenericParameter(4, new LinkedList());
        Generics.printGenericWithGenericParameter("kewl", new Generics<>(3));
        Generics.printGenericWithGenericParameter(-293.503, true);

        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(5); intList.add(1); intList.add(0);
        Generics.printList(intList);

        ArrayList<String> strList = new ArrayList<>();
        strList.add("qewe"); strList.add("unu"); strList.add("str3");
        Generics.printList(strList);

    }
}

class Pair<KeyClass, ValueClass>{
    KeyClass key;
    ValueClass value;
    public Pair(KeyClass kc, ValueClass vc) {
        key = kc;
        value = vc;
    }

}


