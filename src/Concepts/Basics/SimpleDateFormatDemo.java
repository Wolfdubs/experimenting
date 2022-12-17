package Concepts.Basics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


// SimpleDateFormat objects are not thread safe, so when shared between threads its behavior becomes very unpredictable
//so create a new object for every thread that will use it
//FastDateFormat from apache commons is thread safe, so can share 1 object between threads
public class SimpleDateFormatDemo {
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
                }
            };
        for (int i=0; i<10; i++){
            executorService.submit(myRunnable);
        }
    }
}
