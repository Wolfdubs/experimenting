package Concepts.FunctionalProgramming;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.*;

//takes no arguments, but returns a single value
//used for lazy generation of values, and define logic for generating sequences
//SAM is get()
//useful for deferred execution; only happens when it is needed, e.g. See Optional & doubleSupplier example
public class SupplierDemo {
    public static void main(String[] args) {
        String product = "Programming";
        Supplier<String> stringSupplier = product::toUpperCase;
        Supplier<Integer> integerSupplier = () -> product.length()-3;
        Supplier<Boolean> booleanSupplier = () -> product.contains("ram");
        System.out.println(stringSupplier.get() + "\n" + integerSupplier.get() + "\n" + booleanSupplier.get() );

        Supplier<Double> randomDoubleSupplier = Math::random;
        randomDoubleSupplier.get();

        Supplier<LocalDate> dateSupplier = LocalDate::now;
        System.out.println(dateSupplier.get());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Supplier<LocalDateTime> localDateTimeSupplier = () -> LocalDateTime.now();
        LocalDateTime time = localDateTimeSupplier.get();
        System.out.println(time);
        Supplier<String> formattedDate = () -> formatter.format(LocalDateTime.now());
        System.out.println(formattedDate.get());


        Supplier<Integer> randomSupplier = SupplierDemo::generateRandom;
        System.out.println(randomSupplier.get());

        List<String> stringList = generateSupplier().get();

        //the value of the doubleSupplier is only calculated if needed because the optional was empty and orElseGet() was called
        Optional<Double> optionalDouble = Optional.empty();
        Supplier<Double> doubleSupplier = () -> 10.0;
        System.out.println(optionalDouble.orElseGet(doubleSupplier));
    }

    private static int generateRandom(){
        return new Random().nextInt(100);

    }

    private static Supplier<List<String>> generateSupplier(){
        return () -> new ArrayList<String>();
        //or return ArrayList::new;
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













