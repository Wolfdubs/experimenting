package Concepts.Basics;

//means you cannot create an instance object of this class. it exists only to be a blueprint inherited by sub classes

abstract class Animal {

    int weight;
    String name;

    //abstract methods can just be declared - don't need implementation {}
    //can only exist in abstract classes
    public abstract void eat();
    public abstract String sleep();

    //abstract class can include concrete methods
    public void move(){System.out.println("moving");
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



//useful as means you can have methods that accept the abstract class type, and so pass in any of the concrete impementation objects that extend it
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
