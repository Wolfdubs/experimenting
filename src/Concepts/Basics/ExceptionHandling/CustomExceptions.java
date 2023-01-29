package Concepts.Basics.ExceptionHandling;

public class CustomExceptions {
    public static void main(String[] args) throws NegativeIntNotAllowedException {

        validateAge(17);
        validateAge(-5);
    }

    //making a method to use with the custom exception
    private static void validateAge(int age) throws NegativeIntNotAllowedException {
        if (age < -10) {
            throw new NegativeIntNotAllowedException("custom message because -10 is just crazy to try");
        }
        if (age < 0){
            throw new NegativeIntNotAllowedException();
        }
        if (age == 0) {
            throw new NegativeIntNotAllowedException("Oops", new RuntimeException());   //can pass an exception for your Throwable parameter constructor
        }
    }
}

//don't extend Throwable or error, as you never want to catch errors and are more serious errors e.g. out of memory, stack overflow
class MyCustomException extends Exception{   //this line is technically all you need to create a custom exception
    String message;
    public MyCustomException(){}   //as we have a parameterized constructor below, java no longer provides the default no-args constructor, so must write it explicitly
    public MyCustomException(String str){   //lets you pass a message to the custom exception from the call
        super(str);               //passes the string to Exception class' constructor, whose own public Exception(string) constructor calls Throwable, which prints the message
    }
}

class NegativeIntNotAllowedException extends IllegalArgumentException{   //should extend the closest exception to your use-case
    public NegativeIntNotAllowedException(){
        super("negative int is not allowed");
    }

    public NegativeIntNotAllowedException(String message){
        super(message);
    }

    //Exception class also offers a constructor that takes in a Throwable as a parameter
    //useful if the custom exception can be caused by another exception
    public NegativeIntNotAllowedException(Throwable cause){
        super(cause);   //now when exception is thrown, it also prints the exception/error that caused it
    }

    public NegativeIntNotAllowedException(String message, Throwable cause){
        super(message,cause);
    }

}



