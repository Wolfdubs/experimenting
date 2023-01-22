package DataStructures.List.LinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ArrayListDemo {



    public static void main(String[] args) {
        //creating a list via Arrays.asList() makes an immutable list, so must pass into instatniation of new list in order to mutate
        String[] dogs = {"womble", "mungo", "kato"};
        List<String> dogsList = Arrays.asList(dogs);   //makes an immutable list
        List<String> mutableList = new ArrayList<>(Arrays.asList(dogs));
        mutableList.add("sita");    //can now add elements to the mutable list


        //modifying lists during iteration
        //Safe to modify elements in a loop, or with Unary Operator
        List<String> list = new ArrayList<>(Arrays.asList("womble","mungo","kato","sita"));
        list.replaceAll(String::toUpperCase);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).repeat(2));
        }
        for (String string: list) {
            list.set(list.indexOf(string), list.get(list.indexOf(string)) +" the dog");
        }
        for (final ListIterator<String> iterator = list.listIterator(); iterator.hasNext();){
            final String element = iterator.next();
            list.set(iterator.nextIndex(), element);
        }

        //Structural modification can be risky;
        list.stream().map(i -> i.concat(" the dog"));          // updating elements with map()
        list.stream().filter(i -> i.length()==4);             //removing elements with filter()
        list.removeIf(i -> i.startsWith("m"));


    }


}
