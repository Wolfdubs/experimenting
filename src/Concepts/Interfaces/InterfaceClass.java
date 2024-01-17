package Concepts.Interfaces;

interface Writer {
    void write();  //all methods by default are always public abstract, even if not written the keywords.
                    // cannot give it a body

    default void draw(){   //default keyword is only way to add implementation body to interface method
        System.out.println("I am drawing; interface default implementation");
    }

    private int interfaceIntMethod() {   //private Interface methods must have a body. cannot have protected methods
        return 0;
    }
    String interfaceString = "implementing objects have this";  //but interface objects cannot call this directly, but implementing class can
}

interface Product {
    int price=0;   //becomes a final constant by default, methods cannot reassign its value

    static void unModifiableMethod(){
        System.out.println("This method cannot be modified");
    }
}

interface InkBasedWriter extends Writer{
    @Override
    default void draw() {
        System.out.println("I'm an ink using pen");
    }

}

class Pen implements InkBasedWriter, Product{    //can also still extend a super class at same time
    public void write(){
        System.out.println("Pen class implementation");
    }
    protected void penClassOnlyMethod(){
        System.out.println("Interface types cannot call this");
        String penString = interfaceString + "penny";
    }
    protected String penClassOnlyString = "woo hoo";
}

public class InterfaceClass {
    /*
    useful for generalization, like abstract super classes; both can declare & define methods, and constants
        all members are implicitly public
        for creating/using related classes that lack inheritance relationship but have similar behavior
        concrete implementors must implement methods. abstract class implementors don't have to
            superclass implementors mean all subclasses will be implementors too
    Objects that implement the interface are not only of their class type, but also the interface type
    Interface: can define methods via anonymous classes, lambdas, MR, and default keyword. inside interface can define public & private helper methods
    to permit concrete classes having more than 1 specification (to extend/implement). as no multiple inheritance of super classes
    note: any variables defined in an interface become a constant
     when you want to ensure classes must have certain methods that they implement
        e.g. APIs require you to implement all the interfaces they provide
    Only 3 ways to create object reference of interface; via lambda, or by creating new object of class that implements interface; InterfaceName obj = new ClassImplementing(), or calling a method that returns instance of interface;
    Marker Interface = interfaces with no methods inside
    interface methods are by default public abstract, so don't need to specify that.
     Can only define methods in interface if it has keywords 'default' or 'static'. must do this if you modify the interface to add more methods, that currently using classes won't have had their own implementation for, else those classes will break as not implementing the new method
        implementing classes get access to default methods without needing own implementation
            they can overwrite the default methods, but not static methods
*/

    public static void main(String[] args) {
        Writer pen = new Pen();        // can still create references of the interface, but object must be of implementing class.
            //Can only access members of the declared type, but uses the instantiated objects implementation
        //cannot create object of the interface (except via anonymous class & lambda, as they dont have a constructor
        pen.write();
        pen.draw();   //calling the default implementation from the interface. but you can overwrite it in the implementing class
        //pen.penClassOnlyMethod();  //can only call memebers of the declared type
        //pen.penClassOnlyString;
        Demo demo = new Demo();
        demo.show();
        StaticDemo.show();    //can directly call static methods from interfaces without needing objects

        Demo1 demo1 = new Demo1() {
            @Override
            public void show() {
                Demo1.super.show();
                System.out.println("We're also printing this folks");
            }
        };
        demo1.show();
    }

}



//Multiple inheritance issue
// can be similar as for classes; java doesnt know what method to fetch if they have same methods
// if class doesnt have method, it will pull default implementation from interface
// but if class implements 2 interfaces, and both have the method - which to use?
// solution:
//      define method inside class
//           - custom implement inside class - java will just use this
//           - call Interface.method to specify which interface to call the method from

interface Demo1{
    public static final String constant = ""; //interface variables are public static and final by default
    int constantInt = 1;   //no instance variables allowed - this is also public static final
    default void show(){
        System.out.println("Inside Demo1 interface");
    }
    static String LANGUAGE = "Java";   //variables defined inside an interface auto become a constant; by default its final and static
}

interface Demo2 {
    default void show(){
        System.out.println("Inside Demo2 interface");
    }
    static void staticMethod(){
        System.out.println("statics rock");
    }
}

//interfaces can extend other interfaces; inheriting all its constants and methods
interface Demo3 extends Demo2 {

}

class Demo implements Demo1, Demo2{   //both interfaces have default show()
    @Override
    public void show(){
        Demo1.super.show();     //calls the super interface implemtnation of show(), so no compilation errors where java doesnt know which to run
    }
   // @Override
   // static void staticMethod(){}
}

//static methods in interfaces mean you dont have to create an object of the class that implements the interface to call the method
interface StaticDemo {   //implementing classes cannot overwrite
    static void show(){
        System.out.println("Inside static method of interface");   //call it just via Interface.method
    }
}

//EXTRACTING INTERFACES
/*
If you write a class, and realize you want many similar classes, governed by shared principles, you can 'extract the interface' from the
existing class, to generate an interface with the methods that class has, so that other similar classes must then implement them
Is easier than writing the interface from scratch
 */