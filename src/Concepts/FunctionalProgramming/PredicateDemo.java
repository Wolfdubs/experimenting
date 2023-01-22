package Concepts.FunctionalProgramming;

import lombok.Getter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

//A functional interface that accepts 1 argument, returns a boolean
//used for stream.filter()
//extensions: IntPredicate, LongPredicate, DoublePredicate
    //exact same, but only accept the specific primitive
//can define with lambda, or have a class implement it and implement its SAM test() (which accepts an argument and returns a boolean)
public class PredicateDemo {
    static List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);

    public static void main(String[] args) {
        evaluate(list, n -> true);    //passing in a predicate where it returns true for each element
        evaluate(list, n -> n % 2 == 0);   //predicate that only returns true for even elements
        Predicate<Integer> predicate = n -> n > 5;
        evaluate(list, predicate);

        List<String> stringList = Arrays.asList("womble","mungo","sita","kato","jambo","kosie");
        Predicate<String> stringPredicate = str -> str.startsWith("k");
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()){
            String currentString = iterator.next();
            if (stringPredicate.test(currentString)) {
                System.out.println(currentString);
            }
        }
    }

    private static void evaluate(List<Integer> list, Predicate<Integer> predicate){
        for (int i : list){
            if (predicate.test(i)){
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }



}

@Getter
class Dog implements Predicate<Dog> {

    String name, species;
    int age;
    double weight;

    @Override
    public boolean test(Dog dog) {
        return dog.species.equals("pekingese");
    }

    public static void main(String[] args) {
        Predicate<String> myPredicate = s -> s.equals("pekingese");//defining Predicate via lambda
        Predicate<Dog> myPredicateDogWeight = dog -> dog.getWeight()>10;
    }
}



















//same as Predicate, but accepts 2 arguments
class BiPredicateDemo {

    public static void main(String[] args) {
        BiPredicate<String,String> stringBiPredicate = (a,b) -> a.length()==b.length();
        String[] strings = {"womble", "mungo", "kato", "sita", "womble", "jambo", "kosie"};
        for (int i = 0; i < strings.length-1; i++){
            for (int j = i+1; j < strings.length; j++)
            if (stringBiPredicate.test(strings[i], strings[j])) {
                System.out.println("Strings with same length found at index " + i + " and " + j + ", with values " + strings[i] + ", " + strings[j]);
            }
        }
    }
}
