package Concepts;

import java.util.Optional;

/*
A container that may or may not contain something
If you have a 1st method that could return null; e.g. if searching for something in a DB but it is not there
If a 2nd method uses the return of the 1st method, it will throw NullPointerException if 1st returned null
    Could wrap 2nd method in a null check
    Or have the 1st method return an Optional
        if it has an actual value to return, it puts it into the Optional, else returns the empty box
        Then have 2nd method can check if the Optional contains a value and branch from there
USE: optional tells user of a method that the value they want might not exist and they must account for that
    use as the return type to a method that could return null
 */
public class OptionalsDemo {
    public static void main(String[] args) {
        Optional<Employee> optionalEmployee = getEmployeeFromDB("Navin");

        //Must check if the Optional actually contains a value, else get() throws an Exception
        if (optionalEmployee.isPresent()) {   //returns true if the Optional contains something, false if ti is empty
            optionalEmployee.get();           //retrieves the actual employee object from within the Optional container
            optionalEmployee.get().getJobTitle();   //can call the objects methods after removing from the optional
        }

        //if the optional contains a value, this will return it, or else (if container is empty) it will return the argument
        optionalEmployee.orElse(new Employee("UNKNOWN", 0));
        optionalEmployee.orElseGet(() -> new Employee("UNKNOWN", 0));  //if Optional is empty, values derived from lambda
        optionalEmployee.orElseThrow();  //if Optional is empty, it throws an Exception (just like get())

        //Can transform the Option from 1 type to another, e.g. from Employee to Partner
        optionalEmployee.map(Employee::getJobTitle)   //Transforms the Optional<Employee> into an Optional containing that employees JobTitle field
                .orElse("N/A");      //retrieve the value from the .map() call (if the optionalEmployee has a value, and its jobTitle does too,
                                        // (if no value in either of those then return N/A)


        Optional.empty();   //creates an empty optional
        Optional.of(optionalEmployee);     //Optional.of() can only accept a non null object here

    }

    private static Optional<Employee> getEmployeeFromDB(String name){    //specify the method will return an Optional that could contain an Employee
        Employee employee = new Employee(name, 60000);
        return Optional.ofNullable(employee);     //add the employee object to be inside the Optional container
                            //this value could either be null, or not - the Optional will accept either. So if employee is null, it will create an empty Optional
    }

}
