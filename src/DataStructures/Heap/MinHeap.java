package DataStructures.Heap;

import java.util.Arrays;

/*
Heap = complete binary tree structure; either maxHeap or minHeap
no gaps, except for last row which can be empty only from the right (no staggered gaps).
unlike binary tree; left child is not always less than right
can contain duplicates
Very fast insertion and removal, and sorting (heapSort)
Very slow traversal and searching
Implement via array because heap has no gaps; mapping any index to its left and right children
    index
        parent = (index - 1) /2
        left child = (index * 2) + 1
        right child = (index * 2) + 2

Min Heap = elements are all smaller than their children, so root is the smallest element
Used for heapSort & to implement priority queues, which are used in graph algorithms e.g. Dijkstra's algorithm


 */
public class MinHeap {

    private int capacity = 10;
    private int size = 0;
    int[] minHeap = new int[capacity];

    private int getLeftChildIndex(int parentIndex) {return (2 * parentIndex) + 1;}
    private int getRightChildIndex(int parentIndex) {return (2 * parentIndex) + 2;}
    private int getParentIndex (int childIndex) {return (childIndex - 1) / 2;}

    private boolean hasLeftChild(int parentIndex) {return getLeftChildIndex(parentIndex) < size;}
    private boolean hasRightChild(int parentIndex) {return getRightChildIndex(parentIndex) < size;}
    private boolean hasParent(int childIndex) {return getParentIndex(childIndex) >= 0;}

    private int getLeftChild(int parentIndex) {return minHeap[getLeftChildIndex(parentIndex)];}
    private int getRightChild(int parentIndex) {return minHeap[getRightChildIndex(parentIndex)];}
    private int getParent(int childIndex) {return minHeap[getParentIndex(childIndex)];}

    private void swap(int index1, int index2) {
        int temp = minHeap[index1];
        minHeap[index1] = minHeap[index2];
        minHeap[index2] = temp;
    }

    private void ensureAvailableCapacity(){
        if (size == capacity) {
            capacity *= 2;
            minHeap = Arrays.copyOf(minHeap, capacity);
        }
    }

    public int peek(){
        if (size == 0) throw new IllegalStateException();
        return minHeap[0];
    }


    //pop the root node, move the last item to the root, and then bubble it down until in order
    public int poll(){
        if (size ==0) throw new IllegalStateException();
        int root = minHeap[0];
        minHeap[0] = minHeap[size-1];    //insert last element added into the root position
        size--;
        heapifyDown();    //then bubble it down to next spot
        return root;      //pop root (minimum element)
    }


    //insert new element into the last empty spot at bottom, and bubble it up to right spot; comparing with parents
    public void insert(int n){
        ensureAvailableCapacity();
        minHeap[size] = n;  //adding element to last spot
        size++;
        heapifyUp();
    }

    private void heapifyUp() {
        int index = size - 1;            //starts at very last element
        while(hasParent(index) && getParent(index) > minHeap[index]) {   //while the current element has a parent index, and its parent's value exceeds its own
            swap(getParentIndex(index), index);    //swap with the parent
            index = getParentIndex(index);   //move further up by updating index to point to elements new position
        }
    }

    private void heapifyDown(){
        int index = 0;   //start from root
        while(hasLeftChild(index)) {   //check if have children: dont need to also check for right, because if no left, then won't have any right
            int smallerChildIndex = getLeftChildIndex(index); //randomly just guess left child is smallest of the 2
            if (hasRightChild(index) && getRightChild(index) < getLeftChild(index)) {   //if right child is smaller than left, update smallerchildindex
                smallerChildIndex = getRightChildIndex(index);
            }
            if (minHeap[index] < minHeap[smallerChildIndex]) {    //if the current value is less than the smallest child index break, it's all in order
                break;
            } else {   //current index is less than smallerIndex
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;   //move index down to the smaller child index, to continue on from there


        }
    }
}



















