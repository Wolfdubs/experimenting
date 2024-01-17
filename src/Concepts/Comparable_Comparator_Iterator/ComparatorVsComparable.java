package Concepts.Comparable_Comparator_Iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorVsComparable {
    public static void main(String[] args) {
        List<CarComparable> cars = new ArrayList<>();
        cars.add(new CarComparable("BMW", 25000, 130)); cars.add(new CarComparable("Porsche", 80000, 190));
        cars.add(new CarComparable("Ford", 10000, 90)); cars.add(new CarComparable("Lamborghini", 150000, 250));

        //use Collections class static method sort() and pass in a list. sort() will then use logic in that lists compareTo()
        Collections.sort(cars);  //sort() only accepts Objects that implement Comparable. Must specify the criteria to sort by
                                //so to pass in a list, your class must implement Comparable interface and override compareTo()
                                 //sort() swaps based on output of 1 or -1 from compareTo() within your class
        for (CarComparable c:cars) {System.out.println(c);}

        //Passing Comparator interface object into sort(), so can customize logic for its SAM "compare()" every time
        //create Comparator interface object via anonymous class / lambda
        Comparator<CarComparable> carComparator = (CarComparable c1, CarComparable c2) -> {   //Comparators' compare() only accepts 2 objects
            char c1Letter = c1.getBrand().charAt(0);
            char c2Letter = c2.getBrand().charAt(0);
                return Character.compare(c1Letter,c2Letter); };

        Collections.sort(cars, carComparator);
        for (CarComparable c:cars) {System.out.println(c);}

        cars.sort(Comparator.comparingInt(CarComparable::getPrice));   //passing in a comparator object made on the fly
        for (CarComparable c:cars) {System.out.println(c);}

    }
}

/*
To sort collection of values, implement Comparable interface and override compareTo()
Comparator for when:
    1. a class doesn't implement Comparable and you cannot change src, e.g. it is a class file
    2. You want ability to change sorting logic every time
       you can pass logic directly to Collections.sort(), as sort() only cares about the collection and the logic to sort by
       sort() accepts a list and a Comparator object
    lets you change the sorting logic without having to change the classes compareTo() every time
        by just passing into sort() a different logic
Comparable does the comparing logic in classes overridden compareTo() comparing this (current object compareTo() is called on and the 1 object passed in
Comparator does the comparing via lambda/anonymous class overriden compare() comparing 2 objects passed in



Comparable:
    meant for default natural sorting order
    only 1 method - compareTo()
    all wrapper classes & String implement Comparable with their own logic
Comparator:
    meant for customized sorting
    2 methods - compare(), equals() (which by default uses Object equals() hence dont need to override yourself)
    only 2 built-in classes implement; Collator & RuleBaseCollator
 */
