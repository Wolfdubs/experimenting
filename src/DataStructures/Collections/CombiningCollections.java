package DataStructures.Collections;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CombiningCollections {
    private static final Collection<String> collection1 = Arrays.asList("womble", "mungo", "tuco");
    private static final Collection<String> collection2 = List.of("sita","jambo","kosie");


    private static void combineWithAddAll() {
        Collection<String> collection3 = new ArrayList<>();
        collection3.addAll(collection1);
        collection3.addAll(collection2);
        System.out.println(collection3);
    }

    private static <E extends String> void combineWithAddAll(Collection<E> c1, Collection<E> c2) {  //just to play around with parameterizing it
        Collection<String> collection3 = new ArrayList<>();
        collection3.addAll(c1);
        collection3.addAll(c2);
        System.out.println(collection3);
    }

    //creating a lazily concatenated Stream whose elements are all the elements of the first Stream followed by all the elements of the second Stream.
    private static void combineWithStreamConcat(){
        Collection<String> collection3 = Stream.concat(collection1.stream(),collection2.stream())
                .toList();
        System.out.println(collection3);

        //combining 3+ streams
        Stream<String> collection4 = Stream
                .concat(Stream.concat(collection1.stream(),collection2.stream()),collection3.stream());
    }

    //returns a Stream after replacing each element of this Stream with the contents of a mapped Stream that is produced by applying the provided mapping function to each element
    private static void combineWithFlatMap(){
        Stream<String> flattened = Stream.of(collection1,collection2)
                .flatMap(Collection::stream);
        Collection<String> collection3 = flattened.toList();
        System.out.println(collection3);
    }

    private static <E> Iterable<E> customConcat(Iterable<? extends  E> iterable1, Iterable<? extends  E> iterable2){
        return new Iterable<E>() {
            @NotNull
            @Override
            public Iterator<E> iterator() {
                return new Iterator<E>() {
                    Iterator<? extends E> listIterator = iterable1.iterator();
                    Boolean checkedHasNext;
                    E nextValue;
                    private boolean startMergingTheSecondCollection;

                    void theNext(){
                        if(listIterator.hasNext()){
                            checkedHasNext = true;
                            nextValue = listIterator.next();
                        } else if (startMergingTheSecondCollection){
                            checkedHasNext = false;
                        } else {
                            startMergingTheSecondCollection = true;
                            listIterator = iterable2.iterator();
                        }
                    }



                    @Override
                    public boolean hasNext() {
                        if (checkedHasNext==null){
                            theNext();
                        }
                        return checkedHasNext;
                    }

                    @Override
                    public E next() {
                        if (!hasNext())
                            throw new NoSuchElementException();
                        //checkedHasNext = null;
                        return nextValue;
                    }
                };
            }
        };
    }

    private static <E> List<E> combineWithIterable(Iterable<E> iterable){
        List<E> combinedList = new ArrayList<>();
        for (E item : iterable){
            combinedList.add(item);
        }
        return combinedList;
    }


    public static void main(String[] args) {
        combineWithAddAll();
        combineWithAddAll(collection1,collection2);
        combineWithStreamConcat();
        combineWithFlatMap();

        Iterable<String> iterable = customConcat(collection1,collection2);
        System.out.println(combineWithIterable(iterable));

    }





}


















