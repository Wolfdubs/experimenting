package Concepts.Basics.ExceptionHandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

//2 Exception types
// - Checked exception: checked for at compile time; compiler throws error before it compiles it. so cannot even compile the code.
// - Unchecked exception: compiler has not checked it, could throw error while running; compiler won't prompt you. Only exception subclasses
//   of RunTime exception are unchecked
//Exception extends Throwable (exceptions & errors).
    //As a Throwable, the exception is an object that is thrown down the call stack to the method that called it
        //the exception object contains info that helps handle it
    //throwing an exception doesn't resolve it, it just passes it down the call stack to the method that caused it, until main, where if not
//    caught, the program will fail
            // so somewhere in the course of throwing exceptions, you want some logic to catch it
    // Never try to catch errors; they generally refer to unrecoverable problems e.g. OutOfMemory, StackOverflow

public class ExceptionHandlingClass {

    public static void main(String[] args) throws IOException {  //throws required for calling buffered reader method

        try{    //try code that may throw checked exception. means code won't fail at runtime, and rest of program can continue to execute
            int[] intArray = new int[5];
            intArray[10] = 5;       //try will stop at the first exception it encounters, will not execute later lines
            int i = 9/0;
        } catch (ArithmeticException e){   //only executes if the specified exception is thrown
            System.out.println("cannot divide by zero");
            e.printStackTrace();
        }
        catch (ArrayIndexOutOfBoundsException | ArrayStoreException e) {  //can catch multiple different exceptions in 1 catch block
            System.out.println("error with arrays");
            e.printStackTrace();
        }
        catch (Exception e) {   //best practice to end with this. all exceptions inherit from this superclass. dont put at front as then wont ever use the specific exception catches
            System.out.println("something else went wrong");
        }
        finally {       //finally block will order to execute some code irrespective of an exception occurs or not, so always executes, even if the try contains a return;
            System.out.println("will execute regardless of exception or not");  //finally is mainly used to close resources/connections. cleanup code
        }
        exceptionWhileLoop();
        useBufferedReader();
        tryWithResources();
        System.out.println(printInt());



    }

    public static int useBufferedReader() throws IOException{   //throws Exception is alternative to try-catch. is mandatory, else get compile time error

        int n=0;
        System.out.println("Enter number");
        BufferedReader bufferedReader = null;
        try{
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(bufferedReader.readLine());   //.readLine() can cause an exception, due to invalid user input for IO
            System.out.println(n);
            return n;

        } catch (Exception e){
            System.out.println("exception in buffered reader");
        }
        finally {
            bufferedReader.close();  //always do in a finally block so it executes regardless of exceptions interrupting code flow
            System.out.println("buffered reader is closed"); //any classes from java.io are resources -> must close them, else it wastes memory
        }
        return n;
    }

    public static int tryWithResources(){ //as soon as object declared in try block goes out of scope, it will always auto close, so dont need finally block
        int n = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)))
        {
            n = Integer.parseInt(bufferedReader.readLine());   //.readLine() can cause an exception, due to invalid user input for IO
            if (n>10){
                throw new MyCustomException(); //you can manually call exceptions too, even call yur own custom exceptions, and catch it
            }
            if (n<-10){
                throw new MyCustomException("passing in message"); //calling alt constructor for custom exception
            }
        } catch (MyCustomException e){
            System.out.println("throwing custom exception " +e.getClass());
        } catch (Exception e){
            e.printStackTrace();
        }
        return n;
    }

    private static int printInt(){
        try{
            return 3;
        } catch (Exception e){
            return 4;
        } finally {
            return 5;    //this method will return 5, even though there is nothing wrong with the try. so returns in finally will always override a return in try/catch
        }
    }

    private static void exceptionWhileLoop(){   //will keep looping until the try block executes successfully
        Scanner scanner = new Scanner(System.in);
        boolean success = false;
        while(!success) {
            try {
                System.out.println("enter an int");
                int myint = scanner.nextInt();
                success = true;
            } catch (InputMismatchException ime) {
                scanner.nextLine();    //needed to shift scanner down to the next line, else will just get stuck on that 1st line
                System.out.println("enter valid int");
            } finally {
                System.out.println("this will run after every iteration of the try/catch, so could have the scanner.nextLine() here");
            }
        }

        //alternative is to do to check scanner has an int to parse
        while(!scanner.hasNextInt()) {
            try {
                scanner.nextLine();
                System.out.println("enter an int");
                int inty = scanner.nextInt();
            } catch (InputMismatchException ie) {}
        }
    }
}




//Unchecked exceptions; you can still add try/catch or throws for these if you want
class UncheckedExceptionDemo{
    public static void main(String[] args) {
        String word = "Echolocation";
        printLength(word); //no exceptions here
        String nullWord = null;
        printLength(nullWord);   // unchecked NullPointerException, thrown at runtime. Java doesn't give any compilation errors.
    }
    private static void printLength(String s){
        System.out.println(s.length());
    }
}




//Scanner does exact same as buffered reader, but is cleaner code, has many methods, handles IO exceptions itself, and closes its own resources itself
/*
A call-stack is formed when one method calls another.  If a() called b(), we would see b() stacked on top of a().
We can then say that the return path out of b() would be back down into its calling method, a().
Exceptions are not just error messages. Exception mechanisms in a programming language let us write code to recover from errors,
and resume normal execution of the code.
Exceptions are objects that are "thrown" by a method. This is done with the keyword throw, and then the construction of a new exception object
(new Exception()).  This would look like: throw new SomeException();
Throwing the exception object causes an alternate return path to be taken: once an exception is thrown, none of the subsequent code in that
method is executed.  Instead, the exception object can be thought of as falling back down the return path to the calling method.
Normal execution through the normal return path does not resume until the exception is caught.  If the exception is not caught it keeps
falling down the alternate exceptional path until the program effectively crashes.
Exceptions can be caught by a method with the keywords try and catch.  First, we try to call the method by placing the method call within the
try's block of code, and if an exception is thrown in that method, then we catch that exception and execute the code in the catch block.
A method should only catch an exception if it can actually do the work to recover from (handle) that exception.  If it is not appropriate for
the current method to deal with an exception, and recover execution, then it should just pass it along, letting the exception object keep
falling down the return path.
In that case, the method would not use the try/catch block. Instead, the method would be indicated as thrown in the method signature
Specifying 'throws Exception' in method signature tells java that instead of the call stack following c() -> b() -> a() -> main()
    it could just be c() throwing that exception down to main if not caught by b() or a() if they have code to deal with it
    Throws will throw the exception down the call stack until it finds a method that catches it
Exceptions can be thrown from anywhere, including inside of a catch block.
    Just like exceptions thrown from elsewhere, no subsequent code in the throwing method executes, instead the exception is thrown out along
    that separate path, and normal operation does not resume until the exception is caught.
A Try/Catch statement has a third part: Finally
The Finally block of code executes either after the catch block (whether an exception was caught or not), or before the method exits if
an exception is thrown within the catch block.

If an exception is declared to be caught, as in catch (SuperException supe) {..., any of the subclasses of that exception will also be
caught by that same catch statement.
Exception hierarchies are typically used to provide a standard response to an entire hierarchy of exceptions, while still leaving open the
option for catching more concrete exceptions individually as needed.
Just remember that catches execute in order, so if there was also a catch (SubException sube), you would need to place its catch before that
of its super class -- otherwise the exception will get gobbled up before the SubException catch is reached.

Checked exceptions = checked by the compiler; IDE will alert you to issues
Unchecked exceptions = compiler won't alert about, but could be thrown by a method e.g. Arithmetic, ArrayOutOfBounds
 */