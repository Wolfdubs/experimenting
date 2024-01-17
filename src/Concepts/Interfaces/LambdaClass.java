package Concepts.Interfaces;

//Types of Interface
// 1. Normal - if it has 2+ methods
// 2. Functional Interface - interface has SAM single abstract method. can have many other default methods though.
    //having other members e.g. variables can effect this maybe???
// 3. Marker interface - has no methods e.g. Java's Serializable interface

//can use lambda expressions for Functional Interfaces
//simpler way to achieve same thing as anonymous class
//exactly the same as anonymous class, but removing boilerplate of specifying inputs and method name and new interface(), just proving the implementation of the
    //interface's SAM so you can create an object of the interface
// a way to quickly & low memory create an interface object which defines implementation for the interface methods

public class LambdaClass {

    //anonymous class has lots of redundant code that wastes memory
    ExampleInterface exampleInterface = new ExampleInterface() {         //even the new ExampleInterface() could be assumed too
        @Override
        public void show() {
            System.out.println("anonymous");}  // it will redefine the SAM in the functional interface. but it is the only method so dont need to rewrite signature. unhelpful boilerplate code
    };

    //Remove boilerplate code - because we can infer the "new ExampleInterface()" and the "public void show()" method signature
    ExampleInterface exampleInterface1 = () -> System.out.println("anonymous");  //just go directly to the implementation to execute for the interface reference
        //only works for SAM because you don't specify the method signature, just create an interface reference and then the implementation, so is can only auto apply to the method if there is only 1 option
}

@FunctionalInterface   //best practice to create compile time error if you add 2+ methods. keeps it to SAM
interface ExampleInterface{
    void show();
    default void defaultMethod(){}   //Functional interface can have multiple methods, but only 1 abstract one without implementation, so others can be default
}
