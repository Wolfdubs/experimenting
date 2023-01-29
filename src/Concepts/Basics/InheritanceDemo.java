package Concepts.Basics;

/*
Inheritance means that everything in a subclass gets everything from the superclass "for free".
    That means that we can call all superclass methods on any subclass (but not the reverse!).
Only protected and public fields and methods are inherited -- private ones are not!
Java looks in the instantiated type of the object to find an implementation for a method, and if it doesn't find one, it looks up the hierarchy.
 */
class InheritanceSuper {       //Super/parent/base.    keyword super() represents it, so it can be called from child class

    int i;
    private int j;   //subclasses will not inherit this private field
    public InheritanceSuper(){
        System.out.println("inside super constructor");
    }
    public InheritanceSuper(int n){
        System.out.println("inside parameterized int super constructor");
    }
    public InheritanceSuper(double d){
        System.out.println("inside parameterized double super constructor");
    }
    protected int add(int i, int j) {
        return i+j;
    }  //means add() can only be called by subsiding classes

    protected void overrideMethod(){
        System.out.println("Inside super's override method");
    }
    private void superPrivateMethod(){      //subclasses don't inherit private fields or methods
        System.out.println("Subclasses will NOT inherit this method");
    }
    public void protectedMethod(){}
    final void finalMethod(){}  //subclasses cannot override final methods
}

class InheritanceSub extends InheritanceSuper{     //sub/child/derived
    public InheritanceSub(){
        //every child class has by default a call to the super class constructor, just as if it explicitly had super(). super() calls constructor of super class
        System.out.println("inside sub constructor");  //this is why super class always has default constructor called even with parameterized subclass constructor calls
    }
    public InheritanceSub(int n){
        System.out.println("inside parameterized int sub constructor");
    }
    public InheritanceSub(double d){
        super(d);           //can tell java to invoke super class parameterized constructor by callings that specific constructor in sub constructor
        System.out.println("inside parameterized double sub constructor");
    }

    @Override  //best practice to include for method overriding, because compiler will make sure there is a super method to override; ie you didn't misspell the method in the sub class
    public void overrideMethod(){   //overrides the same method of the super class
        super.overrideMethod();   //can also make a call back to super's methods
        System.out.println("Inside subs's override method");
       // super.overrideMethod();   //if you wanted to run the super classes override method too
        super.i = 8; //can access super class variables via call to super()
    }

    @Override
    public void protectedMethod() {  //cannot make this private, as cannot assign weaker privileges to superclass members
        super.protectedMethod();
    }

    public final void subOnly(){     //child classes cannot override this method, must follow initial implementation\
        System.out.println("only the sub has this method");
    }

}

public final class InheritanceDemo extends InheritanceSub{    //final class means no other classes can extend it

    public InheritanceDemo(){
        System.out.println("inside grandchild constructor");
    }
    @Override  //best practice to include for method overriding, because compiler will make sure there is a super method to override; ie you didn't misspell the method in the sub class
    public void overrideMethod(){   //overrides the same method of the super class
        System.out.println("Inside grandchild's override method");
    }


    public static void main(String[] args) {

        InheritanceSub ic = new InheritanceSub();   //creating object of child class will call both the child and the parents constructor
        ic.add(3,4);               //sub class has access to the add() method despite not implementing it, because it can access super parent's implementation

        InheritanceDemo id = new InheritanceDemo();  //this will call constructor of every class up the chain it extends
        id.add(8,10);     //multi-level inheritance means this child child-class can access super class methods

        //creating a subclass object via parameterized constructor, will still on the superclass only invoke its no-args constructor via auto super()
        InheritanceSub icp = new InheritanceSub(3);
        InheritanceSub icp2 = new InheritanceSub(3.0);   //this will enter the supers parameterized constructor, as sub has call to super(d);

        icp.overrideMethod();

        //2 polymorphism types; compile time & runtime. Objects created during runtime, so decision of implementation is at runtime
        InheritanceSuper superObject = new InheritanceSub();   //can create an object of sub with a reference type of super
        superObject.overrideMethod();    //the method will still call the implementation of the sub,
        // superObject.subOnly();        // but cannot call any sub only methods not in super because super doesnt know about that class
                //Java looks in the declared type of an object to see what methods are available to that object
                // (it doesn't matter how the object is instantiated)
        superObject = new InheritanceDemo();
        superObject.overrideMethod();       //Dynamic Method Dispatch; whereby runtime polymorphism decisions which implementation to run based on the object class
            /*
            Dynamic method dispatch use:
                Say you have a class called Product.
                Now, we have products of different types like Mobile, Shirt, etc... which extend the Product class.
                Say, there is a method:
                void printFeatures(Product p)
                Please note that here the parameter is of the Object type, rather Product type parameter.
                NOW IN FUTURE, WE DONT KNOW WHAT OTHER TYPES OF PRODUCTS MAY BE INVENTED/DISCOVERED/ADDED
                SO, TO ENSURE CODE REUSABILITY, AND ALSO TO NOT REPEAT CODE AGAIN AND AGAIN FOR DIFFERENT/NEW CLASSES THAT EXTEND THE PARENT CLASS, (DRY DONT REPEAT YOURSELF PRINCIPLE), WE PASS THE PARENT REFERENCE TO THE METHOD THAT WILL ITSELF SEE WHICH OBJECT/CLASS TYPE IT IS LINKED TO AND EXECUTE THAT PARTICULAR CHILD CLASS' IMPLEMENTATION OF THE METHOD.
                HERE, PRINTFEATURES WOULD PRINT MOBILE SPECIFIC FEATURES WHEN P2  IS PASSED AND SHIRT FEATURES WHEN P3 IS PASSED AS A PARAMETER.

                Ex:
                class Mobile extends Product{...}
                class Shirt extends Product{...}

                Product p1 = new Product();
                Product p2 = new Mobile();
                Product p3 = new Shirt();

                printFeatures(p1);
                printFeatures(p2);// PRINT MOBILE SPECIFIC FEATURES
                printFeatures(p3);// PRINT SHIRT FEATURES WHEN P3
             */

    }
}

//java does not support multiple inheritance; can only extend 1 class
    //because of ambiguity problem: if 2 super classes implement a method, and sub calls it, which should it use?
//2 types of inheritance
//   > is-a: when class extends another class
//   > has-a: when a class creates an object of another class; it has the object




