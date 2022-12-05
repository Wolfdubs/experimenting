package Concepts.Basics.Variables;

import java.lang.annotation.*;
import java.util.ArrayList;

public class AnnotationsDemo {
    public static void main(String[] args) {
        SecondClass sc = new SecondClass();
        sc.show();
        sc.oldShow();   //Deprecated annotation means this line is added when method is called

        //Fetching annotation values for a class
        // 1. Create object of annotation implementing class
        Nokia nokia = new Nokia();
        //  2. Create object of the annotation, via creating a class object (aka Reflection API)to get values of the annotation
        Class c = nokia.getClass();     //creates class object of nokia's class
        Annotation an = c.getAnnotation(SmartPhone.class);  //annotation is an interface, get object of it by getting the specified annotation of the input class (nokia)
        SmartPhone smartPhone = (SmartPhone) an;   //must cast the generic Annotation type

        //3. Can retrieve values from specific annotation now
        System.out.println(smartPhone.os());
        System.out.println(smartPhone.version());

    }

}

class FirstClass{
    public void show(){
        System.out.println("implementation in FirstClass");
    }
}

class SecondClass extends  FirstClass{
    @Override   //useful if method name could be misspelled, as will throw compile time error highlighting the method is not the same name
    @SuppressWarnings("uncheck")  //will suppress warnings shown creating an ArrayList with no type specified
    public void show(){
        System.out.println("implementation in SecondClass");
        ArrayList unSpecifiedType = new ArrayList();
    }

    @Deprecated  // says this method is no longer to be used. but doesn't stop you using it
    public void oldShow(){
        System.out.println("deprecated method can still be called");
    }
}

@FunctionalInterface   //will throw compile error if you try to add a 2nd method. Ensures only SAM
interface functionalInterfaceDemo{
    void doStuff();
}

//Create own Annotations

@interface myMarkerAnno{}   //Marker Annotations = annotations with no definition

//all annotations extend Annotation interface
//when creating an annotation, to create object of it requires 2 extra Meta-Annotations; Target & Retention
@Inherited  //optional meta-annotation that means subclasses of classes with the annotation will also have it
@Target(ElementType.TYPE)    //when creating annotation, must specify at what level it will be used (class, method, field, etc). ElementType.TYPE means for classes/interfaces
@Retention(RetentionPolicy.RUNTIME)   //until at what point the annotation will be available
@interface SmartPhone{   //@interface is command to create a SmartPhone, whose properties you then define.
    String os();           //creates a field that all implementing classes must have
    int version() default 1;        //Multi Value Annotations have multiple values, vs Single Value Annotations.  Can set default values classes will have
}

//to specify that the Nokia class is a SmartPhone
@SmartPhone (os="Android", version = 8)   //must define the values within the interface that the class will thus have. if you dont, it uses any defaults
class Nokia{

}


