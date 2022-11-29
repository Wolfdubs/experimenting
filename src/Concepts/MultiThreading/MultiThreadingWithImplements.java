package Concepts.MultiThreading;

public class MultiThreadingWithImplements implements Runnable{

    @Override
    public void run(){
        for (int i = 1; i<=5; i++){              //each loop will be executed concurrently by a different thread
            System.out.println(i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
