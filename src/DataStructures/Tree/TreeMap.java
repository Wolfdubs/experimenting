package DataStructures.Tree;

import utils.Pekingese;

import java.util.Map;
import java.util.Set;

/*
Inbuilt sorted map based on red black trees
 */
public class TreeMap {

    static Map<String, Pekingese> pekingeseMap = new java.util.TreeMap<>();

    public static void main(String[] args) {
        pekingeseMap.put("womle",new Pekingese("womble",14,10));
        Set<Map.Entry<String, Pekingese>> entries = pekingeseMap.entrySet();
    }

}
