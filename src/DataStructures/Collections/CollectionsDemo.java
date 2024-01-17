package DataStructures.Collections;

import Concepts.Basics.Variables.EnumDemo;
import org.junit.Assert;
import utils.Pekingese;
import utils.Weapon;

import java.util.*;
import java.util.Collections;

/*
Collection interface used to create collections of elements
    ArrayList, Set, HashMap, Map, Comparator, Comparable
    Collection interface -> List interface -> ArrayList class
    has inbuilt methods e.g. size(), contains(), isEmpty(), iterator(), toArray(), add() returns boolean, remove()
Collections in-built class
To retrieve custom Objects from HashTable, must override the equals() and hashCode() methods for your custom object, else java will see that the object
you pass in to search for isn't the same reference as the object in the table, so returns nothing and can throw exception
    because hashTables get() uses the equals() of the object to get its equivalent, so must override Object class' default to check the values
    must override equals() and hashCode() together as they work together
 */
public class CollectionsDemo {

    public static void main(String[] args) {

        //COLLECTION INTERFACE
        Collection collectionValues = new ArrayList();  //reference of Collection interface using implementation from ArrayList class. cannot just create object of interface
        Collection<Integer> values1 = new ArrayList<>(); //Can specify List type with <>. Else by default, type = Object, so can add any type.
        collectionValues.add(5);
        collectionValues.add(9);
        collectionValues.add(2);  //no way to add elements in middle due to no indices

        //Iterate over Collection via Iterator interface. cannot use for loop as Collection doesn't have indices
        Iterator<?> it = collectionValues.iterator();   //returns an Iterator object to iterate over the values collection, loading its elements into the iterator
        while (it.hasNext()) {  //checks if there is a next value
            System.out.println(it.next());  //fetches the next value
        }

        //LIST INTERFACE
        //Collection reference doesn't support indexing, so cannot get/add/remove values at specific index.
        // Must use reference to List interface (which extends Collection interface)
        List listValues = new ArrayList<>();   //creates a mutable object whose values can be changed
        boolean added = listValues.add(7);
        listValues.add("ee3ed3");
        listValues.add(true); //can add any type of object if List <> not specified, using wrapper classes
        listValues.add(1, 3);  //can add elements to List at specific index. shifts later indices along by 1
        //can use Iterator with List, but also just use for loops over indices
        for (int i = 0; i < listValues.size(); i++) {
            System.out.println(listValues.get(i));  //use .get() to fetch value at specific index
        }
        /*
        ArrayList = array is underlying data structure. not thread safe
        LinkedList = linked nodes as underlying data structure. not thread safe
        Vector = same as arraylist, with underlying array. but is thread safe
        Best to initialize with List declaration to expose functionalities of the interface
         */
        List<String> vector = new Vector<>();


        //GENERICS
        //makes Collection of specific type, to make it type-safe
        // Collections, and its sub-interfaces e.g. List, accept generics which default as Object, unless you specify the concrete with <>
        List<Integer> listValuesInts = new ArrayList<>();
        listValuesInts.add(4);
        listValuesInts.add(1);
        listValuesInts.add(13);
        listValuesInts.add(8);
        //sort a Collection interface using static sort() from Collections class
        java.util.Collections.sort(listValuesInts);
        java.util.Collections.reverse(listValuesInts);   //to reverse the list
        Collections.copy(listValuesInts, listValues);


        /*SET INTERFACE
            all elements must be unique
            2 implementations:
                HashSet = using hashing algorithm to stores elements in random order in heap memory
                    backed by hashtable as underlying implementation
                    insertion order not maintained
                LinkedHashSet = ordered version of HashSet using doubly LinkedList as implementation
            SortedSet interface extends Set
                TreeSet implements SortedSet; sorts items by ascending order by default
        */

        Set<Integer> uniqueValues = new HashSet<>();   //HashSet is class that implements the Set interface we referenced
        uniqueValues.add(2);uniqueValues.add(18);uniqueValues.add(70); uniqueValues.add(6);
        Assert.assertFalse(uniqueValues.add(6));   //the 2nd time adding 6, it won't actually add it, that add() will return false
        for (int i : uniqueValues) {            System.out.print(i + " ");        }

        Set<Integer> linkedHashSet = new LinkedHashSet<>();

        Comparator<String> comparator = Comparator.comparingInt(String::length);

        SortedSet<Integer> treeValues = new TreeSet<>();  //TreeSet sorts the elements inside by ascending order
        SortedSet<String> sortedSet = new TreeSet<>(comparator); //specify the comparator to use
        Set<String> myStrings = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // Define comparing logic here
                return o1.compareTo(o2);
            }
        });
        Set<String> myStringsSet = new TreeSet<>((o1, o2) -> o1.compareTo(o2));
        Set<Weapon> stringSet = new TreeSet<>(Weapon::sortByLethality);



        /*MAP INTERFACE
            Just like a list, but uses unique key instead of plain indices to access values
            keys are stored in keySet() so no duplicates allowed
            Implementation classes:
                HashMap: basic general purpose implementation
                    unsynchronized so not thread safe.
                    allows multiple null values, only 1 null key
                    hashing will not maintain sequence you added elements
                HashTable: allows no null keys
                    synchronized, so thread safe
                SortedMap interface implemented by TreeMap
                    TreeMap = sorts elements by keys. not synchronized
                EnumMap = optimized for Enum keys, only has a single Enum type as keys. less chance of collisions for enum keys vs hashmap with enums
                    elements ordered in natural order of map keys (the order in which enums are declared in the enum type
                    not thread safe
                    iterator does not throw ConcurrentModificationException when modifying map during iteration
                    no null keys, but many null values*/

        Map<String, String> mapValues = new HashMap<>();
        mapValues.put("company", "google");
        mapValues.put("country", "USA");
        mapValues.put("food", "cheese");
        mapValues.put("sport", "baseball");
        mapValues.put("food", "pizza");  //latest put will override value for earlier
        System.out.println(mapValues.get("country"));
        for (String key : mapValues.keySet()) {   //iterate over all the keys in the HashMap
            System.out.println(mapValues.get(key));
        }

        Set<String> myKeySet = mapValues.keySet();  //extract all the keys and save in a set
        System.out.println(myKeySet);

        Set<Map.Entry<String, String>> mapEntries = mapValues.entrySet();
        for (Map.Entry<String,String> entry : mapEntries){
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.equals("womble")) mapEntries.remove(value);
        }

        Collection<String> stringValues = mapValues.values();

        Map<Integer, String> hashtableValues = new Hashtable<>();          //HashTable: thread safe/synchronized, and disallows any null keys

        Map<String, Float> treeMap = new TreeMap<>();
        SortedMap<Pekingese,Weapon> weaponizedPekingese = new TreeMap<>();
        Map<EnumDemo, Integer> enumMap = new EnumMap<>(EnumDemo.class);
        enumMap.put(EnumDemo.HORSE, 3);
        Map<EnumDemo, Integer> threadSafeEnumMap = Collections.synchronizedMap(enumMap);     //create thread safe reference of the enumMap


        /*STACK inbuilt class
            extends Vector, so is a synchronized list with an array underpinning it
            cannot declare as List type, else won't have push, pop or peek
            Disadvantages -> use Dequeue instead
                inconsistent - can access any item in the stack, not just top
                synchronized; if not needed then means unjustified performance degradation*/
        Stack<Integer> stack = new Stack<>();
        stack.push(18);
        Integer i = stack.get(5);   //bad as can access any index

        /*QUEUE inbuilt interface
            4 implementations:
                ArrayDeque = standard queue implementation using array. not synchronized
                Priority Queue = dequeues items by priority rather than insertion order
                LinkedList = uses LL as underlying implementation, but must declare as Queue type. able to use as LL implements Deque interface
                Deque = double ended queue so accessible from both ends
                    offers LIFO by default but also gives access to FIFO
                    Can instantiate via LL or ArrayDeque implementation
                    Better than Stack because:
                        flexibility of LL & AD implementations
                        consistent - only allows access to top items as defined in Stack abstract data type (cannot do a get of a random index)
                        not synchronized; but can be made thread safe */
        Queue<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.add("hi");
        arrayDeque.remove();
        arrayDeque.peek();
        arrayDeque.poll();
        arrayDeque.element();
        arrayDeque.offer("werd");
        Queue<String> priorityQueue = new PriorityQueue<>();
        Queue<String> linkedlistQueue = new LinkedList<>();
        Deque<String> dequeQueue = new ArrayDeque<>();
        dequeQueue.push("holla");
        dequeQueue.pop();
        dequeQueue.poll();
        dequeQueue.peek();
        Deque<String> linkedDequeQueue = new LinkedList<>();   //USE THIS AS A STACK, as best for LIFO operations
        linkedDequeQueue.push("womble");
        // linkedDequeQueue.get(3);   //attempting to access a random index throws compile error; can only access 1st or last indices




    }
}




