package Concepts.Basics;

public class OuterClass {

    int a;
    public void show(){}

    /*3 types of inner classes
        - Member classes
        - Static classes
        - Anonymous classes
    //Inner classes useful only functionality will only help outer class, only works to help outer class*/
    class Inner{             //is a member class contained by the outer class
        public void display(){
            System.out.println("In inner class' Display Method");
        }
    }

    static class Inner2{ //can have multiple inner classes. inner classes can also be static -> means dont need to call the constructor on an object of outer
        public void displayStatic(){
        System.out.println("In Static inner class' Display Method");
    }
    }

    public static void main(String[] args) {
        OuterClass out = new OuterClass();
        //Inner inner = new Inner();     cannot create an inner class directly
        Inner inner1 = out.new Inner();   //must create the outer class object, then, as new Inner() is an object of the inner class, you can only access it via an object of the outer class
        inner1.display();    //now can call inner class methods
        OuterClass.Inner2 innerStatic= new OuterClass.Inner2();  //static inncer classes dont need object of outer to call inner's constructor
        innerStatic.displayStatic();



        //Using example of below outer & inner classes
        //MEMBER/NON-STATIC INNER CLASS
        //To access variables from inner class, need object of outer class. cannot directly construct it from main();
        //must first create object of outer class, then use it to create inner class object
        OuterClassDemo outer = new OuterClassDemo();  //this object contains instance class just like instance variables
        OuterClassDemo.InnerClassDemo inner = outer.new InnerClassDemo();   //call the inner class constructor from outer class object
        inner.j = 5;    //how to access variables of inner class

        //STATIC INNER CLASS
        //Can access static inner class directly without needing to create object of outer class
        OuterClassDemo.StaticInnerClassDemo staticInner = new OuterClassDemo.StaticInnerClassDemo();

        //ANONYMOUS INNER CLASS
        //rather than creating an entire subclass just to override methods of a parent class
        //to dynamically implement the methods of a class, to override the implementation on the fly
        //so create the object, and the the implementation (reverse of normal)
        //     for any class, you can override its non final methods just like this
        //     usually used to define interface method implementations; dont have to create new class
        //        cannot create objects of interface because interface doesn't give implementation, but anonymous class does, so you can create interface object
        OuterClassDemo ocd = new OuterClassDemo(){
            @Override
            public void show() {
                System.out.println("inside overridden anonymous class show method");;
            }
        };

    }
}


/*
3 types of Inner Classes
    1. Member/Non-Static Inner Class
    2. Static Inner Class / Nested Inner Class
    3. Anonymous Inner Class
    4. Lambda Inner Class (ony applicable to SAM interfaces)
 */

class OuterClassDemo {
    int i;
    public void show(){
        System.out.println("inside original show method");
    }

    class InnerClassDemo{   //Member inner class - like an instance class of A. It compiles as OuterClassDemo$InnerClassDemo.class
        int j;
    }

    static class StaticInnerClassDemo{
        int k;
    }
}
