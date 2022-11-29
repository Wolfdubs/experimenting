package DataStructures.Queue;

public class QueueMain {
    public static void main(String[] args) {

        Queue myQueue = new Queue(5);
        myQueue.enqueue(17);
        myQueue.enqueue(62);
        myQueue.enqueue(39);
        myQueue.enqueue(1);
        myQueue.enqueue(6);
        myQueue.printQueue();
        myQueue.dequeue();
        myQueue.enqueue(76);
        myQueue.printQueue();
        myQueue.enqueue(11);
        System.out.println(myQueue.isEmpty());
        System.out.println(myQueue.isFull());
        System.out.println(myQueue.getRealSize());
        myQueue.dequeue();
        myQueue.dequeue();
        myQueue.dequeue();
        myQueue.dequeue();
        myQueue.dequeue();
        myQueue.dequeue();
        System.out.println(myQueue.getRealSize());

    }
}
