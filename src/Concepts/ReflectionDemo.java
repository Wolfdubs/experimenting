package Concepts;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
/*
Reflection API is for calling private methods of other classes
 can also tell you about the fields and methods of a class
 Used just for debugging, not used for normal usage of programs

Do not use Reflection API unless you work on frameworks like Spring or libraries like Hibernate, Junit .etc.
Why not reflection?
1. It violates type-safety
2. Using it to invoke private methods violates encapsulation and security guarantees.
3. Since it is violates type-safety, it makes it exponentially harder to debug.
 */

public class ReflectionDemo {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        //can create an object using reference to the class "Class" (not related to reflection though)
        Class c = Class.forName("Concepts.Chemistry"); //put fully qualified package directory for class
        Chemistry chemistry = (Chemistry) c.newInstance();

        //create object of Method in order to call a private method from the Chemistry object
        Method m = c.getDeclaredMethod("experiment", null); //pass in method name, and its parameter types
        m.setAccessible(true);    //make the private method become accessible
        m.invoke(chemistry, null);  //pass it the object to run the method with, and any parameters


    /*More on "Class" class:
        class & interface files both compile to .class files. To discover what type a .class file contains
     */
        Class myClass = Class.forName("Concepts.Chemistry");
        System.out.println(myClass.isInterface());  //boolean for if the class type is an interface
        System.out.println(myClass.getSuperclass());   //returns superclass FQPN of input
        System.out.println(Arrays.toString(myClass.getDeclaredFields()));


    }
}

class Chemistry{
    String name;
    int beakers;

    private void experiment(){
        System.out.println("running experiment");
    }
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Write programs that can examine & change any element of any class while running
//To peek into the structure of a class
//USE: Used in frameworks e.g. Spring, which must look at all the classes you've written and create objects of those classes and
//      inject into other classes
//     For testing; e.g. set private fields in testing and ensure methods still perform how they should
//Dangers: can break your code and cause bugs e.g. changing the name of a method, means the
//          old name passed into the reflection invoke will not refer to an actual method, with no compilation error
//    Reflection runs at runtime, and does no compile time optimizations, so code using it is slower

class Reflection{
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Employee employee = new Employee("Kane", 77000);
        Field[] fields= employee.getClass().getDeclaredFields();  //returns an array of type Field
        for (Field f : fields){
            System.out.println(f.getName()); }

        //Reflection can mutate even private final fields
        for (Field f : fields){
            if (f.getName().equals("name")){   //finding the name field in the Employee class
                f.setAccessible(true);   //make the private field become accessible
                f.set(employee, "Kirk");    //for the specific object 'employee', setting that name field to Kirk
            }
        }
        System.out.println(employee.getName());


        Method[] methods = employee.getClass().getDeclaredMethods();
        for (Method m : methods){
            if (m.getName().equals("bathroomBreak")){     //finding a specific method
                m.setAccessible(true);                //now able to invoke this private method
                m.invoke(employee, 5, "tp please", true);         //then calling that non-static method on the object, and giving arguments
            }
            if(m.getName().equals("cashPaycheck")){
                m.setAccessible(true);
                m.invoke(null);    //as this method is static, just pass in null for the object
            }
        }

        Class myClass = Class.forName("Concepts.Employee");
        Method method = myClass.getDeclaredMethod("changeJobs");
        method.isAnnotationPresent(Override.class);
        Class[] parameters = method.getParameterTypes();
        method.getExceptionTypes();





    }

}

class Employee{
    private final String name;   //private and final fields still mutatable via reflection
    private int salary;
    private String jobTitle;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;}

    public String getName() {        return name;    }
    public int getSalary() {        return salary;    }
    public void setSalary(int salary) {        this.salary = salary;    }
    public String getJobTitle() {        return jobTitle;    }
    public void setJobTitle(String jobTitle) {        this.jobTitle = jobTitle;    }

    public void work(){        System.out.println("I'm working");    }
    private void bathroomBreak(int time, String message, boolean washHands) {
        System.out.println("Bathroom breaks are private, I need " + time + " minutes");
        System.out.println("I also wanna say: " + message);
        if (washHands) {System.out.println("I'm washing my hands");  }
    }

    public static void changeJobs() {        System.out.println("I gotta new job");    }
    private static void cashPaycheck() {        System.out.println("I love to cash my paycheck");    }

}
