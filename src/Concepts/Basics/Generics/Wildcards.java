package Concepts.Basics.Generics;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private class UpperBoundedWildCardClass {
        private List<?> sortList(List<? extends Comparable> list){   //a bounded wildcard; compilation error thrown if try passing in an invalid type
            //do sorting
            return new Vector<>();
        }

        private void display(int n){            System.out.println("this class can still have non-generic methods" + n);        }
    }

    //lower bounded wildcard
    private class LowerBoundedWilcards{
        private static void printOnlyIntegerClassorSuperClass(List<? super Integer> list) {   //only operates on those wildcarded elements that are a superclass of Integer
            System.out.println(list);
        }
        public static void main(String[] args) {
            List<Integer> list1 = Arrays.asList(4, 5, 6, 7);
            printOnlyIntegerClassorSuperClass(list1);
        }
    }

    class WildcardsVsGenerics<T>{

        private <T> List<? extends T> mergeWild(List<? extends T> list1, List<? extends T> list2){   //Don't use this as the return type is too vague
            return Stream.concat(list1.stream(),list2.stream()).collect(Collectors.toList());
        }

        private <T> List<T> mergeGeneric(List<? extends T> list1, List<? extends T> list2) { //When a generic method returns a generic type, we should use a type parameter instead of a wildcard:
            return Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());
        }

        public static long sum(List<Number> nums) {
            return nums.stream().mapToLong(Number::longValue).sum();
        }

        public static long sumWildcardUpperBound(List<? extends Number> nums) {
            return nums.stream().mapToLong(Number::longValue).sum();
        }

        //can do the same as sumWildcard with type parameters
        public static <T extends Number> long sumGenericUpperBound(List<T> nums) {  //argument type is limited with an upper bound (<T extends Number>).
            return nums.stream().mapToLong(Number::longValue).sum();
        }

        //method to sum only Integers and parent types (so no longs, floats, etc), only Integer, Number, Object
        //Can only do lower bounds with wildcards
        public static void sumLowerBound(List<? super Integer> nums, Integer num) {
            nums.add(num);
        }
        /*
        If we are unsure whether we should use the upper or lower bound, we can think about the PECS – Producer Extends, Consumer Super.
        Whenever our method consumes a collection, for example adding elements, we should use a lower bound. On the other hand,
        if our method only reads elements, we should use the upper bound. Additionally, if our method does both, i.e., it produces
        and consumes a collection, we can’t apply the PECS rule and should use unbounded types instead.
         */

        //Wildcarded Swap Method
        private static void swap(List<?> list, int srcIndex, int destIndex){
            //list.set(srcIndex, list.set(destIndex,list.get(srcIndex)));  //set() method is called, the compiler isn’t able to determine the type of object being inserted into the list. Therefore it produces an error. This way, Java enforces type safety at compile time
        }

        private static <E> void swapGeneric(List<E> list, int srcIndex, int destIndex){
            list.set(srcIndex, list.set(destIndex, list.get(srcIndex)));  //method knows that list is a List<E>. Therefore, it knows any value it gets out of this list is of type E and it’s safe to put any value of type E back into the list.
        }


        public static void main(String[] args) {
            List<Integer> ints = new ArrayList<>(List.of(5,3,8,9,10,2));
            List<Number> numbers = new ArrayList<>(Arrays.asList(4L,71L,3f,2,9f,2.9f,295.3,6));
            //sum(ints);   //compile error as List<Long> isn’t a subtype of List<Number>
            sum(numbers);   //works fine as it's the expected List<Number>

            //Solve this problem with wildcards with upper bound
            sumWildcardUpperBound(ints);  // the List<Integer> is a type of the List<? extends Number> which makes the call of the method valid and type-safe.
        }
    }
}

