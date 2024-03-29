package Concepts.Basics;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static java.util.Map.copyOf;
import static java.util.Map.entry;

//Static import: to be able to call predefined methods without needing to qualify owning class (whereas regular import just means don't need to specify package)
import static java.lang.Math.*;
import static java.lang.System.*;
import static org.junit.Assert.assertFalse;

class StaticImports {
    public static void main(String[] args) {
        out.println(sqrt(5)); //can directly call the members of the System class due to static import
        out.println(pow(3.1,7.8));   //dont need to specify Math.pow due to static import.
    }
}
public class AboutJava {


}



class RandomLearnings {
    public static void main(String[] args) {
        System.out.println("apple".compareTo("banana"));  //returns -1 if not equal
        boolean isString = "nifty" instanceof String;

        List<Boolean> list = new ArrayList<>();
        list.add(true);
        list.add(Boolean.parseBoolean("FalSe"));
        list.add(Boolean.TRUE);
        System.out.print(list.size());
        System.out.print(list.get(1) instanceof Boolean);

        String s = null;
        System.gc();  // Force object to be garbage collected via setting it to null and calling System.gc()



        String message = "womble is a furry pekingese dog";
        System.out.println(message.split(" ")[4]);   //prints pekingese, as split() returns an []
        out.println(message.substring(1,3) + message.substring(8,10));

        message.getClass().getSimpleName();   //using Reflection API
        message.getClass().getDeclaredMethods();

        Map<String,Integer> myMap = new HashMap<>() {   //create map with values
            {
                put("womble",8);
                put("mungo",0);
                put("sita",6);
                put("kosie",1);
            }
        };
        System.out.println(myMap.size());

        //standard input/output streams provided by System
        out.println();
        err.println();
        in.toString();

        for (int i = 0; i < 10; i=i++) {  //prints 10 times as the value of i=i++ is never used
            i+=1;
            out.println("womble");
        }





    }

}




//Interface static method
    //contain full method implementation; cannot be overridden by implementor
    //similar to Default keyword for interface methods (but default can be overridden), and don't have to create class object, just use reference to method
class InterfaceLearnings {
    interface myInterface {
        static void print() {
            System.out.println("Running static interface method");
        }

        default void printDefault() {   //requires an instantiated object, as is not static
            System.out.println("running default interface method");
        }
    }

    class interfaceImplementor implements myInterface {
        public static void main(String[] args) {
            myInterface.print();  //calling the interface's static method to execute its implementation. doesn't have to be done in an implementing class
            Consumer<interfaceImplementor> printDefault = myInterface::printDefault;
        }

        public void printDefault() {
            myInterface.super.printDefault();   //how to call default methods of interface from an implementing class
        }
    }
}

class ListLearning {

    //Arraylist is a generic class implementation of List interface using array as data structure
        //default length = 10 null values
        //fast reads due to random access in arrays to access directly, so get() is O(1)
    //LinkedList have no initial sizing; starts empty and only adds space for each new element
        //doesn't require copying into a new list to grow, and don't have to shift elements rightward when adding at start(unlike arraylist)
        //consist of nodes that store data and pointer to next node that links all the nodes across the LL
        //get requires traversal of list

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("hello");
        list.add(2);
        System.out.println(list.get(0) instanceof Object);
        System.out.println(list.get(1) instanceof Integer);


        //Ways to alphabetically sort an arraylist
        List<String> stringList = Arrays.asList("womble","mungo","tuco","jambo","sita","kato","kosie");
        stringList.sort(Comparator.comparing(String::toString));
        Collections.sort(stringList);
        stringList.stream().sorted((s1, s2) -> s1.compareTo(s2)).collect(Collectors.toList());
        stringList.stream().sorted(String::compareTo).collect(Collectors.toList());


        //sorting a list with strings that are also numbers
        String[] arrayStrings = {"Hello", "100", "23", "yodel","0","5"};
        List<String> listStrings = Arrays.asList(arrayStrings);
        Collections.sort(listStrings);   //operations on this are fed directly back to original input array
        System.out.println(Arrays.toString(arrayStrings));  //will print sorted order, as changes to the list are fed back to the original array

