package Concepts.MultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Lets the machine run multiple tasks at a time -> multiple threads running concurrently improves app capabilities
2 units of execution:
    Process = standalone execution environment with private runtime resources and memory. Can contain multiple threads, always at least 1
    Thread = The smallest sequence of instructions that can be managed independently by the scheduler and executed independently by the processor
        a task; like a lightweight process. has own execution environment, but creating new threads requires much less resources than processes
        different threads manage different aspects of a program. exist inside processes
        has dedicated resources allocated -> registry, stack memory
        States = Running, Sleep, Ready, Dead (completed), Awaiting IO Input
        Create via extends Thread or implements Runnable
Parallelism:
    Programs can consist of multiple processes
        Process = actual execution of the program
            different processes do not share resources, have different memory addresses
            alive as long as it contains a single active thread
        Single threaded process = 1 thread executes all operations
        Multi threaded process = Parallelism; multiple threads execute the operations concurrently
            Multiple threads can can exist in a process and share its resources e.g. stack memory, heap memory, code
            intra-thread communication within a process is faster than inter-process communication due to resource sharing
            CPU core count defines max number of parallel threads
                when thread count exceeds cores, processor grants illusion of parallelism by allocating minuscule time slots to each operation
    Drawbacks: threads share resources e.g. access same file, so one idly waits for other to finish, so move from parallel to concurrent
                    Scheduler fixes by ensuring efficient distribution of processor time across threads
                        assigns prioritization to threads according to set rules
    Multi-process vs multi-threaded
        multi-process = if 1 process crashes, rest can continue
            better memory management and optimization for current activities, so higher performance & less glitches
        multi-threaded = if 1 thread crashes, the whole program crashes

Concurrency: improves program performance, as it is faster.
    challenging as all simultaneously executing functions are all accessing same shared memory and reading/writing variables & objects
        can cause devlock, livelock and resource starvation problems




 */
public class MultiThreadingDemo {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new ThreadExtends();  //Initialize class containing run()
            thread.start();               //call start(), which itself calls run(), changing state from ready to running
        }

        // creating objects of my multithreaded class with extends Thread
        MultiThreadingWithExtends myThreadExtended = new MultiThreadingWithExtends(1);
        myThreadExtended.start();        //here java will execute the run() inside your objects class; create a new thread. Java branches off into run(), but still proceeds to continue down code in the main thread in main()


        for (int i = 2; i < 7; i++) {           // each loop will create a new multithreaded object at the same time and start() it
            MultiThreadingWithExtends myThreadLoopExtended = new MultiThreadingWithExtends(i);
            myThreadLoopExtended.start();   //so 5 new objects will then call run() to execute code inside
        }
        try {
            myThreadExtended.join();                // pauses all other threads until this thread completes; so here main() will stop until above threads complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // creating objects of my multithreaded class with implements Runnable
        MultiThreadingWithImplements myThreadImplements = new MultiThreadingWithImplements();
        Thread myThread = new Thread(myThreadImplements);
        System.out.println(myThread.isAlive()); //returns boolean of if thread currently running
        myThread.start();
        System.out.println(myThread.isAlive());

        //creating threads with ExecutorService
        try (ExecutorService executorService = Executors.newFixedThreadPool(10)) {
            Runnable myRunnable = () -> System.out.println("inside a new thread");
            for (int i = 0; i < 10; i++) {
                executorService.submit(printThreadRunning());   //can only submit a synchronized method / runnable object
                executorService.submit(myRunnable);
            }
            executorService.shutdown();
        }  //makes a pool of 10 threads

    }

    public static Runnable printThreadRunning() {
        return () -> System.out.println("thread is running");
    }

}
