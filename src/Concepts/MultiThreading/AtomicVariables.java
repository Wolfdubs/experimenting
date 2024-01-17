package Concepts.MultiThreading;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.LongBinaryOperator;

public class AtomicVariables {
    /*
    To execute read/write operations in thread safe way on 1 field variable only without having to deal with synchronized keyword
    atomic operation = smallest possible operation e.g. i = 1;
                        an operation that is performed entirely, or not at all
                        operate in a thread safe environment
              i = i+1 is 3 operations; retrieving value of i, incrementing by 1, updating i to the new value
     */

    //e.g.
    protected class Pekingese{
        private static volatile int idCounter = 0;  //volatile, so value is shared across all threads
        private int id;
        public Pekingese(){
            this.id = getAndIncrementID();   //every new object gets an incremented id
        }
        private synchronized int getAndIncrementID(){  //also need this to be synchronized because every processor creating objects, must both read and write its values
            return idCounter++;
        }
    }

    protected class PekingeseAtomic{
        private AtomicInteger idCounter = new AtomicInteger(0);
        private int id;
        public PekingeseAtomic(){
            id = idCounter.incrementAndGet();   //incremements to 1 and returns 1
        }                           //ensures only 1 thread is incrementing the variable at a time, and its value is read directly from main memory, and returns the current increment
    }

    //Specific Atomic classes have get() and set() to read/write values from/to memory
    void specificAtomicClasses(){
        AtomicInteger atomicInteger;
        AtomicReference<String> atomicReference = new AtomicReference<>("womble");
        atomicReference.compareAndSet("womble", "alive");  //will just return "womble"
        atomicReference.compareAndSet("mungo", "dead");  //checks if the atomic reference value equals the 1st parameter. if not, it changes the object value to be the 2nf parameter //updates value to "dead"
        AtomicLong atomicLong = new AtomicLong(0L);
        atomicLong.accumulateAndGet(7L, (left, right) -> left * right);
        AtomicBoolean atomicBoolean;
    }

}
