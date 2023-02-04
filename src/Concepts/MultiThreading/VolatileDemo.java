package Concepts.MultiThreading;

public class VolatileDemo {
    /*
    Volatile variables are never cached by processors, always read directly from main memory
        so every time a processor operates on a variable, it must reconcile with the master variable in main
    Ensures updates to variable propagate predictably to other threads
        tells the runtime and processor not to reorder any instructions with volatile variables
        so is another way of making a class thread safe
            otherwise changes to a variable in 1 thread may not immediately reflect in another thread
    Problem Statement:
        multithreaded programs can have memory visibility issues; a thread might check/work with a variable having a specific value, while another
            thread is changing it, and the order that different threads see that lines have run in other threads can vary
        If threads run on different processors, they hold local copies of variables - so if one thread modifies its variable, this might not be seen in main
        memory immediately
        Shared variable in main memory -> 1st processor reads it, 2nd processor updates it -> but 1st processor has already cached the old value, so won't
            see the update
    Multithreaded programs rules:
        - Mutual exclusion = one 1 thread can execute a code block at a time, or update shared data
        - Visibility = changes to shared data by 1 thread are visible to other threads, for data consistency
        Synchronized methods & blocks provide this (at cost of app performance)
        Volatile keyword provides visibility, without cost of mutual exclusion (less overhead on the application);
            so multiple threads can execute code in parallel
            can be used with primitives or with objects, but not classes or methods
            means the value is not cached by the processor- it is always read from main memory
    Rarely needed as most operations do both read & write
        USE: if only performing read operations on a field variable
     */
    private volatile static int myInt;  //can only be used for field declarations (no classes, methods, local variables or parameters)
    private volatile boolean myBool;
}
