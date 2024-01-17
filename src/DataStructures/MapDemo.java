package DataStructures;

import java.util.HashMap;
import java.util.LinkedList;

public class MapDemo {
}

class HashMapCustomImplementation {
//an array, each index contains a LL

    LinkedList<Integer>[] customHashMap = new LinkedList[10];

    public HashMapCustomImplementation() {
        for (int i = 0; i < customHashMap.length; i++) {
            customHashMap[i] = new LinkedList<>();
        }
    }
}
