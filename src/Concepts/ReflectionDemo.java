package Concepts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
/*
Reflection API is for calling private methods of other classes
 can also tell you about the fields and methods of a class
 Used just for debugging, not used for normal usage of programs

Do not use Reflection API unless you work on frameworks like Spring or libraries like Hibernate, Junit .etc.
Why not reflection?
1. It violates type-safety
2. Using it to invoke private methods violates encapsulation and security guarantees.
3. Since it is violates type-safety, it makes it exponentially harder to debug.
 */

public class ReflectionDemo {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        //can create an object using reference to the class "Class" (not related to reflection though)
        Class c = Class.forName("Concepts.Chemistry"); //put fully qualified package directory for class
        Chemistry chemistry = (Chemistry) c.newInstance();

        //create object of Method in order to call a private method from the Chemistry object
        Method m = c.getDeclaredMethod("experiment", null); //pass in method name, and its parameter types
        m.setAccessible(true);    //make the private method become accessible
        m.invoke(chemistry, null);  //pass it the object to run the method with, and any parameters


    /*More on "Class" class:
        class & interface files both compile to .class files. To discover what type a .class file contains
     */
        Class myClass = Class.forName("Concepts.Chemistry");
        System.out.println(myClass.isInterface());  //boolean for if the class type is an interface
        System.out.println(myClass.getSuperclass());   //returns superclass FQPN of input
        System.out.println(Arrays.toString(myClass.getDeclaredFields()));


    }
}

class Chemistry{
    String name;
    int beakers;

    private void experiment(){
        System.out.println("running experiment");
    }
}
