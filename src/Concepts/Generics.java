package Concepts;

import java.util.Collections;
import java.util.List;

public class Generics<T> {

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
