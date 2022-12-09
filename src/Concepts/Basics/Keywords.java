package Concepts.Basics;
//POJO = plain old java object. is just a regular java class/interface
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


        //super keyword lessons
        SuperDemo sd = new SuperDemo("do", 3, 1.1);
        sd.show();
        sd.showParent();
        sd.writeCode(); //subclass can directly call super classes methods (not related to super keyword, just inheritance)
    }

}
//super = to access items in the super class of the class you're currently working in
    //2 uses:
    // To call methods in the superclass that the child class has overridden
    // To call superclasses constructors (can only do this in subclasses constructors)
//can only use it inside the subclass implementation, not with objects created of the subclass e.g. while in main()
//super cannot access any private properties of the superclass
class SuperDemo extends SuperDemoParent{
    double trouble;

    public SuperDemo(String name, int id, double trouble) {
        super(name, id);     //calling the super's constructor to set the fields for the sub (that it inherited from super).
                            // calls the constructor matching the parameter types passed
                            //must be done in the 1st statement of the subclasses constructor
                            //without this, java will still automatically call super's default no args constructor, so as if doing super()
                                //only have a default no-args constructor if you haven't added one of your own, so must define it if also using others and still want to be able to call super()
        this.trouble = trouble;}   //normal assignment for the sub's unique field


    @Override
    public void show(){
        System.out.println("inside sub class");
        super.show();   //calling the method implementation from the superclass. can do this from any non-static method in the subclass, not just one with the same name
    }
    public void showParent(){
        super.show();
        writeCode();   //can also call super methods the sub does not override at all. don't need to reference super for this
       //denyAccess();   private method of super, so sub cannot call it
    }
}

class SuperDemoParent{
    String name;
    int id;

    public SuperDemoParent(String name, int id) {    //sub can invoke the super's constructors
        this.name = name;
        this.id = id;}


    public void show(){
        System.out.println("inside super class");
    }
    public void writeCode(){
        System.out.println("Super is writing code: sub will not overwrite this method");
    }
    private void denyAccess(){
        System.out.println("private method so sub cannot call super on this");
    }
}