        //iterating through a list
        for (int i = 0; i < listStrings.size(); i++) {
            System.out.println(listStrings.get(i));
        }
        for (Object object : listStrings) {
            System.out.println(object);
        }
        listStrings.forEach(System.out::println);

        Iterator<String> it = listStrings.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }


        //directly creating a list
        List<String> listAs = Arrays.asList("womble","mungo","sita");    //just a list wrapper over an array; no copying occurs
                //permits nulls. is unmodifiable as it returns fixed size list, but can overwrite values
        //listAs.add("pekingese");  this throws UnsupportedOperationException
        listAs.set(1,"pet");  //can overwrite values though, as it is only unmodifiable as far as fixed size. this reflects back to input array
        String[] stringArray = {"bloo","blah","bluuuu"};
        List<String> listAs2 = Arrays.asList(stringArray);   //returns a view of the passed array (it just wraps the input array with the List
                                    //interface), so changes to the input array are reflected in the list.
        List<String> listOf = List.of("jambo","kosie","kato");    //returns an immutable list that cannot be modified ->
                                // UnsupportedOperationException. does not permit nulls. changes to input array are not reflected in this list
        List<String> dogList = new ArrayList<>(List.of("womble","mungo","sita","jambo","kato")); //makes a mutable list, despite using List.of()
        List<String> listAsModifiable = new ArrayList<>(Arrays.asList("pekingese","shitzu","labrador")); //this list is modifiable, as is a standard AL
                    //creates an independent copy of the array input, so modifications are not relfected in input array
        listAsModifiable.add("boo dog");

        //list equality
        String[] strings = new String[]{"A", "B", "C"};
        List<String> list1 = Arrays.asList(strings);
        List<String> list2 = new ArrayList<>(Arrays.asList(strings));
        List<String> list3 = new ArrayList<>(Arrays.asList("A", new String("B"), "C"));
        System.out.println(list1.equals(list2));
        System.out.println(list1.equals(list3));  //both return true, as list equality checks for value equality


        //create list with values already
        List<String> stringsList = new ArrayList<>(Arrays.asList("one","two","three"));

        class Employee{
            String name;
            Employee(String name) {this.name=name;}
            String getName(){return name;}
        }
        List<Employee> employees = new ArrayList<>(Arrays.asList(new Employee("womble"), new Employee("kato"), new Employee("jambo")));
        List<String> employeeNames = employees.stream().map(Employee::getName).toList();   //to print a specific member value of a list of a class
        System.out.println(employeeNames);

        //modifying a collection while iterating through it can throw a ConcurrentModificationException
        List<String> letters = new ArrayList<>(Arrays.asList("a","b","c","d"));
        for (String letter : letters) {
            if (letter.equals("c")) {
                letters.remove(letter);
            }
        }
        out.println(letters);

        //So how to modify a list during a loop:


        List<String> strings1 = new ArrayList<>();
        strings1.add("One"); strings1.add("Two"); strings1.add("Three");
        List<String> strings2 = new ArrayList<>();
        strings2.add("Two");
        strings1.remove(strings2);
        out.println(strings1);   //prints One Two Three


        //can have lists of different objects
        List multiList = new ArrayList<>();
        multiList.add(Integer.valueOf(1));
        multiList.add(true);
        multiList.add(String.valueOf("womble"));

        //to synchronize an existing arraylist (not synchronized by default)
        multiList = Collections.synchronizedList(multiList);
        synchronized (multiList) { //iterator should now be in a synchronized block
            Iterator iterator = multiList.iterator();
            while (iterator.hasNext()) out.println(iterator.next());
        }
        //create a synchronized list
        CopyOnWriteArrayList<String> synchronizedList = new CopyOnWriteArrayList<>();
        //don't need a synchronized block to iterate


    }


}


class ClassFinalMethod {
     class ClassParent {
         final String message(){    //final method cannot be overridden by child class
             return "howdy";
         }
     }

     class ClassChild extends ClassParent {
         //final String message(){return "hello";}   //this will throw compile error as cannot override final method
     }

    public static void main(String[] args) {
         ClassFinalMethod cfm = new ClassFinalMethod();
         ClassChild cc = cfm.new ClassChild();
        out.println(cc.message());
    }
}

