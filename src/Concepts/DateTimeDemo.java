package Concepts;

import java.time.*;
import java.util.Set;

import static java.time.ZoneId.getAvailableZoneIds;

//DataTime package is thread safe & immutable
public class DateTimeDemo {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();   //don't want to manually create a new object of LocalDate, just do so by method call
        System.out.println(localDate);
        LocalDate d = LocalDate.of(1994, 03, 22);
        System.out.println(d);
        LocalDate d1 = LocalDate.of(1932, Month.JULY, 3);
        System.out.println(d1);


        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);    //output includes nanoseconds
        LocalTime l = LocalTime.of(11,48,34,837423);  //can specify your own time
        System.out.println(l);
        Set<String> listOfZoneIDs = ZoneId.getAvailableZoneIds();   //can see all Java inbuilt ZoneIDs
        System.out.println(listOfZoneIDs.toString());
        LocalTime specificTimeZone = LocalTime.now(ZoneId.of("Pacific/Kwajalein"));  //gets the current time in the specified timezone
        System.out.println(specificTimeZone);

        Instant i = Instant.now();   //returns current GMT date time
        System.out.println(i);

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldtOf = LocalDateTime.of(2018,Month.DECEMBER,11, 8, 47,30,9303);
        System.out.println(ldtOf);
    }
}
