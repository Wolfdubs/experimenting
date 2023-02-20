package DesignPrinciples;

import java.util.ArrayList;
import java.util.List;

public class DependencyInversionPrinciple {
    /*
    "We must depend on abstractions, not concrete classes"
    all that is depended on should be abstractions
    Modules: high-level modules should not depend on low-level ones, but on abstractions
        rather than classes in Module 1 relying on classes in Module 2, utilize interfaces as per Open-Closed principle

    Module 1: classes & interfaces
    Module 2: classes that depend on Module 1 interfaces

     */
    //design principle whereby you declare objects of the higher supertype, and instantiate with the actual implementation you want e.g.
    List<Integer> myList = new ArrayList<>();

}

//DAO
//DTO - Data Transfer Object classes


