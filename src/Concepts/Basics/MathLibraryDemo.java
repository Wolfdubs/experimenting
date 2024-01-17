package Concepts.Basics;

public class MathLibraryDemo {

    public static void main(String[] args) {
        int a = 5; int b = 8;
        System.out.println(Math.E + " , " + Math.PI);
        Math.abs(-13);  //returns absolute value - aka just makes it positive
        Math.acos(0.54);   //returns arc-cosine of the value
        Math.asin(0.87);
        System.out.println(Math.cbrt(27));   //returns cube root
        System.out.println(Math.ceil(6.24));  //rounds the double up to ceiling int
        System.out.println(Math.decrementExact(19)); //decrements value by 1
        Math.incrementExact(4);
        System.out.println(Math.exp(2));  //raising E to the value passed in
        System.out.println(Math.floor(6.24));   //opposite of ceil
        System.out.println(Math.floorDiv(11,3));  //divides x by y, then flooring it
        System.out.println(Math.floorMod(11,3));  //does x % y, then floors it
        System.out.println(Math.hypot(6,10));  //gets hypotenuse of 2 triangle sides
        System.out.println(Math.log(Math.E));   //gets the log of input
        System.out.println(Math.log10(12));  //gets log 10
        System.out.println(Math.max(51,184));  //returns max value
        System.out.println(Math.min(51,184));
        System.out.println(Math.negateExact(43));  //inverts the sign; negative -> positive and positive -> negative
        System.out.println(Math.pow(4,9));   //raises x to power y
        System.out.println(Math.random());   //returns random value from 0-1
        System.out.println(Math.rint(4.3));   //rounds double to closest int
        System.out.println(Math.round(4.3));   //also rounds
        System.out.println(Math.sqrt(81));  //returns square root
        System.out.println(Math.getExponent(64));  //returns the exponent of 2 to reach the value; e.g. 2^6 = 64, so returns 6

    }
}