class Dates {
    //Several ways to instantiate the current date
    public static void main(String[] args) {
        new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        new Date(System.currentTimeMillis());
        LocalDate.now();   //this is the most up to date way
        Calendar.getInstance().getTime();
    }
}

//constructors do not define a return value, and can be called with this or super. they cannot be static
class ChainingConstructors {
    ChainingConstructors(String s, int i){
        this("hi",1,true);     //to call the other constructor
    }
    ChainingConstructors(String s, int i, boolean b) {}
}

class ClassRelations {
    Object message() {
        return "Hello";
    }

    public static void main(String[] args) {
        System.out.print(new ClassRelations().message());   //direct call to super's implementation
        System.out.print(new ClassRelationsChild().message());  //will override message()

    }
}
class ClassRelationsChild extends ClassRelations {
    Object message() {
        return " World";
    }
}

//Error = serious issue thrown by JVM it cannot recover from
//Exception = unexpected event an application could manage and continue execution after
//will not compile if you add Exception catches beneath catches of super class Exceptions
class ErrorVsException {
    public static void main(String[] args) {   //prints out AD, because error is not inherited from Exception
        try {
            System.out.println("A");
            errorThrown();
            System.out.println("B");
        } catch (Exception e) {
            System.out.println("C");
      /*  } catch (ArithmeticException ae){    //this causes compile error, as it is already caught by Exception block
            out.println("this will never be reached");*/
        }
        finally {      //note: if an exception is thrown by both the finally & try block, the method returns the exception from finally
            System.out.println("D");
        }
    }
    private static void errorThrown(){
        throw new Error();
    }
}

class RecursiveCalls {
    static int count = 0;

    public static void main(String[] args) {
        if (count < 3) {
            count++;
            out.println("incremented counter");
            main(null);
        } else {
            out.println("about to return");
            return;
        }
        System.out.println("Hello world");  //this will print 3 times
    }
}

class KeywordsLearning {
    private final int x = 5;   //final variables must be assigned either in declaration, constructor, or instance initializer block
    private final int y;
    private final int z;

    public KeywordsLearning(int y){
        this.y = y;
    }

    {                     //instance initializer block runs each time an object is created,
        z = 10;           //as its code is added to the constructor by java and called after super()
    }                     //useful if have many constructors, all with shared otherwise duplicated code
                          // betters than variable initialization if require exception handling
                          //also for anonymous classes, which cannot contain constructors

    private native String runNativeCode();//part of JNI (Java Native Interface). to access methods implemented in other languages
        //native method has implementation in e.g. C/C++
        //cannot have a body; provide implementation in a static block
        //only for methods
        //native means method is implemented in platform dependent code e.g. C
        //Why?
            //improved performance
            //use existing non-Java code
            //achieve machine/memory level communication
    static {  //STATIC BLOCK = thread safe block, unlike constructor that must use a synchronized block to check initialization status
        System.loadLibrary("nativeCodeDLLFileToBeLoaded");  //calls the dynamically loaded library and sends results back to java
        }

}



//if importing 2 classes with same method, and then calling it, java will call the method from the mre specific import
// e.g. where the specific class is references, not just * (which is a Type-Import-On-Demand

class Blocks {

}


class AnonymousObjectCreation {
    private static final Map<String,String> map = new HashMap<String,String>() {   //anonymous population of a map using an instance block
        {
            put("womble","pekingese");
            put("sita","labrador");
            put("jambo");
            put("kosie");
        }
        void put(String code){      //can create a method to simplify block methods too
            put(code.substring(0,1),code);
        }
    };
    private static final Map<String,String> unmodifiableMap = Collections.unmodifiableMap(new HashMap<>() {
        {
            put("jambo", "labrador");
            put("kato", "pug");
        }
    });





    public static void main(String[] args) {
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action has been performed");
            }
        });

        //lambda version
        button.addActionListener((event) -> System.out.println("Action has been performed"));



    }


}

