package Concepts.Basics.Variables;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;

import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

//Annotations provide metadata to compiler to give extra instructions; processed either at runtime, or compile time
public class AnnotationsDemo {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        SecondClass sc = new SecondClass();
        sc.show();
        sc.oldShow();   //Deprecated annotation means this line is added when method is called

        //Fetching annotation values for a class
        // 1. Create object of annotation implementing class
        Nokia nokia = new Nokia();
        //  2. Create object of the annotation, via creating a class object (aka Reflection API) to get values of the annotation
        Class<? extends Nokia> c = nokia.getClass();     //creates class object of nokia's class. Because it is the class that has the annotation on it, not the object
        c.isAnnotationPresent(SmartPhone.class);   //checks if the class has the annotation applied to it. returns boolean
        Annotation an = c.getAnnotation(SmartPhone.class);  //annotation is an interface, get object of it by getting the specified annotation of the input class (nokia)
        SmartPhone smartPhone = (SmartPhone) an;   //must cast the generic Annotation type

        //3. Can retrieve values from specific annotation now
        System.out.println(smartPhone.os());
        System.out.println(smartPhone.version());

        //using the method annotations; loop through methods in class and run if it has specified annotation
        for (Method method : nokia.getClass().getDeclaredMethods()) {   //gets all the methods in the class, returning an array of method objects
            if (method.isAnnotationPresent(RunImmediately.class)) {    //if the method has this annotation
                method.invoke(new Nokia());                            //then invoke it, passing in the object to run the method on
            }
        }

        //using method annotation field to determine number of times to run
        Method method = nokia.getClass().getDeclaredMethod("browseInternet");  //gets the specified method from the class
        MagicalSweatshop msAnnotation = method.getAnnotation(MagicalSweatshop.class);   //make the annotation object by getting the annotation from the method
        msAnnotation.times();   //retrieve the value set for the annotation by the method
        for (int i =0; i<msAnnotation.times(); i++) {//can use this times field value to determine how many times to run the method
            method.invoke(new Nokia());
        }

        //using the field annotations of a class
        Field[] fields = nokia.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(FieldAnnotation.class)) {
                //get the value of the field with that annotation, in the nokia object
                Object objFieldValue = f.get(nokia);  //because the field could be of any type, not necessarily a string, must wrap as an object
                if (objFieldValue instanceof String stringFieldValue) { //then cast the object to a string; 1st check if the object is already an instance String, and if so, cast it to a string with the name stringFieldValue
                    System.out.println(stringFieldValue.toUpperCase());
                }
            }
        }


        //Using Lombok getters, setters, toString;
        LombokDemo ld = new LombokDemo();
        ld.id();  //the getter. fluent means it doesn't need the get prefix
        ld.name();
        ld.id(3).name("womble").isAlive(true);    //chain=true allows chaining setters for the fields
        ld.toString();
        ld.hashCode();
        ld.equals(new LombokDemo());

        @SuppressWarnings("unused")    //stops the IDE telling you a variable is unused
        LombokDemo ld2 = new LombokDemo();



    }

}

class FirstClass{
    public void show(){
        System.out.println("implementation in FirstClass");
    }
}

class SecondClass extends  FirstClass{
    @Override   //useful if method name could be misspelled, as will throw compile time error highlighting the method is not the same name. but not required
    @SuppressWarnings("uncheck")  //will suppress warnings shown creating an ArrayList with no type specified
    public void show(){
        System.out.println("implementation in SecondClass");
        ArrayList unSpecifiedType = new ArrayList();
    }

    @Deprecated  // says this method is no longer to be used. but doesn't stop you using it
    public void oldShow(){
        System.out.println("deprecated method can still be called");
    }
}

@FunctionalInterface   //will throw compile error if you try to add a 2nd method. Ensures only SAM
interface functionalInterfaceDemo{
    void doStuff();
}
/////////////////////////////////////////////////////////////////////////////////////////////
//CREATING CUSTOM ANNOTATIONS

@interface myMarkerAnno{}   //Marker Annotations = annotations with no definition

