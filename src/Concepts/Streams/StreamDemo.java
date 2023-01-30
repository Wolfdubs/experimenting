package Concepts.Streams;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utils.Pekingese;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.*;

import static org.junit.Assert.assertEquals;

/*
Streams are to process large collections/sets of objects
has methods to pipeline the data as desired
so developers can write declarative code like in SQL
Streams cannot store data, so is not a data structure -> just conveys data through a pipeline of operations
does not modify the data source, each operation just produces new streams
intermediate operations are lazy & return a new stream
    lazy evaluation = computations only occur when terminal operation is initiated, so data source elements are only consumed when required, so is optimized
    Intermediate operations only invoked if needed by a terminal operation
    e.g. if terminal is findFirst(), then the intermediates will only run until the required first has been found
terminal operations end a stream and return a result
dy default, stream operations are sequential - but can be parallelized
auto-iterate over elements; you dont have to specify this
Standard streams contain objects

 */
public class StreamDemo {
    public static void main(String[] args) {
        sortedDemo();
        filterCustomObjects();
        iteratingDemo();
        generatingSequentialList();
        mappingMethod();
        gettingMaxValue();
        countOfPredicate();
        forEachStream();
        lazyExecution();
        peekStream();


        //obtaining a stream from an array
        Pekingese[] pekingeseArray = {
                new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)};
        Stream.of(pekingeseArray);   //inbuilt stream method
        Stream.of(pekingeseArray,2,3,4,5);  //specify the indices of the array to stream
        Stream.of(5,2,8,6,9,0,9,3);
        Arrays.stream(pekingeseArray);   //inbuilt array method
        Stream.of(pekingeseArray[0], pekingeseArray[1], pekingeseArray[2]);  //create stream of individual objects

        //StreamBuilder
        Stream.Builder<Pekingese> pekingeseBuilder = Stream.builder();//via StreamBuilder
        pekingeseBuilder.accept(pekingeseArray[0]); pekingeseBuilder.accept(pekingeseArray[1]); pekingeseBuilder.accept(pekingeseArray[2]);
        Stream<Pekingese> pekingeseStream = pekingeseBuilder.build();
        Stream<String> streamBuilder = Stream.<String>builder().add("womble").add("mungo").build();


        //generate() creates an infinite stream of integers from 1, limited to 100
        //Stream.generate useful to define logic for stream elements
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Stream<Integer> integerStream = Stream.generate(() -> atomicInteger.incrementAndGet()).limit(100);
        DoubleStream doubleStream = IntStream.range(12,39).asDoubleStream();
        Stream<String> generatedStrings = Stream.generate(() -> "womble").limit(10);  //gives 10 wombles

        Stream<String> stringStream = Stream.of("womble", "mungo", "sita", "kato", "jambo")
                .skip(3)  //which index to start from, aka how many elements to skip
                .filter(element -> {
                        System.out.println("Filter just ran");
                        return element.contains("o");
                })
                .map(element -> {
                    System.out.println("map was called");
                    return element.toUpperCase();
                });
        List<String> stringList = stringStream.collect(Collectors.toList());
        System.out.println(stringList);

        Stream<Boolean> booleanStream = Stream.of(true, false, true, true, true, false, true, true, true, true, false, true, false, false, false, true);
        Optional<Boolean> options = booleanStream.findAny();   //once the stream has been consumed, it cannot be reused;
        //Optional<Boolean> lastElement = booleanStream.findFirst(); /attempt  to reuse triggers IllegalStateException

        CustomClassWithStream<Integer> ccws = new CustomClassWithStream(4,7,0);
        ccws.stream();

