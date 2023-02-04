package Concepts.Basics.Variables;

import java.util.ArrayList;
import java.util.List;
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



}





















