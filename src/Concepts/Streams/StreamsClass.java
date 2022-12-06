package Concepts.Streams;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
//Streams help for data processing; supports functional programming.
// Has many inbuilt methods for data operations. 2 types;
//     Intermediate methods = can keep passing values; lazy evaluation e.g. filter, map
        //lazy = they are process the data required by later methods. e.g. findFirst() ending means map & filter will stop processing once got a suitable value to pass it
//     Terminate methods = once you can used the stream values, you cannot reuse it. e.g. findFirst, forEach
//Is a default method for Collection interface, so can be applied to any collection to convert it into an object of Stream interface
// Much more time efficient than modifying collections with for loop, as each loop must mutate the result to return every time, whereas lazy methods stop nce got the value required by a terminal method on the stream e.g. findFirst()
public class StreamsClass {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(4, 7, 2, 4, 0, 1, 5, 3, 6, 9, 6);
        Stream<Integer> intStream = integerList.stream(); // stream() returns a Stream type composed of the elements in the input collection
        //A stream can only be consumed/used once, then it auto closes, to avoid wastage and data leakage. So must decide what to do with it
        //e.g.  intStream.forEach(i -> System.out.println(i));
        //      long count = intStream.count();
        Stream<Integer> sortedIntStream = intStream.sorted();     //will sort values and return a new Stream

        //changing values of the stream will not effect the list; good as maintains immutable data for collections. unlike a for loop operating on the original list
        Stream<Integer> mappedInts = sortedIntStream.map(n -> n * 2);    //map() returns a new stream
        mappedInts.forEach(i -> System.out.println(i));

        //Condense the commands: Builder design pattern, building a new stream with every method call
        integerList.stream()   //as stream() returns a Stream, can directly call sorted() on it. then 1st stream dies as has been consumed
                .filter(n -> n<50)  //will filter out any numbers above 50. literally just the lambda version of defining filter via anonymous class as below
                .sorted()      //and sorted() also returns a stream, so can call map()
                .map(n -> n + 100)
                .map(n -> n * 3)
                .forEach(System.out::println);  //forEach is void return, so cannot save output into new variable

/*    Filter: takes a Predicate
        predicate is a functional interface with a method test() that returns boolean. which is what we filter using
              so can create object of Predicate interface via anonymous/lambda */
        //predicate is a method that returns a boolean
        Predicate<Integer> myPredicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer i) {    //SAM to override
                return i%2 == 1; //returns true for odd numbers
            }
        };

/* Map: takes a Function, which is a Functional Interface with a method apply() that returns same type as input
        Function itself takes 2 parameters
        So can manually create the object of Function interface */

        Function<Integer, Integer> myFunction = new Function<Integer, Integer>(){
            @Override
            public Integer apply(Integer i) {
                return i * i;
            }
        };


        int result = integerList.stream()   //reduce returns a stream with same type that was passed, so can save as a variable
                .filter(myPredicate)  //can pass filter an explicit object of Predicate you manually defined
                .map(myFunction)  //pass map the explicit object of Function you manually defined. map accepts object of Function interface
                .reduce(0, (a,b) -> a+b);  //takes 2 parameters; the value to start with, and an object of BinaryOperator accummulator which itself takes 2 values and operation to perform on them to combine
                System.out.println(result);       //a is the accumulated value, b is the next value in the stream



        Stream<Integer> parallelResult = integerList.parallelStream(); //will split stream across multiple threads for faster processing. java auto creates the threads
                                        //don't use if some values are dependant on earlier values

        //can use forEach + method references & lambdas on streams & parrellstreams
        List<String> iGottaList = Arrays.asList("wer", "bug", "lll", "nom", "rev");
        iGottaList.parallelStream().forEach(System.out::println);   //use: splits the operation across many threads if a giant list

        Set<Double> iGottaSet = new HashSet<>(Arrays.asList(4.5, 8.0, 3.9, 2.8, 7.1, 4.5));
        iGottaSet.parallelStream().forEach(System.out::println);


        //Manually defining BinaryOperator Interface to manually pass into reduce() to accept
        BinaryOperator<Double> bo = new BinaryOperator<Double>() {
            @Override
            public Double apply(Double double1, Double double2) {
                return double1 * double2;
            }
        };

        double setProduct = iGottaSet.stream().reduce(1.0, bo);
        System.out.println("SetProduct = " + setProduct);

        //passing inbuilt wrapper methods into stream methods, so don't have to define logic yourself
        double sumSet = iGottaSet.stream().reduce(0.0, (a,b) -> Double.sum(a,b));
        double sumSet2 = iGottaSet.stream().reduce(0.0, Double::sum);


        //findFirst = returns first value that passed the filter
        // returns an 'Optional' whereby java doesn't want to return 0 if your list doesnt contain any matches for the search. can use orElse to say what t return if list doesnt' have
        Stream<Double> filteredSet = iGottaSet.stream().filter(i -> i/2==0);
        Optional<Double> firstDouble = Optional.of(filteredSet.findFirst().orElse(0.0));   //orElse is called if findFirst doesnt find any matches
    }
}



/*
Collections store data of objects including wrappers
Processing data; changing values, filtering, reducing values
    Stream API creates a stream of the values in the collection, and operate on it
    e.g. 2x every value in a list
        map() is far more concise than for loop, and leaves original stream unmodified
    Stream interface defines many methods; map, filter, reduce
        reduce() combines all the values in the stream into 1 value

                it starts with 0, and uses an accumulator

 */


