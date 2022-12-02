package Concepts.Interfaces;

public class AnonymousClass {

    public static void main(String[] args) {
        //If a class exists only to extend and overwrite 1 method of a super class, it is a waste of memory & code
        //can create a new class instantly via:
        Parent parent = new Parent(){};

        //specify the method for the new anonymous class, with the custom implementation to override the parent class method
        //is anonymous because it doesn't have a name
        //used for the sole purpose of overwriting the parent class method
        //scope exists only exactly where it is being used; only exists for 1 time use
        //basically create the object first, and then what it does. unlike normal class-> object direction
        Parent parent1 = new Parent(){
            public void show(){System.out.println("Inside parent anonymous class show method");}
        };
        parent1.show();

        //Can do same for interfaces
        // dont need a new class that implements the interface with implementation for methods
        //normally you cannot create object of interface as it lacks implementation; but anonymous class will provide it, so can create interface object
        myInterface itf = new myInterface(){
            public void show(){System.out.println("Inside interface anonymous class show method ");
            }
        };
        itf.show();




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
