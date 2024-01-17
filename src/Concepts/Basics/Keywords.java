package Concepts.Basics;


//POJO = plain old java object. is just a regular java class/interface
public class Keywords {

    //STATIC
    //declares a field, method or inner class as a class property, so it belongs to the class, not an instance of the class
        //still accessible without objects of the class
        //classes have only 1 copy of static members
    //static methods and attributes can only operate on static attributes, not anything bound to an object
    String differentForEachObject;
    static String sharedForAllObjects;    //mean the variable is not specific to the specific object, but is true for all objects of the class. all objects share the same value
    static String assignOnceViaStaticBlock;       //getters for static variables must also be static

    static void staticMethod(){
        System.out.println(sharedForAllObjects);
        //System.out.println(differentForEachObject);       //static methods cannot access non-static members (as these can only exist within association to an instance)
        //this.differentForEachObject = "womble";          //cannot use 'this' in static methods or for static variables
    }


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
    final double PI = 3.141;   //constant, cannot be initialized more than once, cannot be changed
                            // can also just declare in class and assign in constructor call
                            //cannot assign a new class instance to same variable name
    final void finalMethod(){}   //final method cannot be overridden e.g by subclass; see InheritanceDemo class demo
    final class FinalClass{}   //final classes cannot be extended; see InheritanceDemo class demo


    //INSTANCEOF
    //boolean that returns if the thing on the left is an instance of whatever is on the right //can use for classes & interfaces
    void instanceOfTesting() {
        Keywords k = new Keywords();
        boolean isKeyword = k instanceof Keywords;
        boolean isString = "ssdsd" instanceof String;
        boolean isInt = (Integer) 6 instanceof Integer;
        //using with sub&super classes
        SuperDemo sd = new SuperDemo("a", 1, 2);
        boolean isSubOfSuper = sd instanceof SuperDemoParent;
    }

    protected String womble;  //only accessible by members of package, and by subclass children
            //different to default/none which cannot be inherited by subclass children
            //so subclasses only inherit public & protected fields; not private or unspecified
    private String mungo; //subclasses will not inherit this


    //ABSTRACT
    //classes cannot be final & abstract, as these clash
    //cannot have abstract variables
    abstract class AbstractClass {   //cannot be instantiated into an object
        void concreteMethod() {    //Abstract class can contain concrete methods
            System.out.println("this is a concrete method");
        }

        abstract void abstractMethod();           //inheriting class must implement all abstract methods.
    }                                      //only abstract classes can contain abstract methods


    //VAR
    //causes type inference to auto detect the variable datatype based on surrounding context
    //can use to declare any local variable
    //cannot use without explicit initialization, with lambda or method reference, or return type
    //var varString = "womble";        //cannot use to declare instance/global variable, or as Generic type
    void varMethod(){
        var varString = "kato";   //var doesn't make the variable final, so can be reinitialized/reassigned
        varString = "sita";
        var varInt = 8;
        var varBoolean = true;
        var varByte = (byte) 4;

    }











    public static void main(String[] args) {
        continueMethod();
        breakMethod();

        //static keyword lessons
        Keywords kw1 = new Keywords();   //create empty object with no values
        Keywords kw2 = new Keywords("hello", "goodbye");   //create object with values
        System.out.println(kw1.sharedForAllObjects);  //despite kw1 not being assigned any values directly, its value for shared comes from kw2, as its a static variable
        System.out.println(Keywords.sharedForAllObjects); //because it is the same for all objects, you can retrieve the static value directly from the class
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
/*
Calling just super() calls the constructor in the superclass
Calling super.someMethod() calls the superclass's implementation of someMethod
use calls to super from within the subclass's implementation of a method to extend the behavior of the superclass's method without
overwriting it completely.  So subclass.someMethod could, at some point, call super.someMethod() which would mean that someMethod
was augmenting the implementation of someMethod as opposed to replacing it entirely
 */
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

class AccessModifiers {

    public int i1;  //any class or package can use the method/field
    private int i2;  //only containing class can use the method/fields
    protected int i3;  //only same package or any subsiding class that extends/implements
    int i4;  //default: package protected. difference vs protected; protected can be accessed outside the package by extending/implementing classes.
        // so unless otherwise specified, class/variable/method cannot be accessed outside the package
    private class PrivateClass{}  //only inner classes can be private, as containing class can still access
    protected class ProtectedClass{}  //only inner classes can be protected
}





