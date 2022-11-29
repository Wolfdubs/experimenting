package Concepts.MultiThreading;

public class MultiThreadingWithExtends extends Thread{

    private int threadNumber;
    public MultiThreadingWithExtends(int threadNumber){
        this.threadNumber=threadNumber;

    }
    @Override
    public void run(){                           // must overwrite the Thread class default run() with the code you want your multithreaded object to execute
        for (int i = 1; i<=5; i++){              //each loop will be executed concurrently by a different thread
            System.out.println(i + " has been printed by thread " + threadNumber);
            if (threadNumber==3){
                throw new RuntimeException();    //demo that if 1 thread has exception/error, the other will still continue on fine
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
