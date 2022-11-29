package Problems;

public class Fibonacci {

    //return the nth number in the fibonacci sequence
    public static long fibonacciRecursive(int n) {                 //use long as it can generate very high numbers
        //base case; 0th and 1st elements are 0 and 1, these are hard-coded values that stop infinite recursive calls
        if (n <= 1) {
            return n;
        }
        return (fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2));
        //this is slow as the reclaculations of values you have already done, means it is inefficient, with exponential increase
    }


    //memoization: save results once calculated, so dont need recalculated
    //once the calculation for one recursive call is complete, it saves this value so it doesn't need to be recalculated again
    public static long fibonacciMemoization(int n) {
        long[] fibonacciCache = new long[n + 1];    //because size of array is 1 above the last index. must declare outside function so recursive calls dont erase it every time
        return fibonacciMemoizationCode(n, fibonacciCache);
    }

    public static long fibonacciMemoizationCode(int n, long[] cache) {
        //save calculated results in an array
        if (n <= 1) {
            return n;
        }

        //check is the result for n has already been calculated and cached in the array. default long value is 0, so if not 0, it must have been added
        if (cache[n] != 0) {
            return cache[n];
        }

        //calculate the result for n and cache result in the array
        long nthFibonacciNumber = fibonacciMemoizationCode(n - 1, cache) + fibonacciMemoizationCode(n - 2, cache);
        cache[n] = nthFibonacciNumber;

        return nthFibonacciNumber;
    }


    public static void printFibonacci(int n) {
        System.out.println("The fibonacci sequence up to index " + n + " : ");
        for (int i = 0; i<=n; i++) {
            System.out.print(fibonacciMemoization(i) + " ");
        }
    }











}