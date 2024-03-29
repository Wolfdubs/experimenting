package DataStructures.Set;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Sets are much faster than lists for searching
//HashSet implements the Set interface
//Uses HashTable, so has extremely fast constant time add, remove, lookup (unlike lists that slow down as size grows as not hashed)
    //HashSet contains() = O(1)
    //HashSet used far more than TreeSet
    //TreeSet contains() = O(logn)
//Set does not maintain the order of elements (unlike Lists)
//allows only 1 null value
//Use: to remove duplicates from lists; just dump all the elements from a list into a set
public class HashSetDemo {
    public static void main(String[] args) {

        Set<String> names = new HashSet<>();
        names.add("Navin"); names.add("Pooja"); names.add("Akshay"); names.add("Jaipal"); names.add("Navin");
        names.remove("Pooja");  //cannot remove items by index as set order is random (unlike lists where you can)
        System.out.println(names);
        names.size();
        names.contains("Harveer");
        names.isEmpty();
        names.forEach(System.out::println);
        Iterator<String> iterator = names.iterator(); //returns an Iterator object
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        names.clear();


        //Using Set to remove duplicates from list
        List<Integer> numsList = new ArrayList<>(Arrays.asList(2,2,5,4,9,9,8,8,1,4,2,3));
        Set<Integer> numsSet = new HashSet<>();
        numsSet.addAll(numsList);
        Set<Integer> numsSet2 = new HashSet<>(numsList); //faster than above, creates the set using the elements of the list passed in
        System.out.println(numsSet + "\n" + numsSet2);



        Set<Integer> integerSetAnonymous = new HashSet<>() {{   //creating with values via anonymous class
            add(1); add(6); add(9); add(6); add(12);
        }};

        Set<Integer> integerSetConstructor = new HashSet<>(Arrays.asList(1,6,7,4,2,8,9,0,4,9));
        Set<Integer> integerSetStream = Stream.of(4,5,3,8,8,8,8,7,8,7,2,1,3,8,4).collect(Collectors.toSet());
        Set<Integer> integerSetStreamCollection = Stream.of(3,8,8,7,8,3,2,6,4,0,8,1,3,2,7).collect(Collectors.toCollection(HashSet::new));


    }
}

//has all same methods as a HashSet
//but orders the elements in their natural order - alphabetical/numerical
//Uses a tree, not hashset, as implementation; as elements are added it puts them in correct spot in a tree
//much slower than hashset, so only use if care about ordering
class TreeSetDemo{

    Set<Integer> tree = new TreeSet<>();

}
//maintains insertion order of elements
//almost as fast as hashset
class LinkedHashSetDemo{

}




















