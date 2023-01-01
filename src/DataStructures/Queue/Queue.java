package DataStructures.Queue;

public class Queue {   //FIFO - Implement with either array(flat or circular) or linkedlist
                        //track 2 positions; start and end variables
    int[] queue;
    int front;
    int end;
    int realSize;
    int potentialSize;

    public Queue(int potentialSize){
        this.queue = new int[potentialSize];
        this.potentialSize = potentialSize;
    }

    public void enqueue(int data){
        if (isFull()){
            System.out.println("\nQueue is already full");
            return;
        }
        queue[end] = data;
        end = (end + 1) % potentialSize;       //makes the array work as a circular array; once the end index hits the last position, its index + 1 will equal the potential array size, so % will produce 0, so it loops back to the start index
        realSize++;
    }

    public int dequeue(){
        if (isEmpty()){
            System.out.println("\nQueue is already empty");
            return 0;
        }
        int element = queue[front];
        front = (front + 1) % potentialSize;
        realSize--;
        return element;
    }

    public void printQueue(){
        System.out.print("\nElements: ");
        for(int i = 0; i< realSize; i++){
            System.out.print(queue[(front + i) % potentialSize] + " ");    //because postion of fron keeps shifting, so must set that as starting point in array
        }
    }



    public int getRealSize(){
        return realSize;
    }

    public boolean isFull(){
        return getRealSize()==potentialSize;
    }

    public boolean isEmpty(){
        return getRealSize()==0;
    }
}
