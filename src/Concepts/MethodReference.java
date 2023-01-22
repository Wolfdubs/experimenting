package Concepts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

//Passing a method as a parameter into another method
// after the :: specify the method, before the :: specify the class the method belongs to = methodClass::methodName
//4 types of methods it can be used for:
//   static
//   instance of particular object
//   instance of arbitrary object of particular type
//   constructor
//is just the shorthand syntax for a lambda expression with 1 method call
public class MethodReference {

    public static void main(String[] args) {

        //method reference derives from lambda expression
        List<String> languages = Arrays.asList("Java", "Python", "Scala", "C#", "Ruby");
        languages.forEach(str -> System.out.println(str));  //frEach() accepts object of Consumer interface

        //direct lambda passing is derived from manually defining the Consumer functional interface object that forEach() accepts,
        // via anonymous/lambda defining its SAM "accept()"
        Consumer<String> stringConsumerAnonymous = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        Consumer<String> stringConsumerLambda = s -> System.out.println(s);
        languages.forEach(stringConsumerAnonymous);  //for each will pick up each value from the list, and pass them 1 by 1 to the accept() method defined by the Consumer object
        languages.forEach(stringConsumerLambda);

        //because for every value forEach() retrieves, it is just printing it, don't need to specify input s, and the method will always apply to input every time
        //so calling the forEach() by method, which will always be applied to every value forEach retrieves
        //passing a function into a function -> higher order functions
        // the :: tells the calling function that it is being passed another function; it defines input as being a function
        // before the :: you must specify the object the input function belongs to
        languages.forEach(System.out::println);   // :println refers to the specific method being passed to forEach.
                                                // System.out specifies the class that method belongs to
                                                //don't have to pass in data, as the forEach already provides that itself

        String myString1 = "grey";
        StringReverser stringReverser = new StringReverser();
        String reversedString1 = stringReverser.reverseString(myString1, new MyParser() {  //calling reverseString() itself calls myParser.parse()
            @Override                                                           //implementation of which is overriden below
            public String parse(String s) {
                return StringParser.convert(s);                   //said implementation calls convert() logic
            }
        });
        System.out.println(reversedString1);

        //entire anonymous class can be replaced by lambda
        String myString2 = "blueberry";
        String reversedString2 = stringReverser.reverseString(myString2, s -> StringParser.convert(s));  //pass in both data and the method (as reverseString itself doesn't fetch data like forEach()
        System.out.println(reversedString2);

        //but in the lambda, the s references are redundant; just want method to be applied onto myString (which is same as s)
        //whatever data is in myString, then call convert() upon too
        // basically saying, "this function (StringParser::convert is the implementation for the `parse` function in the interface".
        //  You are implementing the interface "on the fly". StringParser::convert is implementation of the interface accepted my reverseString()
        //         good as permits customized logic every time
        String myString3 = "kill";
        String reversedString3 = stringReverser.reverseString(myString3, StringParser::convert);  //as convert() is static, you can call directly with class name
        System.out.println(reversedString3);

        String myString4 = "Womble";
        StringParser sp = new StringParser();
        String reversedString4 = stringReverser.reverseString(myString4, sp::convert2);  //convert2() is non-static, so must call from instantiated object
        System.out.println(reversedString4);

        //can even create the new StringParser in place, for non-static methods
        String myString5 = "Mung";
        String reveredString5 = stringReverser.reverseString(myString5, new StringParser()::convert2);
        System.out.println(reveredString5);


        //calling simple static & non-static custom methods via method reference
        List<Integer> integerList = Arrays.asList(4,7,2,9,4,0,6,1);
        //Static calls without objects, just on the class
        integerList.stream().map(i -> DoubleInput.doubleInputStatic(i));   //calling custom static method via lambda
        integerList.stream().map(DoubleInput::doubleInputStatic);   //calling static method directly on each list value

        //Non-static calls on objects
        DoubleInput di = new DoubleInput();
        integerList.stream().map(i -> di.doubleInput(i));
        integerList.stream().map(di::doubleInput).toList().forEach(System.out::println);    //calling method from te instantiated object

        //Note: CANNOT use forEach to return/assign values to a variable, as its a terminal operation ; use map()


    }
}

//Create own method references
class StringReverser {
    public String reverseString(String inputString, MyParser myParser){
        inputString = myParser.parse(inputString);
        String outputStr ="";
        char c;

        for (int i=0; i<inputString.length(); i++){
            c = inputString.charAt(i);
            outputStr = c + outputStr;   //adds the next letter from input to be in front of previous letters iterated so far
        }
        return outputStr;
    }

    //have to also pass in the data for the method to operate on
}

interface MyParser{
    String parse(String s);
    //don't need to define implementation, as want to customize on the fly
}

class StringParser{
    public static String convert(String str){
        if (str.length()<=4){ return str.toUpperCase();}
        else { return str.toLowerCase();}
    }

    public String convert2(String str){
        if (str.length()<=4){ return str.toUpperCase();}
        else { return str.toLowerCase();}
    }
}

//StringParser class provides logic for MyParser interface to follow
//want the MyParser to implement logic of StringParser
//to create object of MyParser, pass in as anonymous/lambda


//for calling static & non-static methods via method reference
class DoubleInput{
    public static int doubleInputStatic(int i){
        return i * 2;
    }
    public int doubleInput(int i){
        return i*2;
    }
}




interface Walkable {
    default void walk(String distance){
        System.out.println("Interface walked so far - " + distance);
    }
    void SAM();
}

@Getter
@Setter
@ToString
class Dog implements Walkable{
    String name;
    String species;
    int age;
    double weight;

    public Dog() {
        //generating a random name of 7 random chars
        Random random = new Random();
        this.name = random.ints(97, 123)
                .limit(7)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    public Dog(String s) {

    }

    @Override
    public void walk(String distance) {
        System.out.println("Dog class walked so far - " + distance);
    }

    @Override
    public void SAM() {

    }

}

class MethodReferenceConstructor {

    private static <T> List<T> getObjectList(int length, Supplier<T> objectSupply) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(objectSupply.get());
        }
        return list;
    }

    public static void main(String[] args) {
        List<Dog> dogs = getObjectList(5, Dog::new);
        dogs.stream()
                .map(Dog::getName)
                .forEach(System.out::println);
        Walkable boo = Dog::new;   //Reference to constructor must be declared as functional interface type
        boo.walk("long way");
    }
}























