package Concepts.SpecificClassTypes;

//abstract classes are meant to be inherited vs final classes which cannot be inherited
//Sealed classes are inbetween;
//    restrict inheritance to only specified classes can inherit
//      e.g. Computer class can only be inherited by laptop, pc and desktop class
    //specify subclasses and subinterfaces that can inherit Sealed class
public class SealedClass {

}

sealed class A permits B,C {   //only want class B & C to be able to inherit A. Compile time error if others try to. This class A could still extend other classes itself

}

sealed class B extends A permits D {     //subclass of sealed class must itself be either sealed, non-sealed, or final

}

non-sealed class C extends A{     //specifies that any other class can inherit this class. so basically just says it is public

}

final class D extends B {

}

sealed interface X permits Y {   //can seal interfaces to specify which other interfaces can extend it
}

non-sealed interface Y extends X{}   //no option of final, as interfaces can never be final


