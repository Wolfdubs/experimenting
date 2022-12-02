package Concepts.MultiThreading;

/*
to allow applications to run multiple paths of execution / processes at once
thread = unit of process
 by default, main() is a thread you always have
Utility:
  1. utilize capabilities of multi-core CPUs; split tasks across threads to run in parallel, so run faster
  2. mean applications won't freeze when waiting for some execution to complete; e.g. sending request and waiting for response. processes can run asynchronously
  3. allow servlet to process many requests from different users simultaneously; new thread of the servlet for each
Extends vs Implements
    Extends better if want to use inbuilt Thread methods, as Runnable interface has only SAM
    Implements better if want class to be able to extend a different class; avoids multiple inheritance problem

*/

/*
Use:
1. Create class that extends Thread or implements Runnable
2. Override run() method with code for the thread to execute
3. Create object of your class
4. Call the start()
     - For extends: call start() directly on your object, to create a new Thread that auto executes run()
     - For implements: create new Thread object and pass in your runnable class object, then call start()

*/


import java.util.concurrent.atomic.AtomicInteger;

class ThreadExtends extends Thread{  //means objects of this class with be Threads
    int[] myArray = {2,4,6,8,10};
    public void run(){         //every thread needs a run() method to define code for the thread to execute
        for (int i=0; i<myArray.length; i++){
            System.out.println("Thread 1");
            try{Thread.sleep(500);} catch(Exception e) {e.printStackTrace();}   //can even call thread.sleep on normal classes to main the main() thread sleep
            myArray[i] = myArray[i]*2;
        }
    }
}

class ThreadExtends2 extends Thread{  //means objects of this class with be Threads
    int[] myArray = {2,4,6,8,10};
    public void run(){         //every thread needs a run() method to define code for the thread to execute
        for (int i=0; i<myArray.length; i++){
            System.out.println("Thread 2");
            try{Thread.sleep(500);} catch(Exception e) {e.printStackTrace();}
        }
    }
}

//Implement runnable
// Runnable is a Functional Interface (only 1 SAM is run()) -> so can use anonymous classes & lambdas
class ThreadImplements implements Runnable{   //means you dont use up the 1 permitted extends
    int[] myArray = {2,4,6,8,10};
    public void run(){         //every thread needs a run() method to define code for the thread to execute
        for (int i=0; i<myArray.length; i++){
            myArray[i] = myArray[i]*2;
            System.out.println(myArray[i]);
        }
        for (int i : myArray){System.out.println(i);}
    }
}

public class MultiThreadingTelusko {

    public static void main(String[] args) throws InterruptedException {

        ThreadExtends myThread = new ThreadExtends();
        myThread.run();  //will not create a new Thread
        myThread.start();  //will create new Thread and then call run()


        //making the different threads run at the same time
        ThreadExtends threadExtends = new ThreadExtends();
        ThreadExtends2 threadExtends2 = new ThreadExtends2();
        threadExtends.start();  //will create new Thread and then call run()
        threadExtends2.start();  //both will alternate execution in random sequence as they are chosen by CPUs scheduler, unless one has a longer sleep which means scheduler will prioritize quicker thread, so will alternate

        ThreadImplements threadImplements1 = new ThreadImplements();
        Runnable threadImplements2 = new ThreadImplements();   //can create object of your class with reference to interface it implements
        // threadImplements.start();   Runnable interface doesn't have a start() so cannot call it, instead;
        Thread t1 = new Thread(threadImplements1);   //can pass in the object of Runnable to Thread object
        Thread t2 = new Thread(threadImplements2, "my second thread name");  //giving name to the thread
        t1.start(); //this will call your custom run() inside your runnable class
        t2.start();
        System.out.println(t1.isAlive()); //returns true/false thread is still running

        //main() continues its own thread execution even while custom threads run
        t1.join();          //can force a thread to stop & wait for another thread to finish executing. Throws InterruptionException
        t2.join();         //sometimes main can finish before custom thread, so you should make main wait at least before it terminates
        t1.setName("my first thread");        //Can give names to threads. default thread names e.g. Thread-0, Thread-1.
        t1.setPriority(10);  //default priority always 5. range = 1-10 (10=highest priority)
        t2.setPriority(Thread.MIN_PRIORITY);   //alternate way to set priority via inbuilt constant

        //Creating interface object via anonymous class implementation, rather than having to define an entire Runnable class for it
        Runnable runnableObject = new Runnable() {
            public void run() {
                System.out.println("inside anonymous class runnable thread");
            }
        };
        Thread t3 = new Thread(runnableObject);
        t3.start();


        //Lambda; rather than creating an implementing interface just to use only 1 method; run()
        Runnable runnableLambda = () -> System.out.println("lambdas version of run");
        Thread t4 = new Thread(runnableLambda);
        t4.start();

        //Lambda 2; as only using the runnable objects once, can make them lambdas too and paste implementation directly into the new Thread
        Thread t5 = new Thread(() -> {
            System.out.println("inside double lambda thread");
            System.out.println("Priority = " + Thread.currentThread().getPriority());
        });   //can get priority of current thread
        t5.start();


        /*Synchronization & Thread safety
          Problem:
            if multiple threads are calling same method to mutate a variable at the same time;
               one thread retrieves variable's value and by the time it mutates that value, the other thread may have already changed it.
               both might mutate the same variable at the same time
               --> means method is not thread safe: will produce inconsistent values
          Solution:
            1. make method synchronized so that only 1 thread can execute it at a time; locks method while thread is executing it
               any method accessed by threads should have keyword synchronized
            2. Use Atomic wrapper class. Is built in wrapper for all primitives that make their variables thread safe
         */
        SynchronizedClass sc = new SynchronizedClass();
        sc.synchronizedMethod();
        sc.atomicWrapperMethod();
    }
}

class SynchronizedClass {
    int counter;
    public synchronized void synchronizedMethod() {   //synchronized keyword means method will only allow 1 thread access at a time
        counter++;
    }

    AtomicInteger atom = new AtomicInteger();
    public void atomicWrapperMethod(){
        atom.incrementAndGet();
    }
}





