package DataStructures;

import java.util.HashMap;

//maps keys to values
// doesnt maintain order of the pairs
public class Map {
    public static void main(String[] args) {

        //Map is an interface, HashMap is the implementation
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

    }
}
