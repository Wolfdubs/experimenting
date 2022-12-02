package Concepts.Basics.ExceptionHandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//2 Exception types
// - Checked exception: compiler throws error before it runs.
// - Unchecked exception: compiler has not checked it, could throw error while running; compiler won't prompt you. Only exception subclasses of RunTime exception are unchecked
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
        finally {       //finally block will order to execute some code irrespective of an exception occurs or not, so always executes
            System.out.println("will execute regardless of exception or not");
        }
        useBufferedReader();
        tryWithResources();
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
                throw new myException(); //you can manually call exceptions too, even call yur own custom exceptions, and catch it
            }
            if (n<-10){
                throw new myException("passing in message"); //calling alt constructor for custom exception
            }
        } catch (myException e){
            System.out.println("throwing custom exception " +e.getClass());
        } catch (Exception e){
            e.printStackTrace();
        }
        return n;
    }

}

class myException extends Exception{
    String message;
    public myException(){}
    public myException(String str){   //lets you pass a message to the custom exception from the call
        super(str);                //passes the string to Exception class, whose own public Exception(string) constructor calls Throwable, which prints the message
    }
}


//Scanner does exact name as buffered reader, but is cleaner code, has many methods, handles IO exceptions itself, and closes its own resources itself