        Stream<String> dogs = Stream.of("womble", "mungo", "kato", "sita");


    }

    //sorted() will sort the list. can pass in a comparator, or a class that implements Comparable
    private static void sortedDemo() {
        List<Integer> list = List.of(3, 8, 7, 1, 0, 7, 9, 7, 4, 5, 1, 3, 8, 4, 7, 2);
        List<Integer> sortedList = list.stream().sorted().toList();
        System.out.println("Sorted list " + sortedList);

        List<Pekingese> pekingese = new ArrayList<>(List.of(new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)));
        pekingese.stream()
                .sorted(Comparator.comparingInt(Pekingese::getAge))
                .toList();
        pekingese.forEach(System.out::println);

        //reducing to product of all odd numbers
        Integer oddsProduct = list.stream()
                .filter(n -> n%2!=0)
                .reduce(1, (sum, n) -> sum * n);   //starting from 1, times values so far with next value. accummulator is the entire lambda expression
        System.out.println("Product of all odd numbers = " + oddsProduct);

    }

    private static void filterCustomObjects() {
        List<Pekingese> pekingese = new ArrayList<>(List.of(new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)));
        Float pekingeseWeights = pekingese.stream()
                .map(Pekingese::getWeight)
                .filter(weight -> weight > 20)
                .reduce(0.0f, Float::sum);    //returns the sum of all weights above 20. alternative BinaryOperator accumulator is lambda: (sum, element) -> sum + element
        System.out.println("The sum of all weights below 20 = " + pekingeseWeights);
    }

    private static void mappingMethod() {
        List<Pekingese> pekingese = new ArrayList<>(List.of(new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)));
        int ageSum = pekingese.stream().mapToInt(Pekingese::getAge).sum();
        System.out.println("The sum of all the pekinese ages = " + ageSum);
    }

    //iterate generates ininite values, up to the limit specified. 1st value is the seed, each subsequent value is generated based on prior value
    private static void iteratingDemo() {  //iterate will generate up to infinite values
        Set<Integer> iteratedValues = Stream.iterate(1, n -> n * 2)   //iterates values from 1, doubling each time
                .limit(20)
                .collect(Collectors.toSet());
        System.out.println("Doubling sequence from 1 to 20th number = " + iteratedValues);
    }

     private static void generatingSequentialList() {
            Object[] sqrtValues = Stream.iterate(34, n->n+1)  //iterates incremental values starting at 34
                    .map(Math::sqrt)
                    .limit(10)
                    .toArray();
         System.out.println("Sqrts of 10 increments from 34 = " + Arrays.stream(sqrtValues).toList());
    }

    private static void gettingMaxValue() {
        List<Pekingese> pekingese = new ArrayList<>(List.of(new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)));
        Pekingese heaviest = pekingese.stream().max((p1, p2) -> Float.compare(p1.getWeight(), p2.getWeight())).get();
        System.out.println("Heaviest weight = " + heaviest.getWeight());
    }

    private static void countOfPredicate() {
        List<Pekingese> pekingese = new ArrayList<>(List.of(new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)));
        Long countOfUnder12s = pekingese.stream()
                .filter(p -> p.getAge()<13)
                .count();
        System.out.println("Count of pekingese under 13 = " + countOfUnder12s);
    }

    private static void convertListToMap() {
        List<Pekingese> pekingese = new ArrayList<>(List.of(new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)));
        Map<String, Integer> pekingeseMap = pekingese.parallelStream()
                .collect(Collectors.toMap(Pekingese::getName, p->p.getAge()));
    }

    private static void forEachRemainingStream() {
        List<Pekingese> pekingese = new ArrayList<>(List.of(new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)));
        pekingese.stream().iterator().forEachRemaining(p -> p.setAge(0));
    }

    private static void iteratorStream() {
        Set<Integer> set = Stream.iterate(0, n->n+10)
                .limit(30)
                .collect(Collectors.toSet());
        System.out.println("Numbers jumping by 10 from 0, 30 times " + set);
    }

    //Specify a supplier that generates the infinite stream of elements. use limit to stop the stream actually being infinite
    private static void generateStream() {   //vs iterate(); generate accepts a supplier so each element is generated independently, unlike iterate() where each element is generated based on previous
        List<Double> randoms = Stream.generate(Math::random).limit(15).toList();

        Stream<LinkedList<Pekingese>> pekingeseLL = Stream.generate(() -> new LinkedList<>(Arrays.asList(new Pekingese("womble", 12, 10f),
                new Pekingese("mungo", 14, 8f), new Pekingese("default",1,1f), new Pekingese("default",1,1f),
                new Pekingese("default",1,1f), new Pekingese("default",1,1f), new Pekingese("default",1,1f),
                new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f))));
        long distinctPekinese = pekingeseLL.distinct()  //stream of only the unique items (removes duplicates)
                .count();

        Stream<ArrayList<String>> stringListStream = Stream.generate(() -> new ArrayList<>(List.of("womble", "mungo", "kato", "sita", "jambo", "kosie")));
    }

    private static void matchingStreams() {
        List<String> strings = Arrays.asList("womble", "mungo", "kato", "sita", "jambo", "kosie");
        Boolean containsMungo = strings.stream()
                .anyMatch(s -> s.equals("mungo"));
        boolean noneAreEmpty = strings.stream()
                .noneMatch(s -> s.equals(""));
        boolean allLessThan10Characters = strings.stream()
                .allMatch(s -> s.length()<10);
        System.out.printf("The list contains mungo = %b \nThe list does not contain any empty strings = %b \nAll strings are less than 10 chars = %b"
                ,containsMungo, noneAreEmpty, allLessThan10Characters);
    }

    private static void fileWriteStream() {
        String[] messages = {"womble is a fluffy pekingese", "kato the fat pug", "jambo was a labrador"};
        try {
            PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(Paths.get("dummyFile.txt")));
            Stream.of(messages).forEach(printWriter::println);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void fileReadStream() {
        try {
            Stream<String> stream = Files.lines(Paths.get("dummyFile.txt"));
            List<String> palindromes = stream
                    .filter(word -> word.compareToIgnoreCase(new StringBuilder(word).reverse()   //using filter to find palindromes
                    .toString())==0)
                    .toList();
            stream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        //simpler example
        try {
            Stream<String> stringStream = Files.lines(Path.of("dummyFile.txt"));
            Stream<String> streamCharset = Files.lines(Path.of("dummyFile.txt"), StandardCharsets.UTF_8);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void forEachStream() {
        List<Pekingese> pekingese = new ArrayList<>(List.of(new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)));
        pekingese.stream()
                .forEach(Pekingese::incrementAge);   //this will modify the input data source
        System.out.println(pekingese.stream().map(Pekingese::getAge).collect(Collectors.toList()));
            //collect performs multiple fold operations, repacking elements to data structures and applying extra logic (e.g. removing duplicates for sets, concatenating items)

        Optional<Pekingese> firstUnder30 = pekingese.stream()
                .filter(p -> p.getWeight()<30f)
                .findFirst();   //returns optional (which could be empty)

        //ParallelStreams to utilize multi-core processing
        pekingese.parallelStream() //parallelStream() is for Collections and Arrays
                .collect(Collectors.toList());
        IntStream intStream = IntStream.iterate(1, n -> n*n).parallel()       //parallel() used for other data sources
                .limit(10);
        boolean isParallel = intStream.isParallel();

        Vector<Integer> iteratedList = Stream.iterate(5, n->n*2)
                .skip(4)   //skips first 4 elements
                .limit(11)  //limits it to 11 elements
                .collect(Collectors.toCollection(Vector::new));

    }

    //the filter will only run for as many elements as is needed for findFirst to return, so will stop at 16 -> only run 4 times (except set is random order so not certain)
    private static void lazyExecution() {
        Set<Integer> integerSet = Stream.of(1,21,68,16,31,77,0,36,11,9,99,42,18,5,5,6,2,8,9,4,2,8)
                .collect(Collectors.toSet());
        Optional<Integer> firstSquare = integerSet.stream()
                .filter(n -> {
                    if (n <= 1) return false;
                    double squareRoot = Math.sqrt(n);
                    long precisionCorrector = (long) (squareRoot + 0.5);
                    return precisionCorrector * precisionCorrector == n;
                })
                .findFirst();
        System.out.println("The first square element = " + firstSquare);
    }

    private void collectorStream() {
        List<Pekingese> pekingese = new ArrayList<>(List.of(new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default", 1, 1f), new Pekingese("sita", 14, 45f), new Pekingese("kato", 24, 22f),
                new Pekingese("jambo", 27, 40f), new Pekingese("kosie", 7, 18f)));
        double averageAge = pekingese.stream()
                .collect(Collectors.averagingInt(Pekingese::getAge));
        double sumAge = pekingese.stream()
                .collect(Collectors.summingInt(Pekingese::getAge));  //aka mapToInt().sum()
        Map<Integer, List<Pekingese>> map = pekingese.stream()
                .collect(Collectors.groupingBy(Pekingese::getAge));

        //joining inserts the specified string between the elements of the stream
        String pekingeseNames = pekingese.stream()
                .map(Pekingese::getName)
                .collect(Collectors.joining(", and the next dog is: "));
        assertEquals(pekingeseNames, "womble, and the next dog is: mungo, and the next dog is: default, and the next dog is: sita, and the next dog is: kato, and the next dog is: jambo, and the next dog is: kosie");
    }

    //flatMap used to flatten data structures, useful when there are nested
    //converts nested lists into a flat list via flattening
    //flattening = converting lists of lists into 1 list with all elements of all lists
    //does transformation, like map, but also flattens
    //  by converting a stream of collections to a stream of objects - hence calling Collection:stream
    private void flatMapStream(){
        List<List<Integer>> nestedList = List.of(
                Arrays.asList(1,2,3,4,5,6,7),
                Arrays.asList(10,9,8,7),
                Arrays.asList(2,4,6,8,10,12,318)
        );
        List<Integer> flatMapList = nestedList.stream()
                .flatMap(Collection::stream)   //or (List::stream) / list -> list.stream()
                .toList();

        String[][] stringArray2D = new String[][]{
                {"womble", "mungo"},
                {"jambo", "sita"},
                {"kato", "kosie"}
        };
        Object[] stringArray1D = Arrays.stream(stringArray2D)
                .flatMap(Arrays::stream)
                .toArray();
    }

    //peek is like forEach, but not terminal - useful to perform specified operation on each element, and then returning a new stream
    //The toList call sends the result of each peek() to modify the input data source
    private static void peekStream(){
        List<Pekingese> pekingese = new ArrayList<>(List.of(new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)));
        pekingese.stream()
                .peek(Pekingese::incrementAge)
                .peek(p -> p.setWeight(p.getWeight()+3))
                .peek(System.out::println)
                .peek(p -> p.setName(p.getName() + "!"))
                .toList();
        System.out.println("List after using peek = ");
        Iterator<Pekingese> piterator = pekingese.iterator();
        while (piterator.hasNext()){
            System.out.println(piterator.next());
        }
    }



}




class CustomClassWithStream<T> {
    T t1,t2,t3;

    CustomClassWithStream(T t1, T t2, T t3) {
        this.t1=t1; this.t2=t2; this.t3=t3;}

    Stream<T> stream() {
        System.out.println("Calling custom stream logic");
        return Stream.of(t1,t2,t3);
    }

}



//Because Stream<T> is a generic, and primitives cannot be used with generics, so must use the primitive interface stream
//don't require auto-boxing
class PrimitiveStreams {
    public static void main(String[] args) {
        IntStream intStream = IntStream.range(1,10);  //exlusive end
        IntStream intStreamOf = IntStream.of(6,6,6);  //  Stream.of(6,6,6) returns a Stream<Integer>, not IntStream
        IntStream chars = "womble".chars();
        Stream<String> streamString = Pattern.compile(" ").splitAsStream("womble is a fluffy dog");
        LongStream longStream = LongStream.iterate(1, i -> i+1);
        DoubleStream doubleStream = new Random().doubles(6);  //6 elements in the stream

        OptionalInt intStreamReduced = IntStream.range(5,10)
                .reduce(Integer::sum);  //aka (a,b) -> a+b
        int reducedWithStartingValue = IntStream.range(5,10)
                .reduce(100, Integer::sum);   //starts at 100, accummulator is the method to use on elements

        List<Pekingese> pekingese = new ArrayList<>(List.of(new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)));
        IntSummaryStatistics summaryStatistics = pekingese.stream()
                .mapToInt(Pekingese::getAge)   //generates an IntStream
                .summaryStatistics();
    }
}

/*
Specialized streams are of doubles, integers, strings, e.g. DoubleStream. these extend BaseStream
    contains specialized operations e.g. sum, average, range
 */
class SpecializedStreams {
    public static void main(String[] args) {
        List<String> nums = List.of("3", "4", "1", "2", "9", "10", "23", "65", "6", "98", "-14", "33");
        OptionalDouble average = nums.stream()
                .mapToDouble(Double::parseDouble)
                .average();
        OptionalInt min = nums.stream()   //max and min are forms of reduce()
                .mapToInt(Integer::parseInt)
                .min();
    }
}

//https://stackify.com/streams-guide-java-8/
