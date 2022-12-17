package Concepts.MultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

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
        ExecutorService executorService = Executors.newFixedThreadPool(10);  //makes a pool of 10 threads
        Runnable myRunnable = () -> System.out.println("inside a new thread");
        for (int i=0; i<10; i++) {
            executorService.submit(printThreadRunning());   //can only submit a synchronized method / runnable object
            executorService.submit(myRunnable);
        }
        executorService.shutdown();

    }

    public static Runnable printThreadRunning() {
        return () -> System.out.println("thread is running");
    }
}