class SwapMethods {
    public static void main(String[] args) {
        int x = 5, y = 10;
        ineffectiveSwap(x,y);   //this will not actually swap the inputs, as the method doesn't return the swapped values back
        System.out.println(x + ", " + y);   //so this prints 5,10
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));
        Collections.swap(list, 1,2);
        System.out.println(list);
        swapListIndices(list, 5,6);
        System.out.println(list);

        String string = "abcdefg";
        String swappedString = swapStringChars(string,1,2);
        swappedString = swapStringBuilderChars(swappedString, 5,6);
        System.out.println(swappedString);


    }
    private static void ineffectiveSwap(int a, int b){
        int temp = a;
        a = b;
        b = temp;
    }

    private static List<Integer> swapListIndices(List<Integer> list, int index1, int index2) {
        int tempIndex = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, tempIndex);
        return list;
    }

    private static String swapStringBuilderChars(String s, int a, int b) {
        StringBuilder stringBuilder = new StringBuilder(s);
        char temp = s.charAt(a);
        stringBuilder.setCharAt(a,s.charAt(b));
        stringBuilder.setCharAt(b, temp);
        return stringBuilder.toString();
    }

    private static String swapStringChars(String s, int a, int b) {
        char[] chars = s.toCharArray();
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
        return Arrays.toString(chars);
    }



}

class ToString {
    private String name;
    ToString(String name){
        this.name = name;
    }
    public String toString(){
        return name;
    }

    public static void main(String[] args) {
        System.out.println(new ToString("myString"));   //without to toString, this would just print the location in memory
    }
}
//PriorityQueue = processes objects based on priority heap -> ordered by natural ordering (unless alternate Comparator provided)
    //doesn't permit null
    //objects must be comparable
    //head of queue is least element
    //non thread-safe; thread safe version = PriorityBlockingQueue
    //O(log(n))
class MyPriorityQueue {
    static PriorityQueue<Integer> queue = new PriorityQueue<>();
    static Comparator<String> comparator = (String o1, String o2) -> {
            if (o1.equals(o2)) return 0;
            return (o1.charAt(o1.length()-1) > o2.charAt(o2.length()-1) ? 1 : -1);   //ascending order
        };
    static Queue<String> queueStrings = new PriorityQueue<>(comparator);   //priority assigned by the comparator; last char in the string

    public static void main(String[] args) {
        queue.add(4); queue.add(5); queue.add(2); queue.add(1); queue.add(10); queue.add(23); queue.add(3);
        out.println(queue.peek());   //will print 1
        while (!queue.isEmpty()) {
            System.out.printf("%d", queue.poll());   //prints 1234
        }

        queueStrings.add("womble"); queueStrings.add("mungo"); queueStrings.add("kato"); queueStrings.add("sita"); queueStrings.add("kosie"); queueStrings.add("jambo"); queueStrings.add("Dog");
        Iterator<String> iterator = queueStrings.iterator();
        while (iterator.hasNext()) out.print(iterator.next() + " ");
        out.println();
        while (!queueStrings.isEmpty()) {   //alt way to iterate the list
            out.println(queueStrings.remove());
        }
        for (String s : queueStrings) {   //another alt way to traverse list
            out.println(s);
        }

        Queue<String> customComparatorClassQueue = new PriorityQueue<>(new CustomComparatorClass());  //custom comparator class to apply to contents
        customComparatorClassQueue.add("womble"); customComparatorClassQueue.add("kosie"); customComparatorClassQueue.add("pekingese");
        customComparatorClassQueue.add("kato"); customComparatorClassQueue.add("mungo"); customComparatorClassQueue.add("si");
        Iterator<String> comparingIt = customComparatorClassQueue.iterator();
        while (comparingIt.hasNext()) out.print(comparingIt.next() + " ");
    }

    static class CustomComparatorClass implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if (o1.length()==o2.length()) return 0;
            return o1.length() > o2.length() ? 1 : -1;
        }
    }
}

enum Direction {
    EAST("E"),
    WEST("W"),
    NORTH("N"),
    SOUTH("S");

    private final String shortCode;
    private int inty;

    Direction(String shortCode){   //because the enum expects the 1 string argument
        this.shortCode=shortCode;
        inty=6;
    }

    public String getShortCode(){
        return shortCode;
    }
}

class UnmodifiableMaps {

