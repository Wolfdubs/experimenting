package Concepts.Interfaces;

interface Writer {
    void write();  //all methods default is always public abstract, even if not written the keywords.
                    // cannot give it a body

    default void draw(){   //default keyword is only way to add implementation body to interface method
        System.out.println("I am drawing; interface default implementation");
    }
}

interface Product {
    int price=0;   //becomes a final constant by default, methods cannot reassign its value
}

class Pen implements Writer, Product{    //can also still extend a super class at same time
    public void write(){
        System.out.println("Pen class implementation");
    }
}

public class InterfaceClass {
    //useful for generalization, like abstract super classes; both can declare & define methods
    //    Interface: can define methods via anonymous classes, lambdas, and default keyword. inside interface can define public & private helper methods
    //to permit concrete classes having more than 1 specification (to extend/implement). as no multiple inheritance of super classes
    // when you want to ensure classes must have certain methods that they implement
    //    e.g. APIs require you to implement all the interfaces they provide
    //Only 2 ways to create object reference of interface; via lambda, or by creating new object of class that implements interface; InterfaceName obj = new ClassImplementing();

    public static void main(String[] args) {
        Writer pen = new Pen();        // can still create references of the interface, but object must be of implementing class
        //cannot create object of the interface (except via anonymous class & lambda, as they dont have a constructor
        pen.write();
        pen.draw();   //calling the default implementation from the interface. but you can overwrite it in the implementing class
        Demo demo = new Demo();
        demo.show();
        StaticDemo.show();    //can directly call static methods from interfaces without needing objects
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
    default void show(){
        System.out.println("Inside Demo1 interface");
    }
}

interface Demo2 {
    default void show(){
        System.out.println("Inside Demo2 interface");
    }
}

class Demo implements Demo1, Demo2{   //both interfaces have default show()
    public void show(){
        Demo1.super.show();     //calls the super interface implemtnation of show(), so no compilation errors where java doesnt know which to run
    }
}

//static methods in interfaces mean you dont have to create an object of the class that implements the interface to call the method
interface StaticDemo {
    static void show(){
        System.out.println("Inside static method of interface");   //call it just via Interface.method
    }
}