package Games;

import java.util.Calendar;
import java.util.Scanner;

public class calculateChineseZodiac {

    public static void main(String[] args) {
        Calendar c = getUserDOB();
        calculateChineseZodiac(c);
    }

    private static Calendar getUserDOB(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your DOB as dd-mm-yyyy");
        String dob = scanner.nextLine();
        int day = Integer.parseInt(dob.substring(0,2));
        int month = Integer.parseInt(dob.substring(3,5));
        int year = Integer.parseInt(dob.substring(6,10));
        Calendar c = Calendar.getInstance();
        c.set(year,month,day);
        return c;
    }

    private static String calculateChineseZodiac(Calendar c) {
        //assumes a NY starts 01-Feb, as in reality, every year is different
        String[] chineseYears = {"Rat","Ox","Tiger","Rabbit","Dragon","Snake","Horse","Sheep","Monkey","Rooster","Dog","Pig"};
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int remainder = Integer.MIN_VALUE;
        if (month==1)
            year--;
        if (year < 1900) {
            System.out.println("formula doesn't apply to periods pre-1900");
            return "";
        }
        if (year < 2000){
            year -= 1900;
            remainder = (year % 12);
        } else {
            year -= 2000;
            remainder = (year % 12);
            if (remainder > 12) remainder -= 12;
            remainder += 4;
        }
        System.out.println("Your Chinese zodiac = " + chineseYears[remainder]);
        return chineseYears[remainder];


        //2023 = index 6 = rabbit
        //2022 = index 5 = tiger
        //2017 = index 0 = rooster
        //transform 2023 -> 6 (out of 12 elements)
        //2017 - 2017


    }
}




