    public static void main(String[] args) {


        //below map is perfectly modifiable
        Map<Double, Character> doubleCharacterMap = new HashMap<>() {{
            put(3.4, 'b');
            put(9.0, 'v');
            put(1.11111, '6');
        }};

        //unmodifiableMap = returns read-only view of the map, so it cannot be modified.
        // but the underlying map can still be changed (unlike immutable maps), and those changes will be reflected in the unmodifiableMap
        Map<Double, Character> unmodifiableMap = Collections.unmodifiableMap(doubleCharacterMap);
        System.out.println(unmodifiableMap);
        try {
            unmodifiableMap.put(4.1, '.');  //this will throw UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
        doubleCharacterMap.put(2.22, '£');
        System.out.println(unmodifiableMap);   //changes to the underlying map will feed through to the unmodifiableMap

        //Make unmodifiable map modifiable by wrapping in HashMap
        Map<Double, Character> modifiableUnmodifiableMap = new HashMap<Double,Character>(Collections.unmodifiableMap(doubleCharacterMap));
        modifiableUnmodifiableMap.remove(2.22);
        out.println(modifiableUnmodifiableMap);



        //3rd way to create unmodifiableMap
        Map<String, String> unmodifiableMapOf = Map.of("1", "a", "2", "b", "3", "c", "4", "d");
        System.out.println(unmodifiableMapOf);
        //unmodifiableMapOf.put("5","e");  //throws UnsupportedOperationException

        //4th way; entries themselves not stored in the map
        Map<Integer, Boolean> unmodifiableMapOfEntries = Map.ofEntries(entry(1, true), entry(6, false), entry(0, true));
        System.out.println(unmodifiableMapOfEntries);

        //5th way;  changes made to original are not reflected in the unmodified copy
        Map<Double,Character> unmodifiableCopy = Map.copyOf(doubleCharacterMap);
    }
}

class Parameters {

}

class Lambda {
    public static void main(String[] args) {
        Function<Integer,Integer> power = x -> x*x;
        Function<Integer,String> oddOrEven = x -> x % 2 == 0 ? "even" : "odd";

        List<String> strings = Arrays.asList("womble","mungo","jambo","kato");
        Function<String, String> capitalize = String::toUpperCase;
        strings.stream().map(capitalize).forEach(out::println);

        List<String> dates = new ArrayList<>();
        UnaryOperator<String> replaceSlashes = date -> date.replaceAll("/","-");
        dates.replaceAll(replaceSlashes);  //replaceAll accepts a UnaryOperator to pass to each element, then puts result back into list

        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8);
        numbers.stream()                         //alternative approach vs using a foreach loop with a total counter to get sum of evens^2
                .filter(x -> x % 2 ==0)  //filters the stream, returns a Stream<Integer>
                .mapToInt(x -> x*x)   //squares x, converts Integer to int and returns IntStream
                .sum();    //returns an int
        //mapToInt = returns an IntStream from an input stream

        //creating an int[] of the lengths of all the words
        List<String> words = List.of("java","python","ruby","c++","go","shell");
        int[] lengths = words.stream().mapToInt(String::length).toArray();   //calls the length() on each element of the input stream, to generate an int stream

        //putting all strings dividable by 3 into an output list
        List<String> numberStrings = Arrays.asList("4","9","0","5","1","19","0","15","3","7");
        List<String> output = new ArrayList<>();
        numberStrings.stream()
                .mapToInt(Integer::parseInt)
                .filter(x -> x % 3 ==0)
                .forEach(i -> output.add(Integer.toString(i)));

        List<String> squarableStrings = Arrays.asList(new String[]{"4","9","0","5","1","19","0","15","3","7"});
        squarableStrings.stream()
                .map(x -> Integer.parseInt(x) * Integer.parseInt(x))
                .forEach(out::println);

        //providing the mapToInt function logic via anonymous class
        List<String> wordles = List.of("womble","mungo","kosie","kato");
        IntStream intStream = wordles.stream()
                .mapToInt(new ToIntFunction<String>(){
                    @Override
                    public int applyAsInt(String value) {
                        return value.length();
                    }
                });
    }
    String processFunction(Integer number, Function<Integer,String> lambda) {
        return lambda.apply(number);
    }

    private class SubLambdaClass {

