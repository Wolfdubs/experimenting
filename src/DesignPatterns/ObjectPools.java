package DesignPatterns;

/*
allow re-use when you have many expensive objects, to avoid re-initializing them
create array for objects, then initialize them, then when required, grab it from array
Not needed anymore as Java & computers have enough memory and power to cheaply re-initialize
Problem: prevent threads accessing objects at same time so require synchronization
    complexity in managing the objects
    pressure on GC as they are never freed, so heap can grow
Can use for:
    DB connections, as want to keep them open, and are expensive to make
    Threads; very expensive to create, so use thread pool
    Large arrays
    Random objects
 */
public class ObjectPools {

}
