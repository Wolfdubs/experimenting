package Concepts.Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CombiningCollections {



    public static void main(String[] args) {
        Collection<String> collection1 = List.of("ABC","DEF");
        Collection<String> collection2 = Arrays.asList("UVW","XYZ");
        Collection<String> collection3 = new ArrayList<>(List.of(new String[]{"CIA", "FBI", "NSA"}));
        Stream<String> combinedCollectionStream = Stream.concat(Stream.concat(collection1.stream(), collection2.stream()), collection3.stream());

        //flatMap returns a stream after replacing each element with a value returned from the mapping function
        Stream<String> flattenedStream = Stream.of(collection1, collection2, collection3)
                .flatMap(Collection::stream);
        Collection<String> flattenedCollection = flattenedStream
                .toList();
    }
}
