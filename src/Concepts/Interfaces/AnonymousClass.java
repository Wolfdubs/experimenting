package Concepts.Interfaces;
//you override the method or variable as the object is created, rather than going into the class and changing it
//so for the new object, java will use the implementation you anonymously define immediately after instantiation, rather than going into the class method
//Anonymous class = to create just 1 single object of the class. It defines the class and instantiates that single object at the same time
//this 1 time use class can implement a specified interface, or extend an existing class; hence creates an object that e.g. implements the interface

public class AnonymousClass {

    public static void main(String[] args) {
        //If a class exists only to extend and overwrite 1 method of a super class, it is a waste of memory & code
        //can create a new class, that extends the Parent class instantly via:
        Parent parent = new Parent(){};
        Parent parent1 = new Parent(){
            @Override
            public void show() {
                System.out.println("overridden show");
            }};


        //specify the method for the new anonymous class, with the custom implementation to override the parent class method
        //is anonymous because it doesn't have a name
        //used for the sole purpose of overwriting the parent class method
        //scope exists only exactly where it is being used; only exists for 1 time use
        //basically create the object first, and then what it does. unlike normal class-> object direction
        Parent parent2 = new Parent(){
            public void show(){System.out.println("Inside parent anonymous class show method");}
        };
        parent2.show();

        //Can do same for interfaces
        // dont need a new class that implements the interface with implementation for methods
        //normally you cannot create object of interface as it lacks implementation; but anonymous class will provide it, so can create interface object
        myInterface itf = new myInterface(){
            public void show(){System.out.println("Inside interface anonymous class show method ");
            }
        };
        itf.show();

        Runnable runnableAnonClass = new Runnable(){  //here we are creating an object of an unamed class that implements the interface
            @Override
            public void run() {
                System.out.println("implementing the interfaces run method");
            }};




    }
}

class Parent{
    public void show(){
        System.out.println("Inside A class show method");
    }
}

interface myInterface{
    void show();
}

//Can actually create anonymous classes and instantiate them inside a method
class AnonInsideMethodDemo{
    public void MethodContainingAnonymousClass(){
        class AnonymousClass{   //only accessible from right inside the method where its declared
            String anonVariable = "qwerty";
            public void anonMethod(){
                System.out.println("inside anonymous class' own method");
            }
        }
        AnonymousClass ac = new AnonymousClass();
        ac.anonMethod();
    }
}
