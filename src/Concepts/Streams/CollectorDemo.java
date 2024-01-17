package Concepts.Streams;

import utils.Weapon;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.junit.Assert.assertThat;
import static utils.Weapon.TYPE.*;
import static utils.Weapon.generateWeaponsList;

/*
provides methods for reduction -> e.g. via accumulating elements into collections, summarizing elements
e.g. to collect the elements passed through a stream and returning them in some format
Used as final step in many Streams -> perform multiple folding operations
 */
public class CollectorDemo {
    public static void main(String[] args) {
        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");

        //generating generic Lists & Sets; no specified implementation
        List<String> collectedList = givenList.stream()
                .collect(toUnmodifiableList());  //modifications throw UnsupportedOperationException

        Set<String> collectedSet = givenList.stream()
                .collect(toSet());

        //generating concrete Lists & Sets, using classes with specified implementation
        //use toCollection
        List<String> arrayList = givenList.stream()
                .collect(toCollection(ArrayList::new));
        Set<String> hashset = givenList.stream()
                .collect(toCollection(HashSet::new));

        //generating Map
        //it doesn't remove duplicates when generating keys, so would throw IllegalStateException, so do distinct()
        Map<String, Integer> stringToLengthMap = givenList.stream()
                .distinct()
                .collect(toMap(Function.identity(), String::length)); // provide a keyMapper to extract key from stream element. Function.identity just returns the same value passed in to it
                                                                    // and valueMapper to extract value for the key

        List<Weapon> weapons = generateWeaponsList();
        Map<String, Integer> WeaponNameToLethalityMap = weapons.stream()
                        .collect(Collectors.toMap(Weapon::getName, Weapon::getLethality
                        ,(key, duplicateKey) -> key));  //alternative way of avoiding ISE from duplicates; just picks one of the duplicates to discard




        //joining joins stream elements into 1 element
        String joined = givenList.stream()
                        .collect(joining());  //no separation between elements
        givenList.stream()
                        .collect(joining(" ---"));  //inserts the delimited between elements
        givenList.stream()
                        .collect(joining(" ", "PRE:", " -POST"));  //adds a delimiter between elements, and a prefix and suffix

        String joinedString = String.join("", givenList);

        //counting to count elements
        long elementCount = givenList.stream()
                        .collect(counting());

        //summarystatistics
        DoubleSummaryStatistics stringLengthStatistics = givenList.stream()
                        .collect(summarizingDouble(String::length));
        stringLengthStatistics.getAverage();
        stringLengthStatistics.getCount();
        stringLengthStatistics.getMax();
        stringLengthStatistics.getSum();

        //grouping by, to group elements by some factor
        Map<Integer, Set<String>> groupedMap = givenList.stream()
                        .collect(groupingBy(String::length, toSet()));

        //partitioningBy; create map of boolean values against collection values, based on if they match the predicate
        Map<Boolean, List<String>> partitionedMap = givenList.stream()
                        .collect(partitioningBy(s->s.length()>2));
        List<String> stringsOver2 = partitionedMap.get(true);
        List<String> stringsUnder3 = partitionedMap.get(false);
        System.out.println(stringsOver2.toString() + stringsUnder3.toString());

        //collectingAndThen: collect the elements, then perform another operation on the result
        Map<Integer,Integer> lethalitiesToCountMap = weapons.stream()
                        .collect(groupingBy(Weapon::getLethality,
                                Collectors.collectingAndThen(Collectors.counting(),
                                        Long::intValue)));





        Collectors.toList();
    }
}


