package Concepts.Basics;

//means you cannot create an instance object of this class. it exists only to be a blueprint inherited by sub classes
//useful to have so subclasses can extend and share specific fields and methods, without the abstract class itself every being instantiated
//can have methods both with and without implementation, that subclasses can @Override
// difference vs interfaces; interface methods dont need abstract keyword as every method is abstract by default. can only extend 1 class, but implement infinite
        //every field in interfaces are static final, so must initialize with a value, and the same value will apply to every object of the
        //implementing class
        //whereas with fields in abstract classes, every extending object can have their own value
    //abstract class = when many closely related classes that must share attributes and behaviors
        // if you need to store a generic ArrayList of the parent abstract class type, e.g. ArrayList<Animal> where you can store any concrete subclasses in the same array
    //interface = many unrelated classes that need to all implement the specified method

/*
Abstract Class sits in between a regular class and an Interface.  It is like a class, in that it can specify behaviour and data.
It is like an interface, in that it can have abstract methods (these are not implemented, and just have a semicolon instead of { }) and
cannot be instantiated
If an abstract class implements an interface, the abstract class does not need all the methods from the interface to be present,
but all the non-abstract subclasses of the abstract class would need to implement the interface in full.
any class with an abstract method must be an abstract class
abstract class can contain regular methods

Use them to hold constants, instance variables and abstract methods shared by classes
 */

abstract class Animal {

    int weight;
    String name;

    //abstract methods can just be declared - don't need implementation {}
    //can only do this in abstract classes
    public abstract void eat();
    abstract String sleep();

    //abstract class can include concrete methods
    public void move(){System.out.println("moving");
    }
    public Animal(){
        System.out.println("inside abstract class constructor");
    }

}

class Whale extends Animal{     //concrete class = not abstract

    //any sub classes extending abstract class, must implement its abstract methods
    @Override
    public void eat(){}
    @Override
    public String sleep(){
        System.out.println("Whale is sleeping");
        return "Whale is sleeping";}
}

class Dolphin extends Animal{

    @Override
    public void eat() {}
    @Override
    public String sleep() {
        System.out.println("Dolphin is sleeping");
        return "Dolphin is sleeping";}
}



//useful as means you can have methods that accept the abstract class type, and so pass in any of the concrete implementation objects that extend it
// e.g. a print(Number n) can then be called with any class that extends Number; ie Integer, Double, Long, Float rather than needing method overloading for each
class Printer{
    public void show(Number n){
        System.out.println(n);
    }
    public void animalSleep(Animal a){
        a.sleep();
    }
}

public class AbstractClass {
    public static void main(String[] args) {
        Animal baloo = new Whale();     //can create object of child with reference to abstract super
        Animal squeaky = new Dolphin();

        //calling a method that accepts an abstract super class, so you can pass in any concrete subclass
        Printer printer = new Printer();
        printer.show(5);
        printer.show(5.5);
        printer.show(5.5f);
        printer.show(999999999999999999L);
        printer.animalSleep(baloo);     //pass in concrete object with abstract reference, into method accepting abstract reference,
        printer.animalSleep(squeaky);   // which will call the specific implementation for the actual concrete object
    }
}


//Separate note: java only creates the default no-args constructor (or canonical for records) if you havent defined another constructor yourself (though you can chain up via super() inside new constructor)