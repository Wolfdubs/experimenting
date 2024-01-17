package Concepts;

import lombok.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static Concepts.Gun.TYPE.*;


//Comparable = define how a list of the class should be ordered when sorted
//is a functional interface; SAM = compareTo()
//sorting order determined by the int returned by compareTo()
    //x vs y -> +ve if x greater, -ve is y greater
//vs Comparator: takes 2 arguments to represent 2 objects being compared
    //then pass comparator object to sort, along with the list
    //comparator is also a functional interface, SAM = compare()
public class ComparableDemo {

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    static class Dog implements Comparable<Dog> {
        String name;
        String species;
        double weight;
        int cuteness;
        boolean fluffy;
        boolean hasSnout;

        public Dog(String name) {            this.name = name;        }

        public Dog(String name, String species, double weight, int cuteness, boolean fluffy, boolean hasSnout) {
            this.name = name; this.species = species; this.weight = weight;this.cuteness = cuteness; this.fluffy = fluffy;this.hasSnout = hasSnout;}

        @Override
        public int compareTo(Dog o) {   //returns 1 if current > specified, -1 if current < specified
            return Double.compare(this.getWeight(), o.getWeight());
        }

        public int compareToCuteness(Dog o) {
            return o.getCuteness()>this.getCuteness() ? 1 : o.getCuteness()==this.getCuteness() ? 0 : -1;
           // return o.getCuteness() - this.getCuteness();   //ascending order: AVOID THIS SUBSTITUTION TRICK, can cause integer overflow if values exceed int max/min, where they circle back around to the wrong sign
        }

        public int compareToStrings(Dog d) {
            if (this.getSpecies().compareTo(d.getSpecies()) != 0) {
                return this.getSpecies().compareTo(d.getSpecies());   //first tries to order by species, in descending order
            }
            return this.getName().compareTo(d.getName());   //if species are the same, will order by name
        }
    }


        public static void main(String[] args) {
            List<Dog> dogList = new java.util.ArrayList<>(List.of(new Dog("womble", "pekingese", 10, 10, true, false),
                    new Dog("mungo", "pekingese", 8, 1, true, false),
                    new Dog("kato", "pug", 25, 2, false, false),
                    new Dog("sita", "labrador", 45, 7, false, true),
                    new Dog("kosie", "shitzu", 18, 5, true, true),
                    new Dog("pixie", "boo dog", 6, 8, true, true),
                    new Dog("jambo", "labrador", 40,6,false,true)));
            dogList.sort(Dog::compareTo);
            dogList.stream().map(Dog::toString).forEach(System.out::println);
            System.out.println("/////////////");
            dogList.sort(Dog::compareToCuteness);
            dogList.stream().map(Dog::toString).forEach(System.out::println);
            System.out.println("/////////////");
            dogList.sort(Dog::compareToStrings);
            dogList.stream().map(d -> d.getName() + ", " + d.getSpecies()).forEach(System.out::println);

            dogList.sort(Collections.reverseOrder());  //sorts the list in reverse order, using the defined compareTo()
        }


}
@ToString
class Gun implements Comparable<Gun> {
    protected TYPE TYPE;
    public enum TYPE {HANDGUN, RIFLE, SHOTGUN, SNIPER, REVOLVER}
    protected String make;
    protected String model;
    protected int capacity;
    protected int power;
    protected boolean automatic;

    public Gun(Gun.TYPE type, String make, String model, int capacity, int power, boolean automatic) {
        TYPE = type;
        this.make = make;
        this.model = model;
        this.capacity = capacity;
        this.power = power;
        this.automatic = automatic;
    }

    @Override
    public int compareTo(Gun g) {
        return Integer.compare(this.power, g.power);
    }

    public static void main(String[] args) {
        Gun[] guns = {new Gun(HANDGUN, "make1", "model1", 10, 3, true),
            new Gun(RIFLE, "make2", "model2", 30, 7, true),
            new Gun(SHOTGUN, "make3", "model3", 2, 8, false),
            new Gun(SNIPER, "make4", "model4", 5,10, false),
            new Gun(REVOLVER, "make5", "make5", 6, 5, false)
        };
        Arrays.sort(guns, Gun::compareTo);
        for (Gun gun : guns) {
            System.out.println(gun);
        }

        Arrays.sort(guns);   //don't have to specify comparator as default will use comparable classes natural ordering via compareTo()
        Arrays.stream(guns).forEach(System.out::println);
    }
}