        public static void main(String[] args) {
            List<Dog> dogs = List.of(new Dog("womble",10),new Dog("mungo",8),new Dog("jambo",45),new Dog("sita",40),new Dog("kato",20));
            DoubleSummaryStatistics doubleSummaryStatistics = dogs.stream()
                    .mapToDouble(Dog::getWeight)
                    .summaryStatistics();  //produces the count, sum, min, max, average
            out.println(doubleSummaryStatistics);

            DoubleSummaryStatistics ageSum = dogs.stream()
                    .collect(Collectors.summarizingDouble(Dog::getAge));

            List<Dog> dogList = List.copyOf(dogs);
            List<String> dogNames = dogList.stream().map(Dog::getName).toList();
        }
        private static class Dog {
            String name;
            String species;
            int age;
            double weight;

            public Dog(String name, double weight) {                this.name = name;  this.weight=weight;          }
            public String getName() {                return name;            }
            public void setName(String name) {                this.name = name;            }
            public String getSpecies() {                return species;            }
            public void setSpecies(String species) {                this.species = species;            }
            public int getAge() {                return age;            }
            public void setAge(int age) {                this.age = age;            }
            public double getWeight() {                return weight;            }
            public void setWeight(double weight) {                this.weight = weight;            }

            @Override
            public String toString() {
                return "Dog{" +                        "name='" + name + '\'' +                        ", species='" + species + '\'' +                        ", age=" + age +                        ", weight=" + weight +                        '}';
            }
        }
    }
}



