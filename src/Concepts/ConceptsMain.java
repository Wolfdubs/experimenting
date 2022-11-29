package Concepts;

import DataStructures.List.LinkedList.LinkedList;

import java.util.ArrayList;

public class ConceptsMain {
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
