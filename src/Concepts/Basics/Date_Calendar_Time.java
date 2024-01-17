package Concepts.Basics;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Date_Calendar_Time{

    //dateFormat gives customized way to print human readable dates
    static DateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");   //Z is the timezone offset to GMT

    public static void main(String[] args) {
        Date now = new Date();  //this calls the system's current time in ms. Date class doesnt have timezone, only has default for the computer
        long ts = System.currentTimeMillis(); //gets timestamp of number of ms since 01/01/1970 to now

        Calendar myCalendar = new GregorianCalendar();  //lets you manipulate the date. has many methods
        myCalendar.set(Calendar.MILLISECOND, 0);  //can set value of specific fields
        myCalendar.set(2021,3, 19, 13, 44, 59);
        //to print, must convert to a date
        System.out.println(myCalendar.getTime());
        myCalendar.setTimeZone(TimeZone.getTimeZone("EST"));
        String formattedDate = myDateFormat.format(now);
        System.out.println(formattedDate);
        myDateFormat.setTimeZone(TimeZone.getTimeZone("CST"));
        System.out.println(myDateFormat.format(myCalendar.getTime()));


    }

}

// SimpleDateFormat objects are not thread safe, so when shared between threads its behavior becomes very unpredictable
//so create a new object for every thread that will use it
//FastDateFormat from apache commons is thread safe, so can share 1 object between threads
class SimpleDateFormatDemo {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd,hh:mm:ss");

    public static void main(String[] args) throws ParseException {

        String dateString = "2022-12-16,19:49:37";
        Date date = sdf.parse(dateString);
        System.out.println("Parsed: " + date);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Runnable myRunnable = () -> {
                try {
                    Date date1 = new SimpleDateFormat("yyyy-MM-dd,hh:mm:ss").parse(dateString);   //thread safe usage as each method call has own object
                    System.out.println("Parsed in Thread: " + date1);

                } catch (ParseException pe) {
                    pe.printStackTrace();
                } finally{
                    executorService.close();
                }
            };
        for (int i=0; i<10; i++){
            executorService.submit(myRunnable);
        }
    }
}
