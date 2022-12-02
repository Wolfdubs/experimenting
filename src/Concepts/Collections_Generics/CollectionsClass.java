package Concepts.Collections_Generics;

import java.util.*;

/*
Collection interface used to create collections of elements
    ArrayList, Set, HashMap, Map, Comparator, Comparable
    Collection interface -> List interface -> ArrayList class
    has inbuilt methods e.g. size(), contains(), isEmpty(), iterator(), toArray(), add() returns boolean, remove()
Collections in-built class
 */
public class CollectionsClass {

    public static void main(String[] args) {

        //COLLECTION INTERFACE
        Collection collectionValues = new ArrayList();  //reference of Collection interface using implementation from ArrayList class. cannot just create object of interface
        Collection<Integer> values1 = new ArrayList<>(); //Can specify List type with <>. Else by default, type = Object, so can add any type.
        collectionValues.add(5); collectionValues.add(9); collectionValues.add(2);  //no way to add elements in middle due to no indices

        //Iterate over Collection via Iterator interface. cannot use for loop as Collection doesn't have indices
        Iterator it = collectionValues.iterator();   //returns an Iterator object to iterate over the values collection, loading its elements into the iterator
        while (it.hasNext()){  //checks if there is a next value
            System.out.println(it.next());  //fetches the next value
        }

        //LIST INTERFACE
        //Collection reference doesn't support indexing, so cannot get/add/remove values at specific index.
        // Must use reference to List interface (which extends Collection interface)
        List listValues = new ArrayList<>();   //creates a mutable object whose values can be changed
        listValues.add(7); listValues.add("ee3ed3"); listValues.add(true); //can add any type of object if List <> not specified, using wrapper classes
        listValues.add(1,3);  //can add elements to List at specific index. shifts later indices along by 1
        //can use Iterator with List, but also just use for loops over indices
        for (int i=0; i<listValues.size(); i++){
            System.out.println(listValues.get(i));  //use .get() to fetch value at specific index
        }


        //GENERICS
        //makes Collection of specific type, to make it type-safe
        // Collections, and its sub-interfaces e.g. List, accept generics which default as Object, unless you specify the concrete with <>
        List<Integer> listValuesInts = new ArrayList<>();
        listValuesInts.add(4); listValuesInts.add(1); listValuesInts.add(13); listValuesInts.add(8);
        //sort a Collection interface using static sort() from Collections class
        Collections.sort(listValuesInts);
        Collections.reverse(listValuesInts);   //to reverse the list
        Collections.copy(listValuesInts, listValues);



        //SET INTERFACE
        // all elements must be unique
        //HashSet: using hashing algorithm to stores elements in random order in heap memory
        Set<Integer> uniqueValues = new HashSet<>();   //HashSet is class that implements the Set interface we referenced
        uniqueValues.add(2); uniqueValues.add(18); uniqueValues.add(70);
        uniqueValues.add(6); uniqueValues.add(6);   //the 2nd time adding 6, it won't actually add it, that add() will return false
        for (int i : uniqueValues){
            System.out.print(i + " ");}

        //TreeSort: maintains the sorted ascending order
        Set<Integer> treeValues = new TreeSet<>();  //TreeSet sorts the elements inside



        //MAP INTERFACE
        //Just like a list, but uses unique key instead of plain indices to access values
        // Map is interface, implementation classes are HashMap and HashTable
        //HashMap: not thread safe. allows multiple null values
                //hashing will not maintain sequence you added elements
                //keys are stored in keySet() so no duplicated allowed
        Map<String, String> mapValues = new HashMap<>();
        mapValues.put("company","google"); mapValues.put("country", "USA"); mapValues.put("food","cheese");
        mapValues.put("sport", "baseball"); mapValues.put("food", "pizza");  //latest put will override value for earlier
        System.out.println(mapValues.get("country"));
        for (String key : mapValues.keySet()){   //iterate over all the keys in the HashMap
            System.out.println(mapValues.get(key));
        }

        Set<String> myKeySet = mapValues.keySet();  //extract all the keys and save in a set
        System.out.println(myKeySet);

        //HashTable: thread safe/synchronized, and disallows any null keys
        Map<Integer, String> hashtableValues = new Hashtable<>();
    }
}
