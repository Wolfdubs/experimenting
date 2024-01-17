package Problems;

public class Fibonacci {

    //return the nth number in the fibonacci sequence
    public static long fibonacciRecursive(int n) {                 //use long as it can generate very high numbers
        //base case; 0th and 1st elements are 0 and 1, these are hard-coded values that stop infinite recursive calls
        if (n <= 1) {
            return n;
        }
        return (fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2));
        //this is slow as the recalculations of values you have already done, means it is inefficient, with exponential increase
    }


    //memoization: save results once calculated, so dont need recalculated
    //once the calculation for one recursive call is complete, it saves this value so it doesn't need to be recalculated again
    //runtime is O(n)
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

    //but it is a waste of memory space to hold on to the values for every fibonacci element and keep passing it to the calling iteration,
        //when each call just needs the specific 2 values added to produce the current fibonacci number
    private int fibonacciTwoVariables(int n){
        if (n<=1) return n;
        int first = 0;
        int second = 1;
        var result = 0;
        for (int i = 2; i<=n; i++){
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }


    ///also, an addendum to the DP array building. O(n)
    //Build it from the Bottom-Up, rather than recursively
    private int fibonacciBottomUp(int n){
        if (n==0 || n==1) return 1;
        int[] fibonacciSequence = new int[n+1];
        fibonacciSequence[0] = 1;
        fibonacciSequence[1] = 1;
        for (int i = 2; i <=n; i++){
            fibonacciSequence[i] = fibonacciSequence[i-1] + fibonacciSequence[i-2];
        }
        return fibonacciSequence[n];
    }

    private double fibonacciMathematical(int n){
        return (Math.pow(((1+Math.sqrt(5))/2),n) - Math.pow(((1-Math.sqrt(5))/2),n) / Math.sqrt(5));
    }


    public static void printFibonacci(int n) {
        System.out.println("The fibonacci sequence up to index " + n + " : ");
        for (int i = 0; i<=n; i++) {
            System.out.print(fibonacciMemoization(i) + " ");
        }
    }











}