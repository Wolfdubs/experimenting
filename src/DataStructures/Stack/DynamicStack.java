package DataStructures.Stack;

public class DynamicStack {

    int capacity = 2;           // default starting size of the array
    int[] stack = new int[capacity];
    int top = 0;                //top points to the next empty space in the array, so top-1 helps keeps track of last element to be added


    public void push(int data){
        if (capacity() == 0){      //alt: if (elementCount == capacity)
            expand();
        }
        stack[top] = data;
        top++;
    }

    public int pop(){                //pop returns an int as it will return the value thats been deleted
        if (isEmpty() == true) {                //alt: if (top == inputSize)
            System.out.println("\nStack is already empty");
            return 0;
        }
        int data = stack[top-1];     //top always points to the next empty index up, so must do -1 to get last element added
        stack[top-1] = 0;
        top--;

        if (elementCount()<=((totalStackSize()/2)/2)) {               //if the number of elements in the array is equal/less than 1/4 of the totalstacksize
            shrink();
        }
        return data;
    }

    public int peek(){
        if (isEmpty()){
            System.out.println("Cannot peek as stack is empty");
            return 0;
        }
        int data = stack[top-1];
        return data;
    }

    public void printStack(){
        for (int i : stack) {
            System.out.print(i + " ");
        }
        System.out.println();  //just for formatting so new calls are separated
    }

    public int totalStackSize(){
        int counter=0;
        for (int i : stack) {
            counter++;
        }
        return counter;
    }

    public int elementCount(){
        return top;
    }

    public int capacity(){
        int counter = 0;
        for (int i = top; i<totalStackSize(); i++){
            counter++;
        }
        return counter;
    }

    public boolean isEmpty(){
        return (top<=0);
    }

    public boolean isEmpty2(){     //if every element in stack is 0, assume it is empty
        for (int i : stack) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    private void expand(){        //keep expand() private as dont want any other class to access, its a helper method only
        int[] newStack = new int[capacity*2];
        for (int i = 0; i<stack.length; i++){
            newStack[i] = stack[i];
        }
      //  System.arraycopy(stack, 0, newStack, 0, stack.length);    //alt way to copy arrays
        stack = newStack;
        capacity*=2;
        /*
        create a new array with 2x capacity
        copy elements across from old array to new
        set stack variable to point to this new stack object
        increase capacity variable to 2x what it was
        */
    }

    private void shrink(){
            capacity = capacity / 2;                       // then half the capacity, create new array of 1/2 capacity, copy old one, new reference variable
            int[] newStack = new int[capacity];
            System.arraycopy(stack, 0, newStack, 0, elementCount());
            stack = newStack;

        // create new array with half the capacity
        // copy elements across from old to new
        // set stack variable to point to new stack object
        // decrease capacity variable to half
    }


}

