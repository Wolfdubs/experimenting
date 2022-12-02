package Concepts.Basics.Generics;

import DataStructures.List.LinkedList.LinkedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Generics<T> {

    T thingToPrint;

    public Generics(T whatever) {
        this.thingToPrint = whatever;
    }

    public void printGeneric() {
        System.out.println(thingToPrint);
    }

    public static <T, V> void printGenericWithGenericParameter(T input, V input2){
        System.out.println(input + " is " + input.getClass() + " and " + input2 + " is " + input2.getClass());
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


