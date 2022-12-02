package Concepts.Basics;

public class Keywords {

    //STATIC
    String differentForEachObject;
    static String sharedForAllObjects;    //mean the variable is not specific to the specific object, but is true for all objects of the class. all objects share the same value
    static String assignOnceViaStaticBlock;

    public Keywords(){}
    public Keywords(String diff, String shared){
        differentForEachObject=diff;
        sharedForAllObjects=shared;
    }

    static {
        assignOnceViaStaticBlock = "rather than each new instance needing to set this, because it is same for every one, you can use static block to do it once";
    }                              //so use constructors to set non-static variables, use static block to set variable once for all objects
                                    //this will only run once, when class is loaded (when creating first object). class loads first, then create object, so static block runs before constructor. can have multiple static blocks

    //CONTINUE & BREAK
    public static void continueMethod(){   //as soon as execution reaches continue, it will exit current block
        for (short s=1; s<6; s++){
            if (s==3){
                continue;               //when s is 3, code will not execute the println, skipping remaining statements, and go straight back to the loop
            }                           //use continue when need to skip an iteration
            System.out.println(s);
        }
    }

    public static void breakMethod(){   //as soon as execution reaches continue, it will exit current block
        for (short s=1; s<6; s++){
            if (s==3){
                break;               //when s is 3, code will completely break out of the for loop that called it
            }                           //use continue when need to exit an iteration
            System.out.println(s);
        }
    }

    //FINAL
    //applicable for variables, methods, classes
    final double PI = 3.141;   //constant, cannot be changed. can also just declare in class and assign in constructor call
    //final class cannot be extended; see InheritanceDemo class demo
    //final method cannot be overridden; see InheritanceDemo class demo





    public static void main(String[] args) {
        continueMethod();
        breakMethod();

        //static keyword lessons
        Keywords kw1 = new Keywords();   //create empty object with no values
        Keywords kw2 = new Keywords("hello", "goodbye");   //create object with values
        System.out.println(kw1.sharedForAllObjects);  //despite kw1 not being assigned any values directly, its value for shared comes from kw2, as its a static variable
        System.out.println(Keywords.sharedForAllObjects); //because it is the same for all objects, you can retrive the static value directly from the class
        Keywords.sharedForAllObjects = "thanks";  //as its a static variable, you can set it directly on the class
        System.out.println(kw2.sharedForAllObjects);
        System.out.println(kw1.assignOnceViaStaticBlock);
    }


}
