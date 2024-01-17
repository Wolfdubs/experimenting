package Concepts.SpecificClassTypes;

import java.util.Objects;

class EmployeeClass {  //replaces having data carrying class; doesn't do any processing as no mutating methods, just stores data for object instances
                // where you have immutable data you won't change
                // but we have needed some many default boilerplate methods just for a data carrying class
                //   such classes must have the fields declared, constructors initializing the fields, getters & setters,
                //   overridden toString, overridden hashCode(), overriddeen equals()

    private final String name;
    private final int id;
    //example of class needing so much boilerplate just to hold values
    public EmployeeClass(String name, int id) {
        if (id<=0){
            throw new IllegalArgumentException("id must be positive");  //constructors have have checking logic for making new objects with appropriate values
        }
        this.name = name;
        this.id = id;}

    public String getName() {return name;}
    public int getId() {return id;}

    @Override
    public String toString() {    //means printing the object won't just print a meaningless hash value
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {    //overrides default equals that would compare if they were actually the exact same instance
        if (this == o) return true;      // now compares if the values of 2 instances are the same
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeClass employee = (EmployeeClass) o;
        return id == employee.id && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}

//massively simplifies need of creating entire Employee class with so many overridden methods, just to hold 2 variables
//record is a type of class, like an enum is
//record is a final class, cannot extend anything further, but can implement interfaces, and can add whatever methods you want
    //only cannot create instance variables -> can only do it in the instance declaration
//inside the () define all the fields the record must hold
    //java then autogenerates private final fields for each component listed,
    //autogenerates public getters as objectname.fieldname (aka no "get")
    //no setters though, as fields are final by default, so immutable. so once created, a record object cannot be changed
    //autgenerates a canonical constructor that assigns the field parameters to the objects fields values.
     //can still define own constructors though, and even override the default one eg. to do validation of values being set, e.g. no id <0)
    //autogenerates implementations for toString(), equals() and hashCode()
    //you can override these yourself though
//cannot extend any class. all implicitly extend the Record class, and no multiple inheritance
// can implement interfaces though
//implicitly are final classes, so no other classes can extend them


record EmployeeRecord(String name, int id) {   //This single line does exactly the same as all the boilerplate above
    public static final String MYFINALSTRING = "bleh";   //can define static fields.

    //  But NOT any non-static instance fields (aka they must be static). These must be defined at top inside the () after the record declaration
    public String nameInUpperCase() {    // you can also define instance methods e.g. field1InUpperCase() that can be called on record objects
        return name.toUpperCase();
    }

    public static void recordStaticMethod() {
        System.out.println("inside static method of record class");
    }   //can define static methods too


    //values default as private & final
//creating objects of this works exactly the same. behind the scenes, record creates parameterized constructor based on the record body, as well as .equals(), .toString()
//will disallow default constructor as you specified some state descriptions
//     but you can create different constructors yourself e.g. This is not recommended as it passes hardcoded values which then become final as records are immutable (as it is just data carrying object)
    public EmployeeRecord() {    //no-args constructor
        this("", 0);       //it must call the default parameterized constructor and pass in some specified values
    }
}



public class RecordDemo {
    //many classes are functional -> just do processing
    // Data carrying classes: some classes only used for storing data e.g. holding it on server to respond to requests, or to extract from DB

    public static void main(String[] args) {
        //Working with the data carrying class
        EmployeeClass employee1 = new EmployeeClass("Navin", 31);
        EmployeeClass employee2 = new EmployeeClass("Neeraj", 58);
        System.out.println(employee1.equals(employee2));    //overridden equals() now compares if 2 different instances have same values, rather that being same instance

        //Working with the equivalent record. commented out as only works with Java 17+
        EmployeeRecord er = new EmployeeRecord("Moojinder", 93);   //using the autogenerated canonical constructor
        //System.out.println(er.name);    //private instance vars mean cannot just call the autogenerated getter java creates for the record fields
        System.out.println(er);  //when you pass an object into sout, sout automatically calls the objects toString method
        System.out.println(er.nameInUpperCase());  //calling self-defined instance methods on record object
        EmployeeRecord.recordStaticMethod();
    }
}
