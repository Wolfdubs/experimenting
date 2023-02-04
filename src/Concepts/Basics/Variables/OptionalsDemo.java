package Concepts.Basics.Variables;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

import static java.lang.System.out;
import static org.junit.Assert.assertFalse;

public class OptionalsDemo {
    //Optional is a container class that may or may not contain a null value; revealed by isPresent()
    //ifPresent() operates if object is inside
//Used as a method return type to show if a value is null, but returning null itself would cause an error - deals with NullPointerExceptions

    public static void main(String[] args) {
        String val = "womble";
        Optional<String> optional = Optional.of(val);   //returns Optional with the present non-null value. cannot pass null
        out.println(optional.isPresent());  //will print true
        optional.ifPresent(out::println);   //only does the optional if the object is present
        out.println(optional.get());  //returns value present in Optional - if empty, returns NoSuchElementException
        String variable = optional.orElseGet(() -> "mungo");   //if the optional is empty, it will return the alternative provided
        optional.filter(x -> x.contains("w"));

        //using Optional to avoid NullPointerException
        Byte[] bytes = new Byte[10];
        Optional<Byte> byteOptional = Optional.ofNullable(bytes[5]);  //returns an Optional with the value; if no value returns empty Optional. can pass null
        if (byteOptional.isPresent()) {
            byte byteHalf = (byte) (bytes[5] / 2);
            byteOptional.orElse((byte) 11);    //if value is not present in Optional, it returns 11 as default
            byteOptional.orElseGet(() -> Byte.valueOf("5"));   //same as orElse() except only executed if value inside the stream operation is not present
        } else {
            out.println("byte not present");
        }

        //create an empty optional
        Optional<String> empty = Optional.empty();
        assertFalse(empty.isPresent());

    }

    //specialized optionals
    OptionalDouble optionalDouble = OptionalDouble.of(4.3);
    OptionalInt optionalInt;
    OptionalLong optionalLong;
}

