package Concepts.Basics;
/*  Declared Type vs Instantiated Type
A parameter is a local variable declaration, so a parameter declaration sets the apparent type of the variable it is declaring.
When passing an argument into a method, the argument must have a declared type of the same type, or a subtype of the parameter's type.
So if Interface A is implemented by B & C, you could have method doStuff(A a) and pass in an object of either B or C
        but not the other way around; cannot substitute a subtype requirement for a supertype passed in
 */

/*  SUBTYPE POLYMORPHISM
• Classes (Abstract or Regular) can both implement Interfaces
• To obtain an object declared to be of an Interface type, you must instantiate a class that implements the interface.
• InterfaceName thingy = new ClassName(); where ClassName implements InterfaceName {...}
• When you declare a variable (SomeType var), you are setting its apparent type to be the declared type.
• When you instantiate an object (var = new SomeClass() ) you are setting its actual type to be the instantiated type.
The declared type determines what methods the object can access, while the instantiated type determines the implementation used
        //method dispatch used to determine implementation used; goes from instantiated class to highest until it finds an implementation to use
 */

//Interfaces (like classes) are a type; so can declare an object to be of type InterfaceName
//  but to instantiate the object requires a class that implements the Interface
//     InterfaceName myVariable = new ClassImplementsInterface();
//    because interface is the SuperType and implementing class is the SubType;
//          and can always create an object of type SuperType by instantiating the SubType


public class PolymorphismDemo {


    static BaseballPlayer bp = new BaseballPlayer();
    static Athlete sportsStar = new BaseballPlayer();
    static LandAnimal lander = new BaseballPlayer();

    public static void joinTeam(Athlete athlete){
        System.out.println("I love this team");
        athlete.playSport();  //can only access the Athlete supertype methods, even if when you actually call the method you pass in a subtype class with extra methods
    }



    public static void main(String[] args) {
        bp.catchBall();   //can call any baseballPlayer method on the baseballPlayer declared object
      //sportsStar.catchBall();   //but cannot call the exclusive BaseballPlayer methods on the Athlete declared object
        joinTeam(bp);     //can pass in the subtype object, for a method that accepts the supertype
      //joinTeam(lander);  //but even if the object is instantiated as a subtype, if its Declared Type is unrelated to the required parameter, you cannot pass it in
        joinTeam((Athlete) lander);  //can cast the unrelated declared type to be of the required declared type though
    }

}


interface Athlete{
    public void playSport();
    public void promoteSponsor();
    public void train();
    default void breathe(){System.out.println("breathing");}
}

interface LandAnimal{
    void walk();
}

class BaseballPlayer implements Athlete, LandAnimal{
    @Override
    public void playSport() { System.out.println("playing baseball");}
    @Override
    public void promoteSponsor() { System.out.println("I always drink Nola's cola");}
    @Override
    public void train() {System.out.println("swing and hit");}
    @Override
    public void walk() {        System.out.println("I'm walkin here");  }

    public void swingBat(){        System.out.println("I'm gonna hit a homerun");    }
    public void catchBall() {        System.out.println("the ball is mine");    }
}

class Swimmer implements Athlete{
    @Override
    public void playSport() { System.out.println("swimming time");    }
    @Override
    public void promoteSponsor() {        System.out.println("I love my speedos");  }
    @Override
    public void train() {      System.out.println("back in the pool");    }

    public void holdBreath(){System.out.println("just count to 30");}
}