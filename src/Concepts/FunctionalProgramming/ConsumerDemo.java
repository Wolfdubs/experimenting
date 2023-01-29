package Concepts.FunctionalProgramming;

import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

import static utils.Weapon.generateWeaponsMap;

/*
Consumer interface accepts only 1 / gentrified argument
no return value
so it represents a side effect
Functional variants; IntConsumer, DoubleConsumer, LongConsumer, accepting primitives as arguments
SAM is accept()
also have andThen()
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<Integer> consumer = value -> System.out.println(value);
        Consumer<Integer> consumerMR = System.out::println;

        //foreach accepts a Consumer
        List<String> dogs = new ArrayList<>(List.of("womble", "mungo", "kato", "sita"));
        Consumer<String> printConsumer = System.out::println;
        dogs.forEach(printConsumer);

        //chaining consumers with andThen
        Consumer<List<String>> capitalize = list -> {
            for (int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i).toUpperCase());
            }
            //alt implementation
            //list.replaceAll(String::toUpperCase);
        };

        Consumer<List<String>> printList = list -> list.forEach(System.out::println);

        capitalize.andThen(printList).accept(dogs);


    }
}

//Bi-Consumer accepts 2 arguments
//also returns nothing
//Use: for iterating through entries in a Map
//effectively a signature for any function that takes 2 arguments and returns nothing. you implement the operation to perform via lambda/MR
//the lambda expression you provide, is used to define BiConsumer interface's accept() to perform on the 2 arguments
class BiConsumerDemo{
    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>() {{
            put("womble", 10);
            put("mungo", 1);
            put("sita", 7);
            put("kosie", 2);
            put("kato", 5);
            put("jambo", 6);
        }};
        BiConsumer<String, Integer> biConsumer = new CustomBiConsumer();  //creating a BiConsumer from a specific custom BiConsumer class
        map.forEach(biConsumer);  //hashMaps inbuilt forEach() performs the biConsumer defined operation on each entry



        List<String> list1 = List.of("womble","mungo","kato","sita","jambo","kosie");
        List<String> list2 = List.copyOf(list1);
        List<String> list3 = Arrays.asList("womble", "pekingese", String.valueOf(10), Integer.toString(8), "fluffy");
        BiConsumer<List<String>, List<String>>  customEquals = (listA,listB) -> {           //defining a BiConsumer with a lambda
            if (listA.size()!= listB.size()) System.out.println("Not equal");
            else {
                for (int i = 0; i < listA.size(); i++) {
                    if (!listA.get(i).equals(listB.get(i))) System.out.println("Not equal");
                    return;
                }
                System.out.println("Equal");
            }
        };
        customEquals.accept(list1,list2);   //prints Equal
        customEquals.accept(list1,list3);   //prints Not equal

        BiConsumer<List<String>, List<String>> customPrint = (listA, listB) -> {
            listA.forEach(System.out::println);
            listB.iterator().forEachRemaining(System.out::println);
        };


        //andThen() returns a composed BiConsumer where the parameterized BC is executed after the 1st one
        //basically chaining BiConsumers(????), calling the 1st one, then the 2nd one, on the list you pass in
        customEquals.andThen(customPrint).accept(list1,list2);
        //can even just chain it to itself
        BiConsumer<String,String> chainedBC = (x, y) -> {
            System.out.println(x);
            System.out.println(y);
        };
        chainedBC.andThen(chainedBC).accept("womble","mungo");




    }

    //Specialized BiConsumers -> accept 2 arguments, one is the primitive in the name, one is parameterized object
    ObjIntConsumer<String> objIntConsumer;
    ObjLongConsumer<Integer> objLongConsumer;
    ObjDoubleConsumer<Double> objDoubleConsumer;

    static class CustomBiConsumer implements BiConsumer<String, Integer>{

        @Override
        public void accept(String s, Integer integer) {
            System.out.println("Key = " + s + "\t Value = " + integer);
            if (integer>6) {System.out.println("That's a special dog");}
        }
    }

    //BiConsumer direct iterating through map
    private void biConsumerIteratingThroughMap() {
        Map<String, Integer> weaponMap = generateWeaponsMap();
        weaponMap.forEach((k, v) -> System.out.println("key = " + k + " value = " + v));
    }


}






















