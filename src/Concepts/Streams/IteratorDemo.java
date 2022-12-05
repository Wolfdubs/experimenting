package Concepts.Streams;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//to iterate over lists
//Iterator is an Interface, to create object of it, must call iterator() method from the list object
//for loops and iterators are 'External Iteration'
//Streams provide 'Internal Iteration'
public class IteratorDemo {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(5, 8, 2, 4, 1, 0, 5, 8, 6, 2);
        Iterator<Integer> myIterator = intList.iterator();  //every value from the list object are sent to the iterator object. list obj can generate an iterator
        while(myIterator.hasNext()){ //check if iterator has a next element. returns boolean
            System.out.println(myIterator.next());  //next() actually moves to the next value
        }
        intList.forEach(i -> System.out.println(i));  //forEach requires object of Consumer functional interface, so can use lambda/method reference to
        intList.forEach(System.out::println);   // to define the implementation for the SAM accept() inside Consumer, thereby creating an object of it
    }
}