/*
Collections operate directly on object references (not for primitives through(?), so if you update an object values, dont need to put it back again
*/


class GuideLeader{
    private String name;
    public GuideLeader(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuideLeader that = (GuideLeader) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

 class GirlGuide{
    private String name;
    public GirlGuide(String name){
        this.name = name;
    }
}

class GuidesRunner {
    public static void main(String[] args) {
        Map<GuideLeader, ArrayList<GirlGuide>> troops = new HashMap<>();

        //set up guiders
        GuideLeader vanessa = new GuideLeader("Vanessa");
        GuideLeader trixie = new GuideLeader("Trixie");
        GirlGuide chloe = new GirlGuide("Chloe");
        GirlGuide kira = new GirlGuide("Kira");

        //set up troops
        troops.put(vanessa, new ArrayList<GirlGuide>());
        troops.put(trixie, new ArrayList<GirlGuide>());

        //get GirlGuides into troops
        ArrayList<GirlGuide> fortyNinthGuides = troops.get(trixie);
        fortyNinthGuides.add(chloe); //don't then need to put() back into the map, as the add() will operate directly on the object already
        // retrieved from inside the map from prior lines get()
        troops.put(vanessa,fortyNinthGuides);    //this then also puts a reference to that same object in the map under the vanessa object key
        fortyNinthGuides.add(kira);            //thus is added to the arraylist for both keys in the map
        troops.get(trixie).remove(chloe);     //and thus is removed from the arraylist for both keys that refer to the same object map value
        ArrayList<GirlGuide> eighteenthGuides = troops.get(new GuideLeader("Trixie"));   //uses the overriden equals() to retrieve the list for the key equivalent to the new object passed in

    }
}




