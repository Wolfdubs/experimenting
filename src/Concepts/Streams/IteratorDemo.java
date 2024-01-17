package Concepts.Streams;

import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
/*to iterate over lists
Iterator is an Interface, to create object of it, must call iterator() method from the list object
for loops and iterators are 'External Iteration'
Streams provide 'Internal Iteration'
Iterator allows removal/retrieval of items during iteration
//https://www.baeldung.com/java-iterator-vs-iterable
 */

public class IteratorDemo {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(5, 8, 2, 4, 1, 0, 5, 8, 6, 2);
        Iterator<Integer> myIterator = intList.iterator();  //every value from the list object are sent to the iterator object. list obj can generate an iterator
        int counter = 0;
        while (myIterator.hasNext() && counter < 5) { //check if iterator has a next element. returns boolean
            System.out.println(myIterator.next());  //next() actually moves to the next value
            counter++;
        }
        myIterator.forEachRemaining(Object::notify);

        Consumer<String> myConsumer = i -> System.out.println(i);     //could pass myConsumer object to forEach, or just pass the implementation directly
        intList.forEach(i -> System.out.println(i));  //forEach requires object of Consumer functional interface, so can use lambda/method reference to
        intList.forEach(System.out::println);   // to define the implementation for the SAM accept() inside Consumer, thereby creating an object of it
    }
}

class CustomIterator implements Iterable<Integer>{

    private static final List<Integer> INTEGER_LIST = Arrays.asList(1,2,3,4,5,6,7,8,9,10,12,16,19,22,25,33,34,35,36,37,38);

    @NotNull
    @Override
    public Iterator<Integer> iterator() {
        return new PrimeIterator();
    }

    public static void main(String[] args) {
        CustomIterator ci = new CustomIterator();
        Iterator<Integer> iterator = ci.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }


    //useful to define iteration custom logic e.g. skip odd elements, only return 'verified' elements
    static class PrimeIterator implements Iterator<Integer>{
        private int cursor;
        static boolean isPrime(int i){
            for (int j = 2; j < Math.sqrt(i); j++){
                if (i%j==0) return false;
            }
            return true;
        }

        @Override
        public boolean hasNext() {
            if (cursor > INTEGER_LIST.size()) return false;
            for (int i = cursor; i < INTEGER_LIST.size(); i++){
                if (isPrime(INTEGER_LIST.get(cursor))){
                    cursor = i;
                    return true;
                }
            }
            return false;
        }

        @Override
        public Integer next() {
            if ((Integer) cursor!=null);
            return INTEGER_LIST.get(cursor++);
        }
    }





    static class CustomAccountIterator implements Iterator<CustomIterator>{
        String accountName;
        boolean verifiedEmail = false;
        private int cursor;

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public CustomIterator next() {
            return null;
        }
    }
}



/*
Iterable = Interface for a data structure that can be iterated over
provides a method that produces an iterator
using an iterable cannot give an element by index
foreach loops only work with objects implementing iterable, and also with an iterator in a while loop, and in foreach()
basically, to iterate over objects with either enhanced loops, iterator or forEach(), the object must implement iterable
 */
class IterableDemo{

    @ToString
    static class GymEquipment implements Iterable<GymEquipment>{

        private TYPE type;
        public enum TYPE {CARDIO, FREE_WEIGHT, MACHINE, ACCESSORY}
        int cost;
        double popularity;
        String name;
        static GymEquipment[] allTheGymEquipment = new GymEquipment[100];
        int equipmentIdNo;
        static int equipmentIdCounter = 0;

        public GymEquipment(String name, TYPE type, int cost, double popularity){
            this.name=name; this.type=type; this.cost=cost; this.popularity=popularity;
            equipmentIdCounter++;
            this.equipmentIdNo=equipmentIdCounter;
            allTheGymEquipment[equipmentIdNo] = this;

        }

        @NotNull
        @Override
        public Iterator<GymEquipment> iterator() {
            return new GymEquipmentIterator();
        }

        class GymEquipmentIterator implements Iterator<GymEquipment>{  //must be an inner class

            int cursor;
            int lastReturned = -1;


            @Override
            public boolean hasNext() {
                return cursor != equipmentIdCounter;
            }

            @Override
            public GymEquipment next() {
                return getNextElement();
            }

            private GymEquipment getNextElement(){
                Integer current = cursor;
                if (current==null) return null;
                cursor = current++;
                lastReturned = current;
                return allTheGymEquipment[lastReturned];
            }
        }
    }

    public static void main(String[] args) {
        GymEquipment gymEquipment1 = new GymEquipment("bench", GymEquipment.TYPE.FREE_WEIGHT, 400, 8);
        GymEquipment gymEquipment2 = new GymEquipment("powerrack", GymEquipment.TYPE.FREE_WEIGHT, 1500, 7);
        GymEquipment gymEquipment3 = new GymEquipment("treadmill", GymEquipment.TYPE.CARDIO, 2000, 7);
        GymEquipment gymEquipment4 = new GymEquipment("medicine ball", GymEquipment.TYPE.ACCESSORY, 30, 2);
        GymEquipment gymEquipment5 = new GymEquipment("kettlebell", GymEquipment.TYPE.FREE_WEIGHT, 90, 3);


        for (GymEquipment gymEquipment: GymEquipment.allTheGymEquipment){
            System.out.println(gymEquipment);
        }
    }



}


