package DataStructures.Collections;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

//maps keys to values
// doesnt maintain order of the pairs
public class MapDemo {
    public static void main(String[] args) {

        //Map is an interface, HashMap is the implementation
            //allows 1 null key, many null values
            //not thread safe
        HashMap<Integer, String> myMap = new HashMap<>();   //type cannot be primitives
        myMap.put(5, "streams"); myMap.put(9, "java"); myMap.put(3, "Servlet");
        System.out.println(myMap);
        System.out.println(myMap.get(9));
        System.out.println(myMap.containsKey(1));
        System.out.println(myMap.containsValue("python"));
        myMap.put(5, "threads");   //will overwrite old value at key 5, or add the pair if key not present
        myMap.replace(5, "API");  //will also overwrite old value, but only if the key exists. if no such key, it does nothing
        myMap.putIfAbsent(1, "reflection");  //only puts the item if key doesn't exist yet; so will never overwrite older key
        myMap.remove(3);

        HashMap<int[], byte[]> arrayMap = new HashMap<>();   //can also create maps of arrays, including primitive arrays, as all array types are reference types, even those of primitive


        Map<String, Integer> map = new HashMap<>() {{    //creating a map with values
            put("womble", 10);
            put("mungo", 1);
            put("sita", 7);
            put("kosie", 2);
            put("kato", 5);
            put("jambo", 6);
        }};
        Map<String, Integer> map2 = Map.copyOf(map);




        map.forEach((key, value) -> {   //maps forEach accepts a BiConsumer function
            System.out.println("Key = " + key + " Value = " + value);
        });
        map.entrySet().forEach(entry -> System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue()));    //entrySets forEach() accepts a Consumer function



        //Iterating maps: for loop over entries
        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            entry.setValue(entry.getValue()+1);   //creating a new map, copying the first map and adding 1 to each value
        }

        //Iterating maps: Iterator. Map doesn't have an inbuilt iterator, but entrySet's set extends Collection
        Iterator<Map.Entry<String,Integer>> mapIterator = map.entrySet().iterator();
        while (mapIterator.hasNext()){
            mapIterator.next().setValue(mapIterator.next().getValue()+1);
        }

        //Iterating maps; use forEachRemaining that performs operation on all elements
        map.entrySet().iterator().forEachRemaining(System.out::println);

        //Iterating maps: forEach
        map.entrySet().forEach(System.out::println);

        //Iterating maps with Stream.of()
        Stream.of(map.entrySet().stream().toArray()).forEach(System.out::println);
        map.entrySet().stream().forEach(System.out::println);


        //HASHTABLE disallows any null keys
            //is thread safe
        Map<Double,Character> hashTableMap = new Hashtable<>();









    }
    //Update a value in a map
    public void updateMap(String key, Integer value, Map<String, Integer> map){     //java already has input map.put() for this to overwrite a value
        if (!map.containsKey(key)) return;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                break;
            }
        }
    }
}
