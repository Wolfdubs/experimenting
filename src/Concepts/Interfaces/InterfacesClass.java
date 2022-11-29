package Concepts.Interfaces;

public class InterfacesClass implements myInterface {

    public static void main(String[] args) {
        InterfacesClass myClass = new InterfacesClass();
        methodThatTakesInterfaceImplementingObject(myClass);   //because the myClass object must implement DoStuff interface, you can pass it into this method call, knowing it must implement to other method in te caller's body
    }

    public InterfacesClass(){}

    @Override
    public void methodThatAllImplementingClassesMustAlsoImplement() {
        System.out.println("custom implementation");
    }

   // @Override
   // public void interfaceMethod(String input) {
//
//    }

    public static void methodThatTakesInterfaceImplementingObject(myInterface ds){       //takes any object that implements DoStuff interface
        ds.methodThatAllImplementingClassesMustAlsoImplement();               // because the method knows the argument implements the interface, it knows the object can perform all its methods
    }


}
