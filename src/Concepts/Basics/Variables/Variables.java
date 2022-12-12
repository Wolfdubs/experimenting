package Concepts.Basics.Variables;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Variables {

    //multiple overloading constructors need different signatures; polymorphism
    public Variables() {
    }

    public Variables(String s) {
    }

    public Variables(double d) {
    }

    public Variables(float f, long l, short s, boolean b) {
    }

    public static void main(String[] args) {
        System.out.println(incrementChar('x'));
        System.out.println(incrementChar('Z'));
        System.out.println(incrementChar('z'));
        char myChar = 72;
        System.out.println(myChar);
        System.out.println(incrementChar(myChar));
        intIncrements();

        int[][] array2D = {{1, 2, 3, 4}, {10, 20, 30, 40, 50}, {9, 8, 7, 6}, {-3, -9}};  //jagged 2D array as column length varies
        int array2DValue = array2D[1][3];
        int[] array2DValue2 = array2D[2];
        System.out.println(array2DValue);

        //can create ArrayList using Arrays.asList(3,6,2,8,6,0)
        List<Integer> funny = Arrays.asList(23, 4, 2, 6);

        //2 ways to iterate over 2D array
        for (byte i = 0; i < array2D.length; i++) {       //iterates over rows
            for (byte j = 0; j < array2D[i].length; j++) {  //iterates over columns
                System.out.print(array2D[i][j] + " ");
            }
            System.out.println();
        }

        for (int[] k : array2D) {
            for (int i : k) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        //STRING IMMUTABILITY
        //  Strings are immutable; changing a variable value doesn't change the String object in memory it creates a new String object and just
        //  changes the reference in the variable
        // 3 benefits:
        // memory optimization
        // security: methods cannot change string values of objects they access
        // Strings completely thread safe as threads cannot change it
        String name = "Cody";
        String anotherName = "Cody";   //both these variables actually point to the same string object inside Java's string pool (used to save memory avoiding duplication)
        System.out.println(name == anotherName);  //returns true as the same object
        String thirdName = new String("Cody");  //creates a new string object
        System.out.println(name == thirdName);   //returns false as fifferet objects

    }

    public static char incrementChar(char inputChar) {
        char myChar = inputChar;
        myChar++;
        return myChar;
    }

    public static void intIncrements() {
        int n = 2, o = 4, p = 1;
        int q = n++;     //first assigns the value to q, then increments the value of n. post increment
        int r = ++n;     //first increments the value of n, then assigns value to r. pre increment
        System.out.println(q);
        System.out.println(r);
    }

    public static int casting(double n) {
        int i = (int) n;
        return i;
    }


    /*by default all decimals are double
    could also store whole numbers in doubles, but will add a .0
    cast to int will truncate the decimal
    float, must end with f; float num = 5.5f;
    short: use if you are certain value will be between -32000 - 32000
    byte; store values from -128 - 127
    long; 8 bytes
    char: can assign to a letter e.g. 'A' or directly to an ASCII character
    so can do a++ to increment to next letter in ASCII

    static means don't need an object to operate on
    */

    /*
    POJO - plain old java object
        based on 3 things a class does NOT have;
            1. Cannot extend another class outside its package
            2. Cannot implement any interfaces outside its package
            3. Cannot use any annotations
        Makes the POJO just a straightforward class that is totally self-contained, not requiring any outside libraries or classes to be used
        So can be used just on its own
        A pojo can extend other class and a pojo can implement an interface. The fundamental aspect of a pojo is that a pojo does not depend
        on any external component, in particular framework components like ejb or hibernate. It can depend on other classes that belong
        to the same domain. Cat extends Animal {} is a pojo if Animal is defined in the same package as Cat. But if you are using a 3rd
        party library that introduces Animal to your application, then Cat is not a pojo. The idea of pojo is that you can express your domain
        using classes that only depend on other classes of your domain.

    */

    /*
    Java Bean
        Must have
        1. Public no-args constructor
        2. All properties must be private
        3. Public getters & setters
        4. Must be serializable
     */
}

class myPOJO{   //is a POJO class as no extends, implements or annotations
    int age;
    String name;
}

class myJavaBean implements Serializable {   //means class can be written to DBs & files
    private int age;
    private String name;
    //even when no defined, by default it has the public no-args constructor
    public int getAge() {   return age;   }
    public void setAge(int age) {        this.age = age;    }
    public String getName() {        return name;    }
    public void setName(String name) {        this.name = name;    }
}


/*
DTO - Data Transfer Object classes
DTO is basically a simple class, usually a POJO, whose job is to work as a middle-man between two representations.
For example, you might have a web service request with a certain class structure, and also a database entity with a similar,
but separate class structure. To avoid tightly coupling those two classes together, we typically will have a DTO (data transfer object)
class that essentially holds the same data, and create mappings from the request to the DTO, and from the DTO to the database entity.
And perhaps mappings for the other way around too, if you also want to be able to return those database entries to the user.
So a DTO is more of a name for the purpose of the class, rather than aspects of what the class has or doesn't have like a pojo or javabean
Also useful to have one store data in the database, and using another to pull data via the API. The DTO is used to pull data so
I don't potentially expose unnecessary information to the end user
 */


/*
null
setting a variable to null means that variable will just be a reference that points to nothing at all in memory
primitives cannot be set to null; so best practice to always use boolean, not Boolean, to make sure its never null
avoid initializing variables to null, and avoid having methods ever return null, to avoid NullPointerExceptions arising later
Can use Optionals to manage null possibilities
 */

