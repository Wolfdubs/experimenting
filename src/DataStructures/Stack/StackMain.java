package DataStructures.Stack;

public class StackMain {

    public static void main(String[] args) {
        DynamicStack myStack = new DynamicStack();
        myStack.printStack();
        System.out.println(myStack.totalStackSize());
        System.out.println(myStack.isEmpty());
        myStack.push(6);
        myStack.push(33);
        myStack.printStack();
        myStack.push(26);
        myStack.push(18);
        System.out.println(myStack.totalStackSize());
        myStack.printStack();
        myStack.push(43);
        myStack.push(91);
        myStack.printStack();
        System.out.println(myStack.isEmpty());
        myStack.pop();
        myStack.pop();
        myStack.pop();
        myStack.pop();
        myStack.printStack();
        System.out.println(myStack.peek());
        System.out.println(myStack.totalStackSize());
        System.out.println(myStack.isEmpty());
        System.out.println(myStack.elementCount());
        System.out.println(myStack.capacity());


    }
}
