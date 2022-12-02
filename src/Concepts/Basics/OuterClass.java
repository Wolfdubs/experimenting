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
        Inner inner1 = out.new Inner();   //must create the outer class object, then, as new Inner() is an object of the inner class, you can onl access it via an object of the outer class
        inner1.display();    //now can call inner class methods
        OuterClass.Inner2 innerStatic= new OuterClass.Inner2();  //static inncer classes dont need object of outer to call inner's constructor
        innerStatic.displayStatic();
    }

}


