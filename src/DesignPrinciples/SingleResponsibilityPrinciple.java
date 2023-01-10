package DesignPrinciples;
//makes code readable & maintainable, and easier to extend

import java.util.ArrayList;
import java.util.List;

/*
Single Responsibility
Open-closed
Liskov Substitution
Interface Segregation
Dependency Inversion

 */
/*
each class should be centered around 1 cohesive concept
If a class has several responsibilities, it should be split into a separate class
    e.g. if it has multiple clusters of methods referring to their own data

reduces coupling, ensures code cohesion within classes
    Coupling = relationship between modules. You want minimal coupling, done mainly via reducing code duplication
        2 classes work together; via method calls, dependencies, holding associated/aggregated fields, holding common functionality
        shared implementation coupling is insidious -> changing in 1 class means must change in another, but no compiler error thrown so only see at runtime
            e.g. formatting of print statements; could abstract it into its own method so changes propagate everywhere
        changes in 1 class may cause compiler alerts, or only be apparent at runtime
    If a singe change must be implemented in many places; it may mean classes are too coupled - > abstract out that info to 1 place so only change once

 */
public class SingleResponsibilityPrinciple {

}

/*ensure class hierarchies are following good design
refers to ability to substitute the objects instantiated type with its declared type, and have methods that accept a broader supertype type
    so you can pass in subtypes
    e.g. List<String> blah = new ArrayList<>();
    For a subclass to be suitable for its superclass, subclass cannot break expectations user of superclass would have;
        1. preconditions of subclass methods cannot be strengthened; e.g. cannot have narrower input range, cannot reduce service provided
        2. post conditions of subclass cannot be weakened; cannot have broader range of effects beyond what the superclass would.
                should not extend classes if the subclass can't perform all of the behaviour in the superclass
 */
class LiskovSubstitutionPrinciple{
    private static List<String> blah = new ArrayList<>();

    private static void acceptList(List<String> mylist){
        mylist.toArray();
    }

    //EXAMPLE OF SUPER-SUBCLASS SUBSTITUTION PROBLEMS WITH BIRD CLASSES
    //SOLUTION: Create Flyer interface to hold all flying-related behavior, so eagle just implements that interface
    abstract static class Bird {
        public void fly(){}
        public void eat(){
            System.out.println("eating");
        }
    }

    static class Eagle extends Bird {

    }

    static class Penguin extends Bird {

    }

    private static void makeFly(Bird bird){
        bird.fly();
    }

    //EXAMPLE OF SUPER-SUBCLASS SUBSTITUTION TOO NARROW PRE-CONDITIONS WITH DOCTOR CLASSES
    //SOLUTION: Replace the extends, with a association 'uses' relationship, so Specialist class has a Doctor field, so it can call Doctor methods
    //and wrap them with specifics for its own purposes
    static class Doctor{
        public void bookAppointment(int time) {
            if (time>9 && time <17){
                bookAppointment();
            }
            else System.out.println("Doctor only works from 9-5");
        }
        private void bookAppointment(){
            System.out.println("Appointment booked");
        }
    }

    static class Specialist extends Doctor{
        public void bookAppointment(int time) {
            if (time>10 && time <14){    //Breaks LSP because the subclass has a narrower precondition that the superclass. This bookAppointment is not substitutable for the superclass' version
                super.bookAppointment();  //so methods that accept declared type of super will not function as expected
            }
            else System.out.println("Specialise only works from 10-2");
        }

    }


    public static void main(String[] args) {
        acceptList(blah);    //can pass an arraylist in as substitution for the list parameter. Not part of LSP, just demo concept of substitution
        makeFly(new Penguin());   //makeFly() accepts a Bird, so can pass in a Penguin, but Penguins don't actually fly, so is not a good subtype of Bird
    }


    //TESTING IF SUBTYPE IS VALID FOR SUPERTYPE ACCORDING TO LSP
    /*
    Substitution Test:  try declaring it as the supertype; Bird b = new Eagle();
    Concatenation Test:  Eagle Bird (just an intuitive name sound check)
    Is-A Test:  best test; is the subtype logically of the supertype
     */



}
