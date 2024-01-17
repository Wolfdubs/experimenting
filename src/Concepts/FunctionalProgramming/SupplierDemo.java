package Concepts.FunctionalProgramming;

import Concepts.Lambdas.myInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

/*takes no arguments, but returns a single value
used for lazy generation of values, and define logic for generating sequences
SAM is get()
useful for deferred execution; only happens when it is needed, e.g. See Optional & doubleSupplier example
 */

public class SupplierDemo {
    public static void main(String[] args) {
        String product = "Programming";
        Supplier<String> stringSupplier = product::toUpperCase;
        Supplier<String> googoo = () -> product.toUpperCase();
        //Supplier<String> gg = product.toUpperCase();   //now allowed as returns string only
        Supplier<Integer> integerSupplier = () -> product.length()-3;
        Supplier<Boolean> booleanSupplier = () -> product.contains("ram");
        System.out.println(stringSupplier.get() + "\n" + integerSupplier.get() + "\n" + booleanSupplier.get() );

        Supplier<Double> randomDoubleSupplier = Math::random;
        randomDoubleSupplier.get();

        Supplier<LocalDate> dateSupplier = LocalDate::now;
        System.out.println(dateSupplier.get());

        Supplier<LocalDateTime> localDateTimeSupplier = () -> LocalDateTime.now();
        LocalDateTime time = localDateTimeSupplier.get();
        System.out.println(time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Supplier<String> formattedDate = () -> formatter.format(time);
        System.out.println(formattedDate.get());


        Supplier<Integer> randomSupplier = SupplierDemo::generateRandom;
        System.out.println(randomSupplier.get());

        List<String> stringList = generateSupplier().get();

        //the value of the doubleSupplier is only calculated if needed because the optional was empty and orElseGet() was called
        Optional<Double> optionalDouble = Optional.empty();
        Supplier<Double> doubleSupplier = () -> 10.0;
        System.out.println(optionalDouble.orElseGet(doubleSupplier));

        //generate accepts a Supplier
        int[] fibonacciStart = {0,1};  //the supplier is using some external state. use array as values for supplier must effectively be final
        Stream<Integer> fibonacci = Stream.generate(() -> {
            int result = fibonacciStart[1];
            int fibonacci3 = fibonacciStart[0] + fibonacciStart[1];
            fibonacciStart[0] = fibonacciStart[1];
            fibonacciStart[1] = fibonacci3;
            return result;
        });

        //can create a Supplier object and use get() to return the value of whatever was used to create it
        //e.g. stream.generate() accepts a Supplier to generate whatever the supplier object was created with
        Supplier<Double> randomSupplier2 = Math::random;
        List<Double> randoms = Stream.generate(randomSupplier2)
                .limit(11).toList();



    }

    private static int generateRandom(){
        return new Random().nextInt(100);

    }

    private static Supplier<List<String>> generateSupplier(){
        return ArrayList::new;
        //or return ArrayList::new;
    }

    //can have a method that accepts a supplier (of some type); useful because it allows lazy generation of the value
    Supplier<Double> supplierOfDouble = () -> 8d;      //lazy generation of double; defers execution of this line, until its called by method
    private double squareLazy(Supplier<Double> supplier) {
        return Math.pow(supplier.get(),2);   //function squares the value of the supplier
    }


}

//supplier of boolean results, method is getAsBoolean()
//doesn't need type specification in <> as it always is boolean
class BooleanSupplierDemo{
    static int bullets = 1640;
    BooleanSupplier booleanSupplier = () -> bullets > 1500;
}


class DoubleSupplierDemo {
    static int bullets = 1640;
    DoubleSupplier doubleSupplier = () -> bullets * 10;


    public static void main(String[] args) {
        DoubleSupplier randomDoubleSupplier = Math::random;
        randomDoubleSupplier.getAsDouble();

    }
}

class IntSupplierDemo{
    static int bullets = 1640;
    IntSupplier intSupplier = () -> bullets - 30;
}

class LongSupplierDemo {
    LongSupplier longSupplier = () -> new Date().getTime();
}














