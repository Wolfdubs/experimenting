package DesignPrinciples;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class LiskovSubstitutionPrinciple {

/*
"Subtypes should be replaceable by their base type"
Makes you think 2x before using inheritance, as classes shouldn't be extended just for sharing some common features - base class may have methods
    unsuited for the child and overriding the method to throw an exception could cause program to crash if running the method in a loop for all super
    class objects that includes some child class elements
    e.g. Video class has playAd() but PremiumVideo hould not
    So ensures class hierarchies are following good design
refers to ability to substitute the objects instantiated type with its declared type, and have methods that accept a broader supertype type
    so you can pass in subtypes
    e.g. List<String> blah = new ArrayList<>();
    For a subclass to be suitable for its superclass, subclass cannot break expectations user of superclass would have;
        1. preconditions of subclass methods cannot be strengthened; e.g. cannot have narrower input range, cannot reduce service provided
        2. post conditions of subclass cannot be weakened; cannot have broader range of effects beyond what the superclass would.
                should not extend classes if the subclass can't perform all of the behaviour in the superclass
 */

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

////////////VIDEO VS PREMIUMVIDEO DEMO
//create a manager class where every specific class will have a manager object as a field
    //manager stores all business logic
    //actual classes can cherry pick the desired behaviors by calling methods from manager object
@Getter
@Setter
class VideoManager {
    private String title;
    private int likes;
    private int views;
    private int length;

    public double getHoursPlayed(){
        return (length / 3600.0) * likes;
    }

    public double calculateEarnings(){
        return (this.getLikes() * 0.012 + this.getViews() * 0.0012);
    }

    public void playAd() {
        System.out.println("introducing the latest pekingese model....");
    }
}

class Video {
    private VideoManager videoManager;

    public double getHoursPlayed() {
        return videoManager.getHoursPlayed();
    }

    public double calculateEarnings() {
        return videoManager.calculateEarnings();
    }

    public void playAd(){
        videoManager.playAd();
    }
}

class PremiumVideo {  //just dont include a call to the VideoManager playAd()
    private int premiumId;
    private VideoManager videoManager;

    public double getHoursPlayed() {
        return videoManager.getHoursPlayed();
    }

    public double calculateEarnings() {
        return (videoManager.calculateEarnings() * 1.1);
    }
}


