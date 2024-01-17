package Concepts.Streams;

import Concepts.Comparable_Comparator_Iterator.CarComparable;
import utils.Pekingese;
import utils.Weapon;

import javax.management.InstanceNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.*;

import static java.util.Comparator.comparing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static utils.Weapon.generateWeaponsList;

/*
Streams are to process large collections/sets of objects -> wrappers for the data passed into them, permitting data processing
has methods to pipeline the data as desired
so developers can write declarative code like in SQL
Streams cannot store data, so is not a data structure -> just conveys data through a pipeline of operations
does not modify the data source, each operation just produces new streams
2 Operation types:
    intermediate operations are lazy & return a new stream. can chain them
        lazy evaluation = computations only occur when terminal operation is initiated, so data source elements are only consumed when required, so is optimized
        Intermediate operations only invoked if needed by a terminal operation
        e.g. if terminal is findFirst(), then the intermediates will only run until the required first has been found
    terminal operations end a stream and return a result
dy default, stream operations are sequential - but can be parallelized
auto-iterate over elements; you dont have to specify this
Standard streams contain objects
Nothing to do with I/O streams e.g. FileInputStream
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

        //creating stream from scratch
        Stream<Integer> integerStream = Stream.of(5,2,8,9,8,9,5,8,3);  //for primitives its better to use the specialized Stream class e.g. IntStream
        IntStream intystreamy = IntStream.of(4,6,3,8,9,0,2,7,3,1);
            //Stream.of() just calls Arrays.stream() for non-primitive types
        Stream<Integer> streamedIntegers = new ArrayList<>(List.of(6,2,7,6,8,1,2,9,5,1)).stream();
        //Array.stream() vs Stream.of()
            //1. for primitive arrays, Array.stream() returns an Int/Double/LongStream, while Stream.of() returns a Stream of the array
            //2. hence Stream.of() with primitive must be flattened into a stream of the actual primitives of the array before consuming it
            //Stream.of() is generic - can be used for any type of array, Array.stream() is not (cannot be used for primitives except int, double, long)
                //so Array.stream() only works for primitive arrays of int, double, long as it returns IntStream, DoubleStream, LongStream
                    //it still works fine for object arrays
            int[] myInts = {4,2,5,1,7,4,9,0,2,7,4,8};
            IntStream arrayIntStream = Arrays.stream(myInts);
            Stream<int[]> arrayStream = Stream.of(myInts);
            IntStream flattenedArrayStream = arrayStream.flatMapToInt(Arrays::stream);

            Weapon[] weapons = generateWeaponsList().toArray(Weapon[]::new);
            Stream<Weapon> weaponStream = Arrays.stream(weapons);  //Arrays.stream still works for object arrays,

            char[] myChars = {'d','o','n','i','r','e','v','s'};   //Arrays.stream() fails for char[]
            //Arrays.stream(myChars);  //compile error; Arrays.stream() only works for ints, double, long
            Stream<char[]> characterArrayStream = Stream.of(myChars);   //Stream.of() works for char[], but must be flattened to character stream
            Stream<String> stringStreamFromCharArray = characterArrayStream.flatMap(array -> Stream.of(Arrays.toString(array)));
              //converting char[] stream to character stream
            Stream<Character> characterStream = new String(myChars).chars().mapToObj(i -> (char) i);  //String.chars() returns an IntStream, each element of which is cast to a char



        //creating stream from an array
        Pekingese[] pekingeseArray = {
                new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)};
        List<Pekingese> pekingeseList = Arrays.asList(pekingeseArray);
        Stream.of(pekingeseArray);   //inbuilt stream method
        Stream.of(pekingeseArray,2,3,4,5);  //specify the indices of the array to stream
        Stream.of(5,2,8,6,9,0,9,3);
        Stream<Pekingese> pekingeseStreamArray = Arrays.stream(pekingeseArray);   //inbuilt array method
        Stream.of(pekingeseArray[0], pekingeseArray[1], pekingeseArray[2]);  //create stream of individual objects
        Stream<Pekingese> pekingeseStreamList = pekingeseList.stream();

        //StreamBuilder
        Stream.Builder<Pekingese> pekingeseBuilder = Stream.builder();//via StreamBuilder
        pekingeseBuilder.accept(pekingeseArray[0]); pekingeseBuilder.accept(pekingeseArray[1]); pekingeseBuilder.accept(pekingeseArray[2]);
        Stream<Pekingese> pekingeseStream = pekingeseBuilder.build();
        Stream<String> streamBuilder = Stream.<String>builder().add("womble").add("mungo").build();



        //generate() creates an infinite stream
        //Stream.generate useful to define logic for stream elements
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Stream<Integer> integerStreamGenerated = Stream.generate(atomicInteger::incrementAndGet).limit(100);
        DoubleStream doubleStream = IntStream.range(12,39).asDoubleStream();
        Stream<String> generatedStrings = Stream.generate(() -> "womble").limit(10);  //gives 10 wombles
        List<Double> randomNumbers = Stream.generate(Math::random).limit(10).toList();

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
        Optional<Boolean> options = booleanStream.findAny();   //once the stream has been consumed, it cannot be reused; findAny retrieves an element regardless of its position
        //Optional<Boolean> lastElement = booleanStream.findFirst(); /attempt  to reuse triggers IllegalStateException

        CustomClassWithStream<Integer> ccws = new CustomClassWithStream<>(4,7,0);
        ccws.stream();

        Stream<String> dogs = Stream.of("womble", "mungo", "kato", "sita");


    }

    //sorted() will sort the list. can pass in a comparator, or a class that implements Comparable
        //or call Comparator.comparing and specify the comparison
        //can chain with multiple thenComparing()
    private static void sortedDemo() {
        List<Integer> list = List.of(3, 8, 7, 1, 0, 7, 9, 7, 4, 5, 1, 3, 8, 4, 7, 2);
        List<Integer> sortedList = list.stream().sorted().toList();
        list.stream().sorted((a,b) -> Integer.compare(a,b));
        list.stream().sorted(Integer::compare);
        list.stream().sorted((o1, o2) -> {   //passing in custom comparator via lambda
            return (o1 % o2 != 0) ?  -1 : (o1 % o2 == 0) ? 0 : 1;
        });
        list.stream().sorted(Comparator.comparing(Math::abs));
        List<CarComparable> carComparables = Arrays.asList(new CarComparable("Ford",15000, 100),
                new CarComparable("Tesla", 40000,130), new CarComparable("Chrysler",30000, 110),
                new CarComparable("Rolls Royce",300000,120), new CarComparable("Prius", 20000,90));
        List<CarComparable> sortedCarsPrice = carComparables.stream().sorted().toList();  //uses the comparable-implementing classes own compareTo()
        List<CarComparable> sortedCarsSpeed = carComparables.stream().sorted(Comparator.comparingInt(CarComparable::getMaxSpeed)).toList();
        System.out.println("Sorted cars by price= " + sortedCarsPrice);
        System.out.println("Sorted cars by speed= " + sortedCarsSpeed);
        System.out.println("Sorted list = " + sortedList);


        List<Pekingese> pekingese = new ArrayList<>(List.of(new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)));
        List<Pekingese> sortedPekingese = pekingese.stream()
                .sorted(Comparator.comparingInt(Pekingese::getAge))
                .toList();
        sortedPekingese.forEach(System.out::println);

        Stream<Weapon> sortedWeapons = generateWeaponsList().stream()
                .sorted(Comparator.comparing(Weapon::getDifficulty)  //pass in the comparison metric to comparing()
                        .thenComparing(Weapon::getName));        //chaining comparisons to sort by

        //reducing to product of all odd numbers
        Integer oddsProduct = list.stream()
                .filter(n -> n % 2 != 0)
                .reduce(1, (sum, n) -> sum * n);   //starting from 1, times values so far with next value. accummulator is the entire lambda expression
        System.out.println("Product of all odd numbers = " + oddsProduct);

    }

    private static void filterCustomObjects() {
        List<Pekingese> pekingese = new ArrayList<>(List.of(new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)));
        Float pekingeseWeights = pekingese.stream()
                .map(Pekingese::getWeight)   //map returns a new stream after applying a function to each element
                .filter(weight -> weight > 20)
                .reduce(0.0f, Float::sum);    //returns the sum of all weights above 20. alternative BinaryOperator accumulator is lambda: (sum, element) -> sum + element
        System.out.println("The sum of all weights above 20 = " + pekingeseWeights);
    }

    private static void mappingMethod() {
        List<Pekingese> pekingese = new ArrayList<>(List.of(new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)));
        int ageSum = pekingese.stream().mapToInt(Pekingese::getAge).sum();
        Optional<Integer> ageSumOptional = pekingese.stream().map(p -> Math.round(p.getWeight())).reduce(Integer::sum);
        int ageSum2 = ageSumOptional.isPresent() ? ageSumOptional.get() : -1;
        int ageSum3 = ageSumOptional.orElse(-1);
        System.out.println("The sum of all the pekinese ages = " + ageSum);
    }

    //iterate generates infinite values, up to the limit specified. 1st value is the seed, each subsequent value is generated based on prior value
        //takes a seed and operator, which feeds the seed
    private static void iteratingDemo() {  //iterate will generate up to infinite values
        Set<Integer> iteratedValues = Stream.iterate(1, n -> n * 2)   //iterates values from 1, doubling each time
                .limit(20)
                .collect(Collectors.toSet());
        System.out.println("Doubling sequence from 1 to 20th number = " + iteratedValues);

        int[] oddNumbers = Stream.iterate(1, n -> n+2).limit(15).mapToInt(Integer::intValue).toArray();
        Integer[] oddNumbers2 = Stream.iterate(1, i -> i+2).limit(15).toArray(Integer[]::new);
        System.out.println("odd numbers = " + Arrays.toString(oddNumbers));

        Stream<Integer> forloopedIteration = Stream.iterate(1, i -> i < 20, i -> i * i);
    }

     private static void generatingSequentialList() {
            Double[] sqrtValues = Stream.iterate(34, n->n+1)  //iterates incremental values starting at 34
                    .map(Math::sqrt)
                    .limit(10)
                    .toArray(Double[]::new);  //toArray by default returns Object[] unless otherwise specified
         System.out.println("Sqrts of 10 increments from 34 = " + Arrays.stream(sqrtValues).toList());
    }

    private static void gettingMaxValue() {
        List<Pekingese> pekingese = new ArrayList<>(List.of(new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)));
        Pekingese heaviest = pekingese.stream().max((p1, p2) -> Float.compare(p1.getWeight(), p2.getWeight())).get();
        System.out.println("Heaviest weight = " + heaviest.getWeight());
    }

    private static Optional<Weapon> gettingMinValue() throws InstanceNotFoundException {  //most terminal operations return Optionals
        Optional<Weapon> minLethality = Optional.ofNullable(generateWeaponsList().stream().min(comparing(Weapon::getLethality))
                .orElseThrow(InstanceNotFoundException::new));
        return minLethality;
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
        long distinctPekingese = pekingeseLL.distinct()  //stream of only the unique items (removes duplicates)
                .count();

        Stream<ArrayList<String>> stringListStream = Stream.generate(() -> new ArrayList<>(List.of("womble", "mungo", "kato", "sita", "jambo", "kosie")));
    }

    private static void matchingStreams() {  //input = predicate, checks that predicate against elements of the stream
        List<String> strings = Arrays.asList("womble", "mungo", "kato", "sita", "jambo", "kosie");
        Boolean containsMungo = strings.stream().limit(5)   //will limit stream to the first 5 elements
                .anyMatch(s -> s.equals("mungo"));   //returns true if the predicate is true for any element
        boolean noneAreEmpty = strings.stream()
                .noneMatch(s -> s.equals(""));   //returns true if the predicate is false for all elements
        boolean allLessThan10Characters = strings.stream()
                .allMatch(s -> s.length()<10);  //returns true if all the elements meet the predicate
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
                        .toString()) == 0)
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

    //Collection.forEach uses the objects iterator, whereas stream.forEach takes elements from collection 1-by-1, ignoring iterator
    //Collections should not be structurally modified during iteration -> throws ConcurrentModificationException
        // but java explicitly allows iterator to modify elements, whereas stream forEach should be non-modifying
    private static void forEachStream() {
        List<Pekingese> pekingese = new ArrayList<>(List.of(new Pekingese("womble", 12, 10f), new Pekingese("mungo", 14, 8f),
                new Pekingese("default",1,1f), new Pekingese("sita",14,45f), new Pekingese("kato",24,22f),
                new Pekingese("jambo",27,40f), new Pekingese("kosie",7,18f)));
        pekingese.forEach(Pekingese::incrementAge);   //this will modify the input data source
        System.out.println(pekingese.stream().map(Pekingese::getAge).collect(Collectors.toList()));
            //collect performs multiple fold operations, repacking elements to data structures and applying extra logic (e.g. removing duplicates for sets, concatenating items)
                //puts elements into a collection; list, set, vector, LL, map

        Optional<Pekingese> firstUnder30 = pekingese.stream()
                .filter(p -> p.getWeight()<30f)
                .findFirst();   //returns optional (which could be empty)

        //ParallelStreams to utilize multi-core processing - order of iteration is random, e.g. list iteration
        pekingese.parallelStream() //parallelStream() is for Collections and Arrays
                .collect(Collectors.toList());
        IntStream intStream = IntStream.iterate(1, n -> n*n).parallel()       //parallel() used for other data sources
                .limit(10);
        boolean isParallel = intStream.isParallel();


        intStream.parallel().forEach(System.out::println);       //execute stream operations in parallel, order not guaranteed

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
                    long precisionCorrector = (long) (squareRoot + 0.5); //long casting truncates decimals
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

        //joining = joins a list of objects into a single string
            // optional delimiter inserts the specified string between the elements of the stream
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
        String[] stringArray1D = Arrays.stream(stringArray2D)
                .flatMap(Arrays::stream)
                .toArray(String[]::new);   //pass in array initializer to make array return a specific type
    }

    //peek is like forEach, but not terminal - useful to perform specified operation on each element, and then returning a new stream
    //The toList call sends the result of each peek() to modify the input data source
    //advised to only use for logging, not actual operations
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

    private static void reduceStrings(){
        List<String> weaponNames = generateWeaponsList().stream()
                .map(Weapon::getName)
                .toList();
        String concantenatedStrings = weaponNames.stream()
                .reduce("", (partialString, element) -> partialString + element);
     }

     //divides stream into 2 categories and puts into a map; 1 stream has key 'true', 1 stream has key 'false'
     private static void partitioningByDemo(){
        Stream<Weapon> weaponStream = generateWeaponsList().stream();
        Map<Boolean, List<Weapon>> partitionMap = weaponStream.collect(Collectors.partitioningBy(weapon -> weapon.getDifficulty()>5));
        List<Weapon> difficultWeapons = partitionMap.get(true);

        Map<Boolean, List<Weapon>> alphabeticallyTop = weaponStream.collect(Collectors.partitioningBy(weapon -> weapon.getName().charAt(0) < 'm'));
        List<Weapon> alphabeticallyLow = alphabeticallyTop.get(false);

         Map<Boolean, List<Weapon>> partitionMap2 = weaponStream.collect(Collectors.partitioningBy(weapon -> weapon.getTYPE().equals(Weapon.TYPE.GASPOWERED)));

     }

     private static void groupingByDemo(){   //to group elements by more than 2 categories
        Stream<Weapon> weaponStream = generateWeaponsList().stream();
        Map<Weapon.TYPE, List<Weapon>> groupedMap = weaponStream.collect(Collectors.groupingBy(Weapon::getTYPE));

        Map<Weapon.TYPE, List<Integer>> typeToReliabilityMap = weaponStream.collect(
                Collectors.groupingBy(
                        Weapon::getTYPE,
                        Collectors.mapping(Weapon::getSelfSustainability, Collectors.toList())  //use with mapping to map 2 categories (fields) against each other
                ));
    }

    //similar to filter;
        //takeWhile will accept elements until the predicate is false, and then it totally drops the remaining list elements, aka breaks
        //dropWhile drops elements as long as the condition is true, and when hits false it returns the remaining items
    private void TakeAndDropWhile(){
        Stream<Integer> integerStream = Stream.of(25,15,75,40,5,10,55,60);
        List<Integer> takeWhile = integerStream.takeWhile(number -> number < 30).toList(); //returns 25, 15
        List<Integer> dropWhile = integerStream.dropWhile(number -> number < 30).toList();  //returns 75,40,5,10,55,60



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
        IntStream intStream = IntStream.range(1,10);  //exclusive end
        IntStream rangedIntStream = IntStream.rangeClosed(1,10);   //inclusive end
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
        summaryStatistics.getAverage(); summaryStatistics.getSum(); summaryStatistics.getCount(); summaryStatistics.getMin(); summaryStatistics.getMax();
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
        OptionalInt min = nums.stream()   //max and min and average and sum and count are forms of reduce()
                .mapToInt(Integer::parseInt)
                .min();

        Stream<Weapon> weaponStream = generateWeaponsList().stream();
        IntSummaryStatistics intSummaryStatistics = weaponStream.collect(Collectors.summarizingInt(Weapon::getDifficulty));
        IntSummaryStatistics stats = weaponStream.mapToInt(Weapon::getLethality).
                                        collect(IntSummaryStatistics::new,
                                        IntSummaryStatistics::accept,
                                        IntSummaryStatistics::combine);
    }
}

class StreamReduction {
    /*3 components:
        Identity – an element that is the initial value of the reduction operation and the default result if the stream is empty
        Accumulator – a function that takes two parameters: a partial result of the reduction operation and the next element of the stream
        Combiner – a function used to combine the partial result of the reduction operation when the reduction is parallelized or when
            there’s a mismatch between the types of the accumulator arguments and the types of the accumulator implementation
    */

    public static void main(String[] args) {
        List<Integer> integerList = List.of(1,2,3,4,5,6);
        int result = integerList
                .stream()
                .reduce(0, (identity, element) -> identity + element);

        int resultMR = integerList
                .stream()
                .reduce(0, Integer::sum);

        List<String> stringList = Arrays.asList("a","b","c","d","e");
        String combined = stringList
                .stream()
                .reduce("", String::concat);

        String upperCombined = stringList
                .stream()
                .reduce("", (identity, character) -> identity.toUpperCase() + character.toUpperCase());

        //using parallel stream
        int resultParallel = integerList
                .parallelStream()
                .reduce(0, (a,b) -> a+b, Integer::sum);
        /*When a stream executes in parallel, the Java runtime splits the stream into multiple substreams.
        // In such cases, we need to use a function to combine the results of the substreams into a single one.
        // This is the role of the combiner — in the above snippet, it’s the Integer::sum method reference.

        When we use parallelized streams, we should make sure that reduce() or any other aggregate operations executed on the streams are:
        associative: the result is not affected by the order of the operands
        non-interfering: the operation doesn’t affect the data source
        stateless and deterministic: the operation doesn’t have state and produces the same output for a given input*/

        final List<Weapon> weapons = generateWeaponsList();
        int netLethalityResult = weapons.parallelStream()
                .reduce(0, (partialLethalityResult, weapon) -> partialLethalityResult + weapon.getLethality(), Integer::sum);


        //handling errors while reducing
        class ErrorHandlingInReducing{
            public static int divideListElements(List<Integer> integers, int divider){   //this pollutes the clean lambda with all the try/catch block
                return integers.stream()
                    .reduce(0, (a,b) -> {
                        try {
                            return a/divider + b/divider;
                        } catch (ArithmeticException ae){
                            Logger.getAnonymousLogger().config("Divide by zero error");
                        }
                        return -1;
                });
            }

            public static int divideListElementsClean(List<Integer> integers, int divider){
                return integers.stream()
                        .reduce(0, (a,b) -> divide(a, divider) + divide(b,divider));
            }

            private static int divide(int myInt, int divider){
                int result = 0;
                try {
                    result =  myInt/divider;
                } catch (ArithmeticException ae){
                    Logger.getAnonymousLogger().config("divide by zero error");
                }
                return result;
            }



        }

    }
}

//https://stackify.com/streams-guide-java-8/
