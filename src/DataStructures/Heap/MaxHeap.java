package DataStructures.Heap;

import java.util.Arrays;
import java.util.Random;

public class MaxHeap {

    private int capacity = 10;
    private int size = 0;
    private int[] maxHeap = new int[capacity];

    private int getLeftChildIndex(int parentIndex) {return (parentIndex * 2) + 1;}
    private int getRightChildIndex(int parentIndex) {return (parentIndex * 2) + 2;}
    private int getParentIndex(int childIndex) {return (childIndex / 2) -1;}

    private boolean hasLeftChild(int parentIndex) {return getLeftChildIndex(parentIndex) < size;}
    private boolean hasRightChild(int parentIndex) {return getRightChildIndex(parentIndex) < size;}
    private boolean hasParent(int childIndex) {return getParentIndex(childIndex) >= 0;}

    private int getLeftChildValue(int parentIndex) {return maxHeap[getLeftChildIndex(parentIndex)];}
    private int getRightChildValue(int parentIndex) {return maxHeap[getRightChildIndex(parentIndex)];}
    private int getParentValue(int childIndex) {return maxHeap[getParentIndex(childIndex)];}

    private void swap(int a, int b) {
        int temp = maxHeap[a];
        maxHeap[a] = maxHeap[b];
        maxHeap[b] = temp;
    }

    private void ensureAvailableCapacity(){
        if (size == capacity) {
            capacity *= 2;
            maxHeap = Arrays.copyOf(maxHeap, capacity);
        }
    }

    public int peek(){
        if (size==0) throw new IllegalStateException();
        return maxHeap[0];
    }

    public int poll() {
        if (size==0) throw new IllegalStateException();
        int root = maxHeap[0];
        maxHeap[0] = maxHeap[--size];
        heapifyDown();
        return root;
    }

    public void insert(int n){
        ensureAvailableCapacity();
        maxHeap[size++] = n;
        heapifyUp();
    }

    public void heapifyDown(){
        int index = 0;
        while(hasLeftChild(index)) {
            int largestChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && getRightChildValue(index) > getLeftChildValue(index)) {
                largestChildIndex = getRightChildIndex(index);
            }
            if (maxHeap[index] > maxHeap[largestChildIndex]) {
                break;
            } else {
                swap(index, largestChildIndex);
            }
            index = largestChildIndex;

        }

    }

    public void heapifyUp(){
        int index = size - 1;
        while (hasParent(index) && getParentValue(index) < maxHeap[index]) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }

    }

    public void printHeap(){
        for (int i = 0; i<size; i++) {
            System.out.println(maxHeap[i]);
        }
    }

    public static void main(String[] args) {
        MaxHeap maxHeapObject = new MaxHeap();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int randomValue = random.nextInt(101);
            maxHeapObject.insert(randomValue);
        }



        Arrays.stream(maxHeapObject.maxHeap).forEach(i -> System.out.print(i + " "));
        System.out.println(maxHeapObject.peek());
        System.out.println(maxHeapObject.poll());
        System.out.println(maxHeapObject.peek());
        maxHeapObject.insert(random.nextInt(101));
        System.out.println(maxHeapObject.peek());
        Arrays.stream(maxHeapObject.maxHeap).forEach(i -> System.out.print(i + " "));
    }






}
















