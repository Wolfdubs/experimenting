package Concepts.Basics.Generics;

import java.util.*;

public class Wildcards {
    /*
    Generics make the entire class, and all its methods generic
    Wildcards make only the method generic
    Can reference any object
    Disadvantages:
        no compilation error thrown even is passing in a list of mixed types to a wildcarded sort
            but will throw runtime exception
            Solution = create the list with type specified e.g. List<String>...
                so it won't compile if types are mixed
     */
    private class WildCardClass {
        private Deque<?> showStuff(Set<?> wildcard){
            return new ArrayDeque<>();
        }
    }

    private class BoundedWildCardClass {
        private List<?> sortList(List<? extends Comparable> list){   //a bounded wildcard; compilation error thrown if try passing in an invalid type
            //do sorting
            return new Vector<>();
        }

        private void display(int n){            System.out.println("this class can still have non-generic methods" + n);        }
    }
}
