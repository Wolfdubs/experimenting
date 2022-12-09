package Concepts;

//method that calls itself.
//To avoid infinite recursion -> StackOverflow error requires:
    // base case so program can exit without always ust making recursive calls
    //some mechanism for the method to work toward the base case with every recursive call. e.g. recursive call being made with a diminishing input, which is checked in the base case
// every new method call is added to the call stack; so main() is at bottom, then each call is added on top
//as inner methods complete, they are removed from call stack until main() is removed and program completes
//base case means the last call will return to the method call that invoked it, which bubbled all the way up to the original call in main()
public class RecursionDemo {
    public static void main(String[] args) {
        printHowdy(5);
    }

    private static void printHowdy(int count){
        System.out.println("Howdy");
        if (count<=1)   //base case that exits the method
            return;
        printHowdy(count-1);   //recursive call with diminishing input; every call the counters will decrease by 1
    }

}
