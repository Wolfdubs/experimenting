package Concepts.Basics.Generics;

import DataStructures.List.LinkedList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.*;

//Placeholder for a class -> discover type compatibility at runtime. so used for classes
//so can use the same code for different input types, so functionality can be reused by many types
    //makes methods flexible for use with different types e.g. sort() for int and string
//takes the type as a parameter so the contained methods also take that type as a parameter
//used with collections because the collection can hold different data types
    //Collections are built upon generics as require a type parameter, e.g. List<String>, List<Integer>, List<MyClass>
//primitives cannot be passed in as the type for a generic -> because generics are calculated at runtime, so during compilation the generic T is erased
    //and replaced by the Object class, so generics must extend Object
//To make a method generic, must make the class generic -> the classes generic type can be used for all its methods
    //convention; T = type, K = key, V = value
class GenericsDemo<T> {

    T thingToPrint;

    public GenericsDemo(T whatever) {
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

        GenericsDemo<Integer> genericInt = new GenericsDemo<>(18);
        genericInt.printGeneric();

        GenericsDemo<Double> genericDouble = new GenericsDemo<>(9.3);
        genericDouble.printGeneric();

        GenericsDemo<String> genericString = new GenericsDemo<>("look");
        genericString.printGeneric();

        GenericsDemo.printGenericWithGenericParameter(4, new LinkedList());
        GenericsDemo.printGenericWithGenericParameter("kewl", new GenericsDemo<>(3));
        GenericsDemo.printGenericWithGenericParameter(-293.503, true);

        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(5); intList.add(1); intList.add(0);
        GenericsDemo.printList(intList);

        ArrayList<String> strList = new ArrayList<>();
        strList.add("qewe"); strList.add("unu"); strList.add("str3");
        GenericsDemo.printList(strList);

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


class GenericsGT {

    //generic class - whoever uses the class must supply the type
    static class MyGeneric<T> {
        T memberVariable;
        MyGeneric(T memberVariable){
            this.memberVariable = memberVariable;
        }
        T getMemberVariable() {return this.memberVariable;}
    }

    @NoArgsConstructor
    static class My2Generics<T, U> {    //has 2 generic types; used for maps
        T fieldT;
        U fieldU;

        My2Generics(T fieldT, U fieldU) {
            this.fieldT = fieldT;
            this.fieldU = fieldU;
        }
        public T getFieldT() {            return fieldT;        }
        public U getFieldU() {            return fieldU;        }
        void display() {            System.out.println(fieldT);            System.out.println(fieldU);        }
    }

    public static void main(String[] args) {
        List rawList = new ArrayList<>();
        rawList.add("womble");
        String wombleString = (String) rawList.get(0);   //for a raw list, must cast elements as a specific type as stored as just objects

        //calling the custom generic class
        MyGeneric<Double> myDoubleGeneric = new MyGeneric<>(10.0);
        myDoubleGeneric.getMemberVariable();
        MyGeneric<Character> myCharacterGeneric = new MyGeneric<>('w');
        myCharacterGeneric.getMemberVariable();
        MyGeneric<Boolean> booleanMyGeneric = new MyGeneric<>(false);

        //calling custom 2 generics class
        My2Generics<String, Integer> stringIntegerMy2Generics = new My2Generics<>("womble", 13);
        stringIntegerMy2Generics.display();
        My2Generics<Boolean, Long> booleanLongMy2Generics = new My2Generics<>();


    }
}

class BoundedGenerics {
    /*
    bound the generics to specific types
    e.g. sort() ensuring the type is a comparable one, so do T extends Comparable so sort() knows what to sort by using its compareTo()
        Integer, Double, String extend Comparable by default
    If the type does not extend the specific type, throws compilation error
     */
    class SortingClass<T extends Comparable<T>> {
        private List<T> sort(List<T> list){
            //sorting logic
            return new Vector<>();
        }
    }

    //can have a generic type bounded to multiple specific types
    class MultipleBoundsClass<T extends Number & Comparable<T>> {

    }

    //further example:
    abstract class Animal {

        protected final String type;
        protected final String name;

        protected Animal(String type, String name) {
            this.type = type;
            this.name = name;
        }

        abstract String makeSound();
    }

    class Dog extends Animal {

        public Dog(String type, String name) {
            super(type, name);
        }

        @Override
        public String makeSound() {
            return "Wuf";
        }

    }

    class Cat extends Animal implements Comparable<Cat> {
        public Cat(String type, String name) {
            super(type, name);
        }
        private String getName(){return this.name;}

        @Override
        public String makeSound() {
            return "Meow";
        }

        @Override
        public int compareTo(@NotNull Cat cat) {
            return this.getName().length() - cat.getName().length();
        }
    }
    //list cannot contain elements of the Dog type since the class doesn’t implement a Comparable interface.
    //the generic here requires the class to fit both criteria
    public static <T extends Animal & Comparable<T>> void order(List<T> list) {
        list.sort(Comparable::compareTo);
    }
}