/*
Wildcards cannot define a generic class or interface
Generics signature says: list1 is a List of Es.
Wildcard signature says: list is a List of instances of some type, but we don't know the type.
Benefits:
    caller has to know less about the object he passes in. For example if I have a Map of Lists: Map<String, List<?>> I can pass its values to your function without specifying the type of the list elements.
    So if I hand out objects parameterized like this I actively limit what people know about these objects and what they can do with it (as long as they stay away from unsafe casting).
    wildcards can have lower bounds, so with lists you can make the add method work, while get doesn't give you anything useful. Of course that triggers the next question: why don't generics have lower bounds?
9

Generics make the collection more type safe.
    List<E> : E here is the Type Parameter, which can be used to determine the content type of the list, but there was No way to check what was the content during the runtime.
Generics are checked only during compilation time.
<? extends String> : This was specially build into java, to handle the problem which was with the Type Parameter. "? extends String" means this
    List can have objects which IS-A String.
    For eg:
        Animal class
            Dog class extends Animal
            Tiger class extends Animal
        So using "public void go(ArrayList<Animal> a)" will NOT accept Dog or Tiger as its content but Animal.

        "public void go(ArrayList<? extends Animal> a)" is whats needed to make the ArrayList take in Dog and Tiger type.

----------------------------------------------
There are certain places where wildcards and type parameters do the same thing. But there are also certain places where you have to use type parameters.

If you want to enforce some relationship on the different types of method arguments, you can't do that with wildcards, you have to use type parameters.
Taking your method as an example, suppose you want to ensure that the src and dest list passed to the copy() method should be of the same parameterized
type, you can do it with type parameters like so:
    public static <T extends Number> void copy(List<T> dest, List<T> src)

Here, you are ensured that both dest and src have the same parameterized type for List. So, it's safe to copy elements from src to dest.

But, if you go on to change the method to use wildcards:
    public static void copy(List<? extends Number> dest, List<? extends Number> src)

it won't work as expected. In 2nd case, you can pass List<Integer> and List<Float> as dest and src. So, moving elements from src to dest wouldn't be
type-safe anymore. If you don't need such kind of relation, then you are free not to use type parameters at all.

Some other differences between using wildcards and type parameters are:

If you have only one parameterized type argument, you can use a wildcard, although type parameter will also work.

Type parameters support multiple bounds, wildcards don't.

Wildcards support both upper and lower bounds, type parameters only support upper bounds. So, if you want to define a method that takes a List of type Integer or it's superclass, you can do:

 public void print(List<? super Integer> list)  // OK
but you can't use a type parameter:

     public <T super Integer> void print(List<T> list)  // Won't compile
------------------
. We can’t use wildcards directly to specify the type of a parameter in a method. ie cannot say methodname(? variablename)
The only place we can use them is as part of a generic code, for example, as a generic type argument defined in the angle brackets.

There’s often a case when we can declare a generic method using either wildcards or type parameters. For instance, here are two possible declarations of a swap() method:

public static <E> void swap(List<E> list, int src, int des);
public static void swap(List<?> list, int src, int des);

The first method uses an unbounded type parameter while the second one uses an unbounded wildcard. Whenever we have an unbounded generic type, we should prefer the second declaration.

Wildcards make code simpler and more flexible. We can pass any list and, additionally, we don’t have to worry about type parameters. If a type parameter appears only once in the method declaration, we should consider replacing it with a wildcard. The same rule applies to bounded type parameters as well.

Without upper or lower bounds, the wildcard represents “any type”, or a type of unknown. Its purpose is to allow a variety of actual argument types to be used at different method invocations. Moreover, wildcards are designed to support flexible subtyping.



----------------
We can use wildcards with bounds in three ways:

Unbounded Wildcards: List<?> – represents a list of any type
Upper Bounded Wildcards: List<? extends Number> – represents a list of Number or its subtypes (for instance, Double or Integer).
Lower Bounded Wildcards: List<? super Integer> – represents a list of Integer or its supertypes, Number, and Object
On the other hand, a bounded parameter type is a generic type that specifies a bound for a generic. We can bound type parameters in two ways:
Unbounded Type Parameter: List<T> represents a list of type T
Bounded Type Parameter: List<T extends Number & Comparable> represents a list of Number or its subtypes such as Integer and Double that implement the Comparable interface
We can’t use type parameters with the lower bound. Furthermore, type parameters can have multiple bounds, while wildcards can’t.
 */
















