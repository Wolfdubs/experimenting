package Concepts.Basics.Variables;

import java.util.stream.Stream;

public class StringBuilderVsStringBuffer {
/*
StringBuilder & StringBuffer do exact same job - both are mutable strings; any modification modifies existing char sequence in memory
    Buffer = Synchronized. degrades buffer performance (not much in single Threaded process) but needed for multi-Threading
    Builder = not synchronized
//String immutability often a benefit
 */
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("womble");
        stringBuilder.append(" the pekingese");  //O(n) as performed directly on the initial sequence, unlike Strings that must be traversed for copying into new object (O(m+n))
        String string = "mungo";
        string.concat(" the pekingese");  //won't actually modify the string object
        System.out.println("stringbuilder = " + stringBuilder + " , string = " + string);
    }

    public static String reverseString(String string) {   //O(n^2) because the loop traverses input string, as does the concatenation
        String reversedString = "";
        for (int i = string.length()-1; i >= 0; i--) {     //O(n)
            reversedString = reversedString + string.charAt(i);   //O(n)
        }
        return reversedString;
    }

    public static String reverseWithStringBuilder(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        Stream.iterate(string.length() -1, i -> i-1)     //O(n)
                .limit(string.length())
                .forEach(i -> stringBuilder.append(string.charAt(i)));  //O(1) append is constant time
        return stringBuilder.toString();
    }

    public static String inbuiltStringBuilderReverse(String string) {
        return new StringBuilder(string).reverse().toString();
    }



}





















