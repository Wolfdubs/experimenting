package Concepts;

import java.util.concurrent.*;

/*
3 key types of java.net.concurrent
    Executor = interface to define subsystems to create with threads.
        help with asynchronous (don't wait for a task to be completed after initiating, just move on) IO tasks or thread pool
    Executor Service = more asynchronous task execution framework. creates a pool of threads to perform the tasks passed to submit()
    Future = fetches the result of a submitted task.

 */
public class ConcurrencyDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        executorServiceDemo();
        futureDemo();
    }

    private static void executorDemo() {
    //    Executor executor = new Caller();
    //    executor.execute(() -> System.out.println("executor demo"));
    }
    private static void executorServiceDemo() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<?> future = executorService.submit(() -> System.out.println("executor service demo"));
        Future.State state = future.state();
        var myVar = future.get();
        Boolean bool = future.isDone();
        System.out.println(state + "\t" + state.toString() + "\t" + myVar +  "\t" + bool);
    }
    private static void futureDemo(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();  //initialize a single-threaded executorService, uses a single worker thread in an unbounded queue. so if the thread terminates prematurely, a new thread will replace it
        Future<String> future = executorService.submit(() -> {   //return a Future object
            Thread.sleep(10000);
            return "future demo";
        });
        try {
            while (!future.isDone()) {
                System.out.println("future task still in progress");
                Thread.sleep(500);
            }
            System.out.println("Future task completed");
            String result = future.get(3000, TimeUnit.MICROSECONDS);    //returns the state of the future object
            System.out.println(result);
        } catch (InterruptedException | ExecutionException | TimeoutException futureException) {
            future.cancel(true);
            future.isDone();
            future.isCancelled();
        }
    }
}

















