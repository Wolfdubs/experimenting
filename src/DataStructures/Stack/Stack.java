package DataStructures.Stack;

public class Stack {

    int stack[];
    int top = 0;                //top points to the next empty space in the array, so top-1 helps keeps track of last element to be added


    public Stack(int inputSize) {
        this.stack = new int[inputSize];
    }

    public void push(int data){
        if (capacity() == 0){
            System.out.println("\nStack is full");
            return;
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

}