//try-with-resources is just a try block that declares resources inside
//the try ensures objects implementing AutoClosable interface are closed when complete, e.g. File, Socket
//can declare and initialize resources in try block, assured they will be closed (as long as resources implement AutoCloseable)
//  or can also create final variables outside try, but use them inside as they wont change
//thus don't need a finally block to ensure resources always closed once block completes
//can have multiple resources; they are closed from last-to-first
class TryWithResources {
    void trying() {

        try(PrintWriter printWriter = new PrintWriter("test.txt"); FileOutputStream fos = new FileOutputStream("womble.txt")){
            printWriter.println("new file content");
            Scanner scanner = new Scanner(new File("test.txt"));
            MyAutoCloseable autoCloseable = new MyAutoCloseable();   //custom AutoCloseable object


            String text = "womble is a fluffy but grumpy dog";
            byte[] bytes = text.getBytes();
            fos.write(bytes);

            while (scanner.hasNext()) {
                out.println(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Custom class for custom resources that can try-with-resources
    class MyAutoCloseable implements AutoCloseable {

        @Override
        public void close() throws Exception {
            out.println("Resource closed");
        }
    }
}


class CustomThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            out.println("Current date = " + LocalDate.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new CustomThread());   //to create an object of the custom runnable class, pass into thread constructor
        thread.start();
    }
}

//compare custom objects by implementing Comparable interface and overriding compareTo()
class MyComparable {
    static class Pekingese implements Comparable<Pekingese>{
        String name;
        double weight;
        int age;

        public Pekingese(String name, double weight) {            this.name = name; this.weight=weight;       }
        public String getName() {            return name;        }
        public void setName(String name) {            this.name = name;        }
        public double getWeight() {           return weight;        }
        public void setWeight(double weight) {            this.weight = weight;        }
        public int getAge() {            return age;        }
        public void setAge(int age) {            this.age = age;        }

        @Override
        public String toString() {            return "Pekingese{" +     "name='" + name + '\'' +     ", weight=" + weight +     '}';       }

        @Override
        public int compareTo(Pekingese o) {
            return Double.compare(this.getWeight(), o.getWeight());   //ascending order of weight
        }
    }

    public static void main(String[] args) {
        List<Pekingese> pekingeses = Arrays.asList
                (new Pekingese("Womble",10),
                (new Pekingese("Mungo",8)),
                (new Pekingese("Sita",45)),
                (new Pekingese("Kato",23)));
        //Collections.sort(pekingeses);  //will sort the elements based on the Comparable the class implements the compareTo() for
        pekingeses.sort(null);
        out.println(pekingeses);
        pekingeses.sort(Comparator.comparing(Pekingese::getWeight));
        pekingeses.stream().sorted(Pekingese::compareTo).collect(Collectors.toList());
        out.println(pekingeses);
        }
}

//Sleep throws IllegalMonitorStateException, whereas Wait throws InterruptedException

class SleepVsWait {
    public static void main(String[] args) throws InterruptedException {
        //wait is an instance method that can be called on any object, used for thread synchronization
        //can only be called from a synchronized block
            //whereby current thread waits and releases the lock it has on the object
        //it tells the calling thread (current thread) to wait until another thread invokes the notify() or notifyAll() method for the object
            //notifyAll() is for if many threads are set to wait() (though only 1 can resume execution)
        //so can be woken by another thread
        Object lock = new Object();
        synchronized (lock) {
            lock.wait(1000);
            out.println("Object " + lock + " just waited for 1 second");
        }


        //sleep is a static method that can be called from any context
        //pauses the current thread, without releasing any locks
        //thread automatically wakes after time elapses. does not lose its ownership of the monitor
        //cannot be woken by another thread
        Thread.sleep(1000);
        out.println("Thread " + Thread.currentThread().getName() + " just slept for 1 second");
    }
    //other threads can wake a waiting thread via notify() or notifyAll() on the object
    int sum = 0;
    public void run(){
        synchronized (this) {
            int i = 0;
            while (i < 1000) {
                sum+=i;
                i++;
            }
            notify();
        }
    }
}

enum Dogs {
    PEKINGESE, LABRADOR, SHITZU
}

//methods in a final class cannot be overridden
//final classes cannot be inherited
final class MyFinalClass {
    private void printing(){
        out.println("this method is final, as in a final class, so cannot be overridden");
    }
}

//Consumer is an operation that accepts 1 input, and returns nothing
class FunctionalInterfaces {
    Consumer<Integer> myConsumer = new Consumer<Integer>() {
        @Override
        public void accept(Integer integer) {

        }
    };

}

class MyMap {
    static Map<String,Integer> myMap = new HashMap<>() {{
        put("womble",6); put("mungo",5); put("jambo",5); put("sita",4); put("kato",4);
    }
        private void put(String name){      //can create a method to simplify block methods too
            put(name, name.length());
        }
    };

    public static void main(String[] args) {
        int result = 0;
        for (Map.Entry<String,Integer> entry : myMap.entrySet()) {    //iterating over each entry object in the Map
            result += entry.getValue();            //getting the value of each entry
        }
        out.println(result);   //prints 24
    }
}


class FormatterDemo {
    public static void main(String[] args) {
        Formatter formatter = new Formatter();
        formatter.format("dog %s", "womble ");
        out.println(formatter);  //prints: dog womble
        formatter.format("pekingese %s", "fluffy");
        out.println(formatter);  //prints dog womble pekingese fluffy
    }
}
//O(AB)
class printUnorderedPairsInArrays {
    void printUnorderedPairsInArrays(int[] array1, int[] array2) {
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; i < array2.length; i++) {
                if (array1[i] > array2[j]) {
                    out.println(array1[i] + " , " + array2[j]);
                }
            }
        }
        out.println();
    }

    public static void main(String[] args) {
        printUnorderedPairsInArrays printing = new printUnorderedPairsInArrays();
        printing.printUnorderedPairsInArrays(new int[]{1,2,3,4,5}, new int[]{9,8,7,6,5});
        printing.printUnorderedPairsInArrays(new int[]{1,2,3,4,5}, new int[]{1,2,3,4,5});

    }

}



//TODO
//https://www.benchresources.net/java-8-stream-reduce-method-with-examples/
//https://www.baeldung.com/java-nested-classes
//https://www.geeksforgeeks.org/using-instance-blocks-in-java/
//https://www.geeksforgeeks.org/static-blocks-in-java/
//https://www.geeksforgeeks.org/the-initializer-block-in-java/?ref=lbp
//https://howtodoinjava.com/java/basics/what-is-block-statement-in-java/
//https://www.baeldung.com/java-tuples
//https://howtodoinjava.com/java/java-tuples/
//https://www.javatpoint.com/java-tuple
//https://www.freecodecamp.org/news/a-quick-intro-to-dependency-injection-what-it-is-and-when-to-use-it-7578c84fa88f/