//all annotations extend Annotation interface
//when creating an annotation, to create object of it requires 2 extra Meta-Annotations; Target & Retention
@Inherited  //optional meta-annotation that means subclasses of classes with the annotation will also have it
@Target({ElementType.TYPE, ElementType.METHOD})    //when creating annotation, must specify at what level it will be used (class, method, field, etc). ElementType.TYPE means for classes/interfaces. Can select multiple types. Can leave blank to make applicable to any java element
@Retention(RetentionPolicy.RUNTIME)   //until at what point the annotation will be available. Runtime good for 99% of cases; tells java to keep annotation through the actual running of the program so other code can see it and use it during runtime. .SOURCE means java gets rid of annotation before compilation. .CLASS means java gets rid of annotation just after compilation, when runtime starts.
@interface SmartPhone{   //@interface is command to create a SmartPhone annotation, whose properties you then define.
    String os();           //creates a field that all implementing classes must have
    int version() default 1;        //Multi Value Annotations have multiple values, vs Single Value Annotations.  Can set default values classes will have. if have a default, implementing element doesnt have to provide a value, otherwise it must
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface RunImmediately{
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MagicalSweatshop{            //custom annotation with parameters
    int times();                       // permitted types are only; primitives, Strings, arrays, enums or Class type
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldAnnotation{}


//to specify that the Nokia class is a SmartPhone
@SmartPhone (os="Android", version = 8)   //must define the values within the interface that the class will thus have. if you dont, it uses any defaults
class Nokia{
    //applying custom annotations to fields too
    @FieldAnnotation
    int price;
    //applying custom annotations to methods as well
    @RunImmediately
    public void sendMessage(){System.out.println("I'm sending a message");}
    @MagicalSweatshop(times = 5)   //have to initialize the field in the annotation when we invoke it. can then use this field for the method calls in main()
    public void browseInternet(){System.out.println("I'm browsing the internet");}
    public void takePhoto(){System.out.println("I'm taking a photo");}

    public static void main(String[] args) throws NoSuchMethodException {
        Nokia nokia = new Nokia();
        Method methodVal = nokia.getClass().getMethod("takePhoto");
        SmartPhone smartPhone = methodVal.getAnnotation(SmartPhone.class);   //use reflection API to get annotation values
        System.out.println("Smartphone details = " + smartPhone.os() + "  " + smartPhone.version());

    }
}


//marker annotation is an empty annotation with no metadata logic; contains no code. just for giving very high level instructions to the compiler
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MarkerAnnotation {

}





////////////////////////////////////////////////////////////////////////

//LOMBOK ANNOTATIONS
//java library that manages boilerplate code
//Lombok autogenerates getters, setters, equals, toString, hashCode, so don't need boilerplate code
    //for fields in the class. when class is compiled, lombok creates the methods and adds them to the compiled class file
//to use Lombok annotations, must download jar file from projectlombok.org and add to project library
//lazy generation -> method only generated when called

@NoArgsConstructor  //generates default empty constructor
@AllArgsConstructor  //constructor that accepts all fields of the class
@RequiredArgsConstructor  //constructor with only the variables marked final or NonNull (as they must be assigned a vlaue when creating an object)
@Getter (AccessLevel.PROTECTED)  //getter & setter auto set to public, but can specify differently
@Setter @Accessors(chain = true, fluent = true)  //get/set customization. chain enables chaining of getter/setter calls for each field.
            // fluent removes set/get prefix from the getter/setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // autogenerates an equals method to check objects of the class are the same, and also a hascode method
@ToString (includeFieldNames = false)  //toString will only contain the values
            //if a subclass & superclass both have Lombok toString, can choose to call the supers via callSuper = true. same for @EqualsAndHashCode
@Data    //Shortcut that combines @Getter, @Setter, @EqualsAndHashCode, @ToString and a canonical constructor (@RequiredArgsConstructor) all into 1 clean annotation
@Builder(setterPrefix = "add")  //generates static builder method for the class, so you create instances via ClassName.builder()
@Log   //generates logger field named log whose type depends on the specific annotation version used. requires extra dependencies added to project
@Log4j   //example alternative log library
class LombokDemo{
    @EqualsAndHashCode.Include  //specifying the fields to include for @EqualsAndHashCode
    String name;    //lombok won't work if these fields are declared private
    @EqualsAndHashCode.Include
    @NonNull   //marks field as required to be given a value, else throws NullPointerException
    int id;
    @Getter(AccessLevel.PRIVATE)
    boolean isAlive;
    @ToString.Exclude  //will exclude the below field from the toString
    final List<String> data = initData();

    public List<String> initData(){
        return new LinkedList<>();
    }

    public static void main(String[] args) throws IOException {

    val valInt = 9;  //val can be used a type of local variable declaration instead of writing the actual type
    val valMap = new TreeMap<String, Double>();   //val marks the variable as final, so cannot reinitialize/reassign
    valMap.put("womble",4.44); valMap.put("mungo",2.33);
    for (val entry : valMap.entrySet()) {    //using val here means no concern about the types in the map
        System.out.println(entry.getKey() + entry.getValue());
    }
    LombokDemo lombokDemo = LombokDemo.builder().build();
    LombokDemo lombokDemo1 = LombokDemo.builder()
            .addId(1).addName("jambo").addIsAlive(false).build();  //specify add due to Builder customization

    @Cleanup  //ensures resources closed before code execution exits current scope
    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("dummyFile.txt")));
    @Cleanup
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("dummyOutput.txt"));
    bufferedWriter.write(bufferedReader.readLine());




    }
}

