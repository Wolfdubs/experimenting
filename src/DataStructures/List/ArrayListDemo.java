package DataStructures.List;

import java.util.*;

public class ArrayListDemo {



    public static void main(String[] args) {
        //creating a list via Arrays.asList() makes an immutable list, so must pass into instantiation of new list in order to mutate
        String[] dogs = {"womble", "mungo", "kato"};
        List<String> dogsList = Arrays.asList(dogs);   //makes an immutable list
        //dogsList.add("kosie");
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
            iterator.previous();
            iterator.previousIndex();
        }

        //Structural modification can throw a ConcurrentModificationException
        // so do not just remove elements via a forEach through a list, or an iterator/ListIterator that then has the list call its remove()
        //Do remove/additions via iteration and the iterators' remove(). Also streams, and removeIf
            //ITERATOR MUST BE THE ONE TO MODIFY as only it knows how to manage changed list structure
        //for lots of modifications, better to use LL as constant time
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()){
            if (iterator.next().equals("kato")){
                iterator.remove();                 //safe way to remove elements from a list
                iterator.add("jambo");      //ListIterator has method to add
            }
        }

        list.stream().map(i -> i.concat(" the dog"));          // updating elements with map()
        list.stream().filter(i -> i.length()==4);             //removing elements with filter()
        list.removeIf(i -> i.startsWith("m"));





        //for loop with iterator
        for (ListIterator<String> iteratorLooper = list.listIterator(); iteratorLooper.hasNext(); iteratorLooper.next()){
            iteratorLooper.next();
            list.set(iteratorLooper.nextIndex()-1, iteratorLooper.previous() + "extra content");
        }


    }

    List<List<List<String>>> veryDeepList = Arrays.asList(
            List.of(
                    List.of("womble","mungo"),
                    List.of("kato", "sita"),
                    List.of("jambo", "kosie")
            ),
            List.of(
                    List.of("java","python"),
                    List.of("kotlin", "pascal"),
                    List.of("javascript", "go")
            ),
            List.of(
                    List.of("dubs","trips"),
                    List.of("get", "rolling")
            )

    );


}
