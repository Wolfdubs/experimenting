package Concepts.Collections_Generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
both are interfaces
make a class that implements Iterable interface
then have a method in that class called Iterator, and create an iterator object that gives access to values in the collection
Collections (Lists, Sets, Queue) already implements Iterable
Iterator object is used for one pass through a collection one at a time
just call the iterator() method on the collection; then hasNext() and next()
 */
public class IteratorVsIterable {
    public static void main(String[] args) {
        IterableDemo<String> iterableDemo = new IterableDemo<>();
        iterableDemo.add("womble"); iterableDemo.add("mungo"); iterableDemo.add("pekingese"); iterableDemo.add("sita");
        for (String s : iterableDemo){   //can loop over custom class because it implements Iterable
            System.out.println(s);   //under the hood, this ehanced loop calls hasNext, and then class next and puts the value in s
        }

    }

}

class IterableDemo<T> implements Iterable<T> {

    private List<T> demoList = new ArrayList<>();
    public void add(T val){
        demoList.add(val);
    }


    @Override
    public Iterator<T> iterator() {
        return new customIterator<T>(demoList);
    }

    //create a custom iterator to iterate over te demoList
    public class customIterator<E> implements Iterator<E>{

        int indexPosition = 0;  //to give the Iterator a state, for tracking position in loops
        List<E> internalList;

        public customIterator(List<E> internalList){
            this.internalList = internalList;
        }

        @Override
        public boolean hasNext() {
            if (internalList.size() <= indexPosition + 1){   //if the next index position is beyond the size limit of the list; checks not OOB
                return false;
            }
            return true;
        }

        @Override
        public E next() {
            E value = internalList.get(indexPosition);
            indexPosition+=2;   //customization: could make the iterator skip positions e.g +=2
            return value;
        }
    }
}
