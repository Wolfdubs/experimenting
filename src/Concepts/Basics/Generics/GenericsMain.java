package Concepts.Basics.Generics;

import DataStructures.List.LinkedList.LinkedList;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Placeholder for a class -> discover type compatability at runtime
//used with collections because the collection can hold different data types
//primitives cannot be passed in as the type for a generic
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


class GenericBubbleSort{
    private static <T extends Comparable<T>> List<T> genericBubbleSort(List<T> list) {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < list.size()-1; i++) {
                if (list.get(i).compareTo(list.get(i+1))==1) {
                    T temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    swapped = true;
                }
            }
        }
        System.out.println(list);
        return list;
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>(List.of("womble", "mungo", "kato", "sita", "jambo", "kosie"));
        List<Integer> integerList = new ArrayList<>(Arrays.asList(1,6,3,9,6,2,0,11,3,7,1,5));
        List<ProgrammingLanguage> programmingLanguages = new ArrayList<>(List.of(new ProgrammingLanguage("Java", 100, true),
                new ProgrammingLanguage("python", 200, false), new ProgrammingLanguage("perl", 30, false),
                new ProgrammingLanguage("ruby", 50, true), new ProgrammingLanguage("shell", 40, false),
                new ProgrammingLanguage("C#", 35, true), new ProgrammingLanguage("go", 5, true)));
        genericBubbleSort(strings);
        genericBubbleSort(integerList);
        genericBubbleSort(programmingLanguages);
    }

    @ToString
    private static class ProgrammingLanguage implements Comparable<ProgrammingLanguage>{
        String name;
        int users;
        boolean isOOP;

        public ProgrammingLanguage(String name, int users, boolean isOOP) {
            this.name = name;
            this.users = users;
            this.isOOP = isOOP;
        }

        @Override
        public int compareTo(ProgrammingLanguage o) {
            return this.users > o.users ? 1 : this.users==o.users ? 0 : -1;
        }
    }
}


