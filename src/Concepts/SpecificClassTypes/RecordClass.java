package Concepts.SpecificClassTypes;

import java.util.Objects;

class Employee{  //data carrying class; doesn't do any processing as no mutating methods, just stores data for object instances
                // where you have immutable data you won't change
                // but we have needed some many default boilerplate methods just for a data carrying class
    private final String name;
    private final int id;

    public Employee(String name, int id) {
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
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}

/* records only supported from Java 17+
//massively simplifies need of creating entire Employee class with so many overridden methods, just to hold 2 variables
//record is a final class, cannot extend anything further, but can implement interfaces, and can add whatever methods you want
    //only cannot create instance variables -> can only do it in the instance declaration
record Employee() {String name, int id}   //values default as private & final

//creating objects of this works exactly the same. behind the scenes, record creates parameterized constructor based on the record body, as well as .equals(), .toString()
//will disallow default constructor as you specified some state descriptions
//     but you can create different constructors yourself e.g. This is not recommended as it passes hardcoded values which then become final as records are immutable (as it is just data carrying object)
    public Employee(){    //no-args constructor
        this("",0)       //it must call the default parameterized constructor and pass in some specified values
    }

 */

public class RecordClass {
    //many classes are functional -> just do processing
    // Data carrying classes: some classes only used for storing data e.g. holding it on server to respond to requests, or to extract from DB

    public static void main(String[] args) {
        Employee employee1 = new Employee("Navin", 31);
        Employee employee2 = new Employee("Navin", 31);
        System.out.println(employee1.equals(employee2));    //overridden equals() now compares if 2 different instances have same values, rather that being same instance
    }
}
