package Concepts.FunctionalProgramming;

import lombok.Getter;
import utils.Weapon;

import java.util.*;
import java.util.function.*;

import static utils.Weapon.generateWeaponsList;

//A functional interface that accepts 1 argument, returns a boolean
//special case of Function interface; takes 1 argument, returns a boolean
//SAM = test() accepts an argument and returns a boolean
//used for stream.filter()
//extensions: IntPredicate, LongPredicate, DoublePredicate
    //exact same, but only accept the specific primitive
//can define with lambda, or have a class implement it and implement test()
//Default methods
    //and() = adds a && between the 2 predicate conditions
    //or() = adds a || between 2 predicates
    //negate() adds a ! before a predicate
public class PredicateDemo {
    static List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);

    public static void main(String[] args) {
        evaluate(list, n -> true);    //passing in a predicate where it returns true for each element
        evaluate(list, n -> n % 2 == 0);   //predicate that only returns true for even elements
        Predicate<Integer> greaterThan5 = n -> n > 5;
        evaluate(list, greaterThan5);

        List<String> stringList = Arrays.asList("womble","mungo","sita","kato","jambo","kosie");
        Predicate<String> stringPredicate = str -> str.startsWith("k");
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()){
            String currentString = iterator.next();
            if (stringPredicate.test(currentString)) {
                System.out.println(currentString);
            }
        }
        List<String> fourLetterNames = stringList.stream()
                .filter(s -> s.length()==4)
                .toList();

        Predicate<String> andPredicate = s -> s.length()==4 && s.contains("oo");//using and()
        stringPredicate.and(andPredicate).test("womble");    //other way to use and()
        Predicate<Double> orPredicate = d -> d>5 || d.toString().length()==3;//using or()
        stringPredicate.or(andPredicate).test("mungo");   //other way to use or()
        andPredicate.negate().test("womble");   //inverses the calling predicate

        Predicate<Integer> lessThan10 = i -> i < 10;
        System.out.println(lessThan10.test(8));
        boolean andResult = lessThan10.and(greaterThan5).test(7);
        System.out.println("and Result = " + andResult);
        boolean orResult = lessThan10.or(n -> n%3==0).test(18);
        System.out.println("or result = " + orResult);

        //negate returns a predicate that is the opposite of the one specified
        System.out.println(lessThan10.negate().test(8));

        predicateInFunction(9, n -> Math.sqrt(81)==n);

        stringLengthAbove5.test("womble");
        stringsPredication();

        List<Weapon> weapons = generateWeaponsList();
        List<Weapon> groupWeapons = weaponsPredication(weapons, weapon -> !weapon.isSinglePerson());
        groupWeapons.forEach(System.out::println);


    }

    private static Predicate<String> stringLengthAbove5 = new Predicate<String>() {
        @Override
        public boolean test(String s) {
            return s.length()>5;
        }
    };

    private static void evaluate(List<Integer> list, Predicate<Integer> predicate){
        for (int i : list){
            if (predicate.test(i)){
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    private static void predicateInFunction(int n, Predicate<Integer> predicate) {
        if (predicate.test(n)){
            System.out.println("The number = " + n);
        }
    }

    private static void stringsPredication() {
        Predicate<String> nonNullPredicate = Objects::nonNull;
        String nullString = null;
        boolean nullStringResult = nonNullPredicate.and(stringLengthAbove5).test(nullString);
        System.out.println(nullStringResult);

        String trueString = "womble is a fluffy dog";
        boolean trueStringResult = nonNullPredicate.and(stringLengthAbove5).test(trueString);
        System.out.println(trueStringResult);
    }

    private static List<Weapon> weaponsPredication(List<Weapon> list, Predicate<Weapon> predicate) {
        List<Weapon> predicatedWeapons = new ArrayList<>();
        for (Weapon weapon : list) {
            if (predicate.test(weapon)) {
                predicatedWeapons.add(weapon);
            }
        }
        return predicatedWeapons;
    }
}

class SpecializedPredicates {
    IntPredicate intPredicate = i -> i>10;
    DoublePredicate doublePredicate = d -> d>10;
    LongPredicate longPredicate = l -> l>10;
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

        BiPredicate<Integer,String> integerStringBiPredicate = (a,b) -> a.toString().equals(b);
    }
}
