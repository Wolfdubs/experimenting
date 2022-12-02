package Concepts.Basics.Variables;

public class Wrappers {

    //primitives are faster than wrapper classes
    // but some frameworks dont support primitives; Collection API, Hibernate. so must use wrapper version

    int i = 10;
    Integer ii = i;       //boxing /wrapping = putting a primitive into an object wrapper. creates an object with that value/ don't need 'new Integer'; it is autoboxed
    int j = ii;          // unboxing / unwrapping.   this is actually autounboxing
    Float f = 10.0f;
    Boolean b = true;
    Double d = 10.0;
    Short s = 25;

    //use wrapper class method to convert string to int
    String str = "123";
    int stringValue = Integer.parseInt(str);




}
