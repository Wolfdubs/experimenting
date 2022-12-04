package Concepts;

//unrelated - can import static reference objects
import static java.lang.System.out;    //static keyword used to import static reference objects e.g. .out
import static Concepts.StaticMethodDemo.show;

//Static block lets you execute code outside of the main(), without having to write anything inside main()
//static block is executed when class is loaded into JVM, executed before main()
//Use: can use static block to assign value to static variables

public class StaticBlockDemo {


    static {
        System.out.println("inside static block");   //this is called before main(), as its called when class is loaded into JVM
    }

    public static void main(String[] args) {
        //not related to the static classes, just to the static import
        out.println("using the static import for simplified println");
        show();
    }

    static {     //can have multiple static blocks, all of which are executed in sequence before main()
        System.out.println("inside second static block");
    }
}

//static methods are normally called by Classname.Methodname
//but if you import the method FQPN, then can just call it directly
class StaticMethodDemo{
    public static void show(){
        out.println("inside static method to show simplified calling of a static method, without needing classname too");
    }
}
